package excecoes;

public class SenhaAnteriorIncorreta extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7531060931873840450L;
	
	public SenhaAnteriorIncorreta(String mensagem) {
		super(mensagem);
	}
}
