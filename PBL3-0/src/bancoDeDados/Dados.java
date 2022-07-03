package bancoDeDados;

import java.util.ArrayList;

import java.util.List;

import model.*;

/**
 * Classe responsavel por conter todas as listas (de maneira estatica) que armazenam os dados 
 * do sistema.
 * 
 * @author Caleo Silva e Joao Pedro.
 *
 */
public class Dados {
	
	// Lista que armazena informacoes do Cardapio:
	private static List <Cardapio> listaCardapio = new ArrayList<>(); 
	
	// Lista que armazena informacoes dos Fornecedores:
	private static List <Fornecedor> listaFornecedor = new ArrayList<>();
	
	// Lista que armazena informacoes das Vendas:
	private static List <Vendas> listaVendas = new ArrayList<>();
		
	private static List <ProdutoGeral> listaProdutosGeral = new ArrayList<>();

	// Lista que armazena informacoes dos Usuarios:
	private static List <Usuario> listaUsuario = new ArrayList<>();

	// Lista que armazena informacoes dos Clientes:
	private static List <Cliente> listaCliente = new ArrayList<>();
		
	// Getters:
	
	public static List<Cardapio> getListaCardapio() {
		return listaCardapio;
	}

	public static List<Fornecedor> getListaFornecedor() {
		return listaFornecedor;
	}

	public static List<Vendas> getListaVendas() {
		return listaVendas;
	}
	
	public static List<ProdutoGeral> getListaProdutosGeral() {
		return listaProdutosGeral;
	}

	public static List<Usuario> getListaUsuario() {
		return listaUsuario;
	}
	
	public static List<Cliente> getListaCliente() {
		return listaCliente;
	}
	
	
}
