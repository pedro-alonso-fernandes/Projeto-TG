import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JList;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class telaConsulta extends JFrame {

	protected JPanel contentPane;
	protected JTable table;
	private String SQL = "", SQL2 = "", SQL3 = "", SQL4 = "";
	private String DriveMySQL = "com.mysql.cj.jdbc.Driver";
	private String URLBD = "jdbc:mysql://localhost:3306";
	private String usuario = "root";
	private String senha = "pedro123";
	Connection conexao = null;
	figurinha Figurinha;
	private JTextField nome2;
	private JTextField ID;
	private JTextField nome;
	private JTextField Nome_txt;
	private JTextField Pais_txt;
	private JTextField Codigo_txt;
	private JTextField ID2;
	private JTextField Nome2;
	private JTextField pais2;

	/**
	 * Launch the application.
	 */

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { telaConsulta frame = new
	 * telaConsulta(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */

	public void criarBancoDeDados() {

		try {
			Class.forName(DriveMySQL);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			conexao = DriverManager.getConnection(URLBD, usuario, senha);
			Statement comando = conexao.createStatement();

			SQL = "create database if not exists Album;";
			comando.execute(SQL);

			SQL = "use Album;";
			comando.execute(SQL);

			SQL = "create table if not exists `Album`.`Figurinha` (" + "  `ID` int not null auto_increment,"
					+ "  `Nome` varchar(200) not null," + "  `Codigo` varchar(45) not null,"
					+ "  `Pais` varchar(45) not null," + "  `Tipo` varchar(45) not null," + "  primary key (`ID`));";
			comando.execute(SQL);
		} catch (SQLException et) {

		}

	}

	public telaConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Dialog", Font.BOLD, 12));
		tabbedPane.setBounds(12, 12, 972, 364);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Figurinhas", null, scrollPane, null);

		table = new JTable();
		table.setFont(new Font("Dialog", Font.BOLD, 12));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome", "C\u00F3digo", "Pa\u00EDs", "Tipo" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(299);
		table.getColumnModel().getColumn(2).setPreferredWidth(84);
		table.getColumnModel().getColumn(3).setPreferredWidth(136);
		table.getColumnModel().getColumn(4).setPreferredWidth(122);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Busca", null, panel, null);
		panel.setLayout(null);

		JLabel lblBuscarPorTema = new JLabel("Digite um Nome, País ou/e Tipo para Buscar");
		lblBuscarPorTema.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblBuscarPorTema.setBounds(282, 12, 464, 22);
		panel.add(lblBuscarPorTema);

		nome2 = new JTextField();
		nome2.setFont(new Font("Dialog", Font.PLAIN, 18));
		nome2.setBounds(326, 80, 365, 37);
		panel.add(nome2);
		nome2.setColumns(10);

		JComboBox tipo2 = new JComboBox();
		tipo2.setModel(new DefaultComboBoxModel(new String[] { "  Jogador", "  Seleção", "  Escudo", "  FWC" }));
		tipo2.setFont(new Font("Dialog", Font.PLAIN, 18));
		tipo2.setBounds(429, 195, 190, 33);
		tipo2.setSelectedItem(null);
		panel.add(tipo2);

		try {
			Class.forName(DriveMySQL);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		JButton buscar = new JButton("Buscar");
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean nome = true, pais = true, tipo = true, sql = true;
				try {
					conexao = DriverManager.getConnection(URLBD, usuario, senha);
					Statement comando = conexao.createStatement();
					ResultSet resultado;

					SQL = "use Album";
					comando.execute(SQL);

					if (nome2.getText().equals("")) {
						nome = false;
					}
					if (pais2.getText().equals("")) {
						pais = false;
					}
					if (String.valueOf(tipo2.getSelectedItem()).equals("null")) {
						tipo = false;
					}

					// 1
					if (nome == true && pais == false && tipo == false) {
						SQL = "select * from Figurinha where Nome LIKE '%" + nome2.getText() + "%';";
					}
					if (nome == false && pais == true && tipo == false) {
						SQL = "select * from Figurinha where Pais LIKE '%" + pais2.getText() + "%';";
					}
					if (nome == false && pais == false && tipo == true) {
						SQL = "select * from Figurinha where Tipo LIKE '%" + String.valueOf(tipo2.getSelectedItem())
								+ "%';";
					}

					// 2
					// Nome
					if (nome == true && pais == true && tipo == false) {
						SQL = "select * from Figurinha where Nome LIKE '%" + nome2.getText() + "%' and Pais LIKE '%"
								+ pais2.getText() + "%';";
					}
					if (nome == true && pais == false && tipo == true) {
						SQL = "select * from Figurinha where Nome LIKE '%" + nome2.getText() + "%' and Tipo LIKE '%"
								+ String.valueOf(tipo2.getSelectedItem()) + "%';";
					}
					// Pais
					if (nome == false && pais == true && tipo == true) {
						SQL = "select * from Figurinha where Nome LIKE '%" + pais2.getText() + "%' and Tipo LIKE '%"
								+ String.valueOf(tipo2.getSelectedItem()) + "%';";
					}

					// 3
					if (nome == true && pais == true && tipo == true) {
						SQL = "select * from Figurinha where Nome LIKE '%" + nome2.getText() + "%' and Pais LIKE '%"
								+ pais2.getText() + "%' and Tipo LIKE '%" + String.valueOf(tipo2.getSelectedItem())
								+ "%';";
					}

					if (SQL == "use Album") {
						sql = false;
					}

					table.setModel(new DefaultTableModel(new Object[][] {},
							new String[] { "ID", "Nome", "C\u00F3digo", "Pa\u00EDs", "Tipo" }));
					table.getColumnModel().getColumn(0).setPreferredWidth(50);
					table.getColumnModel().getColumn(1).setPreferredWidth(299);
					table.getColumnModel().getColumn(2).setPreferredWidth(84);
					table.getColumnModel().getColumn(3).setPreferredWidth(136);
					table.getColumnModel().getColumn(4).setPreferredWidth(122);

					resultado = comando.executeQuery(SQL);
					while (resultado.next()) {
						Figurinha.setID(resultado.getInt("ID"));
						Figurinha.setNome(resultado.getString("Nome"));
						Figurinha.setCod(resultado.getString("Codigo"));
						Figurinha.setPais(resultado.getString("Pais"));
						Figurinha.setTipo(resultado.getString("Tipo"));

						DefaultTableModel modelo = (DefaultTableModel) table.getModel();
						modelo.addRow(new Object[] { Figurinha.getID(), Figurinha.getNome(), Figurinha.getCod(),
								Figurinha.getPais(), Figurinha.getTipo() });

						table.setModel(modelo);
					}

				} catch (SQLException et) {

				}

				if (sql == true) {
					nome2.setText("");
					pais2.setText("");
					tipo2.setSelectedItem(null);

					confirmacao tela = new confirmacao();
					tela.setVisible(true);
				}

			}
		});
		buscar.setFont(new Font("Dialog", Font.PLAIN, 20));
		buscar.setBounds(444, 275, 175, 37);
		panel.add(buscar);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNome.setBounds(238, 91, 70, 15);
		panel.add(lblNome);

		pais2 = new JTextField();
		pais2.setFont(new Font("Dialog", Font.PLAIN, 18));
		pais2.setBounds(381, 143, 282, 37);
		panel.add(pais2);
		pais2.setColumns(10);

		JLabel lblPas = new JLabel("País:");
		lblPas.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblPas.setBounds(317, 154, 46, 15);
		panel.add(lblPas);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblTipo.setBounds(356, 204, 55, 15);
		panel.add(lblTipo);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Deletar", null, panel_1, null);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("Deletar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean id = true, nomes = true, sql = true;

				if (ID.getText().equals("")) {
					id = false;
				}
				if (nome.getText().equals("")) {
					nomes = false;
				}

				try {
					conexao = DriverManager.getConnection(URLBD, usuario, senha);
					Statement comando = conexao.createStatement();
					ResultSet resultado;

					SQL = "use Album";
					comando.execute(SQL);

					if (id == false && nomes == true) {
						SQL = "delete from Figurinha where Nome='" + nome.getText() + "';";
					}

					if (id == true && nomes == false) {
						SQL = "delete from Figurinha where ID='" + ID.getText() + "';";
					}

					if (id == true && nomes == true) {
						SQL = "delete from Figurinha where Nome='" + nome.getText() + "' and ID='" + ID.getText()
								+ "';";
					}

					comando.execute(SQL);
					if (SQL == "use Album") {
						sql = false;
					}

					SQL = "select * from Figurinha";

					table.setModel(new DefaultTableModel(new Object[][] {},
							new String[] { "ID", "Nome", "C\u00F3digo", "Pa\u00EDs", "Tipo" }));
					table.getColumnModel().getColumn(0).setPreferredWidth(50);
					table.getColumnModel().getColumn(1).setPreferredWidth(299);
					table.getColumnModel().getColumn(2).setPreferredWidth(84);
					table.getColumnModel().getColumn(3).setPreferredWidth(136);
					table.getColumnModel().getColumn(4).setPreferredWidth(122);

					resultado = comando.executeQuery(SQL);
					while (resultado.next()) {
						Figurinha.setID(resultado.getInt("ID"));
						Figurinha.setNome(resultado.getString("Nome"));
						Figurinha.setCod(resultado.getString("Codigo"));
						Figurinha.setPais(resultado.getString("Pais"));
						Figurinha.setTipo(resultado.getString("Tipo"));

						DefaultTableModel modelo = (DefaultTableModel) table.getModel();
						modelo.addRow(new Object[] { Figurinha.getID(), Figurinha.getNome(), Figurinha.getCod(),
								Figurinha.getPais(), Figurinha.getTipo() });

						table.setModel(modelo);
					}

					if (sql == true) {
						ID.setText("");
						nome.setText("");

						confirmacao tela = new confirmacao();
						tela.setVisible(true);
					}

				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnNewButton.setBounds(371, 242, 171, 34);
		panel_1.add(btnNewButton);
		
//		JLabel lblTeste = new JLabel("");
//		lblTeste.setBounds(54, 101, 70, 15);
//		panel_1.add(lblTeste);

		ID = new JTextField();
		ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int codigo = e.getKeyChar();

				if (codigo != 48 && codigo != 49 && codigo != 50 && codigo != 51 && codigo != 52 && codigo != 53
						&& codigo != 54 && codigo != 55 && codigo != 56 && codigo != 57 && codigo != 8
						&& codigo != 65535 && codigo != 10) {
					ID.setText("");
				}
				
				//lblTeste.setText("" + codigo);
			}

			@Override
			public void keyPressed(KeyEvent e) {
//				int codigo = e.getKeyChar();
//
//				if (codigo != 48 && codigo != 49 && codigo != 50 && codigo != 51 && codigo != 52 && codigo != 53
//						&& codigo != 54 && codigo != 55 && codigo != 56 && codigo != 57 && codigo != 8
//						&& codigo != 65535 && codigo != 10) {
//					ID.setText("");
//				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
//				int codigo = e.getKeyChar();
//
//				if (codigo != 48 && codigo != 49 && codigo != 50 && codigo != 51 && codigo != 52 && codigo != 53
//						&& codigo != 54 && codigo != 55 && codigo != 56 && codigo != 57 && codigo != 8
//						&& codigo != 65535 && codigo != 10) {
//					ID.setText("");
//				}
			}
		});
		ID.setFont(new Font("Dialog", Font.PLAIN, 18));
		ID.setBounds(314, 113, 241, 34);
		panel_1.add(ID);
		ID.setColumns(10);

		nome = new JTextField();
		nome.setFont(new Font("Dialog", Font.PLAIN, 18));
		nome.setBounds(271, 181, 365, 34);
		panel_1.add(nome);
		nome.setColumns(10);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel.setBounds(271, 116, 33, 23);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(204, 186, 64, 24);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Digite o ID ou/e o Nome da figurinha que deseja remover da tabela");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(125, 24, 707, 34);
		panel_1.add(lblNewLabel_2);
		

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Alterar", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel_2_1 = new JLabel("Digite o novo Nome, Código, País ou/e Tipo da figurinha");
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(184, 10, 578, 34);
		panel_2.add(lblNewLabel_2_1);

		Nome_txt = new JTextField();
		Nome_txt.setFont(new Font("Dialog", Font.PLAIN, 18));
		Nome_txt.setBounds(75, 56, 365, 34);
		panel_2.add(Nome_txt);
		Nome_txt.setColumns(10);

		Pais_txt = new JTextField();
		Pais_txt.setFont(new Font("Dialog", Font.PLAIN, 18));
		Pais_txt.setBounds(587, 56, 211, 35);
		panel_2.add(Pais_txt);
		Pais_txt.setColumns(10);

		Codigo_txt = new JTextField();
		Codigo_txt.setFont(new Font("Dialog", Font.PLAIN, 18));
		Codigo_txt.setBounds(142, 112, 203, 34);
		panel_2.add(Codigo_txt);
		Codigo_txt.setColumns(10);

		JComboBox Tipo_txt = new JComboBox();
		Tipo_txt.setModel(new DefaultComboBoxModel(new String[] { "  Jogador", "  Seleção", "  Escudo", "  FWC" }));
		Tipo_txt.setFont(new Font("Dialog", Font.PLAIN, 18));
		Tipo_txt.setBounds(587, 113, 188, 33);
		Tipo_txt.setSelectedItem(null);
		panel_2.add(Tipo_txt);

		JLabel lblNewLabel_3 = new JLabel("Nome:");
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(12, 66, 63, 14);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Código:");
		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(48, 122, 76, 24);
		panel_2.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("País:");
		lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(521, 66, 48, 14);
		panel_2.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Tipo:");
		lblNewLabel_6.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(514, 122, 63, 24);
		panel_2.add(lblNewLabel_6);

		JButton btnNewButton_1 = new JButton("Alterar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean id = true, Tipo2 = true, Nome = true, Codigo = true, Pais = true, Tipo = true, sql = true,
						sql2 = true, sql3 = true, sql4 = true;
				if (Nome_txt.getText().equals("")) {
					Nome = false;
				}

				if (Codigo_txt.getText().equals("")) {
					Codigo = false;
				}

				if (Pais_txt.getText().equals("")) {
					Pais = false;
				}

				if (String.valueOf(Tipo_txt.getSelectedItem()).equals("null")) {
					Tipo = false;
				}

				if (ID2.getText().equals("")) {
					id = false;
				}

				if (Nome2.getText().equals("")) {
					Tipo2 = false;
				}

				try {
					conexao = DriverManager.getConnection(URLBD, usuario, senha);
					Statement comando = conexao.createStatement();
					ResultSet resultado;

					SQL = "use Album";
					comando.execute(SQL);

					// Por ID
					if (id == true && Tipo2 == false) {
						// 1
						if (Nome == true && Codigo == false && Pais == false && Tipo == false) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
						}

						if (Nome == false && Codigo == true && Pais == false && Tipo == false) {
							SQL = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "';";
						}

						if (Nome == false && Codigo == false && Pais == true && Tipo == false) {
							SQL = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
						}

						if (Nome == false && Codigo == false && Pais == false && Tipo == true) {
							SQL = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "';";
						}

						// 2
						// Nome
						if (Nome == true && Codigo == true && Pais == false && Tipo == false) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
							SQL2 = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "';";
						}

						if (Nome == true && Codigo == false && Pais == true && Tipo == false) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
							SQL2 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
						}

						if (Nome == true && Codigo == false && Pais == false && Tipo == true) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
							SQL2 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "';";
						}

						// Codigo
						if (Nome == false && Codigo == true && Pais == true && Tipo == false) {
							SQL = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "';";
							SQL2 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
						}

						if (Nome == false && Codigo == true && Pais == false && Tipo == true) {
							SQL = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "';";
							SQL2 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "';";
						}

						// País
						if (Nome == false && Codigo == false && Pais == true && Tipo == true) {
							SQL = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
							SQL2 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "';";
						}

						// 3
						// Nome e Codigo
						if (Nome == true && Codigo == true && Pais == true && Tipo == false) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
							SQL2 = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "';";
							SQL3 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
						}

						if (Nome == true && Codigo == true && Pais == false && Tipo == true) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
							SQL2 = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "';";
							SQL3 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "';";
						}

						// Nome e País
						if (Nome == true && Codigo == false && Pais == true && Tipo == true) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
							SQL2 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
							SQL3 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "';";
						}

						// Codigo e País
						if (Nome == false && Codigo == true && Pais == true && Tipo == true) {
							SQL = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "';";
							SQL2 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
							SQL3 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "';";
						}

						// 4
						if (Nome == true && Codigo == true && Pais == true && Tipo == true) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
							SQL2 = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "';";
							SQL3 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "';";
							SQL4 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "';";
						}
					}

					// Por nome
					if (id == false && Tipo2 == true) {
						// 1
						if (Nome == true && Codigo == false && Pais == false && Tipo == false) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
						}

						if (Nome == false && Codigo == true && Pais == false && Tipo == false) {
							SQL = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
						}

						if (Nome == false && Codigo == false && Pais == true && Tipo == false) {
							SQL = "update Figurinha set Pais='" + Pais_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
						}

						if (Nome == false && Codigo == false && Pais == false && Tipo == true) {
							SQL = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where Nome='" + Nome2.getText() + "';";
						}

						// 2
						// Nome
						if (Nome == true && Codigo == true && Pais == false && Tipo == false) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL2 = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
						}

						if (Nome == true && Codigo == false && Pais == true && Tipo == false) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL2 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
						}

						if (Nome == true && Codigo == false && Pais == false && Tipo == true) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL2 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where Nome='" + Nome2.getText() + "';";
						}

						// Codigo
						if (Nome == false && Codigo == true && Pais == true && Tipo == false) {
							SQL = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL2 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
						}

						if (Nome == false && Codigo == true && Pais == false && Tipo == true) {
							SQL = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL2 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where Nome='" + Nome2.getText() + "';";
						}

						// País
						if (Nome == false && Codigo == false && Pais == true && Tipo == true) {
							SQL = "update Figurinha set Pais='" + Pais_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL2 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where Nome='" + Nome2.getText() + "';";
						}

						// 3
						// Nome e Codigo
						if (Nome == true && Codigo == true && Pais == true && Tipo == false) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL2 = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL3 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
						}

						if (Nome == true && Codigo == true && Pais == false && Tipo == true) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL2 = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL3 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where Nome='" + Nome2.getText() + "';";
						}

						// Nome e País
						if (Nome == true && Codigo == false && Pais == true && Tipo == true) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL2 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL3 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where Nome='" + Nome2.getText() + "';";
						}

						// Codigo e País
						if (Nome == false && Codigo == true && Pais == true && Tipo == true) {
							SQL = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL2 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL3 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where Nome='" + Nome2.getText() + "';";
						}

						// 4
						if (Nome == true && Codigo == true && Pais == true && Tipo == true) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL2 = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL3 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where Nome='"
									+ Nome2.getText() + "';";
							SQL4 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where Nome='" + Nome2.getText() + "';";
						}
					}

					// Por Id e Tema
					if (id == true && Tipo2 == true) {
						// 1
						if (Nome == true && Codigo == false && Pais == false && Tipo == false) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
						}

						if (Nome == false && Codigo == true && Pais == false && Tipo == false) {
							SQL = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
						}

						if (Nome == false && Codigo == false && Pais == true && Tipo == false) {
							SQL = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
						}

						if (Nome == false && Codigo == false && Pais == false && Tipo == true) {
							SQL = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
						}

						// 2
						// Nome
						if (Nome == true && Codigo == true && Pais == false && Tipo == false) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
							SQL2 = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
						}

						if (Nome == true && Codigo == false && Pais == true && Tipo == false) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
							SQL2 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
						}

						if (Nome == true && Codigo == false && Pais == false && Tipo == true) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
							SQL2 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
						}

						// Codigo
						if (Nome == false && Codigo == true && Pais == true && Tipo == false) {
							SQL = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
							SQL2 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
						}

						if (Nome == false && Codigo == true && Pais == false && Tipo == true) {
							SQL = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
							SQL2 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
						}

						// País
						if (Nome == false && Codigo == false && Pais == true && Tipo == true) {
							SQL = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
							SQL2 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
						}

						// 3
						// Nome e Codigo
						if (Nome == true && Codigo == true && Pais == true && Tipo == false) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
							SQL2 = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
							SQL3 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
						}

						if (Nome == true && Codigo == true && Pais == false && Tipo == true) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
							SQL2 = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
							SQL3 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
						}

						// Nome e País
						if (Nome == true && Codigo == false && Pais == true && Tipo == true) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
							SQL2 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
							SQL3 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
						}

						// Codigo e País
						if (Nome == false && Codigo == true && Pais == true && Tipo == true) {
							SQL = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
							SQL2 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
							SQL3 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
						}

						// 4
						if (Nome == true && Codigo == true && Pais == true && Tipo == true) {
							SQL = "update Figurinha set Nome='" + Nome_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
							SQL2 = "update Figurinha set Codigo='" + Codigo_txt.getText() + "' where ID='"
									+ ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
							SQL3 = "update Figurinha set Pais='" + Pais_txt.getText() + "' where ID='" + ID2.getText()
									+ "' and Nome='" + Nome2.getText() + "';";
							SQL4 = "update Figurinha set Tipo='" + String.valueOf(Tipo_txt.getSelectedItem())
									+ "' where ID='" + ID2.getText() + "' and Nome='" + Nome2.getText() + "';";
						}
					}
					comando.execute(SQL);
					if (SQL == "use Album") {
						sql = false;
					}

					if (SQL2 != "") {
						comando.execute(SQL2);
					}

					if (SQL3 != "") {
						comando.execute(SQL3);
					}

					if (SQL4 != "") {
						comando.execute(SQL4);
					}

					SQL = "select * from Figurinha";

					table.setModel(new DefaultTableModel(new Object[][] {},
							new String[] { "ID", "Nome", "C\u00F3digo", "Pa\u00EDs", "Tipo" }));
					table.getColumnModel().getColumn(0).setPreferredWidth(50);
					table.getColumnModel().getColumn(1).setPreferredWidth(299);
					table.getColumnModel().getColumn(2).setPreferredWidth(84);
					table.getColumnModel().getColumn(3).setPreferredWidth(136);
					table.getColumnModel().getColumn(4).setPreferredWidth(122);

					resultado = comando.executeQuery(SQL);
					while (resultado.next()) {
						Figurinha.setID(resultado.getInt("ID"));
						Figurinha.setNome(resultado.getString("Nome"));
						Figurinha.setCod(resultado.getString("Codigo"));
						Figurinha.setPais(resultado.getString("Pais"));
						Figurinha.setTipo(resultado.getString("Tipo"));

						DefaultTableModel modelo = (DefaultTableModel) table.getModel();
						modelo.addRow(new Object[] { Figurinha.getID(), Figurinha.getNome(), Figurinha.getCod(),
								Figurinha.getPais(), Figurinha.getTipo() });

						table.setModel(modelo);
					}

					if (sql == true) {
						Nome_txt.setText("");
						Codigo_txt.setText("");
						Pais_txt.setText("");
						Tipo_txt.setSelectedItem(null);
						ID2.setText("");
						Nome2.setText("");

						confirmacao tela = new confirmacao();
						tela.setVisible(true);
					}

				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnNewButton_1.setBounds(397, 291, 125, 34);
		panel_2.add(btnNewButton_1);

		ID2 = new JTextField();
		ID2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int codigo = e.getKeyChar();

				if (codigo != 48 && codigo != 49 && codigo != 50 && codigo != 51 && codigo != 52 && codigo != 53
						&& codigo != 54 && codigo != 55 && codigo != 56 && codigo != 57 && codigo != 8
						&& codigo != 65535 && codigo != 10) {
					ID2.setText("");
				}
			}
		});
		ID2.setFont(new Font("Dialog", Font.PLAIN, 18));
		ID2.setBounds(142, 238, 203, 34);
		panel_2.add(ID2);
		ID2.setColumns(10);

		Nome2 = new JTextField();
		Nome2.setFont(new Font("Dialog", Font.PLAIN, 18));
		Nome2.setBounds(521, 240, 365, 30);
		panel_2.add(Nome2);
		Nome2.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("ID:");
		lblNewLabel_7.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(100, 245, 32, 14);
		panel_2.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Nome:");
		lblNewLabel_8.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel_8.setBounds(452, 248, 60, 14);
		panel_2.add(lblNewLabel_8);

		JLabel lblNewLabel_2_2 = new JLabel("Identifique a figurinha que deseja alterar pelo ID ou/e pelo Nome");
		lblNewLabel_2_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(127, 181, 669, 34);
		panel_2.add(lblNewLabel_2_2);

		JButton deletar2 = new JButton("Deletar todos os dados");
		deletar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaConsulta consulta = new telaConsulta();
				deletar dados = new deletar(consulta);
				dados.setVisible(true);
			}
		});
		deletar2.setFont(new Font("Dialog", Font.PLAIN, 18));
		deletar2.setBounds(32, 388, 263, 43);
		contentPane.add(deletar2);

		JButton encerrar = new JButton("Fechar Janela");
		encerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		encerrar.setFont(new Font("Dialog", Font.PLAIN, 18));
		encerrar.setBounds(713, 388, 235, 43);
		contentPane.add(encerrar);

		JButton mostrar = new JButton("Mostrar todos os dados");
		mostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					conexao = DriverManager.getConnection(URLBD, usuario, senha);
					Statement comando = conexao.createStatement();
					ResultSet resultado;

					SQL = "use Album";
					comando.execute(SQL);

					SQL = "select * from Figurinha";

					table.setModel(new DefaultTableModel(new Object[][] {},
							new String[] { "ID", "Nome", "C\u00F3digo", "Pa\u00EDs", "Tipo" }));
					table.getColumnModel().getColumn(0).setPreferredWidth(50);
					table.getColumnModel().getColumn(1).setPreferredWidth(299);
					table.getColumnModel().getColumn(2).setPreferredWidth(84);
					table.getColumnModel().getColumn(3).setPreferredWidth(136);
					table.getColumnModel().getColumn(4).setPreferredWidth(122);

					resultado = comando.executeQuery(SQL);
					while (resultado.next()) {
						Figurinha.setID(resultado.getInt("ID"));
						Figurinha.setNome(resultado.getString("Nome"));
						Figurinha.setCod(resultado.getString("Codigo"));
						Figurinha.setPais(resultado.getString("Pais"));
						Figurinha.setTipo(resultado.getString("Tipo"));

						DefaultTableModel modelo = (DefaultTableModel) table.getModel();
						modelo.addRow(new Object[] { Figurinha.getID(), Figurinha.getNome(), Figurinha.getCod(),
								Figurinha.getPais(), Figurinha.getTipo() });

						table.setModel(modelo);
					}

				} catch (SQLException et) {

				}
			}
		});
		mostrar.setFont(new Font("Dialog", Font.PLAIN, 18));
		mostrar.setBounds(378, 388, 247, 43);
		contentPane.add(mostrar);

		Figurinha = new figurinha();

		try {
			conexao = DriverManager.getConnection(URLBD, usuario, senha);

			Statement comando = conexao.createStatement();
			ResultSet resultado;

			criarBancoDeDados();

			SQL = "use Album;";
			comando.execute(SQL);

			SQL = "Select * from Figurinha";
			resultado = comando.executeQuery(SQL);
			if (resultado.next()) {
				Figurinha.setID(resultado.getInt("ID") + 1);
			}

			resultado = comando.executeQuery(SQL);
			if (Figurinha.getID() > 1) {
				while (resultado.next()) {
					Figurinha.setID(resultado.getInt("ID"));
					Figurinha.setNome(resultado.getString("Nome"));
					Figurinha.setCod(resultado.getString("Codigo"));
					Figurinha.setPais(resultado.getString("Pais"));
					Figurinha.setTipo(resultado.getString("Tipo"));

					DefaultTableModel modelo = (DefaultTableModel) table.getModel();
					modelo.addRow(new Object[] { Figurinha.getID(), Figurinha.getNome(), Figurinha.getCod(),
							Figurinha.getPais(), Figurinha.getTipo() });

					table.setModel(modelo);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
