package excecoes;

public class LoginExistente extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4953370467355119762L;
	
	public LoginExistente(String mensagem) {
		super(mensagem);
	}

}
