package controller.views;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MenuFuncionarioController {
	
	private Scene scene = null;
	
	private MudarTelaController mtc = new MudarTelaController();

    @FXML
    private AnchorPane centroTela;

    @FXML
    private BorderPane painelPrincipal;

    @FXML
    void deslogarSistema(MouseEvent event) {
    	mtc.mudarTelaCompleta("/view/TelaLogin.fxml", scene, painelPrincipal);
    }

}
