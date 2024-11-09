package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.BD;

public class GuardaDAO {

	public static void cadastrarGuarda(int[] guarda, String cor, Date data, int[] IDs) {
		BD.selecionarDatabase();
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		String sql = "insert into Guarda" + cor + " (valor, data, atiradorId) values (?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			for(int i = 0; i < guarda.length; i++) {
				ps.setString(1, String.valueOf(guarda[i]));
				ps.setString(2, formato.format(data));
				ps.setString(3, String.valueOf(IDs[i]));;
				ps.execute();
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar Guarda" + cor + " no BD: " + e.getMessage());
		}
		
	}
	
	public static ResultSet getGuardas(String cor) {
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
	
	public static ResultSet getGuardaPorData(String cor, Date data) {
		BD.selecionarDatabase();
		
		String sql = "select * from Guarda" + cor + " where data = ?;";
		PreparedStatement ps = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(data));
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao pegar todos Guarda " + cor + " pela data: " + e.getMessage());
		}
		
		return rs;
	}
	
	public static Date getDataUltimaGuarda(String cor) {
		BD.selecionarDatabase();
		
		String sql = "select data from Guarda" + cor + " order by data desc limit 1;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Date data = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			data = rs.getDate("data");
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a data da última Guarda " + cor + ": " + e.getMessage());
		}
		
		return data;
	}
	
	public static int getValorGuardaPorAtirador(String cor, Date data, int atiradorId) {
		BD.selecionarDatabase();
		
		int valor = Integer.MIN_VALUE;
		
		String sql = "select valor from Guarda" + cor + " where data = ? and atiradorId = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(data));
			ps.setString(2, String.valueOf(atiradorId));
			rs = ps.executeQuery();
			
			if(rs.next()) {
				valor = rs.getInt("valor");
			}
			else {
				valor = 0;
			}
			
			
		} catch (SQLException e) {
			System.out.println("Erro ao pegar o valor da Guarda " + cor + " de um Atirador: " + e.getMessage());
		}
		
		return valor;
	}
	
	public static void apagarGuardasDataMaior(String cor, Date data) {
		BD.selecionarDatabase();
		
		String sql = "delete from Guarda" + cor + " where data >= ?";
		PreparedStatement ps = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(data));
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar Guarda " + cor + " posteriores à data informada: " + e.getMessage());
		}
		
	}
	
	public static void apagarGuardasPassadas(String cor) {
		BD.selecionarDatabase();

		
		String sql = "delete from Guarda" + cor + " where data < ?";
		PreparedStatement ps = null;
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date dataHoje = new Date();
		Date dataUltimaGuarda = getDataUltimaGuarda(cor);
		
		Date dataFinal = null;
		if(dataUltimaGuarda.after(dataHoje)) {
			dataFinal = dataHoje;
		}
		else {
			dataFinal = dataUltimaGuarda;
		}
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(dataFinal));
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar Guarda " + cor + " passada: " + e.getMessage());
		}
		
	}
	
	public static Date getDataGuardaMaisProxima(String cor, Date data) {
		BD.selecionarDatabase();
		
		String sql = "select data from Guarda" + cor + " where data <= ? order by data desc limit 1;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Date dataGuarda = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(data));
			rs = ps.executeQuery();
			if(rs.next()) {
				dataGuarda = rs.getDate("data");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a data da última Guarda " + cor + ": " + e.getMessage());
		}
		
		return dataGuarda;
	}
	
}
