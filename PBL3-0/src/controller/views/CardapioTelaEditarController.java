package controller.views;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import alertas.AlertasFornecedor;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cardapio;
import model.Fornecedor;
import model.ProdutoGeral;
import model.facade.GerenciadorDeCardapio;

import uteis.UteisGeral;

public class CardapioTelaEditarController implements Initializable{
	
	CardapioTelaCadastrarController cardDados = new CardapioTelaCadastrarController();
	private List<ProdutoGeral> listaDeProdutos = Dados.getListaProdutosGeral();
	private GerenciadorDeCardapio gdc = new GerenciadorDeCardapio();
	private UteisGeral uteisGeral = new UteisGeral();
	private AlertasGerais alertasGerais = new AlertasGerais();
	private MudarTelaController mtc = new MudarTelaController();
	
	private List<String> listaCopia;
	private Cardapio cardapio = null;
	private HashMap<String, Double> listaItensCardapio;
	private ObservableList<String> obsDadosCardapio;

    @FXML
    private TextField campoCategoria;

    @FXML
    private TextField campoDescricao;

    @FXML
    private TextField campoItens;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoQuantidade;

    @FXML
    private TextField campoTodosOsItens;

    @FXML
    private TextField campoPreco;

    @FXML
    private ChoiceBox<String> choiceBoxRemoverCard;

    @FXML
    void botaoAddProduto(ActionEvent event) {
    	try {
    		String nomeCardapio = uteisGeral.verificarTextField(campoItens);
    		String todosOsPratos = "";
    		
    		boolean sucesso = gdc.conferirItens(listaDeProdutos, nomeCardapio);
    		if (!sucesso) {
    			throw new InputsIncorretos("Produto não presente do estoque!!!");
    		}
    		
    		String verifQuant = uteisGeral.verificarTextField(campoQuantidade);
    		
    		Double quant =  Double.parseDouble(verifQuant);
    		
    		if (!listaItensCardapio.containsKey(nomeCardapio)) {
    			listaItensCardapio.put(nomeCardapio.toLowerCase(), quant);
    		} else {
    			System.out.println();
    			//alertasFornecedor.alertaNomeJaExistente();
    		}
    		
    		for (String prato : listaItensCardapio.keySet()) {
    			todosOsPratos += prato + ", ";
			}
    		
			int fim = todosOsPratos.length() - 2;
			todosOsPratos = todosOsPratos.substring(0, fim);
    		
			campoItens.clear();
			campoQuantidade.clear();
			campoTodosOsItens.setText(todosOsPratos);
    		
    	} catch(InputsIncorretos ii) {
    		alertasGerais.faltaDadosOuIncorretos();
    	}
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
    		boolean sucesso = gdc.editarCardapio(this.cardapio, dados);
    		
    		if (sucesso) {
    			alertasGerais.informarSucessoOperacao();
    			Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	    	stage.close();
    		}
    		
    	} catch (NullPointerException npe) {
    		alertasGerais.erroNaOperacao();
		} catch(ClassCastException cce) {
			alertasGerais.erroNaOperacao();
		}
    	
    }

    @FXML
    void botaoRemoverProduto(ActionEvent event) {
    	try {
			String produto = uteisGeral.verificarChoiceBoxString(choiceBoxRemoverCard);
			//COPIA
			listaCopia.remove(produto);
			carregarNomeCardapioChoiceBox();
			carregarNomeCardapioListagem();
		} catch(InputsIncorretos ii) {
			alertasGerais.faltaDadosOuIncorretos();
		} catch(NullPointerException npe) {
			alertasGerais.faltaDadosOuIncorretos();
		}
    }
    
    public void adicionarInformacoes(String nome, String descricao, String preco,String categoria, HashMap<String, Double> itens, Cardapio cardapio) {
		campoNome.setText(nome);
		campoDescricao.setText(descricao);
		campoPreco.setText(preco.replaceAll(",", "."));
		campoCategoria.setText(categoria);
		this.listaItensCardapio = itens;
		this.cardapio = cardapio;
		listaCopia = new ArrayList<>(this.listaItensCardapio.keySet());
		
		carregarNomeCardapioChoiceBox();
		carregarNomeCardapioListagem();
		
	}
    
    public void carregarNomeCardapioChoiceBox() {
		if (listaCopia.size() > 0) {
			obsDadosCardapio = FXCollections.observableArrayList(listaCopia);
			choiceBoxRemoverCard.setItems(obsDadosCardapio);
		}	
	}
    
    public void carregarNomeCardapioListagem() {
		if (listaCopia.size() > 0) {
			String todosOsPratos = "";
			for (String produto : listaCopia) {
				todosOsPratos += produto + ", ";
			}
			int fim = todosOsPratos.length() - 2;
			todosOsPratos = todosOsPratos.substring(0, fim);
			campoTodosOsItens.setText(todosOsPratos);
		} else {
			campoTodosOsItens.setText("Vazio");
		}

    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		uteisGeral.validarCampoDecimal(campoPreco);
		uteisGeral.validarCampoDecimal(campoQuantidade);
		
	}
    
	public HashMap<String, Object> juntarInformacoes() {
		HashMap<String, Object> dados = null;
    	try {
			String nome = uteisGeral.verificarTextField(campoNome);
			String preco = uteisGeral.verificarTextField(campoPreco);
			String categoria = uteisGeral.verificarTextField(campoCategoria);
			String descricao = uteisGeral.verificarTextField(campoDescricao);
			
			dados = new HashMap<>();
			
			dados.put("nome", nome);
			dados.put("preco", preco);
			dados.put("categoria", categoria);
			dados.put("descricao", descricao);
			dados.put("itens", listaItensCardapio);
		} catch (InputsIncorretos e) {
			alertasGerais.faltaDadosOuIncorretos();
		} catch(NullPointerException npe) {
			System.out.println("Entrou aqui");
		}
    	return dados;
	}

	@FXML
    void botaoVisualizarProdutos(ActionEvent event) {
    	mtc.abrirNovaJanela("/view/listagem/ProdutoListagem.fxml", (Stage) ((Node) event.getTarget()).getScene().getWindow(), true);
    }
	
}
