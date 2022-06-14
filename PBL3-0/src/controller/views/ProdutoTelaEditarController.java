package controller.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Fornecedor;

public class ProdutoTelaEditarController {

    @FXML
    private ChoiceBox<Fornecedor> campoFornecedor;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoPreco;

    @FXML
    private TextField campoQuantidade;

    @FXML
    private ChoiceBox<String> campoUnidadeDeMedida;

    @FXML
    private DatePicker campoValidade;

    @FXML
    void botaoCancelar(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoConfirmarCadastro(ActionEvent event) {

    }
    
//    public void adicionarInformacoes(String nome) {
//    	this.campoPreco.setText(nome);
//    	System.out.println("Antes: " + campoPreco.getPromptText());
//    	this.campoPreco.setPromptText(nome);
//    	System.out.println("Depois: " + campoPreco.getPromptText());
//    }

}
