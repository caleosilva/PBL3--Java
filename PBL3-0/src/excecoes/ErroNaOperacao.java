package excecoes;

/**
 * Classe utilizada como uma excecao para informar um erro na operacao.
 * 
 * @author Caleo Silva e Joao Pedro
 *
 */
public class ErroNaOperacao extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -273487869201126643L;
	
	public ErroNaOperacao(String mensagem) {
		super(mensagem);
	}

}
