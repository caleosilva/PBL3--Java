package controller.views;

import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import uteis.UteisGeral;

public class FornecedorTelaEditarController implements Initializable{
	
	private UteisGeral uteisGeral = new UteisGeral();
	private AlertasGerais alertasGerais = new AlertasGerais();
	private AlertasFornecedor alertasFornecedor = new AlertasFornecedor();
	
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

    }

    @FXML
    void botaoConfirmarEdicao(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		carregarNomeProdutosChoiceBox();
	}
	
	@FXML
    void botaoRemoverProduto(ActionEvent event) {
		try {
			String produto = uteisGeral.verificarChoiceBoxString(choiceBoxRemoverProd);
			listaComTodosProdutos.remove(produto);
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
    		
    		if (!listaComTodosProdutos.contains(nomeProduto)) {
    			listaComTodosProdutos.add(nomeProduto);
    		} else {
    			alertasFornecedor.alertaNomeJaExistente();
    		}
    		
    		for (String produto : listaComTodosProdutos) {
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
	
	public void adicionarInformacoes(String nome, String cnpj, String endereco, List<String> produtos) {
		campoNome.setText(nome);
		campoCnpj.setText(cnpj);
		campoEndereco.setText(endereco);
		this.listaComTodosProdutos = produtos;
		
		carregarNomeProdutosChoiceBox();
		carregarNomeProdutosListagem();
		
	}
	
	public void carregarNomeProdutosChoiceBox() {
		obsDadosProdutos = FXCollections.observableArrayList(listaComTodosProdutos);
		choiceBoxRemoverProd.setItems(obsDadosProdutos);
	}
	
	public void carregarNomeProdutosListagem() {
		String todosOsNomes = "";
		
		for (String produto : listaComTodosProdutos) {
			todosOsNomes += produto + ", ";
		}
		int fim = todosOsNomes.length() - 2;
		todosOsNomes = todosOsNomes.substring(0, fim);
		campoTodosOsProdutos.setText(todosOsNomes);
	}

}
