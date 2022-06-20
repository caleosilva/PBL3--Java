package model.facade;

import java.util.HashMap;

import java.util.List;

import bancoDeDados.Dados;
import model.Funcionario;
import model.Gerente;
import model.Usuario;

/**
 * Classe respons�vel por implementar os m�todos respons�veis por cadastrar/editar/excluir objetos do tipo
 * Usuario.
 *
 * @author Caleo Silva e Jo�o Pedro.
 */

public class GerenciadorDeUsuario{
	
	private static List<Usuario> listaDeUsuario = Dados.getListaUsuario();
	
	/**
	 * M�todo repons�vel por realizar a busca de um objeto do tipo Usuario em um ArrayList.
	 * 
	 * @param listaDeUsuario Um ArraysList que cont�m todos os usu�rios cadastrados.
	 * @param id Id do usu�rio que ser� buscado.
	 * @param exibirMsg Vari�vel de controle que � usada para exibir uma mensagem (true) ou n�o (false).
	 * 
	 * @return Retorna o endere�o de mem�ria do objeto, caso n�o seja encontrado retorna null.
	 */
	public static Usuario encontrarUsuarioPorId(String id, boolean exibirMsg) {

		int achou = 0;
		
		for(int i = 0; i < listaDeUsuario.size(); i++){
			Usuario user = listaDeUsuario.get(i);
			
	        if(user.getId().equals(id)){
	        	achou = 1;
	        	return user;
	        }
		}
		
		if(achou == 0 && exibirMsg == true) {
//			ViewGerenciamentoUsuario.mensagemUserNaoEncontrado();
		}
		
		return null;
	}
	
	
	public Usuario editarUsuario(Usuario usuario, String[] informacao) {
	    
		// Se a informa��o passada for null.
		if(informacao[1] == null) {
			return null;
		}
		
		if(informacao[0] == "0") {
//			ViewGerenciamentoUsuario.mensagemUserNaoEncontrado();
		
		// Editar login.
		} else if (informacao[0].equals("1")) {
			
			// Verifica se o login escolhido j� existe.
			int loginExistente = GerenciadorDeUsuario.encontrarUsuarioPorLogin(informacao[1]);
			
			if (loginExistente == 1) {
				return null;
			}
			
			usuario.setLogin(informacao[1]);
//			ViewMetodosGerais.mensagemConfirmandoMudanca();
    		
		// Editar senha.	
    	} else if (informacao[0].equals("2")) {
    		usuario.setSenha(informacao[1]);
//    		ViewMetodosGerais.mensagemConfirmandoMudanca();	
    	} 

		
		return usuario;
    }
	
	
	public boolean excluirUsuario(Usuario usuario) {
		
		boolean excluido = false;
		
		if(usuario != null) {
			excluido = listaDeUsuario.remove(usuario);
//			ViewGerenciamentoUsuario.mensagemExclusao(excluido);
		}
		
		return excluido;
		
	}
	
	/**
	 * M�todo respons�vel po buscar um objeto do tipo "Usuario" atrav�s do seu Login.
	 * 
	 * @param listaDeUsuario Um ArraysList que cont�m todos os usu�rios cadastrados.
	 * @param login Login que ser� utilizado para buscar o usu�rio.
	 * 
	 * @return 1 caso encontre, 0 caso n�o encontre.
	 */
	public static int encontrarUsuarioPorLogin(String login) {		
		for(int i = 0; i < listaDeUsuario.size(); i++){
			
	        Usuario user = listaDeUsuario.get(i);
	        
	        if(user.getLogin().equals(login)) {
//	        	ViewGerenciamentoUsuario.mensagemLoginJaExistente();
	        	return 1;
	        }
		}
		return 0;
	}

	
	public Usuario cadastrarUsuario(HashMap<String, String> dadosCadastro) {
		
		// Verificando se algum dado � null:
		if (dadosCadastro.get("login") == null || dadosCadastro.get("senha") == null  || dadosCadastro.get("funcao") == null ) {
			return null;
		}
		
		// Verificando se o LOGIN j� existe no sistema:
		int loginUsado = GerenciadorDeUsuario.encontrarUsuarioPorLogin(dadosCadastro.get("login"));
		if (loginUsado == 1) return null;
		
		
		// Gerando um ID exclusivo.
		int liberado = 0;
		String id = "0";
		
		do {
			id = GerenciadorDeId.gerarId(1);

			Usuario user = GerenciadorDeUsuario.encontrarUsuarioPorId(id, false);
			
			// Se n�o houver nenhum usu�rio com o ID gerado:
			if (user == null) liberado = 1;
			
		} while(liberado == 0);
		
		//APAGAR ISSO AQ DEPOIS
		System.out.println("PRINT TESTE - [CADASTRAR USUARIO] ID:" + id);

		//Cadastrando um gerente:
		if (dadosCadastro.get("funcao").equals("1")) {
			
			// Criando o objeto:
			Gerente novoGerente = new Gerente(id, dadosCadastro.get("login"), dadosCadastro.get("senha"));
			
			// Adicionando na lista:
			listaDeUsuario.add(novoGerente);
			
			// Mensagem de confirma��o:
//			ViewMetodosGerais.mensagemConfirmandoCadastro("Gerente");
			return novoGerente;
		
		//Cadastrando um funcion�rio:
		} else if (dadosCadastro.get("funcao").equals("2")) {
			
			// Criando o objeto:
			Funcionario novoFuncionario = new Funcionario(id, dadosCadastro.get("login"), dadosCadastro.get("senha"));
			
			// Adicionando na lista:
			listaDeUsuario.add(novoFuncionario);
			
			// Mensagem de confirma��o:
//			ViewMetodosGerais.mensagemConfirmandoCadastro("Funcionario");
			return novoFuncionario;

		} else {
//			ViewGerenciamentoUsuario.mensagemTipoDeUsuarioInvalido();
			return null;
		}
		
	}
}
