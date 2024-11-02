package view.escala;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.AtiradorDAO;
import controller.EscalaDAO;
import controller.GuardaDAO;
import model.Array;
import model.BD;
import model.Data;
import model.Escala;
import view.telaPrincipal;

public class telaGerarEscala extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private int qtdAtiradoresGeral = AtiradorDAO.getQtdAtiradoresGeral();
	private int[] guardaVermelha = new int[qtdAtiradoresGeral];
	private int[] guardaPreta = new int[qtdAtiradoresGeral];
	private int[] qtdGuarda = new int[qtdAtiradoresGeral];
	private static List<Date> datasPermitidas = new ArrayList<>();
	private static boolean escala = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaGerarEscala frame = new telaGerarEscala();
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
	public telaGerarEscala() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(telaGerarEscala.class.getResource("/model/images/calendario.png")));
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 586, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTelaTitulo = new JLabel("DADOS DA ESCALA DOS ATIRADORES E MONITORES");
		lblTelaTitulo.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblTelaTitulo.setBounds(30, 10, 522, 24);
		contentPane.add(lblTelaTitulo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 76, 557, 326);
		contentPane.add(scrollPane);

		JLabel lblCodigo = new JLabel("");
		lblCodigo.setBounds(42, 224, 70, 15);
		contentPane.add(lblCodigo);

		table = new JTable();
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome de Guerra", "Cargo", "Guarda Preta", "Guarda Vermelha", "Qtd Guarda" }) {

			private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, false, true, true, true };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(27);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(131);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(73);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(83);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(107);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(82);
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(5).setCellRenderer(centralizado);

		scrollPane.setViewportView(table);

		JButton btnEscala = new JButton("Gerar Escala");
		btnEscala.setFont(new Font("Dialog", Font.BOLD, 14));
		btnEscala.setBounds(214, 481, 156, 34);
		contentPane.add(btnEscala);

		JButton btnmenu = new JButton("Voltar ao Menu");
		btnmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				telaPrincipal principal = new telaPrincipal();
				principal.setVisible(true);
			}
		});
		btnmenu.setFont(new Font("Dialog", Font.BOLD, 14));
		btnmenu.setBounds(406, 481, 156, 34);
		contentPane.add(btnmenu);

		JLabel lblNewLabel = new JLabel("CLIQUE NOS CAMPOS E PREENCHA AS INFORMAÇÕES");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblNewLabel.setBounds(23, 41, 529, 13);
		contentPane.add(lblNewLabel);

		JLabel lblSpinner1 = new JLabel("Escolha a data do primeiro");
		lblSpinner1.setFont(new Font("Arial", Font.BOLD, 14));
		lblSpinner1.setBounds(15, 423, 198, 24);
		contentPane.add(lblSpinner1);
		
		JLabel lblSpinner2 = new JLabel("dia da Escala:");
		lblSpinner2.setFont(new Font("Arial", Font.BOLD, 14));
		lblSpinner2.setBounds(55, 447, 216, 24);
		contentPane.add(lblSpinner2);
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		escala = EscalaDAO.verificarExistenciaEscala();
		
		if(escala) {
			datasPermitidas.clear();
			datasPermitidas.add(Data.addDias(new Date(), 1));
			btnEscala.setText("Regerar Escala");
			lblSpinner1.setText("A escala vai ser regerada");
			lblSpinner2.setText("a partir de amanhã:");
			lblSpinner2.setBounds(32, 447, 216, 24);
		}
		else {
			datasPermitidas.clear();
			datasPermitidas = gerarDatasPermitidas(formato);
		}
		
		SpinnerListModel modeloData = new SpinnerListModel(datasPermitidas);
		JSpinner dataSpinner = new JSpinner(modeloData);
		dataSpinner.setFont(new Font("Arial Black", Font.BOLD, 15));
		dataSpinner.setBounds(45, 477, 120, 42);

		JSpinner.DefaultEditor editorData = (JSpinner.DefaultEditor) dataSpinner.getEditor();
		
		if(escala) {
			editorData.getTextField().setEditable(false); // Torna o campo não editável
			
			// Remove os botões de incremento e decremento
			for (Component component : dataSpinner.getComponents()) {
			    if (component instanceof JButton) {
			        dataSpinner.remove(component);
			    }
			}
			
			// Diminui a largura do dataSpinner
			dataSpinner.setBounds(45, 477, 105, 42);
		}
		
		JFormattedTextField textFieldData = editorData.getTextField();
		textFieldData.setFormatterFactory(
				new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(formato)));

		// Configura evento Enter e FocusLost
		textFieldData.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent evt) {
        		if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    verificarDataSpinner(dataSpinner, textFieldData, formato, datasPermitidas);
                }
        	}
        });
//        textFieldData.addFocusListener(new FocusAdapter() {
//        	@Override
//        	public void focusLost(FocusEvent e) {
//        		verificarDataSpinner(dataSpinner, textFieldData, formato, datasPermitidas);
//        	}
//        });
		contentPane.add(dataSpinner);


		// Popular tabela inicial
		ResultSet rs = AtiradorDAO.getAtiradoresByMonitores();
		try {
			if (escala) {

				Date data = (Date) dataSpinner.getValue();
				
				Date dataGuardaPreta = GuardaDAO.getDataGuardaMaisProxima("Preta", data);
				Date dataGuardaVermelha = GuardaDAO.getDataGuardaMaisProxima("Vermelha", data);
				int i = 0;
				
				
				while (rs.next()) {
					int qtdGuarda = rs.getInt("qtdGuarda") + EscalaDAO.getQtdGuardaAtirador(rs.getInt("id"), new Date());
					
					String linhaPreta = dataGuardaPreta == null ? "0" : "" + GuardaDAO.getValorGuardaPorAtirador("Preta", dataGuardaPreta, rs.getInt("id"));
					String linhaVermelha = dataGuardaVermelha == null ? "0" : "" + GuardaDAO.getValorGuardaPorAtirador("Vermelha", dataGuardaVermelha, rs.getInt("id"));
							
					DefaultTableModel modelo = (DefaultTableModel) table.getModel();
					modelo.addRow(new Object[] { rs.getInt("id"), rs.getString("guerra"), rs.getString("cargo"),
							linhaPreta, linhaVermelha, "" + qtdGuarda });
					
					i++;
				}

			} else {
				int i = 0;
				while (rs.next()) {
					DefaultTableModel modelo = (DefaultTableModel) table.getModel();
					modelo.addRow(new Object[] { rs.getInt("id"), rs.getString("guerra"), rs.getString("cargo"),
							"" + guardaPreta[i], "" + guardaVermelha[i], "" + qtdGuarda[i] });

					i++;
				}
			}

		} catch (SQLException e) {
			System.out.println("Erro ao preecher tabela guarda: " + e.getMessage());
		}

		btnEscala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int ultimaLinha = table.getModel().getRowCount() - 1;
//					int idUltimo = (Integer) table.getModel().getValueAt(ultimaLinha, 0);

					ultimaLinha++;
					int j = 0;
					for (int i = 0; i < ultimaLinha; i++) {
						String celula = (String) table.getModel().getValueAt(j, 3);
						guardaPreta[i] = Integer.parseInt(celula);

						celula = (String) table.getModel().getValueAt(j, 4);
						guardaVermelha[i] = Integer.parseInt(celula);

						celula = (String) table.getModel().getValueAt(j, 5);
						qtdGuarda[i] = Integer.parseInt(celula);

						j++;
					}

					int maiorPreta = Array.getMaiorValorArray(guardaPreta, 0);
					int maiorVermelha = Array.getMaiorValorArray(guardaVermelha, 0);

					if (maiorPreta > 370 || maiorVermelha > 370) {
						JOptionPane.showMessageDialog(null,
								"Um dos valores informados é muito grande para ser uma guarda!", "Erro!",
								JOptionPane.WARNING_MESSAGE);
					} else if(escala) {
						Date data = (Date) dataSpinner.getValue();
						Date dataProximaSemana = Data.diaProximaSemana(data);
						
						EscalaDAO.apagarEscalasDataMaior(data);
						GuardaDAO.apagarGuardasDataMaior("Preta", data);
						GuardaDAO.apagarGuardasDataMaior("Vermelha", data);
						
						Escala.gerarEscala(guardaPreta, guardaVermelha, data, Data.ultimoDiaSemana(dataProximaSemana));
						
						BD.reiniciarTabelaAlteracao();
						
						dispose();

						telaEscala tela = new telaEscala();
						tela.setVisible(true);
						
					} else {

						BD.reiniciarTabelaEscala();
						BD.reiniciarTabelaGuarda("Preta");
						BD.reiniciarTabelaGuarda("Vermelha");

						Date data = (Date) dataSpinner.getValue();

						Escala.gerarPrimeiraEscala(guardaPreta, guardaVermelha, qtdGuarda, data);
						
						BD.reiniciarTabelaAlteracao();
						
						dispose();

						telaEscala tela = new telaEscala();
						tela.setVisible(true);

					}

				} catch (NumberFormatException ey) {
					JOptionPane.showMessageDialog(null, "Um dos valores informados não é um número, ou possui espaços",
							"Atenção!", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		this.setLocationRelativeTo(null);
	}

	private static List<Date> gerarDatasPermitidas(SimpleDateFormat formato) {
	    List<Date> datasPermitidas = new ArrayList<>();
	    
	    switch (Data.getDiaSemana(new Date())) {
	        case "DOM":
	            for (int i = 0; i < 7; i++) {
	                datasPermitidas.add(zerarHoras(Data.addDias(new Date(), i)));
	            }
	            break;
	        case "SEG":
	            for (int i = 0; i < 6; i++) {
	                datasPermitidas.add(zerarHoras(Data.addDias(new Date(), i)));
	            }
	            break;
	        case "TER":
	            for (int i = 0; i < 5; i++) {
	                datasPermitidas.add(zerarHoras(Data.addDias(new Date(), i)));
	            }
	            break;
	        case "QUA":
	            for (int i = 0; i < 4; i++) {
	                datasPermitidas.add(zerarHoras(Data.addDias(new Date(), i)));
	            }
	            break;
	        case "QUI":
	            for (int i = 0; i < 3; i++) {
	                datasPermitidas.add(zerarHoras(Data.addDias(new Date(), i)));
	            }
	            break;
	        case "SEX":
	            for (int i = 0; i < 2; i++) {
	                datasPermitidas.add(zerarHoras(Data.addDias(new Date(), i)));
	            }
	            break;
	        case "SAB":
	            datasPermitidas.add(zerarHoras(Data.addDias(new Date(), 0)));
	            break;
	    }

	    return datasPermitidas;
	}

	// Método que tira o hórario das datas, pois isso influencia na comparação "contains" do método abaixo "verificarDataSpinner"
	private static Date zerarHoras(Date data) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(data);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    return calendar.getTime();
	}


	private static void verificarDataSpinner(JSpinner spinner, JFormattedTextField textField, SimpleDateFormat formato,
			List<Date> datasPermitidas) {
		try {
			String text = textField.getText();
			Date data = formato.parse(text);

			// Verifica se a data está na lista de datas permitidas
			if (datasPermitidas.contains(data)) {
				spinner.setValue(data); // Define a data no spinner
			} else {
				textField.setValue(spinner.getValue());

				if(escala) {
					JOptionPane.showMessageDialog(null, "A escala só pode ser regerada amanhã.", "Data Inválida!",
							JOptionPane.WARNING_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Só é possível gerar Escala na Semana Atual.", "Data Inválida!",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		} catch (ParseException ex) {
			// Reverte para o valor anterior se a data não for válida
			textField.setValue(spinner.getValue());
			JOptionPane.showMessageDialog(null, "A data digitada não é válida. Digite uma data no formato DD/MM/AAAA",
					"Data Inválida!", JOptionPane.WARNING_MESSAGE);
		}
	}
}
