package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.BD;

public class AlteracaoDAO {

	public static void cadastrarAlteracao(String tipo) {
		BD.selecionarDatabase();
		
		String sql = "insert into Alteracao (tipo) values (?);";
		
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, tipo);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar Alteracao: " + e.getMessage());
		}
	}
	
	public static ResultSet getAlteracao() {
		String sql = "select * from Alteracao;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao pegar os elementos da tabela Alteracao: " + e.getMessage());
		}
		
		return rs;
	}
	
}
