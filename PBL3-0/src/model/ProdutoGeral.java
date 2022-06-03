package model;

import java.util.ArrayList;
import java.util.List;

public class ProdutoGeral {
	
	// Atributos:
	private String nome;
	private List<ProdutoEspecifico> listaDeProdutos = new ArrayList<>();
	
	
	// Construtor:
	public ProdutoGeral(String nome) {
		this.nome = nome;
	}
	
	// Getters e Setters:
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ProdutoEspecifico> getListaDeProdutos() {
		return listaDeProdutos;
	}

}
