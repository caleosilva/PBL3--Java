package model.facade;

import java.util.HashMap;

import java.util.List;

import bancoDeDados.Dados;
import model.Fornecedor;

/**
 * Classe responsável por implementar os métodos responsáveis por cadastrar/editar/excluir objetos do tipo
 * Fornecedor.
 * 
 * @author Caleo Silva e João Pedro.
 *
 */
public class GerenciadorDeFornecedor{
	
	private List<Fornecedor> listaDeFornecedor = Dados.getListaFornecedor();
	
	/**
	 * Método responsável por buscar um fornecedor através de um ID informado.
	 * 
	 * @param listaDeFornecedor Um ArraysList que contém todos os Fornecedores cadastrados.
	 * @param id ID de um fornecedor.
	 * 
	 * @return Endereço de memória do fornecedor se for encontrado, caso contrário retorna null.
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
		
		// Verificando se o HashMap é null
		if(dados == null) {
			return false;
		}
		
		if (dados.containsKey("cnpj") && dados.get("cnpj") != null) {
			fornecedor.setCnpj(dados.get("cnpj"));
//    		ViewGerenciamentoFornecedor.mensagemConfirmandoMudanca();
    		return true;
    		
		} else if (dados.containsKey("nome" )  && dados.get("nome") != null) {
			fornecedor.setNome(dados.get("nome"));
//    		ViewGerenciamentoFornecedor.mensagemConfirmandoMudanca();
    		return true;
    		
		} else if (dados.containsKey("endereco") && dados.get("endereco") != null ) {
			fornecedor.setEndereco(dados.get("endereco"));
//    		ViewGerenciamentoFornecedor.mensagemConfirmandoMudanca();
    		return true;
    		
		}
		
		return false;
	}
		
	
	public boolean excluirFornecedor(Fornecedor fornecedor) {
		boolean excluido = false;
		if(fornecedor != null) {
			excluido = listaDeFornecedor.remove(fornecedor);
		}
		return excluido;
	}
	
	
	public boolean cadastrarFornecedor(List<Fornecedor> listaDeFornecedor, HashMap<String, String> dados) {
		
		if (dados.get("cnpj") == null || dados.get("nome") == null ||  dados.get("endereco") == null) {
			return false;
		}
		
		
		boolean sucesso = false;
	
		//Criando um id exclusivo:
		String id;
		int exclusivo = 0;
		
		do {
			id = GerenciadorDeId.gerarId(2);
			Fornecedor f = GerenciadorDeFornecedor.buscarFornecedorPeloID(listaDeFornecedor, id);
			
			if (f == null) exclusivo = 1;
			
		} while (exclusivo == 0);
		 
		
		// Criar o novo objeto
		Fornecedor novoFornecedor = new Fornecedor(id, dados.get("cnpj"), dados.get("nome"), dados.get("endereco"));
		
		//Adicionar na lista
		sucesso = listaDeFornecedor.add(novoFornecedor);
		
		// Verificando se foi adicionado com sucesso
		if (sucesso == true) {
//			ViewGerenciamentoFornecedor.mensagemFornecedorCadastrado();
		} else {
//			ViewGerenciamentoFornecedor.mensagemFornecedorNaoCadastrado();
		}
		
		return sucesso;	
	}
}
