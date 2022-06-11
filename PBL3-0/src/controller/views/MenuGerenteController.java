package controller.views;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class MenuGerenteController {
	
	private Scene scene = null;
	private MudarTelaController mtc = new MudarTelaController();
	
    @FXML
    private BorderPane painelPrincipal;
    
    @FXML
    private AnchorPane centroTela;

    @FXML
    void abrirTelaFornecedor(MouseEvent event) {
    	mtc.mudarSubTela("/view/Fornecedor.fxml", centroTela);
    }

    @FXML
    void abrirTelaUsuarios(MouseEvent event) {
    	mtc.mudarSubTela("/view/Usuario.fxml", centroTela);
    }
    
    @FXML
    void abrirTelaProdutos(MouseEvent event) {
    	mtc.mudarSubTela("/view/ProdutosTelaGeral.fxml", centroTela);
    }

    @FXML
    void deslogarSistema(MouseEvent event) {
    	mtc.mudarTelaCompleta("/view/TelaLogin.fxml", scene, painelPrincipal);
    }
}