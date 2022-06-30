package controller.listagem;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.spi.InitialContextFactory;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;

import alertas.AlertasGerais;
import alertas.AlertasPdf;
import bancoDeDados.Dados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.Fornecedor;
import model.ProdutoGeral;
import model.facade.GerenciadorDeRelatorio;

/**
 * 
 * @author Caleo Silva e Joao Pedro
 * 
 * Classe responsavel por ser o controller do fxml "FornecedorPorProduto.fxml" e gerar
 * o relatorio com as informacoes de um produto selecionado.
 *
 */
public class FornecedorPorProdutoController implements Initializable{
	
	private ObservableList<ProdutoGeral> dadosProdutosGeral;
	private GerenciadorDeRelatorio gdr = new GerenciadorDeRelatorio();
	private AlertasPdf alertasPdf = new AlertasPdf();
	private AlertasGerais alertasGeral = new AlertasGerais();


    @FXML
    private ChoiceBox<ProdutoGeral> choiceBoxProduto;
    
    /**
     * Metodo responsavel por fechar a tela em questao. 
     * 
     * @param event Evento gerado pelo usuario.
     */
    @FXML
    void botaoCancelar(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }
    
    /**
     * Metodo responsavel por gerar o relatorio em questao.
     * 
     * @param event
     */
    @FXML
    void botaoGerarRelatorio(ActionEvent event) {
    	ProdutoGeral produto = choiceBoxProduto.getValue();
    	
    	if (produto != null) {
    		Paragraph paragrafo = gdr.tituloRelatorio("Fornecedores de um produto!");
    		PdfPTable tabela = gdr.tabelaFornecedorPorProduto(produto);
    		boolean sucesso = gdr.montarPDF(paragrafo, tabela, "Relatorio-Fornecedor-Por-Produto" );
    		
    		if(sucesso) {
    			alertasPdf.alertaPdfSucesso();
    			Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	    	stage.close();
    		}
    		else alertasPdf.alertaPdfErro();
    	} else {
    		alertasGeral.faltaDadosOuIncorretos();
    	}
    }
    
    /**
     * Metodo responsavel por carregar os nomes dos produtos dentro da choiceBox.
     */
    public void carregarNomeProdutos() {
    	dadosProdutosGeral = FXCollections.observableArrayList(Dados.getListaProdutosGeral());
    	choiceBoxProduto.setItems(dadosProdutosGeral);
	}
    
    /**
     * Metodo que sera executado quando a classe for executada.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarNomeProdutos();
		
	}

}
