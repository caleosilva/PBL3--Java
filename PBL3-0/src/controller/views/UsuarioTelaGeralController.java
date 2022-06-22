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
import model.Usuario;

public class UsuarioTelaGeralController implements Initializable {
	
	AlertasGerais alertas = new AlertasGerais();
	MudarTelaController mtc = new MudarTelaController();
	private List<Usuario> informacoes = new ArrayList<>();
    private ObservableList<Usuario> observableInformacoes = null;;
    
    @FXML
    private TableColumn<Usuario, String> colunaCargo;
    
    @FXML
    private TableColumn<Usuario, String> colunaId;

    @FXML
    private TableColumn<Usuario, String> colunaLogin;

    @FXML
    private TableColumn<Usuario, String> colunaSenha;

    @FXML
    private AnchorPane painelPrincipal;

    @FXML
    private TableView<Usuario> tabelaInformacoes;

    @FXML
    void botaoAtualizarTabela(ActionEvent event) {
    	atualizarTabela();
    }

    @FXML
    void botaoCadastrarUsuario(ActionEvent event) {
    	mtc.abrirNovaJanela("/view/UsuarioTelaCadastrar.fxml", (Stage) ((Node) event.getTarget()).getScene().getWindow(), false);
    }

    @FXML
    void botaoEditarUsuario(ActionEvent event) {
    	Usuario usuarioEspecifico = tabelaInformacoes.getSelectionModel().getSelectedItem();
    	
    	if (usuarioEspecifico != null) {
    		
    		FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/view/UsuarioTelaEditar.fxml"));
            
            try {
                loader.load();
            } catch (IOException ex) {
            	alertas.erroNaOperacao();
            }
            
            UsuarioTelaEditarController controllerEditar = loader.getController();
            controllerEditar.adicionarInformacoes(usuarioEspecifico.getLogin(), usuarioEspecifico.getSenha(), usuarioEspecifico);
            
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
    void botaoExcluirUsuario(ActionEvent event) {
    	Usuario usuarioEspecifico = tabelaInformacoes.getSelectionModel().getSelectedItem();
    	
    	if (usuarioEspecifico != null) {
    		
    		FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/view/UsuarioTelaExcluir.fxml"));
            
            try {
                loader.load();
            } catch (IOException ex) {
            	alertas.erroNaOperacao();
            }
            
            UsuarioTelaExcluirController controllerExcluir =  loader.getController();
            controllerExcluir.receberInformacao(usuarioEspecifico);
            
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
    	colunaLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
    	colunaSenha.setCellValueFactory(new PropertyValueFactory<>("senhaInvisivel"));
    	colunaCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
    	
    	for (Usuario u :Dados.getListaUsuario()) {
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
