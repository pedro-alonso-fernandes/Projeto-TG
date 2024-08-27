package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import model.Feriado;

public class FeriadoDAO {

	public static void cadastrarFeriado(Feriado feriado) {
		BD.selecionarDatabase();

		String sql = "insert into Feriado (nome, data, tipo) values (?, ?, ?);";
		PreparedStatement ps = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, feriado.getNome());
			ps.setString(2, formato.format(feriado.getData()));
			ps.setString(3, feriado.getTipo());
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar Feriado: " + e.getMessage());
		}
	}
	
	public static void removerFeriado(int id) {
		BD.selecionarDatabase();
		
		String sql = "delete from Feriado where id = ?;";
		
		PreparedStatement ps = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(id));
			ps.execute();

			
		} catch (SQLException e) {
			System.out.println("Erro ao remover Feriado: " + e.getMessage());
		}
	
	}
	
	public static void editarFeriado(Feriado feriado) {
		BD.selecionarDatabase();
		
		String sql = "update Feriado set nome = ?, data = ?, tipo = ? where id = ?;";
		
		PreparedStatement ps = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, feriado.getNome());
			ps.setString(2, formato.format(feriado.getData()));
			ps.setString(3, feriado.getTipo());
			ps.setString(4, String.valueOf(feriado.getId()));
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao editar Feriado: " + e.getMessage());
		}
		
	}
	
	public static ResultSet getFeriados() {
		BD.selecionarDatabase();
		
		String sql = "select * from Feriado;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao pegar todos os feriados: " + e.getMessage());
		}
		
		return rs;
	}
	
	public static int getQtdFeriados() {
		BD.selecionarDatabase();
		
		String sql = "select count(*) from Feriado;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int qtd = 0;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			qtd = rs.getInt("count(*)");
	
		} catch (SQLException e) {
			System.out.println("Erro ao pegar quantidade de feriados: " + e.getMessage());
		}
		
		return qtd;
	}
	
}
