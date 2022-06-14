package controller.views;

import bancoDeDados.Dados;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.ProdutoEspecifico;
import model.ProdutoGeral;

public class UsuarioTelaGeralController {

    @FXML
    private TableColumn<String, String> colunaId;

    @FXML
    private TableColumn<String, String> colunaLogin;

    @FXML
    private TableColumn<String, String> colunaSenha;

    @FXML
    private AnchorPane painelPrincipal;

    @FXML
    private TableView<String> tabelaInformacoes;

    @FXML
    void botaoAtualizarTabela(ActionEvent event) {

    }

    @FXML
    void botaoCadastrarUsuario(ActionEvent event) {

    }

    @FXML
    void botaoEditarUsuario(ActionEvent event) {

    }

    @FXML
    void botaoExcluirUsuario(ActionEvent event) {

    }
    
    
    
    public void carregarInformacoesTableView() {
		
    	colunaId.setCellValueFactory(new PropertyValueFactory<>("fornecedorToString"));
    	colunaLogin.setCellValueFactory(new PropertyValueFactory<>("id"));
    	colunaSenha.setCellValueFactory(new PropertyValueFactory<>("nome"));;
		
//		for (ProdutoGeral pg : Dados.getListaProdutosGeral()){
//			for (ProdutoEspecifico pe : pg.getListaDeProdutos()) {
//				pe.setNome(pg.getNome());
//				informacoes.add(pe);
//			}
//		}
//		observableInformacoes = FXCollections.observableArrayList(informacoes);
//		tabelaInformacoes.setItems(observableInformacoes);
	}

}
