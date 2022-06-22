package controller.listagem;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import bancoDeDados.Dados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.ProdutoEspecifico;
import model.ProdutoGeral;

public class ProdutoListagemController implements Initializable{
	
	private List<ProdutoEspecifico> informacoes = new ArrayList<>();
    private ObservableList<ProdutoEspecifico> observableInformacoes = null;
	
	@FXML
    private TableColumn<ProdutoEspecifico, String> colunaFornecedor;

    @FXML
    private TableColumn<ProdutoEspecifico, String> colunaId;

    @FXML
    private TableColumn<ProdutoEspecifico, String> colunaNome;

    @FXML
    private TableColumn<ProdutoEspecifico, Double> colunaPreco;

    @FXML
    private TableColumn<ProdutoEspecifico, Double> colunaQuantidade;

    @FXML
    private TableColumn<ProdutoEspecifico, Integer> colunaUnidadeDeMedida;

    @FXML
    private TableColumn<ProdutoEspecifico, String> colunaValidade;

    @FXML
    private AnchorPane painelPrincipal;

    @FXML
    private TableView<ProdutoEspecifico> tabelaInformacoes;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarInformacoesTableView();
		
	}
	
	public void carregarInformacoesTableView() {
		
		colunaFornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedorToString"));
		colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
		colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		colunaUnidadeDeMedida.setCellValueFactory(new PropertyValueFactory<>("unidadeDeMedidaFormatada"));
		colunaValidade.setCellValueFactory(new PropertyValueFactory<>("validade"));
		
		for (ProdutoGeral pg : Dados.getListaProdutosGeral()){
			for (ProdutoEspecifico pe : pg.getListaDeProdutos()) {
				pe.setNome(pg.getNome());
				informacoes.add(pe);
			}
		}
		observableInformacoes = FXCollections.observableArrayList(informacoes);
		tabelaInformacoes.setItems(observableInformacoes);
	}

}
