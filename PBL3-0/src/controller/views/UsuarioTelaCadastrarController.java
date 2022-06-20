package controller.views;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.security.auth.login.LoginException;

import alertas.AlertasGerais;
import alertas.AlertasUsuario;
import excecoes.ErroNaOperacao;
import excecoes.InputsIncorretos;
import excecoes.LoginExistente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;
import model.facade.GerenciadorDeProduto;
import model.facade.GerenciadorDeUsuario;
import uteis.UteisGeral;
import uteis.UteisProduto;

public class UsuarioTelaCadastrarController implements Initializable{
	
	private GerenciadorDeUsuario gdu = new GerenciadorDeUsuario();
	private AlertasGerais alertasGerais = new AlertasGerais();
	private AlertasUsuario alertasUsuario = new AlertasUsuario();
	private UteisGeral uteisGeral = new UteisGeral();
	
	private String[] cargoUsuarios = {"Gerente", "Funcionario"};
	private ObservableList<String> dadosCargoUsuarios;

    @FXML
    private ChoiceBox<String> campoCargo;

    @FXML
    private TextField campoLogin;

    @FXML
    private TextField campoSenha;

    @FXML
    private Button idBotaoFechar;

    @FXML
    void botaoCancelar(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoConfirmarCadastro(ActionEvent event) {
    	try {
    		HashMap<String, String> dados = juntarInformacoes();
    		if (dados != null) {
    			gdu.cadastrarUsuario(dados);
            	alertasGerais.informarSucessoOperacao();
            	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            	stage.close();
    		}
    	} catch(ErroNaOperacao eno) {
    		alertasGerais.erroNaOperacao();
    	} catch (LoginExistente le) {
    		alertasUsuario.alertaLoginExistente();
		} catch (NullPointerException e) {
			alertasGerais.erroNaOperacao();
		}
    }
    
    public HashMap<String, String> juntarInformacoes() {
    	
    	HashMap<String, String> dados = null;
    	
    	try {
			String login = uteisGeral.verificarTextField(campoLogin);
			String senha = uteisGeral.verificarTextField(campoSenha);
			String cargo = uteisGeral.verificarChoiceBoxString(campoCargo);
			
			dados = new HashMap<>();
			
			dados.put("senha", senha);
			dados.put("login", login);
			dados.put("cargo", cargo);
		} catch (InputsIncorretos e) {
			alertasGerais.faltaDadosOuIncorretos();
		}
    	
    	return dados;
    }
    
    public void carregarUnidadeDeMedidas() {
    	dadosCargoUsuarios = FXCollections.observableArrayList(cargoUsuarios);
		campoCargo.setItems(dadosCargoUsuarios);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarUnidadeDeMedidas();
	}

}
