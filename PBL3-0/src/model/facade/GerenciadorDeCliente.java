package model.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bancoDeDados.Dados;
import model.Cardapio;
import model.Cliente;

/**
 * Classe respons�vel por implementar os m�todos respons�veis por cadastrar/excluir objetos do tipo
 * Cliente.
 * 
 * @author Caleo Silva e Jo�o Pedro.
 *
 */
public class GerenciadorDeCliente {
	private List<Cliente> listaDeClientes = Dados.getListaCliente();
	
	/**
	 * M�todo respons�vel por cadastrar um novo Cliente no sistema.
	 * 
	 * @param dados Informacoes para serem registradas no novo cliente.
	 * 
	 * @return true se o cadastro ocorrer com sucesso, false caso contr�rio.
	 */
	public boolean cadastrarCliente(HashMap<String, String> dados) {
		
		boolean sucesso = false;
		//Gerando id
		String id;
		int exclusivo = 0;
		//String precoItem = Double.toString(valor);
		do {
			id = GerenciadorDeId.gerarId(3);
			Cliente c = GerenciadorDeCliente.encontrarCliente(listaDeClientes, id);
			
			if (c == null) exclusivo = 1;
			
		} while (exclusivo == 0);
		
		String nome = String.valueOf(dados.get("nome"));
		String cpf = String.valueOf(dados.get("cpf"));
		String email = String.valueOf(dados.get("email"));
		String telefone = String.valueOf(dados.get("telefone"));
		
		
		Cliente novoItem = new Cliente(id, nome,cpf, email, telefone);
		
		
		
		boolean confirmacao = listaDeClientes.add(novoItem);
		return confirmacao;
		
	}
	/**
	 * M�todo respons�vel por buscar um Cliente atrav�s de um ID informado pelo usuario.
	 * 
	 * @param listaDeClientes Um ArraysList que cont�m todos os clientes cadastrados.
	 * @param id ID de um item presente no cardapio.
	 * 
	 * @return Endere�o de mem�ria do item do cardapio se for encontrado, caso contr�rio retorna null.
	 */
	public static Cliente encontrarCliente(List<Cliente> listaDeClientes, String id) {
		
		for(int i = 0; i < listaDeClientes.size(); i++){
			
			Cliente confereCliente = listaDeClientes.get(i);
	        if(confereCliente.getId().equals(id)){
	        	return confereCliente;
	        }
		}
		
		return null;
	}
	
	/**
	 * M�todo respons�vel por excluir um objeto do tipo Cliente existente no sistema.
	 * 
	 * @param cliente Objeto referente ao cliente a ser editado.
	 * 
	 * @return true se a exclus�o ocorrer com sucesso, false caso contr�rio.
	 */
	public boolean excluirCliente(Cliente cliente) {
		boolean excluido = false;
		if(cliente != null) {
			excluido = listaDeClientes.remove(cliente);
		}
		return excluido;
		
	}
	
}
