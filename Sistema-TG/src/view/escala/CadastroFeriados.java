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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class CadastroFeriados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FieldGeral;

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
		this.setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Folgas e Feriados");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel.setBounds(130, 10, 316, 33);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Insira as informações nos campos abaixo");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_1.setBounds(98, 53, 365, 22);
		contentPane.add(lblNewLabel_1);

		JLabel Nomes = new JLabel("Nome do Feriado");
		Nomes.setFont(new Font("Arial Black", Font.BOLD, 15));
		Nomes.setBounds(10, 214, 154, 17);
		contentPane.add(Nomes);

		FieldGeral = new JTextField();
		FieldGeral.setFont(new Font("Arial Black", Font.PLAIN, 10));
		FieldGeral.setBounds(166, 214, 344, 21);
		contentPane.add(FieldGeral);
		FieldGeral.setColumns(10);

		JLabel NomeData = new JLabel("Data:");
		NomeData.setFont(new Font("Arial Black", Font.BOLD, 15));
		NomeData.setBounds(10, 305, 51, 13);
		contentPane.add(NomeData);

		JSpinner DiaS = new JSpinner();
		DiaS.setModel(
				new SpinnerDateModel(new Date(1704078000000L), new Date(1704078000000L), null, Calendar.DAY_OF_YEAR));
		DiaS.setFont(new Font("Arial Black", Font.BOLD, 15));
		DiaS.setBounds(71, 290, 178, 42);
		contentPane.add(DiaS);

		JLabel TipoFeriado = new JLabel("Tipo de Feriado");
		TipoFeriado.setFont(new Font("Arial Black", Font.BOLD, 15));
		TipoFeriado.setBounds(10, 358, 154, 21);
		contentPane.add(TipoFeriado);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial Black", Font.BOLD, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nacional", "Estadual", "Municipal"}));
		comboBox.setBounds(166, 354, 114, 28);
		contentPane.add(comboBox);
		comboBox.setSelectedItem(null);

		JButton CadastrarFeriado = new JButton("Cadastrar ");
		CadastrarFeriado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (FieldGeral.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Seu Cadastro Está Incompleto!!", "Atenção!!",
							JOptionPane.WARNING_MESSAGE);

			}}
		});
		CadastrarFeriado.setFont(new Font("Arial Black", Font.BOLD, 15));
		CadastrarFeriado.setBounds(63, 436, 206, 33);
		contentPane.add(CadastrarFeriado);

		JButton Menu = new JButton("Voltar ao Menu");
		Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				telaFolga folga = new telaFolga();
				folga.setVisible(true);
			}
		});
		Menu.setFont(new Font("Arial Black", Font.BOLD, 15));
		Menu.setBounds(304, 436, 206, 33);
		contentPane.add(Menu);

		JLabel NomeData2 = new JLabel("Datas abaixo de 01/01/2024");
		NomeData2.setFont(new Font("Arial Black", Font.BOLD, 15));
		NomeData2.setBounds(259, 282, 245, 33);
		contentPane.add(NomeData2);

		JLabel NomeData3 = new JLabel(" não são permitidas");
		NomeData3.setFont(new Font("Arial Black", Font.BOLD, 15));
		NomeData3.setBounds(290, 305, 185, 37);
		contentPane.add(NomeData3);
		
		JCheckBox FeriadoCHK = new JCheckBox("Feriado");
		FeriadoCHK.setFont(new Font("Arial Black", Font.BOLD, 15));
		FeriadoCHK.setBounds(98, 130, 93, 21);
		contentPane.add(FeriadoCHK);
		
		JCheckBox FolgaCHK = new JCheckBox("Folga");
		FolgaCHK.setFont(new Font("Arial Black", Font.BOLD, 15));
		FolgaCHK.setBounds(353, 130, 93, 21);
		contentPane.add(FolgaCHK);
		
		
		Nomes.setVisible(false);
		FieldGeral.setVisible(false);
		DiaS.setVisible(false);
		NomeData.setVisible(false);
		NomeData2.setVisible(false);
		NomeData3.setVisible(false);
		TipoFeriado.setVisible(false);
		comboBox.setVisible(false);
		
		FeriadoCHK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (FolgaCHK.isSelected()) {
					FolgaCHK.setSelected(false);
				}
		
				if(FeriadoCHK.isSelected()) {
				Nomes.setVisible(true);
				Nomes.setText("Nome do Feriado");
				FieldGeral.setVisible(true);
				DiaS.setVisible(true);
				NomeData.setVisible(true);
				NomeData2.setVisible(true);
				NomeData3.setVisible(true);
				TipoFeriado.setVisible(true);
				comboBox.setVisible(true);
				}
				if(!FolgaCHK.isSelected() && !FeriadoCHK.isSelected()) {
					Nomes.setVisible(false);
					FieldGeral.setVisible(false);
					DiaS.setVisible(false);
					NomeData.setVisible(false);
					NomeData2.setVisible(false);
					NomeData3.setVisible(false);
					TipoFeriado.setVisible(false);
					comboBox.setVisible(false);
				}
			}
		});

		FolgaCHK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if ( FeriadoCHK.isSelected()) {
					FeriadoCHK.setSelected(false);
				}
				if(FolgaCHK.isSelected()) {
					Nomes.setVisible(true);
					Nomes.setText("Nome da Folga");
					FieldGeral.setVisible(true);
					DiaS.setVisible(true);
					NomeData.setVisible(true);
					NomeData2.setVisible(true);
					NomeData3.setVisible(true);
					TipoFeriado.setVisible(false);
					comboBox.setVisible(false);
					}
				if(!FolgaCHK.isSelected() && !FeriadoCHK.isSelected()) {
					Nomes.setVisible(false);
					FieldGeral.setVisible(false);
					DiaS.setVisible(false);
					NomeData.setVisible(false);
					NomeData2.setVisible(false);
					NomeData3.setVisible(false);
					TipoFeriado.setVisible(false);
					comboBox.setVisible(false);
				}

			}
		});
		
		JLabel lblNewLabel_7 = new JLabel("Deseja cadastrar Feriado ou Folga?");
		lblNewLabel_7.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_7.setBounds(125, 85, 321, 28);
		contentPane.add(lblNewLabel_7);

		this.setLocationRelativeTo(null);
	}
}
