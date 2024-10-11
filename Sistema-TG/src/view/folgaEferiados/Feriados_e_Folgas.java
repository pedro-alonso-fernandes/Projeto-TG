package view.folgaEferiados;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.AtiradorDAO;
import controller.FeriadoDAO;
import controller.FolgaDAO;
import view.atirador.Remover;
import view.atirador.telaPrincipal;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class Feriados_e_Folgas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;
	private JButton btnEditar;
	private JButton btnRemover;
	private JButton btnMenu;
	private JTable table_1;
	private JLabel lblNewLabel;
	private JLabel lblTabelaDeFolgas;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Feriados_e_Folgas.class.getResource("/model/images/calendario.png")));
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 586, 544);

	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        contentPane.setLayout(null);
	        setContentPane(contentPane); // Moved here to set the content pane before adding components

	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setBounds(10, 47, 552, 176);
	        contentPane.add(scrollPane);

	        table = new JTable();
	        table.setBackground(new Color(255, 255, 255));
	        table.setFont(new Font("Arial Black", Font.BOLD, 12));
	        table.setModel(new DefaultTableModel(
	            new Object[][] {},
	            new String[] { "Nome", "Data", "Tipo", "Feriados" }
	        ));
	        scrollPane.setViewportView(table);

	        table.getColumnModel().getColumn(0).setPreferredWidth(400);
	        table.getColumnModel().getColumn(1).setPreferredWidth(140);
	        table.getColumnModel().getColumn(2).setPreferredWidth(100);
	        table.getColumnModel().getColumn(3).setPreferredWidth(100);
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
	        SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy");
	        try {
	            while (rs.next()) {
	                modelo.addRow(new Object[] {
	                    rs.getString("nome"),
	                    formato1.format(rs.getDate("data")),
	                    rs.getString("tipo"),
	                    "Feriado"
	                });
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Exception handling
	        }

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
	        btnEditar.setBounds(148, 465, 114, 21);
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
	        btnRemover.setBounds(289, 465, 114, 21);
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
	        btnMenu.setBounds(437, 465, 114, 21);
	        contentPane.add(btnMenu);

	        JScrollPane scrollPane_1 = new JScrollPane();
	        scrollPane_1.setBounds(10, 275, 552, 169);
	        contentPane.add(scrollPane_1);

	        table_1 = new JTable();
	        table_1.setBackground(new Color(255, 255, 255));
	        table_1.setFont(new Font("Arial Black", Font.BOLD, 12));
	        table_1.setModel(new DefaultTableModel(
	            new Object[][] {},
	            new String[] { "Nome", "Data", "Folga" }
	        ));
	        scrollPane_1.setViewportView(table_1);

	        table_1.getColumnModel().getColumn(0).setPreferredWidth(400);
	        table_1.getColumnModel().getColumn(1).setPreferredWidth(140);
	        table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
	        table_1.getTableHeader().setReorderingAllowed(false);
	        table_1.setEnabled(false);

	        DefaultTableCellRenderer centralizado1 = new DefaultTableCellRenderer();
	        centralizado1.setHorizontalAlignment(SwingConstants.CENTER);

	        table_1.getColumnModel().getColumn(0).setCellRenderer(centralizado1);
	        table_1.getColumnModel().getColumn(1).setCellRenderer(centralizado1);
	        table_1.getColumnModel().getColumn(2).setCellRenderer(centralizado1);

	        DefaultTableModel modelo1 = (DefaultTableModel) table_1.getModel();
	        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	        ResultSet fr = FolgaDAO.getFolgas();
	        try {
	            while (fr.next()) {
	                modelo1.addRow(new Object[] {
	                    fr.getString("nome"),
	                    formato.format(fr.getDate("data")),
	                    "Folga"
	                });
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Exception handling
	        }

	        scrollPane_1.setViewportView(table_1);
	        
	        lblNewLabel = new JLabel("Tabela de Feriados");
	        lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
	        lblNewLabel.setBounds(175, 10, 228, 27);
	        contentPane.add(lblNewLabel);
	        
	        lblTabelaDeFolgas = new JLabel("Tabela de Folgas");
	        lblTabelaDeFolgas.setFont(new Font("Arial Black", Font.BOLD, 20));
	        lblTabelaDeFolgas.setBounds(175, 238, 228, 27);
	        contentPane.add(lblTabelaDeFolgas);

	        setResizable(false);
	        setLocationRelativeTo(null);
	    }
}
