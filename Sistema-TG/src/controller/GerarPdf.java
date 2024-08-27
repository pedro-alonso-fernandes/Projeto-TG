package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.text.StyleConstants.ColorConstants;

import org.w3c.dom.html.HTMLFieldSetElement;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabStop;
import com.itextpdf.text.TabStop.Alignment;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GerarPdf {
	public GerarPdf() {

		Document document = new Document();
		// gera o pdf
		try {

			PdfWriter.getInstance(document, new FileOutputStream("Atiradores.pdf"));

			document.open();

			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.DATE_FIELD);
			String Data1 = "Data: ";
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, BaseColor.BLACK);
			Font font2 = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
			Image imagem = Image.getInstance(GerarPdf.class.getResource("/model/images/Ex.png"));
			Paragraph paragrafo1 = new Paragraph("Todos os Atiradores do Tiro de Guerra", font);
			imagem.scalePercent(75);
			paragrafo1.add(imagem);
			paragrafo1.setAlignment(Element.ALIGN_CENTER);
			document.add(paragrafo1);
			document.add(new Paragraph("     "));
			document.add(new Paragraph(Data1 + formatador.format(data), font));
			document.add(new Paragraph("     "));
			Paragraph paragrafo2 = new Paragraph("Atiradores Cadastrados", font);
			paragrafo2.setAlignment(Element.ALIGN_CENTER);
			document.add(paragrafo2);
			document.add(new Paragraph("     "));
			document.add(new Paragraph("     "));
			document.add(new Paragraph("     "));
			// TABELA Atiradores

			float[] colsWidth = { 0.5f, 3.5f, 1.3f, 1f, 1f };
			PdfPTable tabela = new PdfPTable(colsWidth);
			Paragraph paraid = new Paragraph("ID", font2);
			PdfPCell coll1 = new PdfPCell(paraid);
			tabela.addCell(coll1).setHorizontalAlignment(1);
			PdfPCell coll2 = new PdfPCell(new Paragraph("Nome", font2));
			tabela.addCell(coll2).setHorizontalAlignment(1);
			PdfPCell coll3 = new PdfPCell(new Paragraph("Nome de Guerra", font2));
			tabela.addCell(coll3).setHorizontalAlignment(1);
			PdfPCell coll4 = new PdfPCell(new Paragraph("Cargo", font2));
			tabela.addCell(coll4).setHorizontalAlignment(1);
			PdfPCell coll5 = new PdfPCell(new Paragraph("Qtd Guardas", font2));
			tabela.addCell(coll5).setHorizontalAlignment(1);

			ResultSet rs = AtiradorDAO.getAtiradores();
			String id = "0";
			String qtd = "0";
			try {

				while (rs.next()) {
					if (rs.getInt("id") < 10) {
						PdfPCell colid = new PdfPCell(new Paragraph(String.valueOf(id + rs.getInt("id"))));
						tabela.addCell(colid).setHorizontalAlignment(1);
						PdfPCell colnome = new PdfPCell(new Paragraph(rs.getString("nome")));
						tabela.addCell(colnome).setHorizontalAlignment(1);
						PdfPCell colguerra = new PdfPCell(new Paragraph(rs.getString("guerra")));
						tabela.addCell(colguerra).setHorizontalAlignment(1);
						PdfPCell colcargo = new PdfPCell(new Paragraph(rs.getString("cargo")));
						tabela.addCell(colcargo).setHorizontalAlignment(1);
						PdfPCell colqtd = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("qtdGuarda"))));
						tabela.addCell(colqtd).setHorizontalAlignment(1);
					} else {
						PdfPCell colid = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("id"))));
						tabela.addCell(colid).setHorizontalAlignment(1);
						PdfPCell colnome = new PdfPCell(new Paragraph(rs.getString("nome")));
						tabela.addCell(colnome).setHorizontalAlignment(1);
						PdfPCell colguerra = new PdfPCell(new Paragraph(rs.getString("guerra")));
						tabela.addCell(colguerra).setHorizontalAlignment(1);
						PdfPCell colcargo = new PdfPCell(new Paragraph(rs.getString("cargo")));
						tabela.addCell(colcargo).setHorizontalAlignment(1);
						PdfPCell colqtd = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("qtdGuarda"))));
						tabela.addCell(colqtd).setHorizontalAlignment(1);
					}

				}

			} catch (Exception e3) {
				System.out.println(e3);
			}

			document.add(tabela);

			// Paragraph paragrafosgt = new Paragraph("Chefe de Instrução:" +
			// (String.valueOf(id + rs.getInt("nome"))), font2);
			// paragrafosgt.setAlignment(Element.ALIGN_BASELINE);
			// document.add(paragrafosgt);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			document.close();
		}

		// abre o pdf no leitor padrão
		try {
			Desktop.getDesktop().open(new File("Atiradores.pdf"));
		} catch (Exception e2) {
			System.out.println(e2);
		}

	}


	public static void GerarPdfEscala() {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("EscalaSRV.pdf"));

			document.open();
			
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.DATE_FIELD);
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, BaseColor.BLACK);
			Font font2 = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
			Paragraph paragrafo1 = new Paragraph("Escala do Tiro de Guerra", font);
			paragrafo1.setAlignment(Element.ALIGN_CENTER);
			document.add(paragrafo1);
			
			
			
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			document.close();
		}

		// abre o pdf no leitor padrão
		try {
			Desktop.getDesktop().open(new File("EscalaSRV.pdf"));
		} catch (Exception e2) {
			System.out.println(e2);
		}
	}
		
	}

