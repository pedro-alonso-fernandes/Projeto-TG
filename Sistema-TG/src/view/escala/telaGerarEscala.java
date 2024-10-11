package view.escala;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.AtiradorDAO;
import model.Array;
import model.BD;
import model.Escala;
import view.telaPrincipal;
import view.folgaEferiados.CadastroFeriados;

public class telaGerarEscala extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private int qtdAtiradoresGeral = AtiradorDAO.getQtdAtiradoresGeral();
	private int[] guardaVermelha = new int[qtdAtiradoresGeral];
	private int[] guardaPreta = new int[qtdAtiradoresGeral];
	private int[] qtdGuarda = new int[qtdAtiradoresGeral];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaGerarEscala frame = new telaGerarEscala();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public telaGerarEscala() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(telaGerarEscala.class.getResource("/model/images/calendario.png")));
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 586, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTelaTitulo = new JLabel("DADOS DA ESCALA DOS ATIRADORES E MONITORES");
		lblTelaTitulo.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblTelaTitulo.setBounds(30, 10, 522, 24);
		contentPane.add(lblTelaTitulo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 76, 557, 340);
		contentPane.add(scrollPane);

		JLabel lblCodigo = new JLabel("");
		lblCodigo.setBounds(42, 224, 70, 15);
		contentPane.add(lblCodigo);

		table = new JTable();
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome de Guerra", "Cargo", "Guarda Preta", "Guarda Vermelha", "Qtd Guarda" }) {

			private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, false, true, true, true };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(27);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(131);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(73);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(83);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(107);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(82);
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(5).setCellRenderer(centralizado);

		scrollPane.setViewportView(table);

		JButton btnEscala = new JButton("Gerar Escala");
		btnEscala.setFont(new Font("Dialog", Font.BOLD, 14));
		btnEscala.setBounds(68, 452, 156, 34);
		contentPane.add(btnEscala);
		
		JButton btnmenu = new JButton("Voltar ao Menu");
		btnmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				telaPrincipal principal = new telaPrincipal();
				principal.setVisible(true);
			}
		});
		btnmenu.setFont(new Font("Dialog", Font.BOLD, 14));
		btnmenu.setBounds(360, 452, 156, 34);
		contentPane.add(btnmenu);
		
		JLabel lblNewLabel = new JLabel("CLIQUE NOS CAMPOS E PREENCHA AS INFORMAÇÕES");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblNewLabel.setBounds(23, 41, 529, 13);
		contentPane.add(lblNewLabel);

		// Popular tabela inicial
		ResultSet rs = AtiradorDAO.getAtiradoresByMonitores();
		try {
			int i = 0;
			while (rs.next()) {
				DefaultTableModel modelo = (DefaultTableModel) table.getModel();
				modelo.addRow(new Object[] { rs.getInt("id"), rs.getString("guerra"), rs.getString("cargo"),
						"" + guardaPreta[i], "" + guardaVermelha[i], "" + qtdGuarda[i] });
				
				i++;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao preecher tabela guarda: " + e.getMessage());
		}
		
		
		

		btnEscala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int ultimaLinha = table.getModel().getRowCount() - 1;
//					int idUltimo = (Integer) table.getModel().getValueAt(ultimaLinha, 0);

					ultimaLinha++;
					int j = 0;
					for (int i = 0; i < ultimaLinha; i++) {
						String celula = (String) table.getModel().getValueAt(j, 3);
						guardaPreta[i] = Integer.parseInt(celula);

						celula = (String) table.getModel().getValueAt(j, 4);
						guardaVermelha[i] = Integer.parseInt(celula);

						celula = (String) table.getModel().getValueAt(j, 5);
						qtdGuarda[i] = Integer.parseInt(celula);

						j++;
					}

					int maiorPreta = Array.getMaiorValorArray(guardaPreta, 0);
					int maiorVermelha = Array.getMaiorValorArray(guardaVermelha, 0);

					if (maiorPreta > 99 || maiorVermelha > 99) {
						JOptionPane.showMessageDialog(null,
								"Um dos valores informados é muito grande para ser uma guarda!", "Erro!",
								JOptionPane.WARNING_MESSAGE);
					} else {
						
						BD.reiniciarTabelaEscala();
						BD.reiniciarTabelaGuarda("Preta");
						BD.reiniciarTabelaGuarda("Vermelha");
						
						Escala.gerarPrimeiraEscala(guardaPreta, guardaVermelha, qtdGuarda, new Date());
						
						dispose();
						
						telaEscala tela = new telaEscala();
						tela.setVisible(true);
					}

				} catch (NumberFormatException ey) {
					JOptionPane.showMessageDialog(null, "Um dos valores informados não é um número, ou possui espaços",
							"Atenção!", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		this.setLocationRelativeTo(null);
	}
}
