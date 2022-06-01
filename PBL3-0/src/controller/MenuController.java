package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

public class MenuController {
	
    @FXML
    private BorderPane painelPrincipal;
    
    @FXML
    private AnchorPane centroTela;

    @FXML
    void abrirTelaFornecedor(MouseEvent event) {
    	abrir("/view/Fornecedor.fxml");
    }

    @FXML
    void abrirTelaUsuarios(MouseEvent event) {
    	abrir("/view/Usuario.fxml");
    }

    @FXML
    void deslogarSistema(MouseEvent event) {
    	abrirTelaLogin("/view/TelaLogin.fxml");
    }
    
    public void abrir(String url){
    	
		try {
			AnchorPane novaTela = (AnchorPane) FXMLLoader.load(getClass().getResource(url));
			
			AnchorPane.setTopAnchor(novaTela, 0.0);
	    	AnchorPane.setBottomAnchor(novaTela, 0.0);
	    	AnchorPane.setLeftAnchor(novaTela, 0.0);
	    	AnchorPane.setRightAnchor(novaTela, 0.0);

	    	centroTela.getChildren().add(novaTela);
	    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void abrirTelaLogin(String url) {
    	
    	Parent root = null;
    	
        try {
            root = FXMLLoader.load(getClass().getResource(url));
        } catch (IOException ex) {

        }
        
        this.painelPrincipal.getScene().setRoot(root);
        
    }
}
