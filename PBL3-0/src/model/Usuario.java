package model;

/**
 * Classe abstrata que servirá como base para todas as demais classes específicas de um usuário. Ex: Gerente,
 * funcionário, etc.
 * 
 * @author Caleo Silva e João Pedro.
 *
 */
public abstract class Usuario {
	
	// Atributos:
	private String id;
	private String senha;
	private String login;
	
	// Construtor utilizado para inicializar o objeto já com as informações adicionadas.
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
