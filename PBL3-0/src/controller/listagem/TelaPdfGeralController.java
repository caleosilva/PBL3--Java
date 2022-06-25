package controller.listagem;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;

import alertas.AlertasPdf;
import bancoDeDados.Dados;
import controller.views.MudarTelaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.facade.GerenciadorDeRelatorio;

public class TelaPdfGeralController {
	
	private GerenciadorDeRelatorio gdr = new GerenciadorDeRelatorio();
	private AlertasPdf alertasPdf = new AlertasPdf();
	private MudarTelaController mtc = new MudarTelaController();

    @FXML
    void estoqueProduto(ActionEvent event) {
    	Paragraph paragrafo = gdr.tituloRelatorio("Estoque - Quantidade por produto");			
		PdfPTable tabela = gdr.tabelaQuantidadePorProduto(Dados.getListaProdutosGeral());
		boolean sucesso = gdr.montarPDF(paragrafo, tabela, "Quantidade-Por-Produto");
		
		if(sucesso) alertasPdf.alertaPdfSucesso();
		else alertasPdf.alertaPdfErro();
    }

    @FXML
    void estoqueTotal(ActionEvent event) {
    	Paragraph paragrafo = gdr.tituloRelatorio("Estoque - Quantidade total");
		PdfPTable tabela = gdr.tabelaQuantidadeTotalDoEstoque(Dados.getListaProdutosGeral());
		boolean sucesso = gdr.montarPDF(paragrafo, tabela, "Quantidade-Total-Estoque");
		
		if(sucesso) alertasPdf.alertaPdfSucesso();
		else alertasPdf.alertaPdfErro();
		
    }

    @FXML
    void estoqueVencimento(ActionEvent event) {

    }

    @FXML
    void fornecedoresGeral(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	mtc.abrirNovaJanela("/view/listagem/FornecedorPorFornecedor.fxml", stage, false);

    }

    @FXML
    void fornecedoresProduto(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	mtc.abrirNovaJanela("/view/listagem/FornecedorPorProduto.fxml", stage, false);
    }

    @FXML
    void vendasPorPeriodo(ActionEvent event) {

    }

    @FXML
    void vendasPorTipoDePrato(ActionEvent event) {

    }

    @FXML
    void vendasRealizadasNoGeral(ActionEvent event) {

    }

}
