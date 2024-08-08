package view.escala;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.AtiradorDAO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class telaFolga extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		scrollPane.setBounds(12, 76, 557, 102);
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

//				if (codigo != 48 && codigo != 49 && codigo != 50 && codigo != 51 && codigo != 52 && codigo != 53
//						&& codigo != 54 && codigo != 55 && codigo != 56 && codigo != 57 && codigo != 8
//						&& codigo != 65535 && codigo != 10 && codigo != 32 && codigo != 9) {
//					int row = table.getSelectedRow();
//					int coluna = table.getSelectedColumn();
//					linha = (String) table.getModel().getValueAt(row, coluna);
//					
//				}
//				else {
//					
//					int row = table.getSelectedRow();
//					int coluna = table.getSelectedColumn();
//					linha = (String) table.getModel().getValueAt(row, coluna);
//				}
				
				lblCodigo.setText("" + codigo);
			}
		});
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome de Guerra", "Cargo", "Folga Vermelha", "Folga Preta"
			}
		) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
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
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        table.getColumnModel().getColumn(4).setCellRenderer(centralizado);

		scrollPane.setViewportView(table);
		
		ResultSet rs = AtiradorDAO.getAtiradoresEscala(1);
		try {
			while (rs.next()) {
				DefaultTableModel modelo = (DefaultTableModel) table.getModel();
				modelo.addRow(new Object[]{
		                rs.getInt("id"), rs.getString("guerra"), rs.getString("cargo"), "", ""
		                });
			}
		} catch (SQLException e) {
			System.out.println("Erro ao preecher tabela folga: " + e.getMessage());
		}
		
		
		this.setLocationRelativeTo(null);	
	}
}
