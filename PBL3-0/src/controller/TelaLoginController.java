package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.Funcionario;
import model.Gerente;
import model.Usuario;
import bancoDeDados.Dados;

public class TelaLoginController implements Initializable{
	
	private Scene scene = null;
	 
	@FXML
    private BorderPane painelLogin;

    @FXML
    private TextField campoLogin;

    @FXML
    private PasswordField campoSenha;

    @FXML
    void fazerLogin(ActionEvent event) {
    	
    	try {
			
			for (Usuario user: Dados.getListaUsuario()) { 
				
				// Retorna o usuário caso o encontre.
				if (user.getLogin().equals(campoLogin.getText()) && user.getSenha().equals(campoSenha.getText())) {
					
					if (user instanceof Gerente) {
						abrirMenu("/view/MenuGerente.fxml");
					} else if(user instanceof Funcionario) {
						abrirMenu("/view/MenuFuncionario.fxml");
					}
					
					
				}
			}
			
			
		} catch (ArrayIndexOutOfBoundsException e) {
			
		}

//    	abrirMenu("/view/MenuFuncionario.fxml");
    	
    }
    
    public void abrirMenu(String url) {

    	Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(url));
            
            scene = this.painelLogin.getScene();
            
            if (scene!= null) {
            	System.out.println("Não é null 0");
            	scene.setRoot(root);
            } else {
            	System.out.println("É null isso aq 0");
            }
            
        } catch (IOException ex) {
        	System.out.println("DEU ERRO AQ 3\n\n\n\n");
        }
        System.out.println("----0\n");
    }
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		application.PreCadastro.main(null);
		
	}

}