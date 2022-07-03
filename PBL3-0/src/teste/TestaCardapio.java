package teste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bancoDeDados.Dados;
import model.Cardapio;
import model.ProdutoGeral;
import model.facade.GerenciadorDeCardapio;
import model.facade.GerenciadorDeFornecedor;

public class TestaCardapio {
	private List<ProdutoGeral> listaDeProdutos = new ArrayList<ProdutoGeral>();
	private List<Cardapio> listaDeCardapio = new ArrayList<Cardapio>();
	private HashMap<String, Double> item1 = new HashMap<String, Double>();
	private GerenciadorDeCardapio gdc = new GerenciadorDeCardapio();
	HashMap<String, Double> item01 = new HashMap<String, Double>();
	HashMap<String, Object> dados1 = new HashMap<String, Object>();
	Cardapio novoCardapio2;
	@BeforeEach
	void beforeEach() { 
		
		item01.put("batata", 4.2);
		item01.put("arroz", 3.7);
		
		
		dados1.put("nome", "Churrasco");
		dados1.put("preco", "15.75");
		dados1.put("categoria", "carne");
		dados1.put("descricao", "Hmmm carninha");
		dados1.put("itens", item01);
		novoCardapio2 = new Cardapio("pizza", "1424", "comida", item1, "7.5", "sobremesa");
	}
	
	
	@Test 
	public void testVerificandoSeOsItensParaOCardapioEstaoRegistrados() {
		ProdutoGeral manga = new ProdutoGeral("manga");
		listaDeProdutos.add(manga);
		boolean confirmar = gdc.conferirItens(listaDeProdutos, "manga");
		assertTrue(confirmar, "Verificando se os produtos adicionados ao prato estão cadastrados");
	}
	
	@Test
	public void testVerificandoItensNaoRegistradosParaOCardapio() {
		ProdutoGeral açucar = new ProdutoGeral("açucar");
		listaDeProdutos.add(açucar);
		boolean confirmar = gdc.conferirItens(listaDeProdutos, "manga");
		assertFalse(confirmar, "Verificando produtos que não estão registrados");
	}
	
	@Test
	public void testCadastrandoPratoNoCardapio() {
		HashMap<String, Double> item2 = new HashMap<String, Double>();
		item2.put("batata", 4.2);
		item2.put("arroz", 3.7);
		
		
		HashMap<String, Object> dados = new HashMap<String, Object>();
		
		dados.put("nome", "batata com arroz");
		dados.put("preco", "15.75");
		dados.put("categoria", "bat ata");
		dados.put("descricao", "Hmmm batatarroz");
		dados.put("itens", item2);
		
		boolean confirmar = gdc.cadastrarCardapio(dados);
		assertTrue(confirmar, "Cadastrando um novo prato no cardapio");
	}
	
	@Test
	public void testCadastrandoPratoNoCardapioEVerificandoTamanhoDaLista() {
		HashMap<String, Double> item2 = new HashMap<String, Double>();
		item2.put("batata", 4.2);
		
		HashMap<String, Object> dados = new HashMap<String, Object>();
		
		dados.put("nome", "batata frita");
		dados.put("preco", "15.75");
		dados.put("categoria", "bat ata");
		dados.put("descricao", "Hmmm batatinha frita");
		dados.put("itens", item2);

		
		gdc.cadastrarCardapio(dados);
		gdc.cadastrarCardapio(dados);
		assertEquals(3,Dados.getListaCardapio().size(), "Verificando tamanho da lista após adicionar prato no cardapio");
	}
	
	@Test
	public void testBuscandoPratoCadastradoPorID() {
		Cardapio novoCardapio = new Cardapio("pizza", "1424", "comida", item1, "7.5", "sobremesa");
		listaDeCardapio.add(novoCardapio);
		
		Cardapio c1 = gdc.encontrarCardapio(listaDeCardapio, "1424");
		assertNotNull(c1, "Verificando se o ID pertence a um prato registrado no cardapio");
	}
	
	@Test
	public void testBuscandoPratoNaoCadastrado() {
		Cardapio c1 = gdc.encontrarCardapio(listaDeCardapio, "6435");
		assertNull(c1, "Verificando se o ID pertence a um prato registrado no cardapio");
	}
	
	@Test
	public void testEditandoInformacaoCardapio() {
		Cardapio novoCardapio = new Cardapio("pizza", "1424", "comida", item1, "7.5", "sobremesa");
		listaDeCardapio.add(novoCardapio);
		
		boolean confirmar = gdc.editarCardapio(novoCardapio, dados1);
		assertTrue(confirmar, "Verificando se o prato do cardapio foi editado");
	}
	
	@Test
	public void testEditandoNomeCardapio() {
		HashMap<String, Object> dados2 = new HashMap<String, Object>();
		dados2.put("nome", "Churrasco");
		dados2.put("preco", "7.5");
		dados2.put("categoria", "sobremesa");
		dados2.put("descricao", "comida");
		dados2.put("itens", item01);
		
		gdc.editarCardapio(novoCardapio2, dados2);
		assertEquals("Churrasco",novoCardapio2.getNome(), "Verificando se o nome do prato alterado");
	}
	
	@Test
	public void testEditandoDescricaoCardapio() {
		
		HashMap<String, Object> dados2 = new HashMap<String, Object>();
		dados2.put("nome", "pizza");
		dados2.put("preco", "7.5");
		dados2.put("categoria", "sobremesa");
		dados2.put("descricao", "De peperone");
		dados2.put("itens", item01);
		
		gdc.editarCardapio(novoCardapio2, dados2);
		assertEquals("De peperone",novoCardapio2.getDescricao(), "Verificando se a descriçao do prato foi alterada");
	}
	
	@Test
	public void testEditandoCategoriaCardapio() {
		HashMap<String, Object> dados2 = new HashMap<String, Object>();
		dados2.put("nome", "pizza");
		dados2.put("preco", "7.5");
		dados2.put("categoria", "Entrada");
		dados2.put("descricao", "comida");
		dados2.put("itens", item01);
		
		gdc.editarCardapio(novoCardapio2, dados2);
		assertEquals("Entrada",novoCardapio2.getCategoria(), "Verificando se a categoria do prato alterado");
	}
	
	@Test
	public void testEditandoPrecoCardapio() {
		HashMap<String, Object> dados2 = new HashMap<String, Object>();
		dados2.put("nome", "pizza");
		dados2.put("preco", "13.25");
		dados2.put("categoria", "sobremesa");
		dados2.put("descricao", "comida");
		dados2.put("itens", item01);
		
		
		gdc.editarCardapio(novoCardapio2, dados2);
		assertEquals("13.25",novoCardapio2.getPreco(), "Verificando se o preço do prato alterado");
	}
	
	
	@Test
	public void testEditandoItensCardapio() {
		HashMap<String, Object> dados2 = new HashMap<String, Object>();
		HashMap<String, Double> itensNovos = new HashMap<String, Double>();
		
		itensNovos.put("Calabresa", 4.0);
		itensNovos.put("tempero", 0.6);
		itensNovos.put("Queijo", 4.0);
		
		dados2.put("nome", "pizza");
		dados2.put("preco", "13.25");
		dados2.put("categoria", "sobremesa");
		dados2.put("descricao", "comida");
		dados2.put("itens", itensNovos);
		
		
		gdc.editarCardapio(novoCardapio2, dados2);
		assertEquals(4.0,novoCardapio2.getItensCardapio().get("Calabresa"), "Verificando se o novo produto consta no prato");
	}
	
	
	@Test
	public void testExcluindoPratoDoCardapio() {
		
		boolean confirmar = gdc.excluirCardapio(Dados.getListaCardapio().get(0));
		assertTrue(confirmar, "Verificando se o prato foi excluido do cardapio");
	}
	
	@Test
	public void testExcluindoTodosOsPratoDoCardapio() {
		for (Cardapio a : Dados.getListaCardapio()) {
			System.out.println(a.getNome());
		}
		boolean confirmar = gdc.excluirCardapio(Dados.getListaCardapio().get(0));
		boolean confirmar2 = gdc.excluirCardapio(Dados.getListaCardapio().get(0));
		
		assertEquals(0, Dados.getListaCardapio().size(), "Apagando todos os itens do cardapio e verificando tamanho da lista");
	}
	
	@Test
	public void testExcluindoPratoNaoRegistrado() {
		
		Cardapio novoCardapio = new Cardapio("pizza", "1424", "comida", item1, "7.5", "sobremesa");
		boolean confirmar = gdc.excluirCardapio(novoCardapio);
		assertFalse(confirmar, "Tentando excluir um prato com ID não registrado");
	}
}
