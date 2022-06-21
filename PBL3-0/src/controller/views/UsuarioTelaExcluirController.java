package controller.views;

import alertas.AlertasGerais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.Usuario;
import model.facade.GerenciadorDeUsuario;

public class UsuarioTelaExcluirController {
	
	private Usuario usuario = null;
	
	private AlertasGerais alertas = new AlertasGerais();
	private GerenciadorDeUsuario gdu = new GerenciadorDeUsuario();

    @FXML
    void BotaoCancelarOperacao(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoExcluirProduto(ActionEvent event) {
    	boolean sucesso = gdu.excluirUsuario(usuario);
    	
    	if (sucesso) {
			alertas.informarSucessoOperacao();
    		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        	stage.close();
		} else {
			alertas.erroNaOperacao();
		}
    }
    
    public void receberInformacao(Usuario user) {
    	this.usuario = user;
    }

}
