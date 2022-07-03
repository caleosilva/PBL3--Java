package controller.listagem;

import controller.views.MudarTelaController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 * @author Caleo Silva e Joao Pedro
 * 
 * Classe responsavel por ser o controller do fxml "TelaListagemGeral.fxml" e exibir na tela as
 * opcoes possiveis para serem listadas.
 *
 */
public class TelaListagemGeralController {
	
	private MudarTelaController mtc = new MudarTelaController();
	
	/**
	 * Metodo responsavel por abrir a listagem do cardapio.
	 * @param event Evento gerado pelo usuario.
	 */
    @FXML
    void abrirListagemCardapio(MouseEvent event) {
    	mtc.abrirNovaJanela("/view/listagem/CardapioListagem.fxml", (Stage) ((Node) event.getTarget()).getScene().getWindow(), true);
    }

    /**
	 * Metodo responsavel por abrir a listagem do fornecedor.
	 * @param event Evento gerado pelo usuario.
	 */
    @FXML
    void abrirListagemFornecedores(MouseEvent event) {
    	mtc.abrirNovaJanela("/view/listagem/FornecedorListagem.fxml", (Stage) ((Node) event.getTarget()).getScene().getWindow(), true);

    }
    
    /**
	 * Metodo responsavel por abrir a listagem do produto.
	 * @param event Evento gerado pelo usuario.
	 */
    @FXML
    void abrirListagemProdutos(MouseEvent event) {
    	mtc.abrirNovaJanela("/view/listagem/ProdutoListagem.fxml", (Stage) ((Node) event.getTarget()).getScene().getWindow(), true);
    }

    /**
	 * Metodo responsavel por abrir a listagem do usuario.
	 * @param event Evento gerado pelo usuario.
	 */
    @FXML
    void abrirListagemUsuarios(MouseEvent event) {
    	mtc.abrirNovaJanela("/view/listagem/UsuarioListagem.fxml", (Stage) ((Node) event.getTarget()).getScene().getWindow(), true);

    }

}
