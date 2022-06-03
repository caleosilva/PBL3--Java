package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Classe que possui as caracteristicas referente a um fornecedor, al�m disso, ela que � instaciada para
 * um objeto do tipo fornecedor.
 * 
 * @author Caleo Silva e Jo�o Pedro.
 *
 */
public class Fornecedor {
	
	// Atributos:
	private String id;
	private String cnpj;
	private String nome;
	private String endereco;
	private List<String> listaNomeProdutos = new ArrayList<>();
	
	//private HashMap<String, list<ProdutosEspecificos> dados> listaDeProdutos;
	
	// Construtor para inicializar cada atributo com o valor correto.
	public Fornecedor(String id, String cnpj, String nome, String endereco) {
		this.id = id;
		this.cnpj = cnpj;
		this.nome = nome;
		this.endereco = endereco;
	}
	
	// Getters e Setters:
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public List<String> getListaNomeProdutos() {
		return listaNomeProdutos;
	}
}