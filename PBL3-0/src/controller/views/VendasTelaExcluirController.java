package controller.views;

import alertas.AlertasGerais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.Vendas;
import model.facade.GerenciadorDeVendas;

public class VendasTelaExcluirController {
	
	private GerenciadorDeVendas gdv = new GerenciadorDeVendas();
	private Vendas venda = null;
	private AlertasGerais alertas = new AlertasGerais();
	
    @FXML
    void BotaoCancelarOperacao(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoExcluirVenda(ActionEvent event) {
    	
    	boolean sucesso = gdv.excluirVendas(venda);
    	if (sucesso) {
			alertas.informarSucessoOperacao();
    		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        	stage.close();
		} else {
			alertas.erroNaOperacao();
		}
	}
	public void receberInformacao(Vendas venda) {
		this.venda = venda;
	}
}

