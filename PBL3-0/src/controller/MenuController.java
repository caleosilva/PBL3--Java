package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

public class MenuController {

    @FXML
    private BorderPane painelPrincipal;
    
    @FXML
    void abrirClientes(MouseEvent event) {
    	System.out.println("cliente 1");
    	abrir("/view/Cliente.fxml");
    	System.out.println("cliente 2");
    }

    @FXML
    void abrirFornecedores(MouseEvent event) {
    	System.out.println("fornecedor 1");
    	abrir("/view/Fornecedor.fxml");
    	System.out.println("fornecedor 2");
    }

    @FXML
    void abrirUsuarios(MouseEvent event) {
    	System.out.println("Usuario 1");
    	abrir("/view/Usuario.fxml");
    	System.out.println("Usuario 2");
    }

    
    @FXML
    void abrirClienteTeste(MouseEvent event) {
    	
    }
    
    public void abrir(String url) {
    	
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(url));
        } catch (IOException ex) {

        }
        this.painelPrincipal.setCenter(root);

    }

}
