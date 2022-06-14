package testandoFuncionalidades;

public class MainTest {
	
	static MainCopy m = new MainCopy();

	public static void main(String[] args) {
		
		Singleton s = Singleton.getInstance();
		
		s.setIdade(21);
		s.setNome("Caleo");
		
		m.mostrar();
		
	}

}
