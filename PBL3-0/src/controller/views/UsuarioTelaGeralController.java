package controller.views;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import bancoDeDados.Dados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Usuario;

public class UsuarioTelaGeralController implements Initializable {
	
	MudarTelaController mtc = new MudarTelaController();
	private List<Usuario> informacoes = new ArrayList<>();
    private ObservableList<Usuario> observableInformacoes = null;;

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

    }

    @FXML
    void botaoCadastrarUsuario(ActionEvent event) {
    	mtc.abrirNovaJanela("/view/UsuarioTelaCadastrar.fxml", (Stage) ((Node) event.getTarget()).getScene().getWindow());
    }

    @FXML
    void botaoEditarUsuario(ActionEvent event) {

    }

    @FXML
    void botaoExcluirUsuario(ActionEvent event) {

    }
    
    
    
    public void carregarInformacoesTableView() {
		
    	colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	colunaLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
    	colunaSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));;
    	
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
