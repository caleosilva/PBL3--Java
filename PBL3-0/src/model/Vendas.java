package model;

import java.util.HashMap;

/**
 * 
 * Classe que possui as caracteristicas referente a um venda, alem disso, ela que eh instaciada para
 * um objeto do tipo vendas.
 * 
 * @author Caleo Silva e Joao Pedro.
 *
 */
public class Vendas {
	
	private String id;
	private String data; 
	private String horario; 
	private HashMap<String, Double> itens; 
	private String precoTotal;
	private String modoPagamento;
	
	// Construtor para inicializar cada atributo com o valor correto.
	public Vendas(String id, String data, String horario, HashMap<String, Double> itens, String precoTotal, String modoPagamento) {
		this.id = id;
		this.data = data;
		this.horario = horario;
		this.setItens(itens);
		this.precoTotal = precoTotal;
		this.modoPagamento = modoPagamento;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(String precoTotal) {
		this.precoTotal = precoTotal;
	}
	public String getModoPagamento() {
		return modoPagamento;
	}
	public void setModoPagamento(String modoPagamento) {
		this.modoPagamento = modoPagamento;
	}

	public HashMap<String, Double> getItens() {
		return itens;
	}

	public void setItens(HashMap<String, Double> itens) {
		this.itens = itens;
	}
	
	

}
