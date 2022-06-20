package testandoFuncionalidades;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
	
	static MainCopy m = new MainCopy();

	public static void main(String[] args) {
		
		List<String> a = new ArrayList<>();
		
		a.add("batata");
		a.add("arroz");
		a.add("feijao");
		
		String nomes = "";
		
		for (String produto : a) {
			nomes += produto + ", ";
		}
		nomes = nomes.substring(0, nomes.length()-2);
		
		System.out.println(nomes);
		System.out.println("----");

	}

}
