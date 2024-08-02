package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import controller.AtiradorDAO;
import controller.Conexao;
import model.Atirador;

public class CadastroAtirador extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroAtirador dialog = new CadastroAtirador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroAtirador() {
		setBounds(100, 100, 586, 544);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setResizable(false);
		{
			JLabel lblNewLabel = new JLabel("Cadastrar");
			lblNewLabel.setBounds(207, 10, 158, 40);
			lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 28));
			contentPanel.add(lblNewLabel);
		}

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblNewLabel_1.setBounds(34, 139, 86, 21);
		contentPanel.add(lblNewLabel_1);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Atirador");
		rdbtnNewRadioButton.setFont(new Font("Arial Black", Font.BOLD, 22));
		rdbtnNewRadioButton.setBounds(130, 299, 151, 21);
		contentPanel.add(rdbtnNewRadioButton);

		JRadioButton rdbtnMonitor = new JRadioButton("Monitor");
		rdbtnMonitor.setFont(new Font("Arial Black", Font.BOLD, 22));
		rdbtnMonitor.setBounds(283, 299, 123, 21);
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

		textField = new JTextField();
		textField.setFont(new Font("Arial Black", Font.PLAIN, 12));
		textField.setBounds(130, 139, 278, 25);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1_3 = new JLabel("ID");
		lblNewLabel_1_3.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblNewLabel_1_3.setBounds(34, 189, 86, 21);
		contentPanel.add(lblNewLabel_1_3);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial Black", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(130, 192, 278, 25);
		contentPanel.add(textField_2);

		textField_2.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int codigo = e.getKeyChar();

				if (codigo != 48 && codigo != 49 && codigo != 50 && codigo != 51 && codigo != 52 && codigo != 53
						&& codigo != 54 && codigo != 55 && codigo != 56 && codigo != 57 && codigo != 8
						&& codigo != 65535 && codigo != 10) {
					textField_2.setText("");
				}
			}
		});

		JLabel lblNewLabel_1_3_1 = new JLabel("Cargo");
		lblNewLabel_1_3_1.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblNewLabel_1_3_1.setBounds(34, 297, 86, 25);
		contentPanel.add(lblNewLabel_1_3_1);

		JButton btnNewButton = new JButton("Cadastrar ");
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnNewButton.setBounds(75, 359, 178, 40);
		contentPanel.add(btnNewButton);

		JButton btnVoltarAoMenu = new JButton("Menu Atirador");
		btnVoltarAoMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				telaAtirador Atirador = new telaAtirador();
				Atirador.setVisible(true);
			}
		});

		btnVoltarAoMenu.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnVoltarAoMenu.setBounds(283, 359, 178, 40);
		contentPanel.add(btnVoltarAoMenu);

		JLabel lblNewLabel_1_1 = new JLabel("Folga");
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblNewLabel_1_1.setBounds(34, 249, 86, 32);
		contentPanel.add(lblNewLabel_1_1);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(-1, -1, 15, 1));
		spinner.setFont(new Font("Arial Black", Font.PLAIN, 20));
		spinner.setBounds(130, 243, 62, 44);
		contentPanel.add(spinner);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || textField_2.getText().equals("")
						|| (rdbtnMonitor.isSelected() == false && rdbtnNewRadioButton.isSelected() == false)
						|| spinner.getValue().equals(-1)) {

					JOptionPane.showMessageDialog(null, "Seu Cadastro Está Incompleto", "Incompleto",
							JOptionPane.WARNING_MESSAGE);

				} else {

					
					Atirador atdr = new Atirador();
					atdr.setNome(textField.getText());
					atdr.setID(Integer.parseInt(textField_2.getText()));
					atdr.setFolga((int) spinner.getValue());
						
					if(rdbtnMonitor.isSelected() == true) {
						atdr.setCargo(rdbtnMonitor.getText());
					}
					
					else if (rdbtnNewRadioButton.isSelected() == true) {
						atdr.setCargo(rdbtnNewRadioButton.getText());
					}
					
					AtiradorDAO.cadastrarAtirador(atdr);
					
					JOptionPane.showMessageDialog(null, "Cadastro Feito");

				}

			}
		});

		this.setLocationRelativeTo(null);
	}
}
