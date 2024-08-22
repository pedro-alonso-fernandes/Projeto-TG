package view.escala;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

public class CadastroFeriados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFeriados frame = new CadastroFeriados();
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
	public CadastroFeriados() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(CadastroFeriados.class.getResource("/model/images/calendario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cadastro de Feriados");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel.setBounds(103, 10, 383, 33);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Insira as informações nos campos abaixo");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_1.setBounds(108, 53, 365, 22);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nome do Feriado");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 165, 154, 17);
		contentPane.add(lblNewLabel_2);

		textField = new JTextField();
		textField.setFont(new Font("Arial Black", Font.PLAIN, 10));
		textField.setBounds(166, 165, 344, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Data:");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_3.setBounds(16, 218, 51, 13);
		contentPane.add(lblNewLabel_3);

		JSpinner DiaS = new JSpinner();
		DiaS.setModel(
				new SpinnerDateModel(new Date(1704078000000L), new Date(1704078000000L), null, Calendar.DAY_OF_YEAR));
		DiaS.setFont(new Font("Arial Black", Font.BOLD, 15));
		DiaS.setBounds(77, 203, 178, 42);
		contentPane.add(DiaS);

		JLabel lblNewLabel_5 = new JLabel("Tipo de Feriado");
		lblNewLabel_5.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_5.setBounds(10, 290, 146, 21);
		contentPane.add(lblNewLabel_5);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial Black", Font.BOLD, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Nacional", "Estadual", "Municipal" }));
		comboBox.setBounds(171, 286, 114, 28);
		contentPane.add(comboBox);
		comboBox.setSelectedItem(null);

		JButton CadastrarFeriado = new JButton("Cadastrar Feriado");
		CadastrarFeriado.setFont(new Font("Arial Black", Font.BOLD, 15));
		CadastrarFeriado.setBounds(63, 392, 206, 33);
		contentPane.add(CadastrarFeriado);

		JButton Menu = new JButton("Voltar ao Menu");
		Menu.setFont(new Font("Arial Black", Font.BOLD, 15));
		Menu.setBounds(304, 392, 206, 33);
		contentPane.add(Menu);

		JLabel lblNewLabel_4 = new JLabel("Datas abaixo de 01/01/2024");
		lblNewLabel_4.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_4.setBounds(265, 196, 245, 33);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_6 = new JLabel(" não são permitidas");
		lblNewLabel_6.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_6.setBounds(288, 218, 185, 37);
		contentPane.add(lblNewLabel_6);

		this.setLocationRelativeTo(null);
	}
}
