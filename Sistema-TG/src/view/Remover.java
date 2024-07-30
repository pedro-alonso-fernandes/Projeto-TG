package view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import javax.swing.SpinnerNumberModel;

public class Remover extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField Campo;
	private JTextField textField;

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
		lblNewLabel.setBounds(209, 10, 159, 48);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblNome.setBounds(10, 156, 86, 21);
		contentPanel.add(lblNome);
		
		Campo = new JTextField();
		Campo.setFont(new Font("Arial Black", Font.PLAIN, 12));
		Campo.setColumns(10);
		Campo.setBounds(102, 156, 370, 25);
		contentPanel.add(Campo);
		
		JLabel lblNewLabel_1_3 = new JLabel("ID");
		lblNewLabel_1_3.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblNewLabel_1_3.setBounds(10, 212, 86, 21);
		contentPanel.add(lblNewLabel_1_3);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int codigo = e.getKeyChar();
				
				if(codigo != 48 && codigo != 49 && codigo != 50 && codigo != 51 && codigo != 52 && codigo != 53 && codigo != 54 && codigo != 55 && codigo != 56 && codigo != 57 && codigo != 8 && codigo != 65535 && codigo != 10) {
					textField.setText("");
				}
			}
		});
		textField.setFont(new Font("Arial Black", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(102, 212, 370, 25);
		contentPanel.add(textField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Folga");
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblNewLabel_1_1.setBounds(10, 262, 86, 32);
		contentPanel.add(lblNewLabel_1_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(-1), null, null, Integer.valueOf(1)));
		spinner.setFont(new Font("Arial Black", Font.PLAIN, 20));
		spinner.setBounds(102, 262, 62, 44);
		contentPanel.add(spinner);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Cargo");
		lblNewLabel_1_3_1.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblNewLabel_1_3_1.setBounds(10, 342, 86, 25);
		contentPanel.add(lblNewLabel_1_3_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Atirador");
		rdbtnNewRadioButton.setFont(new Font("Arial Black", Font.BOLD, 22));
		rdbtnNewRadioButton.setBounds(102, 349, 151, 21);
		contentPanel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnMonitor = new JRadioButton("Monitor");
		rdbtnMonitor.setFont(new Font("Arial Black", Font.BOLD, 22));
		rdbtnMonitor.setBounds(255, 349, 123, 21);
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
				
				if(rdbtnNewRadioButton.isSelected()) {
					rdbtnNewRadioButton.setSelected(false);
				}
				
			}
		});
		
		JButton Remover = new JButton("Remover");
		Remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(Campo.getText().equals("") || textField.getText().equals("") ||  (rdbtnMonitor.isSelected() == false && rdbtnNewRadioButton.isSelected() == false) || spinner.getValue().equals(-1) ) {
					  
					  JOptionPane.showMessageDialog(null, "Seu Cadastro Está Incompleto", "Incompleto", JOptionPane.WARNING_MESSAGE);
					  
				 }
			}
		});
		Remover.setFont(new Font("Arial Black", Font.BOLD, 15));
		Remover.setBounds(75, 409, 178, 40);
		contentPanel.add(Remover);
		
		JButton Menu = new JButton("Menu Atirador");
		Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				telaAtirador Atirador = new telaAtirador();
				Atirador.setVisible(true);			}
		});
		Menu.setFont(new Font("Arial Black", Font.BOLD, 15));
		Menu.setBounds(294, 409, 178, 40);
		contentPanel.add(Menu);
	
		this.setLocationRelativeTo(null);
	}
}
