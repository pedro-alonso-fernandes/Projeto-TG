package view.escala;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import model.Escala;

public class telaFolga extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private int contador = 0;
	private int idUltimoAtirador = AtiradorDAO.getIdUltimoAtirador();
	private int[] folgaVermelha = new int[idUltimoAtirador];
	private int[] folgaPreta = new int[idUltimoAtirador];
	private int[] qtdGuarda = new int[idUltimoAtirador];
	private List<Integer> indicesMonitores = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaFolga frame = new telaFolga();
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
	public telaFolga() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 586, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTelaFolga = new JLabel("TELA FOLGA");
		lblTelaFolga.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTelaFolga.setBounds(214, 12, 138, 24);
		contentPane.add(lblTelaFolga);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 76, 557, 262);
		contentPane.add(scrollPane);

		JLabel lblCodigo = new JLabel("");
		lblCodigo.setBounds(42, 224, 70, 15);
		contentPane.add(lblCodigo);

		table = new JTable();
//				table.addKeyListener(new KeyAdapter() {
//					@Override
//					public void keyReleased(KeyEvent e) {
//						int codigo = e.getKeyChar();
//						String linha = "";
		//
//						if (codigo != 48 && codigo != 49 && codigo != 50 && codigo != 51 && codigo != 52 && codigo != 53
//								&& codigo != 54 && codigo != 55 && codigo != 56 && codigo != 57 && codigo != 8
//								&& codigo != 65535 && codigo != 10 && codigo != 32 && codigo != 9) {
//							int row = table.getSelectedRow();
//							int coluna = table.getSelectedColumn();
//							table.getModel().setValueAt("", row, coluna);
		//
//						} else {
		//
//							int row = table.getSelectedRow();
//							int coluna = table.getSelectedColumn();
//							linha = (String) table.getValueAt(row, coluna);
//						}
		//
//						lblCodigo.setText("" + linha);
//					}
//				});
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome de Guerra", "Cargo", "Folga Vermelha", "Folga Preta", "Qtd Guarda" }) {

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
		table.getColumnModel().getColumn(3).setPreferredWidth(107);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(83);
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

		JButton btnProximo = new JButton(">");
		btnProximo.setFont(new Font("Dialog", Font.BOLD, 13));
		btnProximo.setBounds(519, 350, 50, 25);
		contentPane.add(btnProximo);

		JButton btnAnterior = new JButton("<");
		btnAnterior.setFont(new Font("Dialog", Font.BOLD, 13));
		btnAnterior.setBounds(12, 350, 50, 25);
		btnAnterior.setVisible(false);
		contentPane.add(btnAnterior);

		JButton btnEscala = new JButton("Gerar Escala");
		btnEscala.setFont(new Font("Dialog", Font.BOLD, 14));
		btnEscala.setBounds(222, 474, 138, 34);
		contentPane.add(btnEscala);

		// Popular tabela inicial
		ResultSet rs = AtiradorDAO.getAtiradoresFolga(1, 15);
		try {
			int i = 0;
			while (rs.next()) {
				DefaultTableModel modelo = (DefaultTableModel) table.getModel();
				modelo.addRow(new Object[] { rs.getInt("id"), rs.getString("guerra"), rs.getString("cargo"),
						"" + folgaVermelha[i], "" + folgaPreta[i], "" + qtdGuarda[i] });
				
				if(rs.getString("cargo").equals("Monitor")) {
					indicesMonitores.add(i);
				}
				
				i++;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao preecher tabela folga: " + e.getMessage());
		}
		
		
		

		// Próximo
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ultimaLinha = table.getModel().getRowCount() - 1;
				Integer idProximo = (Integer) table.getModel().getValueAt(ultimaLinha, 0) + 1;
				ResultSet rs = AtiradorDAO.getAtiradoresFolga(idProximo, 15);
				int idUltimo = idProximo - 1;

				try {

					ultimaLinha++;
					int j = 0;
					for (int i = idUltimo - ultimaLinha; i < idUltimo; i++) {
						String celula = (String) table.getModel().getValueAt(j, 3);
						folgaVermelha[i] = Integer.parseInt(celula);

						celula = (String) table.getModel().getValueAt(j, 4);
						folgaPreta[i] = Integer.parseInt(celula);

						celula = (String) table.getModel().getValueAt(j, 5);
						qtdGuarda[i] = Integer.parseInt(celula);

						j++;
					}
					ultimaLinha--;

					limparTabela();
					DefaultTableModel modelo = (DefaultTableModel) table.getModel();

					int i = idProximo - 1;
					while (rs.next()) {
						modelo.addRow(new Object[] { rs.getInt("id"), rs.getString("guerra"), rs.getString("cargo"),
								"" + folgaVermelha[i], "" + folgaPreta[i], "" + qtdGuarda[i] });
						
						if(rs.getString("cargo").equals("Monitor")) {
							indicesMonitores.add(i);
						}
						
						i++;
					}

					table.setModel(modelo);
					contador++;

					ultimaLinha = table.getModel().getRowCount() - 1;
					idUltimo = (Integer) table.getModel().getValueAt(ultimaLinha, 0);

					if (contador > 0) {
						btnAnterior.setVisible(true);
						if (idUltimo >= idUltimoAtirador) {
							btnProximo.setVisible(false);
						}
					}

				} catch (SQLException ex) {
					System.out.println("Erro ao preecher tabela folga: " + ex.getMessage());
				} catch (NumberFormatException ey) {
					JOptionPane.showMessageDialog(null, "Um dos valores informados não é um número", "Erro!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		
		
		
		
		// Anterior
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ultimaLinha = table.getModel().getRowCount() - 1;
				Integer idAnterior = (Integer) table.getModel().getValueAt(0, 0) - 15;
				ResultSet rs = AtiradorDAO.getAtiradoresFolga(idAnterior, 15);
				int idUltimo = (Integer) table.getModel().getValueAt(ultimaLinha, 0);

				try {

					ultimaLinha++;
					int j = 0;
					for (int i = idUltimo - ultimaLinha; i < idUltimo; i++) {
						String celula = (String) table.getModel().getValueAt(j, 3);
						folgaVermelha[i] = Integer.parseInt(celula);

						celula = (String) table.getModel().getValueAt(j, 4);
						folgaPreta[i] = Integer.parseInt(celula);

						celula = (String) table.getModel().getValueAt(j, 5);
						qtdGuarda[i] = Integer.parseInt(celula);

						j++;
					}
					ultimaLinha--;

					limparTabela();
					DefaultTableModel modelo = (DefaultTableModel) table.getModel();

					int i = idAnterior - 1;
					while (rs.next()) {
						modelo.addRow(new Object[] { rs.getInt("id"), rs.getString("guerra"), rs.getString("cargo"),
								"" + folgaVermelha[i], "" + folgaPreta[i], "" + qtdGuarda[i] });
						
						if(rs.getString("cargo").equals("Monitor")) {
							indicesMonitores.add(i);
						}
						
						i++;
					}

					table.setModel(modelo);
					contador--;

					ultimaLinha = table.getModel().getRowCount() - 1;
					idUltimo = (Integer) table.getModel().getValueAt(ultimaLinha, 0);

					if (idUltimo < idUltimoAtirador) {
						btnProximo.setVisible(true);
						if (contador == 0) {
							btnAnterior.setVisible(false);
						}
					}

				} catch (SQLException ex) {
					System.out.println("Erro ao preecher tabela folga: " + ex.getMessage());
				} catch (NumberFormatException ey) {
					JOptionPane.showMessageDialog(null, "Um dos valores informados não é um número", "Erro!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnEscala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int ultimaLinha = table.getModel().getRowCount() - 1;
					int idUltimo = (Integer) table.getModel().getValueAt(ultimaLinha, 0);

					ultimaLinha++;
					int j = 0;
					for (int i = idUltimo - ultimaLinha; i < idUltimo; i++) {
						String celula = (String) table.getModel().getValueAt(j, 3);
						folgaVermelha[i] = Integer.parseInt(celula);

						celula = (String) table.getModel().getValueAt(j, 4);
						folgaPreta[i] = Integer.parseInt(celula);

						celula = (String) table.getModel().getValueAt(j, 5);
						qtdGuarda[i] = Integer.parseInt(celula);

						j++;
					}

					int maiorVermelha = Escala.getMaiorValorArray(folgaVermelha, 0);
					int maiorPreta = Escala.getMaiorValorArray(folgaPreta, 0);

					if (maiorVermelha > 30 || maiorPreta > 30) {
						JOptionPane.showMessageDialog(null,
								"Um dos valores informados é muito grande para ser uma folga!", "Erro!",
								JOptionPane.WARNING_MESSAGE);
					} else {
						
						for(int i = 0; i < indicesMonitores.size(); i++) {
							System.out.println(indicesMonitores.get(i));
						}
						

						//int[] indiceFolgaVermelha = Escala.getIndicesFolga(folgaVermelha, indicesMonitores);

						//System.out.println(Arrays.toString(indiceFolgaVermelha));
					}

				} catch (NumberFormatException ey) {
					JOptionPane.showMessageDialog(null, "Um dos valores informados não é um número, ou possui espaços",
							"Atenção!", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		this.setLocationRelativeTo(null);
	}

	private void limparTabela() {
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome de Guerra", "Cargo", "Folga Vermelha", "Folga Preta", "Qtd Guarda" }) {

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
		table.getColumnModel().getColumn(3).setPreferredWidth(107);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(83);
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
	}

}
