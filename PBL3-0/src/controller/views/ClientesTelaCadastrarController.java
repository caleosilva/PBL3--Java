package controller.views;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import alertas.AlertasGerais;
import excecoes.InputsIncorretos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.facade.GerenciadorDeCardapio;
import model.facade.GerenciadorDeCliente;
import uteis.UteisGeral;

public class ClientesTelaCadastrarController implements Initializable {

	private UteisGeral uteisGeral = new UteisGeral();
	private AlertasGerais alertasGerais = new AlertasGerais();
	private GerenciadorDeCliente gdc = new GerenciadorDeCliente();
	
    @FXML
    private TextField campoCPF;

    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoTelefone;

    @FXML
    private Button idBotaoFechar;

    @FXML
    void botaoCancelar(ActionEvent event) {

    }

    @FXML
    void botaoConfirmarCadastro(ActionEvent event) {
    	
    	HashMap<String, String> dados = juntarInformacoes();
    	try {
    		if (dados != null) {
    			boolean sucesso = gdc.cadastrarCliente(dados);
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

 public HashMap<String, String> juntarInformacoes() {
    	
    	HashMap<String, String> dados = null;
    	
    	try {
			String nome = uteisGeral.verificarTextField(campoNome);
			String email = uteisGeral.verificarTextField(campoEmail);
			String cpf = uteisGeral.verificarTextField(campoCPF);
			String telefone = uteisGeral.verificarTextField(campoTelefone);
			
			
			dados = new HashMap<>();
			
			dados.put("nome", nome);
			dados.put("email", email);
			dados.put("cpf", cpf);
			dados.put("telefone", telefone);
		} catch (InputsIncorretos e) {
			alertasGerais.faltaDadosOuIncorretos();
		}
    	return dados;
    }
 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		uteisGeral.validarCampoDecimal(campoCPF);
		uteisGeral.validarCampoDecimal(campoTelefone);
		
	}
}
