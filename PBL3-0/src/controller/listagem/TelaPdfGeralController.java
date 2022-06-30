package controller.listagem;

import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfPTable;

import alertas.AlertasPdf;
import bancoDeDados.Dados;
import controller.views.MudarTelaController;
import excecoes.SemDadosParaPdf;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.facade.GerenciadorDeRelatorio;

/**
 * 
 * @author Caleo Silva e Joao Pedro
 * 
 * Classe responsavel por ser o controller do fxml "TelaPdfGeral.fxml" e gerar os relatorios
 * disponibilzados no sistema.
 *
 */
public class TelaPdfGeralController {
	
	private GerenciadorDeRelatorio gdr = new GerenciadorDeRelatorio();
	private AlertasPdf alertasPdf = new AlertasPdf();
	private MudarTelaController mtc = new MudarTelaController();
	
	/**
	 * Metodo responsavel por gerar o relatorio do estoque total.
	 * @param event Evento gerado pelo usuario.
	 */
    @FXML
    void estoqueProduto(ActionEvent event) {
    	Paragraph paragrafo = gdr.tituloRelatorio("Estoque - Quantidade por produto");			
		PdfPTable tabela = gdr.tabelaQuantidadePorProduto(Dados.getListaProdutosGeral());
		boolean sucesso = gdr.montarPDF(paragrafo, tabela, "Quantidade-Por-Produto");
		
		if(sucesso) alertasPdf.alertaPdfSucesso();
		else alertasPdf.alertaPdfErro();
    }
    
    /**
	 * Metodo responsavel por gerar o relatorio com a quantidade total de produtos no estoque.
	 * @param event Evento gerado pelo usuario.
	 */
    @FXML
    void estoqueTotal(ActionEvent event) {
    	Paragraph paragrafo = gdr.tituloRelatorio("Estoque - Quantidade total");
		PdfPTable tabela = gdr.tabelaQuantidadeTotalDoEstoque(Dados.getListaProdutosGeral());
		boolean sucesso = gdr.montarPDF(paragrafo, tabela, "Quantidade-Total-Estoque");
		
		if(sucesso) alertasPdf.alertaPdfSucesso();
		else alertasPdf.alertaPdfErro();
		
    }
    
    /**
	 * Metodo responsavel por gerar o relatorio com os produto proximos ao vencimento.
	 * @param event Evento gerado pelo usuario.
	 */
    @FXML
    void estoqueVencimento(ActionEvent event) {
    	Paragraph paragrafo = gdr.tituloRelatorio("Estoque - Produtos próximo do vencimento");			
		
    	PdfPTable tabela;
		try {
			tabela = gdr.tabelaProdutosPertosDeVencer(Dados.getListaProdutosGeral());
			
			boolean sucesso = gdr.montarPDF(paragrafo, tabela, "Quantidade-Por-Produto");
			
			if(sucesso) alertasPdf.alertaPdfSucesso();
			else alertasPdf.alertaPdfErro();
			
		} catch (SemDadosParaPdf e) {
			alertasPdf.alertaPdfErro();
		} catch (ClassCastException cce) {
			alertasPdf.alertaPdfErro();
		}
    }
    
    /**
   	 * Metodo responsavel por gerar o relatorio com os fornecedores cadastrados no sistema.
   	 * @param event Evento gerado pelo usuario.
   	 */
    @FXML
    void fornecedoresGeral(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
    	mtc.abrirNovaJanela("/view/listagem/FornecedorPorFornecedor.fxml", stage, false);

    }

    /**
   	 * Metodo responsavel por gerar o relatorio com os fornecedores de um produto em especifico.
   	 * @param event Evento gerado pelo usuario.
   	 */
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
