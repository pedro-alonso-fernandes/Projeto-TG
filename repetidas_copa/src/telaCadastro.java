import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;

public class telaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField nome;
	private JTextField COD;
	private JTextField pais;
	private String SQL = "";
	private String DriveMySQL = "com.mysql.cj.jdbc.Driver";
	private String URLBD = "jdbc:mysql://localhost:3306";
	private String usuario = "root";
	private String senha = "pedro123";
	Connection conexao = null;
	telaConsulta consulta;
	figurinha Figurinha;


	/**
	 * Launch the application.
	 */
	
	/*public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaCadastro frame = new telaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();Frame;
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public telaCadastro(telaConsulta tela) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel Texto_titulo = new JLabel("TELA DE CADASTRO");
		Texto_titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		Texto_titulo.setBounds(187, 11, 357, 59);
		contentPane.add(Texto_titulo);
		
		nome = new JTextField();
		nome.setFont(new Font("Dialog", Font.PLAIN, 18));
		nome.setBounds(197, 120, 365, 33);
		contentPane.add(nome);
		nome.setColumns(10);
		
		JLabel texto1 = new JLabel("Nome:");
		texto1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		texto1.setBounds(125, 124, 64, 24);
		contentPane.add(texto1);
		
		COD = new JTextField();
		COD.setFont(new Font("Dialog", Font.PLAIN, 18));
		COD.setColumns(10);
		COD.setBounds(251, 165, 242, 33);
		contentPane.add(COD);
		
		JLabel lblAutor = new JLabel("Código:");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAutor.setBounds(166, 169, 85, 24);
		contentPane.add(lblAutor);
		
		pais = new JTextField();
		pais.setFont(new Font("Dialog", Font.PLAIN, 18));
		pais.setColumns(10);
		pais.setBounds(251, 210, 242, 33);
		contentPane.add(pais);
		
		JLabel lblDataDeChegadaddmmaaaa = new JLabel("País:");
		lblDataDeChegadaddmmaaaa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDataDeChegadaddmmaaaa.setBounds(197, 214, 54, 24);
		contentPane.add(lblDataDeChegadaddmmaaaa);
		
		JButton salvar = new JButton("Salvar cadastro");
		salvar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		salvar.setBounds(93, 349, 200, 38);
		contentPane.add(salvar);
		
		JLabel lblTema = new JLabel("Tipo:");
		lblTema.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblTema.setBounds(218, 263, 54, 16);
		contentPane.add(lblTema);
		
		JButton encerrar = new JButton("Fechar Janela");
		encerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		encerrar.setFont(new Font("Dialog", Font.PLAIN, 18));
		encerrar.setBounds(464, 350, 188, 38);
		contentPane.add(encerrar);
		
		JComboBox tipo = new JComboBox();
		tipo.setModel(new DefaultComboBoxModel(new String[] {"  Jogador", "  Seleção", "  Escudo", "  FWC"}));
		tipo.setFont(new Font("Dialog", Font.PLAIN, 18));
		tipo.setBounds(290, 255, 188, 33);
		tipo.setSelectedItem(null);
		contentPane.add(tipo);
		
		JLabel lblNewLabel = new JLabel("Digite as informações da figurinha");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel.setBounds(197, 63, 352, 38);
		contentPane.add(lblNewLabel);
		
		/*JButton menu = new JButton("Tela Principal");
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaPrincipal tela = new telaPrincipal();
				tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				telaPrincipal frame4 = new telaPrincipal();
				frame4.setVisible(true);
			}
		});
		menu.setFont(new Font("Dialog", Font.PLAIN, 18));
		menu.setBounds(82, 385, 205, 38);
		contentPane.add(menu);*/
		
		
		try {
			Class.forName(DriveMySQL);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		consulta = tela;
		Figurinha = new figurinha();
		
		salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean sql = true;
				try {
					conexao = DriverManager.getConnection(URLBD, usuario, senha);
					
					Statement comando = conexao.createStatement();
					ResultSet resultado;
					
					SQL = "use Album;";
					comando.execute(SQL);
					
					/*SQL= "Select idLivro from Livro order by idLivro desc";
					resultado = comando.executeQuery(SQL);
					if(resultado.next()) {
						Livro.setID(resultado.getInt("idLivro") + 1);
						System.out.println(Livro.getID());
						
						if(Livro.getID() == 0) {
							Livro.setID(Livro.getID() + 1);
						}
					}*/
					
					if(nome.getText().equals("") || COD.getText().equals("") || pais.getText().equals("") || String.valueOf(tipo.getSelectedItem()).equals("null")) {
						sql = false;
						incompleto tela2 = new incompleto();
						tela2.setVisible(true);
					}
					if(sql == true) {
						SQL = ""
								+ "insert into Figurinha"
								+ "(Nome,Codigo,Pais,Tipo)"
								+ " values('" + nome.getText()
								+ "','" + COD.getText()
								+ "','" + pais.getText()
								+ "','" + String.valueOf(tipo.getSelectedItem())
								+ "');";
						
						comando.execute(SQL);
						
						confirmacao tela = new confirmacao();
						tela.setVisible(true);
					}

				}catch(SQLException ex) {
					ex.printStackTrace();
				}
				
				/*livro.setTitulo(titulo.getText());
				livro.setAutor(autor.getText());
				livro.setData(data.getText());
				livro.setCategoria(categoria.getText());
				
				cont = lista.size();
				livro.setId(cont);
				lista.add(livro);*/
				
				if(sql == true) {
					try {
						conexao = DriverManager.getConnection(URLBD, usuario, senha);
						Statement comando = conexao.createStatement();
						ResultSet resultado;
						
						SQL = "use Album";
						comando.execute(SQL);
						
						SQL = "Select ID from Figurinha order by ID desc";
						resultado = comando.executeQuery(SQL);
						if(resultado.next()) {
							Figurinha.setID(resultado.getInt("ID"));
						}
						
					}catch(SQLException ey){
						ey.printStackTrace();
					}
					
					
					DefaultTableModel modelo = (DefaultTableModel) consulta.table.getModel();
					modelo.addRow(new Object[] {Figurinha.getID(), nome.getText(), COD.getText(), pais.getText(), String.valueOf(tipo.getSelectedItem())});
						
					consulta.table.setModel(modelo);
					
					nome.setText("");
					COD.setText("");
					pais.setText("");
					tipo.setSelectedItem(null);
					
				}
			}
		});
		
	}
}
