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
import model.facade.GerenciadorDeProduto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CardapioTelaGeralController implements Initializable {
	
	//private List<ProdutoEspecifico> informacoes = new ArrayList<>();
    //private ObservableList<ProdutoEspecifico> observableInformacoes = null;;
    //private GerenciadorDeProduto gdp = new GerenciadorDeProduto();
    private MudarTelaController mtc = new MudarTelaController();
    private AlertasGerais alertas = new AlertasGerais();
    
    private List<Cardapio> informacoes = new ArrayList<>();
    private ObservableList<Cardapio> observableInformacoes = null;;


    @FXML
    private TableColumn<Cardapio, String> colunaId;

    @FXML
    private TableColumn<Cardapio, String> colunaNome;

    @FXML
    private TableColumn<Cardapio, String> colunaDescricao;

    @FXML
    private TableColumn<Cardapio, HashMap<String,Double>> colunaItens;

    @FXML
    private TableColumn<Cardapio, Integer> colunaPreco;

    @FXML
    private TableColumn<Cardapio, String> colunaCategoria;

    @FXML
    private AnchorPane painelPrincipal;

    @FXML
    private TableView<Cardapio> tabelaInformacoes;
    
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
    	colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    	colunaItens.setCellValueFactory(new PropertyValueFactory<>("itens"));
    	colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
    	colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));;
    	
    	for (Cardapio u :Dados.getListaCardapio()) {
    		informacoes.add(u);
    	}
    	
    	observableInformacoes = FXCollections.observableArrayList(informacoes);
		tabelaInformacoes.setItems(observableInformacoes);
	}

}
