package controller.views;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.JFrame;

import bancoDeDados.Dados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Fornecedor;
import model.ProdutoEspecifico;
import model.ProdutoGeral;
import model.facade.GerenciadorDeProduto;

public class ProdutosController implements Initializable {
	
	private List<ProdutoEspecifico> informacoes = new ArrayList<>();
    private ObservableList<ProdutoEspecifico> observableInformacoes = null;;
    private GerenciadorDeProduto gdp = new GerenciadorDeProduto();
    private MudarTelaController mtc = new MudarTelaController();

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
    
    @FXML
    void botaoAtualizarTabela(ActionEvent event) {
    	atualizarTabela();
    }

    @FXML
    void botaoCadastrarProduto(ActionEvent event) {
    	mtc.abrirNovaJanela("/view/ProdutoTelaCadastrar.fxml", (Stage) ((Node) event.getTarget()).getScene().getWindow());
    }

    @FXML
    void botaoEditarProduto(ActionEvent event) {
    	
    	ProdutoEspecifico produtoEspecifico = tabelaInformacoes.getSelectionModel().getSelectedItem();
    	
    	HashMap<String, Object> dados = new HashMap<>();
    	
    	if (produtoEspecifico != null) {    		
        	mtc.abrirNovaJanela("/view/ProdutoTelaEditar.fxml", (Stage) ((Node) event.getTarget()).getScene().getWindow());
    	}
    	
    }

    @FXML
    void botaoExcluirProduto(ActionEvent event) {
    	ProdutoEspecifico produtoEspecifico = tabelaInformacoes.getSelectionModel().getSelectedItem();
    	gdp.excluirProdutos(produtoEspecifico, tabelaInformacoes);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarInformacoesTableView();
	}
	
    public void atualizarTabela() {
    	try {
    		observableInformacoes.clear();
        	informacoes.clear();
        	carregarInformacoesTableView();
    	} catch (NullPointerException npe) {
			System.out.println("Não há dados para serem apresentados!");
		}
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
