package controller;

import java.sql.*;
import java.util.StringJoiner;

import model.Atirador;

public class AtiradorDAO {

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
				+ "nome varchar(100) not null," + "guerra varchar(30) not null," + "cargo varchar(20) not null)";

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Erro ao criar tabela Atirador: " + e.getMessage());
		}

		sql = "create table if not exists `TG`.`Escala`(" + "id int not null primary key," + "dia int not null,"
				+ "mes int not null," + "monitorId int not null," + "atirador1Id int not null,"
				+ "atirador2Id int not null," + "atirador3Id int not null,"
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

	public static void cadastrarAtirador(Atirador atirador) {
		String sql = "use TG;";

		PreparedStatement ps = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao acessar o database TG: " + e.getMessage());
		}

		sql = "insert into Atirador (id, nome, guerra, cargo) values (?, ?, ?, ?);";

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(atirador.getID()));
			ps.setString(2, atirador.getNome());
			ps.setString(3, atirador.getGuerra());
			ps.setString(4, atirador.getCargo());
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getNomeAtirador(int ID) {
		String sql = "use TG;";

		PreparedStatement ps = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao acessar o database TG: " + e.getMessage());
		}
		
		String nome = null;
		
		sql = "select * from Atirador where id = ?;";
		
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(ID));
			rs = ps.executeQuery();		
			rs.next();
			nome = rs.getString("guerra");
		} catch (SQLException e) {
			System.out.println("Erro ao buscar Atirador por ID: " + e.getMessage());
		}
		
		return nome;
	}
	
	
	
	
	public static void apagarTabela() {
		String sql = "drop table if exists TG.Atirador;";
		PreparedStatement ps = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar tabela Atirador: " + e.getMessage());
			;
		}

	}

	public static void apagarDatabase() {
		EscalaDAO.apagarTabela();
		apagarTabela();

		String sql = "drop database if exists TG;";
		PreparedStatement ps = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar database TG: " + e.getMessage());
			;
		}
	}

}