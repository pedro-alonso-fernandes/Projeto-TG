package view.atirador;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.AtiradorDAO;
import controller.EscalaDAO;
import controller.GerarPdf;
import view.telaPrincipal;

public class telaAtirador extends JFrame {

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
					telaAtirador frame = new telaAtirador();
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

	public telaAtirador() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(telaAtirador.class.getResource("/model/images/exercito.png")));
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 677, 544);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 643, 426);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.setModel(
				new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Nome de Guerra", "Cargo", "Qtd Guarda"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(520);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(105);
		table.getColumnModel().getColumn(4).setPreferredWidth(94);
		scrollPane.setViewportView(table);
		table.getTableHeader().setReorderingAllowed(false);
		table.setEnabled(false);
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
        table.getColumnModel().getColumn(4).setCellRenderer(centralizado);
        
        table.getColumnModel().getColumn(0).setResizable(false); // Impede que o usuário mude o tamanho da coluna
        table.getColumnModel().getColumn(1).setResizable(false); // Impede que o usuário mude o tamanho da coluna
        table.getColumnModel().getColumn(2).setResizable(false); // Impede que o usuário mude o tamanho da coluna
        table.getColumnModel().getColumn(3).setResizable(false); // Impede que o usuário mude o tamanho da coluna
        table.getColumnModel().getColumn(4).setResizable(false); // Impede que o usuário mude o tamanho da coluna

		DefaultTableModel modelo = (DefaultTableModel) table.getModel();

		ResultSet rs = AtiradorDAO.getAtiradoresGeral();
		String id = "0";
		try {
			while (rs.next()) {
				int qtdGuarda = rs.getInt("qtdGuarda") + EscalaDAO.getQtdGuardaAtirador(rs.getInt("id"), new Date());
				if (rs.getInt("id") < 10) {
					modelo.addRow(new Object[] { id + rs.getInt("id"), rs.getString("nome"),
							rs.getString("guerra"), rs.getString("cargo"), "" + qtdGuarda });
				} else {
					modelo.addRow(new Object[] { rs.getInt("id"), rs.getString("nome"),
							rs.getString("guerra"), rs.getString("cargo"), "" + qtdGuarda });
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table.setModel(modelo);
		JButton btnNewButton = new JButton("Cadastrar ");
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CadastroAtirador Cadastro = new CadastroAtirador();
				Cadastro.setVisible(true);
			}
		});
		btnNewButton.setBounds(20, 455, 114, 26);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Editar");
		btnNewButton_1.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editar edt = new Editar();
				edt.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(163, 455, 114, 26);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Remover");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Remover Rm = new Remover();
				Rm.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnNewButton_2.setBounds(313, 455, 114, 26);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Menu");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				telaPrincipal principal = new telaPrincipal();
				principal.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnNewButton_3.setBounds(465, 455, 114, 26);
		contentPane.add(btnNewButton_3);
		
		JButton PDF = new JButton("");
		PDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GerarPdf();
			}
		});
		PDF.setToolTipText("Gerar Pdf");
		PDF.setIcon(new ImageIcon(telaAtirador.class.getResource("/model/images/business (1).png")));
		PDF.setBounds(600, 446, 40, 40);
		contentPane.add(PDF);

		this.setLocationRelativeTo(null);
	}
}
