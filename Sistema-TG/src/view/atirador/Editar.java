package view.atirador;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AtiradorDAO;
import model.Atirador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Editar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField Campo1;
	private JTextField Campo2;
	private JTextField Campid1;
	private JTextField Campo3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Editar dialog = new Editar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Editar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Editar.class.getResource("/model/images/soldado (2).png")));
		setBounds(100, 100, 586, 544);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblEditar = new JLabel("Editar");
		lblEditar.setFont(new Font("Arial Black", Font.BOLD, 28));
		lblEditar.setBounds(232, 10, 158, 40);
		contentPanel.add(lblEditar);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblNewLabel_1.setBounds(26, 225, 86, 21);
		contentPanel.add(lblNewLabel_1);

		Campo1 = new JTextField();
		Campo1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		Campo1.setColumns(10);
		Campo1.setBounds(122, 221, 345, 25);
		contentPanel.add(Campo1);

		Campo2 = new JTextField();
		Campo2.setFont(new Font("Arial Black", Font.PLAIN, 12));
		Campo2.setColumns(10);
		Campo2.setBounds(122, 263, 345, 25);
		contentPanel.add(Campo2);

		Campo2.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int codigo = e.getKeyChar();

				if (codigo != 48 && codigo != 49 && codigo != 50 && codigo != 51 && codigo != 52 && codigo != 53
						&& codigo != 54 && codigo != 55 && codigo != 56 && codigo != 57 && codigo != 8
						&& codigo != 65535 && codigo != 10) {
					Campo2.setText("");
				}
			}
		});

		JLabel ID = new JLabel("ID");
		ID.setFont(new Font("Arial Black", Font.BOLD, 22));
		ID.setBounds(26, 267, 46, 21);
		contentPanel.add(ID);

		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblCargo.setBounds(26, 351, 86, 25);
		contentPanel.add(lblCargo);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Atirador");
		rdbtnNewRadioButton.setFont(new Font("Arial Black", Font.BOLD, 22));
		rdbtnNewRadioButton.setBounds(122, 353, 151, 21);
		contentPanel.add(rdbtnNewRadioButton);

		JRadioButton rdbtnMonitor = new JRadioButton("Monitor");
		rdbtnMonitor.setFont(new Font("Arial Black", Font.BOLD, 22));
		rdbtnMonitor.setBounds(275, 353, 123, 21);
		contentPanel.add(rdbtnMonitor);

		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnMonitor.isSelected()) {
					rdbtnMonitor.setSelected(false);
				}
			}
		});

		rdbtnMonitor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (rdbtnNewRadioButton.isSelected()) {
					rdbtnNewRadioButton.setSelected(false);
				}

			}
		});

		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnEditar.setBounds(65, 408, 178, 40);
		contentPanel.add(btnEditar);

		JButton btnVoltarAoMenu = new JButton("Menu Atirador");
		btnVoltarAoMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaAtirador Atirador = new telaAtirador();
				Atirador.setVisible(true);
				dispose();
			}
		});
		btnVoltarAoMenu.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnVoltarAoMenu.setBounds(318, 408, 178, 40);
		contentPanel.add(btnVoltarAoMenu);

		JLabel lblNewLabel_1_3_2 = new JLabel("ID");
		lblNewLabel_1_3_2.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblNewLabel_1_3_2.setBounds(26, 139, 41, 21);
		contentPanel.add(lblNewLabel_1_3_2);

		Campid1 = new JTextField();
		Campid1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int codigo = e.getKeyChar();
				if (codigo != 48 && codigo != 49 && codigo != 50 && codigo != 51 && codigo != 52 && codigo != 53
						&& codigo != 54 && codigo != 55 && codigo != 56 && codigo != 57 && codigo != 8
						&& codigo != 65535 && codigo != 10) {
					Campid1.setText("");
				}

			}
		});
		Campid1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		Campid1.setColumns(10);
		Campid1.setBounds(122, 141, 217, 25);
		contentPanel.add(Campid1);

		JButton Pesquisar = new JButton("Pesquisar");
		Pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Campid1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe um ID antes de Pesquisar!", "Atenção!!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					ResultSet rs = AtiradorDAO.getAtirador(Integer.parseInt(Campid1.getText()));

					try {
						if (rs.next() == false) {
							JOptionPane.showMessageDialog(null, "Esse ID não Existe!", "Erro!!",
									JOptionPane.ERROR_MESSAGE);
						} else {
							Campo1.setText(rs.getString("nome"));
							if (rs.getInt("id") < 10) {
								Campo2.setText("0" + String.valueOf(rs.getInt("id")));
							} else {
								Campo2.setText(String.valueOf(rs.getInt("id")));
							}

							Campo3.setText(rs.getString("guerra"));

							if (rs.getString("cargo").equalsIgnoreCase("Atirador")) {
								rdbtnNewRadioButton.setSelected(true);
								rdbtnMonitor.setSelected(false);
							} else if (rs.getString("cargo").equalsIgnoreCase("Monitor")) {
								rdbtnMonitor.setSelected(true);
								rdbtnNewRadioButton.setSelected(false);
							}

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		Pesquisar.setFont(new Font("Arial Black", Font.BOLD, 10));
		Pesquisar.setBounds(349, 142, 109, 25);
		contentPanel.add(Pesquisar);

		JLabel texto = new JLabel("Pesquise o Atirador ou Monitor pelo ID!");
		texto.setFont(new Font("Arial Black", Font.BOLD, 18));
		texto.setBounds(76, 54, 420, 48);
		contentPanel.add(texto);

		JLabel lblConfiraOsCampos = new JLabel("Confira os campos e Edite o Atirador ou Monitor");
		lblConfiraOsCampos.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblConfiraOsCampos.setBounds(86, 97, 395, 21);
		contentPanel.add(lblConfiraOsCampos);

		JLabel lblNewLabel_1_1 = new JLabel("Informações do Atirador ou Monitor ");
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(132, 176, 324, 13);
		contentPanel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2 = new JLabel("Pesquisado!");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_2.setBounds(217, 194, 114, 17);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_1_1_1 = new JLabel("Nome de Guerra");
		lblNewLabel_1_1_1.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblNewLabel_1_1_1.setBounds(26, 312, 219, 21);
		contentPanel.add(lblNewLabel_1_1_1);

		Campo3 = new JTextField();
		Campo3.setFont(new Font("Arial Black", Font.PLAIN, 12));
		Campo3.setColumns(10);
		Campo3.setBounds(244, 314, 223, 25);
		contentPanel.add(Campo3);
		this.setLocationRelativeTo(null);

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Campo1.getText().equals("") || Campo2.getText().equals("") || Campo3.getText().equals("")
						|| (rdbtnMonitor.isSelected() == false && rdbtnNewRadioButton.isSelected() == false)) {

					JOptionPane.showMessageDialog(null, "Sua Edição Está Incompleta", "Atenção!!",
							JOptionPane.WARNING_MESSAGE);

				} else {
					
					ResultSet rs = AtiradorDAO.getAtirador(Integer.parseInt(Campo2.getText()));
					Atirador atdr = new Atirador();
				
					try {
						boolean comp = rs.next();
						if ((comp == false && !Campid1.getText().equals(Campo2.getText())) || (comp == true && Campid1.getText().equals(Campo2.getText()))) {
							atdr.setNome(Campo1.getText());
							atdr.setID(Integer.parseInt(Campo2.getText()));
							atdr.setGuerra(Campo3.getText());

							if (rdbtnMonitor.isSelected() == true) {
								atdr.setCargo(rdbtnMonitor.getText());
							}

							else if (rdbtnNewRadioButton.isSelected() == true) {
								atdr.setCargo(rdbtnNewRadioButton.getText());
							}

							AtiradorDAO.EditarAtirador(atdr, Integer.parseInt(Campid1.getText())); // Edita Atirador
							telaAtirador.alteracao = true;
							
							JOptionPane.showMessageDialog(null, "Edição Feita!", "Completa!!",
									JOptionPane.INFORMATION_MESSAGE);
							Campid1.setText("");
							Campo1.setText("");
							Campo2.setText("");
							Campo3.setText("");
							if (rdbtnMonitor.isSelected() == true) {
								rdbtnMonitor.setSelected(false);
							} else if (rdbtnNewRadioButton.isSelected() == true) {
								rdbtnNewRadioButton.setSelected(false);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Esse ID já Existe", "Erro!!",
									JOptionPane.ERROR_MESSAGE);
							Campo2.setText("");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

	}
}
