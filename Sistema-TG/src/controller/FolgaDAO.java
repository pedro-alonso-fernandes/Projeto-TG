package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.BD;
import model.Data;
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
	
}
