package application;

import java.util.HashMap;
import java.util.List;

import bancoDeDados.Dados;
import model.ProdutoEspecifico;
import model.ProdutoGeral;

public class MainParaTeste {

	public static void main(String[] args) {
		
		HashMap<String, List<ProdutoEspecifico>> dados = new HashMap<>();
		
		for (ProdutoGeral pg : Dados.getListaProdutosGeral()){
			String nome = pg.getNome();
			
			dados.put(nome, pg.getListaDeProdutos());
		}
		
		

	}

}
