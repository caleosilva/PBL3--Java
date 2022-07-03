package controller.views;

import alertas.AlertasGerais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.Cardapio;
import model.Cliente;
import model.facade.GerenciadorDeCardapio;
import model.facade.GerenciadorDeCliente;

public class ClienteTelaExcluirController {

	private GerenciadorDeCliente gdc = new GerenciadorDeCliente();
	private Cliente cliente = null;
	private AlertasGerais alertas = new AlertasGerais();
	
    @FXML
    void BotaoCancelarOperacao(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoExcluirCliente(ActionEvent event) {
      	boolean sucesso = gdc.excluirCliente(cliente);
    	if (sucesso) {
			alertas.informarSucessoOperacao();
    		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        	stage.close();
		} else {
			alertas.erroNaOperacao();
		}
	}
	public void receberInformacao(Cliente cliente) {
		this.cliente = cliente;
	}

}
