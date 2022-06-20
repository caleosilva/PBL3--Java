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

import alertas.AlertasGerais;
import bancoDeDados.Dados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Cardapio;
import model.Fornecedor;
import model.ProdutoEspecifico;
import model.ProdutoGeral;
import model.Usuario;
import model.Vendas;
import model.facade.GerenciadorDeProduto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VendasTelaGeralController implements Initializable {
	
	//private List<ProdutoEspecifico> informacoes = new ArrayList<>();
    //private ObservableList<ProdutoEspecifico> observableInformacoes = null;;
    //private GerenciadorDeProduto gdp = new GerenciadorDeProduto();
    private MudarTelaController mtc = new MudarTelaController();
    private AlertasGerais alertas = new AlertasGerais();
    
    private List<Vendas> informacoes = new ArrayList<>();
    private ObservableList<Vendas> observableInformacoes = null;;


    @FXML
    private TableColumn<Vendas, String> colunaId;

    @FXML
    private TableColumn<Vendas, String> colunaData;

    @FXML
    private TableColumn<Vendas, String> colunaHorario;

    @FXML
    private TableColumn<Vendas, HashMap<String,Double>> colunaItens;

    @FXML
    private TableColumn<Vendas, String> colunaprecoTotal;

    @FXML
    private TableColumn<Vendas, String> colunamodoPagamento;

    @FXML
    private AnchorPane painelPrincipal;

    @FXML
    private TableView<Vendas> tabelaInformacoes;
    
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
  
    	
    	    	
    }

    @FXML
    void botaoExcluirProduto(ActionEvent event) {
    	
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
		
    	colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
    	colunaHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
    	colunaItens.setCellValueFactory(new PropertyValueFactory<>("itens"));
    	colunaprecoTotal.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
    	colunamodoPagamento.setCellValueFactory(new PropertyValueFactory<>("modoPagamento"));
    	
    	for (Vendas u :Dados.getListaVendas()) {
    		informacoes.add(u);
    	}
    	
    	observableInformacoes = FXCollections.observableArrayList(informacoes);
		tabelaInformacoes.setItems(observableInformacoes);
	}

}
