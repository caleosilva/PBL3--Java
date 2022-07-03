package teste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bancoDeDados.Dados;
import model.Cardapio;
import model.Cliente;
import model.Fornecedor;
import model.ProdutoEspecifico;
import model.ProdutoGeral;
import model.Vendas;
import model.facade.GerenciadorDeFornecedor;
import model.facade.GerenciadorDeVendas;


public class TestaVendas {
	private List<ProdutoGeral> listaDeProdutos = new ArrayList<ProdutoGeral>();
	private List<Cardapio> listaDeCardapio = new ArrayList<Cardapio>();
	private List<Cardapio> listaDeCardapio1 = new ArrayList<Cardapio>();
	private HashMap<String, Double> item1 = new HashMap<String, Double>();
	private HashMap<String, Double> item2 = new HashMap<String, Double>();
	private HashMap<String, Double> item4 = new HashMap<String, Double>();
	private List<Vendas> listaDeVendas = new ArrayList<Vendas>();
	private List<Vendas> listaDeVendas2 = new ArrayList<Vendas>();
	private GerenciadorDeVendas gdv = new GerenciadorDeVendas();
	private HashMap<String, Object> dados = new HashMap<>();
	private HashMap<String, Object> dados2 = new HashMap<>();
	HashMap<String, Double> itensVendas2 = new HashMap<String, Double>();
	@BeforeEach
	void beforeEach() {
		// Adicionando Fornecedores:
		ProdutoGeral batata = new ProdutoGeral("batata");
		Fornecedor forn1 = new Fornecedor("id1", "cnpj1", "nome1", "endereco1");

		ProdutoEspecifico batata1 = new ProdutoEspecifico(5.60, "111", "10/07/2022", 60, 3, forn1);
		batata.getListaDeProdutos().add(batata1);
		ProdutoEspecifico batata2 = new ProdutoEspecifico(10.50, "222", "22/08/2022", 45, 2, forn1);
		batata.getListaDeProdutos().add(batata2);
		
		
		ProdutoGeral arroz = new ProdutoGeral("feijao");
	
		ProdutoEspecifico arroz1 = new ProdutoEspecifico(3.99, "333", "05/02/2023", 70, 1, forn1);
		arroz.getListaDeProdutos().add(arroz1);
		
		listaDeProdutos.add(batata);
		listaDeProdutos.add(arroz);
		item1.put("batata", 4.5);
		item1.put("arroz", 3.2);
		Cardapio novoCardapio = new Cardapio("pizza", "1424", "comida", item1, "7.0", "sobremesa");
		listaDeCardapio.add(novoCardapio);
		
		
		item2.put("batata", 2.0);
		Cardapio novoCardapio2 = new Cardapio("lasanha", "5435", "comida", item2, "2.0", "sobremesa");
		listaDeCardapio.add(novoCardapio2);
		
		
		HashMap<String, Double> itensVendas3 = new HashMap<String, Double>();;
		itensVendas3.put("Pizza", 5.0);
		itensVendas3.put("Suco", 2.4);
		itensVendas3.put("Lasanha", 5.0);
		Vendas v1 = new Vendas("65346", "27/12/2022", "15:20", itensVendas3, "25.50", "Pix", "Luffy");
		
		HashMap<String, Double> itensVendas4 = new HashMap<String, Double>();;
		itensVendas4.put("Coca-Cola", 3.0);
		itensVendas4.put("Coxinha", 5.0);
		itensVendas4.put("Pastel", 2.0);
		
		Vendas v2 = new Vendas("54555", "11/02/2023", "05:25", itensVendas4, "17.75", "Dinheiro", "Zoro");
		listaDeVendas2.add(v1);
		listaDeVendas2.add(v2);
		}
	
	
	
	@Test
	public void verificandoSeOsPratosParaVendaEstaoRegistradosNoCardapio() {
		
		Cardapio novoCardapio = new Cardapio("pizza", "1424", "comida", item1, "4.5", "sobremesa");
		Cardapio novoCardapio2 = new Cardapio("suco", "6543", "comida", item1, "9.5", "sobremesa");
		listaDeCardapio1.add(novoCardapio);
		listaDeCardapio1.add(novoCardapio2);
		
		item4.put("pizza", 2.0);
		item4.put("suco", 3.0);
		double resultado = gdv.conferirVenda(listaDeCardapio1, item4);
		assertEquals(37.5, resultado, "Caso os pratos estejam registrados no cardapio é retornado o valor total da venda");
	}
	
	@Test
	public void verificandoSeOsPratosNaoRegistradosNoCardapio() {
		
		Cardapio novoCardapio = new Cardapio("pizza", "1424", "comida", item1, "4.5", "sobremesa");
		listaDeCardapio.add(novoCardapio);
		
		item1.put("pizza", 5.2);
		item1.put("suco", 3.5);
		double resultado = gdv.conferirVenda(listaDeCardapio, item1);
		assertEquals(-1, resultado, "Caso nem todos os pratos estejam registrados no cardapio é retornado -1");
	}
	
	@Test
	public void verificaSomaDosProdutosPresentesNosPratosDoCardapioParaVenda() {
		HashMap<String, Double> item3 = new HashMap<String, Double>();
		item3.put("pizza", 2.0);
		item3.put("lasanha", 1.0);
		
		HashMap<String, Double> resultado = gdv.somarProdutos(listaDeCardapio, item3);
		assertEquals(11, resultado.get("batata"), "Soma todos os produtos presentes nos pratos para conferir antes da venda");
	}	
	
	@Test
	public void verificaSomaDosProdutosNaoPresentesNosPratosDoCardapioParaVenda() {
		HashMap<String, Double> item3 = new HashMap<String, Double>();
		item3.put("pizza", 2.0);
		item3.put("lasanha", 1.0);
		item3.put("salada", 3.0);
		HashMap<String, Double> resultado = gdv.somarProdutos(listaDeCardapio, item3);
		assertEquals(11, resultado.get("batata"), "Soma dos produtos, incluindo um produto que não está em nenhum prato (logo, não foi incluido na soma)");
	}	
	
	
	@Test
	public void removendoProdutosDoEstoque() {
		HashMap<String, Double> item3 = new HashMap<String, Double>();
		item3.put("batata", 4.0);
		item3.put("arroz", 3.0);
		
		boolean confirmar = gdv.removerDoEstoque(listaDeProdutos, item3);
		
		assertTrue(confirmar, "confirma se os produtos foram removidos do estoque");
	}
	
	@Test
	public void removendoProdutosDoEstoqueDeVariosSubProdutos() {
		HashMap<String, Double> item3 = new HashMap<String, Double>();
		item3.put("batata", 97.0);
		item3.put("arroz", 15.0);
		
		boolean confirmar = gdv.removerDoEstoque(listaDeProdutos, item3);
		
		assertTrue(confirmar, "Remove itens de varios subprodutos do estoque");
	}
	@Test
	public void removendoProdutosDoEstoqueEVerificandoQuandidadeDeProdutos() {
		HashMap<String, Double> item3 = new HashMap<String, Double>();
		item3.put("batata", 97.0);
		
		boolean confirmar = gdv.removerDoEstoque(listaDeProdutos, item3);
		
		assertEquals(8, listaDeProdutos.get(0).getListaDeProdutos().get(1).getQuantidade(), "Removendo produtos do Estoque para a venda e verificando quantidade");
	}
	
	@Test
	public void TentandoRemoverProdutosSemEstoqueSuficiente() {
		HashMap<String, Double> item3 = new HashMap<String, Double>();
		item3.put("batata", 500.0);
		item3.put("arroz", 500.0);
		
		boolean confirmar = gdv.removerDoEstoque(listaDeProdutos, item3);
		
		assertFalse(confirmar, "Retorna false caso não tenham produtos suficientes no estoque");
	}
	
	@Test 
	public void cadastrandoUmaVenda() {
		HashMap<String, Double> itensVendas = new HashMap<String, Double>();;
		itensVendas.put("Pizza", 5.0);
		itensVendas.put("Suco", 2.4);
		itensVendas.put("Lasanha", 5.0);
		
		dados.put("modoPagamento", "PIX");
		dados.put("preco", "15.5");
		dados.put("pratos", itensVendas);
		dados.put("cliente", "Nami");
		
		boolean confirma = gdv.cadastrarVendas(dados);
		assertTrue(confirma, "Confirma se uma venda foi cadastrada com sucesso");
	}

	
	@Test
	public void buscandoVendaAtravesDoID() {
		Vendas venda1 = gdv.encontrarVendas(listaDeVendas2, "54555");
		
		assertNotNull(venda1, "Verifica se o ID inserido correseponde a alguma venda registrada");
	}
	
	@Test
	public void buscandoVendaNaoRegistrada() {
		Vendas venda1 = gdv.encontrarVendas(listaDeVendas2, "54356");
		
		assertNull(venda1, "Retorna Null caso o ID inserido não pertença a nenhuma venda registrada");
	}
	
	@Test
	public void editandoVendaRegistrada() {
		
		boolean confirma = gdv.editarVendas(listaDeVendas2.get(0), dados);
		
		assertTrue(confirma, "Retorna Null caso o ID inserido não pertença a nenhuma venda registrada");
	}
	
	@Test
	public void editandoDataDaVendaRegistrada() {
		
		itensVendas2.put("Pizza", 5.0);
		itensVendas2.put("Suco", 2.4);
		itensVendas2.put("Lasanha", 5.0);
		
		dados2.put("modoPagamento", "Pix");
		dados2.put("preco", "25.50");
		dados2.put("pratos", itensVendas2);
		dados2.put("cliente", "Luffy");
		dados2.put("data", "13/12/2022");
		dados2.put("horario", "12:34");
		
		gdv.editarVendas(listaDeVendas2.get(0), dados2);
		
		
		assertEquals("13/12/2022", listaDeVendas2.get(0).getData(), "Verifica se a data foi editada corretamente");
	}
	
	@Test
	public void editandoHoraDaVendaRegistrada() {
		itensVendas2.put("Pizza", 5.0);
		itensVendas2.put("Suco", 2.4);
		itensVendas2.put("Lasanha", 5.0);
		
		dados2.put("modoPagamento", "Pix");
		dados2.put("preco", "25.50");
		dados2.put("pratos", itensVendas2);
		dados2.put("cliente", "Luffy");
		dados2.put("data", "13/12/2022");
		dados2.put("horario", "13:30");
		gdv.editarVendas(listaDeVendas2.get(0), dados2);
		
		assertEquals("13:30", listaDeVendas2.get(0).getHorario(), "Verifica se o horario foi editado corretamente");
	}
	
	@Test
	public void editandoValorTotalDaVendaRegistrada() {
		
		itensVendas2.put("Pizza", 5.0);
		itensVendas2.put("Suco", 2.4);
		itensVendas2.put("Lasanha", 5.0);
		
		dados2.put("modoPagamento", "Pix");
		dados2.put("preco", "72,25");
		dados2.put("pratos", itensVendas2);
		dados2.put("cliente", "Luffy");
		dados2.put("data", "13/12/2022");
		dados2.put("horario", "72,25");
		gdv.editarVendas(listaDeVendas2.get(0), dados2);
		
		assertEquals("72,25", listaDeVendas2.get(0).getPrecoTotal(), "Verifica se o Valor total editado corretamente");
	}
	
	@Test
	public void editandoModoDePagamentoDaVendaRegistrada() {
		
		itensVendas2.put("Pizza", 5.0);
		itensVendas2.put("Suco", 2.4);
		itensVendas2.put("Lasanha", 5.0);
		
		dados2.put("modoPagamento", "Barras de ouro");
		dados2.put("preco", "25.50");
		dados2.put("pratos", itensVendas2);
		dados2.put("cliente", "Luffy");
		dados2.put("data", "13/12/2022");
		dados2.put("horario", "12:34");
		gdv.editarVendas(listaDeVendas2.get(0), dados2);
		
		assertEquals("Barras de ouro", listaDeVendas2.get(0).getModoPagamento(), "Verifica se o modo de pagamento editado corretamente");
	}
	
	@Test
	public void excluirVenda() {
		Dados.getListaVendas().add(listaDeVendas2.get(0));
		boolean confirma = gdv.excluirVendas(Dados.getListaVendas().get(0));
		
		assertTrue(confirma, "Confirma se a venda foi excluída");
	}
	
	@Test
	public void TendandoexcluirVendaNaoRegistrada() {
		
		itensVendas2.put("Pizza", 5.0);
		Vendas v4 = new Vendas("65656", "11/02/2043", "07:51", itensVendas2, "87.95", "cartão", "Sanji");
		
		boolean confirma = gdv.excluirVendas(v4);
		assertFalse(confirma, "Rertorna false caso a venda não seja encontrada");
	}
	
	@Test
	public void apagandoVendasRegistradasEVerificandoTamanhoDaLista() {
		Dados.getListaVendas().add(listaDeVendas2.get(0));
		gdv.excluirVendas(Dados.getListaVendas().get(0));
		
		assertEquals(0, Dados.getListaVendas().size(), "Verifica o tamanho da lista após apagar uma venda");
	}
	
}
