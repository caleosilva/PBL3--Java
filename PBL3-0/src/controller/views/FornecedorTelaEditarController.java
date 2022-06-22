package controller.views;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import alertas.AlertasFornecedor;
import alertas.AlertasGerais;
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
import model.Fornecedor;
import model.facade.GerenciadorDeFornecedor;
import uteis.UteisGeral;

public class FornecedorTelaEditarController implements Initializable{
	
	private GerenciadorDeFornecedor gdf = new GerenciadorDeFornecedor();
	private UteisGeral uteisGeral = new UteisGeral();
	private AlertasGerais alertasGerais = new AlertasGerais();
	private AlertasFornecedor alertasFornecedor = new AlertasFornecedor();
	
	private List<String> listaCopia;
	private Fornecedor fornecedor = null;
	private List<String> listaComTodosProdutos;
	private ObservableList<String> obsDadosProdutos;

    @FXML
    private TextField campoAddProduto;

    @FXML
    private TextField campoCnpj;

    @FXML
    private TextField campoEndereco;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoTodosOsProdutos;

    @FXML
    private ChoiceBox<String> choiceBoxRemoverProd;

    @FXML
    void botaoCancelar(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoConfirmarEdicao(ActionEvent event) {
    	HashMap<String, Object> dados = juntarInformacoes();
    	
    	try {
    		boolean sucesso = gdf.editarFornecedor(this.fornecedor, dados);
    		
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//TODO
	}
	
	@FXML
    void botaoRemoverProduto(ActionEvent event) {
		try {
			String produto = uteisGeral.verificarChoiceBoxString(choiceBoxRemoverProd);
			//COPIA
			listaCopia.remove(produto);
			carregarNomeProdutosChoiceBox();
			carregarNomeProdutosListagem();
		} catch(InputsIncorretos ii) {
			alertasGerais.faltaDadosOuIncorretos();
		} catch(NullPointerException npe) {
			alertasGerais.faltaDadosOuIncorretos();
		}
		
    }
	
	@FXML
    void botaoAddProduto(ActionEvent event) {
		try {
    		String nomeProduto = uteisGeral.verificarTextField(campoAddProduto);
    		String todosOsNomes = "";
    		
    		if (!listaCopia.contains(nomeProduto)) {
    			listaCopia.add(nomeProduto);
    		} else {
    			alertasFornecedor.alertaNomeJaExistente();
    		}
    		
    		for (String produto : listaCopia) {
    			todosOsNomes += produto + ", ";
			}
    		
			int fim = todosOsNomes.length() - 2;
			todosOsNomes = todosOsNomes.substring(0, fim);
    		
			campoAddProduto.clear();
			campoTodosOsProdutos.setText(todosOsNomes);
			
			carregarNomeProdutosChoiceBox();
    		
    	} catch(InputsIncorretos ii) {
    		alertasGerais.faltaDadosOuIncorretos();
    	}
		
    }
	
	public void adicionarInformacoes(String nome, String cnpj, String endereco, List<String> produtos, Fornecedor fornecedor) {
		campoNome.setText(nome);
		campoCnpj.setText(cnpj);
		campoEndereco.setText(endereco);
		this.listaComTodosProdutos = produtos;
		this.fornecedor = fornecedor;
		listaCopia = new ArrayList<>(this.listaComTodosProdutos);
		
		carregarNomeProdutosChoiceBox();
		carregarNomeProdutosListagem();
		
	}
	
	public void carregarNomeProdutosChoiceBox() {
		if (listaCopia.size() > 0) {
			obsDadosProdutos = FXCollections.observableArrayList(listaCopia);
			choiceBoxRemoverProd.setItems(obsDadosProdutos);
		}
		
	}
	
	public void carregarNomeProdutosListagem() {
		if (listaCopia.size() > 0) {
			String todosOsNomes = "";
			for (String produto : listaCopia) {
				todosOsNomes += produto + ", ";
			}
			int fim = todosOsNomes.length() - 2;
			todosOsNomes = todosOsNomes.substring(0, fim);
			campoTodosOsProdutos.setText(todosOsNomes);
		} else {
			campoTodosOsProdutos.setText("Vazio");
		}
		
		
	}
	
	public HashMap<String, Object> juntarInformacoes() {
		HashMap<String, Object> dados = null;
    	try {
			String nome = uteisGeral.verificarTextField(campoNome);
			String cnpj = uteisGeral.verificarTextField(campoCnpj);
			String endereco = uteisGeral.verificarTextField(campoEndereco);
			String todosProdutos = uteisGeral.verificarTextField(campoTodosOsProdutos);
			
			dados = new HashMap<>();
			
			dados.put("nome", nome);
			dados.put("cnpj", cnpj);
			dados.put("endereco", endereco);
			dados.put("produtos", listaCopia);
		} catch (InputsIncorretos e) {
			alertasGerais.faltaDadosOuIncorretos();
		}
    	return dados;
	}
}
