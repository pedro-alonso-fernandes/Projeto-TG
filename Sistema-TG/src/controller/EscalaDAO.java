package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.BD;
import model.Data;
import model.Escala;
import model.Feriado;
import model.Folga;

public class EscalaDAO {

	public static void cadastrarEscala(Escala escala) {
		BD.selecionarDatabase();
		
		String sql = "insert into Escala (data, cor, monitorId, atirador1Id, atirador2Id, atirador3Id) values (?, ?, ?, ?, ?, ?);";
		
		PreparedStatement ps = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(escala.getData()));
			ps.setString(2, escala.getCor());
			ps.setString(3, String.valueOf(escala.getMonitorId()));
			ps.setString(4, String.valueOf(escala.getAtirador1Id()));
			ps.setString(5, String.valueOf(escala.getAtirador2Id()));
			ps.setString(6, String.valueOf(escala.getAtirador3Id()));
			ps.execute();
			
		} catch (SQLException e) {
			System.out.println("Erro ao criar nova escala: " + e.getMessage());
		}
	}
	
	//Pega todas as escalas
	public static ResultSet getEscalas() {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar todas as Escalas: " + e.getMessage());
			return rs;
		}
	}
	
	//Pega escala por ID
	public static ResultSet getEscala(int id) {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala where id = ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(id));
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a Escala pelo id: " + e.getMessage());
			return rs;
		}
	}
	
	//Pega escala por Data
	public static ResultSet getEscala(Date data) {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala where data = ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(data));
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a Escala pela data(úńica): " + e.getMessage());
			return rs;
		}
	}
	
	//Pega escalas apartir da Data informada
	public static ResultSet getEscalas(Date data) {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala where data >= ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(data));
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a Escala pela data(várias): " + e.getMessage());
			return rs;
		}
	}
	
	//Pega a escala da Semana
		public static ResultSet getEscalaSemana(Date data) {
			BD.selecionarDatabase();
			
			String sql = "select * from Escala where data >= ? and data <= ?;";
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			
			data = Data.primeiroDiaSemana(data);
			
			try {
				ps = Conexao.getConexao().prepareStatement(sql);
				ps.setString(1, formato.format(data));
				ps.setString(2, formato.format(Data.addDias(data, 6)));
				rs = ps.executeQuery();
				return rs;
			} catch (SQLException e) {
				System.out.println("Erro ao pegar a Escala da Semana: " + e.getMessage());
				return rs;
			}
		}
	
	//Pegar escalas por cor
	public static ResultSet getEscalas(String cor) {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala where cor = ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if(!cor.equalsIgnoreCase("vermelha") && !cor.equalsIgnoreCase("preta")) {
			System.out.println("Essa não é uma cor válida!");
			return rs;
		}
		else {
			
			try {
				ps = Conexao.getConexao().prepareStatement(sql);
				ps.setString(1, cor);
				rs = ps.executeQuery();
				return rs;
			} catch (SQLException e) {
				System.out.println("Erro ao pegar a Escala por cor: " + e.getMessage());
				return rs;
			}
			
		}
		
	}
	
	//Pegar escalas por Monitor
	public static ResultSet getEscalasMonitor(int monitorId) {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala where monitorId = ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(monitorId));
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a Escala por Monitor: " + e.getMessage());
			return rs;
		}
	}
	
	//Pegar escalas por Atirador
	public static ResultSet getEscalasAtirador(int atiradorId) {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala where atirador1Id = ? or atirador2Id = ? or atirador3Id = ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(atiradorId));
			ps.setString(2, String.valueOf(atiradorId));
			ps.setString(3, String.valueOf(atiradorId));
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a Escala por Atirador: " + e.getMessage());
			return rs;
		}
	}
	
	public static boolean verificarAtiradorEmEscala(int atiradorId) {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala where monitorId = ? or atirador1Id = ? or atirador2Id = ? or atirador3Id = ?;";
		
		boolean existencia = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(atiradorId));
			ps.setString(2, String.valueOf(atiradorId));
			ps.setString(3, String.valueOf(atiradorId));
			ps.setString(4, String.valueOf(atiradorId));
			rs = ps.executeQuery();
			existencia = rs.next();
			
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se o Atirador " + atiradorId + " está em alguma escala: " + e.getMessage());
			
		}
		
		return existencia;
	}
	
	//Pega escala por Data
	public static ResultSet getEscalaDataCor(Date data, String cor) {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala where data = ? and cor = ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(data));
			ps.setString(2, cor);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a Escala pela data e pela cor: " + e.getMessage());
			return rs;
		}
	}
	
	public static ResultSet getPrimeiraEscala() {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala limit 1";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a primeira Escala do BD: " + e.getMessage());
		}
		
		return rs;
	}
	
	public static ResultSet getUltimaEscala() {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala order by data desc limit 1";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a ultima Escala do BD: " + e.getMessage());
		}
		
		return rs;
	}
	
	// Retorna todas as Escalas com data maior que a informada
	public static ResultSet getEscalasDataMaior(Date data) {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala where data > ?;";
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(data));
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar Escalas com datas maiores do que a informada: " + e.getMessage());
			return rs;
		}
	}
	
	public static void apagarEscalasDataMaior(Date data) {
		BD.selecionarDatabase();
		
		String sql = "delete from Escala where data >= ?;";
		
		PreparedStatement ps = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(data));
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao apagar escalas por data: " + e.getMessage());
		}
		
	}
	
	public static boolean verificarExistenciaEscala() {
		BD.selecionarDatabase();
		
		ResultSet rs = getPrimeiraEscala();
		boolean escala = false;
		
		try {
			escala = rs.next();
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se há escala cadastrada no BD: " + e.getMessage());
		}
		
		return escala;
	}
	
	public static boolean verificarEscalaEmData(Date data) {
		BD.selecionarDatabase();
		
		String sql = "select * from Escala where data = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		boolean escala = false;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, formato.format(data));
			rs = ps.executeQuery();
			escala = rs.next();
			
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se tem Escala na data "+ formato2.format(data) + ": " + e.getMessage());
		}
		
		return escala;
	}
	
	public static boolean verificarEscalaNaSemana(Date data) {
		BD.selecionarDatabase();
		
		boolean escala = false;
		SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		
		ResultSet rs = getEscalaSemana(data);
		
		try {
			escala = rs.next();
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se a semana da data " + formato2.format(data) + " possui escala: " + e.getMessage());
		}
		
		return escala;
	}
	
	public static int getQtdGuardaAtirador(int atiradorId, Date data) {
		BD.selecionarDatabase();
		
		int qtdGuarda = Integer.MIN_VALUE;
		
//		String sql = "select count(*) from Escala where atirador1Id = ? or atirador2Id = ? or atirador3Id = ? and data <= ?;";
		String sql = "select  (select count(*) from Escala where monitorId = ? and data <= ?)"
				+ "    + (select count(*) from Escala where atirador1Id = ? and data <= ?)"
				+ "    + (select count(*) from Escala where atirador2Id = ? and data <= ?)"
				+ "    + (select count(*) from Escala where atirador3Id = ? and data <= ?) as total_ocorrencias;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(atiradorId));
			ps.setString(2, formato.format(data));
			ps.setString(3, String.valueOf(atiradorId));
			ps.setString(4, formato.format(data));
			ps.setString(5, String.valueOf(atiradorId));
			ps.setString(6, formato.format(data));
			ps.setString(7, String.valueOf(atiradorId));
			ps.setString(8, formato.format(data));
			rs = ps.executeQuery();
			if(rs.next()) {
				qtdGuarda = rs.getInt("total_ocorrencias");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a quantidades de guardas do Atirador: " + e.getMessage());
		}
		
		return qtdGuarda;
	}
	
}
