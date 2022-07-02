package application;

import model.Cardapio;


import model.Fornecedor;
import model.Funcionario;
import model.Gerente;
import model.ProdutoEspecifico;
import model.ProdutoGeral;
import model.Usuario;
import model.Vendas;

import java.util.HashMap;
import java.util.List;

import bancoDeDados.Dados;

/**
 * 
 * @author Caleo Silva e Joao Pedro
 * Classe responsavel por adicionar alguns itens no sistema para que a manipulacao do mesmo seja
 * facilitada.
 *
 */

public class PreCadastro {
	
	/**
	 * Classe estatica que adicionara as informacoes desejadas no sistema.
	 * @param args
	 */
	public static void main(String[] args) {
		// Adicionando Usu�rios:
		List<Usuario> lstU = Dados.getListaUsuario();
		
		Gerente g1 = new Gerente("123", "a", "a", "Gerente");
		Funcionario f1 = new Funcionario("321", "jose", "123", "Funcionario");
		
		
		lstU.add(g1);
		lstU.add(f1);
		
		// Adicionando Fornecedores:
		Fornecedor forn1 = new Fornecedor("1941", "1111", "Barbosa", "Salvador");
		Fornecedor forn2 = new Fornecedor("2142", "2222", "Joyce", "Feira de Santana");
		Fornecedor forn3 = new Fornecedor("9723", "3333", "Jussara", "Sergipe");
		
		List<Fornecedor> lstF = Dados.getListaFornecedor();
		lstF.add(forn1);
		lstF.add(forn2);
		lstF.add(forn3);
		
		// Adicionando Produtos:
		
		ProdutoGeral batata = new ProdutoGeral("batata");
		
		ProdutoEspecifico batata1 = new ProdutoEspecifico(5.60, "145161", "10/07/2022", 60, 3, forn1);
		batata.getListaDeProdutos().add(batata1);
		forn1.getListaNomeProdutos().add(batata.getNome());
		
		ProdutoEspecifico batata2 = new ProdutoEspecifico(10.50, "298242", "22/08/2022", 45, 3, forn2);
		batata.getListaDeProdutos().add(batata2);
		forn2.getListaNomeProdutos().add(batata.getNome());
		
		
		ProdutoGeral arroz = new ProdutoGeral("Arroz");
	
		ProdutoEspecifico arroz1 = new ProdutoEspecifico(3.99, "973633", "05/02/2023", 70, 1, forn3);
		arroz.getListaDeProdutos().add(arroz1);
		forn3.getListaNomeProdutos().add(arroz.getNome());
		
		ProdutoGeral leite = new ProdutoGeral("Leite");
		
		List<ProdutoGeral> lstPG = Dados.getListaProdutosGeral();
		
		lstPG.add(batata);
		lstPG.add(leite);
		lstPG.add(arroz);
		
		// Adicionando
		
		HashMap<String, Double> item1 = new HashMap<String, Double>();
		HashMap<String, Double> item2 = new HashMap<String, Double>();
		item1.put("arroz", 3.5);
		item1.put("batata", 4.5);
		item2.put("batata", 2.0);
		Cardapio novoCardapio = new Cardapio("pizza", "1424", "Muito boa e apetitosa", item1, "7,0", "comida");
		Dados.getListaCardapio().add(novoCardapio);
		Cardapio novoCardapio2 = new Cardapio("lasanha", "5436", "gostosa demais", item2, "5,5", "comida");
		Dados.getListaCardapio().add(novoCardapio2);
		
		HashMap<String, Double> itensVendas = new HashMap<String, Double>();;
		itensVendas.put("pizza", 5.0);
		itensVendas.put("suco", 2.4);
		itensVendas.put("lasanha", 5.0);
		Vendas v1 = new Vendas("65346", "27/12/2022", "15:20", itensVendas, "25.50", "Pix", "Liz");
		
		HashMap<String, Double> itensVendas2 = new HashMap<String, Double>();;
		itensVendas2.put("coca-cola", 3.0);
		itensVendas2.put("coxinha", 5.0);
		itensVendas2.put("pastel", 2.0);
		
		Vendas v2 = new Vendas("54555", "11/02/2023", "15:20", itensVendas2, "17.75", "Dinheiro", "Judite");
		
		Dados.getListaVendas().add(v1);
		Dados.getListaVendas().add(v2);
	}
}
