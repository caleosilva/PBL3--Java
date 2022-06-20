package model.facade;

import java.util.HashMap;

import java.util.List;

import bancoDeDados.Dados;
import excecoes.ErroNaOperacao;
import excecoes.LoginExistente;
import javafx.fxml.LoadException;
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
			boolean loginExistente = encontrarUsuarioPorLogin(informacao[1]);
			
			if (loginExistente) {
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
	public boolean encontrarUsuarioPorLogin(String login) {
		
		for (Usuario user : listaDeUsuario) {
			if(user.getLogin().equals(login)) {
				return true;
	        }
		}
		return false;
	}

	
	public boolean cadastrarUsuario(HashMap<String, String> dadosCadastro) throws LoginExistente, ErroNaOperacao {
		
		// Verificando se o LOGIN j� existe no sistema:
		if (dadosCadastro == null) {
			System.out.println("Achei papai");
		}
		boolean loginUsado = encontrarUsuarioPorLogin(dadosCadastro.get("login"));
		if (loginUsado) throw new LoginExistente("Login j� presente no sistema!");
		
		// Gerando um ID exclusivo.
		int liberado = 0;
		String id = "0";
		do {
			id = GerenciadorDeId.gerarId(1);
			Usuario user = GerenciadorDeUsuario.encontrarUsuarioPorId(id, false);
			// Se n�o houver nenhum usu�rio com o ID gerado:
			if (user == null) liberado = 1;
		} while(liberado == 0);

		//Cadastrando um gerente:
		if (dadosCadastro.get("cargo").equals("Gerente")) {
			// Criando o objeto:
			Gerente novoGerente = new Gerente(id, dadosCadastro.get("login"), dadosCadastro.get("senha"), "Gerente");
			
			// Adicionando na lista:
			listaDeUsuario.add(novoGerente);
			return true;
		
		//Cadastrando um funcion�rio:
		} else if (dadosCadastro.get("cargo").equals("Funcionario")) {
			// Criando o objeto:
			Funcionario novoFuncionario = new Funcionario(id, dadosCadastro.get("login"), dadosCadastro.get("senha"), "Funcionario");
			
			// Adicionando na lista:
			listaDeUsuario.add(novoFuncionario);
			return true;

		} else {
			throw new ErroNaOperacao("Erro desconhecido!");
		}
		
	}
}
