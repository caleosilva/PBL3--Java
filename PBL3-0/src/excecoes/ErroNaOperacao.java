package excecoes;

public class ErroNaOperacao extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -273487869201126643L;
	
	public ErroNaOperacao(String mensagem) {
		super(mensagem);
	}

}
