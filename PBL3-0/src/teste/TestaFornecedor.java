package teste;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bancoDeDados.Dados;
import model.Fornecedor;
import model.facade.GerenciadorDeFornecedor;
import model.facade.GerenciadorDeUsuario;

class TestaFornecedor {
	
	private GerenciadorDeFornecedor gdf = new GerenciadorDeFornecedor();
	private List<Fornecedor> listaDeFornecedor = Dados.getListaFornecedor();
	
	@BeforeEach
	void beforeEach() {
		Fornecedor f1 = new Fornecedor("id1", "cnpj1", "nome1", "endereco1");
		Fornecedor f2 = new Fornecedor("id2", "cnpj2", "nome2", "endereco2");
		Fornecedor f3 = new Fornecedor("id3", "cnpj3", "nome3", "endereco3");
		
		listaDeFornecedor.add(f1);
		listaDeFornecedor.add(f2);
		listaDeFornecedor.add(f3);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		listaDeFornecedor.clear();
	}
	
	// Adicionar:
	@Test
	void testeAdicionandoNovoFornecedorEVerificandoTamanhoDaLista() {
		
		HashMap<String, Object> dadosFornecedor = new HashMap<>();
				
		dadosFornecedor.put("cnpj", "novoCnpj");
		dadosFornecedor.put("nome", "novoNome");
		dadosFornecedor.put("endereco", "novoEndereco");
		List<String> produtos = new ArrayList<>();
		produtos.add("feijao");
		dadosFornecedor.put("produtos", produtos);
		
		gdf.cadastrarFornecedor(dadosFornecedor);
				
		assertEquals(4, listaDeFornecedor.size(), "Adicionando um novo fornecedor.");
	}
	
	@Test
	void testeAdicionandoNovosFornecedoresEVerificandoTamanhoDaLista() {
		
		HashMap<String, Object> dadosFornecedor = new HashMap<>();
		dadosFornecedor.put("cnpj", "novoCnpj");
		dadosFornecedor.put("nome", "novoNome");
		dadosFornecedor.put("endereco", "novoEndereco");
		List<String> produtos = new ArrayList<>();
		produtos.add("ovo");
		dadosFornecedor.put("produtos", produtos);
		
		gdf.cadastrarFornecedor(dadosFornecedor);
		
		dadosFornecedor.put("cnpj", "novoCnpj1");
		dadosFornecedor.put("nome", "novoNome2");
		dadosFornecedor.put("endereco", "novoEndereco3");
		dadosFornecedor.put("produtos", produtos);

		
		gdf.cadastrarFornecedor(dadosFornecedor);
		
		assertEquals(5, listaDeFornecedor.size(), "Adicionando novos fornecedores.");
	}
	
	@Test
	void testeAdicionandoNovoFornecedorEVerificandoCnpj() {
		
		HashMap<String, Object> dadosFornecedor = new HashMap<>();
		dadosFornecedor.put("cnpj", "novoCnpj");
		dadosFornecedor.put("nome", "novoNome");
		dadosFornecedor.put("endereco", "novoEndereco");
		
		List<String> produtos = new ArrayList<>();
		produtos.add("arroz, feijao");
		dadosFornecedor.put("produtos", produtos);
		
		gdf.cadastrarFornecedor(dadosFornecedor);
		String cnpjDoNovoFornecedor = listaDeFornecedor.get(3).getCnpj();
		
		assertEquals("novoCnpj", cnpjDoNovoFornecedor, "Confirmando uma informacao do novo fornecedor.");
	}
	
	// Editar:
	@Test
	void testeEditarCnpjFornecedor() {
		
		Fornecedor fornecedorEditado = listaDeFornecedor.get(0);
		
		HashMap<String, Object> novoDado = new HashMap<>();
		novoDado.put("cnpj", "novoCnpjTeste");
		
		gdf.editarFornecedor(fornecedorEditado, novoDado);
		
		assertEquals(novoDado.get("cnpj"), listaDeFornecedor.get(0).getCnpj(), "Alterando o CNPJ.");
	}
	
	@Test
	void testeEditarNomeFornecedor() {
		
		Fornecedor fornecedorEditado = listaDeFornecedor.get(1);
		
		HashMap<String, Object> novoDado = new HashMap<>();
		novoDado.put("nome", "novoNomeTeste");
		
		gdf.editarFornecedor(fornecedorEditado, novoDado);
		
		assertEquals(novoDado.get("nome"), listaDeFornecedor.get(1).getNome(), "Alterando o Nome.");
	}
	
	@Test
	void testeEditarEnderecoFornecedor() {
		
		Fornecedor fornecedorEditado = listaDeFornecedor.get(2);
		
		HashMap<String, Object> novoDado = new HashMap<>();
		novoDado.put("endereco", "novoEnderecoTeste");
		
		gdf.editarFornecedor(fornecedorEditado, novoDado);
		
		assertEquals(novoDado.get("endereco"), listaDeFornecedor.get(2).getEndereco(), "Alterando o Endere�o.");
	}
	
	// Excluir:
	@Test
	void testeExcluirUmFornecedor() {
		Fornecedor fornecedorExcluido = listaDeFornecedor.get(0);
		
		gdf.excluirFornecedor(fornecedorExcluido);
		
		assertEquals(2, listaDeFornecedor.size(), "Excluindo um fornecedor.");
	}
	
	@Test
	void testeExcluirUmFornecedorQueNaoEstaNaLista() {
		Fornecedor fornecedorExcluido = new Fornecedor("id4", "cnpj4", "nome4", "endereco4");
		
		boolean excluiu = gdf.excluirFornecedor(fornecedorExcluido);
		
		assertFalse(excluiu, "Tentando excluir um fornecedor que n�o t� na lista.");
	}
	
	@Test
	void testeExcluirUmFornecedorNull() {
		Fornecedor fornecedorExcluido = null;
		
		boolean excluiu = gdf.excluirFornecedor(fornecedorExcluido);
		
		assertFalse(excluiu, "Tentando excluir um fornecedor null");
	}
	
	@Test
	void testeExcluirDoisFornecedores() {
		Fornecedor fornecedorExcluido1 = listaDeFornecedor.get(0);
		Fornecedor fornecedorExcluido2 = listaDeFornecedor.get(1);
		
		gdf.excluirFornecedor(fornecedorExcluido1);
		gdf.excluirFornecedor(fornecedorExcluido2);
		
		assertEquals(1, listaDeFornecedor.size(), "Excluindo dois fornecedores.");
	}
	
	// Buscar:
	@Test
	void buscarFornecedorPeloIdExistente() {
		Fornecedor forn = GerenciadorDeFornecedor.buscarFornecedorPeloID("id2");
		String id = "id2";
		assertEquals(id, forn.getId(), "Buscando Fornecedor atrav�s de um ID existente.");
	}
	
	@Test
	void buscarFornecedorPeloIdNaoExistente() {
		Fornecedor forn = GerenciadorDeFornecedor.buscarFornecedorPeloID("idNaoExistente");
		assertNull(forn, "Buscando Fornecedor atrav�s de um ID N�O existente");
	}
		
		

}
