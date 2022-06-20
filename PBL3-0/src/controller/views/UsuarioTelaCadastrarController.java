package controller.views;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import alertas.AlertasGerais;
import excecoes.InputsIncorretos;
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
import uteis.UteisGeral;
import uteis.UteisProduto;

public class UsuarioTelaCadastrarController implements Initializable{
	
	private GerenciadorDeProduto gdp = new GerenciadorDeProduto();
	private AlertasGerais alertas = new AlertasGerais();
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
    	HashMap<String, String> dados = juntarInformacoes();
    }
    
    public HashMap<String, String> juntarInformacoes() {
    	
    	HashMap<String, String> dados = new HashMap<>();
    	
    	try {
			String login = uteisGeral.verificarTextField(campoLogin);
			String senha = uteisGeral.verificarTextField(campoSenha);
			String cargo = uteisGeral.verificarChoiceBoxString(campoCargo);
			
			dados.put("senha", senha);
			dados.put("login", login);
			dados.put("cargo", cargo);
			
			System.out.println("Tudo certo!");
		} catch (InputsIncorretos e) {
			alertas.faltaDadosOuIncorretos();
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
