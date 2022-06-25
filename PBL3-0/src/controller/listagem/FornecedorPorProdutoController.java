package controller.listagem;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.spi.InitialContextFactory;

import bancoDeDados.Dados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.ProdutoGeral;

public class FornecedorPorProdutoController implements Initializable{
	
	private ObservableList<ProdutoGeral> dadosProdutosGeral;


    @FXML
    private ChoiceBox<ProdutoGeral> choiceBoxProduto;

    @FXML
    void botaoCancelar(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoGerarRelatorio(ActionEvent event) {
    	// Antes eu passava um id, porém agr é melhor passar a lista e a partir dela gerar
    	// o relatório
    }
    
    public void carregarNomeProdutos() {
    	dadosProdutosGeral = FXCollections.observableArrayList(Dados.getListaProdutosGeral());
    	choiceBoxProduto.setItems(dadosProdutosGeral);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarNomeProdutos();
		
	}

}
