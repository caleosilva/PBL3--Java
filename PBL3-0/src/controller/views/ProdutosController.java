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

import model.Fornecedor;
import model.ProdutoEspecifico;
import model.ProdutoGeral;
import model.facade.GerenciadorDeProduto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutosController implements Initializable {
	
	private List<ProdutoEspecifico> informacoes = new ArrayList<>();
    private ObservableList<ProdutoEspecifico> observableInformacoes = null;
    private MudarTelaController mtc = new MudarTelaController();
    private AlertasGerais alertas = new AlertasGerais();

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
    	
    	if (produtoEspecifico != null) {
    		FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/view/ProdutoTelaEditar.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                System.out.println("Erro");
            }
            
            // Controller:
            ProdutoTelaEditarController controllerEditar =  loader.getController();
            
            LocalDate data = LocalDate.parse(produtoEspecifico.getValidade(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            controllerEditar.adicionarInformacoes(produtoEspecifico.getNome(), produtoEspecifico.getPreco(),
            		 data, produtoEspecifico.getQuantidade(), produtoEspecifico.getFornecedor(),
            		 produtoEspecifico.getUnidadeDeMedidaFormatada(), produtoEspecifico.getId());
            
            // Abrindo nova tela:
            Stage parentStage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initOwner(parentStage);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
    	} else {
    		alertas.itemNaoSelecionado();
    	}
    	
    	
    	    	
    }

    @FXML
    void botaoExcluirProduto(ActionEvent event) {
    	
    	ProdutoEspecifico produtoEspecifico = tabelaInformacoes.getSelectionModel().getSelectedItem();
    	
    	if (produtoEspecifico != null) {
    		
    		FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/view/ProdutoTelaExcluir.fxml"));
            
            try {
                loader.load();
            } catch (IOException ex) {
            	alertas.erroNaOperacao();
            }
            
            ProdutoTelaExcluirController controllerExcluir =  loader.getController();
            controllerExcluir.receberInformacao(produtoEspecifico, tabelaInformacoes);
            
            // Abrindo nova tela:
            Stage parentStage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initOwner(parentStage);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
    	} else {
    		alertas.itemNaoSelecionado();
    	}
    	
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
