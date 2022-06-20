package controller.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MudarTelaController {
	
	public void mudarSubTela(String url, AnchorPane centroTela){
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
	
	public void mudarTelaCompleta(String url, Scene scene, BorderPane painelPrincipal) {
    	Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(url));
            scene = painelPrincipal.getScene();
            
            if (scene!= null) {
            	scene.setRoot(root);
            }
        } catch (IOException ex) {
        	System.out.println("Erro em abrirTelaLogin - MudarTelaControler\n");
        }
    }
	
	public void abrirNovaJanela(String url, Stage parentStage) {
		Parent root = null;
		
		try {
            root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initOwner(parentStage);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
            
        } catch (IOException ex) {
        	System.out.println("Erro em abrirNovajanela - MudarTelaControler\n");
        }
		
	}
	

}
