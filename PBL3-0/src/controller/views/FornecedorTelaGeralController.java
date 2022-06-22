package controller.views;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import model.Fornecedor;
import model.ProdutoEspecifico;
import model.ProdutoGeral;
import model.Usuario;

public class FornecedorTelaGeralController implements Initializable{
	
	private MudarTelaController mtc = new MudarTelaController();
	private List<Fornecedor> informacoes = new ArrayList<>();
    private ObservableList<Fornecedor> observableInformacoes = null;
    private AlertasGerais alertas = new AlertasGerais();

    @FXML
    private TableColumn<Fornecedor, String> colunaCNPJ;

    @FXML
    private TableColumn<Fornecedor, String> colunaEndereco;

    @FXML
    private TableColumn<Fornecedor, String> colunaId;

    @FXML
    private TableColumn<Fornecedor, String> colunaNome;

    @FXML
    private TableColumn<Fornecedor, String> colunaProdutos;

    @FXML
    private AnchorPane painelPrincipal;

    @FXML
    private TableView<Fornecedor> tabelaInformacoes;

    @FXML
    void botaoAtualizarTabela(ActionEvent event) {
    	atualizarTabela();
    }

    @FXML
    void botaoCadastrarFornecedor(ActionEvent event) {
    	mtc.abrirNovaJanela("/view/FornecedorTelaCadastrar.fxml", (Stage) ((Node) event.getTarget()).getScene().getWindow(), false);
    }

    @FXML
    void botaoEditarFornecedor(ActionEvent event) {
    	Fornecedor fornecedorEspecifico = tabelaInformacoes.getSelectionModel().getSelectedItem();
    	
    	if (fornecedorEspecifico != null) {
    		
    		FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/view/FornecedorTelaEditar.fxml"));
            
            try {
                loader.load();
            } catch (IOException ex) {
            	alertas.erroNaOperacao();
            }
            
            FornecedorTelaEditarController controllerEditar =  loader.getController();
            
            controllerEditar.adicionarInformacoes(fornecedorEspecifico.getNome(), fornecedorEspecifico.getCnpj(),
            		fornecedorEspecifico.getEndereco(), fornecedorEspecifico.getListaNomeProdutos(),fornecedorEspecifico);
            
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
    void botaoExcluirFornecedor(ActionEvent event) {
    	Fornecedor fornecedorEspecifico = tabelaInformacoes.getSelectionModel().getSelectedItem();
    	
    	if (fornecedorEspecifico != null) {
    		
    		FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/view/FornecedorTelaExcluir.fxml"));
            
            try {
                loader.load();
            } catch (IOException ex) {
            	alertas.erroNaOperacao();
            }
            
            FornecedorTelaExcluirController controllerExcluir =  loader.getController();
            controllerExcluir.receberInformacao(fornecedorEspecifico);
            
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
    
    public void carregarInformacoesTableView() {
		
    	colunaCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		colunaProdutos.setCellValueFactory(new PropertyValueFactory<>("produtoPorString"));
		
		for (Fornecedor f : Dados.getListaFornecedor()){
			informacoes.add(f);
		}
		
		observableInformacoes = FXCollections.observableArrayList(informacoes);
		tabelaInformacoes.setItems(observableInformacoes);
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

}
