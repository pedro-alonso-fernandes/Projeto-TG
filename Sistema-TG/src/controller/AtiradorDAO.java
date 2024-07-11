package controller;

import java.sql.*;

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
		
		sql = "create table if not exists `TG`.`Atirador`("
				+ "id int not null primary key,"
				+ "nome varchar(100) not null,"
				+ "cargo varchar(20) not null);";
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao criar tabela Atirador: " + e.getMessage());
		}
		
	}
	
	public static void cadastrarAtirador(Atirador atirador) {
		String sql = "use TG;";
		
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
			
		} catch (SQLException e) {
			System.out.println("Erro ao acessar o database TG: " + e.getMessage());;
		}
		
		sql = "insert into Atirador (id, nome, cargo) values (?, ?, ?)";
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(atirador.getID()));
			ps.setString(2, atirador.getNome());
			ps.setString(3, atirador.getCargo());
			
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void apagarTabela() {
		String sql = "drop table if exists TG.Atirador;";
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar tabela Atirador: " + e.getMessage());;
		}
		
	}
	
	public static void apagarDatabase() {
		apagarTabela();
		
		String sql = "drop database if exists TG;";
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar database TG: " + e.getMessage());;
		}
	}

}
