package teste;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bancoDeDados.Dados;
import model.Cliente;
import model.facade.GerenciadorDeCliente;

public class TestaCliente {

	private HashMap<String, String> dados = new HashMap<>();
	private HashMap<String, String> dados2 = new HashMap<>();
	private List<Cliente> listaDeClientes = Dados.getListaCliente();
	private GerenciadorDeCliente gdc = new GerenciadorDeCliente();
	@BeforeEach
	void beforeEach() { 
		dados.put("nome", "Jose");
		dados.put("email", "jose@gmail.com");
		dados.put("cpf", "43245");
		dados.put("telefone", "654656");
		
		dados2.put("nome", "Maria");
		dados2.put("email", "maria@gmail.com");
		dados2.put("cpf", "635463");
		dados2.put("telefone", "345345");
		Cliente cliente2= new Cliente("5435", "Naruto", "5643643", "Naruto@gmail.com", "543534");
		Cliente cliente3= new Cliente("5455", "Goku", "6546635", "Goku@gmail.com", "543535");
		listaDeClientes.add(cliente2);
		listaDeClientes.add(cliente3);
	}
	
	@Test
	public void cadastrandoUmNovoCliente() {
		boolean sucesso = gdc.cadastrarCliente(dados);
		assertTrue(sucesso, "Tentando cadastrar um novo cliente");
		
	}
	
	@Test
	public void cadastrandoVariosClientesEVerificandoTamanhoDaLista() {
		listaDeClientes.clear();
		gdc.cadastrarCliente(dados);
		gdc.cadastrarCliente(dados2);
		assertEquals(2, listaDeClientes.size(),"Cadastrando multiplos clientes e verificando tamanho da lista");
	}
	
	@Test
	public void excluindoUmCliente() {
		boolean sucesso = gdc.excluirCliente(listaDeClientes.get(0));
		assertTrue(sucesso,"Verificando se o cliente foi excluido");
	}
	
	@Test
	public void TendandoExcluirClienteQueNaoEstaNaLista() {
		Cliente cliente1= new Cliente("4324", "Luiz", "64367376", "Luiz@gmail.com", "56436367");
		boolean sucesso = gdc.excluirCliente(cliente1);
		assertFalse(sucesso, "Tentativa de excluir cliente não registrado");
	}
	
	@Test
	public void excluindoTodosOsClientes() {
		gdc.excluirCliente(listaDeClientes.get(0));
		gdc.excluirCliente(listaDeClientes.get(0));
		assertEquals(0, listaDeClientes.size(), "Verificando tamanho da lista após excluir todos os clientes");
	}
	
}
