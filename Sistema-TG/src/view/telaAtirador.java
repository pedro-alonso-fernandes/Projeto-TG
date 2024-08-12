package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import controller.AtiradorDAO;
import controller.BD;
import controller.Conexao;
import controller.GerarPdf;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

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
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 586, 544);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 552, 426);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.setModel(
				new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Nome de Guerra", "Cargo"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(48);
		table.getColumnModel().getColumn(1).setPreferredWidth(411);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(105);
		scrollPane.setViewportView(table);
		table.getTableHeader().setReorderingAllowed(false);
		table.setEnabled(false);
		
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
        table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
        table.getColumnModel().getColumn(3).setCellRenderer(centralizado);

		DefaultTableModel modelo = (DefaultTableModel) table.getModel();

		ResultSet rs = AtiradorDAO.getAtiradores();
		String id = "0";
		try {
			while (rs.next()) {
				if (rs.getInt("id") < 10) {
					modelo.addRow(new Object[] { id = "0" + rs.getInt("id"), rs.getString("nome"),
							rs.getString("guerra"), rs.getString("cargo") });
				} else {
					modelo.addRow(new Object[] { rs.getInt("id"), rs.getString("nome"),
							rs.getString("guerra"), rs.getString("cargo") });
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
		btnNewButton.setBounds(10, 465, 114, 21);
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
		btnNewButton_1.setBounds(136, 465, 114, 21);
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
		btnNewButton_2.setBounds(260, 465, 114, 21);
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
		btnNewButton_3.setBounds(384, 465, 114, 21);
		contentPane.add(btnNewButton_3);
		
		JButton PDF = new JButton("");
		PDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerarPdf pdf = new GerarPdf();
				
			}
		});
		PDF.setToolTipText("GerarpdfAtdr");
		PDF.setIcon(new ImageIcon(telaAtirador.class.getResource("/model/images/business (1).png")));
		PDF.setBounds(508, 446, 40, 40);
		contentPane.add(PDF);

		this.setLocationRelativeTo(null);
	}
}
