package controller.views;

import alertas.AlertasGerais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.Cardapio;
import model.Fornecedor;
import model.facade.GerenciadorDeCardapio;

public class CardapioTelaExcluirController {
	
	private GerenciadorDeCardapio gdc = new GerenciadorDeCardapio();
	private Cardapio cardapio = null;
	private AlertasGerais alertas = new AlertasGerais();
	
    @FXML
    void BotaoCancelarOperacao(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoExcluirProduto(ActionEvent event) {
    	
    	boolean sucesso = gdc.excluirCardapio(cardapio);
    	if (sucesso) {
			alertas.informarSucessoOperacao();
    		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        	stage.close();
		} else {
			alertas.erroNaOperacao();
		}
	}
	public void receberInformacao(Cardapio cardapio) {
		this.cardapio = cardapio;
	}
	
    
}

