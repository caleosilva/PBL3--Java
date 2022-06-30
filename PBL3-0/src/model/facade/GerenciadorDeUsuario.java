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
 * @author Caleo Silva e Joao Pedro.
 */

public class GerenciadorDeUsuario{
	
	private static List<Usuario> listaDeUsuario = Dados.getListaUsuario();
	
	/**
	 * M�todo repons�vel por realizar a busca de um objeto do tipo Usuario em um ArrayList.
	 * 
	 * @param id Id do usuario que sera buscado.
	 * @param exibirMsg Variavel de controle que eh usada para exibir uma mensagem (true) ou
	 * nao (false).
	 * 
	 * @return Retorna o endereco de memoria do objeto, caso nao seja encontrado retorna null.
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
	
	/**
	 * Metodo responsavel por editar as informacoes de um usuario.
	 * 
	 * @param usuario Objeto do tipo usuario que sera editado.
	 * @param dadosHash HashMap contendo as novas informacoes.
	 * @return true caso seja editado ou false caso nao.
	 * @throws LoginExistente Excecao caso o login digitado ja exista.
	 * @throws SenhaAnteriorIncorreta Excecao caso a senha digitada esteja incorreta.
	 * @throws SenhasNovasNaoIguais Excecao caso as novas senhas nao estao iguais.
	 */
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
	
	/**
	 * Metodo responsavel por excluir um usuario.
	 * @param usuario Objeto do tipo Usuario que sera excluido.
	 * @return true caso seja excluido ou false caso nao.
	 */
	public boolean excluirUsuario(Usuario usuario) {
		boolean excluido = false;
		if(usuario != null) {
			excluido = listaDeUsuario.remove(usuario);
		}
		return excluido;
	}
	
	/**
	 * Metodo responsavel po buscar um objeto do tipo "Usuario" atraves do seu Login.
	 * 
	 * @param listaDeUsuario Um ArraysList que contem todos os usuarios cadastrados.
	 * @param login Login que sera utilizado para buscar o usuario.
	 * 
	 * @return Objeto do tipo Usuario caso o encontre, caso nao retorna null.
	 */
	public Usuario encontrarUsuarioPorLogin(String login) {
		for (Usuario user : listaDeUsuario) {
			if(user.getLogin().equals(login)) {
				return user;
	        }
		}
		return null;
	}

	/**
	 * Metodo responsavel por cadastrar um novo usuario no sistema.
	 * 
	 * @param dadosCadastro HashMap contendo as informacoes do novo usuario.
	 * @return true caso o cadastro seja feito, caso nao retorna false.
	 * 
	 * @throws LoginExistente Excecao caso o login digitado ja exista.
	 * @throws ErroNaOperacao Excecao caso ocorra um erro durante o cadastro.
	 * @throws InformacoesInvalidas Excecao caso as informacoes passadas sejam invalidas.
	 */
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
