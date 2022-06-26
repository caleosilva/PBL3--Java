package teste;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bancoDeDados.Dados;
import excecoes.LoginSenhaInvalidos;
import model.Funcionario;
import model.Gerente;
import model.Usuario;
import model.facade.GerenciadorDeLogin;

class TestaLogin {
	
	private GerenciadorDeLogin gdl = new GerenciadorDeLogin();
	private List<Usuario> listaDeUsuarios = Dados.getListaUsuario();
	
	@BeforeEach
	void beforeEach() {
		Gerente g1 = new Gerente("1", "login1", "senha1", "Gerente");
		Gerente g2 = new Gerente("2", "login2", "senha2", "Gerente");
		Funcionario f1 = new Funcionario("3", "login3", "senha3", "Funcionario");
		
		listaDeUsuarios.add(g1);
		listaDeUsuarios.add(g2);
		listaDeUsuarios.add(f1);
	}
	
	@Test
	void testeLoginComDadosValidos() {		
		Usuario user = null;
		try {
			user = gdl.logarNoSistema("login1", "senha1");
		} catch (LoginSenhaInvalidos e) {
			//Senha e login inválidos
		}
		assertNotNull(user);
	}
	
	@Test
	void testeLoginComDadosInexistentes() {
		
		Usuario user = null;
		try {
			user = gdl.logarNoSistema("loginInvalido", "senhaInvalida");
		} catch (LoginSenhaInvalidos e) {
			//Senha e login inválidos
		}
		assertNull(user);
	}
}
