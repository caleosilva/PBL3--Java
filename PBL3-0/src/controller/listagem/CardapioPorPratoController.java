package controller.listagem;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import model.Cardapio;
import model.Cliente;
import model.facade.GerenciadorDeRelatorio;

/**
 * 
 * @author Caleo Silva e Joao Pedro
 * 
 * Classe responsavel por ser o controller do fxml "CardapioPorTipoDePrato.fxml" e gerar
 * o relatorio com as informacoes de um Prato selecionado.
 *
 */
public class CardapioPorPratoController implements Initializable {

	private ObservableList<String> dadosCardapioGeral;
	private GerenciadorDeRelatorio gdr = new GerenciadorDeRelatorio();
	private AlertasPdf alertasPdf = new AlertasPdf();
	private AlertasGerais alertasGeral = new AlertasGerais();
	private List<String> listaPratosCardapio;
	
    @FXML
    private ChoiceBox<String> choiceBoxCardapio;

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
    	
    	String nomePrato = choiceBoxCardapio.getValue();
    	
    	if (nomePrato != null) {
    		Paragraph paragrafo3 = gdr.tituloRelatorio("RELATORIO DE VENDAS POR PRODUTO");
			PdfPTable tabela3 = gdr.tabelaPorPrato(Dados.getListaVendas(), nomePrato);
			boolean sucesso = gdr.montarPDF(paragrafo3, tabela3, "Relatorio de Vendas");
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
     * Metodo responsavel por carregar os nomes dos pratos dentro da choiceBox.
     */
    public void carregarNomepratos() {
    	listaPratosCardapio = new ArrayList<>();
    	for (Cardapio cardapio : Dados.getListaCardapio() ) {
    		this.listaPratosCardapio.add(cardapio.getNome());
    	}
    	dadosCardapioGeral = FXCollections.observableArrayList(listaPratosCardapio);
    	choiceBoxCardapio.setItems(dadosCardapioGeral);
	}
    
    /**
     * Metodo que sera executado quando a classe for executada.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarNomepratos();
	}
}
