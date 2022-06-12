package testandoFuncionalidades;

public final class Singleton {
	
	private static Singleton instance;
	
    private String nome;
    private int idade;
    
    private Singleton() {
    }
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    
    public String getNome() {
		return nome;
	}
    public void setNome(String nome) {
		this.nome = nome;
	}
    public int getIdade() {
		return idade;
	}
    public void setIdade(int idade) {
		this.idade = idade;
	}

}
