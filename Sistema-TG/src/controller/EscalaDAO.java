package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Data;
import model.Escala;

public class EscalaDAO {

	public static void cadastrarEscala(Escala escala) {
		BD.selecionarDatabase();
		
		String sql = "insert into Escala (data, cor, monitorId, atirador1Id, atirador2Id, atirador3Id) values (?, ?, ?, ?, ?, ?);";
		
		PreparedStatement ps = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(escala.getData()));
			ps.setString(2, escala.getCor());
			ps.setString(3, String.valueOf(escala.getMonitorId()));
			ps.setString(4, String.valueOf(escala.getAtirador1Id()));
			ps.setString(5, String.valueOf(escala.getAtirador2Id()));
			ps.setString(6, String.valueOf(escala.getAtirador3Id()));
			ps.execute();
			
		} catch (SQLException e) {
			System.out.println("Erro ao criar nova escala: " + e.getMessage());
		}
	}
	
	//Pega todas as escalas
	public static ResultSet getEscalas() {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar todas as Escalas: " + e.getMessage());
			return rs;
		}
	}
	
	//Pega escala por ID
	public static ResultSet getEscala(int id) {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala where id = ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(id));
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a Escala pelo id: " + e.getMessage());
			return rs;
		}
	}
	
	//Pega escala por Data
	public static ResultSet getEscala(Date data) {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala where data = ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(data));
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a Escala pela data(úńica): " + e.getMessage());
			return rs;
		}
	}
	
	//Pega escalas apartir da Data informada
	public static ResultSet getEscalas(Date data) {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala where data >= ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(data));
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a Escala pela data(várias): " + e.getMessage());
			return rs;
		}
	}
	
	//Pega a escala da Semana
		public static ResultSet getEscalaSemana(Date data) {
			BD.selecionarDatabase();
			
			String sql = "select * from Escala where data >= ? and data <= ?;";
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			SimpleDateFormat formato = new SimpleDateFormat("yyy-MM-dd");
			
			data = Data.primeiroDiaSemana(data);
			
			try {
				ps = Conexao.getConexao().prepareStatement(sql);
				ps.setString(1, formato.format(data));
				ps.setString(2, formato.format(Data.addDias(data, 6)));
				rs = ps.executeQuery();
				return rs;
			} catch (SQLException e) {
				System.out.println("Erro ao pegar a Escala da Semana: " + e.getMessage());
				return rs;
			}
		}
	
	//Pegar escalas por cor
	public static ResultSet getEscalas(String cor) {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala where cor = ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if(!cor.equalsIgnoreCase("vermelha") && !cor.equalsIgnoreCase("preta")) {
			System.out.println("Essa não é uma cor válida!");
			return rs;
		}
		else {
			
			try {
				ps = Conexao.getConexao().prepareStatement(sql);
				ps.setString(1, cor);
				rs = ps.executeQuery();
				return rs;
			} catch (SQLException e) {
				System.out.println("Erro ao pegar a Escala por cor: " + e.getMessage());
				return rs;
			}
			
		}
		
	}
	
	//Pegar escalas por Monitor
	public static ResultSet getEscalasMonitor(int monitorId) {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala where monitorId = ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(monitorId));
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a Escala por Monitor: " + e.getMessage());
			return rs;
		}
	}
	
	//Pegar escalas por Atirador
		public static ResultSet getEscalasAtirador(int atiradorId) {
			BD.selecionarDatabase();
			
			String sql = "select * from Escala where atirador1Id = ? or atirador2Id = ? or atirador3Id = ?;";
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				ps = Conexao.getConexao().prepareStatement(sql);
				ps.setString(1, String.valueOf(atiradorId));
				ps.setString(2, String.valueOf(atiradorId));
				ps.setString(3, String.valueOf(atiradorId));
				rs = ps.executeQuery();
				return rs;
			} catch (SQLException e) {
				System.out.println("Erro ao pegar a Escala por Atirador: " + e.getMessage());
				return rs;
			}
		}
		
}
