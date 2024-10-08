package view.folgaEferiados;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

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

import controller.FeriadoDAO;
import controller.FolgaDAO;
import view.telaPrincipal;

public class Feriados_e_Folgas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;
	private JButton btnEditar;
	private JButton btnRemover;
	private JButton btnMenu;
	private JButton pDF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Feriados_e_Folgas frame = new Feriados_e_Folgas();
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
	public Feriados_e_Folgas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 552, 426);
		contentPane.add(scrollPane);
		setContentPane(contentPane);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		table.setModel(
				new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Data", "Tipo", "Fe/Fo"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(400);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
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

		ResultSet rs = FeriadoDAO.getFeriados();
		ResultSet fr = FolgaDAO.getFolgas();
		String id = "0";
		try {
			while (rs.next()) {
					modelo.addRow(new Object[] {rs.getString("nome"),
							rs.getString("data"), rs.getString("tipo"), ("Feriado") });
			}
			while(fr.next()) {
				modelo.addRow(new Object[] {fr.getString("nome"),
						fr.getString("data"), ("X"), ("Folga") });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table.setModel(modelo);
		
		btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CadastroFeriados CF = new CadastroFeriados();
				CF.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnNewButton.setBounds(10, 465, 114, 21);
		contentPane.add(btnNewButton);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EditarFeriado ef = new EditarFeriado();
				ef.setVisible(true);
			}
		});
		btnEditar.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnEditar.setBounds(136, 466, 114, 21);
		contentPane.add(btnEditar);
		
		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RemoverFF rm = new RemoverFF();
				rm.setVisible(true);
			}
		});
		btnRemover.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnRemover.setBounds(260, 465, 114, 21);
		contentPane.add(btnRemover);
		
		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				telaPrincipal tp = new telaPrincipal();
				tp.setVisible(true);
			}
		});
		btnMenu.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnMenu.setBounds(384, 465, 114, 21);
		contentPane.add(btnMenu);
		
		pDF = new JButton("");
		pDF.setIcon(new ImageIcon(Feriados_e_Folgas.class.getResource("/model/images/business (1).png")));
		pDF.setToolTipText("Gerar Pdf");
		pDF.setBounds(508, 446, 40, 40);
		contentPane.add(pDF);

		
		
		
		
		
		setResizable(false);
		setLocationRelativeTo(null);
	}

}
