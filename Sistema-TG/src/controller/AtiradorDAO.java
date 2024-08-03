package controller;

import java.sql.*;
import java.util.StringJoiner;

import model.Atirador;

public class AtiradorDAO {



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

	public static String getGuerraAtirador(int ID) {
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
	
	
	
	public static ResultSet getAtirador() {
		String sql = "use TG;";

		PreparedStatement ps = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao acessar o database TG: " + e.getMessage());
		}
		
		 sql = "select * from Atirador;";
		
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar todos os atiradores: " + e.getMessage());
			return rs;
		}
	}
	

}