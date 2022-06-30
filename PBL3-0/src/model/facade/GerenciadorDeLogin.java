package model.facade;

import bancoDeDados.Dados;
import excecoes.LoginSenhaInvalidos;
import model.Gerente;
import model.Usuario;

/**
 * Classe responsavel por conter os metodos que realizam o login no sistema.
 * @author Caleo Silva e Joao Pedro.
 *
 */
public class GerenciadorDeLogin {
	
	/**
	 * Metodo responsavel por verificar as informacoes e realizar o login no sistema.
	 * @param login Login digitado pelo usuario
	 * @param senha Senha digitada pelo usuario
	 * @return Objeto do tipo usuario caso o login seja realizado.
	 * @throws LoginSenhaInvalidos Excecao caso o login falhe.
	 */
	public Usuario logarNoSistema(String login, String senha) throws LoginSenhaInvalidos {	    	
    	
		if (Dados.getListaUsuario().size() == 0) {
			Gerente admin = new Gerente("000", "admin", "admin", "Gerente");
			if (admin.getLogin().equals(login) && admin.getSenha().equals(senha)) return admin;
		}
		
    	for (Usuario user: Dados.getListaUsuario()) {
			if (user.getLogin().equals(login) && user.getSenha().equals(senha)) return user;
    	}
    	
    	throw new LoginSenhaInvalidos("Login e/ou senha inv�lidos!");
    }
	
	

}
