package controller.views;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.stream.events.EndElement;

import alertas.AlertasFornecedor;
import alertas.AlertasGerais;
import excecoes.InputsIncorretos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.facade.GerenciadorDeFornecedor;
import uteis.UteisGeral;

public class FornecedorTelaCadastrarController implements Initializable{
	
	private UteisGeral uteisGeral = new UteisGeral();
	private AlertasGerais alertasGerais = new AlertasGerais();
	private AlertasFornecedor alertasFornecedor = new AlertasFornecedor();
	private List<String> listaNomeProdutos = new ArrayList<>();
	private GerenciadorDeFornecedor gdf = new GerenciadorDeFornecedor();
	
    @FXML
    private TextField campoCnpj;

    @FXML
    private TextField campoEndereco;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoProduto;

    @FXML
    private Button idBotaoFechar;

    @FXML
    private TextField campoTodosProdutos;
    
    @FXML
    void botaoAdicionarProduto(ActionEvent event) {
    	try {
    		String nomeProduto = uteisGeral.verificarTextField(campoProduto);
    		String todosOsNomes = "";
    		
    		if (!listaNomeProdutos.contains(nomeProduto)) {
    			listaNomeProdutos.add(nomeProduto);
    		} else {
    			alertasFornecedor.alertaNomeJaExistente();
    		}
    		
    		for (String produto : listaNomeProdutos) {
    			todosOsNomes += produto + ", ";
			}
    		
			int fim = todosOsNomes.length() - 2;
			todosOsNomes = todosOsNomes.substring(0, fim);
    		
			campoProduto.clear();
    		campoTodosProdutos.setText(todosOsNomes);
    		
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
    			boolean sucesso = gdf.cadastrarFornecedor(dados);
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
		uteisGeral.validarCampoNumerico(campoCnpj);
		
	}
	
	public HashMap<String, Object> juntarInformacoes() {
		HashMap<String, Object> dados = null;
    	try {
			String nome = uteisGeral.verificarTextField(campoNome);
			String cnpj = uteisGeral.verificarTextField(campoCnpj);
			String endereco = uteisGeral.verificarTextField(campoEndereco);
			String todosProdutos = uteisGeral.verificarTextField(campoTodosProdutos);
			
			dados = new HashMap<>();
			
			dados.put("nome", nome);
			dados.put("cnpj", cnpj);
			dados.put("endereco", endereco);
			dados.put("produtos", listaNomeProdutos);
		} catch (InputsIncorretos e) {
			alertasGerais.faltaDadosOuIncorretos();
		}
    	return dados;
	}

}
