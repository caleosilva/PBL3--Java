package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoEspecifico {
	
	// Atributos:
	private double preco;
	private String id;
	private String validade;
	private double quantidade;
	private Fornecedor fornecedor;
	private int unidadeDeMedida;
	
	private String fornecedorToString;
	private String nome;
	private String unidadeDeMedidaFormatada;
	
	// Construtor:
	public ProdutoEspecifico(double preco, String id, String validade, double quantidade, int unidadeDeMedida, Fornecedor fornecedor) {
		this.preco = preco;
		this.id = id;
		this.validade = validade;
		this.quantidade = quantidade;
		this.unidadeDeMedida = unidadeDeMedida;
		this.fornecedor = fornecedor;
	}
	
	
	public String getUnidadeDeMedidaFormatada() {
		
		int unidadeDeMedida = getUnidadeDeMedida();
		
		String udm = null;
		
		switch (unidadeDeMedida) {
		
		case 1: {
			udm = "Quilograma";
			break;
		}
		case 2: {
			udm = "Litro";
			break;
		}
		
		case 3: {
			udm = "Unidade";
			break;
		}
		
		default:
			this.unidadeDeMedidaFormatada = null;
		}
		
		return this.unidadeDeMedidaFormatada = udm;
	}
	
	
	// Getters e Setters
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getValidade() {
		return validade;
	}
	public void setValidade(String validade) {
		this.validade = validade;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public int getUnidadeDeMedida() {
		return unidadeDeMedida;
	}


	public void setUnidadeDeMedida(int unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}
	
	public String getFornecedorToString() {
		return this.fornecedorToString = fornecedor.getNome();
	}
	
	public LocalDate getDataLocalDate() {
        LocalDate ld = LocalDate.parse(getValidade(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return ld;
	}
	
}
