package excecoes;

/**
 * Classe utilizada como uma excecao quando a senha original informada na edicao da
 * mesma for incorreta.
 * 
 * @author Caleo Silva e Joao Pedro
 *
 */
public class SenhaAnteriorIncorreta extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7531060931873840450L;
	
	public SenhaAnteriorIncorreta(String mensagem) {
		super(mensagem);
	}
}
