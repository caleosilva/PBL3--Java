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
import model.Cardapio;

/**
 * 
 * @author Caleo Silva e Joao Pedro
 * 
 * Classe responsavel por ser o controller do fxml "CardapioListagem.fxml" e exibir na tela
 * a listagem das informacoes relacionadas ao objeto do tipo "Cardapio"
 *
 */
public class CardapioListagemController implements Initializable {

	private List<Cardapio> informacoes = new ArrayList<>();
    private ObservableList<Cardapio> observableInformacoes = null;
	
    @FXML
    private TableColumn<Cardapio, String> colunaCategoria;

    @FXML
    private TableColumn<Cardapio, String> colunaDescricao;

    @FXML
    private TableColumn<Cardapio, String> colunaId;

    @FXML
    private TableColumn<Cardapio, String> colunaItens;

    @FXML
    private TableColumn<Cardapio, String> colunaNome;
    
    @FXML
    private TableColumn<Cardapio, String> colunaPreco;

    @FXML
    private AnchorPane painelPrincipal;

    @FXML
    private TableView<Cardapio> tabelaInformacoes;
    
    /**
     * Metodo responsavel por carregar as informacoes na tableview.
     */
    public void carregarInformacoesTableView() {
		
    	colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    	colunaItens.setCellValueFactory(new PropertyValueFactory<>("itensCardapio"));
    	colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
    	colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));;
    	
    	for (Cardapio u :Dados.getListaCardapio()) {
    		informacoes.add(u);
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
