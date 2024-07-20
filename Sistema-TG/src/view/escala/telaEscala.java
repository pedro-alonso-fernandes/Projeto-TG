package view.escala;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class telaEscala extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable dias_da_semana;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaEscala frame = new telaEscala();
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
	public telaEscala() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 816, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTabelaEscala = new JLabel("TABELA ESCALA");
		lblTabelaEscala.setFont(new Font("Dialog", Font.BOLD, 24));
		lblTabelaEscala.setBounds(289, 12, 215, 23);
		contentPane.add(lblTabelaEscala);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 106, 666, 142);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(86, 88, 666, 23);
		contentPane.add(scrollPane_1);
		
		dias_da_semana = new JTable();
		dias_da_semana.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Domingo", "Segunda", "Ter\u00E7a", "Quarta", "Quinta", "Sexta", "S\u00E1bado"
			}
		));
		dias_da_semana.getColumnModel().getColumn(0).setResizable(false);
		dias_da_semana.getColumnModel().getColumn(1).setResizable(false);
		dias_da_semana.getColumnModel().getColumn(2).setResizable(false);
		dias_da_semana.getColumnModel().getColumn(3).setResizable(false);
		dias_da_semana.getColumnModel().getColumn(4).setResizable(false);
		dias_da_semana.getColumnModel().getColumn(5).setResizable(false);
		dias_da_semana.getColumnModel().getColumn(6).setResizable(false);
		
		dias_da_semana.getTableHeader().setReorderingAllowed(false); // Impede que o usuário mova as colunas
		scrollPane_1.setViewportView(dias_da_semana);
		
		this.setLocationRelativeTo(null);
	}
}
