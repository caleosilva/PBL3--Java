package model.facade;

import java.util.Random;

/**
 * Classe responsável por conter todos os métodos relacionados a criação/manipulação
 * dos ID's presentes no sistema.
 * 
 * @author Caleo Silva e João Pedro.
 *
 */
public class GerenciadorDeId{
	
	/**
	 * Método responsável por gerar um ID aleátorio para cada entidade do sistema.
	 * 
	 * @param opcao Número referente a entidade que está solicitando um novo ID.
	 * @return O ID que foi gerado ou null caso a opção for inválida.
	 */
	public static String gerarId(int opcao) {
		
		Random gerador = new Random();
		int novoId = 0;
		
		switch (opcao) { 
		case 1:
			// Usuario - ID de 3 dígitos.
			novoId = gerador.nextInt(100, 999);
			break;
	
		case 2:
			// Fornecedor - ID de 4 dígitos.
			novoId = gerador.nextInt(1000, 9999);
			break;
		
		case 3:
			// Cardapio - ID de 5 dígitos.
			novoId = gerador.nextInt(10000, 99999);
			break;
			
		case 4:
			// Produto - ID de 6 dígitos.
			novoId = gerador.nextInt(100000, 999999);
			break;
			
		case 5:
			// Vendas - ID de 7 dígitos.
			novoId = gerador.nextInt(1000000, 9999999);
			break;
			
		default:
			return null;
		}
		
		//Tranforma o id do tipo "int" para "String".
		return String.valueOf(novoId);
	}
	
	
	
	
}
