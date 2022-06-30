package excecoes;

/**
 * Classe utilizada como uma excecao quando o login e a senha forem invalidos.
 * 
 * @author Caleo Silva e Joao Pedro
 *
 */
public class LoginSenhaInvalidos extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4812692029157609163L;
	
	public LoginSenhaInvalidos(String mensagem) {
		super(mensagem);
	}
}
