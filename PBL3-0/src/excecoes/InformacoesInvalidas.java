package excecoes;

/**
 * Classe utilizada como uma excecao para quando determinada informacao for invalida.
 * 
 * @author Caleo Silva e Joao Pedro
 *
 */
public class InformacoesInvalidas extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7908692968207172156L;
	
	public InformacoesInvalidas(String mensagem) {
		super(mensagem);
	}

}
