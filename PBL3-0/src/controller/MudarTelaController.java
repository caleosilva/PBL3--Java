package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MudarTelaController {
	
	public void abrirTelaFornecedor(AnchorPane centroTela) {
    	abrir("/view/Fornecedor.fxml", centroTela);
    }
    
	public void abrirTelaUsuarios(AnchorPane centroTela) {
    	abrir("/view/Usuario.fxml", centroTela);
    }
    
	public void deslogarSistema(Scene scene, BorderPane painelPrincipal) {
    	abrirTelaLogin("/view/TelaLogin.fxml", scene, painelPrincipal);
    }
	
	public void abrirTelaProdutos(Scene scene, BorderPane painelPrincipal) {
    	abrirTelaLogin("/view/TelaLogin.fxml", scene, painelPrincipal);
    }
	
	
	
	
	public void abrir(String url, AnchorPane centroTela){
    	
		try {
			AnchorPane novaTela = (AnchorPane) FXMLLoader.load(getClass().getResource(url));
			AnchorPane.setTopAnchor(novaTela, 0.0);
	    	AnchorPane.setBottomAnchor(novaTela, 0.0);
	    	AnchorPane.setLeftAnchor(novaTela, 0.0);
	    	AnchorPane.setRightAnchor(novaTela, 0.0);
	    	centroTela.getChildren().add(novaTela);
		} catch (IOException e) {
			System.out.println("Erro em abrir - MudarTelaController\n");
			e.printStackTrace();
		}
    }
	
	public void abrirTelaLogin(String url, Scene scene, BorderPane painelPrincipal) {
    	
    	Parent root = null;
    	
        try {
            root = FXMLLoader.load(getClass().getResource(url));
            scene = painelPrincipal.getScene();
            
            if (scene!= null) {
            	System.out.println("Não é null 1");
            	scene.setRoot(root);
            } else {
            	System.out.println("É null isso aq 1");
            }
            
        } catch (IOException ex) {
        	System.out.println("Erro em abrirTelaLogin - MudarTelaControler\n");
        }
    }

}
