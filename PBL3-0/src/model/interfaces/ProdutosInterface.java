package model.interfaces;

import java.util.HashMap;

import java.util.List;

import javafx.scene.control.TableView;
import model.Fornecedor;
import model.ProdutoEspecifico;
import model.ProdutoGeral;

/**
 * Interface que cont�m a assinatura dos m�todos respons�veis por manipular objetos do tipo Produto.
 * 
 * @author Caleo Silva e Jo�o Pedro.
 *
 */
public interface ProdutosInterface {
	
	/**
	 * M�todo respons�vel por editar as informa��es de um produto j� registrado no sistema.
	 * 
	 * @param novaInformacao Nova informa��o que ser� utilizada para substituir a anterior.
	 * @param opcaoEdicao N�mero referente a informa��o que ser� editada.
	 * @param novoProduto Endere�o de mem�ria do objeto Fornecedor que ser� editado.
	 * 
	 * @return true se a edi��o ocorrer com sucesso, false caso contr�rio.
	 */
	public boolean editarProdutos(HashMap<String, Object> dadosProduto, HashMap<String, Object> novaInformacao);
	
	/**
	 * M�todo respons�vel por cadastrar um novo produto no sistema.
	 * 
	 * @param listaDeProdutos Um ArraysList que cont�m todos os produtos cadastrados.
	 * @param listaDados ArrayList contento as informa��es do produto (Nome, Pre�o e Validade).
	 * 
	 * @return true se o cadastro ocorrer com sucesso, false caso contr�rio.
	 */
	public boolean cadastrarProdutos(List<ProdutoGeral> listaDeProdutos, List<Fornecedor> listaDeFornecedores, HashMap<String, Object> listaDados);
	
	/**
	 * M�todo respons�vel por excluir um objeto do tipo Produto existente no sistema.
	 * 
	 * @param listaDeProdutos Um ArraysList que cont�m todos aos produtos cadastrados.
	 * @param idExcluir String que contem o ID do objeto que ser� exclu�do.
	 * 
	 * @return true se a exclus�o ocorrer com sucesso, false caso contr�rio.
	 */
	public boolean excluirProdutos(List<ProdutoGeral> listaDeProdutos,  ProdutoEspecifico produto, TableView<ProdutoEspecifico> tabelaInformacoes);
}
