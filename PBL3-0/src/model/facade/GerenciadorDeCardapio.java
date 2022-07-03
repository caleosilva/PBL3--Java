package model.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bancoDeDados.Dados;
import model.Cardapio;
import model.ProdutoGeral;
import model.Vendas;


/**
 * Classe respons�vel por implementar os m�todos respons�veis por cadastrar/editar/excluir objetos do tipo
 * Cardapio.
 * 
 * @author Caleo Silva e Jo�o Pedro.
 *
 */
public class GerenciadorDeCardapio{
	private List<Cardapio> listaDeCardapio = Dados.getListaCardapio();
	
	
	/**
	 * M�todo respons�vel por editar as informacoes de um item do cardapio j� registrado no sistema.
	 * 
	 * @param cardapio Objeto referente ao item a ser editado.
	 * @param dados Informacoes para serem atualizadas no item do cardapio.
	 * 
	 * @return true se a edicao ocorrer com sucesso, false caso contr�rio.
	 */
	public boolean editarCardapio(Cardapio cardapio, HashMap<String, Object> dados) {
		
		boolean sucesso = false;
		
		// Verificando se os dados s�o null
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

	/**
	 * M�todo respons�vel por cadastrar um novo item do cardapio no sistema.
	 * 
	 * @param dados Informacoes para serem registradas no novo item do cardapio.
	 * 
	 * @return true se o cadastro ocorrer com sucesso, false caso contr�rio.
	 */
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

	/**
	 * M�todo respons�vel por excluir um objeto do tipo Cardapio (um item do cardapio) existente no sistema.
	 * 
	 * @param cardapio Objeto referente ao item a ser editado.
	 * 
	 * @return true se a exclus�o ocorrer com sucesso, false caso contr�rio.
	 */
	public boolean excluirCardapio(Cardapio cardapio) {
		boolean excluido = false;
		if(cardapio != null) {
			excluido = listaDeCardapio.remove(cardapio);
		}
		return excluido;
		
	}
	
	/**
	 * M�todo respons�vel por buscar um Item no cardapio atrav�s de um ID informado pelo usuario.
	 * 
	 * @param listaDeCardapio Um ArraysList que cont�m todos os Itens cadastrados no cardapio.
	 * @param id ID de um item presente no cardapio.
	 * 
	 * @return Endere�o de mem�ria do item do cardapio se for encontrado, caso contr�rio retorna null.
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
	 * M�todo respons�vel por conferir se os itens que comp�em o prato digitado pelo usuario
	 * est�o cadastrado na lista de produtos.
	 * 
	 * @param listaDeProdutos Um ArraysList que cont�m todos os Produtos cadastrados.
	 * @param itensCardapio String com o nome de um produto.
	 * 
	 * @return True caso os itens estejam cadastrados, caso contr�rio retorna false.
	 */
	public boolean conferirItens(List<ProdutoGeral> listaDeProdutos, String itemCardapio) {
		for (ProdutoGeral produtoAtual : listaDeProdutos) {
			if (itemCardapio.equals(produtoAtual.getNome().toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
}
