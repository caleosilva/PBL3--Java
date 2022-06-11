package bancoDeDados;

import java.util.ArrayList;

import java.util.List;

import model.*;

/**
 * Classe responsável por conter todas as listas (de maneira estática) que armazenam os dados do sistema.
 * 
 * @author Caleo Silva e João Pedro.
 *
 */
public class Dados {
	
	// Lista que armazena informações do Cardápio:
	private static List <Cardapio> listaCardapio = new ArrayList<>(); 
	
	// Lista que armazena informações dos Fornecedores:
	private static List <Fornecedor> listaFornecedor = new ArrayList<>();
	
	// Lista que armazena informações das Vendas:
	private static List <Vendas> listaVendas = new ArrayList<>();
		
	private static List <ProdutoGeral> listaProdutosGeral = new ArrayList<>();

	// Lista que armazena informações dos Usuários:
	private static List <Usuario> listaUsuario = new ArrayList<>();

	
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
}
