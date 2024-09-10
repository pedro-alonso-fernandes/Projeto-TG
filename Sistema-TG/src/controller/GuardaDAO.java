package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.BD;

public class GuardaDAO {

	public static void cadastrarGuarda(int[] guarda, String cor) {
		BD.selecionarDatabase();
		
		BD.reiniciarTabelaGuarda(cor);
		
		String sql = "insert into Guarda" + cor + " (valor) values (?)";
		PreparedStatement ps = null;
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			for(int i = 0; i < guarda.length; i++) {
				ps.setString(1, String.valueOf(guarda[i]));
				ps.execute();
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar Guarda" + cor + " no BD: " + e.getMessage());
		}
		
	}
	
	public static ResultSet getGuarda(String cor) {
		BD.selecionarDatabase();
		
		String sql = "select * from Guarda" + cor + ";";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao pegar todos as Guardas " + cor + "s: " + e.getMessage());
		}
		
		return rs;
	}
	
}
