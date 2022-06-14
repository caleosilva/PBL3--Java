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
	
	private TableView<ProdutoEspecifico> tabelaInformacoes;
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
		boolean sucesso = gdp.excluirProdutos(produtoEspecifico, tabelaInformacoes);
		
		if (sucesso) {
			alertas.informarSucessoOperacao();
    		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        	stage.close();
		} else {
			alertas.erroNaOperacao();
		}

    }
    
    public void atualizarProduto(ProdutoEspecifico pe, TableView<ProdutoEspecifico> tabela) {
    	this.produtoEspecifico = pe;
    	this.tabelaInformacoes = tabela;
    }

}
