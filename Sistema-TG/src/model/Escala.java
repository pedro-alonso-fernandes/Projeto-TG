package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.sound.sampled.TargetDataLine;
import javax.swing.table.DefaultTableModel;

import controller.AtiradorDAO;
import controller.Data;
import controller.EscalaDAO;
import view.escala.telaEscala;

public class Escala {

	private int id;
	private Date data;
	private String cor;
	private int monitorId;
	private int atirador1Id;
	private int atirador2Id;
	private int atirador3Id;

	public Escala(Date data, String cor, int monitorId, int atirador1Id, int atirador2Id, int atirador3Id) {
		this.data = data;
		this.cor = cor;
		this.monitorId = monitorId;
		this.atirador1Id = atirador1Id;
		this.atirador2Id = atirador2Id;
		this.atirador3Id = atirador3Id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(int monitorId) {
		this.monitorId = monitorId;
	}

	public int getAtirador1Id() {
		return atirador1Id;
	}

	public void setAtirador1Id(int atirador1Id) {
		this.atirador1Id = atirador1Id;
	}

	public int getAtirador2Id() {
		return atirador2Id;
	}

	public void setAtirador2Id(int atirador2Id) {
		this.atirador2Id = atirador2Id;
	}

	public int getAtirador3Id() {
		return atirador3Id;
	}

	public void setAtirador3Id(int atirador3Id) {
		this.atirador3Id = atirador3Id;
	}

	public static DefaultTableModel getModelSemanaAtual(Date data, String[] colunas) {
		DefaultTableModel modelo = (DefaultTableModel) telaEscala.semanaAtual.getModel();
		modelo = getModel(data, modelo, "semanaAtual", colunas);
		return modelo;
	}

	public static DefaultTableModel getModelProximaSemana(Date data, String[] colunas) {
		DefaultTableModel modelo = (DefaultTableModel) telaEscala.proximaSemana.getModel();
		modelo = getModel(data, modelo, "proximaSemana", colunas);
		return modelo;
	}

	private static DefaultTableModel getModel(Date data, DefaultTableModel modelo, String tabela, String[] colunas) {

		ResultSet rs = EscalaDAO.getEscalaSemana(data);

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		int[] monitores = new int[7];
		int[] atiradores1 = new int[7];
		int[] atiradores2 = new int[7];
		int[] atiradores3 = new int[7];
		Date[] datas = new Date[7];

		try {
			int i = 0;
			while (rs.next()) {

				datas[i] = rs.getDate("data");
				monitores[i] = rs.getInt("monitorId");
				atiradores1[i] = rs.getInt("atirador1Id");
				atiradores2[i] = rs.getInt("atirador2Id");
				atiradores3[i] = rs.getInt("atirador3Id");
				i++;
			}

		} catch (SQLException e) {
			System.out.println("Erro " + e.getMessage());
		}

		if (datas[0] != null) {
			String[] linha = new String[7];
			int correcao = 0;
			int j = 0;

			switch (Data.getDiaSemana(datas[0])) {
			case "DOM":
				// Monitores
				for (int i = 0; i < 7; i++) {
					if (monitores[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[i]);
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
				modelo.addRow(linha);

				// Atiradores 1
				correcao = 0;
				j = 0;
				for (int i = 0; i < 7; i++) {
					if (atiradores1[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[i]);
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
				modelo.addRow(linha);

				// Atiradores 2
				correcao = 0;
				j = 0;
				for (int i = 0; i < 7; i++) {
					if (atiradores2[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[i]);
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
				modelo.addRow(linha);

				// Atiradores 3
				correcao = 0;
				j = 0;
				for (int i = 0; i < 7; i++) {
					if (atiradores3[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
						continue;
					} else if (correcao == 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[i]);
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
				modelo.addRow(linha);
				break;

			case "SEG":
				// Monitores
				for (int i = 0; i < 7; i++) {
					if (i < 1) {
						linha[i] = "";
						continue;
					} else if (monitores[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);

				// Atiradores 1
				correcao = 0;
				j = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 1) {
						linha[i] = "";
						continue;
					} else if (atiradores1[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);

				// Atiradores 2
				correcao = 0;
				j = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 1) {
						linha[i] = "";
						continue;
					} else if (atiradores2[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);

				// Atiradores 3
				correcao = 0;
				j = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 1) {
						linha[i] = "";
						continue;
					} else if (atiradores3[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);
				break;

			case "TER":
				// Monitores
				for (int i = 0; i < 7; i++) {
					if (i < 2) {
						linha[i] = "";
						continue;
					} else if (monitores[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);

				// Atiradores 1
				correcao = 0;
				j = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 2) {
						linha[i] = "";
						continue;
					} else if (atiradores1[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);

				// Atiradores 2
				correcao = 0;
				j = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 2) {
						linha[i] = "";
						continue;
					} else if (atiradores2[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);

				// Atiradores 3
				correcao = 0;
				j = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 2) {
						linha[i] = "";
						continue;
					} else if (atiradores3[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);

				break;

			case "QUA":
				// Monitores
				for (int i = 0; i < 7; i++) {
					if (i < 3) {
						linha[i] = "";
						continue;
					} else if (monitores[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);

				// Atiradores 1
				correcao = 0;
				j = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 3) {
						linha[i] = "";
						continue;
					} else if (atiradores1[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);

				// Atiradores 2
				correcao = 0;
				j = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 3) {
						linha[i] = "";
						continue;
					} else if (atiradores2[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);

				// Atiradores 3
				correcao = 0;
				j = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 3) {
						linha[i] = "";
						continue;
					} else if (atiradores3[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);

				break;

			case "QUI":
				// Monitores
				for (int i = 0; i < 7; i++) {
					if (i < 4) {
						linha[i] = "";
						continue;
					} else if (monitores[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);

				// Atiradores 1
				correcao = 0;
				j = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 4) {
						linha[i] = "";
						continue;
					} else if (atiradores1[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);

				// Atiradores 2
				correcao = 0;
				j = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 4) {
						linha[i] = "";
						continue;
					} else if (atiradores2[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);

				// Atiradores 3
				correcao = 0;
				j = 0;
				for (int i = 0; i < 7; i++) {
					if (i < 4) {
						linha[i] = "";
						continue;
					} else if (atiradores3[j] == 0) {
						linha[i] = "";
						j++;
						continue;
					} else if (!formato.format(datas[j]).equals(colunas[i]) && correcao == 0) {
						correcao = j;
						// J vai continuar com o seu valor
						linha[i] = "";
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
				modelo.addRow(linha);

				break;

			case "SEX":
				for (int i = 0; i < 7; i++) {
					if (i < 5 || monitores[i - 5] == 0) {
						linha[i] = "";
					} else {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[i - 5]);
					}
				}
				modelo.addRow(linha);

				for (int i = 0; i < 7; i++) {
					if (i < 5 || atiradores1[i - 5] == 0) {
						linha[i] = "";
					} else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[i - 5]);
					}
				}
				modelo.addRow(linha);

				for (int i = 0; i < 7; i++) {
					if (i < 5 || atiradores2[i - 5] == 0) {
						linha[i] = "";
					} else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[i - 5]);
					}
				}
				modelo.addRow(linha);

				for (int i = 0; i < 7; i++) {
					if (i < 5 || atiradores3[i - 5] == 0) {
						linha[i] = "";
					} else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[i - 5]);
					}
				}
				modelo.addRow(linha);

				break;

			case "SAB":
				for (int i = 0; i < 7; i++) {
					if (i < 6) {
						linha[i] = "";
					} else {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[i - 6]);
					}
				}
				modelo.addRow(linha);

				for (int i = 0; i < 7; i++) {
					if (i < 6) {
						linha[i] = "";
					} else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[i - 6]);
					}
				}
				modelo.addRow(linha);

				for (int i = 0; i < 7; i++) {
					if (i < 6) {
						linha[i] = "";
					} else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[i - 6]);
					}
				}
				modelo.addRow(linha);

				for (int i = 0; i < 7; i++) {
					if (i < 6) {
						linha[i] = "";
					} else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[i - 6]);
					}
				}
				modelo.addRow(linha);

				break;
			}
		} else {
			String[] linha = { "", "", "", "", "", "", "" };
			modelo.addRow(linha);
			modelo.addRow(linha);
			modelo.addRow(linha);
			modelo.addRow(linha);
			if (tabela.equals("semanaAtual")) {
				telaEscala.aviso1 = true;
			} else if (tabela.equals("proximaSemana")) {
				telaEscala.aviso2 = true;
			}
		}

		return modelo;
	}

	
	
	
	
	
	public static int[] getIndicesFolga(int[] folgas, List indicesMonitores) {
		int[] indices = new int[4];

		// Pega o indice do monitor mais folgado
		int maior = Integer.MIN_VALUE;
		int qtdMonitores = AtiradorDAO.getQtdMonitores();
		int indiceMonitor = -1;
		for (int i = 0; i < qtdMonitores; i++) {

			if (folgas[i] > maior) {
				maior = folgas[i];
				indiceMonitor = i;
			} else if (folgas[i] == maior) {
				indiceMonitor = i;
			}

		}

		// Pega os indices do 3 atiradores mais folgados
		int l = 0;
		maior = Integer.MIN_VALUE;
		int[] indicesAtirador = new int[0];

		for (int i = 0; i < 3; i++) {

			for (int j = qtdMonitores; j < folgas.length; j++) {

				if (folgas[j] > maior) {
					maior = folgas[j];

					indicesAtirador = new int[3];
					indicesAtirador[i] = j;

				} else if (folgas[j] == maior) {

					if (i == 0) {
						indicesAtirador[i] = j;
					} else if (i == 1 && indicesAtirador[i - 1] != j) {
						indicesAtirador[i] = j;
					} else if (i == 2 && indicesAtirador[i - 1] != j && indicesAtirador[i - 2] != j) {
						indicesAtirador[i] = j;
					}

				}

			}

		}

		// Preencher array indicesAtirador caso não tenha preenchido por completo
//		if (indicesAtirador[2] == 0 && indicesAtirador[0] > 0) {
//			
//			while(indicesAtirador[2] == 0) {
//				int index = 0;
//				int menor = Integer.MIN_VALUE;
//				
//				if(indicesAtirador[1] == 0) {
//					index = 1;
//				}
//				else {
//					index = 2;
//				}
//				
//				for(int i = qtdMonitores; i < folgas.length; i++) {
//					
//					if(folgas[i] < maior && menor < 0) {
//						menor = folgas[i];
//						indicesAtirador[index] = i;
//					}
//					else if(folgas[i] < maior && folgas[i] > menor) {
//						indicesAtirador[index] = i;
//					}
//					else if(folgas[i] == menor) {
//						
//						if(index == 2 && indicesAtirador[index - 1] != i) {
//							indicesAtirador[index] = i;
//						}
//						else if(index == 1) {
//							indicesAtirador[index] = i;
//						}
//						
//					}
//					
//				}
//				
//			}
			// Testar codigo recem feito

//		}

		// Armazena o indice do monitor e dos atiradores em 1 array só
		l = 0;
		for (int i = 0; i < indices.length; i++) {

			if (i == 0) { 
				indices[i] = indiceMonitor;
			} else {
				indices[i] = indicesAtirador[l];
				l++;
			}

		}


		return indices;
	}

	public static int getMaiorValorArray(int[] array, int modificador) {
		int maior = Integer.MIN_VALUE;

		for (int i = modificador; i < array.length; i++) {

			if (array[i] > maior) {
				maior = array[i];

			}
		}

		return maior;
	}

}
