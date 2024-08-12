package view.escala;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.AtiradorDAO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaFolga extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private int contador = 0;

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
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int codigo = e.getKeyChar();
				String linha = "";

				if (codigo != 48 && codigo != 49 && codigo != 50 && codigo != 51 && codigo != 52 && codigo != 53
						&& codigo != 54 && codigo != 55 && codigo != 56 && codigo != 57 && codigo != 8
						&& codigo != 65535 && codigo != 10 && codigo != 32 && codigo != 9) {
					int row = table.getSelectedRow();
					int coluna = table.getSelectedColumn();
					table.getModel().setValueAt("", row, coluna);
					
				}
				else {
					
					int row = table.getSelectedRow();
					int coluna = table.getSelectedColumn();
					linha = (String) table.getValueAt(row, coluna);
				}
				
				lblCodigo.setText("" + linha);
			}
		});
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome de Guerra", "Cargo", "Folga Vermelha", "Folga Preta", "Qtd Guarda"
			}
		));
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
       

		scrollPane.setViewportView(table);
		
		JButton btnProximo = new JButton(">");
		btnProximo.setFont(new Font("Dialog", Font.BOLD, 13));
		btnProximo.setBounds(519, 350, 50, 25);
		contentPane.add(btnProximo);
		
		
		//Anterior
		JButton btnAnterior = new JButton("<");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ultimaLinha = table.getModel().getRowCount() - 1;
				Integer id = (Integer) table.getModel().getValueAt(0, 0) - 15;
				ResultSet rs = AtiradorDAO.getAtiradoresFolga(id, 15);
				int idUltimoAtirador = AtiradorDAO.getIdUltimoAtirador();
				
				try {
					limparTabela();
					DefaultTableModel modelo = (DefaultTableModel) table.getModel();
					
					while (rs.next()) {
						modelo.addRow(new Object[]{
				                rs.getInt("id"), rs.getString("guerra"), rs.getString("cargo"), "", "", ""
				                });
					}
					
					table.setModel(modelo);
					contador--;
					
					ultimaLinha = table.getModel().getRowCount() - 1;
					id = (Integer) table.getModel().getValueAt(ultimaLinha, 0);
					
					if(id < idUltimoAtirador) {
						btnProximo.setVisible(true);
						if(contador == 0) {
							btnAnterior.setVisible(false);
						}
					}
					
				} catch (SQLException ex) {
					System.out.println("Erro ao preecher tabela folga: " + ex.getMessage());
				}
			}
		});
		btnAnterior.setFont(new Font("Dialog", Font.BOLD, 13));
		btnAnterior.setBounds(12, 350, 50, 25);
		btnAnterior.setVisible(false);
		contentPane.add(btnAnterior);
		
		JButton btnNewButton = new JButton("Gerar Escala");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBounds(222, 474, 138, 34);
		contentPane.add(btnNewButton);
		
		
		
		
		// Popular tabela inicial
		ResultSet rs = AtiradorDAO.getAtiradoresFolga(1, 15);
		try {
			while (rs.next()) {
				DefaultTableModel modelo = (DefaultTableModel) table.getModel();
				modelo.addRow(new Object[]{
		                rs.getInt("id"), rs.getString("guerra"), rs.getString("cargo"), "", "", ""
		                });
			}
		} catch (SQLException e) {
			System.out.println("Erro ao preecher tabela folga: " + e.getMessage());
		}
		
		
		
		
		//Próximo
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ultimaLinha = table.getModel().getRowCount() - 1;
				Integer id = (Integer) table.getModel().getValueAt(ultimaLinha, 0) + 1;
				ResultSet rs = AtiradorDAO.getAtiradoresFolga(id, 15);
				int idUltimoAtirador = AtiradorDAO.getIdUltimoAtirador();
				
				try {
					limparTabela();
					DefaultTableModel modelo = (DefaultTableModel) table.getModel();
					
					while (rs.next()) {
						modelo.addRow(new Object[]{
				                rs.getInt("id"), rs.getString("guerra"), rs.getString("cargo"), "", "", ""
				                });
					}
					
					
					table.setModel(modelo);
					contador++;
					
					ultimaLinha = table.getModel().getRowCount() - 1;
					id = (Integer) table.getModel().getValueAt(ultimaLinha, 0);
					
					if(contador > 0) {
						btnAnterior.setVisible(true);
						if(id >= idUltimoAtirador) { 
							btnProximo.setVisible(false);
						}
					}
					
				} catch (SQLException ex) {
					System.out.println("Erro ao preecher tabela folga: " + ex.getMessage());
				}
			}
		});
		
		this.setLocationRelativeTo(null);	
	}
	
	private void limparTabela() {
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Nome de Guerra", "Cargo", "Folga Vermelha", "Folga Preta", "Qtd Guarda"
				}
			));
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
	}
}
