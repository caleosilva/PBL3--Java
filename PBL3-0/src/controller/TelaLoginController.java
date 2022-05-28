package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaLoginController {

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
    	
    	MenuController mc = new MenuController();
    	mc.abrir("/view/TelaLogin.fxml");
    }

}