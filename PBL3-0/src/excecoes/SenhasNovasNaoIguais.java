package excecoes;

public class SenhasNovasNaoIguais extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7531060931873840450L;
	
	public SenhasNovasNaoIguais(String mensagem) {
		super(mensagem);
	}
}
