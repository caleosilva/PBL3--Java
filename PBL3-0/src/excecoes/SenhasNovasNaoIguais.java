package excecoes;

/**
 * Classe utilizada como uma excecao quando a confirmacao da nova senha falhar.
 * 
 * @author Caleo Silva e Joao Pedro
 *
 */
public class SenhasNovasNaoIguais extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7531060931873840450L;
	
	public SenhasNovasNaoIguais(String mensagem) {
		super(mensagem);
	}
}
