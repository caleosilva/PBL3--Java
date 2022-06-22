package controller.views;

import java.util.HashMap;

import alertas.AlertasGerais;
import alertas.AlertasUsuario;
import excecoes.InputsIncorretos;
import excecoes.LoginExistente;
import excecoes.SenhaAnteriorIncorreta;
import excecoes.SenhasNovasNaoIguais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
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
    private PasswordField campoNovaSenha1;

    @FXML
    private PasswordField campoNovaSenha2;

    @FXML
    private PasswordField campoSenhaAnterior;

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
    			
    			if (novasInformacoes != null) {
    				boolean sucesso = gdu.editarUsuario(usuario, novasInformacoes);
            		
            		if(sucesso) {
            			alertas.informarSucessoOperacao();
            			Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            	    	stage.close();
            		}
    			}
    		} catch(LoginExistente le){
    			alertasUsuario.alertaLoginExistente();
    		} catch(SenhaAnteriorIncorreta sai) {
    			alertasUsuario.alertaSenhaAnteriorIncorreta();
    		} catch (SenhasNovasNaoIguais snni) {
    			alertasUsuario.alertaSenhasNovasNaoIguais();
    		} catch(NullPointerException npe) {
    			System.out.println("null point exception");
    			//TODO: Pq isso aq é possível?
    		}
    	} else {
    		alertas.erroNaOperacao();
    	}
    	
    }
    
    public void adicionarInformacoes(Usuario user) {
    	this.usuario = user;
    	campoLogin.setText(user.getLogin());
    }
    
    public HashMap<String, String> juntarInformacoes() {
		HashMap<String, String> informacoes = null;
		try {
			String login = uteisGeral.verificarTextField(campoLogin);
			String senhaAnterior = uteisGeral.verificarPassWordField(campoSenhaAnterior);
			String senhaNova1 = uteisGeral.verificarPassWordField(campoNovaSenha1);
			String senhaNova2 = uteisGeral.verificarPassWordField(campoNovaSenha2);
			
			informacoes = new HashMap<>();
			
			informacoes.put("login", login);
			informacoes.put("senhaAnterior", senhaAnterior);
			informacoes.put("senhaNova1", senhaNova1);
			informacoes.put("senhaNova2", senhaNova2);
		} catch(InputsIncorretos e) {
			alertas.faltaDadosOuIncorretos();
		} 
		return informacoes;
	}

}
