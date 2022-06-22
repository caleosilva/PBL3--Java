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
import model.Usuario;

public class UsuarioListagemController implements Initializable {
	
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarInformacoesTableView();
		
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

}
