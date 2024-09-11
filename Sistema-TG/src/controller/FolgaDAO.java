package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import model.BD;
import model.Folga;

public class FolgaDAO {

	public static void cadastrarFolga(Folga folga){
		BD.selecionarDatabase();
		
		String sql = "insert into Folgas (nome, data) values (?, ?);";
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
	
}
