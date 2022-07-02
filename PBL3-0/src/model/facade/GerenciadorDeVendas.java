package model.facade;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import bancoDeDados.Dados;
import model.Cardapio;
import model.ProdutoGeral;
import model.Vendas;

/**
 * Classe respons�vel por implementar os m�todos respons�veis por cadastrar/editar/excluir objetos do tipo
 * Vendas.
 * 
 * @author Caleo Silva e Jo�o Pedro.
 *
 */
public class GerenciadorDeVendas {
	
	private List<Vendas> listaDeVendas = Dados.getListaVendas();
	public boolean editarVendas(Vendas vendas, HashMap<String, Object> dados) {
		boolean sucesso = false;
		
		// Verificando se os dados s�o null
		if(dados == null && vendas != null) {
			return false;
		} else {
			
			String modoPagamento = String.valueOf(dados.get("modoPagamento"));
			String preco = String.valueOf(dados.get("preco"));
			String cliente = String.valueOf(dados.get("cliente"));
			String data =String.valueOf(dados.get("data"));
			String horario =String.valueOf(dados.get("horario"));
			HashMap<String, Double> itensVendas = (HashMap<String, Double>) dados.get("pratos");
			
			
			vendas.setClienteVinculado(cliente);
			vendas.setData(data);
			vendas.setHorario(horario);
			vendas.setItens(itensVendas);
			vendas.setModoPagamento(modoPagamento);
			vendas.setPrecoTotal(preco);
			return true;
		}
		
	}
	
	
	public boolean excluirVendas(Vendas venda) {
		boolean excluido = false;
		if(venda != null) {
			excluido = listaDeVendas.remove(venda);
		}
		return excluido;
	}
	
	
	public boolean cadastrarVendas(HashMap<String, Object> dados) {
		//Pegando data
		LocalDate dataAtual = LocalDate.now();//For reference
		DateTimeFormatter formatoPadraoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = dataAtual.format(formatoPadraoData);
		
		//Pegando horario
		LocalTime horaAtual = LocalTime.now();
		DateTimeFormatter foramatoPadraoHoras = DateTimeFormatter.ofPattern("hh:mm");
		String novaHora = horaAtual.format(foramatoPadraoHoras);
		
		//Gerando id
		String id;
		int exclusivo = 0;
		
		do {
			id = GerenciadorDeId.gerarId(5);
			Vendas v = GerenciadorDeVendas.encontrarVendas(listaDeVendas, id);
			
			if (v == null) exclusivo = 1;
			
		} while (exclusivo == 0);
		
		String modoPagamento = String.valueOf(dados.get("modoPagamento"));
		String preco = String.valueOf(dados.get("preco"));
		String cliente = String.valueOf(dados.get("cliente"));
		HashMap<String, Double> pratos = (HashMap<String, Double>) dados.get("pratos");
		
		
		Vendas novaVenda = new Vendas(id, dataFormatada, novaHora, pratos, preco, modoPagamento, cliente);
		boolean adicionou = listaDeVendas.add(novaVenda);
		return adicionou;
	}
	
	/**
	 * M�todo respons�vel por conferir se os itens que comp�em a venda digitada pelo usuario
	 * est�o cadastrado na lista do cardapio.
	 * 
	 * @param listaDeCardapio Um ArraysList que cont�m todos os itens cadastrados no cardapio.
	 * @param itensVendas um HashMap com os pratos do cardapio digitados pelo usuario.
	 * 
	 * @return caso os itens estejam cadastrados � retornado o valor total da compra, caso contr�rio � retornado -1.
	 */
	public double conferirVenda(List<Cardapio> listaDeCardapio, HashMap<String, Double> itensVendas) {
		
		int quantidadeItens = itensVendas.size();
		int contadorPratos = 0;
		double precoTotal = 0;
		String guardarValor;
		
		for (String itemAtual : itensVendas.keySet()) {
			for (Cardapio produtoAtual : listaDeCardapio) {
				if (itemAtual.equals(produtoAtual.getNome().toLowerCase())) {
					//contagem para verificar e soma do valor da venda
					contadorPratos += 1;
					guardarValor = produtoAtual.getPreco().replaceAll(",", ".");
					precoTotal += Double.parseDouble(guardarValor) * itensVendas.get(itemAtual);
				}
			}
		}
		
		if (contadorPratos >= quantidadeItens) {
			return precoTotal;}
		else {
			
			return -1;}
	}
	
	/**
	 * M�todo respons�vel por buscar uma venda no hist�rico atrav�s de um ID informado pelo usuario.
	 * 
	 * @param listaDeVendas Um ArraysList que cont�m todos as vendas registradas.
	 * @param id ID de uma venda registrada na lista de vendas.
	 * 
	 * @return Endere�o de mem�ria do item do cardapio se for encontrado, caso contr�rio retorna null.
	 */
	public static Vendas encontrarVendas(List<Vendas> listaDeVendas, String id) {
		
		for(int i = 0; i < listaDeVendas.size(); i++){
			
			Vendas confereVendas = listaDeVendas.get(i);
	        if(confereVendas.getId().equals(id)){
	        	return confereVendas;
	        }
		}
		
		return null;
	}
	
	/**
	 * M�todo respons�vel por somar todos os produtos necessarios para que o prato seja vendido.
	 * 
	 * @param listaDeVendas Um ArraysList que cont�m todos as vendas registradas.
	 * @param listaDeCardapio Um ArraysList que cont�m todos os pratos do Cardapio registrados..
	 * 
	 * @return Endere�o de mem�ria do item do cardapio se for encontrado, caso contr�rio retorna null.
	 */
	public HashMap<String, Double> somarProdutos(List<Cardapio> listaDeCardapio, HashMap<String, Double> itensVendas ){
		
		//HashMap para guardar os produtos e suas quantidades
		HashMap<String, Double> produtosParaVenda = new HashMap<String, Double>();
		double quant = 0;
		for (String itemAtual : itensVendas.keySet()) {
			for (Cardapio produtoAtual : listaDeCardapio) {
				for (String keyAtual : produtoAtual.getItensCardapio().keySet()) {
					if (itemAtual.equals(produtoAtual.getNome().toLowerCase())) {
						quant = produtoAtual.getItensCardapio().get(keyAtual) * itensVendas.get(itemAtual);
						double controle = 0;
						if (produtosParaVenda.containsKey(keyAtual)) {
							controle = produtosParaVenda.get(keyAtual);
							produtosParaVenda.remove(keyAtual);
							quant += controle;
							}
						
						produtosParaVenda.put(keyAtual, quant);
					}
				}
			}	
		}
		return produtosParaVenda;
	}
	
	/**
	 * M�todo respons�vel por remover os itens vendidos da lista de produtos.
	 * 
	 * @param listaDeProdutos Um ArraysList que cont�m todos os Produtos.
	 * @param produtosParaVenda HashMap que contem a quantidade total de produtos a ser removida.
	 * 
	 * @return True caso a remo��o seja feita com sucesso e False caso n�o ocorra a remo��o
	 */
	public boolean removerDoEstoque(List<ProdutoGeral> listaDeProdutos, HashMap<String, Double> produtosParaVenda) {
		//verifica��o se no estoque tem quantidade suficiente de produtos
		//Percorre os itens do prato no HashMap
		for (String itemAtual : produtosParaVenda.keySet()) {
			//Percorre os produtos na lista de produto
			for (ProdutoGeral produtoAtual : listaDeProdutos) {
				//Verifica se o item em especifo corresponde a algum produto registrado
				if (itemAtual.equals(produtoAtual.getNome().toLowerCase())) {
					
					//Pega o tamanho da lista interna dos produtos (Lista dos produtos especificos dentro do produto geral)
					int tamanhoLista = produtoAtual.getListaDeProdutos().size();
					//Variavel para armazenar a soma da quantidade dos produtos especificos
					double quantTotalEspecifica = 0;
					//La�o para percorrer a lista interna dos produtos
					for (int i = 0; i < tamanhoLista ; i += 1) {
						//Somatoria da quantidade total dos itens internos
						quantTotalEspecifica += produtoAtual.getListaDeProdutos().get(i).getQuantidade();
					}
					//Verifica se a quantidade total do produto satisfaz a necessidade do prato
					if (quantTotalEspecifica < produtosParaVenda.get(itemAtual)) {
						//Retorna false caso algum dos itens necessarios para os pratos da venda n�o satisfa�am a necessidade
						return false;
					}
				}	
			}
		}
		//Caso existam produtos suficientes no estoque, eles seram removidos para que a venda seja efetuada
		//OBS: O trecho do c�digo se repete pois antes de remover qualquer quantidade de um produto � necessario que o programa passe anteriormente pela verifica��o
		//Percorre os itens do prato no HashMap
		for (String itemAtual : produtosParaVenda.keySet()) {
			//Percorre os produtos na lista de produto
			for (ProdutoGeral produtoAtual : listaDeProdutos) {
				//Verifica se o item em especifo corresponde a algum produto registrado
				if (itemAtual.equals(produtoAtual.getNome().toLowerCase())) {
					//Pega o tamanho da lista interna dos produtos (Lista dos produtos especificos dentro do produto geral)
					int tamanhoLista = produtoAtual.getListaDeProdutos().size();
					double quantItemVenda = produtosParaVenda.get(itemAtual);
					//La�o para percorrer a lista interna dos produtos
					for (int i = 0; i < tamanhoLista ; i++) {
						//Variavel que guarda a quantidade de produtos especificos para a remo��o
						double quantTotalEspecifica = produtoAtual.getListaDeProdutos().get(i).getQuantidade();
						//Caso o produto especifico seja suficiente para a remo��o a sua quantidade ser� imediatamente alterado no estoque
						if (quantTotalEspecifica >= quantItemVenda) {
							produtoAtual.getListaDeProdutos().get(i).setQuantidade(quantTotalEspecifica - quantItemVenda);
							quantItemVenda = 0;
							break;
						}
						//Caso n�o seja, a sua quantidade ser� alterada no estoque e o la�o continuar� percorrendo e removendo a quantidade restante dos outros produtos especificos
						else {
							if (quantItemVenda != 0) {
								double result = quantTotalEspecifica -  quantItemVenda;
								produtoAtual.getListaDeProdutos().get(i).setQuantidade(0);
								quantItemVenda = result * (-1);
							}
						}
					}
					
				}
			}
		}
		return true;
	}
	
}
