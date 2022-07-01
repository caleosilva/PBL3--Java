package model.facade;

import java.util.Random;

/**
 * Classe respons�vel por conter todos os m�todos relacionados a criacao/manipulacao
 * dos ID's presentes no sistema.
 * 
 * @author Caleo Silva e Joao Pedro.
 *
 */
public class GerenciadorDeId{
	
	/**
	 * M�todo responsavel por gerar um ID aleatorio para cada entidade do sistema.
	 * 
	 * @param opcao Numero referente a entidade que esta solicitando um novo ID.
	 * @return O ID que foi gerado ou null caso a opcao for invalida.
	 */
	public static String gerarId(int opcao) {
		
		Random gerador = new Random();
		int novoId = 0;
		
		switch (opcao) { 
		case 1:
			// Usuario - ID de 3 d�gitos.
			novoId = gerador.nextInt(100, 999);
			break;
	
		case 2:
			// Fornecedor - ID de 4 d�gitos.
			novoId = gerador.nextInt(1000, 9999);
			break;
		
		case 3:
			// Cardapio - ID de 5 d�gitos.
			novoId = gerador.nextInt(10000, 99999);
			break;
			
		case 4:
			// Produto - ID de 6 d�gitos.
			novoId = gerador.nextInt(100000, 999999);
			break;
			
		case 5:
			// Vendas - ID de 7 d�gitos.
			novoId = gerador.nextInt(1000000, 9999999);
			break;
			
		default:
			return null;
		}
		
		//Tranforma o id do tipo "int" para "String".
		return String.valueOf(novoId);
	}
}
