package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.BD;
import model.Data;
import model.Feriado;
import model.Folga;

public class FolgaDAO {

	public static void cadastrarFolga(Folga folga){
		BD.selecionarDatabase();
		
		String sql = "insert into Folga (nome, data) values (?, ?);";
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, folga.getNome());
			ps.setString(2, formato.format(folga.getData()));
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar Folga: " + e.getMessage());
		}
	}
	
	public static ResultSet getFolgas() {
		BD.selecionarDatabase();
		
		String sql = "select * from Folga;";
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao pegar todas as Folgas: " + e.getMessage());
		}
		
		
		return rs;
	}
	
	public static ResultSet getFolgasSemana(Date data) {
		BD.selecionarDatabase();
		
		String sql = "select * from Folga where data >= ? and data <= ?;";
		
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
			System.out.println("Erro ao pegar as Folgas da Semana: " + e.getMessage());
			return rs;
		}
	}
	public static ResultSet getFolgaData(Date data) {
		BD.selecionarDatabase();
		
		String sql = "select * from Folga where data = ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(data));
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a Folga por data: " + e.getMessage());
			return rs;
		}
	}
	public static void editarFolga(Folga folga) {
		BD.selecionarDatabase();
		
		String sql = "update Folga set nome = ?, data = ? where id = ?;";
		
		PreparedStatement ps = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, folga.getNome());
			ps.setString(2, formato.format(folga.getData()));
			ps.setString(3, String.valueOf(folga.getId()));
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao editar Folga: " + e.getMessage());
		}
		
	}
	
	public static void removerFolga(int id) {
		BD.selecionarDatabase();
		
		String sql = "delete from Folga where id = ?;";
		
		PreparedStatement ps = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(id));
			ps.execute();

			
		} catch (SQLException e) {
			System.out.println("Erro ao remover Folga: " + e.getMessage());
		}
	
	}
	
	public static void registrarEscala(Date data, boolean escala) {
		BD.selecionarDatabase();
		
		String sql = "update Folga set escala = ? where data = ?;";
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		PreparedStatement ps = null;
		
		int valor = escala ? 1 : 0; // Se escala for true, valor = 1; se não, valor = 0;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(valor));
			ps.setString(2, formato.format(data));
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao registrar que a Escala passou pela folga: " + e.getMessage());
		}
			
	}
	
}
