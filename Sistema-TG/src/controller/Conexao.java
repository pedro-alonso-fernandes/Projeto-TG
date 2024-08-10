package controller;

import java.sql.*;

public class Conexao {

	private static String DriveMySQL = "com.mysql.cj.jdbc.Driver";
	private static String URLBD = "jdbc:mysql://localhost:3306";
	private static String usuario = "root";
	private static String senha = "pedro123";
	private static Connection conexao;
	
	public static Connection getConexao() {
		
		
		try {
			Class.forName(DriveMySQL);
			if(conexao == null) {
				conexao = DriverManager.getConnection(URLBD, usuario, senha);
				return conexao;
			} else {
				return conexao;
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Erro no Class.forName: " + e.getMessage());
			return null;
			
		} catch (SQLException s) {
			System.out.println("Erro ao criar banco de dados: " + s.getMessage());
			return null;
		} 
		
		
	}
}
