package controller.listagem;

import java.net.URL;
import java.util.ResourceBundle;

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
import model.facade.GerenciadorDeRelatorio;

/**
 * 
 * @author Caleo Silva e Joao Pedro
 * 
 * Classe responsavel por ser o controller do fxml "FornecedorPorFornecedor.fxml" e gerar
 * o relatorio com as informacoes de um fornecedor em especifico.
 *
 */
public class FornecedorPorFornecedorController implements Initializable {
	
	private ObservableList<Fornecedor> dadosFornecedor;
	private GerenciadorDeRelatorio gdr = new GerenciadorDeRelatorio();
	private AlertasPdf alertasPdf = new AlertasPdf();
	private AlertasGerais alertasGeral = new AlertasGerais();
	
    @FXML
    private ChoiceBox<Fornecedor> choiceBoxFornecedor;

    @FXML
    void botaoCancelar(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoGerarRelatorio(ActionEvent event) {
    	
    	Fornecedor fornecedor = choiceBoxFornecedor.getValue();
    	
    	if (fornecedor != null) {
    		Paragraph paragrafo = gdr.tituloRelatorio("Produtos distribuidos por um fornecedor");
    		PdfPTable tabela = gdr.tabelaFornecedorPorFornecedor(fornecedor);
    		boolean sucesso = gdr.montarPDF(paragrafo, tabela, "Relatorio-Produto-Por-Fornecedor" );
    		
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarNomeProdutos();
		
	}
	
	public void carregarNomeProdutos() {
		dadosFornecedor = FXCollections.observableArrayList(Dados.getListaFornecedor());
		choiceBoxFornecedor.setItems(dadosFornecedor);
	}

}
