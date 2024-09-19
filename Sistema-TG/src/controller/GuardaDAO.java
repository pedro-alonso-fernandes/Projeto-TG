package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.BD;

public class GuardaDAO {

	public static void cadastrarGuarda(int[] guarda, String cor, Date data) {
		BD.selecionarDatabase();
		
//		BD.reiniciarTabelaGuarda(cor);
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		String sql = "insert into Guarda" + cor + " (valor, data, atiradorId) values (?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			for(int i = 0; i < guarda.length; i++) {
				ps.setString(1, String.valueOf(guarda[i]));
				ps.setString(2, formato.format(data));
				ps.setString(3, String.valueOf(i + 1));;
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
