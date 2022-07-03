package controller.listagem;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;

import alertas.AlertasGerais;
import alertas.AlertasPdf;
import bancoDeDados.Dados;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.Vendas;
import model.facade.GerenciadorDeRelatorio;

public class NotaFiscalClienteController {
	private GerenciadorDeRelatorio gdr = new GerenciadorDeRelatorio();
	private AlertasPdf alertasPdf = new AlertasPdf();
	private AlertasGerais alertasGeral = new AlertasGerais();
	
    @FXML
    void botaoNao(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	stage.close();
    }

    @FXML
    void botaoSIM(ActionEvent event) {
    	Vendas novaVenda = Dados.getVendaAtual().get(0);
    	if (novaVenda != null) {
    		Paragraph paragrafo1 = gdr.tituloRelatorio("Nota Fiscal");
			PdfPTable tabela1 = gdr.tabelaNotaFiscal(novaVenda);
			boolean sucesso = gdr.montarPDF(paragrafo1, tabela1, "Nota Fiscal - " + novaVenda.getClienteVinculado());
			Dados.getVendaAtual().clear();
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
}