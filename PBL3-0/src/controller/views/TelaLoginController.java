package controller.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import alertas.AlertasGerais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.Funcionario;
import model.Gerente;
import model.Usuario;
import model.concreto.GerenciadorDeLogin;
import bancoDeDados.Dados;
import excecoes.LoginSenhaInvalidos;

public class TelaLoginController implements Initializable{
	
	private Scene scene = null;
	
	private AlertasGerais alertas = new AlertasGerais();
	 
	@FXML
    private BorderPane painelLogin;

    @FXML
    private TextField campoLogin;

    @FXML
    private PasswordField campoSenha;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

    @FXML
    void fazerLogin(ActionEvent event) {
    	
    	try {
    		GerenciadorDeLogin login = new GerenciadorDeLogin();
    		Usuario user = login.logarNoSistema(campoLogin.getText(), campoSenha.getText());
    		
    		if (user instanceof Gerente) {
				abrirMenu("/view/MenuGerente.fxml");					
			} else if(user instanceof Funcionario) {
				abrirMenu("/view/MenuFuncionario.fxml");
			}
    		
		} catch (ArrayIndexOutOfBoundsException e) {
			alertas.alertaArrayOutOfBounds();
			
		} catch(LoginSenhaInvalidos e) {
			
			if (campoLogin.getText().isBlank() || campoSenha.getText().isBlank()) {
				alertas.alertaFaltaInformacoesLogin();
			} else {
				alertas.alertaErroLogin();
			}
		}
	}
    
    public void abrirMenu(String url) {

    	Parent root = null;
    	
        try {
        	
            root = FXMLLoader.load(getClass().getResource(url));
            scene = this.painelLogin.getScene();
            
            if (scene!= null) {
            	scene.setRoot(root);
            }
        } catch (IOException ex) {
        	System.out.println("Erro em abrirMenu\n");
        }
    }

	

}