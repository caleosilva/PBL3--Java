package testandoFuncionalidades;

public class MainCopy {
	
	public void mostrar() {
		Singleton x = Singleton.getInstance();
		
		System.out.println("Idade: " + x.getIdade());
		System.out.println("Nome: " + x.getNome());
	}

}
