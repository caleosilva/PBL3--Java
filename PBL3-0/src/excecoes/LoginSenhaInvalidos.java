package excecoes;

public class LoginSenhaInvalidos extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4812692029157609163L;
	
	public LoginSenhaInvalidos(String mensagem) {
		super(mensagem);
	}
}
