package model.facade;

import java.util.HashMap;

import java.util.List;
import java.util.NoSuchElementException;

import bancoDeDados.Dados;
import model.Fornecedor;
import model.ProdutoEspecifico;
import model.ProdutoGeral;

/**
 * Classe responsavel por implementar os metodos responsaveis por cadastrar/editar/excluir objetos do tipo
 * Produtos.
 * 
 * @author Caleo Silva e Joao Pedro.
 *
 */
public class GerenciadorDeProduto {
	
	private static List<ProdutoGeral> listaDeProdutos = Dados.getListaProdutosGeral();
	
	/**
	 * Classe responsavel por realizar a exclusao de um produto no sistema.
	 * @param produto Objeto do tipo ProdutoEspecifico que sera excluido.
	 * @return true caso seja realizada a exclusao ou false caso nao.
	 */
	public boolean excluirProdutos(ProdutoEspecifico produto){
		try {
			
			if(listaDeProdutos.size() == 0) {
				return false;
			}
			
			for (ProdutoGeral pg : listaDeProdutos){
				for (ProdutoEspecifico pe : pg.getListaDeProdutos()) {
					if (pe.equals(produto)) {
						return pg.getListaDeProdutos().remove(produto);
					}
				}
			}
			return false;
		} catch (IndexOutOfBoundsException e) {
			return false;
		} catch(NullPointerException e) {
			return false;
		}
	}
	
	/**
	 * Metodo responsavel por realizar o cadastro de um produto no sistema.
	 * @param listaDados HashMap contendo as informacoes do produto que sera cadastrado.
	 * @return true caso seja realizado cadastro ou false caso nao.
	 */
	public boolean cadastrarProdutos(HashMap<String, Object> listaDados) {
		
		boolean sucessoOperacao = false;
		
		// Gerando o Id:
		String id;
		boolean exclusivo = false;
		do {
			id = GerenciadorDeId.gerarId(4);
			HashMap<String, Object> dados = encontrarProduto(id);
			if (dados == null) exclusivo = true;
		} while (!exclusivo);
		
		// Verifica se o nome dele j� t� na lista de produto:
		boolean achou = false;
		for (int i = 0; i <listaDeProdutos.size(); i++) {
			ProdutoGeral pg = listaDeProdutos.get(i);
			
			// Se tiver:				
			if (pg.getNome().equals(listaDados.get("nome"))) {
				achou = true;
				ProdutoEspecifico pe = new ProdutoEspecifico((double) listaDados.get("preco"), id,
						(String) listaDados.get("validade"), (double) listaDados.get("quantidade"),
						(int) listaDados.get("unidadeDeMedida"), (Fornecedor) listaDados.get("fornecedor"));
				
				List<ProdutoEspecifico> lista = pg.getListaDeProdutos();
				sucessoOperacao = lista.add(pe);
			}
		}
		// Se n�o tiver:			
		if (!achou) {								
			ProdutoGeral pg = new ProdutoGeral((String) listaDados.get("nome"));
			listaDeProdutos.add(pg);
			
			ProdutoEspecifico pe = new ProdutoEspecifico((double) listaDados.get("preco"), id,
					(String) listaDados.get("validade"), (double) listaDados.get("quantidade"),
					(int) listaDados.get("unidadeDeMedida"), (Fornecedor) listaDados.get("fornecedor"));
							
			List<ProdutoEspecifico> lista = pg.getListaDeProdutos();
			sucessoOperacao = lista.add(pe);
		}
		
		// Adicionando o nome do produto na lista do fornecedor em espec�fico:
		
		Fornecedor f = (Fornecedor) listaDados.get("fornecedor");
		List<String> listaProdutoPorFornecedor = f.getListaNomeProdutos();
		
		// Se a lista n�o estiver vazia:
		if (listaProdutoPorFornecedor.size() > 0) {
			
			boolean jaCadastrado = false;
			
			for (int k = 0; k < listaProdutoPorFornecedor.size(); k++) {
				if(listaProdutoPorFornecedor.get(k).equals((String)listaDados.get("nome"))) {
					jaCadastrado = true;
					break;
				}
			}
			
			if (!jaCadastrado) {
				listaProdutoPorFornecedor.add((String)listaDados.get("nome"));
			}
			
		} else {
			listaProdutoPorFornecedor.add((String)listaDados.get("nome"));
		}
		
		return sucessoOperacao;
	}
	
	/**
	 * Metodo responsavel por buscar um produto atraves de um id.
	 * 
	 * @param id informacao que sera utilizada para buscar o produto.
	 * @return HashMap contento dois objeto: ProdutoGeral e ProdutoEspecifico.
	 */
	public HashMap<String, Object> encontrarProduto(String id) {
		
		try {
			HashMap<String, Object> dadosProduto = new HashMap<>();
			
			// Itera sobre a lista que possui ProdutoGeral
			for(int i = 0; i < listaDeProdutos.size(); i++){
				
				ProdutoGeral proGeral = listaDeProdutos.get(i);
				
				// Itera sobre a lista dentro do ProdutoGeral que possui ProdutoEspecifico
		        for (int j = 0; j < proGeral.getListaDeProdutos().size(); j++) {
		        	
		        	ProdutoEspecifico proEspecifico = proGeral.getListaDeProdutos().get(j);
		        	
		        	// Verifica se o Id do ProdutoEspecifico � encontrado
		        	if (proEspecifico.getId().equals(id)) {
		        		
		        		dadosProduto.put("produtoGeral", proGeral);
		        		dadosProduto.put("produtoEspecifico", proEspecifico);
		        		
		        		return dadosProduto;
		        	}
		        }
			}
		} catch(NullPointerException npe) {
			//ViewMetodosGerais.mensagemErroGeral();
		} catch (NoSuchElementException nsee) {
			//ViewMetodosGerais.mensagemErroGeral();
		}
		
		return null;
	}
	
	/**
	 * Metodo responsavel por editar as informacoes de um produto.
	 * 
	 * @param dadosProduto dados do produto que sera editado.
	 * @param novaInformacao novas informacoes do produto.
	 * @return true caso seja editado com sucesso ou false caso nao.
	 */
	public boolean editarProdutos(HashMap<String, Object> dadosProduto, HashMap<String, Object> novaInformacao) {
		
		boolean confirmacao = false;

		try {
			
			ProdutoEspecifico pe = (ProdutoEspecifico) dadosProduto.get("produtoEspecifico");
			ProdutoGeral pg = (ProdutoGeral) dadosProduto.get("produtoGeral");
			
			if(dadosProduto != null && novaInformacao != null) {
				
				// Removendo o produto da lista do seu antigo fornecedor
				pe.getFornecedor().getListaNomeProdutos().remove(pg.getNome());
				
				// Adicionando o novo fornecedor no produto:
				Fornecedor forn = (Fornecedor) novaInformacao.get("fornecedor");
				pe.setFornecedor(forn);
				
				String nome = (String) novaInformacao.get("nome");
				double preco = (double)novaInformacao.get("preco");
				String validade = (String) novaInformacao.get("validade");
				double quantidade = (double) novaInformacao.get("quantidade");
				int unidadeDeMedida =(int) novaInformacao.get("unidadeDeMedida");
				
				// Adicionando o produto na lista do novo fornecedor
				forn.getListaNomeProdutos().add(nome);
				
				// Adicionando as demais informacoes no produto
				pg.setNome(nome);
				pe.setNome(nome);
				pe.setPreco(preco);
				pe.setValidade(validade);
				pe.setQuantidade(quantidade);
				pe.setUnidadeDeMedida(unidadeDeMedida);
								
				confirmacao = true;
			}
			
		} catch(NoSuchElementException nsee) {
			return false;
	    } catch(NullPointerException npe) {
	    	return false;
    	} catch(ClassCastException cce) {
    		return false;
    	}
		return confirmacao;
	}
	
}
