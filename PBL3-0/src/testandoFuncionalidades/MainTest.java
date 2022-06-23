package testandoFuncionalidades;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

import com.itextpdf.text.pdf.PdfPTable;

import bancoDeDados.Dados;
import model.facade.*;

public class MainTest {
	public static void main(String[] args) {
		GerenciadorDeRelatorio g = new GerenciadorDeRelatorio();
		
		Paragraph paragrafo1 = g.tituloRelatorio("RELATORIO DE VENDAS");
		
		PdfPTable tabela1 = g.tabelaVendasGerais(Dados.getListaVendas());
		
		g.montarPDF(paragrafo1, tabela1, "Relatorio de Vendas");
		
	}

}
