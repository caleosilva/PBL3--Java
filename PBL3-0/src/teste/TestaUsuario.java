package teste;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bancoDeDados.Dados;
import excecoes.ErroNaOperacao;
import excecoes.InformacoesInvalidas;
import excecoes.LoginExistente;
import excecoes.SenhaAnteriorIncorreta;
import excecoes.SenhasNovasNaoIguais;
import model.Funcionario;
import model.Gerente;
import model.Usuario;
import model.facade.GerenciadorDeUsuario;

class TestaUsuario {
	
	private GerenciadorDeUsuario gdu = new GerenciadorDeUsuario();
	private List<Usuario> listaDeUsuario = Dados.getListaUsuario();
	
	@BeforeEach
	void beforeEach() {
		Gerente g1 = new Gerente("id1", "login1", "senha1", "Gerente");
		Gerente g2 = new Gerente("id2", "login2", "senha2", "Gerente");
		Funcionario f1 = new Funcionario("id3", "login3", "senha3", "Funcionario");
		
		listaDeUsuario.add(g1);
		listaDeUsuario.add(g2);
		listaDeUsuario.add(f1);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		listaDeUsuario.clear();
	}
	
	// Cadastrar:
	
	@Test
	void testeCadastrandoNovoGerenteEVerificandoTamanhoDaLista() {
		
		HashMap<String, String> dadosGerente = new HashMap<>();
		dadosGerente.put("login", "loginTeste1");
		dadosGerente.put("senha", "senhaTeste1");
		dadosGerente.put("cargo", "Gerente");
		
		try {
			gdu.cadastrarUsuario(dadosGerente);
		} catch (LoginExistente | ErroNaOperacao | InformacoesInvalidas e) {
			// Erro
		}
		
		assertEquals("Veificando o tamanho", 4, listaDeUsuario.size());
		
	}
	
	@Test
	void testeCadastrandoNovoGerenteEVerificandoLogin() {
		
		HashMap<String, String> dadosGerente = new HashMap<>();
		dadosGerente.put("login", "verificaLogin");
		dadosGerente.put("senha", "123");
		dadosGerente.put("cargo", "Gerente");
				
		try {
			gdu.cadastrarUsuario(dadosGerente);
		} catch (LoginExistente | ErroNaOperacao | InformacoesInvalidas e) {
			// Erro
		}
		assertEquals("Verificando o Login", "verificaLogin", listaDeUsuario.get(3).getLogin());
	}
	
	@Test
	void testeCadastrandoNovoFuncionarioEVerificandoSenha() {
		
		HashMap<String, String> dadosFuncionario = new HashMap<>();
		dadosFuncionario.put("login", "verificaLogin");
		dadosFuncionario.put("senha", "verificaSenha");
		dadosFuncionario.put("cargo", "Funcionario");
		
		try {
			gdu.cadastrarUsuario(dadosFuncionario);
		} catch (LoginExistente | ErroNaOperacao | InformacoesInvalidas e) {
			// Erro
		}
		assertEquals("Verificando o Login", "verificaLogin", listaDeUsuario.get(3).getLogin());
	}
	
	@Test
	void testeCadastrandoNovoUsuarioComLoginRepetidoVerificandoTamanhoDaLista() {
		
		HashMap<String, String> dadosUsuario = new HashMap<>();
		dadosUsuario.put("login", "login1");
		dadosUsuario.put("senha", "senhaAleatoria");
		dadosUsuario.put("cargo", "Gerente");
		
		try {
			gdu.cadastrarUsuario(dadosUsuario);
		} catch (LoginExistente | ErroNaOperacao | InformacoesInvalidas e) {
			// Erro
		}
		assertEquals("Verificando se vai cadastrar ou não", 3, listaDeUsuario.size());
	}
	
	@Test
	void testeCadastrandoVariosUsuarios() {
		
		HashMap<String, String> dadosUsuario = new HashMap<>();
		dadosUsuario.put("login", "novoLogin1");
		dadosUsuario.put("senha", "senha1");
		dadosUsuario.put("cargo", "Gerente");
		
		try {
			gdu.cadastrarUsuario(dadosUsuario);
		} catch (LoginExistente | ErroNaOperacao | InformacoesInvalidas e) {
			// Erro
		}
		//NÃO TEM QUE LIMPAR O HASHMAP?
		dadosUsuario.put("login", "novoLogin2");
		dadosUsuario.put("senha", "senha2");
		dadosUsuario.put("cargo", "Funcionario");
		
		try {
			gdu.cadastrarUsuario(dadosUsuario);
		} catch (LoginExistente | ErroNaOperacao | InformacoesInvalidas e) {
			// Erro
		}
		
		assertEquals("Verificando tamanho da lista", 5, listaDeUsuario.size());
	}
	
	@Test
	void testeCadastrandoUsuarioComTipoInvalido() {
		
		HashMap<String, String> dadosUsuario = new HashMap<>();
		dadosUsuario.put("login", "nome1");
		dadosUsuario.put("senha", "senha1");
		dadosUsuario.put("cargo", "vendedor");
		
		
		try {
			gdu.cadastrarUsuario(dadosUsuario);
		} catch (LoginExistente | ErroNaOperacao | InformacoesInvalidas e) {
			// Erro
		}
		
		assertEquals("Verificando tamanho da lista", 3, listaDeUsuario.size());
	}
	
	// Editar:
	@Test
	void testeEditandoOLogin() {
		Usuario user = listaDeUsuario.get(0);
		
		HashMap<String, String> dados = new HashMap<>();
		dados.put("login", "NovoLogin");
		dados.put("senhaAnterior", "senha1");
		dados.put("senhaNova1", "senha1");
		dados.put("senhaNova2", "senha1");
		
		try {
			gdu.editarUsuario(user, dados);
		} catch (LoginExistente | SenhaAnteriorIncorreta | SenhasNovasNaoIguais e) {
			// Erro
		}
		
		assertEquals("Editando o Login", dados.get("login"), user.getLogin());
	}
	
	@Test
	void testeEditandoASenha() {
		Usuario user = listaDeUsuario.get(1);
	
		HashMap<String, String> dados = new HashMap<>();
		dados.put("login", "login");
		dados.put("senhaAnterior", "senha2");
		dados.put("senhaNova1", "novaSenha");
		dados.put("senhaNova2", "novaSenha");
		
		try {
			gdu.editarUsuario(user, dados);
		} catch (LoginExistente | SenhaAnteriorIncorreta | SenhasNovasNaoIguais e) {
			// Erro
		}
		
		assertEquals("Editando a senha", dados.get("senhaNova1"), user.getSenha());
	}
	
	@Test
	void testeEditandoLoginParaUmJaExistente() {
		Usuario user = listaDeUsuario.get(2);
	
		HashMap<String, String> dados = new HashMap<>();
		dados.put("login", "login1");
		dados.put("senhaAnterior", "senha3");
		dados.put("senhaNova1", "senha3");
		dados.put("senhaNova2", "senha3");
		
		try {
			gdu.editarUsuario(user, dados);
		} catch (LoginExistente | SenhaAnteriorIncorreta | SenhasNovasNaoIguais e) {
			// Erro
		}
		
		assertNotEquals("Editando o login", dados.get("login"), user.getLogin());
	}
	
	@Test
	void testeExcluirUmElemento() {
		Usuario userExcluido = listaDeUsuario.get(0);
		
		gdu.excluirUsuario(userExcluido);
		
		assertEquals(listaDeUsuario.size(), 2, "Excluindo apenas um usuario.");
	}
	
	@Test
	void testeExcluirVariosElementos() {
		Usuario userExcluido1 = listaDeUsuario.get(0);
		Usuario userExcluido2 = listaDeUsuario.get(2);
		
		gdu.excluirUsuario(userExcluido1);
		gdu.excluirUsuario(userExcluido2);
		
		assertEquals(listaDeUsuario.size(), 1, "Excluindo 2 usu�rios.");
	}
	
	@Test
	void testeExcluirUmElementoEVerificarAPosicao() {
		Usuario userExcluido = listaDeUsuario.get(0);
		
		gdu.excluirUsuario(userExcluido);
		
		assertFalse(listaDeUsuario.get(0).equals(userExcluido), "Verificando o novo elemento da posi��o.");
	}
	
	@Test
	void testeExcluirUmElementoDuasVezes() {
		
		Usuario userExcluido = listaDeUsuario.get(0);
		Usuario copiaUserExcluido = userExcluido;
		
		gdu.excluirUsuario(userExcluido);
		boolean excluiu2 = gdu.excluirUsuario(copiaUserExcluido);
		
		assertFalse(excluiu2, "Excluido 1 elemento duas vezes");
	}
	
	// Buscar:
	
	@Test
	void testeBuscarGerentePorLoginExistente() {
		Usuario user = gdu.encontrarUsuarioPorLogin("login1");
		assertNotNull(user, "Buscando Gerente por um Login existente");
	}
	
	@Test
	void testeBuscarGerentePorLoginNaoExistente() {
		Usuario user = gdu.encontrarUsuarioPorLogin("loginNaoExiste");
		assertNull(user, "Buscando Gerente por um Login nao existente");
	}
	
	@Test
	void testeBuscarFuncionarioPeloIdExistente() {
		Usuario user = gdu.encontrarUsuarioPorId("id3", false);
		String id = "id3";
		assertEquals("Buscando Funcionario por um ID existente", id, user.getId());
	}
	
	@Test
	void testeBuscarFuncionarioPeloIdNaoExistente() {
		Usuario user = gdu.encontrarUsuarioPorId("idNaoExistente", false);
		assertNull(user, "Buscar Funcionario por um ID N�O existente");
	}
	
	@Test
	void testeBuscarFuncionarioPeloIdNull() {
		Usuario user = gdu.encontrarUsuarioPorId(null, false);
		assertNull(user, "Buscando Funcionario atrav�s de um ID null");
	}

}
