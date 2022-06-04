package model;

import bancoDeDados.Dados;
import excecoes.LoginSenhaInvalidos;

public class Login {
	
	
	public Usuario logarNoSistema(String login, String senha) throws LoginSenhaInvalidos {
	    	
	    	boolean fezLogin = false;
	    	
	    	for (Usuario user: Dados.getListaUsuario()) {
	    		
				if (user.getLogin().equals(login) && user.getSenha().equals(senha)) {
					
					fezLogin = true;
					return user;
				}
	    	}
	    	if (!fezLogin) {
	    		throw new LoginSenhaInvalidos("Login e/ou senha inválidos!");
	    	}
	    	
	    	return null;
    }
	
	

}
