package testandoFuncionalidades;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import application.PreCadastro;
import bancoDeDados.Dados;
import model.ProdutoEspecifico;
import model.ProdutoGeral;

public class MainCopy {
	
	public static void main(String[] args) {
		PreCadastro.main(args);
		
		List<ProdutoEspecifico> dadosOrdenados = new ArrayList<>();
		// Produtos a vencer
		List<ProdutoGeral> dados = Dados.getListaProdutosGeral();
		
		for (ProdutoGeral pg : dados) {
			for (ProdutoEspecifico pe : pg.getListaDeProdutos()) {
				pe.setNome(pg.getNome());
				dadosOrdenados.add(pe);
			}
		}
		System.out.println("Antes de ordenar:\n");
		for (ProdutoEspecifico pe : dadosOrdenados) {
			System.out.println("Nome: " + pe.getNome() + ", validade: " + pe.getValidade());
		}
		
		System.out.println("Depois de ordenar:\n");
		dadosOrdenados.sort(Comparator.comparing(ProdutoEspecifico::getDataLocalDate));
		
		for (ProdutoEspecifico pe : dadosOrdenados) {
			System.out.println("Nome: " + pe.getNome() + ", validade: " + pe.getValidade());
		}
		
	}
}
