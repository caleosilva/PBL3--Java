package model.facade;

import java.util.Random;

/**
 * Classe respons�vel por conter todos os m�todos relacionados a cria��o/manipula��o
 * dos ID's presentes no sistema.
 * 
 * @author Caleo Silva e Jo�o Pedro.
 *
 */
public class GerenciadorDeId{
	
	/**
	 * M�todo respons�vel por gerar um ID ale�torio para cada entidade do sistema.
	 * 
	 * @param opcao N�mero referente a entidade que est� solicitando um novo ID.
	 * @return O ID que foi gerado ou null caso a op��o for inv�lida.
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
