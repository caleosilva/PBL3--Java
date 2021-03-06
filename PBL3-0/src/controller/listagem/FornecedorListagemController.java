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
import model.Fornecedor;

/**
 * 
 * @author Caleo Silva e Joao Pedro
 * 
 * Classe responsavel por ser o controller do fxml "FornecedorListagem.fxml" e exibir na tela
 * a listagem das informacoes relacionadas ao objeto do tipo "Fornecedor"
 *
 */
public class FornecedorListagemController implements Initializable{
	
	private List<Fornecedor> informacoes = new ArrayList<>();
    private ObservableList<Fornecedor> observableInformacoes = null;

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
    
    /**
     * Metodo responsavel por carregar as informacoes na tableview.
     */
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
    
    /**
     * Metodo que sera ativado assim que a classe for iniciada.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarInformacoesTableView();
	}

}
