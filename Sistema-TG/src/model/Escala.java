package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import controller.AtiradorDAO;
import controller.EscalaDAO;
import controller.FeriadoDAO;
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

	
	
	
	
	
	public static int[] getIdFolga(int[] folgas) {
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
			
		int index = 0;
		while(indicesAtirador[2] == 0) {
			int menor = Integer.MIN_VALUE;
			
			if(indicesAtirador[1] == 0) {
				index = 1;
			}
			else {
				index = 2;
			}
			
			for(int i = qtdMonitores; i < folgas.length; i++) {
				
				if(folgas[i] < maior && menor < 0) {
					menor = folgas[i];
					indicesAtirador[index] = i;
				}
				else if(folgas[i] < maior && folgas[i] > menor && indicesAtirador[1] != i) {
					menor = folgas[i];
					indicesAtirador[index] = i;
				}
				else if(folgas[i] == menor) {
					
					if(index == 2 && indicesAtirador[index - 1] != i) {
						indicesAtirador[index] = i;
					}
					else if(index == 1) {
						indicesAtirador[index] = i;
					}
					
				}
				
			}
			
		}


		// Armazena o indice do monitor e dos atiradores em 1 array só
		int j = 0;
		for (int i = 0; i < indices.length; i++) {

			if (i == 0) { 
				indices[i] = indiceMonitor + 1;
			} else {
				indices[i] = indicesAtirador[j] + 1;
				j++;
			}

		}


		return indices;
	}

	
	public static void gerarEscala(int[] folgaPreta, int[] folgaVermelha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		
		try {
			data = formato.parse("18/08/2024");
		} catch (ParseException e) {
			System.out.println("Erro ao salvar data: " + e.getMessage());
		}
		int contador = 0;
		
		
		boolean feriado = false;
		ResultSet rs = FeriadoDAO.getFeriados();
		
		try {
			while(rs.next()) {
				if(formato.format(rs.getDate("data")).equals(formato.format(data))) {
					feriado = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao percorrer pelos feriados do BD: " + e.getMessage());
		}
		
		Escala escala = null;
		
		
		switch (Data.getDiaSemana(data)) {
		case "DOM":
			
			int[] idFolgaVermelha = getIdFolga(folgaVermelha);
			escala = new Escala(data, "Vermelha", idFolgaVermelha[0], idFolgaVermelha[1], idFolgaVermelha[2], idFolgaVermelha[3]);
			EscalaDAO.cadastrarEscala(escala);
			int[] indicesFolgaVermelha = Array.getIndicePorId(idFolgaVermelha);
			
			for(int i = 0; i < folgaVermelha.length; i++) {
				if(i != indicesFolgaVermelha[0] && i != indicesFolgaVermelha[1] && i != indicesFolgaVermelha[2] && i != indicesFolgaVermelha[3]) {
					folgaVermelha[i]++;
				}
				else {
					folgaVermelha[i] = 0;
				}
			}
			
			contador = 13;
			
			break;
		case "SEG":
			
			if(!feriado) {
				int[] idFolgaPreta = getIdFolga(folgaPreta);
				escala = new Escala(data, "Preta", idFolgaPreta[0], idFolgaPreta[1], idFolgaPreta[2], idFolgaPreta[3]);
				EscalaDAO.cadastrarEscala(escala);
				int[] indicesFolgaPreta = Array.getIndicePorId(idFolgaPreta);
				
				for(int i = 0; i < folgaPreta.length; i++) {
					if(i != indicesFolgaPreta[0] && i != indicesFolgaPreta[1] && i != indicesFolgaPreta[2] && i != indicesFolgaPreta[3]) {
						folgaPreta[i]++;
					}
					else {
						folgaPreta[i] = 0;
					}
				}
				
			}
			else {
				
				idFolgaVermelha = getIdFolga(folgaVermelha);
				escala = new Escala(data, "Vermelha", idFolgaVermelha[0], idFolgaVermelha[1], idFolgaVermelha[2], idFolgaVermelha[3]);
				EscalaDAO.cadastrarEscala(escala);
				indicesFolgaVermelha = Array.getIndicePorId(idFolgaVermelha);
				
				for(int i = 0; i < folgaVermelha.length; i++) {
					if(i != indicesFolgaVermelha[0] && i != indicesFolgaVermelha[1] && i != indicesFolgaVermelha[2] && i != indicesFolgaVermelha[3]) {
						folgaVermelha[i]++;
					}
					else {
						folgaVermelha[i] = 0;
					}
				}
				
			}
			
			contador = 12;
			
			break;
		case "TER":
			
			if(!feriado) {
				int[] idFolgaPreta = getIdFolga(folgaPreta);
				escala = new Escala(data, "Preta", idFolgaPreta[0], idFolgaPreta[1], idFolgaPreta[2], idFolgaPreta[3]);
				EscalaDAO.cadastrarEscala(escala);
				int[] indicesFolgaPreta = Array.getIndicePorId(idFolgaPreta);
				
				for(int i = 0; i < folgaPreta.length; i++) {
					if(i != indicesFolgaPreta[0] && i != indicesFolgaPreta[1] && i != indicesFolgaPreta[2] && i != indicesFolgaPreta[3]) {
						folgaPreta[i]++;
					}
					else {
						folgaPreta[i] = 0;
					}
				}
				
			}
			else {
				
				idFolgaVermelha = getIdFolga(folgaVermelha);
				escala = new Escala(data, "Vermelha", idFolgaVermelha[0], idFolgaVermelha[1], idFolgaVermelha[2], idFolgaVermelha[3]);
				EscalaDAO.cadastrarEscala(escala);
				indicesFolgaVermelha = Array.getIndicePorId(idFolgaVermelha);
				
				for(int i = 0; i < folgaVermelha.length; i++) {
					if(i != indicesFolgaVermelha[0] && i != indicesFolgaVermelha[1] && i != indicesFolgaVermelha[2] && i != indicesFolgaVermelha[3]) {
						folgaVermelha[i]++;
					}
					else {
						folgaVermelha[i] = 0;
					}
				}
				
			}
			
			contador = 11;
			
			break;
		case "QUA":
			
			if(!feriado) {
				int[] idFolgaPreta = getIdFolga(folgaPreta);
				escala = new Escala(data, "Preta", idFolgaPreta[0], idFolgaPreta[1], idFolgaPreta[2], idFolgaPreta[3]);
				EscalaDAO.cadastrarEscala(escala);
				int[] indicesFolgaPreta = Array.getIndicePorId(idFolgaPreta);
				
				for(int i = 0; i < folgaPreta.length; i++) {
					if(i != indicesFolgaPreta[0] && i != indicesFolgaPreta[1] && i != indicesFolgaPreta[2] && i != indicesFolgaPreta[3]) {
						folgaPreta[i]++;
					}
					else {
						folgaPreta[i] = 0;
					}
				}
				
			}
			else {
				
				idFolgaVermelha = getIdFolga(folgaVermelha);
				escala = new Escala(data, "Vermelha", idFolgaVermelha[0], idFolgaVermelha[1], idFolgaVermelha[2], idFolgaVermelha[3]);
				EscalaDAO.cadastrarEscala(escala);
				indicesFolgaVermelha = Array.getIndicePorId(idFolgaVermelha);
				
				for(int i = 0; i < folgaVermelha.length; i++) {
					if(i != indicesFolgaVermelha[0] && i != indicesFolgaVermelha[1] && i != indicesFolgaVermelha[2] && i != indicesFolgaVermelha[3]) {
						folgaVermelha[i]++;
					}
					else {
						folgaVermelha[i] = 0;
					}
				}
				
			}
			
			contador = 10;
			
			break;
		case "QUI":
			
			if(!feriado) {
				int[] idFolgaPreta = getIdFolga(folgaPreta);
				escala = new Escala(data, "Preta", idFolgaPreta[0], idFolgaPreta[1], idFolgaPreta[2], idFolgaPreta[3]);
				EscalaDAO.cadastrarEscala(escala);
				int[] indicesFolgaPreta = Array.getIndicePorId(idFolgaPreta);
				
				for(int i = 0; i < folgaPreta.length; i++) {
					if(i != indicesFolgaPreta[0] && i != indicesFolgaPreta[1] && i != indicesFolgaPreta[2] && i != indicesFolgaPreta[3]) {
						folgaPreta[i]++;
					}
					else {
						folgaPreta[i] = 0;
					}
				}
				
			}
			else {
				
				idFolgaVermelha = getIdFolga(folgaVermelha);
				escala = new Escala(data, "Vermelha", idFolgaVermelha[0], idFolgaVermelha[1], idFolgaVermelha[2], idFolgaVermelha[3]);
				EscalaDAO.cadastrarEscala(escala);
				indicesFolgaVermelha = Array.getIndicePorId(idFolgaVermelha);
				
				for(int i = 0; i < folgaVermelha.length; i++) {
					if(i != indicesFolgaVermelha[0] && i != indicesFolgaVermelha[1] && i != indicesFolgaVermelha[2] && i != indicesFolgaVermelha[3]) {
						folgaVermelha[i]++;
					}
					else {
						folgaVermelha[i] = 0;
					}
				}
				
			}
			
			contador = 9;
			
			break;
		case "SEX":
			
			if(!feriado) {
				int[] idFolgaPreta = getIdFolga(folgaPreta);
				escala = new Escala(data, "Preta", idFolgaPreta[0], idFolgaPreta[1], idFolgaPreta[2], idFolgaPreta[3]);
				EscalaDAO.cadastrarEscala(escala);
				int[] indicesFolgaPreta = Array.getIndicePorId(idFolgaPreta);
				
				for(int i = 0; i < folgaPreta.length; i++) {
					if(i != indicesFolgaPreta[0] && i != indicesFolgaPreta[1] && i != indicesFolgaPreta[2] && i != indicesFolgaPreta[3]) {
						folgaPreta[i]++;
					}
					else {
						folgaPreta[i] = 0;
					}
				}
				
			}
			else {
				
				idFolgaVermelha = getIdFolga(folgaVermelha);
				escala = new Escala(data, "Vermelha", idFolgaVermelha[0], idFolgaVermelha[1], idFolgaVermelha[2], idFolgaVermelha[3]);
				EscalaDAO.cadastrarEscala(escala);
				indicesFolgaVermelha = Array.getIndicePorId(idFolgaVermelha);
				
				for(int i = 0; i < folgaVermelha.length; i++) {
					if(i != indicesFolgaVermelha[0] && i != indicesFolgaVermelha[1] && i != indicesFolgaVermelha[2] && i != indicesFolgaVermelha[3]) {
						folgaVermelha[i]++;
					}
					else {
						folgaVermelha[i] = 0;
					}
				}
				
			}
			
			contador = 8;
			
			break;
		case "SAB":
			
			idFolgaVermelha = getIdFolga(folgaVermelha);
			escala = new Escala(data, "Vermelha", idFolgaVermelha[0], idFolgaVermelha[1], idFolgaVermelha[2], idFolgaVermelha[3]);
			EscalaDAO.cadastrarEscala(escala);
			indicesFolgaVermelha = Array.getIndicePorId(idFolgaVermelha);
			
			for(int i = 0; i < folgaVermelha.length; i++) {
				if(i != indicesFolgaVermelha[0] && i != indicesFolgaVermelha[1] && i != indicesFolgaVermelha[2] && i != indicesFolgaVermelha[3]) {
					folgaVermelha[i]++;
				}
				else {
					folgaVermelha[i] = 0;
				}
			}
			
			contador = 7;
			
			break;	
		}
		
		data = Data.addDias(data, 1);
		
		
		
		
		for(int j = 0; j < contador; j++) {
			feriado = false;
			rs = FeriadoDAO.getFeriados();
			
			try {
				while(rs.next()) {
					if(formato.format(rs.getDate("data")).equals(formato.format(data))) {
						feriado = true;
					}
				}
			} catch (SQLException e) {
				System.out.println("Erro ao percorrer pelos feriados do BD: " + e.getMessage());
			}
			
			escala = null;
			
			
			switch (Data.getDiaSemana(data)) {
			case "DOM":
				
				int[] idFolgaVermelha = getIdFolga(folgaVermelha);
				escala = new Escala(data, "Vermelha", idFolgaVermelha[0], idFolgaVermelha[1], idFolgaVermelha[2], idFolgaVermelha[3]);
				EscalaDAO.cadastrarEscala(escala);
				int[] indicesFolgaVermelha = Array.getIndicePorId(idFolgaVermelha);
				
				for(int i = 0; i < folgaVermelha.length; i++) {
					if(i != indicesFolgaVermelha[0] && i != indicesFolgaVermelha[1] && i != indicesFolgaVermelha[2] && i != indicesFolgaVermelha[3]) {
						folgaVermelha[i]++;
					}
					else {
						folgaVermelha[i] = 0;
					}
				}
				
				
				break;
			case "SEG":
				
				if(!feriado) {
					int[] idFolgaPreta = getIdFolga(folgaPreta);
					escala = new Escala(data, "Preta", idFolgaPreta[0], idFolgaPreta[1], idFolgaPreta[2], idFolgaPreta[3]);
					EscalaDAO.cadastrarEscala(escala);
					int[] indicesFolgaPreta = Array.getIndicePorId(idFolgaPreta);
					
					for(int i = 0; i < folgaPreta.length; i++) {
						if(i != indicesFolgaPreta[0] && i != indicesFolgaPreta[1] && i != indicesFolgaPreta[2] && i != indicesFolgaPreta[3]) {
							folgaPreta[i]++;
						}
						else {
							folgaPreta[i] = 0;
						}
					}
					
				}
				else {
					
					idFolgaVermelha = getIdFolga(folgaVermelha);
					escala = new Escala(data, "Vermelha", idFolgaVermelha[0], idFolgaVermelha[1], idFolgaVermelha[2], idFolgaVermelha[3]);
					EscalaDAO.cadastrarEscala(escala);
					indicesFolgaVermelha = Array.getIndicePorId(idFolgaVermelha);
					
					for(int i = 0; i < folgaVermelha.length; i++) {
						if(i != indicesFolgaVermelha[0] && i != indicesFolgaVermelha[1] && i != indicesFolgaVermelha[2] && i != indicesFolgaVermelha[3]) {
							folgaVermelha[i]++;
						}
						else {
							folgaVermelha[i] = 0;
						}
					}
					
				}
				
				
				break;
			case "TER":
				
				if(!feriado) {
					int[] idFolgaPreta = getIdFolga(folgaPreta);
					escala = new Escala(data, "Preta", idFolgaPreta[0], idFolgaPreta[1], idFolgaPreta[2], idFolgaPreta[3]);
					EscalaDAO.cadastrarEscala(escala);
					int[] indicesFolgaPreta = Array.getIndicePorId(idFolgaPreta);
					
					for(int i = 0; i < folgaPreta.length; i++) {
						if(i != indicesFolgaPreta[0] && i != indicesFolgaPreta[1] && i != indicesFolgaPreta[2] && i != indicesFolgaPreta[3]) {
							folgaPreta[i]++;
						}
						else {
							folgaPreta[i] = 0;
						}
					}
					
				}
				else {
					
					idFolgaVermelha = getIdFolga(folgaVermelha);
					escala = new Escala(data, "Vermelha", idFolgaVermelha[0], idFolgaVermelha[1], idFolgaVermelha[2], idFolgaVermelha[3]);
					EscalaDAO.cadastrarEscala(escala);
					indicesFolgaVermelha = Array.getIndicePorId(idFolgaVermelha);
					
					for(int i = 0; i < folgaVermelha.length; i++) {
						if(i != indicesFolgaVermelha[0] && i != indicesFolgaVermelha[1] && i != indicesFolgaVermelha[2] && i != indicesFolgaVermelha[3]) {
							folgaVermelha[i]++;
						}
						else {
							folgaVermelha[i] = 0;
						}
					}
					
				}
				
				
				break;
			case "QUA":
				
				if(!feriado) {
					int[] idFolgaPreta = getIdFolga(folgaPreta);
					escala = new Escala(data, "Preta", idFolgaPreta[0], idFolgaPreta[1], idFolgaPreta[2], idFolgaPreta[3]);
					EscalaDAO.cadastrarEscala(escala);
					int[] indicesFolgaPreta = Array.getIndicePorId(idFolgaPreta);
					
					for(int i = 0; i < folgaPreta.length; i++) {
						if(i != indicesFolgaPreta[0] && i != indicesFolgaPreta[1] && i != indicesFolgaPreta[2] && i != indicesFolgaPreta[3]) {
							folgaPreta[i]++;
						}
						else {
							folgaPreta[i] = 0;
						}
					}
					
				}
				else {
					
					idFolgaVermelha = getIdFolga(folgaVermelha);
					escala = new Escala(data, "Vermelha", idFolgaVermelha[0], idFolgaVermelha[1], idFolgaVermelha[2], idFolgaVermelha[3]);
					EscalaDAO.cadastrarEscala(escala);
					indicesFolgaVermelha = Array.getIndicePorId(idFolgaVermelha);
					
					for(int i = 0; i < folgaVermelha.length; i++) {
						if(i != indicesFolgaVermelha[0] && i != indicesFolgaVermelha[1] && i != indicesFolgaVermelha[2] && i != indicesFolgaVermelha[3]) {
							folgaVermelha[i]++;
						}
						else {
							folgaVermelha[i] = 0;
						}
					}
					
				}
				
				
				break;
			case "QUI":
				
				if(!feriado) {
					int[] idFolgaPreta = getIdFolga(folgaPreta);
					escala = new Escala(data, "Preta", idFolgaPreta[0], idFolgaPreta[1], idFolgaPreta[2], idFolgaPreta[3]);
					EscalaDAO.cadastrarEscala(escala);
					int[] indicesFolgaPreta = Array.getIndicePorId(idFolgaPreta);
					
					for(int i = 0; i < folgaPreta.length; i++) {
						if(i != indicesFolgaPreta[0] && i != indicesFolgaPreta[1] && i != indicesFolgaPreta[2] && i != indicesFolgaPreta[3]) {
							folgaPreta[i]++;
						}
						else {
							folgaPreta[i] = 0;
						}
					}
					
				}
				else {
					
					idFolgaVermelha = getIdFolga(folgaVermelha);
					escala = new Escala(data, "Vermelha", idFolgaVermelha[0], idFolgaVermelha[1], idFolgaVermelha[2], idFolgaVermelha[3]);
					EscalaDAO.cadastrarEscala(escala);
					indicesFolgaVermelha = Array.getIndicePorId(idFolgaVermelha);
					
					for(int i = 0; i < folgaVermelha.length; i++) {
						if(i != indicesFolgaVermelha[0] && i != indicesFolgaVermelha[1] && i != indicesFolgaVermelha[2] && i != indicesFolgaVermelha[3]) {
							folgaVermelha[i]++;
						}
						else {
							folgaVermelha[i] = 0;
						}
					}
					
				}
				
				
				break;
			case "SEX":
				
				if(!feriado) {
					int[] idFolgaPreta = getIdFolga(folgaPreta);
					escala = new Escala(data, "Preta", idFolgaPreta[0], idFolgaPreta[1], idFolgaPreta[2], idFolgaPreta[3]);
					EscalaDAO.cadastrarEscala(escala);
					int[] indicesFolgaPreta = Array.getIndicePorId(idFolgaPreta);
					
					for(int i = 0; i < folgaPreta.length; i++) {
						if(i != indicesFolgaPreta[0] && i != indicesFolgaPreta[1] && i != indicesFolgaPreta[2] && i != indicesFolgaPreta[3]) {
							folgaPreta[i]++;
						}
						else {
							folgaPreta[i] = 0;
						}
					}
					
				}
				else {
					
					idFolgaVermelha = getIdFolga(folgaVermelha);
					escala = new Escala(data, "Vermelha", idFolgaVermelha[0], idFolgaVermelha[1], idFolgaVermelha[2], idFolgaVermelha[3]);
					EscalaDAO.cadastrarEscala(escala);
					indicesFolgaVermelha = Array.getIndicePorId(idFolgaVermelha);
					
					for(int i = 0; i < folgaVermelha.length; i++) {
						if(i != indicesFolgaVermelha[0] && i != indicesFolgaVermelha[1] && i != indicesFolgaVermelha[2] && i != indicesFolgaVermelha[3]) {
							folgaVermelha[i]++;
						}
						else {
							folgaVermelha[i] = 0;
						}
					}
					
				}
				
				
				break;
			case "SAB":
				
				idFolgaVermelha = getIdFolga(folgaVermelha);
				escala = new Escala(data, "Vermelha", idFolgaVermelha[0], idFolgaVermelha[1], idFolgaVermelha[2], idFolgaVermelha[3]);
				EscalaDAO.cadastrarEscala(escala);
				indicesFolgaVermelha = Array.getIndicePorId(idFolgaVermelha);
				
				for(int i = 0; i < folgaVermelha.length; i++) {
					if(i != indicesFolgaVermelha[0] && i != indicesFolgaVermelha[1] && i != indicesFolgaVermelha[2] && i != indicesFolgaVermelha[3]) {
						folgaVermelha[i]++;
					}
					else {
						folgaVermelha[i] = 0;
					}
				}
				
				
				break;	
			}
			
			data = Data.addDias(data, 1);
		}
		
	}

}
