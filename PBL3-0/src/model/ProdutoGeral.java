package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsavel por ser a "caixa" de um produto. Alem disso ela eh
 * utilizada para instanciar o objeto do tipo "ProdutoEspecifico".
 * 
 * @author Caleo Silva e Joao Pedro
 *
 */
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
	
	@Override
	public String toString() {
		return getNome();
	}

}
