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
    	mtc.mudarSubTela("/view/FornecedorTelaGeral.fxml", centroTela);
    }

    @FXML
    void abrirTelaUsuarios(MouseEvent event) {
    	mtc.mudarSubTela("/view/UsuariosTelaGeral.fxml", centroTela);
    }
    
    @FXML
    void abrirTelaProdutos(MouseEvent event) {
    	mtc.mudarSubTela("/view/ProdutosTelaGeral.fxml", centroTela);
    }
    
    @FXML
    void abrirTelaCardapio(MouseEvent event) {
    	mtc.mudarSubTela("/view/CardapioTelaGeral.fxml", centroTela);
    }
    
    @FXML
    void abrirTelaVendas(MouseEvent event) {
    	mtc.mudarSubTela("/view/VendasTelaGeral.fxml", centroTela);
    }
    
    @FXML
    void abrirTelaPdf(MouseEvent event) {
    	mtc.mudarSubTela("/view/listagem/TelaPdfGeral.fxml", centroTela);
    }

    @FXML
    void deslogarSistema(MouseEvent event) {
    	mtc.mudarTelaCompleta("/view/TelaLogin.fxml", scene, painelPrincipal);
    }
}
