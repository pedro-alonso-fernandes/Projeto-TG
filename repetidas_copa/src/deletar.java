import java.awt.EventQueue;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class deletar extends JFrame {

	private JPanel contentPane;
	Connection conexao = null;
	private String SQL = "";
	private String DriveMySQL = "com.mysql.cj.jdbc.Driver";
	private String URLBD = "jdbc:mysql://localhost:3306";
	private String usuario = "root";
	private String senha = "pedro123";
	telaConsulta consulta;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deletar frame = new deletar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public deletar(telaConsulta tela) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 254);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblErroVocDeixou = new JLabel("Tem certeza que deseja deletar");
		lblErroVocDeixou.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblErroVocDeixou.setBounds(92, 45, 306, 27);
		contentPane.add(lblErroVocDeixou);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(deletar.class.getResource("/imagens/erro.png")));
		lblNewLabel_1.setBounds(26, 45, 66, 60);
		contentPane.add(lblNewLabel_1);
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.setFont(new Font("Dialog", Font.PLAIN, 20));
		cancelar.setBounds(262, 159, 136, 36);
		contentPane.add(cancelar);
		
		JLabel lblNewLabel = new JLabel("TODOS OS DADOS?");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(126, 73, 249, 27);
		contentPane.add(lblNewLabel);
		
		try {
			Class.forName(DriveMySQL);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		consulta = tela;
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				consulta.table.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"ID", "Nome", "C\u00F3digo", "Pa\u00EDs", "Tipo"
						}
					));
					consulta.table.getColumnModel().getColumn(0).setPreferredWidth(50);
					consulta.table.getColumnModel().getColumn(1).setPreferredWidth(299);
					consulta.table.getColumnModel().getColumn(2).setPreferredWidth(84);
					consulta.table.getColumnModel().getColumn(3).setPreferredWidth(136);
					consulta.table.getColumnModel().getColumn(4).setPreferredWidth(122);
					
				
				try {
					
					conexao = DriverManager.getConnection(URLBD, usuario, senha);
					Statement comando = conexao.createStatement();
					ResultSet resultado;
					
					SQL = "use Album";
					comando.execute(SQL);
					
					SQL = "drop table Figurinha";
					comando.execute(SQL);
					
					SQL = "create table if not exists `Album`.`Figurinha` ("
							+ "  `ID` int not null auto_increment,"
							+ "  `Nome` varchar(200) not null,"
							+ "  `Codigo` varchar(45) not null,"
							+ "  `Pais` varchar(45) not null,"
							+ "  `Tipo` varchar(45) not null,"
							+ "  primary key (`ID`));";
					comando.execute(SQL);
					
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
				dispose();
			}
		});
		btnDeletar.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnDeletar.setBounds(51, 161, 117, 36);
		contentPane.add(btnDeletar);
		
		this.setLocationRelativeTo(null);
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
}
