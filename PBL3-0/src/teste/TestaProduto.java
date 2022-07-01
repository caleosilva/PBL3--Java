package teste;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.itextpdf.text.log.SysoCounter;

import bancoDeDados.Dados;
import model.Fornecedor;
import model.Gerente;
import model.ProdutoEspecifico;
import model.ProdutoGeral;
import model.facade.GerenciadorDeProduto;

class TestaProduto {
	private GerenciadorDeProduto gdp = new GerenciadorDeProduto();
	private List<ProdutoGeral> listaDeProdutos = Dados.getListaProdutosGeral();
	private List<Fornecedor> listaDeFornecedor = Dados.getListaFornecedor();
	
	@BeforeEach
	void beforeEach() {
		// Adicionando Fornecedores:
		Fornecedor forn1 = new Fornecedor("11", "11", "Barbosa", "Salvador");
		Fornecedor forn2 = new Fornecedor("22", "22", "Joyce", "Feira de Santana");
		Fornecedor forn3 = new Fornecedor("33", "33", "Jussara", "Sergipe");
		
		listaDeFornecedor.add(forn1);
		listaDeFornecedor.add(forn2);
		listaDeFornecedor.add(forn3);
		
		// Adicionando Produtos:
		ProdutoGeral batata = new ProdutoGeral("batata");
		
		ProdutoEspecifico batata1 = new ProdutoEspecifico(5.60, "111", "10/07/2022", 60, 3, forn1);
		batata.getListaDeProdutos().add(batata1);
		ProdutoEspecifico batata2 = new ProdutoEspecifico(10.50, "222", "22/08/2022", 45, 2, forn2);
		batata.getListaDeProdutos().add(batata2);
		
		ProdutoGeral arroz = new ProdutoGeral("feijao");
	
		ProdutoEspecifico arroz1 = new ProdutoEspecifico(3.99, "333", "05/02/2023", 70, 1, forn3);
		arroz.getListaDeProdutos().add(arroz1);
		
		listaDeProdutos.add(batata);
		listaDeProdutos.add(arroz);

	}
	
	@AfterEach
	void tearDown() throws Exception {
		listaDeProdutos.clear();
	}
	
	// Adicionando:
	
	@Test
	void testeAdicionandoProdutoComNomeJaExistente() {
		
		HashMap<String, Object> dadosProduto = new HashMap<>();
		
		dadosProduto.put("nome", "batata");
		dadosProduto.put("preco", 10.5);
		dadosProduto.put("validade", "10/05/2022");
		dadosProduto.put("quantidade", 5.9);
		dadosProduto.put("unidadeDeMedida", 3);
		dadosProduto.put("fornecedor", listaDeFornecedor.get(0));
		
		gdp.cadastrarProdutos(dadosProduto);
		
		ProdutoGeral pg = listaDeProdutos.get(0);
		
		assertEquals(3, pg.getListaDeProdutos().size(), "Verificando Tamanho da lista apos adicionar um produto.");
	}
	
	@Test
	void testeAdicionandoNovoProdutoEVerificandoTamanhoDaLista() {
		
		HashMap<String, Object> dadosProduto = new HashMap<>();
		
		dadosProduto.put("nome", "arroz");
		dadosProduto.put("preco", 10.5);
		dadosProduto.put("validade", "10/05/2022");
		dadosProduto.put("quantidade", 5.9);
		dadosProduto.put("unidadeDeMedida", 3);
		dadosProduto.put("fornecedor", listaDeFornecedor.get(0));
		
		gdp.cadastrarProdutos(dadosProduto);
		
		assertEquals(3, listaDeProdutos.size(), "Verificando Tamanho da lista apos adicionar um novo produto.");
	}
	
	@Test
	void testeAdicionandoNovoProdutoEVerificandoInformacao() {
		
		HashMap<String, Object> dadosProduto = new HashMap<>();
		
		dadosProduto.put("nome", "abacaxi");
		dadosProduto.put("preco", 10.5);
		dadosProduto.put("validade", "12/02/2023");
		dadosProduto.put("quantidade", 30.0);
		dadosProduto.put("unidadeDeMedida", 1);
		dadosProduto.put("fornecedor", listaDeFornecedor.get(0));
		
		gdp.cadastrarProdutos(dadosProduto);
		
		ProdutoGeral pe = listaDeProdutos.get(2);
		
		assertEquals("12/02/2023", pe.getListaDeProdutos().get(0).getValidade(), "Confirmando informa��o de um novo produto.");
	}
	
	@Test
	void testeAdicionandoDoisNovosProdutosEVerificandoSeuNomeNaListaInternaDoFornecedor() {
		
		HashMap<String, Object> dadosProduto = new HashMap<>();
		
		dadosProduto.put("nome", "abacaxi");
		dadosProduto.put("preco", 10.5);
		dadosProduto.put("validade", "12/02/2023");
		dadosProduto.put("quantidade", 30.0);
		dadosProduto.put("unidadeDeMedida", 1);
		dadosProduto.put("fornecedor", listaDeFornecedor.get(1));
		
		gdp.cadastrarProdutos(dadosProduto);
		
		dadosProduto.put("nome", "banana");
		dadosProduto.put("preco", 14.9);
		dadosProduto.put("validade", "27/05/2023");
		dadosProduto.put("quantidade", 30.0);
		dadosProduto.put("unidadeDeMedida", 2);
		dadosProduto.put("fornecedor", listaDeFornecedor.get(1));
		
		gdp.cadastrarProdutos(dadosProduto);
		
//		ProdutoGeral pe = listaDeProdutos.get(2);
		
		assertEquals(2, listaDeFornecedor.get(1).getListaNomeProdutos().size(), "Confirmando informa��o do novo produto no fornecedor");
	}
	
	@Test
	void testeAdicionandoNovoProdutoESeuNomeNaListaInternaDoFornecedor() {
		
		HashMap<String, Object> dadosProduto = new HashMap<>();
		
		dadosProduto.put("nome", "abacaxi");
		dadosProduto.put("preco", 10.5);
		dadosProduto.put("validade", "12/02/2023");
		dadosProduto.put("quantidade", 30.0);
		dadosProduto.put("unidadeDeMedida", 1);
		dadosProduto.put("fornecedor", listaDeFornecedor.get(1));
		
		gdp.cadastrarProdutos(dadosProduto);
		
		assertEquals(2, listaDeFornecedor.get(1).getListaNomeProdutos().size(), "Confirmando informa��o do novo produto no fornecedor");
	}
	
	@Test
	void testExcluirProdutoValido() {
		
		ProdutoEspecifico produtoEspecifico = listaDeProdutos.get(0).getListaDeProdutos().get(0);
		
		boolean excluiu = gdp.excluirProdutos(produtoEspecifico);
		assertTrue(excluiu, "Tentando excluir um produto");
	}
	
	@Test
	void testExcluirTodosOsProdutosDaLista() {
		
		ProdutoEspecifico produtoEspecifico1 = listaDeProdutos.get(0).getListaDeProdutos().get(0);
		ProdutoEspecifico produtoEspecifico2 = listaDeProdutos.get(0).getListaDeProdutos().get(1);
		ProdutoEspecifico produtoEspecifico3 = listaDeProdutos.get(1).getListaDeProdutos().get(0);

		gdp.excluirProdutos(produtoEspecifico1);
		gdp.excluirProdutos(produtoEspecifico2);
		gdp.excluirProdutos(produtoEspecifico3);
		
		int totalProdutos = listaDeProdutos.get(0).getListaDeProdutos().size() + listaDeProdutos.get(1).getListaDeProdutos().size();
		
		assertEquals(0, totalProdutos, "Tentando todos os produtos da lista");
	}

	@Test
	void testEditandoNomeDeUmProduto() {
		HashMap<String, Object> produtoAEditar = gdp.encontrarProduto("111");
		HashMap<String, Object> novosDados = new HashMap<>();
				
		ProdutoEspecifico pe = (ProdutoEspecifico) produtoAEditar.get("produtoEspecifico");
		ProdutoGeral pg = (ProdutoGeral) produtoAEditar.get("produtoGeral");

		novosDados.put("nome", "novoNome");
		novosDados.put("preco", pe.getPreco());
		novosDados.put("quantidade", pe.getQuantidade());
		novosDados.put("unidadeDeMedida", pe.getUnidadeDeMedida());
		novosDados.put("fornecedor", pe.getFornecedor());
		novosDados.put("validade", pe.getValidade());
		
		gdp.editarProdutos(produtoAEditar, novosDados);

		assertEquals("novoNome", pg.getNome(), "Alterando o Nome.");
	}

	@Test
	void testEditandoPrecoDeUmProduto() {
		
		HashMap<String, Object> produtoAEditar = gdp.encontrarProduto("222");
		HashMap<String, Object> novosDados = new HashMap<>();
		ProdutoEspecifico pe = (ProdutoEspecifico) produtoAEditar.get("produtoEspecifico");
		ProdutoGeral pg = (ProdutoGeral) produtoAEditar.get("produtoGeral");

		novosDados.put("nome", pg.getNome());
		novosDados.put("preco", 20.2);
		novosDados.put("quantidade", pe.getQuantidade());
		novosDados.put("unidadeDeMedida", pe.getUnidadeDeMedida());
		novosDados.put("fornecedor", pe.getFornecedor());
		novosDados.put("validade", pe.getValidade());
		
		gdp.editarProdutos(produtoAEditar, novosDados);

		assertEquals(20.2, pe.getPreco(), "Alterando o preco.");
	}
	
	@Test
	void testEditandoFornecedorDeUmProduto() {
		
		HashMap<String, Object> produtoAEditar = gdp.encontrarProduto("222");
		HashMap<String, Object> novosDados = new HashMap<>();
		
		ProdutoEspecifico pe = (ProdutoEspecifico) produtoAEditar.get("produtoEspecifico");
		ProdutoGeral pg = (ProdutoGeral) produtoAEditar.get("produtoGeral");

		novosDados.put("nome", pg.getNome());
		novosDados.put("preco", pe.getPreco());
		novosDados.put("quantidade", pe.getQuantidade());
		novosDados.put("unidadeDeMedida", pe.getUnidadeDeMedida());
		novosDados.put("fornecedor", listaDeFornecedor.get(2));
		novosDados.put("validade", pe.getValidade());
		
		gdp.editarProdutos(produtoAEditar, novosDados);

		assertEquals(listaDeFornecedor.get(2) , pe.getFornecedor(), "Alterando o fornecedor.");
	}

	
	@Test
	void testEditandoValidadeDeUmProduto() {
		
		HashMap<String, Object> produtoAEditar = gdp.encontrarProduto("333");
		HashMap<String, Object> novosDados = new HashMap<>();
		
		ProdutoEspecifico pe = (ProdutoEspecifico) produtoAEditar.get("produtoEspecifico");
		ProdutoGeral pg = (ProdutoGeral) produtoAEditar.get("produtoGeral");

		novosDados.put("nome", pg.getNome());
		novosDados.put("preco", pe.getPreco());
		novosDados.put("quantidade", pe.getQuantidade());
		novosDados.put("unidadeDeMedida", pe.getUnidadeDeMedida());
		novosDados.put("fornecedor", pe.getFornecedor());
		novosDados.put("validade", "01/01/2021");
		
		gdp.editarProdutos(produtoAEditar, novosDados);
		assertEquals("01/01/2021" , pe.getValidade(), "Alterando a validade.");
	}
	
	@Test
	void testEditandoQuantidadeDeUmProduto() {
		
		HashMap<String, Object> produtoAEditar = gdp.encontrarProduto("333");
		HashMap<String, Object> novosDados = new HashMap<>();
		ProdutoEspecifico pe = (ProdutoEspecifico) produtoAEditar.get("produtoEspecifico");
		ProdutoGeral pg = (ProdutoGeral) produtoAEditar.get("produtoGeral");

		novosDados.put("nome", pg.getNome());
		novosDados.put("preco", pe.getPreco());
		novosDados.put("quantidade", 60.3);
		novosDados.put("unidadeDeMedida", pe.getUnidadeDeMedida());
		novosDados.put("fornecedor", pe.getFornecedor());
		novosDados.put("validade", pe.getValidade());
		
		gdp.editarProdutos(produtoAEditar, novosDados);		

		assertEquals(60.3 , pe.getQuantidade(), "Alterando a quantidade.");
	}
	
	// Buscando produto:
	@Test
	void buscarProdutoPeloIdExistente() {
		
		HashMap<String, Object> produto = gdp.encontrarProduto("111");
		ProdutoGeral pg = (ProdutoGeral) produto.get("produtoGeral");
		
		assertEquals("batata", pg.getNome(), "Buscando Produto atrav�s de um ID existente.");
	}
	
	@Test
	void buscarProdutoPeloIdNaoExistente() {
		HashMap<String, Object> produto = gdp.encontrarProduto("idNaoExistente");
		assertNull(produto, "Buscando Produto atrav�s de um ID N�O existente");
	}

}
