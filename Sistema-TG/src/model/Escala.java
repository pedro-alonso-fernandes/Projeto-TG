package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
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
	
	public static DefaultTableModel getModelSemanaAtual(Date data) {
		DefaultTableModel modelo = (DefaultTableModel) telaEscala.semanaAtual.getModel();
		modelo = getModel(data, modelo, "semanaAtual");
		return modelo;
	}
	
	public static DefaultTableModel getModelProximaSemana(Date data) {
		DefaultTableModel modelo = (DefaultTableModel) telaEscala.proximaSemana.getModel();
		modelo = getModel(data, modelo, "proximaSemana");
		return modelo;
	}
	
	private static DefaultTableModel getModel (Date data, DefaultTableModel modelo, String tabela) {
		
		ResultSet rs = EscalaDAO.getEscalaSemana(data);
		
		int[] monitores = new int[7];
		int[] atiradores1 = new int[7];
		int[] atiradores2 = new int[7];
		int[] atiradores3 = new int[7]; 
		Date[] datas = new Date[7];
//		try {
//			dataEscala = formato.parse("29/07/2024");
//		} catch (ParseException e) {
//			System.out.println("Erro ao salvar data: " + e.getMessage());
//		}
		try {
			int i = 0;
			while(rs.next()) {
				
				datas[i] = rs.getDate("data");
				monitores[i] = rs.getInt("monitorId");
				atiradores1[i] = rs.getInt("atirador1Id");
				atiradores2[i] = rs.getInt("atirador2Id");
				atiradores3[i] = rs.getInt("atirador3Id");
				i++;
			}
			
		} catch(SQLException e) {
			System.out.println("Erro " + e.getMessage());
		}
		
		String[] linha = new String[7];
		
		if(datas[0] != null) {
			
			switch (Data.getDiaSemana(datas[0])) {
			case "DOM":
				
				for(int i = 0; i < 7; i++) {
					if(monitores[i] != 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[i]);
					}
					else {
						linha[i] = "";
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(atiradores1[i] != 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[i]);
					}
					else {
						linha[i] = "";
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(atiradores2[i] != 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[i]);
					}
					else {
						linha[i] = "";
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(atiradores3[i] != 0) {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[i]);
					}
					else {
						linha[i] = "";
					}
				}
				modelo.addRow(linha);
				break;
				
				
				
				
			case "SEG":
				for(int i = 0; i < 7; i++) {
					if(i < 1 || monitores[i - 1] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[i - 1]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 1 || atiradores1[i - 1] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[i - 1]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 1 || atiradores2[i - 1] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[i - 1]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 1 || atiradores3[i - 1] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[i - 1]);
					}
				}
				modelo.addRow(linha);
				break;
				
				
				
				
			case "TER":
				for(int i = 0; i < 7; i++) {
					if(i < 2 || monitores[i - 2] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[i - 2]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 2 || atiradores1[i - 2] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[i - 2]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 2 || atiradores2[i - 2] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[i - 2]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 2 || atiradores3[i - 2] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[i - 2]);
					}
				}
				modelo.addRow(linha);
				
				break;
				
				
				
			case "QUA":
				for(int i = 0; i < 7; i++) {
					if(i < 3 || monitores[i - 3] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[i - 3]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 3 || atiradores1[i - 3] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[i - 3]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 3 || atiradores2[i - 3] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[i - 3]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 3 || atiradores3[i - 3] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[i - 3]);
					}
				}
				modelo.addRow(linha);
				
				break;
				
				
				
				
			case "QUI":
				for(int i = 0; i < 7; i++) {
					if(i < 4 || monitores[i - 4] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[i - 4]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 4 || atiradores1[i - 4] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[i - 4]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 4 || atiradores2[i - 4] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[i - 4]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 4 || atiradores3[i - 4] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[i - 4]);
					}
				}
				modelo.addRow(linha);
				
				break;
				
				
				
				
			case "SEX":
				for(int i = 0; i < 7; i++) {
					if(i < 5 || monitores[i - 5] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[i - 5]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 5 || atiradores1[i - 5] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[i - 5]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 5 || atiradores2[i - 5] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[i - 5]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 5 || atiradores3[i - 5] == 0) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[i - 5]);
					}
				}
				modelo.addRow(linha);
				
				break;
				
				
				
				
			case "SAB":
				for(int i = 0; i < 7; i++) {
					if(i < 6) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(monitores[i - 6]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 6) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores1[i - 6]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 6) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores2[i - 6]);
					}
				}
				modelo.addRow(linha);
				
				for(int i = 0; i < 7; i++) {
					if(i < 6) {
						linha[i] = "";
					}
					else {
						linha[i] = AtiradorDAO.getGuerraAtirador(atiradores3[i - 6]);
					}
				}
				modelo.addRow(linha);
				
				break;
			}
		}
		else {
			if(tabela.equals("semanaAtual")) {
				telaEscala.aviso1 = true;
			}
			else if(tabela.equals("proximaSemana")) {
				telaEscala.aviso2 = true;
			}
		}
		
		return modelo;
	}
	
}
