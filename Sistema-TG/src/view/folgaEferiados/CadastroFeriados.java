package view.folgaEferiados;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import controller.AlteracaoDAO;
import controller.EscalaDAO;
import controller.FeriadoDAO;
import controller.FolgaDAO;
import model.Data;
import model.Feriado;
import model.Folga;

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

		JLabel Nomes = new JLabel("Nome do Feriado:");
		Nomes.setFont(new Font("Arial Black", Font.BOLD, 15));
		Nomes.setBounds(10, 214, 154, 17);
		contentPane.add(Nomes);

		FieldGeral = new JTextField();
		FieldGeral.setFont(new Font("Arial Black", Font.BOLD, 15));
		FieldGeral.setBounds(166, 209, 344, 28);
		contentPane.add(FieldGeral);
		FieldGeral.setColumns(10);

		JLabel NomeData = new JLabel("Data:");
		NomeData.setFont(new Font("Arial Black", Font.BOLD, 15));
		NomeData.setBounds(10, 292, 51, 13);
		contentPane.add(NomeData);

        Date dataInicial = new Date();
		
		SpinnerDateModel dateModel = new SpinnerDateModel(dataInicial, null, null, Calendar.DAY_OF_MONTH);
        JSpinner dataSpinner = new JSpinner(dateModel);

        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dataSpinner, "dd/MM/yyyy");
        dataSpinner.setEditor(dateEditor);
        
		dataSpinner.setFont(new Font("Arial Black", Font.BOLD, 15));
		dataSpinner.setBounds(71, 277, 120, 42);
		contentPane.add(dataSpinner);

		JLabel TipoFeriado = new JLabel("Tipo de Feriado:");
		TipoFeriado.setFont(new Font("Arial Black", Font.BOLD, 15));
		TipoFeriado.setBounds(10, 358, 154, 21);
		contentPane.add(TipoFeriado);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial Black", Font.BOLD, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nacional", "Estadual", "Municipal"}));
		comboBox.setBounds(166, 354, 114, 28);
		contentPane.add(comboBox);
		comboBox.setSelectedItem(null);

		JButton btnVoltar = new JButton("");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Feriados_e_Folgas FF  = new Feriados_e_Folgas();
				FF.setVisible(true);
			}
		});
		btnVoltar.setIcon(new ImageIcon(CadastroFeriados.class.getResource("/model/images/desfazer.png")));
		btnVoltar.setBounds(8, 10, 35, 35);
		contentPane.add(btnVoltar);
		btnVoltar.setVisible(true);
		
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
		dataSpinner.setVisible(false);
		NomeData.setVisible(false);
		TipoFeriado.setVisible(false);
		comboBox.setVisible(false);
		
		FeriadoCHK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (FolgaCHK.isSelected()) {
					FolgaCHK.setSelected(false);
				}
		
				if(FeriadoCHK.isSelected()) {
				Nomes.setVisible(true);
				Nomes.setText("Nome do Feriado:");
				FieldGeral.setVisible(true);
				dataSpinner.setVisible(true);
				NomeData.setVisible(true);
				TipoFeriado.setVisible(true);
				comboBox.setVisible(true);
				}
				if(!FolgaCHK.isSelected() && !FeriadoCHK.isSelected()) {
					Nomes.setVisible(false);
					FieldGeral.setVisible(false);
					dataSpinner.setVisible(false);
					NomeData.setVisible(false);
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
					Nomes.setText("Nome da Folga:");
					FieldGeral.setVisible(true);
					dataSpinner.setVisible(true);
					NomeData.setVisible(true);
					TipoFeriado.setVisible(false);
					comboBox.setVisible(false);
					}
				if(!FolgaCHK.isSelected() && !FeriadoCHK.isSelected()) {
					Nomes.setVisible(false);
					FieldGeral.setVisible(false);
					dataSpinner.setVisible(false);
					NomeData.setVisible(false);
					TipoFeriado.setVisible(false);
					comboBox.setVisible(false);
				}

			}
		});
		
		JLabel lblNewLabel_7 = new JLabel("Deseja cadastrar Feriado ou Folga?");
		lblNewLabel_7.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_7.setBounds(125, 85, 321, 28);
		contentPane.add(lblNewLabel_7);

		JButton CadastrarFeriado = new JButton("Cadastrar ");
		CadastrarFeriado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(FeriadoCHK.isSelected()) {
				    if (FieldGeral.getText().equals("") ||  String.valueOf(comboBox.getSelectedItem()).equals("null")) {

				        JOptionPane.showMessageDialog(null, "Seu Cadastro Está Incompleto!!", "Atenção!!",
				                JOptionPane.WARNING_MESSAGE);
				    }
				    
				    else {
				        
				        // Pegando o ano atual dinamicamente
				        int anoModelo = Calendar.getInstance().get(Calendar.YEAR);
				        
				        SimpleDateFormat formato = new SimpleDateFormat("yyyy");
				        Date data = (Date) dataSpinner.getValue();
				        int ano = Integer.parseInt(formato.format(data));
				        
				        if(ano < anoModelo) {
							JOptionPane.showMessageDialog(null,
									"Não é possível cadastrar datas inferiores a 01/01/" + anoModelo + "!", "Atenção!!",
									JOptionPane.WARNING_MESSAGE);
				        }
				        else {
				        	ResultSet rsFeriado = FeriadoDAO.getFeriadoData(data);
				        	
				        	try {
				        		// Se já existir um feriado na data informada
								if(rsFeriado.next()) {
									JOptionPane.showMessageDialog(null, "Já existe um feriado cadastrado nessa data!", "Feriado Existente!", JOptionPane.ERROR_MESSAGE);
								}
								// Cadastra o Feriado                   
								else {
									
									Feriado feriado = new Feriado(FieldGeral.getText(), (Date) dataSpinner.getValue(), String.valueOf(comboBox.getSelectedItem()));
									
									// Verifica se a data do feriado cadastrado é posterior a hoje
									if(feriado.getData().after(new Date())) {
										
										boolean existenciaFeriado = EscalaDAO.verificarEscalaEmData(feriado.getData());
										
										String diaSemana = Data.getDiaSemana(feriado.getData());
										
										if(existenciaFeriado && (!diaSemana.equals("DOM") && !diaSemana.equals("SAB"))) {
											AlteracaoDAO.cadastrarAlteracao("Feriado");
										}
									}
									
									
									FeriadoDAO.cadastrarFeriado(feriado);
									FieldGeral.setText("");
									comboBox.setSelectedItem(null);
									
									JOptionPane.showMessageDialog(null, "Feriado cadastrado com sucesso!", "Info", JOptionPane.INFORMATION_MESSAGE);
								}
							} catch (HeadlessException e1) {
								System.out.println("Erro! Ambiente Headless (Feriado) na tela CadastroFeriado: " + e1.getMessage());
							} catch (SQLException e1) {
								System.out.println("Erro ao buscar feriado por data na tela CadastroFeriado: " + e1.getMessage());
							}
				        	
				        }
				    }
				}
				else if (FolgaCHK.isSelected()) {
				    if(FieldGeral.getText().equals("")) {
				        JOptionPane.showMessageDialog(null, "Seu Cadastro Está Incompleto!!", "Atenção!!",
				                JOptionPane.WARNING_MESSAGE);
				    }
				    else {
				        // Pegando o ano atual dinamicamente
				        int anoModelo = Calendar.getInstance().get(Calendar.YEAR);
				        
				        SimpleDateFormat formato = new SimpleDateFormat("yyyy");
				        Date data = (Date) dataSpinner.getValue();
				        int ano = Integer.parseInt(formato.format(data));
				        
				        if(ano < anoModelo) {
				            JOptionPane.showMessageDialog(null, "Não é possível cadastrar datas inferiores a 01/01/" + anoModelo + "!", "Atenção!!", JOptionPane.WARNING_MESSAGE);
				        }
				        else {
				        	ResultSet rsFolga = FolgaDAO.getFolgaData(data);
				        	
				        	try {
				        		// Se já existir uma folga na data informada
								if(rsFolga.next()) {
									JOptionPane.showMessageDialog(null, "Já existe uma folga cadastrada nessa data!", "Folga Existente!", JOptionPane.WARNING_MESSAGE);
								}
								// Cadastra a Folga
								else {
									Folga folga = new Folga(FieldGeral.getText(), (Date) dataSpinner.getValue());
									
									// Verifica se a data da folga cadastrado é posterior a hoje
									if(folga.getData().after(new Date())) {
										
										boolean existenciaFolga = EscalaDAO.verificarEscalaEmData(folga.getData());
										
										if(existenciaFolga) {
											AlteracaoDAO.cadastrarAlteracao("Folga");
										}
									}
									
									FolgaDAO.cadastrarFolga(folga);
									
									FieldGeral.setText("");
									
									JOptionPane.showMessageDialog(null, "Folga cadastrada com sucesso!", "Info", JOptionPane.INFORMATION_MESSAGE);
								}
								
							} catch (HeadlessException e1) {
								System.out.println("Erro! Ambiente Headless (Folga) na tela CadastroFeriado: " + e1.getMessage());
							} catch (SQLException e1) {
								System.out.println("Erro ao buscar folga por data na tela CadastroFeriado: " + e1.getMessage());
							}
				        	
				        }
				        
				    }
				}
				else {
				    JOptionPane.showMessageDialog(null, "Não há nada para cadastrar! Selecione \"Feriado\" ou \"Folga\".", "Atenção!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		CadastrarFeriado.setFont(new Font("Arial Black", Font.BOLD, 15));
		CadastrarFeriado.setBounds(178, 444, 206, 33);
		contentPane.add(CadastrarFeriado);
		this.setLocationRelativeTo(null);
	}
}
