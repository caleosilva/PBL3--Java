package excecoes;

/**
 * Classe utilizada como uma excecao para quando determinado valor inserido seja invalido.
 * 
 * @author Caleo Silva e Joao Pedro
 *
 */
public class InputsIncorretos extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 22738487901806867L;
	
	public InputsIncorretos(String mensagem) {
		super(mensagem);
	}
}
