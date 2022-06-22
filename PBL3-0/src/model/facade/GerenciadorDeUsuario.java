package model.facade;

import java.util.HashMap;

import java.util.List;

import bancoDeDados.Dados;
import excecoes.ErroNaOperacao;
import excecoes.InformacoesInvalidas;
import excecoes.LoginExistente;
import excecoes.SenhaAnteriorIncorreta;
import excecoes.SenhasNovasNaoIguais;
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
	
	
	public boolean editarUsuario(Usuario usuario, HashMap<String, String> dadosHash) throws LoginExistente,
	SenhaAnteriorIncorreta, SenhasNovasNaoIguais {
		
		System.out.println("Entrei no editar usuario");
		
		boolean sucesso = false;
		// Se o login j� existir no sistema:
		if (!usuario.getLogin().equals(dadosHash.get("login")) && encontrarUsuarioPorLogin(dadosHash.get("login"))){
			System.out.println("Login j� existe no sistema\n");
			throw new LoginExistente("Login j� existe no sistema");
			
		//Caso contr�rio, a edi��o � permitida:
		} else {
			
			System.out.println("TO no else 1");
			// Confirma a senha anterior
			if(dadosHash.get("senhaAnterior").equals(usuario.getSenha())) {
				
				System.out.println("TO no if 1");
				
				if (dadosHash.get("novaSenha1").equals(dadosHash.get("novaSenha2"))) {
					
					System.out.println("Tudo certo, if 2\n");
					
					usuario.setLogin(dadosHash.get("login"));
					usuario.setSenha(dadosHash.get("novaSenha1"));
					sucesso = true;
				}else {
					System.out.println("Senhas novas incoerentes!, else 2\n");

					throw new SenhasNovasNaoIguais("Senhas novas incoerentes!");
				}
			} else {
				System.out.println("Senha anterior incorreta!, else 2");
				throw new SenhaAnteriorIncorreta("Senha anterior incorreta!");
			}
		}
		return sucesso;
    }
	
	
	public boolean excluirUsuario(Usuario usuario) {
		boolean excluido = false;
		if(usuario != null) {
			excluido = listaDeUsuario.remove(usuario);
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

	
	public boolean cadastrarUsuario(HashMap<String, String> dadosCadastro) throws LoginExistente, ErroNaOperacao, InformacoesInvalidas {
		
		if (dadosCadastro.get("login").equals("admin") && dadosCadastro.get("senha").equals("admin")) {
			throw new InformacoesInvalidas("Login e senha inv�lidos");
		}
		
		// Verificando se o LOGIN j� existe no sistema:
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
