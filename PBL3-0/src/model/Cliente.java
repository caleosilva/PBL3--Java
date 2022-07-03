package model;

/**
 * 
 * Classe que possui as caracteristicas referente a um Cliente, além disso, ela que é instaciada para
 * um objeto do tipo Cliente.
 * 
 * @author Caleo Silva e João Pedro.
 *
 */
public class Cliente {
	
	// Atributos:
	String id;
	String nome;
	String cpf;
	String email;
	String telefone;
	
	// Construtor
	public Cliente(String id, String nome, String cpf, String email, String telefone) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
	}
	
	
	// Getters e Setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
