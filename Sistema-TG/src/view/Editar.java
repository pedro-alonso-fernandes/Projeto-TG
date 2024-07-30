package view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.awt.event.ActionEvent;

public class Editar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField Campo1;
	private JTextField textField;

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
		lblNewLabel_1.setBounds(26, 148, 86, 21);
		contentPanel.add(lblNewLabel_1);
		
		Campo1 = new JTextField();
		Campo1.setFont(new Font("Arial Black", Font.PLAIN, 12));
		Campo1.setColumns(10);
		Campo1.setBounds(122, 150, 345, 25);
		contentPanel.add(Campo1);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial Black", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(122, 202, 345, 25);
		contentPanel.add(textField);
		
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int codigo = e.getKeyChar();
				
				if(codigo != 48 && codigo != 49 && codigo != 50 && codigo != 51 && codigo != 52 && codigo != 53 && codigo != 54 && codigo != 55 && codigo != 56 && codigo != 57 && codigo != 8 && codigo != 65535 && codigo != 10) {
					textField.setText("");
				}
			}
		});

		
		
		
		JLabel ID = new JLabel("ID");
		ID.setFont(new Font("Arial Black", Font.BOLD, 22));
		ID.setBounds(26, 202, 86, 21);
		contentPanel.add(ID);
		
		JLabel lblFolga = new JLabel("Folga");
		lblFolga.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblFolga.setBounds(26, 260, 86, 32);
		contentPanel.add(lblFolga);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(-1), null, null, Integer.valueOf(1)));
		spinner.setFont(new Font("Arial Black", Font.PLAIN, 20));
		spinner.setBounds(122, 248, 62, 44);
		contentPanel.add(spinner);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblCargo.setBounds(26, 322, 86, 25);
		contentPanel.add(lblCargo);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Atirador");
		rdbtnNewRadioButton.setFont(new Font("Arial Black", Font.BOLD, 22));
		rdbtnNewRadioButton.setBounds(122, 329, 151, 21);
		contentPanel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnMonitor = new JRadioButton("Monitor");
		rdbtnMonitor.setFont(new Font("Arial Black", Font.BOLD, 22));
		rdbtnMonitor.setBounds(275, 329, 123, 21);
		contentPanel.add(rdbtnMonitor);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnEditar.setBounds(65, 380, 178, 40);
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
		btnVoltarAoMenu.setBounds(310, 380, 178, 40);
		contentPanel.add(btnVoltarAoMenu);
		this.setLocationRelativeTo(null);
		
		btnEditar.addActionListener(new ActionListener() { 
		 public void actionPerformed(ActionEvent e) { 
			  try {
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			  
			  if(Campo1.getText().equals("") || textField.getText().equals("") ||  (rdbtnMonitor.isSelected() == false && rdbtnNewRadioButton.isSelected() == false) || spinner.getValue().equals(-1) ) {
				  
				  JOptionPane.showMessageDialog(null, "Seu Cadastro Está Incompleto", "Incompleto", JOptionPane.WARNING_MESSAGE);
				  
	 }
			  else {
				  
			  }
			  } });
		
	}
}
