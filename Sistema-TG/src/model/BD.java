package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.Conexao;

public class BD {

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
				+ "guerra varchar(30) not null,"
				+ "cargo varchar(20) not null,"
				+ "qtdGuarda int null);";

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
				+ "constraint fk_Monitor_Escala foreign key (monitorId) references Atirador (id) on update cascade on delete cascade,"
				+ "constraint fk_Atirador1_Escala foreign key (atirador1Id) references Atirador (id) on update cascade on delete cascade,"
				+ "constraint fk_Atirador2_Escala foreign key (atirador2Id) references Atirador (id) on update cascade on delete cascade,"
				+ "constraint fk_Atirador3_Escala foreign key (atirador3Id) references Atirador (id) on update cascade on delete cascade);";

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao criar tabela Escala: " + e.getMessage());
		}
		
		sql = "create table if not exists `TG`.`Feriado`(" 
				+ "id int not null primary key auto_increment," 
				+ "nome varchar(50) not null,"
				+ "data date not null,"
				+ "tipo varchar(15) not null);";

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao criar tabela Feriado: " + e.getMessage());
		}
		
		sql = "create table if not exists `TG`.`Folga`(" 
				+ "id int not null primary key auto_increment," 
				+ "nome varchar(50) not null,"
				+ "data date not null);";
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao criar tabela Folga: " + e.getMessage());
		}
		
		sql = "create table if not exists `TG`.`GuardaVermelha`(" 
				+ "id int not null primary key auto_increment," 
				+ "valor int not null,"
				+ "data date not null,"
				+ "atiradorId int not null,"
				+ "constraint fk_Atirador_GuardaVermeha foreign key (atiradorId) references Atirador (id) on update cascade on delete cascade);";

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao criar tabela GuardaVermelha: " + e.getMessage());
		}
		
		sql = "create table if not exists `TG`.`GuardaPreta`(" 
				+ "id int not null primary key auto_increment," 
				+ "valor int not null,"
				+ "data date not null,"
				+ "atiradorId int not null,"
				+ "constraint fk_Atirador_GuardaPreta foreign key (atiradorId) references Atirador (id) on update cascade on delete cascade);";

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao criar tabela GuardaPreta: " + e.getMessage());
		}

	}
	
	public static void selecionarDatabase() {
		String sql = "use TG";

		PreparedStatement ps = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao criar o database TG: " + e.getMessage());
		}

	}
	
	public static void apagarTabelaEscala() {
		String sql = "drop table if exists TG.Escala;";
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar tabela Escala: " + e.getMessage());;
		}
		
	}
	
	public static void reiniciarTabelaEscala() {
		apagarTabelaEscala();
		
		
		String sql = "create table if not exists `TG`.`Escala`(" 
				+ "id int not null primary key auto_increment," 
				+ "data date not null,"
				+ "cor varchar(20) not null,"
				+ "monitorId int not null," 
				+ "atirador1Id int not null,"
				+ "atirador2Id int not null," 
				+ "atirador3Id int not null,"
				+ "constraint fk_Monitor_Escala foreign key (monitorId) references Atirador (id) on update cascade on delete cascade,"
				+ "constraint fk_Atirador1_Escala foreign key (atirador1Id) references Atirador (id) on update cascade on delete cascade,"
				+ "constraint fk_Atirador2_Escala foreign key (atirador2Id) references Atirador (id) on update cascade on delete cascade,"
				+ "constraint fk_Atirador3_Escala foreign key (atirador3Id) references Atirador (id) on update cascade on delete cascade);";
		
		PreparedStatement ps = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao criar tabela Escala: " + e.getMessage());
		}
		
	}
	
	public static void apagarTabelaAtirador() {
		String sql = "drop table if exists TG.Atirador;";
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar tabela Atirador: " + e.getMessage());;
		}
		
	}
	
	public static void apagarTabelaFeriado() {
		String sql = "drop table if exists TG.Feriados;";
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar tabela Feriados: " + e.getMessage());;
		}
		
	}
	
	public static void apagarTabelaFolga() {
		String sql = "drop table if exists TG.Folga;";
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar tabela Folga: " + e.getMessage());;
		}
		
	}
	
	public static void apagarTabelaGuardaVermelha() {
		String sql = "drop table if exists TG.GuardaVermelha;";
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar tabela GuardaVermelha: " + e.getMessage());;
		}
		
	}
	
	public static void apagarTabelaGuardaPreta() {
		String sql = "drop table if exists TG.GuardaPreta;";
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar tabela GuardaPreta: " + e.getMessage());;
		}
		
	}
	
	public static void reiniciarTabelaGuarda(String cor) {
		String sql = "";
		PreparedStatement ps = null;
		
		
		if(cor.equals("Preta")) {
			apagarTabelaGuardaPreta();
			
		}
		else if(cor.equals("Vermelha")) {
			apagarTabelaGuardaVermelha();
		}
		
		sql = "create table if not exists `TG`.`Guarda" + cor + "`(" 
				+ "id int not null primary key auto_increment," 
				+ "valor int not null,"
				+ "data date not null,"
				+ "atiradorId int not null,"
				+ "constraint fk_Atirador_Guarda" + cor + " foreign key (atiradorId) references Atirador (id) on update cascade on delete cascade);";
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao reiniciar a Tabela Guarda " + cor + ": " + e.getMessage());
		}
		
	}
	
	public static void apagarDatabase() {
		apagarTabelaGuardaVermelha();
		apagarTabelaGuardaPreta();
		apagarTabelaFeriado();
		apagarTabelaFolga();
		apagarTabelaEscala();
		apagarTabelaAtirador();
		
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
