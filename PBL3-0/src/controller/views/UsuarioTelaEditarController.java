package controller.views;

import java.util.HashMap;

import alertas.AlertasGerais;
import alertas.AlertasUsuario;
import excecoes.InputsIncorretos;
import excecoes.LoginExistente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;
import model.facade.GerenciadorDeUsuario;
import uteis.UteisGeral;

public class UsuarioTelaEditarController {
	
	private Usuario usuario = null;
	private UteisGeral uteisGeral = new UteisGeral();
	private AlertasGerais alertas = new AlertasGerais();
	private AlertasUsuario alertasUsuario = new AlertasUsuario();
	private GerenciadorDeUsuario gdu = new GerenciadorDeUsuario();

    @FXML
    private TextField campoLogin;

    @FXML
    private TextField campoSenha;

    @FXML
    void botaoCancelar(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoConfirmarEdicao(ActionEvent event) {
    	if (usuario != null) {
    		
    		try {
    			HashMap<String, String> novasInformacoes = juntarInformacoes();
        		boolean sucesso = gdu.editarUsuario(usuario, novasInformacoes);
        		
        		if(sucesso) {
        			alertas.informarSucessoOperacao();
        			Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        	    	stage.close();
        		}
    			
    		} catch(LoginExistente le){
    			alertasUsuario.alertaLoginExistente();
    		}
    		
    		
    	} else {
    		alertas.erroNaOperacao();
    	}
    	
    }
    
    public void adicionarInformacoes(String login, String senha, Usuario user) {
    	campoLogin.setText(login);
    	campoSenha.setText(senha);
    	this.usuario = user;
    }
    
    public HashMap<String, String> juntarInformacoes() {
		HashMap<String, String> informacoes = null;
		try {
			String login = uteisGeral.verificarTextField(campoLogin);
			String senha = uteisGeral.verificarTextField(campoSenha);
			
			informacoes = new HashMap<>();
			
			informacoes.put("login", login);
			informacoes.put("senha", senha);
		} catch(InputsIncorretos e) {
			alertas.faltaDadosOuIncorretos();
		}
		return informacoes;
	}

}
