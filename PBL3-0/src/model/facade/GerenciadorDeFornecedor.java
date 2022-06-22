package model.facade;

import java.util.HashMap;

import java.util.List;

import bancoDeDados.Dados;
import model.Fornecedor;

/**
 * Classe respons�vel por implementar os m�todos respons�veis por cadastrar/editar/excluir objetos do tipo
 * Fornecedor.
 * 
 * @author Caleo Silva e Jo�o Pedro.
 *
 */
public class GerenciadorDeFornecedor{
	
	private List<Fornecedor> listaDeFornecedor = Dados.getListaFornecedor();
	
	/**
	 * M�todo respons�vel por buscar um fornecedor atrav�s de um ID informado.
	 * 
	 * @param listaDeFornecedor Um ArraysList que cont�m todos os Fornecedores cadastrados.
	 * @param id ID de um fornecedor.
	 * 
	 * @return Endere�o de mem�ria do fornecedor se for encontrado, caso contr�rio retorna null.
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
	
	
	public boolean editarFornecedor(Fornecedor fornecedor, HashMap<String, String> dados) {
		
		// Verificando se o HashMap � null
		if(dados == null) {
			return false;
		}
		
		//TODO
		
		return false;
	}
		
	
	public boolean excluirFornecedor(Fornecedor fornecedor) {
		boolean excluido = false;
		if(fornecedor != null) {
			excluido = listaDeFornecedor.remove(fornecedor);
		}
		return excluido;
	}
	
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
