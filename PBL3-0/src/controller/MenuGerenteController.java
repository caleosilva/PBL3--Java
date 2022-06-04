package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import controller.MudarTelaController.*;

public class MenuGerenteController {
	
	private Scene scene = null;
	private MudarTelaController mtc = new MudarTelaController();
	
    @FXML
    private BorderPane painelPrincipal;
    
    @FXML
    private AnchorPane centroTela;

    @FXML
    void abrirTelaFornecedor(MouseEvent event) {
    	mtc.abrir("/view/Fornecedor.fxml", centroTela);
    }

    @FXML
    void abrirTelaUsuarios(MouseEvent event) {
    	mtc.abrir("/view/Usuario.fxml", centroTela);
    }
    
    @FXML
    void abrirTelaProdutos(MouseEvent event) {
    	mtc.abrir("/view/Produtos.fxml", centroTela);
    }

    @FXML
    void deslogarSistema(MouseEvent event) {
    	mtc.abrirTelaLogin("/view/TelaLogin.fxml", scene, painelPrincipal);
    }
}
