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
	public Usuario encontrarUsuarioPorId(String id, boolean exibirMsg) {

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
		
		boolean sucesso = false;
		// Se o login ja existir no sistema:
		if (!usuario.getLogin().equals(dadosHash.get("login")) && encontrarUsuarioPorLogin(dadosHash.get("login")) != null){
			throw new LoginExistente("Login ja existente no sistema");
			
		//Caso contrario, a edicao eh permitida:
		} else {
			
			// Confirma a senha anterior
			if(dadosHash.get("senhaAnterior").equals(usuario.getSenha())) {
				if (dadosHash.get("senhaNova1").equals(dadosHash.get("senhaNova2"))) {
					usuario.setLogin(dadosHash.get("login"));
					usuario.setSenha(dadosHash.get("senhaNova1"));
					sucesso = true;
				}else {
					throw new SenhasNovasNaoIguais("Senhas novas incoerentes!");
				}
			} else {
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
	 * @return .
	 */
	public Usuario encontrarUsuarioPorLogin(String login) {
		for (Usuario user : listaDeUsuario) {
			if(user.getLogin().equals(login)) {
				return user;
	        }
		}
		return null;
	}

	
	public boolean cadastrarUsuario(HashMap<String, String> dadosCadastro) throws LoginExistente, ErroNaOperacao, InformacoesInvalidas {

		
		if (dadosCadastro.get("login").equals("admin") && dadosCadastro.get("senha").equals("admin")) {
			throw new InformacoesInvalidas("Login e senha inv�lidos");
		}
		
		// Verificando se o LOGIN j� existe no sistema:
		Usuario loginUsado = encontrarUsuarioPorLogin(dadosCadastro.get("login"));
		if (loginUsado != null) throw new LoginExistente("Login j� presente no sistema!");
		
		// Gerando um ID exclusivo.
		int liberado = 0;
		String id = "0";
		do {
			id = GerenciadorDeId.gerarId(1);
			Usuario user = encontrarUsuarioPorId(id, false);
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
