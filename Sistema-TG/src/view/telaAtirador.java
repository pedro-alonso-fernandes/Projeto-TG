package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaAtirador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 20, 1000, 493);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1920, 1080);
		tabbedPane.addTab("Cadastrar", null, panel, null);

		JLabel lblCadastrar = new JLabel("CADASTRAR");
		lblCadastrar.setBounds(743, 12, 429, 76);
		panel.add(lblCadastrar);
		lblCadastrar.setFont(new Font("Dialog", Font.BOLD, 60));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(734, 361, 446, 331);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNome.setBounds(72, 95, 58, 15);
		panel_2.add(lblNome);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.BOLD, 16));
		textField.setColumns(10);
		textField.setBounds(148, 91, 233, 24);
		panel_2.add(textField);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Dialog", Font.BOLD, 16));
		lblId.setBounds(99, 133, 29, 15);
		panel_2.add(lblId);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.BOLD, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(148, 133, 233, 24);
		panel_2.add(textField_1);

		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCargo.setBounds(72, 174, 58, 15);
		panel_2.add(lblCargo);

		JRadioButton rdbtnAtirador = new JRadioButton("Atirador");
		rdbtnAtirador.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnAtirador.setBounds(148, 170, 108, 23);
		panel_2.add(rdbtnAtirador);

		JRadioButton rdbtnMonitor = new JRadioButton("Monitor");
		rdbtnMonitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnAtirador.isSelected()) {
					rdbtnAtirador.setSelected(false);
				}
			}
		});
		rdbtnMonitor.setFont(new Font("Dialog", Font.BOLD, 16));
		rdbtnMonitor.setBounds(260, 170, 114, 23);
		panel_2.add(rdbtnMonitor);

		rdbtnAtirador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnMonitor.isSelected()) {
					rdbtnMonitor.setSelected(false);
				}
			}
		});

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnCadastrar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnCadastrar.setBounds(167, 221, 130, 34);
		panel_2.add(btnCadastrar);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Gerenciar", null, scrollPane, null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 10, 10);
		contentPane.add(panel_1);
	}

}
