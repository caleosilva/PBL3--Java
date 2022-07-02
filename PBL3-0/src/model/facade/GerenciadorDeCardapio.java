package model.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bancoDeDados.Dados;
import model.Cardapio;
import model.ProdutoGeral;
import model.Vendas;


/**
 * Classe responsável por implementar os métodos responsáveis por cadastrar/editar/excluir objetos do tipo
 * Cardapio.
 * 
 * @author Caleo Silva e João Pedro.
 *
 */
public class GerenciadorDeCardapio{
	private List<Cardapio> listaDeCardapio = Dados.getListaCardapio();
	public boolean editarCardapio(Cardapio cardapio, HashMap<String, Object> dados) {
		
		boolean sucesso = false;
		
		// Verificando se os dados são null
		if(dados == null && cardapio != null) {
			return false;
		} else {
			
			String nome = String.valueOf(dados.get("nome"));
			String preco = String.valueOf(dados.get("preco"));
			String categoria = String.valueOf(dados.get("categoria"));
			String descricao =	String.valueOf(dados.get("descricao"));
			HashMap<String, Double> itensCardapio = (HashMap<String, Double>) dados.get("itens");
			
			cardapio.setNome(nome);
			cardapio.setPreco(preco);
			cardapio.setCategoria(categoria);
			cardapio.setDescricao(descricao);
			cardapio.setItensCardapio(itensCardapio);
			return true;
		}
		
	}

	public boolean cadastrarCardapio(HashMap<String, Object> dados) {
		
		boolean sucesso = false;
		//Gerando id
		String id;
		int exclusivo = 0;
		//String precoItem = Double.toString(valor);
		do {
			id = GerenciadorDeId.gerarId(3);
			Cardapio c = GerenciadorDeCardapio.encontrarCardapio(listaDeCardapio, id);
			
			if (c == null) exclusivo = 1;
			
		} while (exclusivo == 0);
		
		// Convertendo os valores:
		String nome = String.valueOf(dados.get("nome"));
		String preco = String.valueOf(dados.get("preco"));
		String categoria = String.valueOf(dados.get("categoria"));
		String descricao =	String.valueOf(dados.get("descricao"));
		HashMap<String, Double> itensCardapio = (HashMap<String, Double>) dados.get("itens");
		Cardapio novoItem = new Cardapio(nome, id,descricao, itensCardapio, preco, categoria);
		
		
		
		boolean confirmacao = listaDeCardapio.add(novoItem);
		return confirmacao;
		
	}

	public boolean excluirCardapio(Cardapio cardapio) {
		boolean excluido = false;
		if(cardapio != null) {
			excluido = listaDeCardapio.remove(cardapio);
		}
		return excluido;
		
	}
	
	/**
	 * Método responsável por buscar um Item no cardapio através de um ID informado pelo usuario.
	 * 
	 * @param listaDeCardapio Um ArraysList que contém todos os Itens cadastrados no cardapio.
	 * @param id ID de um item presente no cardapio.
	 * 
	 * @return Endereço de memória do item do cardapio se for encontrado, caso contrário retorna null.
	 */
	public static Cardapio encontrarCardapio(List<Cardapio> listaDeCardapio, String id) {
		
		for(int i = 0; i < listaDeCardapio.size(); i++){
			
			Cardapio confereCardapio = listaDeCardapio.get(i);
	        if(confereCardapio.getId().equals(id)){
	        	return confereCardapio;
	        }
		}
		
		return null;
	}
	
	/**
	 * Método responsável por conferir se os itens que compõem o prato digitado pelo usuario
	 * estão cadastrado na lista de produtos.
	 * 
	 * @param listaDeProdutos Um ArraysList que contém todos os Produtos cadastrados.
	 * @param itensCardapio HashMap conta.
	 * 
	 * @return True caso os itens estejam cadastrados, caso contrário retorna false.
	 */
	public boolean conferirItens(List<ProdutoGeral> listaDeProdutos, String itemCardapio) {
		for (ProdutoGeral produtoAtual : listaDeProdutos) {
			if (itemCardapio.equals(produtoAtual.getNome().toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Método responsável por guardar os itens e suas quantidades digitados pelo usuario
	 * em um HashMaP
	 * 
	 * @return HashMap contendo os itens e suas quantidades.
	 */
	public static HashMap<String, Double> pegarItens(){
		HashMap<String, Double> itensCardapio = new HashMap<String, Double>();
		//Variaveis
		boolean sair = false;
		String item = "";
		double quant = 0;
		double controle = 0;
		int opcao;
		do {
			item = ViewMetodosGerais.pegarNomeItem();
			ViewGerenciamentoCardapio.mensagemQuantidadeItens();
			quant = ViewMetodosGerais.pegarValores();
			
			if (itensCardapio.containsKey(item)) {
				controle = itensCardapio.get(item);
				itensCardapio.remove(item);
				quant += controle;
			}
			
			itensCardapio.put(item, quant);
			
			opcao = ViewMetodosGerais.continuarAcao("adicionando itens");
			if (opcao == 0) {
			sair = true;}
		}while(sair == false);
		
		return itensCardapio;
	}
}
