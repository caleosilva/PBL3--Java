package controller.views;

import alertas.AlertasGerais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.ProdutoEspecifico;
import model.facade.GerenciadorDeProduto;

public class ProdutoTelaExcluirController {
	
	private ProdutoEspecifico produtoEspecifico = null;
	private GerenciadorDeProduto gdp = new GerenciadorDeProduto();
	private AlertasGerais alertas = new AlertasGerais();

    @FXML
    void BotaoCancelarOperacao(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoExcluirProduto(ActionEvent event) {
    	try {
    		boolean sucesso = gdp.excluirProdutos(produtoEspecifico);
    		if (sucesso) {
    			alertas.informarSucessoOperacao();
        		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            	stage.close();
    		} else {
    			alertas.erroNaOperacao();
    		}
    	} catch(NullPointerException npe) {
    		alertas.erroNaOperacao();
    	}
    }
    
    public void receberInformacao(ProdutoEspecifico pe) {
    	this.produtoEspecifico = pe;
    }

}
