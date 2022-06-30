package excecoes;

/**
 * Classe utilizada como uma excecao quando o login digitado ja existe.
 * 
 * @author Caleo Silva e Joao Pedro
 *
 */
public class LoginExistente extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4953370467355119762L;
	
	public LoginExistente(String mensagem) {
		super(mensagem);
	}

}
