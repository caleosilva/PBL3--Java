package controller.listagem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;

import alertas.AlertasGerais;
import alertas.AlertasPdf;
import bancoDeDados.Dados;
import excecoes.InputsIncorretos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import model.facade.GerenciadorDeRelatorio;
import uteis.UteisGeral;

public class VendasPorPeriodoController {
	
	private GerenciadorDeRelatorio gdr = new GerenciadorDeRelatorio();
	private AlertasPdf alertasPdf = new AlertasPdf();
	private UteisGeral uteisGeral = new UteisGeral();
	private AlertasGerais alertasGerais = new AlertasGerais();
	
    @FXML
    private DatePicker campoDataFinal;

    @FXML
    private DatePicker campoDataInicial;

    @FXML
    void botaoCancelar(ActionEvent event) {

    }

    @FXML
    void botaoGerarRelatorio(ActionEvent event) {
    	try {
    		String data1 = uteisGeral.validarData(campoDataFinal);
    		String data2 = uteisGeral.validarData(campoDataInicial);
    		LocalDate dataInicial = LocalDate.parse(data1, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    		LocalDate dataFinal = LocalDate.parse(data2, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    		
    		
    		Paragraph paragrafo2 = gdr.tituloRelatorio("RELATORIO DE VENDAS");
			PdfPTable tabela2 = gdr.tabelaPorPeriodo(Dados.getListaVendas(), dataInicial, dataFinal);
			boolean sucesso = gdr.montarPDF(paragrafo2, tabela2, "Relatorio de Vendas");
			
			if(sucesso) alertasPdf.alertaPdfSucesso();
			else alertasPdf.alertaPdfErro();
			
    	} catch (InputsIncorretos e) {
			alertasGerais.faltaDadosOuIncorretos();
		} catch(NullPointerException npe) {
			System.out.println("Entrou aqui");
		}
 
    }
}
