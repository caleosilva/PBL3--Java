package model.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bancoDeDados.Dados;
import model.Cardapio;
import model.Cliente;

public class GerenciadorDeCliente {
	private List<Cliente> listaDeClientes = Dados.getListaCliente();
	
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
	
	public static Cliente encontrarCliente(List<Cliente> listaDeClientes, String id) {
		
		for(int i = 0; i < listaDeClientes.size(); i++){
			
			Cliente confereCliente = listaDeClientes.get(i);
	        if(confereCliente.getId().equals(id)){
	        	return confereCliente;
	        }
		}
		
		return null;
	}
	
	public boolean excluirCliente(Cliente cliente) {
		boolean excluido = false;
		if(cliente != null) {
			excluido = listaDeClientes.remove(cliente);
		}
		return excluido;
		
	}
	
}
