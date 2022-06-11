package model.interfaces;

import java.util.HashMap;

import java.util.List;

import javafx.scene.control.TableView;
import model.Fornecedor;
import model.ProdutoEspecifico;
import model.ProdutoGeral;

/**
 * Interface que contém a assinatura dos métodos responsáveis por manipular objetos do tipo Produto.
 * 
 * @author Caleo Silva e João Pedro.
 *
 */
public interface ProdutosInterface {
	
	/**
	 * Método responsável por editar as informações de um produto já registrado no sistema.
	 * 
	 * @param novaInformacao Nova informação que será utilizada para substituir a anterior.
	 * @param opcaoEdicao Número referente a informação que será editada.
	 * @param novoProduto Endereço de memória do objeto Fornecedor que será editado.
	 * 
	 * @return true se a edição ocorrer com sucesso, false caso contrário.
	 */
	public boolean editarProdutos(HashMap<String, Object> dadosProduto, HashMap<String, Object> novaInformacao);
	
	/**
	 * Método responsável por cadastrar um novo produto no sistema.
	 * 
	 * @param listaDeProdutos Um ArraysList que contém todos os produtos cadastrados.
	 * @param listaDados ArrayList contento as informações do produto (Nome, Preço e Validade).
	 * 
	 * @return true se o cadastro ocorrer com sucesso, false caso contrário.
	 */
	public boolean cadastrarProdutos(List<ProdutoGeral> listaDeProdutos, List<Fornecedor> listaDeFornecedores, HashMap<String, Object> listaDados);
	
	/**
	 * Método responsável por excluir um objeto do tipo Produto existente no sistema.
	 * 
	 * @param listaDeProdutos Um ArraysList que contém todos aos produtos cadastrados.
	 * @param idExcluir String que contem o ID do objeto que será excluído.
	 * 
	 * @return true se a exclusão ocorrer com sucesso, false caso contrário.
	 */
	public boolean excluirProdutos(List<ProdutoGeral> listaDeProdutos,  ProdutoEspecifico produto, TableView<ProdutoEspecifico> tabelaInformacoes);
}
