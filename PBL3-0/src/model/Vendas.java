package model;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * 
 * Classe que possui as caracteristicas referente a um venda, al�m disso, ela que � instaciada para
 * um objeto do tipo vendas.
 * 
 * @author Caleo Silva e Jo�o Pedro.
 *
 */
public class Vendas {
	
	private String id;
	private String data; 
	private String horario; 
	private HashMap<String, Double> itens; 
	private String precoTotal;
	private String modoPagamento;
	private String clienteVinculado;
	
	// Construtor para inicializar cada atributo com o valor correto.
	public Vendas(String id, String dataFormatada, String horario, HashMap<String, Double> itens, String precoTotal, String modoPagamento, String clienteVinculado) {
		this.id = id;
		this.data = dataFormatada;
		this.horario = horario;
		this.setItens(itens);
		this.precoTotal = precoTotal;
		this.modoPagamento = modoPagamento;
		this.setClienteVinculado(clienteVinculado);
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

	public String getClienteVinculado() {
		return clienteVinculado;
	}

	public void setClienteVinculado(String clienteVinculado) {
		this.clienteVinculado = clienteVinculado;
	}
	
	

}
