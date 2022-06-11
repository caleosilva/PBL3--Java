package bancoDeDados;

import java.util.ArrayList;

import java.util.List;

import model.*;

/**
 * Classe respons�vel por conter todas as listas (de maneira est�tica) que armazenam os dados do sistema.
 * 
 * @author Caleo Silva e Jo�o Pedro.
 *
 */
public class Dados {
	
	// Lista que armazena informa��es do Card�pio:
	private static List <Cardapio> listaCardapio = new ArrayList<>(); 
	
	// Lista que armazena informa��es dos Fornecedores:
	private static List <Fornecedor> listaFornecedor = new ArrayList<>();
	
	// Lista que armazena informa��es das Vendas:
	private static List <Vendas> listaVendas = new ArrayList<>();
		
	private static List <ProdutoGeral> listaProdutosGeral = new ArrayList<>();

	// Lista que armazena informa��es dos Usu�rios:
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
