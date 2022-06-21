package model.facade;

import bancoDeDados.Dados;
import excecoes.LoginSenhaInvalidos;
import model.Gerente;
import model.Usuario;

public class GerenciadorDeLogin {
	
	
	public Usuario logarNoSistema(String login, String senha) throws LoginSenhaInvalidos {	    	
    	
		if (Dados.getListaUsuario().size() == 0) {
			Gerente admin = new Gerente("000", "admin", "admin", "Gerente");
			if (admin.getLogin().equals(login) && admin.getSenha().equals(senha)) return admin;
		}
		
    	for (Usuario user: Dados.getListaUsuario()) {
			if (user.getLogin().equals(login) && user.getSenha().equals(senha)) return user;
    	}
    	
    	throw new LoginSenhaInvalidos("Login e/ou senha inválidos!");
    }
	
	

}
