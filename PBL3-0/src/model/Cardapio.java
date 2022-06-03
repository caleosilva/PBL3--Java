package model;

import java.util.HashMap;

/**
 * 
 * Classe que possui as caracteristicas referente a um item do Cardapio, além disso, ela que é instaciada para
 * um objeto do tipo Cardapio.
 * 
 * @author Caleo Silva e João Pedro.
 *
 */
public class Cardapio {
	private String nome;
	private String id;
	private String descricao;
	private HashMap<String, Double> itensCardapio;
	private String preco;
	private String categoria;
	
	// Construtor para inicializar cada atributo com o valor correto.
	public Cardapio(String nome, String id, String descricao, HashMap<String, Double> itensCardapio, String preco, String categoria) {
		this.nome = nome;
		this.id = id;
		this.descricao = descricao;
		this.itensCardapio = itensCardapio;
		this.preco = preco;
		this.categoria = categoria;
	}
	
	//GETTERS E SETTERS
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public HashMap<String, Double> getItensCardapio() {
		return itensCardapio;
	}

	public void setItensCardapio(HashMap<String, Double> itensCardapio) {
		this.itensCardapio = itensCardapio;
	}
	
	
}
