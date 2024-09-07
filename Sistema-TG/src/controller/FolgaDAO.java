package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.BD;

public class FolgaDAO {

	public static void cadastrarFolga(int[] folga, String cor) {
		BD.selecionarDatabase();
		
		BD.reiniciarTabelaFolga(cor);
		
		String sql = "insert into Folga" + cor + " (valor) values (?)";
		PreparedStatement ps = null;
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			for(int i = 0; i < folga.length; i++) {
				ps.setString(1, String.valueOf(folga[i]));
				ps.execute();
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar Folga" + cor + " no BD: " + e.getMessage());
		}
		
	}
	
	public static ResultSet getFolga(String cor) {
		BD.selecionarDatabase();
		
		String sql = "select * from Folga" + cor + ";";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao pegar todos as Folgas " + cor + "s: " + e.getMessage());
		}
		
		return rs;
	}
	
}
