package controller.views;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import alertas.AlertasGerais;
import bancoDeDados.Dados;
import excecoes.InputsIncorretos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cardapio;
import model.Cliente;
import model.ProdutoGeral;
import model.Vendas;
import model.facade.GerenciadorDeVendas;
import uteis.UteisGeral;

public class VendasTelaEditarController implements Initializable {
	
	//CardapioTelaCadastrarController cardDados = new CardapioTelaCadastrarController();
		private List<Cardapio> listaDeCardapio = Dados.getListaCardapio();
		private List<ProdutoGeral> listaDeProdutos = Dados.getListaProdutosGeral();
		private List<Cliente> listaDeClientes = Dados.getListaCliente();
		private GerenciadorDeVendas gdv = new GerenciadorDeVendas();
		private UteisGeral uteisGeral = new UteisGeral();
		private AlertasGerais alertasGerais = new AlertasGerais();
		
		private List<String> listaPratosCardapio;
		private List<String> listaNomesClientes;
		private List<String> listaPratosVenda;
		private Vendas vendas = null;
		private HashMap<String, Double> listaItensVendas = new HashMap<String, Double>();
		private ObservableList<String> obsDadosVendas;
		double valorTotal;
		HashMap<String, Double> somaProdutos;
		boolean removerEstoque;

    @FXML
    private DatePicker campoData;

    @FXML
    private TextField campoFormaDePagamento;

    @FXML
    private TextField campoHora;

    @FXML
    private TextField campoMinutos;

    @FXML
    private TextField campoQuantidade;

    @FXML
    private TextField campoTodosOsItens;

    @FXML
    private ChoiceBox<String> choiceBoxAddVendas;

    @FXML
    private ChoiceBox<String> choiceBoxCliente;

    @FXML
    private ChoiceBox<String> choiceBoxRemoverPrato;

    @FXML
    private Label exibirPreco;

    @FXML
    void botaoAddPrato(ActionEvent event) {
    	try {
			String nomePrato = uteisGeral.verificarChoiceBoxString(choiceBoxAddVendas);
			String todosOsPratos = "";
			//COPIA
			String verifQuant = uteisGeral.verificarTextField(campoQuantidade);
    		
    		Double quant =  Double.parseDouble(verifQuant);
    		
    		if (!listaItensVendas.containsKey(nomePrato)) {
    			listaItensVendas.put(nomePrato, quant);
    		} else {
    			//nesse caso está somando caso o usuario adicione o msm item mais de uma vez
    			//mas tambem pode ser alterado para que a quantidade do item mude para a mais recente ao inves de adicionar
    			double controle = 0;
    			controle = listaItensVendas.get(nomePrato);
    			listaItensVendas.remove(nomePrato);
    			controle = quant;
    			listaItensVendas.put(nomePrato, controle);
    			//alertasFornecedor.alertaNomeJaExistente();
    		}
    		
    		this.valorTotal = gdv.conferirVenda(listaDeCardapio, listaItensVendas);
    		if (valorTotal == -1) {
    			listaItensVendas.remove(nomePrato);
    			throw new InputsIncorretos("Prato não presente no estoque!!!");
    		}
    		
    		this.exibirPreco.setText("R$ " + String.valueOf(valorTotal));
    		for (String prato : listaItensVendas.keySet()) {
    			todosOsPratos += prato +" : "+ listaItensVendas.get(prato) +", ";
			}
    		
			int fim = todosOsPratos.length() - 2;
			todosOsPratos = todosOsPratos.substring(0, fim);
    		
			campoTodosOsItens.setText(todosOsPratos);
			
			//listaCopia.remove(produto);
			carregarPratosVendasChoiceBox();
			carregarRemoverPratoChoiceBox();
			//carregarNomeCardapioListagem();
		} catch(InputsIncorretos ii) {
			alertasGerais.faltaDadosOuIncorretos();}
    }

    @FXML
    void botaoCancelar(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoConfirmarEdicao(ActionEvent event) {
    	
    		HashMap<String, Object> dados = juntarInformacoes();
    		
    	try {
    		
    		
    		boolean sucesso = gdv.editarVendas(vendas, dados);
    		System.out.println(vendas.getHorario());
    		System.out.println("Aaaaa");
    		if (sucesso) {
    			//System.out.println("bbbbbb");
    			alertasGerais.informarSucessoOperacao();
    			Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	    	stage.close();
    		}
    		
    	} catch (NullPointerException npe) {
    		alertasGerais.erroNaOperacao();
		} 
    	catch(ClassCastException cce) {
			alertasGerais.erroNaOperacao();
		}

    }

    @FXML
    void botaoRemoverPrato(ActionEvent event) {
    	try {
			String prato = uteisGeral.verificarChoiceBoxString(choiceBoxRemoverPrato);
			//COPIA
			//listaPratosVenda.remove(prato);
			listaItensVendas.remove(prato);
			carregarPratosVendasChoiceBox();
			carregarNomeCardapioListagem();
			carregarRemoverPratoChoiceBox();
		} catch(InputsIncorretos ii) {
			alertasGerais.faltaDadosOuIncorretos();
		} catch(NullPointerException npe) {
			alertasGerais.faltaDadosOuIncorretos();
		}
    }
    
    public void carregarPratosVendasChoiceBox() {
 		if (listaPratosCardapio.size() > 0) {
 			obsDadosVendas = FXCollections.observableArrayList(listaPratosCardapio);
 			choiceBoxAddVendas.setItems(obsDadosVendas);
 		}	
 	}
    
    public void carregarClientesVendasChoiceBox() {
 		if (Dados.getListaCliente().size() > 0) {
 			obsDadosVendas = FXCollections.observableArrayList(listaNomesClientes);
 			choiceBoxCliente.setItems(obsDadosVendas);
 		}	
 	}
    
    public void carregarRemoverPratoChoiceBox() {
		if (listaItensVendas.size() > 0) {
			obsDadosVendas = FXCollections.observableArrayList(listaItensVendas.keySet());
			choiceBoxRemoverPrato.setItems(obsDadosVendas);
		}	
	}
    
    
    public void carregarNomeCardapioListagem() {
		if (listaItensVendas.size() > 0) {
			String todosOsPratos = "";
			for (String produto : this.listaItensVendas.keySet()) {
				todosOsPratos += produto + ", ";
			}
			int fim = todosOsPratos.length() - 2;
			todosOsPratos = todosOsPratos.substring(0, fim);
			campoTodosOsItens.setText(todosOsPratos);
		} else {
			campoTodosOsItens.setText("Vazio");
		}

    }
    
    public void adicionarInformacoes(LocalDate data, String hora, String Pagamento, String preco, HashMap<String, Double> pratos, Vendas venda) {
		
    	campoData.setValue(data);
    	campoFormaDePagamento.setText(Pagamento);
    	campoHora.setText(hora.substring(0, 2));
    	campoMinutos.setText(hora.substring(3, 5));
    	choiceBoxCliente.setValue(venda.getClienteVinculado());
    	exibirPreco.setText(preco);
    	this.listaItensVendas = pratos;
    	this.vendas = venda;
    	System.out.println(this.vendas.getData());
    	
    	//listaPratosVenda = new ArrayList<>(this.listaItensVendas.keySet());
    	
		listaPratosCardapio = new ArrayList<>();
		listaNomesClientes = new ArrayList<>();
		
    	for (Cardapio prato : listaDeCardapio){
			this.listaPratosCardapio.add(prato.getNome());
			
			
		}
    	for (Cliente clientes : listaDeClientes ) {
    		this.listaNomesClientes.add(clientes.getNome());
    	}
		
		carregarPratosVendasChoiceBox();
		carregarClientesVendasChoiceBox();
		carregarNomeCardapioListagem();
		carregarRemoverPratoChoiceBox();
	}
    
    public HashMap<String, Object> juntarInformacoes() {
		HashMap<String, Object> dados = new HashMap<String, Object>();
		
    	try {
    		
			String pagamento = uteisGeral.verificarTextField(campoFormaDePagamento);
			String confirmacaoItens = uteisGeral.verificarTextField(campoTodosOsItens);
			String confirmaCliente = uteisGeral.verificarChoiceBoxString(choiceBoxCliente);
			String hora = uteisGeral.verificarTextField(campoHora);
			String minutos = uteisGeral.verificarTextField(campoMinutos);
			String horario = hora + ":" + minutos;
			String data = uteisGeral.validarData(campoData);
			
			dados.put("modoPagamento", pagamento);
			dados.put("preco", this.valorTotal);
			dados.put("pratos", this.listaItensVendas);
			dados.put("cliente", confirmaCliente);
			dados.put("data", data);
			dados.put("horario", horario);
			
			
			
		} catch (InputsIncorretos e) {
			alertasGerais.faltaDadosOuIncorretos();
		} catch(NullPointerException npe) {
			System.out.println("Entrou aqui");
		}
    	return dados;
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		uteisGeral.validarCampoNumerico(campoHora);
		uteisGeral.validarCampoNumerico(campoMinutos);
		uteisGeral.validarCampoNumerico(campoQuantidade);
		
	}

}
