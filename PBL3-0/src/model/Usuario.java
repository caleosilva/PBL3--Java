package model;

/**
 * Classe abstrata que servir� como base para todas as demais classes espec�ficas de um usu�rio. Ex: Gerente,
 * funcion�rio, etc.
 * 
 * @author Caleo Silva e Jo�o Pedro.
 *
 */
public abstract class Usuario {
	
	// Atributos:
	private String id;
	private String senha;
	private String login;
	
	// Construtor utilizado para inicializar o objeto j� com as informa��es adicionadas.
	public Usuario(String newId, String newLogin, String newSenha) {
		this.id = newId;
		this.login = newLogin;
		this.senha = newSenha;
	}
	
	// Getters e Setters
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}
	
}
