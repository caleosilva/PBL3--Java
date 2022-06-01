package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TelaLoginController {
	 
	@FXML
    private BorderPane painelLogin;

    @FXML
    private TextField campoLogin;

    @FXML
    private PasswordField campoSenha;

    @FXML
    void fazerLogin(ActionEvent event) {
    	
    	String login = campoLogin.getText();
    	String senha = campoSenha.getText();
    	
    	System.out.println("Login: " + login);
    	System.out.println("Senha: " + senha);
    	
    	abrirMenu("/view/MenuGerente.fxml");

//    	abrirMenu("/view/MenuFuncionario.fxml");
    	
    }
    
    public void abrirMenu(String url) {
    	
    	Parent root = null;
    	
        try {
            root = FXMLLoader.load(getClass().getResource(url));
        } catch (IOException ex) {

        }
        
        this.painelLogin.getScene().setRoot(root);
        
    }

}