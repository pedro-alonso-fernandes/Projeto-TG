package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import controller.AtiradorDAO;
import controller.EscalaDAO;
import controller.FeriadoDAO;
import controller.FolgaDAO;
import controller.GuardaDAO;
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

	
	
	
	
	
	public static int[] getidGuarda(int[] guardas) {
		int[] indices = new int[4];

		// Pega o indice do monitor mais folgado
		int maior = Integer.MIN_VALUE;
		int qtdMonitores = AtiradorDAO.getQtdMonitores();
		int indiceMonitor = -1;
		for (int i = 0; i < qtdMonitores; i++) {

			if (guardas[i] > maior) {
				maior = guardas[i];
				indiceMonitor = i;
			} else if (guardas[i] == maior) {
				indiceMonitor = i;
			}

		}

		// Pega os indices do 3 atiradores mais folgados
		maior = Integer.MIN_VALUE;
		int[] indicesAtirador = new int[0];

		for (int i = 0; i < 3; i++) {

			for (int j = qtdMonitores; j < guardas.length; j++) {

				if (guardas[j] > maior) {
					maior = guardas[j];

					indicesAtirador = new int[3];
					indicesAtirador[i] = j;

				} else if (guardas[j] == maior) {

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
			
			for(int i = qtdMonitores; i < guardas.length; i++) {
				
				if(guardas[i] < maior && menor < 0) {
					menor = guardas[i];
					indicesAtirador[index] = i;
				}
				else if(guardas[i] < maior && guardas[i] > menor && indicesAtirador[1] != i) {
					menor = guardas[i];
					indicesAtirador[index] = i;
				}
				else if(guardas[i] == menor) {
					
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

	
	public static void gerarEscala(int[] guardaPreta, int[] guardaVermelha, Date dataEscala) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = dataEscala;
		
//		try {
//			data = formato.parse("25/08/2024");
//		} catch (ParseException e) {
//			System.out.println("Erro ao salvar data: " + e.getMessage());
//		}
		int contador = 0;
		
		
		boolean feriado = false;
		boolean folga = false;
		ResultSet rsFeriado = FeriadoDAO.getFeriados();
		ResultSet rsFolga = FolgaDAO.getFolgas();
		
		try {
			while(rsFeriado.next() || rsFolga.next()) {
				if(formato.format(rsFolga.getDate("data")).equals(formato.format(data))) {
					folga = true;
					break;
				}
				
				if(formato.format(rsFeriado.getDate("data")).equals(formato.format(data))) {
					feriado = true;
				}
				
			}
		} catch (SQLException e) {
			System.out.println("Erro ao percorrer pelos Feriados e Folgas do BD: " + e.getMessage());
		}
		
		Escala escala = null;
		
		if(!folga) {
			
			switch (Data.getDiaSemana(data)) {
			case "DOM":
				
				int[] idGuardaVermelha = getidGuarda(guardaVermelha);
				escala = new Escala(data, "Vermelha", idGuardaVermelha[0], idGuardaVermelha[1], idGuardaVermelha[2], idGuardaVermelha[3]);
				EscalaDAO.cadastrarEscala(escala);
				int[] indicesGuardaVermelha = Array.getIndicePorId(idGuardaVermelha);
				
				for(int i = 0; i < guardaVermelha.length; i++) {
					if(i != indicesGuardaVermelha[0] && i != indicesGuardaVermelha[1] && i != indicesGuardaVermelha[2] && i != indicesGuardaVermelha[3]) {
						guardaVermelha[i]++;
					}
					else {
						guardaVermelha[i] = 0;
					}
				}
				
				contador = 13;
				
				break;
			case "SEG":
				
				if(!feriado) {
					int[] idGuardaPreta = getidGuarda(guardaPreta);
					escala = new Escala(data, "Preta", idGuardaPreta[0], idGuardaPreta[1], idGuardaPreta[2], idGuardaPreta[3]);
					EscalaDAO.cadastrarEscala(escala);
					int[] indicesGuardaPreta = Array.getIndicePorId(idGuardaPreta);
					
					for(int i = 0; i < guardaPreta.length; i++) {
						if(i != indicesGuardaPreta[0] && i != indicesGuardaPreta[1] && i != indicesGuardaPreta[2] && i != indicesGuardaPreta[3]) {
							guardaPreta[i]++;
						}
						else {
							guardaPreta[i] = 0;
						}
					}
					
				}
				else {
					
					idGuardaVermelha = getidGuarda(guardaVermelha);
					escala = new Escala(data, "Vermelha", idGuardaVermelha[0], idGuardaVermelha[1], idGuardaVermelha[2], idGuardaVermelha[3]);
					EscalaDAO.cadastrarEscala(escala);
					indicesGuardaVermelha = Array.getIndicePorId(idGuardaVermelha);
					
					for(int i = 0; i < guardaVermelha.length; i++) {
						if(i != indicesGuardaVermelha[0] && i != indicesGuardaVermelha[1] && i != indicesGuardaVermelha[2] && i != indicesGuardaVermelha[3]) {
							guardaVermelha[i]++;
						}
						else {
							guardaVermelha[i] = 0;
						}
					}
					
				}
				
				contador = 12;
				
				break;
			case "TER":
				
				if(!feriado) {
					int[] idGuardaPreta = getidGuarda(guardaPreta);
					escala = new Escala(data, "Preta", idGuardaPreta[0], idGuardaPreta[1], idGuardaPreta[2], idGuardaPreta[3]);
					EscalaDAO.cadastrarEscala(escala);
					int[] indicesGuardaPreta = Array.getIndicePorId(idGuardaPreta);
					
					for(int i = 0; i < guardaPreta.length; i++) {
						if(i != indicesGuardaPreta[0] && i != indicesGuardaPreta[1] && i != indicesGuardaPreta[2] && i != indicesGuardaPreta[3]) {
							guardaPreta[i]++;
						}
						else {
							guardaPreta[i] = 0;
						}
					}
					
				}
				else {
					
					idGuardaVermelha = getidGuarda(guardaVermelha);
					escala = new Escala(data, "Vermelha", idGuardaVermelha[0], idGuardaVermelha[1], idGuardaVermelha[2], idGuardaVermelha[3]);
					EscalaDAO.cadastrarEscala(escala);
					indicesGuardaVermelha = Array.getIndicePorId(idGuardaVermelha);
					
					for(int i = 0; i < guardaVermelha.length; i++) {
						if(i != indicesGuardaVermelha[0] && i != indicesGuardaVermelha[1] && i != indicesGuardaVermelha[2] && i != indicesGuardaVermelha[3]) {
							guardaVermelha[i]++;
						}
						else {
							guardaVermelha[i] = 0;
						}
					}
					
				}
				
				contador = 11;
				
				break;
			case "QUA":
				
				if(!feriado) {
					int[] idGuardaPreta = getidGuarda(guardaPreta);
					escala = new Escala(data, "Preta", idGuardaPreta[0], idGuardaPreta[1], idGuardaPreta[2], idGuardaPreta[3]);
					EscalaDAO.cadastrarEscala(escala);
					int[] indicesGuardaPreta = Array.getIndicePorId(idGuardaPreta);
					
					for(int i = 0; i < guardaPreta.length; i++) {
						if(i != indicesGuardaPreta[0] && i != indicesGuardaPreta[1] && i != indicesGuardaPreta[2] && i != indicesGuardaPreta[3]) {
							guardaPreta[i]++;
						}
						else {
							guardaPreta[i] = 0;
						}
					}
					
				}
				else {
					
					idGuardaVermelha = getidGuarda(guardaVermelha);
					escala = new Escala(data, "Vermelha", idGuardaVermelha[0], idGuardaVermelha[1], idGuardaVermelha[2], idGuardaVermelha[3]);
					EscalaDAO.cadastrarEscala(escala);
					indicesGuardaVermelha = Array.getIndicePorId(idGuardaVermelha);
					
					for(int i = 0; i < guardaVermelha.length; i++) {
						if(i != indicesGuardaVermelha[0] && i != indicesGuardaVermelha[1] && i != indicesGuardaVermelha[2] && i != indicesGuardaVermelha[3]) {
							guardaVermelha[i]++;
						}
						else {
							guardaVermelha[i] = 0;
						}
					}
					
				}
				
				contador = 10;
				
				break;
			case "QUI":
				
				if(!feriado) {
					int[] idGuardaPreta = getidGuarda(guardaPreta);
					escala = new Escala(data, "Preta", idGuardaPreta[0], idGuardaPreta[1], idGuardaPreta[2], idGuardaPreta[3]);
					EscalaDAO.cadastrarEscala(escala);
					int[] indicesGuardaPreta = Array.getIndicePorId(idGuardaPreta);
					
					for(int i = 0; i < guardaPreta.length; i++) {
						if(i != indicesGuardaPreta[0] && i != indicesGuardaPreta[1] && i != indicesGuardaPreta[2] && i != indicesGuardaPreta[3]) {
							guardaPreta[i]++;
						}
						else {
							guardaPreta[i] = 0;
						}
					}
					
				}
				else {
					
					idGuardaVermelha = getidGuarda(guardaVermelha);
					escala = new Escala(data, "Vermelha", idGuardaVermelha[0], idGuardaVermelha[1], idGuardaVermelha[2], idGuardaVermelha[3]);
					EscalaDAO.cadastrarEscala(escala);
					indicesGuardaVermelha = Array.getIndicePorId(idGuardaVermelha);
					
					for(int i = 0; i < guardaVermelha.length; i++) {
						if(i != indicesGuardaVermelha[0] && i != indicesGuardaVermelha[1] && i != indicesGuardaVermelha[2] && i != indicesGuardaVermelha[3]) {
							guardaVermelha[i]++;
						}
						else {
							guardaVermelha[i] = 0;
						}
					}
					
				}
				
				contador = 9;
				
				break;
			case "SEX":
				
				if(!feriado) {
					int[] idGuardaPreta = getidGuarda(guardaPreta);
					escala = new Escala(data, "Preta", idGuardaPreta[0], idGuardaPreta[1], idGuardaPreta[2], idGuardaPreta[3]);
					EscalaDAO.cadastrarEscala(escala);
					int[] indicesGuardaPreta = Array.getIndicePorId(idGuardaPreta);
					
					for(int i = 0; i < guardaPreta.length; i++) {
						if(i != indicesGuardaPreta[0] && i != indicesGuardaPreta[1] && i != indicesGuardaPreta[2] && i != indicesGuardaPreta[3]) {
							guardaPreta[i]++;
						}
						else {
							guardaPreta[i] = 0;
						}
					}
					
				}
				else {
					
					idGuardaVermelha = getidGuarda(guardaVermelha);
					escala = new Escala(data, "Vermelha", idGuardaVermelha[0], idGuardaVermelha[1], idGuardaVermelha[2], idGuardaVermelha[3]);
					EscalaDAO.cadastrarEscala(escala);
					indicesGuardaVermelha = Array.getIndicePorId(idGuardaVermelha);
					
					for(int i = 0; i < guardaVermelha.length; i++) {
						if(i != indicesGuardaVermelha[0] && i != indicesGuardaVermelha[1] && i != indicesGuardaVermelha[2] && i != indicesGuardaVermelha[3]) {
							guardaVermelha[i]++;
						}
						else {
							guardaVermelha[i] = 0;
						}
					}
					
				}
				
				contador = 8;
				
				break;
			case "SAB":
				
				idGuardaVermelha = getidGuarda(guardaVermelha);
				escala = new Escala(data, "Vermelha", idGuardaVermelha[0], idGuardaVermelha[1], idGuardaVermelha[2], idGuardaVermelha[3]);
				EscalaDAO.cadastrarEscala(escala);
				indicesGuardaVermelha = Array.getIndicePorId(idGuardaVermelha);
				
				for(int i = 0; i < guardaVermelha.length; i++) {
					if(i != indicesGuardaVermelha[0] && i != indicesGuardaVermelha[1] && i != indicesGuardaVermelha[2] && i != indicesGuardaVermelha[3]) {
						guardaVermelha[i]++;
					}
					else {
						guardaVermelha[i] = 0;
					}
				}
				
				contador = 7;
				
				break;	
			}
			
			data = Data.addDias(data, 1);
			
		}
		else {
			data = Data.addDias(data, 1);
		}
		
		
		
		
		// Próximas escalas
		for(int j = 0; j < contador; j++) {
			feriado = false;
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
			
			escala = null;
			String diaDaSemana = Data.getDiaSemana(data); 
			
			if(diaDaSemana.equals("DOM") || diaDaSemana.equals("SAB")) {
				
				int[] idGuardaVermelha = verificarGuarda(guardaVermelha, data);
				int[] indicesGuardaVermelha = Array.getIndicePorId(idGuardaVermelha);
				
				escala = new Escala(data, "Vermelha", idGuardaVermelha[0], idGuardaVermelha[1], idGuardaVermelha[2], idGuardaVermelha[3]);
				EscalaDAO.cadastrarEscala(escala);
				
				for(int i = 0; i < guardaVermelha.length; i++) {
					if(i != indicesGuardaVermelha[0] && i != indicesGuardaVermelha[1] && i != indicesGuardaVermelha[2] && i != indicesGuardaVermelha[3]) {
						guardaVermelha[i]++;
					}
					else {
						guardaVermelha[i] = 0;
					}
				}
				
			}
			else if(diaDaSemana.equals("SEG") || diaDaSemana.equals("TER") || diaDaSemana.equals("QUA") 
					|| diaDaSemana.equals("QUI") || diaDaSemana.equals("SEX")) {
				
				if(!feriado) {
					
					int[] idGuardaPreta = verificarGuarda(guardaPreta, data);
					int[] indicesGuardaPreta = Array.getIndicePorId(idGuardaPreta);
					
					escala = new Escala(data, "Preta", idGuardaPreta[0], idGuardaPreta[1], idGuardaPreta[2], idGuardaPreta[3]);
					EscalaDAO.cadastrarEscala(escala);
					
					for(int i = 0; i < guardaPreta.length; i++) {
						if(i != indicesGuardaPreta[0] && i != indicesGuardaPreta[1] && i != indicesGuardaPreta[2] && i != indicesGuardaPreta[3]) {
							guardaPreta[i]++;
						}
						else {
							guardaPreta[i] = 0;
						}
					}
					
				}
				else {
					
					int[] idGuardaVermelha = verificarGuarda(guardaVermelha, data);
					int[] indicesGuardaVermelha = Array.getIndicePorId(idGuardaVermelha);
					
					escala = new Escala(data, "Vermelha", idGuardaVermelha[0], idGuardaVermelha[1], idGuardaVermelha[2], idGuardaVermelha[3]);
					EscalaDAO.cadastrarEscala(escala);
					
					for(int i = 0; i < guardaVermelha.length; i++) {
						if(i != indicesGuardaVermelha[0] && i != indicesGuardaVermelha[1] && i != indicesGuardaVermelha[2] && i != indicesGuardaVermelha[3]) {
							guardaVermelha[i]++;
						}
						else {
							guardaVermelha[i] = 0;
						}
					}
					
				}
				
			}
			
			
			data = Data.addDias(data, 1);
		}
		GuardaDAO.cadastrarGuarda(guardaPreta, "Preta");
		GuardaDAO.cadastrarGuarda(guardaVermelha, "Vermelha");
		
		
	}
	
	
	
	
	private static int[] verificarGuarda(int[] guarda, Date data) {
		int[] idGuarda = getidGuarda(guarda);
		
		
		// Verifica se os atiradores pegaram guarda anteontem
		int[] simulacaoguarda = new int[guarda.length];
		for(int i = 0; i < guarda.length; i++) {
			simulacaoguarda[i] = guarda[i];
		}
		int []indicesGuarda = Array.getIndicePorId(idGuarda);
		
		for(int i = 0; i < simulacaoguarda.length; i++) {
			if(i != indicesGuarda[0] && i != indicesGuarda[1] && i != indicesGuarda[2] && i != indicesGuarda[3]) {
				simulacaoguarda[i]++;
			}
			else {
				simulacaoguarda[i] = 0;
			}
		}
		
		
		ResultSet rsAnterior  = EscalaDAO.getEscala(Data.addDias(data, -2));
		int[] idGuardaAnterior = new int[4];
		
		
		try {
			if(rsAnterior.next()) {
				
				idGuardaAnterior[0] = rsAnterior.getInt("monitorId");
				idGuardaAnterior[1] = rsAnterior.getInt("atirador1Id");
				idGuardaAnterior[2] = rsAnterior.getInt("atirador2Id");
				idGuardaAnterior[3] = rsAnterior.getInt("atirador3Id");
				
				
				int[] idGuardaProximo = getidGuarda(simulacaoguarda);
				
				int l = 1;
				for(int i = 0; i < idGuarda.length; i++) {
					if(idGuarda[i] == idGuardaAnterior[i] && i == 0) {
						idGuarda[i] = idGuardaProximo[i];
					}
					else if(idGuarda[i] == idGuardaAnterior[i] && i > 0) {
						idGuarda[i] = idGuardaProximo[l];
						l++;
					}
				}
			}
			
			
		} catch (SQLException e) {
			System.out.println("Erro ao pegar Escala anterior: " + e.getMessage());
		}
		
		// Verifica se os atiradores pegaram guarda no dia anterior
		
		
		
		
		indicesGuarda = Array.getIndicePorId(idGuarda);
		
		for(int i = 0; i < simulacaoguarda.length; i++) {
			if(i != indicesGuarda[0] && i != indicesGuarda[1] && i != indicesGuarda[2] && i != indicesGuarda[3]) {
				simulacaoguarda[i]++;
			}
			else {
				simulacaoguarda[i] = 0;
			}
		}
		
		rsAnterior  = EscalaDAO.getEscala(Data.addDias(data, -1));
		idGuardaAnterior = new int[4];
		
		
		try {
			rsAnterior.next();
			idGuardaAnterior[0] = rsAnterior.getInt("monitorId");
			idGuardaAnterior[1] = rsAnterior.getInt("atirador1Id");
			idGuardaAnterior[2] = rsAnterior.getInt("atirador2Id");
			idGuardaAnterior[3] = rsAnterior.getInt("atirador3Id");
			
			
		} catch (SQLException e) {
			System.out.println("Erro ao pegar Escala anterior: " + e.getMessage());
		}
		
		int[] idGuardaProximo = getidGuarda(simulacaoguarda);
		
		int l = 1;
		for(int i = 0; i < idGuarda.length; i++) {
			if(idGuarda[i] == idGuardaAnterior[i] && i == 0) {
				idGuarda[i] = idGuardaProximo[i];
			}
			else if(idGuarda[i] == idGuardaAnterior[i] && i > 0) {
				idGuarda[i] = idGuardaProximo[l];
				l++;
			}
		}
		
		
		return idGuarda;
	}

}
