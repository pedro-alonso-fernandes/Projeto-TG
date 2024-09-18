package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Atirador;
import model.BD;

public class AtiradorDAO {

	public static void cadastrarAtirador(Atirador atirador) {
		String sql = "use TG;";

		PreparedStatement ps = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao acessar o database TG: " + e.getMessage());
		}

		sql = "insert into Atirador (id, nome, guerra, cargo, qtdGuarda) values (?, ?, ?, ?, 0);";

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(atirador.getID()));
			ps.setString(2, atirador.getNome());
			ps.setString(3, atirador.getGuerra());
			ps.setString(4, atirador.getCargo());
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar Atirador: " + e.getMessage());
		}
	}

	public static String getGuerraAtirador(int ID) {
		String sql = "use TG;";

		PreparedStatement ps = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao acessar o database TG: " + e.getMessage());
		}

		String nome = null;

		sql = "select * from Atirador where id = ?;";

		ResultSet rs = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(ID));
			rs = ps.executeQuery();
			rs.next();
			nome = rs.getString("guerra");
		} catch (SQLException e) {
			System.out.println("Erro ao buscar Atirador por ID: " + e.getMessage());
		}

		return nome;
	}

	// pega geral para a Escala
	public static ResultSet getAtiradores() {
		String sql = "use TG;";

		PreparedStatement ps = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao acessar o database TG: " + e.getMessage());
		}

		sql = "select * from Atirador;";

		ResultSet rs = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar todos os atiradores: " + e.getMessage());
			return rs;
		}
	}
	
	public static ResultSet getAtiradoresByMonitores() {
		BD.selecionarDatabase();

		String sql = "select * from Atirador order by cargo desc, id;";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar todos os atiradores: " + e.getMessage());
			return rs;
		}
	}

	// Pega atirador por ID
	public static ResultSet getAtirador(int id) {
		BD.selecionarDatabase();

		String sql = "select * from Atirador where id = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(id));
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.out.println("Erro ao pegar o Atirador pelo id: " + e.getMessage());
			return rs;
		}
	}

	// Apagar atirador selecionado
	public static void RemoverAtirador(int id) {
		BD.selecionarDatabase();
		
		String sql = "delete from Atirador where id = ?;";
		
		PreparedStatement ps = null;

		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(id));
			ps.execute();

			
		} catch (SQLException e) {
			System.out.println("Erro ao remover Atirador: " + e.getMessage());
		}
	
	}
	// Editar atirador selecionado
	public static void EditarAtirador(Atirador atirador , int id) {
		BD.selecionarDatabase();
		
		String sql = "update Atirador set id = ?, nome = ?, guerra = ?, cargo = ? where id = ?;";
		
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(atirador.getID()));
			ps.setString(2, atirador.getNome());
			ps.setString(3, atirador.getGuerra());
			ps.setString(4, atirador.getCargo());
			ps.setString(5, String.valueOf(id));
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao editar Atirador: " + e.getMessage());
		}
		
	}
	
	
	
	public static void cadastrarQtdGuarda(int id, int qtd) {
		BD.selecionarDatabase();
		
		String sql = "update Atirador set qtdGuarda = ? where id = ?;";
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(qtd));
			ps.setString(2, String.valueOf(id));
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar qtdGuarda: " + e.getMessage());
		}
	}
	
	public static void adicionarQtdGuarda(int id) {
		BD.selecionarDatabase();
		
		String sql = "update Atirador set qtdGuarda = qtdGuarda + 1 where id = ?;";
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			ps.setString(1, String.valueOf(id));
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao adicionar qtdGuarda: " + e.getMessage());
		}
	}
	
	public static ResultSet getQtdGuardas() {
		BD.selecionarDatabase();
		
		ResultSet rs = null;
		String sql = "select qtdGuarda from Atirador;";
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("Erro ao pegar Quantidade de Guardas dos atiradores: " + e.getMessage());
		}
		
		return rs;
	}
	
	public static int getIdUltimoAtirador() {
		BD.selecionarDatabase();
		
		int id = 0;
		ResultSet rs = null;
		String sql = "select id from Atirador order by id desc limit 1;";
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			id = rs.getInt("id");
		} catch (SQLException e) {
			System.out.println("Erro ao pegar id do ultimo atirador: " + e.getMessage());
		}
		
		return id;
	}
	
	public static int getQtdMonitores() {
		BD.selecionarDatabase();
		
		int qtd = 0;
		
		String sql = "select count(*) from Atirador where cargo = \"Monitor\";";
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			qtd = rs.getInt("count(*)");
			
		} catch (SQLException e) {
			System.out.println("Erro ao pegar quantidade de monitores: " + e.getMessage());
		}
		
		
		return qtd;
	}
	
	public static int getQtdAtiradoresGeral() {
		BD.selecionarDatabase();
		
		int qtd = 0;
		
		String sql = "select count(*) from Atirador;";
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = Conexao.getConexao().prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			qtd = rs.getInt("count(*)");
			
		} catch (SQLException e) {
			System.out.println("Erro ao pegar quantidade de atiradores (geral): " + e.getMessage());
		}
		
		
		return qtd;
	}
	
}