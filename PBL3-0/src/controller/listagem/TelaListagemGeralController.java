package controller.listagem;

import controller.views.MudarTelaController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TelaListagemGeralController {
	
	private MudarTelaController mtc = new MudarTelaController();

    @FXML
    void abrirListagemCardapio(MouseEvent event) {
    	
    }

    @FXML
    void abrirListagemFornecedores(MouseEvent event) {
    	mtc.abrirNovaJanela("/view/listagem/FornecedorListagem.fxml", (Stage) ((Node) event.getTarget()).getScene().getWindow(), true);

    }

    @FXML
    void abrirListagemProdutos(MouseEvent event) {
    	mtc.abrirNovaJanela("/view/listagem/ProdutoListagem.fxml", (Stage) ((Node) event.getTarget()).getScene().getWindow(), true);
    }

    @FXML
    void abrirListagemUsuarios(MouseEvent event) {
    	mtc.abrirNovaJanela("/view/listagem/UsuarioListagem.fxml", (Stage) ((Node) event.getTarget()).getScene().getWindow(), true);

    }

}
