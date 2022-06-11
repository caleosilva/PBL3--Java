package model.concreto;

import java.util.HashMap;

import java.util.List;
import java.util.NoSuchElementException;

import alertas.AlertasGerais;
import bancoDeDados.Dados;
import javafx.scene.control.TableView;
import model.Fornecedor;
import model.ProdutoEspecifico;
import model.ProdutoGeral;

/**
 * Classe responsável por implementar os métodos responsáveis por cadastrar/editar/excluir objetos do tipo
 * Produtos.
 * 
 * @author Caleo Silva e João Pedro.
 *
 */
public class GerenciadorDeProduto {
	
	private AlertasGerais alertas = new AlertasGerais();
	private List<ProdutoGeral> listaDeProdutos = Dados.getListaProdutosGeral();
	private List<Fornecedor> listaDeFornecedores = Dados.getListaFornecedor();
		
	public boolean excluirProdutos(ProdutoEspecifico produto, TableView<ProdutoEspecifico> tabelaInformacoes) {
		try {
			for (ProdutoGeral pg : this.listaDeProdutos){
				for (ProdutoEspecifico pe : pg.getListaDeProdutos()) {
					if (pe.equals(produto)) {
						tabelaInformacoes.getItems().remove(produto);
						return pg.getListaDeProdutos().remove(produto);
					}
				}
			}
		} catch(NullPointerException npe) {
			alertas.erroNaOperacao();
		}
		
		return false;
	}
	
	
	public boolean cadastrarProdutos(HashMap<String, Object> listaDados) {
		
		// Gerando o Id:
		String id;
		boolean exclusivo = false;		
		do {
			id = GerenciadorDeId.gerarId(4);
			
			HashMap<String, Object> dados = null;
			
			try {
				dados = encontrarProduto(listaDeProdutos, id);
			} catch(NullPointerException npe) {
				//ViewMetodosGerais.mensagemErroGeral();
			}
						
			if (dados == null) exclusivo = true;
			
		} while (!exclusivo);
		
		System.out.printf("Id do produto: %s\n", id);
		
		boolean confirmacao = false;
		
		// Verifica se o nome dele já tá na lista de produto:
		try {
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
					confirmacao = lista.add(pe);
				}
			}
			
			// Se não tiver:			
			if (!achou) {
								
				ProdutoGeral pg = new ProdutoGeral((String) listaDados.get("nome"));
				listaDeProdutos.add(pg);
				
				
				ProdutoEspecifico pe = new ProdutoEspecifico((double) listaDados.get("preco"), id,
						(String) listaDados.get("validade"), (double) listaDados.get("quantidade"),
						(int) listaDados.get("unidadeDeMedida"), (Fornecedor) listaDados.get("fornecedor"));
								
				List<ProdutoEspecifico> lista = pg.getListaDeProdutos();
				confirmacao = lista.add(pe);
			}
			
			// Adicionando o nome do produto na lista do fornecedor em específico:
			
			Fornecedor f = (Fornecedor) listaDados.get("fornecedor");
			List<String> listaProdutoPorFornecedor = f.getListaNomeProdutos();
			
			// Se a lista não estiver vazia:
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
			
		} catch(NullPointerException npe) {
			//ViewMetodosGerais.mensagemErroGeral();
		} catch(ClassCastException cce) {
			//ViewMetodosGerais.mensagemErroGeral();
		}
		
		return confirmacao;
		
	}
	
	public static HashMap<String, Object> encontrarProduto(List<ProdutoGeral> listaDeProdutos, String id) {
		
		try {
			boolean achou = false;
			HashMap<String, Object> dadosProduto = new HashMap<>();
			
			// Itera sobre a lista que possui ProdutoGeral
			for(int i = 0; i < listaDeProdutos.size(); i++){
				
				ProdutoGeral proGeral = listaDeProdutos.get(i);
				
				// Itera sobre a lista dentro do ProdutoGeral que possui ProdutoEspecifico
		        for (int j = 0; j < proGeral.getListaDeProdutos().size(); j++) {
		        	
		        	ProdutoEspecifico proEspecifico = proGeral.getListaDeProdutos().get(j);
		        	
		        	// Verifica se o Id do ProdutoEspecifico é encontrado
		        	if (proEspecifico.getId().equals(id)) {
		        		
		        		dadosProduto.put("produtoGeral", proGeral);
		        		dadosProduto.put("produtoEspecifico", proEspecifico);
		        		
		        		achou = true;
		        		return dadosProduto;
		        	}
		        }
			}
			
			if (!achou) {
				//ViewMetodosGerais.mensagemNaoEncontrada();
			}
		} catch(NullPointerException npe) {
			//ViewMetodosGerais.mensagemErroGeral();
		} catch (NoSuchElementException nsee) {
			//ViewMetodosGerais.mensagemErroGeral();
		}
		
		return null;
	}
	
	public boolean editarProdutos(HashMap<String, Object> dadosProduto, HashMap<String, Object> novaInformacao) {
		
		boolean confirmacao = false;
		
		try {
			
			ProdutoEspecifico pe = (ProdutoEspecifico) dadosProduto.get("produtoEspecifico");
			
			if (novaInformacao.containsKey("nome")) {
				ProdutoGeral pg = (ProdutoGeral) dadosProduto.get("produtoGeral");
				pg.setNome( (String) novaInformacao.get("nome"));
				confirmacao = true;
				
			} else if (novaInformacao.containsKey("preco")) {
				pe.setPreco((double) novaInformacao.get("preco"));
				confirmacao = true;
				
			} else if (novaInformacao.containsKey("validade")) {
				pe.setValidade((String) novaInformacao.get("validade"));
				confirmacao = true;
				
			} else if (novaInformacao.containsKey("quantidade")) {
				pe.setQuantidade((double) novaInformacao.get("quantidade"));
				confirmacao = true;
				
			} else if (novaInformacao.containsKey("unidadeDeMedida")) {
				pe.setUnidadeDeMedida((int) novaInformacao.get("unidadeDeMedida"));
				confirmacao = true;
				
			} else if (novaInformacao.containsKey("fornecedor")) {
				pe.setFornecedor((Fornecedor) novaInformacao.get("fornecedor"));
				confirmacao = true;
			}
			
		} catch(NoSuchElementException nsee) {
			//ViewMetodosGerais.mensagemErroGeral();
	    } catch(NullPointerException npe) {
	    	//ViewMetodosGerais.mensagemErroGeral();
	    }
		
		return confirmacao;
	}
	
}
