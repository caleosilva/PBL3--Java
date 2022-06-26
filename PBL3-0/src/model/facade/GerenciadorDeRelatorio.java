package model.facade;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfTable;

import bancoDeDados.Dados;
import excecoes.SemDadosParaPdf;
import model.Fornecedor;
import model.ProdutoEspecifico;
import model.ProdutoGeral;
import model.Vendas;

public class GerenciadorDeRelatorio{
	
	// Fontes utilizadas:
	private static Font fonteCabecalho = new Font(Font.FontFamily.COURIER, 18, Font.BOLD);
	private static Font negritoPequena = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
	private static Font fonteVermelha = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	
	// Tabelas:
	
	//Fornecedor:
	public PdfPTable tabelaFornecedorPorProduto(ProdutoGeral produtoGeral) {
		
		try {
			
			// HashMap null:
			if (produtoGeral == null) {
				return null;
			}
			// Criando a tabela:
			
			// Definindo largura das colunas:
			float larguraCol[] = {0.4f, 0.2f, 0.2f, 0.2f};
			
			PdfPTable tabela = new PdfPTable(larguraCol);

			PdfPCell header = new PdfPCell();
			
			// Parte 1 da Tabela - Produto:
			linhaCompletaCorCinza(header, tabela, 4, "NOME DO PRODUTO");
			
			//PdfPCell linha, PdfPTable tabela, int numeroColunas, String mensagem
			linhaCompletaSemCor(header, tabela, 4, produtoGeral.getNome());
			
			// Pular uma linha:
			pularLinhaTabela(header, 4, tabela);
			pularLinhaTabela(header, 4, tabela);
			
			// Parte 2 da Tabela - Fornecedor:
			linhaCompletaCorCiano(header, tabela, 4, "FORNECEDORES QUE O DISTRIBUEM");
			
			// Pular uma linha:
			pularLinhaTabela(header, 4, tabela);
			
			// Identificadores:
			PdfPCell celula = new PdfPCell();
			
			linhaSimplesCorCiano(celula, tabela, "NOME");
			
			linhaSimplesCorCiano(celula, tabela, "ID");
			
			linhaSimplesCorCiano(celula, tabela, "CNPJ");
			
			linhaSimplesCorCiano(celula, tabela, "ENDEREÇO");
			
			if (produtoGeral.getListaDeProdutos().size() > 0) {
				// Dados dos fornecedores
				List<String> fornecedoresApresentados = new ArrayList<>();
				
				for(ProdutoEspecifico pe : produtoGeral.getListaDeProdutos()) {
					
					if (!fornecedoresApresentados.contains(pe.getFornecedor().getId())) {
						tabela.addCell(pe.getFornecedor().getNome());
						tabela.addCell(pe.getFornecedor().getId());
						tabela.addCell(pe.getFornecedor().getCnpj());
						tabela.addCell(pe.getFornecedor().getEndereco());
						
						fornecedoresApresentados.add(pe.getFornecedor().getId());
					}
				}
				return tabela;
				
			} else {
				linhaCompletaAvisoSemInformacao(celula, tabela, 4, "Sem dados no sistema!");
				return tabela;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	public PdfPTable tabelaFornecedorPorFornecedor(Fornecedor fornecedor) {
				
		try {
						
			// Fornecedor null:
			if (fornecedor == null) {
				return null;
			}
			
			if (fornecedor.getListaNomeProdutos().size() > 0) {
								
				// Criando a tabela:
				
				// Definindo largura das colunas:
				float larguraCol[] = {0.3f, 0.2f, 0.2f, 0.3f};
				
				PdfPTable tabela = new PdfPTable(larguraCol);

				PdfPCell header = new PdfPCell();
				
				// Parte 1 da Tabela - Dados do fornecedor:
				linhaCompletaCorCinza(header, tabela, 4, "DADOS DO FORNECEDOR");
				
				// Pular uma linha:
				pularLinhaTabela(header, 4, tabela);
				
				// Identificadores:
				PdfPCell celula = new PdfPCell();
				
				linhaSimplesCorCinza(celula, tabela, "NOME");
				linhaSimplesCorCinza(celula, tabela, "ID");
				linhaSimplesCorCinza(celula, tabela, "CNPJ");
				linhaSimplesCorCinza(celula, tabela, "ENDEREÇO");
				
				// Dados do Fornecedor:
				tabela.addCell(fornecedor.getNome());
				tabela.addCell(fornecedor.getId());
				tabela.addCell(fornecedor.getCnpj());
				tabela.addCell(fornecedor.getEndereco());
				
				// Pula uma linha:
				pularLinhaTabela(celula, 4, tabela);
				
				// Parte 2 da Tabela - Nomes do Produto:
				linhaCompletaCorCiano(celula, tabela, 4, "PRODUTOS QUE ELE FORNECE");
				
				List<String> produtosApresentados = new ArrayList<>();
				
				List<String> listaNomesFornecedor = fornecedor.getListaNomeProdutos();
				
				for (int i = 0; i < listaNomesFornecedor.size(); i ++ ) {
					
					if (!produtosApresentados.contains(listaNomesFornecedor.get(i))) {
						linhaCompletaSemCor(celula, tabela, 4, fornecedor.getListaNomeProdutos().get(i));
						
						produtosApresentados.add(fornecedor.getListaNomeProdutos().get(i));
					}
				}
				return tabela;				
			} else {
				return null; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	// Estoque:
	public PdfPTable tabelaQuantidadePorProduto(List<ProdutoGeral> listaDeProdutos) {
		
		try {
			
			// HashMap null:
			if (listaDeProdutos == null || listaDeProdutos.size() == 0) {
				return null;
			}
			
			
			float larguraCol[] = {0.2f, 0.2f, 0.4f, 0.2f};
			
			PdfPTable tabela = new PdfPTable(larguraCol);
			
			PdfPCell header = new PdfPCell();
			
			PdfPCell celula = new PdfPCell();
			
			
			for(ProdutoGeral pg : listaDeProdutos){
				
				linhaCompletaCorCinza(header, tabela, 4, "NOME DO PRODUTO - GERAL");
				
				linhaCompletaSemCor(header, tabela, 4, pg.getNome());
				
				pularLinhaTabela(header, 4, tabela);
				
				linhaCompletaCorCiano(header, tabela, 4, "DADOS DO PRODUTO - ESPECÍFICO");
				
				pularLinhaTabela(header, 4, tabela);
				
				linhaSimplesCorCiano(celula, tabela, "ID");
				linhaSimplesCorCiano(celula, tabela, "PREÇO");			
				linhaSimplesCorCiano(celula, tabela, "VALIDADE");			
				linhaSimplesCorCiano(celula, tabela, "QUANTIDADE");
				
				if (pg.getListaDeProdutos().size() > 0) {
					
					for (ProdutoEspecifico pe : pg.getListaDeProdutos()) {
						
						tabela.addCell(pe.getId());
						
						Double preco = pe.getPreco();
						String precoStr = Double.toString(preco);
						tabela.addCell(precoStr);						
						
						tabela.addCell(pe.getValidade());
						
						String quantidade = Double.toString(pe.getQuantidade());
						tabela.addCell(quantidade);
						
					}
					
				} else {
					
					linhaCompletaAvisoSemInformacao(header, tabela, 4, "SEM INFORMAÇÕES");
					
				}
				
				pularLinhaTabela(header, 4, tabela);
				
			}
			
			return tabela;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public PdfPTable tabelaQuantidadeTotalDoEstoque(List<ProdutoGeral> listaDeProdutos) {
		
		try {
			// Lista null:
			if (listaDeProdutos == null || listaDeProdutos.size() == 0) {
				return null;
			}
			
			int totalKg = 0;
			int totalLitro = 0;
			int totalUnidade = 0;
			
			float larguraCol[] = {0.3f, 0.3f, 0.3f};
			
			PdfPTable tabela = new PdfPTable(larguraCol);
			
			PdfPCell header = new PdfPCell();
			
			PdfPCell celula = new PdfPCell();
			
			linhaCompletaCorCinza(header, tabela, 3, "QUANTIDADE TOTAL DE PRODUTOS NO ESTOQUE (POR TIPO)");
			
			pularLinhaTabela(header, 3, tabela);
			
			linhaSimplesCorCiano(celula, tabela, "QUILOGRAMAS");
			linhaSimplesCorCiano(celula, tabela, "LITROS");			
			linhaSimplesCorCiano(celula, tabela, "UNIDADES");
			
			for(ProdutoGeral pg : listaDeProdutos){
				
				if (pg.getListaDeProdutos().size() > 0) {

					for (ProdutoEspecifico pe : pg.getListaDeProdutos()) {
						
						if (pe.getUnidadeDeMedida() == 1) {
							totalKg += pe.getQuantidade();
						} else if (pe.getUnidadeDeMedida() == 2) {
							totalLitro += pe.getQuantidade();
						} else if (pe.getUnidadeDeMedida() == 3) {
							totalUnidade += pe.getQuantidade();
						}
					}
				}
			}
			tabela.addCell(Integer.toString(totalKg));
			tabela.addCell(Integer.toString(totalLitro));
			tabela.addCell(Integer.toString(totalUnidade));
						
			return tabela;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	public PdfPTable tabelaProdutosPertosDeVencer(List<ProdutoGeral> listaDeProdutos) throws SemDadosParaPdf{
		
		if (listaDeProdutos == null || listaDeProdutos.size() == 0) {
			throw new SemDadosParaPdf("Sem dados para gerar o relatório!");
		}
		
		float larguraCol[] = {0.2f, 0.4f, 0.2f, 0.2f};
		
		PdfPTable tabela = new PdfPTable(larguraCol);
		
		PdfPCell header = new PdfPCell();
		
		PdfPCell celula = new PdfPCell();
		
		linhaCompletaCorCinza(header, tabela, 4, "Produtos ordenados pela data de vencimento");

		pularLinhaTabela(header, 4, tabela);
		
		linhaSimplesCorCiano(celula, tabela, "ID");
		linhaSimplesCorCiano(celula, tabela, "NOME");			
		linhaSimplesCorCiano(celula, tabela, "VALIDADE");			
		linhaSimplesCorCiano(celula, tabela, "QUANTIDADE");
		
		
		List<ProdutoEspecifico> dadosOrdenados = new ArrayList<>();
		
		for (ProdutoGeral pg : listaDeProdutos) {
			for (ProdutoEspecifico pe : pg.getListaDeProdutos()) {
				pe.setNome(pg.getNome());
				dadosOrdenados.add(pe);
			}
		}
		dadosOrdenados.sort(Comparator.comparing(ProdutoEspecifico::getDataLocalDate));
		
		for (ProdutoEspecifico pe : dadosOrdenados) {
			tabela.addCell(pe.getId());
			tabela.addCell(pe.getNome());
			tabela.addCell(pe.getValidade());
			
			String quatidadeStr = String.valueOf(pe.getQuantidade());
			tabela.addCell(quatidadeStr);
		}
		
		return tabela;
	}
	
	// Vendas:
	public PdfPTable tabelaVendasGerais(List<Vendas> listaDeVendas) {
		
		try {
			if(listaDeVendas.size() == 0) {
				return null;
			}
			
			float larguraCol[] = {0.1f, 0.15f, 0.15f, 0.23f, 0.17f, 0.2f};
			
			PdfPTable tabela = new PdfPTable(larguraCol);

			PdfPCell header = new PdfPCell();
			
			// Parte 1 da Tabela - HISTORICO DE VENDAS:
			linhaCompletaCorCiano(header, tabela, 6, "HISTORICO DE VENDAS");
			// Pular uma linha:
			pularLinhaTabela(header, 6, tabela);
			
			// Identificadores:
			PdfPCell celula = new PdfPCell();
			

			linhaSimplesCorCinza(celula, tabela, "ID");
			linhaSimplesCorCinza(celula, tabela, "DATA");
			linhaSimplesCorCinza(celula, tabela, "HORARIO");
			linhaSimplesCorCinza(celula, tabela, "ITENS / QUANTIDADE");
			linhaSimplesCorCinza(celula, tabela, "VALOR TOTAL");
			linhaSimplesCorCinza(celula, tabela, "FORMA DE PAGAMENTO");
			
			for (Vendas vendaAtual : listaDeVendas) {
				
				tabela.addCell(vendaAtual.getId());
				tabela.addCell(vendaAtual.getData());
				tabela.addCell(vendaAtual.getHorario());
				
				String itens = null;
				itens = "";
				for(String itemAtual : vendaAtual.getItens().keySet()) {
					itens+= itemAtual + " : " + Double.toString(vendaAtual.getItens().get(itemAtual)) + "\n";
		
				}
				
				tabela.addCell(itens);
				itens = "";
				tabela.addCell(vendaAtual.getPrecoTotal());
				tabela.addCell(vendaAtual.getModoPagamento());
				
			}
			return tabela;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public PdfPTable tabelaPorPrato(List<Vendas> listaDeVendas, String produto) {
		
		try {
			if(listaDeVendas.size() == 0) {
				return null;
			}
			
			float larguraCol[] = {0.1f, 0.15f, 0.15f, 0.23f, 0.17f, 0.2f};
			
			PdfPTable tabela = new PdfPTable(larguraCol);

			PdfPCell header = new PdfPCell();
			
			// Parte 1 da Tabela - HISTORICO DE VENDAS:
			linhaCompletaCorCiano(header, tabela, 6, produto);
			// Pular uma linha:
			pularLinhaTabela(header, 6, tabela);
			
			// Identificadores:
			PdfPCell celula = new PdfPCell();
			

			linhaSimplesCorCinza(celula, tabela, "ID");
			linhaSimplesCorCinza(celula, tabela, "DATA");
			linhaSimplesCorCinza(celula, tabela, "HORARIO");
			linhaSimplesCorCinza(celula, tabela, "ITENS / QUANTIDADE");
			linhaSimplesCorCinza(celula, tabela, "VALOR TOTAL");
			linhaSimplesCorCinza(celula, tabela, "FORMA DE PAGAMENTO");
			
			for (Vendas vendaAtual : listaDeVendas) {
				if (vendaAtual.getItens().containsKey(produto.toLowerCase())) {
					tabela.addCell(vendaAtual.getId());
					tabela.addCell(vendaAtual.getData());
					tabela.addCell(vendaAtual.getHorario());
					
					String itens = null;
					itens = "";
					for(String itemAtual : vendaAtual.getItens().keySet()) {
						itens+= itemAtual + " : " + Double.toString(vendaAtual.getItens().get(itemAtual)) + "\n";
			
					}
					
					tabela.addCell(itens);
					itens = "";
					tabela.addCell(vendaAtual.getPrecoTotal());
					tabela.addCell(vendaAtual.getModoPagamento());
				}
			}
			return tabela;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public PdfPTable tabelaPorPeriodo(List<Vendas> listaDeVendas, String data1, String data2) {
	List<Vendas> listaDeVendasOrganizada = new ArrayList<>();
		try {
			if(listaDeVendas.size() == 0) {
				return null;
			}
		
			
			
			float larguraCol[] = {0.1f, 0.15f, 0.15f, 0.23f, 0.17f, 0.2f};
			
			PdfPTable tabela = new PdfPTable(larguraCol);

			PdfPCell header = new PdfPCell();
			
			// Parte 1 da Tabela - HISTORICO DE VENDAS:
			linhaCompletaCorCiano(header, tabela, 6, data1 + " até " + data2);
			// Pular uma linha:
			pularLinhaTabela(header, 6, tabela);
			
			// Identificadores:
			PdfPCell celula = new PdfPCell();
			

			linhaSimplesCorCinza(celula, tabela, "ID");
			linhaSimplesCorCinza(celula, tabela, "DATA");
			linhaSimplesCorCinza(celula, tabela, "HORARIO");
			linhaSimplesCorCinza(celula, tabela, "ITENS / QUANTIDADE");
			linhaSimplesCorCinza(celula, tabela, "VALOR TOTAL");
			linhaSimplesCorCinza(celula, tabela, "FORMA DE PAGAMENTO");
			
			String[] separaData = data1.split("/");
			String[] separaData2 = data2.split("/");
			
			LocalDate dt1 = LocalDate.of(Integer.parseInt(separaData[2]),Integer.parseInt(separaData[1]), Integer.parseInt(separaData[0]));
			
			LocalDate dt2 = LocalDate.of(Integer.parseInt(separaData2[2]),Integer.parseInt(separaData2[1]), Integer.parseInt(separaData[0]));
			boolean a = false;
			Period periodo  = Period.between(dt1, dt2);

			int valorData = periodo.getDays();
				if (valorData < 0) {
					valorData= valorData * (-1);
					a = true;
				}
				
				for (Vendas vendaAtual : listaDeVendas) {
					String dataAtual = vendaAtual.getData();
					String[] separaData3 = dataAtual.split("/");
					LocalDate dt3 = LocalDate.of(Integer.parseInt(separaData3[2]),Integer.parseInt(separaData3[1]), Integer.parseInt(separaData3[0]));
					
					
		
					if (!a) {
						Period periodo2  = Period.between(dt1, dt3);
		
						if (periodo.getDays() < valorData) {
							
							tabela.addCell(vendaAtual.getId());
							tabela.addCell(vendaAtual.getData());
							tabela.addCell(vendaAtual.getHorario());
							
							String itens = null;
							itens = "";
							for(String itemAtual : vendaAtual.getItens().keySet()) {
								itens+= itemAtual + " : " + Double.toString(vendaAtual.getItens().get(itemAtual)) + "\n";
					
							}
							
							tabela.addCell(itens);
							itens = "";
							tabela.addCell(vendaAtual.getPrecoTotal());
							tabela.addCell(vendaAtual.getModoPagamento());
						}
					}
					else {
						Period periodo2  = Period.between(dt2, dt3);
						
						if (periodo.getDays() < valorData) {
							
							tabela.addCell(vendaAtual.getId());
							tabela.addCell(vendaAtual.getData());
							tabela.addCell(vendaAtual.getHorario());
							
							String itens = null;
							itens = "";
							for(String itemAtual : vendaAtual.getItens().keySet()) {
								itens+= itemAtual + " : " + Double.toString(vendaAtual.getItens().get(itemAtual)) + "\n";
					
							}
							
							tabela.addCell(itens);
							itens = "";
							tabela.addCell(vendaAtual.getPrecoTotal());
							tabela.addCell(vendaAtual.getModoPagamento());
						}
							
						}
					}
			
			
			return tabela;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Métodos gerais:
	public boolean montarPDF(Paragraph paragrafo, PdfPTable tabelaInformacoes, String nomeDoArquivo) {
		try {
			// Nome do Arquivo:
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dateString = date.format(formatter);
			String nomeArquivo = nomeDoArquivo + "[" + dateString.replace('/', '-') + "].pdf"; 
			
			// Inicia o documento:
			Document documento = new Document();
			PdfWriter.getInstance(documento, new FileOutputStream(nomeArquivo));
			
			documento.open();
			
			Paragraph primeiroParagrago = paragrafo;
			
			if (primeiroParagrago == null) {
				return false;
			}
			
			if (tabelaInformacoes == null) {
				return false;
			}
			
			// Adicionando os elementos:
			documento.add(primeiroParagrago);
			documento.add(tabelaInformacoes);
 
			// Fecha o arquivo após a escrita da mensagem
			documento.close();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Paragraph tituloRelatorio(String mensagem) {
		try {
			
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataStr = date.format(formatter);
			
			Paragraph paragrafo = new Paragraph();
			
			paragrafo.add(new Phrase(mensagem + "\n\n", fonteCabecalho));		
			
			paragrafo.add(new Phrase("Data da geração do relatório: " + dataStr, negritoPequena));
			paragrafo.add(new Paragraph(" "));
			paragrafo.add(new Paragraph(" "));
			
			paragrafo.setAlignment(Element.ALIGN_CENTER);
			
			return paragrafo;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	// Metodos plug and play para fazer as tabelas:
	public static void pularLinhaTabela(PdfPCell linha, int numeroColunas, PdfPTable tabela) {
		
		linha = new PdfPCell(new Paragraph(" "));
		linha.setColspan(numeroColunas);
		linha.setBorder(0);
		tabela.addCell(linha);
	}
	
	public static void linhaCompletaCorCiano(PdfPCell linha, PdfPTable tabela, int numeroColunas, String mensagem) {
		
		linha = new PdfPCell(new Paragraph(mensagem));			
		linha.setHorizontalAlignment(Element.ALIGN_CENTER);
		linha.setColspan(numeroColunas);
		linha.setBackgroundColor(BaseColor.CYAN);
		
		tabela.addCell(linha);
	}
	
	public static void linhaCompletaCorCinza(PdfPCell linha, PdfPTable tabela, int numeroColunas, String mensagem) {
		
		linha = new PdfPCell(new Paragraph(mensagem));			
		linha.setHorizontalAlignment(Element.ALIGN_CENTER);
		linha.setColspan(numeroColunas);
		linha.setBackgroundColor(BaseColor.GRAY);
		
		tabela.addCell(linha);
	}
	
	public static void linhaCompletaSemCor(PdfPCell linha, PdfPTable tabela, int numeroColunas, String mensagem) {
		
		linha = new PdfPCell(new Paragraph(mensagem));			
		linha.setHorizontalAlignment(Element.ALIGN_CENTER);
		linha.setColspan(numeroColunas);
		
		tabela.addCell(linha);
	}
	
	public static void linhaSimplesCorCiano(PdfPCell celula, PdfPTable tabela, String mensagem) {
		
		celula = new PdfPCell(new Phrase(mensagem));
		celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		celula.setBackgroundColor(BaseColor.CYAN);
		
		tabela.addCell(celula);
		
	}
	
	public static void linhaSimplesCorCinza(PdfPCell celula, PdfPTable tabela, String mensagem) {
		
		celula = new PdfPCell(new Phrase(mensagem));
		celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		celula.setBackgroundColor(BaseColor.GRAY);
		
		tabela.addCell(celula);
		
	}
	
	public static void linhaCompletaAvisoSemInformacao(PdfPCell linha, PdfPTable tabela, int numeroColunas, String mensagem) {
		
		linha = new PdfPCell(new Paragraph(mensagem, fonteVermelha));			
		linha.setHorizontalAlignment(Element.ALIGN_CENTER);
		linha.setColspan(numeroColunas);
		
		tabela.addCell(linha);
	}
	
}