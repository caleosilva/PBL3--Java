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
 * Classe responsável por implementar os métodos responsáveis por cadastrar/editar/excluir objetos do tipo
 * Usuario.
 *
 * @author Caleo Silva e João Pedro.
 */

public class GerenciadorDeUsuario{
	
	private static List<Usuario> listaDeUsuario = Dados.getListaUsuario();
	
	/**
	 * Método reponsável por realizar a busca de um objeto do tipo Usuario em um ArrayList.
	 * 
	 * @param listaDeUsuario Um ArraysList que contém todos os usuários cadastrados.
	 * @param id Id do usuário que será buscado.
	 * @param exibirMsg Variável de controle que é usada para exibir uma mensagem (true) ou não (false).
	 * 
	 * @return Retorna o endereço de memória do objeto, caso não seja encontrado retorna null.
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
	
	
	public boolean editarUsuario(Usuario usuario, HashMap<String, String> informacao) throws LoginExistente {
		
		boolean sucesso = false;
		
		// Se o login já existir no sistema:
		if (encontrarUsuarioPorLogin(informacao.get("login"))){
			throw new LoginExistente("Login já existe no sistema");
		
		//Caso contrário, a edição é permitida:
		} else {
			usuario.setLogin(informacao.get("login"));
			usuario.setSenha(informacao.get("senha"));
			sucesso = true;
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
	 * Método responsável po buscar um objeto do tipo "Usuario" através do seu Login.
	 * 
	 * @param listaDeUsuario Um ArraysList que contém todos os usuários cadastrados.
	 * @param login Login que será utilizado para buscar o usuário.
	 * 
	 * @return 1 caso encontre, 0 caso não encontre.
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
		
		// Verificando se o LOGIN já existe no sistema:
		if (dadosCadastro == null) {
			System.out.println("Achei papai");
		}
		boolean loginUsado = encontrarUsuarioPorLogin(dadosCadastro.get("login"));
		if (loginUsado) throw new LoginExistente("Login já presente no sistema!");
		
		// Gerando um ID exclusivo.
		int liberado = 0;
		String id = "0";
		do {
			id = GerenciadorDeId.gerarId(1);
			Usuario user = GerenciadorDeUsuario.encontrarUsuarioPorId(id, false);
			// Se não houver nenhum usuário com o ID gerado:
			if (user == null) liberado = 1;
		} while(liberado == 0);

		//Cadastrando um gerente:
		if (dadosCadastro.get("cargo").equals("Gerente")) {
			// Criando o objeto:
			Gerente novoGerente = new Gerente(id, dadosCadastro.get("login"), dadosCadastro.get("senha"), "Gerente");
			
			// Adicionando na lista:
			listaDeUsuario.add(novoGerente);
			return true;
		
		//Cadastrando um funcionário:
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
