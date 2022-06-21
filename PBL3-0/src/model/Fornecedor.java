package model;

import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import bancoDeDados.Dados;

/**
 * 
 * Classe que possui as caracteristicas referente a um fornecedor, além disso, ela que é instaciada para
 * um objeto do tipo fornecedor.
 * 
 * @author Caleo Silva e João Pedro.
 *
 */
public class Fornecedor {
	
	// Atributos:
	private String id;
	private String cnpj;
	private String nome;
	private String endereco;
	private List<String> listaNomeProdutos = new ArrayList<>();
	
	private String produtoPorString;
	
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
	
	
	@Override
	public String toString() {
		return getNome();
	}
	
	public String getProdutoPorString() {
		
		if (getListaNomeProdutos().size() == 0) {
			produtoPorString = "Vazio";
			return produtoPorString;
		} else {
			String nomes = "";
			for (String produto : getListaNomeProdutos()) {
				nomes += produto + ", ";
			}
			int fim = nomes.length() - 2;
			produtoPorString = nomes.substring(0, fim);
		}
		return produtoPorString;
	}
}
