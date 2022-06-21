package controller.views;

import alertas.AlertasGerais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.Fornecedor;
import model.facade.GerenciadorDeFornecedor;

public class FornecedorTelaExcluirController {
	
	private GerenciadorDeFornecedor gdf = new GerenciadorDeFornecedor();
	private Fornecedor fornecedor = null;
	private AlertasGerais alertas = new AlertasGerais();

    @FXML
    void BotaoCancelarOperacao(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoExcluirProduto(ActionEvent event) {
    	boolean sucesso = gdf.excluirFornecedor(fornecedor);
    	
    	if (sucesso) {
			alertas.informarSucessoOperacao();
    		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        	stage.close();
		} else {
			alertas.erroNaOperacao();
		}
    }
    
    public void receberInformacao(Fornecedor fornecedor) {
    	this.fornecedor = fornecedor;
    }

}
