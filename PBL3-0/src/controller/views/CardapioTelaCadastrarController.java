package controller.views;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import alertas.AlertasGerais;
import bancoDeDados.Dados;
import excecoes.InputsIncorretos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ProdutoGeral;
import model.facade.GerenciadorDeCardapio;
import uteis.UteisGeral;

public class CardapioTelaCadastrarController implements Initializable {
	
	private List<ProdutoGeral> listaDeProdutos = Dados.getListaProdutosGeral();
	private UteisGeral uteisGeral = new UteisGeral();
	private AlertasGerais alertasGerais = new AlertasGerais();
	private HashMap<String, Double> listaItensCardapio = new HashMap<String, Double>();
	//private List<Double> ListaPrecoCardapio = new ArrayList<>();
	private GerenciadorDeCardapio gdc = new GerenciadorDeCardapio();
	private MudarTelaController mtc = new MudarTelaController();

    @FXML
    private TextField campoCategoria;

    @FXML
    private TextField campoDescricao;

    @FXML
    private TextField campoItens;
    
    @FXML
    private TextField campoQuantidade;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoPreco;

    @FXML
    private TextField campoTodosItens;

    @FXML
    private Button idBotaoFechar;

    @FXML
    void botaoAdicionarCardapio(ActionEvent event) {
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
    			listaItensCardapio.put(nomeCardapio, quant);
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
			campoTodosItens.setText(todosOsPratos);
    		
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
    void botaoConfirmarCadastro(ActionEvent event) {
    	HashMap<String, Object> dados = juntarInformacoes();
    	try {
    		if (dados != null) {
    			boolean sucesso = gdc.cadastrarCardapio(dados);
    			if (sucesso) {
    				alertasGerais.informarSucessoOperacao();
                	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
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
