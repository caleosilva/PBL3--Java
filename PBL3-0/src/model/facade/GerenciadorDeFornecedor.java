package model.facade;

import java.util.HashMap;

import java.util.List;

import alertas.AlertasGerais;
import bancoDeDados.Dados;
import model.Fornecedor;

/**
 * Classe respons�vel por implementar os metodos responsaveis por cadastrar/editar/excluir objetos do tipo
 * Fornecedor.
 * 
 * @author Caleo Silva e Joao Pedro.
 *
 */
public class GerenciadorDeFornecedor{
	
	private List<Fornecedor> listaDeFornecedor = Dados.getListaFornecedor();
	
	/**
	 * Metodo responsavel por buscar um fornecedor atraves de um ID informado.
	 * 
	 * @param listaDeFornecedor Um ArraysList que contem todos os Fornecedores cadastrados.
	 * @param id ID de um fornecedor.
	 * 
	 * @return Endereco de memoria do fornecedor se for encontrado, caso contrario retorna null.
	 */
	public static Fornecedor buscarFornecedorPeloID(List<Fornecedor> listaDeFornecedor, String id) {
		
		for (int i = 0; i < listaDeFornecedor.size(); i++) {
			
			Fornecedor fornecedor = listaDeFornecedor.get(i);
			
			if (fornecedor.getId().equals(id)) {
				return fornecedor;
			}
		}
		return null;
		
	}
	
	/**
	 * Metodo responsavel por editar as informacoes de um fornecedor.
	 * 
	 * @param fornecedor Objeto do tipo fornecedor que sera editado.
	 * @param dados HashMap contendo as novas informacoes.
	 * @return true caso seja editado ou false caso nao.
	 */
	public boolean editarFornecedor(Fornecedor fornecedor, HashMap<String, Object> dados) {
		
		boolean sucesso = false;
		
		// Verificando se os dados sao null
		if(dados == null && fornecedor != null) {
			return false;
		} else {
			String nome = String.valueOf(dados.get("nome"));
			String endereco = String.valueOf(dados.get("endereco"));
			String cnpj = String.valueOf(dados.get("cnpj"));
			List<String> lista = (List<String>) dados.get("produtos");
			
			fornecedor.setCnpj(cnpj);
			fornecedor.setNome(nome);
			fornecedor.setEndereco(endereco);
			fornecedor.setListaNomeProdutos(lista);
			sucesso = true;
		}		
		return sucesso;
	}
		
	/**
	 * Metodo responsavel por excluir um fornecedor.
	 * @param fornecedor Objeto do tipo fornecedor que sera excluido.
	 * @return true caso exclua ou false caso nao.
	 */
	public boolean excluirFornecedor(Fornecedor fornecedor) {
		boolean excluido = false;
		if(fornecedor != null) {
			excluido = listaDeFornecedor.remove(fornecedor);
		}
		return excluido;
	}
	
	/**
	 * Classe responsavel por cadastrar um novo produto no sistema.
	 * @param dados HashMap contendo as informacoes do novo produto a ser cadastrado.
	 * @return true caso seja cadastrado ou false caso nao.
	 */
	public boolean cadastrarFornecedor(HashMap<String, Object> dados) {
		
		boolean sucesso = false;
		//Criando um id exclusivo:
		String id;
		int exclusivo = 0;
		do {
			id = GerenciadorDeId.gerarId(2);
			Fornecedor f = GerenciadorDeFornecedor.buscarFornecedorPeloID(listaDeFornecedor, id);
			if (f == null) exclusivo = 1;
		} while (exclusivo == 0);
		
		// Convertendo os valores:
		String nome = String.valueOf(dados.get("nome"));
		String cnpj = String.valueOf(dados.get("cnpj"));
		String endereco = String.valueOf(dados.get("endereco"));
		List<String> x = (List<String>) dados.get("produtos");
		
		// Criando o novo objeto do tipo fornecedor:
		Fornecedor novoFornecedor = new Fornecedor(id, cnpj, nome, endereco);
		for (String produto: x) {
			novoFornecedor.getListaNomeProdutos().add(produto);
		}
		//Adicionar na lista
		sucesso = listaDeFornecedor.add(novoFornecedor);
		
		return sucesso;	
	}
}
