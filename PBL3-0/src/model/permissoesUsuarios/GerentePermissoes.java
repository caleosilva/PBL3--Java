package model.permissoesUsuarios;

import java.util.HashMap;
import java.util.List;

import javafx.scene.control.TableView;
import model.Fornecedor;
import model.ProdutoEspecifico;
import model.ProdutoGeral;
import model.concreto.GerenciadorDeProduto;
import model.interfaces.*;

public class GerentePermissoes implements ProdutosInterface{
	
	

	@Override
	public boolean editarProdutos(HashMap<String, Object> dadosProduto, HashMap<String, Object> novaInformacao) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cadastrarProdutos(List<ProdutoGeral> listaDeProdutos, List<Fornecedor> listaDeFornecedores,
			HashMap<String, Object> listaDados) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluirProdutos(List<ProdutoGeral> listaDeProdutos,  ProdutoEspecifico produto, TableView<ProdutoEspecifico> tabelaInformacoes) {
		return false;
	}

}
