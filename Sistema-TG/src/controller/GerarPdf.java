package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.text.StyleConstants.ColorConstants;

import org.w3c.dom.html.HTMLFieldSetElement;

import com.itextpdf.awt.geom.Rectangle;
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

import model.Data;

import com.itextpdf.text.PageSize;

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

			ResultSet rs = AtiradorDAO.getAtiradoresGeral();
			String id = "0";
			try {

				while (rs.next()) {
					int qtdGuarda = rs.getInt("qtdGuarda") + EscalaDAO.getQtdGuardaAtirador(rs.getInt("id"), new Date());
					
					if (rs.getInt("id") < 10) {
						PdfPCell colid = new PdfPCell(new Paragraph(String.valueOf(id + rs.getInt("id"))));
						tabela.addCell(colid).setHorizontalAlignment(1);
						PdfPCell colnome = new PdfPCell(new Paragraph(rs.getString("nome")));
						tabela.addCell(colnome).setHorizontalAlignment(1);
						PdfPCell colguerra = new PdfPCell(new Paragraph(rs.getString("guerra")));
						tabela.addCell(colguerra).setHorizontalAlignment(1);
						PdfPCell colcargo = new PdfPCell(new Paragraph(rs.getString("cargo")));
						tabela.addCell(colcargo).setHorizontalAlignment(1);
						PdfPCell colqtd = new PdfPCell(new Paragraph(String.valueOf(qtdGuarda)));
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
						PdfPCell colqtd = new PdfPCell(new Paragraph(String.valueOf(qtdGuarda)));
						tabela.addCell(colqtd).setHorizontalAlignment(1);
					}

				}

			} catch (Exception e3) {
				System.out.println(e3);
			}
			document.add(tabela);
			document.add(new Paragraph("     "));
			Paragraph paragrafosgt = new Paragraph(
					"Chefe de Instrução:__________________________________", font2);
			paragrafosgt.setAlignment(Element.ALIGN_BASELINE);
			document.add(paragrafosgt);

			
			
			
			
			
			
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
		Document document = new Document(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream("EscalaSRV.pdf"));

			document.open();

			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, BaseColor.BLACK);
			Font font2 = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
			Font font3 = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.WHITE);
			Paragraph paragrafo1 = new Paragraph("Escala do Tiro de Guerra 01-013", font);
			paragrafo1.setAlignment(Element.ALIGN_CENTER);
			document.add(paragrafo1);
			document.add(new Paragraph("     "));
			Paragraph paragrafo2 = new Paragraph("Semana Atual", font);
			paragrafo2.setAlignment(Element.ALIGN_CENTER);
			document.add(paragrafo2);
			document.add(new Paragraph("     "));

			Date data1 = new Date();
			Date data2 = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

			float[] colsWidth = { 1f, 1f, 1f, 1f, 1f, 1f, 1f };
			PdfPTable tabela = new PdfPTable(colsWidth);
			Paragraph D = new Paragraph("Domingo", font2);
			PdfPCell coll1 = new PdfPCell(D);
			tabela.addCell(coll1).setHorizontalAlignment(1);
			PdfPCell coll2 = new PdfPCell(new Paragraph("Segunda", font2));
			tabela.addCell(coll2).setHorizontalAlignment(1);
			PdfPCell coll3 = new PdfPCell(new Paragraph("Terça", font2));
			tabela.addCell(coll3).setHorizontalAlignment(1);
			PdfPCell coll4 = new PdfPCell(new Paragraph("Quarta", font2));
			tabela.addCell(coll4).setHorizontalAlignment(1);
			PdfPCell coll5 = new PdfPCell(new Paragraph("Quinta", font2));
			tabela.addCell(coll5).setHorizontalAlignment(1);
			PdfPCell coll6 = new PdfPCell(new Paragraph("Sexta", font2));
			tabela.addCell(coll6).setHorizontalAlignment(1);
			PdfPCell coll7 = new PdfPCell(new Paragraph("Sábado", font2));
			tabela.addCell(coll7).setHorizontalAlignment(1);

			data1 = Data.primeiroDiaSemana(data1);

			float[] colsWidth1 = { 1f, 1f, 1f, 1f, 1f, 1f, 1f };
			PdfPTable tabela1 = new PdfPTable(colsWidth1);
			PdfPCell D1 = new PdfPCell(new Paragraph(formato.format(data1), font2));
			tabela1.addCell(D1).setHorizontalAlignment(1);
			PdfPCell D2 = new PdfPCell(new Paragraph(formato.format(Data.addDias(data1, 1)), font2));
			tabela1.addCell(D2).setHorizontalAlignment(1);
			PdfPCell D3 = new PdfPCell(new Paragraph(formato.format(Data.addDias(data1, 2)), font2));
			tabela1.addCell(D3).setHorizontalAlignment(1);
			PdfPCell D4 = new PdfPCell(new Paragraph(formato.format(Data.addDias(data1, 3)), font2));
			tabela1.addCell(D4).setHorizontalAlignment(1);
			PdfPCell D5 = new PdfPCell(new Paragraph(formato.format(Data.addDias(data1, 4)), font2));
			tabela1.addCell(D5).setHorizontalAlignment(1);
			PdfPCell D6 = new PdfPCell(new Paragraph(formato.format(Data.addDias(data1, 5)), font2));
			tabela1.addCell(D6).setHorizontalAlignment(1);
			PdfPCell D7 = new PdfPCell(new Paragraph(formato.format(Data.addDias(data1, 6)), font2));
			tabela1.addCell(D7).setHorizontalAlignment(1);



			float[] colsWidth2 = { 1f, 1f, 1f, 1f, 1f, 1f, 1f };
			PdfPTable tabela2 = new PdfPTable(colsWidth2);
			String[] colunas = { formato.format(data1), formato.format(Data.addDias(data1, 1)),
					formato.format(Data.addDias(data1, 2)), formato.format(Data.addDias(data1, 3)),
					formato.format(Data.addDias(data1, 4)), formato.format(Data.addDias(data1, 5)),
					formato.format(Data.addDias(data1, 6)) };

			listarAtiradores(colunas, data1, tabela2);
			
			document.add(tabela);
			document.add(tabela1);
			document.add(tabela2);
			
			document.add(new Paragraph("     "));
			Paragraph paragrafo3 = new Paragraph("Próxima Semana", font);
			paragrafo3.setAlignment(Element.ALIGN_CENTER);
			document.add(paragrafo3);
			document.add(new Paragraph("     "));
			
			float[] colsWidth3 = { 1f, 1f, 1f, 1f, 1f, 1f, 1f };
			PdfPTable tabela3 = new PdfPTable(colsWidth);
			Paragraph Dia3 = new Paragraph("Domingo", font2);
			PdfPCell colun1 = new PdfPCell(Dia3);
			tabela3.addCell(colun1).setHorizontalAlignment(1);
			PdfPCell colun2 = new PdfPCell(new Paragraph("Segunda", font2));
			tabela3.addCell(colun2).setHorizontalAlignment(1);
			PdfPCell colun3 = new PdfPCell(new Paragraph("Terça", font2));
			tabela3.addCell(colun3).setHorizontalAlignment(1);
			PdfPCell colun4 = new PdfPCell(new Paragraph("Quarta", font2));
			tabela3.addCell(colun4).setHorizontalAlignment(1);
			PdfPCell colun5 = new PdfPCell(new Paragraph("Quinta", font2));
			tabela3.addCell(colun5).setHorizontalAlignment(1);
			PdfPCell colun6 = new PdfPCell(new Paragraph("Sexta", font2));
			tabela3.addCell(colun6).setHorizontalAlignment(1);
			PdfPCell colun7 = new PdfPCell(new Paragraph("Sábado", font2));
			tabela3.addCell(colun7).setHorizontalAlignment(1);
			
			data2 = Data.diaProximaSemana(data2);

			
			float[] colsWidth4 = { 1f, 1f, 1f, 1f, 1f, 1f, 1f };
			PdfPTable tabela4 = new PdfPTable(colsWidth1);
			PdfPCell di1 = new PdfPCell(new Paragraph(formato.format(data2), font2));
			tabela4.addCell(di1).setHorizontalAlignment(1);
			PdfPCell di2 = new PdfPCell(new Paragraph(formato.format(Data.addDias(data2, 1)), font2));
			tabela4.addCell(di2).setHorizontalAlignment(1);
			PdfPCell di3 = new PdfPCell(new Paragraph(formato.format(Data.addDias(data2, 2)), font2));
			tabela4.addCell(di3).setHorizontalAlignment(1);
			PdfPCell di4 = new PdfPCell(new Paragraph(formato.format(Data.addDias(data2, 3)), font2));
			tabela4.addCell(di4).setHorizontalAlignment(1);
			PdfPCell di5 = new PdfPCell(new Paragraph(formato.format(Data.addDias(data2, 4)), font2));
			tabela4.addCell(di5).setHorizontalAlignment(1);
			PdfPCell di6 = new PdfPCell(new Paragraph(formato.format(Data.addDias(data2, 5)), font2));
			tabela4.addCell(di6).setHorizontalAlignment(1);
			PdfPCell di7 = new PdfPCell(new Paragraph(formato.format(Data.addDias(data2, 6)), font2));
			tabela4.addCell(di7).setHorizontalAlignment(1);
			
			
			float[] colsWidth5 = { 1f, 1f, 1f, 1f, 1f, 1f, 1f };
			PdfPTable tabela5 = new PdfPTable(colsWidth2);
			String[] colunas5 = { formato.format(data2), formato.format(Data.addDias(data2, 1)),
					formato.format(Data.addDias(data2, 2)), formato.format(Data.addDias(data2, 3)),
					formato.format(Data.addDias(data2, 4)), formato.format(Data.addDias(data2, 5)),
					formato.format(Data.addDias(data2, 6)) };

			listarAtiradores(colunas5, data2, tabela5);
			
			document.add(tabela3);
			document.add(tabela4);
			document.add(tabela5);
			document.add(new Paragraph("     "));
			Paragraph paragrafosgt = new Paragraph(
					"Chefe de Instrução:___________________________________________________________________", font2);
			paragrafosgt.setAlignment(Element.ALIGN_BASELINE);
			document.add(paragrafosgt);
			document.add(new Paragraph("     "));
			Paragraph instrucao = new Paragraph("Instruções para o uso das Escala:", font2);
			instrucao.setAlignment(Element.ALIGN_CENTER);
			document.add(instrucao);
			document.add(new Paragraph("     "));
			float[] colsWidth6 = {1f, 1f, 1f};
			PdfPTable tabela6 = new PdfPTable(colsWidth6);
			PdfPCell red = new PdfPCell(new Paragraph("Feriado/Escala Vermelha", font2));
			red.setBackgroundColor(BaseColor.RED);
			tabela6.addCell(red);
			PdfPCell black = new PdfPCell(new Paragraph("Preta", font3));
			black.setBackgroundColor(BaseColor.BLACK);
			tabela6.addCell(black);
			PdfPCell folga = new PdfPCell(new Paragraph("X = Folga", font2));
			folga.setBackgroundColor(BaseColor.WHITE);
			tabela6.addCell(folga);
			document.add(tabela6);
			
		
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
	
	private static void listarAtiradores(String[] colunas, Date data1, PdfPTable tabela2) {
		ResultSet rs = EscalaDAO.getEscalaSemana(data1);
		ResultSet rs1 = FolgaDAO.getFolgasSemana(data1);
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Font font2 = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
		Font font3 = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.WHITE);
		
		int[] monitores = new int[7];
		int[] atiradores1 = new int[7];
		int[] atiradores2 = new int[7];
		int[] atiradores3 = new int[7];
		Date[] datas = new Date[7];
		Date[] datasFolga = new Date[7];

		int m = 0;

		try {
			while (rs.next()) {
				datas[m] = rs.getDate("data");
				monitores[m] = rs.getInt("monitorId");
				atiradores1[m] = rs.getInt("atirador1Id");
				atiradores2[m] = rs.getInt("atirador2Id");
				atiradores3[m] = rs.getInt("atirador3Id");
				m++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		m = 0;

		try {
			while (rs1.next()) {
				datasFolga[m] = rs1.getDate("data");
				m++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (datas[0] != null) {
			String[] linha = new String[7];
			int correcao = 0;
			int j = 0;
			int f = 0;

			Date dataRecente = null;
			if (datas[0] != null && datasFolga[0] != null) {
				dataRecente = Data.dataMaisRecente(datas[0], datasFolga[0]);
			} else if (datas[0] != null) {
				dataRecente = datas[0];
			} else {
				dataRecente = datasFolga[0];
			}

			boolean folga = false;
			switch (Data.getDiaSemana(dataRecente)) {
			case "DOM":
				// Monitores
				for (int i = 0; i < 7; i++) {
					if (monitores[j] == 0) {

						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}

						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor

						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}

						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 1
				correcao = 0;
				j = 0;
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (atiradores1[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 2
				correcao = 0;
				j = 0;
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (atiradores2[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 3
				correcao = 0;
				j = 0;
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (atiradores3[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);
				break;

			case "SEG":
				// Monitores
				for (int i = 0; i < 7; i++) {
					if (i < 1) {
						linha[i] = "";
						continue;
					} else if (monitores[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 1
				correcao = 0;
				j = 0;
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 1) {
						linha[i] = "";
						continue;
					} else if (atiradores1[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 2
				correcao = 0;
				j = 0;
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 1) {
						linha[i] = "";
						continue;
					} else if (atiradores2[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 3
				correcao = 0;
				j = 0;
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 1) {
						linha[i] = "";
						continue;
					} else if (atiradores3[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);
				break;

			case "TER":
				// Monitores
				for (int i = 0; i < 7; i++) {
					if (i < 2) {
						linha[i] = "";
						continue;
					} else if (monitores[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 1
				correcao = 0;
				j = 0;
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 2) {
						linha[i] = "";
						continue;
					} else if (atiradores1[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 2
				correcao = 0;
				j = 0;
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 2) {
						linha[i] = "";
						continue;
					} else if (atiradores2[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 3
				correcao = 0;
				j = 0;
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 2) {
						linha[i] = "";
						continue;
					} else if (atiradores3[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				break;

			case "QUA":
				// Monitores
				for (int i = 0; i < 7; i++) {
					if (i < 3) {
						linha[i] = "";
						continue;
					} else if (monitores[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 1
				correcao = 0;
				j = 0;
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 3) {
						linha[i] = "";
						continue;
					} else if (atiradores1[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 2
				correcao = 0;
				j = 0;
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 3) {
						linha[i] = "";
						continue;
					} else if (atiradores2[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 3
				correcao = 0;
				j = 0;
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 3) {
						linha[i] = "";
						continue;
					} else if (atiradores3[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				break;

			case "QUI":
				// Monitores
				for (int i = 0; i < 7; i++) {
					if (i < 4) {
						linha[i] = "";
						continue;
					} else if (monitores[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 1
				correcao = 0;
				j = 0;
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 4) {
						linha[i] = "";
						continue;
					} else if (atiradores1[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 2
				correcao = 0;
				j = 0;
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 4) {
						linha[i] = "";
						continue;
					} else if (atiradores2[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 3
				correcao = 0;
				j = 0;
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 4) {
						linha[i] = "";
						continue;
					} else if (atiradores3[j] == 0) {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
							j++;
						} else {
							linha[i] = "";
							j++;
						}
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[j]);
						j++;
						continue;
					} else if (correcao > 0 && formato.format(datas[j]).equals(colunas[i])) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[correcao]);
						correcao = 0;
						j++;
						continue;
					} else {
						linha[i] = "";
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				break;

			case "SEX":
				for (int i = 0; i < 7; i++) {
					if (i < 5) {
						linha[i] = "";

					} else if (monitores[i - 5] == 0) {

						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}

					} else {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = AtiradorDAO.getGuerraAtirador(monitores[i - 5]);
						}
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 1
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 5) {
						linha[i] = "";
					} else if (atiradores1[i - 5] == 0) {

						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}

					} else {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[i - 5]);
						}
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 2
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 5) {
						linha[i] = "";
					} else if (atiradores2[i - 5] == 0) {

						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}

					} else {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[i - 5]);
						}
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 3
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 5) {
						linha[i] = "";
					} else if (atiradores3[i - 5] == 0) {

						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}

					} else {
						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[i - 5]);
						}
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				break;

			case "SAB":
				for (int i = 0; i < 7; i++) {
					if (i < 6) {
						linha[i] = "";
					} else if (monitores[i - 6] == 0) {

						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}

					} else {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[i - 6]);
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 1
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 6) {
						linha[i] = "";
					} else if (atiradores1[i - 6] == 0) {

						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}

					} else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[i - 6]);
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 2
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 6) {
						linha[i] = "";
					} else if (atiradores2[i - 6] == 0) {

						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}

					} else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[i - 6]);
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				// Atiradores 3
				f = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 6) {
						linha[i] = "";
					} else if (atiradores3[i - 6] == 0) {

						folga = false;
						f = 0;
						while (datasFolga[f] != null) {
							if (formato.format(datasFolga[f]).equals(colunas[i])) {
								folga = true;
								break;
							}
							f++;
						}

						if (folga) {
							linha[i] = "X";
						} else {
							linha[i] = "";
						}

					} else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[i - 6]);
					}
				}
				adicionarLinha(tabela2, linha, font3, datas, font2);

				break;
			}
		}
	}

	private static void adicionarLinha(PdfPTable tabela, String[] linha, Font fonte1, Date[] datas, Font fonte2) {
		int j = 0;
		for (String celula : linha) {
			PdfPCell c = null;
			if (datas[j] != null && celula != "" && celula != "X") {
				c = new PdfPCell(new Paragraph(celula, fonte1));
				String dia = Data.getDiaSemana(datas[j]);
				if (dia.equals("DOM") || dia.equals("SAB")) {
					c.setBackgroundColor(BaseColor.RED);
				} else if (dia.equals("SEG") || dia.equals("TER") || dia.equals("QUA") || dia.equals("QUI")
						|| dia.equals("SEX")) {
					ResultSet rs = FeriadoDAO.getFeriadosSemana(datas[0]);

					try {
						boolean feriado = false;
						while (rs.next()) {
							if (datas[j].equals(rs.getDate("data"))) {
								c.setBackgroundColor(BaseColor.RED);
								feriado = true;
								break;
							}
						}
						if (!feriado) {
							c.setBackgroundColor(BaseColor.BLACK);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
				j++;
			}
			else {
				c = new PdfPCell(new Paragraph(celula, fonte2));
			}
			
			tabela.addCell(c).setHorizontalAlignment(1);
		}
	}

}
