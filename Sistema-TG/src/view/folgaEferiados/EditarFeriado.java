package view.folgaEferiados;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.FeriadoDAO;
import controller.FolgaDAO;
import model.Feriado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.lang.model.util.SimpleAnnotationValueVisitor14;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class EditarFeriado extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private int id = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditarFeriado dialog = new EditarFeriado();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditarFeriado() {
		setBounds(100, 100, 586, 544);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Editar Feriado e Folga");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel.setBounds(158, 10, 273, 40);
		contentPanel.add(lblNewLabel);

		Calendar calendario = Calendar.getInstance();
		calendario.set(2024, Calendar.JANUARY, 1);
		Date dataInicial = calendario.getTime();

		SpinnerDateModel dateModel = new SpinnerDateModel(dataInicial, null, null, Calendar.DAY_OF_MONTH);
		JSpinner dataSpinner = new JSpinner(dateModel);

		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dataSpinner, "dd/MM/yyyy");
		dataSpinner.setEditor(dateEditor);

		dataSpinner.setModel(
				new SpinnerDateModel(new Date(1704078000000L), new Date(1704078000000L), null, Calendar.DAY_OF_YEAR));
		dataSpinner.setFont(new Font("Arial Black", Font.BOLD, 15));
		dataSpinner.setBounds(252, 116, 120, 42);
		contentPanel.add(dataSpinner);

		Calendar calendario1 = Calendar.getInstance();
		calendario1.set(2024, Calendar.JANUARY, 1);
		Date dataInicial1 = calendario1.getTime();

		SpinnerDateModel dateModel1 = new SpinnerDateModel(dataInicial1, null, null, Calendar.DAY_OF_MONTH);
		JSpinner dataSpinner1 = new JSpinner(dateModel1);

		JSpinner.DateEditor dateEditor1 = new JSpinner.DateEditor(dataSpinner1, "dd/MM/yyyy");
		dataSpinner1.setEditor(dateEditor1);

		dataSpinner1.setModel(
				new SpinnerDateModel(new Date(1704078000000L), new Date(1704078000000L), null, Calendar.DAY_OF_YEAR));
		dataSpinner1.setFont(new Font("Arial Black", Font.BOLD, 15));
		dataSpinner1.setBounds(85, 281, 120, 42);
		contentPanel.add(dataSpinner1);
		dataSpinner1.setVisible(false);

		JLabel lblNewLabel_2 = new JLabel("Data da Folga ou Feriado:");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_2.setBounds(8, 129, 233, 16);
		contentPanel.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnNewButton.setBounds(394, 125, 128, 28);
		contentPanel.add(btnNewButton);

		JLabel lblNewLabel_3 = new JLabel("Nome do Feriado:");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_3.setBounds(8, 216, 160, 25);
		contentPanel.add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);

		textField = new JTextField();
		textField.setFont(new Font("Arial Black", Font.BOLD, 15));
		textField.setBounds(176, 217, 364, 28);
		contentPanel.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);

		JLabel lblNewLabel_3_1 = new JLabel("Data:");
		lblNewLabel_3_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_3_1.setBounds(8, 290, 69, 25);
		contentPanel.add(lblNewLabel_3_1);
		lblNewLabel_3_1.setVisible(false);

		JLabel lblNewLabel_3_2 = new JLabel("Tipo de Feriado: ");
		lblNewLabel_3_2.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_3_2.setBounds(8, 367, 152, 25);
		contentPanel.add(lblNewLabel_3_2);
		lblNewLabel_3_2.setVisible(false);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Nacional", "Estadual", "Municipal" }));
		comboBox.setFont(new Font("Arial Black", Font.BOLD, 15));
		comboBox.setBounds(158, 367, 114, 28);
		contentPanel.add(comboBox);
		comboBox.setSelectedItem(null);
		comboBox.setVisible(false);

		JButton btnNewButton_1 = new JButton("Editar");
		btnNewButton_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnNewButton_1.setBounds(85, 436, 206, 33);
		contentPanel.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Menu");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnNewButton_1_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnNewButton_1_1.setBounds(316, 436, 206, 33);
		contentPanel.add(btnNewButton_1_1);

		JLabel lblNewLabel_1_1 = new JLabel("Insira as informações nos campos abaixo");
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(100, 47, 365, 22);
		contentPanel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_7 = new JLabel("Deseja editar qual Data?");
		lblNewLabel_7.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_7.setBounds(169, 78, 221, 28);
		contentPanel.add(lblNewLabel_7);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int anoModelo = 2024;

				SimpleDateFormat formato = new SimpleDateFormat("yyyy");
				Date data = (Date) dataSpinner.getValue();
				int ano = Integer.parseInt(formato.format(data));

				if ((ano < anoModelo)) {
					JOptionPane.showMessageDialog(null, "Não é possível pesquisar datas inferiores a 01/01/2024!",
							"Atenção!!", JOptionPane.WARNING_MESSAGE);
				}
				// Editar folga ou feriado
				else {
					ResultSet rs = FolgaDAO.getFolgaData(data);
					ResultSet fr = FeriadoDAO.getFeriadoData(data);
					try {
						boolean comp1 = rs.next();
						boolean comp2 = fr.next();
						if (comp1 == true) {
							id = rs.getInt("id");
							lblNewLabel_3.setVisible(true);
							lblNewLabel_3.setText("Nome da Folga:");
							lblNewLabel_3_1.setVisible(true);
							textField.setVisible(true);
							dataSpinner1.setVisible(true);
							lblNewLabel_3_2.setVisible(false);
							comboBox.setVisible(false);
						}
						if (comp2 == true) {
							id = fr.getInt("id");
							lblNewLabel_3.setVisible(true);
							lblNewLabel_3.setText("Nome do Feriado:");
							lblNewLabel_3_1.setVisible(true);
							textField.setVisible(true);
							dataSpinner1.setVisible(true);
							lblNewLabel_3_2.setVisible(true);
							comboBox.setVisible(true);
						}
						if ((comp2 || comp1) == false) {
							id = 0;
							JOptionPane.showMessageDialog(null, "Não há nenhuma Folga ou Feriado cadastrado nesse dia!",
									"Atenção!!", JOptionPane.WARNING_MESSAGE);
							lblNewLabel_3.setVisible(false);
							lblNewLabel_3_1.setVisible(false);
							textField.setVisible(false);
							dataSpinner1.setVisible(false);
							lblNewLabel_3_2.setVisible(false);
							comboBox.setVisible(false);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Feriado fr = new Feriado();
				fr.setId(id);
				fr.setNome(textField.getText());
				fr.setData((Date)dataSpinner1.getValue());
				fr.setTipo(String.valueOf(comboBox.getSelectedItem()));
				
				FeriadoDAO.editarFeriado(fr);
				
			}
		});
	}
}
