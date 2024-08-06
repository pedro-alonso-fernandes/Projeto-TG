package view;

import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AtiradorDAO;
import controller.BD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SpinnerNumberModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Remover extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField Campid;
	private JTable table;
	private int removerId = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Remover dialog = new Remover();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Remover() {
		setBounds(100, 100, 586, 544);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setResizable(false);

		JLabel lblNewLabel = new JLabel("Remover");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel.setBounds(198, 0, 159, 48);
		contentPanel.add(lblNewLabel);

		JButton Remover = new JButton("Remover");
		Remover.setFont(new Font("Arial Black", Font.BOLD, 15));
		Remover.setBounds(65, 421, 178, 40);
		contentPanel.add(Remover);

		JButton Menu = new JButton("Menu Atirador");
		Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				telaAtirador Atirador = new telaAtirador();
				Atirador.setVisible(true);
			}
		});
		Menu.setFont(new Font("Arial Black", Font.BOLD, 15));
		Menu.setBounds(317, 421, 178, 40);
		contentPanel.add(Menu);

		JLabel texto = new JLabel("Pesquise o Atirador ou Monitor pelo ID!");
		texto.setFont(new Font("Arial Black", Font.BOLD, 19));
		texto.setBounds(65, 47, 451, 48);
		contentPanel.add(texto);

		JLabel texto2 = new JLabel("Confira os campos e Remova o Atirador ou Monitor");
		texto2.setFont(new Font("Arial Black", Font.BOLD, 14));
		texto2.setBounds(75, 94, 420, 21);
		contentPanel.add(texto2);

		JLabel lblNewLabel_1_3_2 = new JLabel("ID");
		lblNewLabel_1_3_2.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblNewLabel_1_3_2.setBounds(37, 165, 41, 21);
		contentPanel.add(lblNewLabel_1_3_2);

		Campid = new JTextField();
		Campid.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int codigo = e.getKeyChar();
				if (codigo != 48 && codigo != 49 && codigo != 50 && codigo != 51 && codigo != 52 && codigo != 53
						&& codigo != 54 && codigo != 55 && codigo != 56 && codigo != 57 && codigo != 8
						&& codigo != 65535 && codigo != 10) {
					Campid.setText("");
				}

			}
		});
		Campid.setFont(new Font("Arial Black", Font.PLAIN, 12));
		Campid.setColumns(10);
		Campid.setBounds(102, 167, 217, 25);
		contentPanel.add(Campid);

		JLabel lblNewLabel_1 = new JLabel("Informações do Atirador ou Monitor ");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_1.setBounds(112, 202, 324, 13);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Pesquisado!");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_2.setBounds(221, 225, 114, 17);
		contentPanel.add(lblNewLabel_2);

		JButton Pesquisar = new JButton("Pesquisar");
		Pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Campid.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe um ID antes de Pesquisar!", "Incompleto",
							JOptionPane.WARNING_MESSAGE);
				} else {
					DefaultTableModel modelo = (DefaultTableModel) table.getModel();

					ResultSet rs = AtiradorDAO.getAtirador(Integer.parseInt(Campid.getText()));

					try {
						if (rs.next() == false) {
							Campid.setText("");
							JOptionPane.showMessageDialog(null, "O Atirador não Existe!", "Incompleto",
									JOptionPane.WARNING_MESSAGE);
						} else {
							removerId =  Integer.parseInt(Campid.getText());;
							table.setModel(new DefaultTableModel(new Object[][] {},
									new String[] { "ID", "Nome", "Guerra", "Cargo" }));

							table.getColumnModel().getColumn(1).setPreferredWidth(330);
							table.getColumnModel().getColumn(2).setPreferredWidth(104);
							table.getColumnModel().getColumn(3).setPreferredWidth(126);
							modelo = (DefaultTableModel) table.getModel();
							modelo.addRow(new Object[] { rs.getInt("id"), rs.getString("nome"), rs.getString("guerra"),
									rs.getString("cargo") });
							table.setModel(modelo);
						}
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}

				}
			}
		});

		Pesquisar.setFont(new Font("Arial Black", Font.BOLD, 10));
		Pesquisar.setBounds(329, 166, 109, 25);
		contentPanel.add(Pesquisar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 260, 552, 151);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "Guerra", "Cargo" }));

		table.getColumnModel().getColumn(1).setPreferredWidth(330);
		table.getColumnModel().getColumn(2).setPreferredWidth(104);
		table.getColumnModel().getColumn(3).setPreferredWidth(126);
		scrollPane.setViewportView(table);

		Remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			
					if(removerId == 0) {
						JOptionPane.showMessageDialog(null, "O Nenhum Atirador para ser Removido!", "Incompleto",
								JOptionPane.ERROR_MESSAGE);

					}
						else {
							AtiradorDAO.RemoverAtirador(removerId);
							modelo = (DefaultTableModel) table.getModel();
							modelo.removeRow(0);
							table.setModel(modelo);
							Campid.setText("");
						}
					
			}
		});
		this.setLocationRelativeTo(null);
	}
}
