package controller.views;


import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cardapio;
import model.Cliente;
import model.ProdutoGeral;
import javafx.scene.control.TextField;
import model.Vendas;
import model.facade.GerenciadorDeVendas;
import uteis.UteisGeral;

public class VendasTelaCadastrarController implements Initializable {
	
	//CardapioTelaCadastrarController cardDados = new CardapioTelaCadastrarController();
	private List<Cardapio> listaDeCardapio = Dados.getListaCardapio();
	private List<ProdutoGeral> listaDeProdutos = Dados.getListaProdutosGeral();
	private List<Cliente> listaDeClientes = Dados.getListaCliente();
	private GerenciadorDeVendas gdv = new GerenciadorDeVendas();
	private UteisGeral uteisGeral = new UteisGeral();
	private AlertasGerais alertasGerais = new AlertasGerais();
	private MudarTelaController mtc = new MudarTelaController();
	
	private List<String> listaPratosCardapio;
	private List<String> listaNomesClientes;
	private Vendas vendas = null;
	private HashMap<String, Double> listaItensVendas = new HashMap<String, Double>();
	private ObservableList<String> obsDadosVendas;
	double valorTotal;
	HashMap<String, Double> somaProdutos;
	boolean removerEstoque;
	
    @FXML
    private TextField campoFormaDePagamento;

    @FXML
    private TextField campoItens;

    @FXML
    private TextField campoQuantidade;

    @FXML
    private TextField campoTodosOsItens;

    @FXML
    private ChoiceBox<String> choiceBoxAddVendas;
    
    @FXML
    private ChoiceBox<String> choiceBoxAddCliente;

    @FXML
    private Button idBotaoFechar;
    

    @FXML
    private Label exibirPreco;


    @FXML
    void botaoCancelar(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoConfirmarCadastro(ActionEvent event) {
    	this.somaProdutos = gdv.somarProdutos(listaDeCardapio, listaItensVendas);
    	this.removerEstoque = gdv.removerDoEstoque(listaDeProdutos, somaProdutos);
    	if (!this.removerEstoque) {
    		System.out.println("COLOCAR ALERTA DE VENDA NÃO EXECUTADA POR FALTA DE PRODUTOS NO ESTOQUE");
    	}
    	else {
	    	HashMap<String, Object> dados = juntarInformacoes();
	    	try {
	    		if (dados != null) {
	    			boolean sucesso = gdv.cadastrarVendas(dados);
	    			if (sucesso) {
	    				alertasGerais.informarSucessoOperacao();
	                	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
	                	mtc.abrirNovaJanela("/view/listagem/NotaFiscalCliente.fxml", stage, false);
	                	stage.close();
	    			} else {
	    				alertasGerais.erroNaOperacao();
	    			}
	    		}
	    	} catch(NullPointerException npe) {
	    		alertasGerais.erroNaOperacao();
	    	} catch (ClassCastException cce) {
	    		alertasGerais.erroNaOperacao();
			}
    	}
    }

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
			//carregarNomeCardapioListagem();
		} catch(InputsIncorretos ii) {
			alertasGerais.faltaDadosOuIncorretos();}
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
 			choiceBoxAddCliente.setItems(obsDadosVendas);
 		}	
 	}
    
    public void adicionarInformacoes() {
		
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
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		adicionarInformacoes();
		uteisGeral.validarCampoNumerico(campoQuantidade);
		
	}
	
	public HashMap<String, Object> juntarInformacoes() {
		HashMap<String, Object> dados = null;
    	try {
    		
			String pagamento = uteisGeral.verificarTextField(campoFormaDePagamento);
			String confirmacaoItens = uteisGeral.verificarTextField(campoTodosOsItens);
			String confirmaCliente = uteisGeral.verificarChoiceBoxString(choiceBoxAddCliente);
			dados = new HashMap<>();
			dados.put("modoPagamento", pagamento);
			dados.put("preco", this.valorTotal);
			dados.put("pratos", this.listaItensVendas);
			dados.put("cliente", confirmaCliente);
		} catch (InputsIncorretos e) {
			alertasGerais.faltaDadosOuIncorretos();
		} catch(NullPointerException npe) {
			System.out.println("Entrou aqui");
		}
    	return dados;
	}
}
