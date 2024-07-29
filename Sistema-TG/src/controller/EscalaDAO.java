package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Escala;

public class EscalaDAO {

	private static void selecionarDatabase() {
		String sql = "use TG";

		PreparedStatement ps = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao criar o database TG: " + e.getMessage());
		}

	}

	public static void criarBanco() {

		String sql = "create database if not exists TG;";

		PreparedStatement ps = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Erro ao criar o database TG: " + e.getMessage());
		}

		sql = "create table if not exists `TG`.`Atirador`(" + "id int not null primary key,"
				+ "nome varchar(100) not null," + "cargo varchar(20) not null);";

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Erro ao criar tabela Atirador: " + e.getMessage());
		}

		sql = "create table if not exists `TG`.`Escala`(" 
				+ "id int not null primary key auto_increment," 
				+ "data date not null,"
				+ "cor varchar(20) not null,"
				+ "monitorId int not null," 
				+ "atirador1Id int not null,"
				+ "atirador2Id int not null," 
				+ "atirador3Id int not null,"
				+ "constraint fk_Monitor_Escala foreign key (monitorId) references Atirador (id),"
				+ "constraint fk_Atirador1_Escala foreign key (atirador1Id) references Atirador (id),"
				+ "constraint fk_Atirador2_Escala foreign key (atirador2Id) references Atirador (id),"
				+ "constraint fk_Atirador3_Escala foreign key (atirador3Id) references Atirador (id));";

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao criar tabela Escala: " + e.getMessage());
		}

	}
	
	public static void apagarTabela() {
		String sql = "drop table if exists TG.Escala;";
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar tabela Escala: " + e.getMessage());;
		}
		
	}
	
	public static void apagarDatabase() {
		apagarTabela();
		AtiradorDAO.apagarTabela();
		
		String sql = "drop database if exists TG;";
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar database TG: " + e.getMessage());;
		}
	}
	
	public static void cadastrarEscala(Escala escala) {
		selecionarDatabase();
		
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
		selecionarDatabase();
		
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
		selecionarDatabase();
		
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
		selecionarDatabase();
		
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
			System.out.println("Erro ao pegar a Escala pela data: " + e.getMessage());
			return rs;
		}
	}
	
	//Pega escalas apartir da Data informada
	public static ResultSet getEscalas(Date data) {
		selecionarDatabase();
		
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
			System.out.println("Erro ao pegar a Escala pela data: " + e.getMessage());
			return rs;
		}
	}
	
	//Pegar escalas por cor
	public static ResultSet getEscalas(String cor) {
		selecionarDatabase();
		
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
				System.out.println("Erro ao pegar a Escala por cors: " + e.getMessage());
				return rs;
			}
			
		}
		
	}
	
	//Pegar escalas por Monitor
	public static ResultSet getEscalas(int monitorId) {
		selecionarDatabase();
		
		String sql = "select * from Escala where monitorId >= ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(monitorId));
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a Escala: " + e.getMessage());
			return rs;
		}
	}
}
