package teste;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import model.facade.GerenciadorDeId;

class TestaGerarId {

	@Test
	void testeGerarIdUsuario() {
		String idUsuario = GerenciadorDeId.gerarId(1);
		assertEquals(3, idUsuario.length(), "Gerando um id para o Usu�rio.");
	}
	
	@Test
	void testeGerarIdFornecedor() {
		String idFornecedor = GerenciadorDeId.gerarId(2);
		assertEquals(4, idFornecedor.length(), "Gerando um id para o Fornecedor.");
	}
	
	@Test
	void testeGerarIdCardapio() {
		String idCardapio = GerenciadorDeId.gerarId(3);
		assertEquals(5, idCardapio.length(), "Gerando um id para o Card�pio.");
	}
	
	@Test
	void testeGerarIdProduto() {
		String idProduto = GerenciadorDeId.gerarId(4);
		assertEquals(6, idProduto.length(), "Gerando um id para o Produto.");
	}
	
	@Test
	void testeGerarIdVendas() {
		String idVendas = GerenciadorDeId.gerarId(5);
		assertEquals(7, idVendas.length(), "Gerando um id para uma Venda.");
	}
	
	@Test
	void testeGerarIdOpcaoIncorretaPositiva() {
		String idIncorreto = GerenciadorDeId.gerarId(6);
		assertNull(idIncorreto, "Tentando gerar um id com a op��o positiva incorreta.");
	}
	
	@Test
	void testeGerarIdOpcaoIncorretaNegativa() {
		String idIncorreto = GerenciadorDeId.gerarId(-3);
		assertNull(idIncorreto, "Tentando gerar um id com a op��o negativa incorreta.");
	}
	
}
