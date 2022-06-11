package model.concreto;

import bancoDeDados.Dados;
import excecoes.LoginSenhaInvalidos;
import model.Usuario;

public class GerenciadorDeLogin {
	
	
	public Usuario logarNoSistema(String login, String senha) throws LoginSenhaInvalidos {	    	
    	
    	for (Usuario user: Dados.getListaUsuario()) {
			if (user.getLogin().equals(login) && user.getSenha().equals(senha)) return user;
    	}
    	
    	throw new LoginSenhaInvalidos("Login e/ou senha inválidos!");
    }
	
	

}
