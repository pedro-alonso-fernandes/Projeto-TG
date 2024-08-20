package view.escala;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.Color;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;

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
		textField.setBounds(166, 164, 344, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Data:");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_3.setBounds(10, 218, 51, 13);
		contentPane.add(lblNewLabel_3);
		
		JSpinner DiaS = new JSpinner();
		DiaS.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		DiaS.setFont(new Font("Arial Black", Font.BOLD, 15));
		DiaS.setBounds(146, 204, 51, 42);
		contentPane.add(DiaS);
		
		JSpinner MêsS = new JSpinner();
		MêsS.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		MêsS.setFont(new Font("Arial Black", Font.BOLD, 15));
		MêsS.setBounds(253, 204, 51, 42);
		contentPane.add(MêsS);
		
		JLabel lblNewLabel_4 = new JLabel("Dia");
		lblNewLabel_4.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_4.setBounds(96, 220, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Mês");
		lblNewLabel_4_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_4_1.setBounds(207, 218, 45, 13);
		contentPane.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Ano");
		lblNewLabel_4_1_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_4_1_1.setBounds(318, 219, 45, 13);
		contentPane.add(lblNewLabel_4_1_1);
		
		JSpinner AnoS = new JSpinner();
		AnoS.setModel(new SpinnerNumberModel(Integer.valueOf(2024), Integer.valueOf(2024), null, Integer.valueOf(1)));
		AnoS.setForeground(new Color(240, 240, 240));
		AnoS.setFont(new Font("Arial Black", Font.BOLD, 15));
		AnoS.setBounds(370, 205, 98, 42);
		contentPane.add(AnoS);
		
		JLabel lblNewLabel_5 = new JLabel("Tipo de Feriado");
		lblNewLabel_5.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_5.setBounds(10, 285, 146, 21);
		contentPane.add(lblNewLabel_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial Black", Font.BOLD, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nacional", "Estadual", "Municipal"}));
		comboBox.setBounds(171, 286, 114, 28);
		contentPane.add(comboBox);
		
		
	}
}
