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
import model.Cardapio;
import model.Cliente;

public class ClientesTelaGeralController implements Initializable {

	private MudarTelaController mtc = new MudarTelaController();
    private AlertasGerais alertas = new AlertasGerais();
    
    private List<Cliente> informacoes = new ArrayList<>();
    private ObservableList<Cliente> observableInformacoes = null;;
	
    @FXML
    private TableColumn<Cliente, String> colunaCPF;

    @FXML
    private TableColumn<Cliente, String> colunaEmail;

    @FXML
    private TableColumn<Cliente, String> colunaId;

    @FXML
    private TableColumn<Cliente, String> colunaNome;

    @FXML
    private TableColumn<Cliente, String> colunaTelefone;

    @FXML
    private AnchorPane painelPrincipal;

    @FXML
    private TableView<Cliente> tabelaInformacoes;

    @FXML
    void botaoAtualizarTabela(ActionEvent event) {
    	atualizarTabela();
    }

    @FXML
    void botaoCadastrarUsuario(ActionEvent event) {
    	mtc.abrirNovaJanela("/view/ClientesTelaCadastrar.fxml", (Stage) ((Node) event.getTarget()).getScene().getWindow(), false);
    }

    @FXML
    void botaoEditarUsuario(ActionEvent event) {

    }

    @FXML
    void botaoExcluirUsuario(ActionEvent event) {

    	Cliente clienteEspecifico = tabelaInformacoes.getSelectionModel().getSelectedItem();
    	
    	if (clienteEspecifico != null) {
    		
    		FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/view/ClienteTelaExcluir.fxml"));
            
            try {
                loader.load();
            } catch (IOException ex) {
            	alertas.erroNaOperacao();
            }
            
            ClienteTelaExcluirController controllerExcluir =  loader.getController();
            controllerExcluir.receberInformacao(clienteEspecifico);
   
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
    	colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    	colunaCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    	colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    	
    	
    	for (Cliente u :Dados.getListaCliente()) {
    		informacoes.add(u);
    	}
    	
    	observableInformacoes = FXCollections.observableArrayList(informacoes);
		tabelaInformacoes.setItems(observableInformacoes);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarInformacoesTableView();
	}
}
