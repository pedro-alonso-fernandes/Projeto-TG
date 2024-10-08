package view.escala;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.AtiradorDAO;
import controller.EscalaDAO;
import controller.FeriadoDAO;
import controller.GuardaDAO;
import model.Data;
import model.Escala;

public class telaEditarEscala extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable dia_da_semana;
	private JTable novaEscala;
	private int qtdAtiradores = AtiradorDAO.getQtdAtiradoresGeral();
	private Date data = null;
	private int monitorId = 0;
	private int atirador1Id = 0;
	private int atirador2Id = 0;
	private int atirador3Id = 0;
	private List<Date> datasPermitidas = new ArrayList<>();
	private List<Integer> monitorIdPermitido = new ArrayList<>();
	private List<Integer> atiradorIdPermitido = new ArrayList<>();
	boolean fechar = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaEditarEscala frame = new telaEditarEscala();
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
	public telaEditarEscala() {
		this.setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Editar Escala");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel.setBounds(216, 10, 162, 26);
		contentPane.add(lblNewLabel);
		
//		Calendar calendar = Calendar.getInstance();
//        Date hoje = calendar.getTime(); // Data atual
//
//        ResultSet rsPrimeiraEscala = EscalaDAO.getPrimeiraEscala();
//        Date dataMinima = null;
//		try {
//			rsPrimeiraEscala.next();
//			dataMinima = rsPrimeiraEscala.getDate("data");
//		} catch (SQLException e) {
//			System.out.println("Erro ao pegar data da primeira Escala: " + e.getMessage());
//		}
//        
//        calendar.set(2030, Calendar.DECEMBER, 31);
//        Date dataMaxima = calendar.getTime();
        
        ResultSet rsEscala = EscalaDAO.getDatasEscalas(new Date()); // O método pega escala com datas maiores do que a data informada
        ResultSet rsMonitor = AtiradorDAO.getMonitores();
        ResultSet rsAtirador = AtiradorDAO.getAtiradores();
        
        try {
			while(rsEscala.next()) {
				datasPermitidas.add(rsEscala.getDate("data"));
			}
			
			monitorIdPermitido.add(0);
			while(rsMonitor.next()) {
				monitorIdPermitido.add(rsMonitor.getInt("id"));
			}
			
			atiradorIdPermitido.add(0);
			while(rsAtirador.next()) {
				atiradorIdPermitido.add(rsAtirador.getInt("id"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a data de todas as escalas, e todos os atiradores: " + e.getMessage());
		}
        
        if(datasPermitidas.size() == 0) {
        	JOptionPane.showMessageDialog(null, "Não nenhuma escala cadastrada!\nCadastre uma Escala antes de editá-la.", "Erro!", JOptionPane.ERROR_MESSAGE);
        	fechar = true;
        	return;
        }
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        SpinnerListModel modeloData = new SpinnerListModel(datasPermitidas);
        JSpinner dataSpinner = new JSpinner(modeloData);
        
        // Formata a exibição do JSpinner manualemnte. Não pude usar o JSpinner.DateEditor porque estou usando um SpinnerListModel 
        // ao invés de um SpinnerDateModel.
        JSpinner.DefaultEditor editorData = (JSpinner.DefaultEditor) dataSpinner.getEditor();
        JFormattedTextField textFieldData = editorData.getTextField();
        textFieldData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
            new javax.swing.text.DateFormatter(new SimpleDateFormat("dd/MM/yyyy"))
        ));
        
        
        textFieldData.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent evt) {
        		if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    verificarDataSpinner(dataSpinner, textFieldData, formato, datasPermitidas);
                }
        	}
        });
        textFieldData.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		verificarDataSpinner(dataSpinner, textFieldData, formato, datasPermitidas);
        	}
        });
        
		dataSpinner.setFont(new Font("Arial", Font.PLAIN, 16));
		dataSpinner.setBounds(181, 122, 106, 42);
		contentPane.add(dataSpinner);
		
		JLabel lblNewLabel_1 = new JLabel("Data da Escala:");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(21, 136, 131, 13);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(181, 319, 194, 22);
		contentPane.add(scrollPane);
		

		data = (Date) dataSpinner.getValue();
		String dia = "";
		
		switch (Data.getDiaSemana(data)) {
		case "DOM":
			dia = "Domingo";
			break;
		case "SEG":
			dia = "Segunda";
			break;
		case "TER":
			dia = "Terça";
			break;
		case "QUA":
			dia = "Quarta";
			break;
		case "QUI":
			dia = "Quinta";
			break;
		case "SEX":
			dia = "Sexta";
			break;
		case "SAB":
			dia = "Sábado";
			break;	
		}
		
		dia_da_semana = new JTable();
		dia_da_semana.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { dia }));
		dia_da_semana.getColumnModel().getColumn(0).setResizable(false); // Impede que o usuário mude o tamanho da coluna
		dia_da_semana.getTableHeader().setReorderingAllowed(false); // Impede que o usuário mova a coluna
		
		
		scrollPane.setViewportView(dia_da_semana);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(181, 339, 194, 103);
		contentPane.add(scrollPane_1);
		
		novaEscala = new JTable();
		novaEscala.setFont(new Font("Arial", Font.PLAIN, 13));
		novaEscala.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { formato.format(data)}));
		novaEscala.getColumnModel().getColumn(0).setResizable(false); // Impede que o usuário mude o tamanho da coluna
		novaEscala.getTableHeader().setReorderingAllowed(false); // Impede que o usuário mova a coluna
		novaEscala.setRowHeight(20);
		novaEscala.setEnabled(false);
		
		
		JTableHeader headerDiaSemana = dia_da_semana.getTableHeader();
		headerDiaSemana.setFont(novaEscala.getFont());						// Atribue uma fonte para o cabelho da tabela
		
		JTableHeader headerNovaEscala = novaEscala.getTableHeader();
		headerNovaEscala.setFont(novaEscala.getFont());						// Atribue uma fonte para o cabelho da tabela
		
		
		scrollPane_1.setViewportView(novaEscala);
		
		JLabel lblNewLabel_2 = new JLabel("ID do Monitor:");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(20, 207, 121, 13);
		contentPane.add(lblNewLabel_2);
		
//        SpinnerNumberModel modeloMonitor = new SpinnerNumberModel(0, 0, qtdAtiradores, 1); // Valor inicial: 0, Mínimo: 0, Máximo: quantidade de atiradores, Passo: de 1 em 1
		SpinnerListModel modeloMonitor = new SpinnerListModel(monitorIdPermitido);
		JSpinner monitorSpinner = new JSpinner(modeloMonitor);
		configurarSpinner(monitorSpinner, monitorIdPermitido);
		
        monitorSpinner.setName("Monitor");
		monitorSpinner.setFont(new Font("Arial", Font.PLAIN, 16));
		monitorSpinner.setBounds(151, 201, 40, 26);
		contentPane.add(monitorSpinner);
		
		JLabel lblNewLabel_2_1 = new JLabel("ID do 1º Atirador:");
		lblNewLabel_2_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(336, 201, 145, 13);
		contentPane.add(lblNewLabel_2_1);
		
		
//		SpinnerNumberModel modeloAtirador1 = new SpinnerNumberModel(0, 0, qtdAtiradores, 1); // Valor inicial: 0, Mínimo: 0, Máximo: quantidade de atiradores, Passo: de 1 em 1
		SpinnerListModel modeloAtirador1 = new SpinnerListModel(atiradorIdPermitido);
		JSpinner atirador1Spinner = new JSpinner(modeloAtirador1);
		configurarSpinner(atirador1Spinner, atiradorIdPermitido);
		
		atirador1Spinner.setName("Atirador 1");
		atirador1Spinner.setFont(new Font("Arial", Font.PLAIN, 16));
		atirador1Spinner.setBounds(507, 195, 40, 26);
		contentPane.add(atirador1Spinner);
		
//		SpinnerNumberModel modeloAtirador2 = new SpinnerNumberModel(0, 0, qtdAtiradores, 1); // Valor inicial: 0, Mínimo: 0, Máximo: quantidade de atiradores, Passo: de 1 em 1
		SpinnerListModel modeloAtirador2 = new SpinnerListModel(atiradorIdPermitido);
		JSpinner atirador2Spinner = new JSpinner(modeloAtirador2);
		configurarSpinner(atirador2Spinner, atiradorIdPermitido);
		
		atirador2Spinner.setName("Atirador 2");
		atirador2Spinner.setFont(new Font("Arial", Font.PLAIN, 16));
		atirador2Spinner.setBounds(176, 255, 40, 26);
		contentPane.add(atirador2Spinner);
		
//		SpinnerNumberModel modeloAtirador3 = new SpinnerNumberModel(0, 0, qtdAtiradores, 1); // Valor inicial: 0, Mínimo: 0, Máximo: quantidade de atiradores, Passo: de 1 em 1
		SpinnerListModel modeloAtirador3 = new SpinnerListModel(atiradorIdPermitido);
		JSpinner atirador3Spinner = new JSpinner(modeloAtirador3);
		configurarSpinner(atirador3Spinner, atiradorIdPermitido);
		
		atirador3Spinner.setName("Atirador 3");
		atirador3Spinner.setFont(new Font("Arial", Font.PLAIN, 16));
		atirador3Spinner.setBounds(507, 255, 40, 26);
		contentPane.add(atirador3Spinner);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("ID do 2º Atirador:");
		lblNewLabel_2_1_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(21, 261, 145, 13);
		contentPane.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("ID do 3º Atirador:");
		lblNewLabel_2_1_2.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2_1_2.setBounds(337, 261, 145, 13);
		contentPane.add(lblNewLabel_2_1_2);
		
		JButton btnEditarEscala = new JButton("Editar Escala");
		btnEditarEscala.setFont(new Font("Arial", Font.PLAIN, 15));
		btnEditarEscala.setBounds(216, 509, 138, 34);
		contentPane.add(btnEditarEscala);
		
		colorirTabela();

		DefaultTableModel modelo = (DefaultTableModel) novaEscala.getModel();
		modelo.addRow(new String[] { "" });
		modelo.addRow(new String[] { "" });
		modelo.addRow(new String[] { "" });
		modelo.addRow(new String[] { "" });
		
		
		dataSpinner.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) {
        		data = (Date) dataSpinner.getValue();
        		String dia = "";
        		
        		switch (Data.getDiaSemana(data)) {
        		case "DOM":
        			dia = "Domingo";
        			break;
        		case "SEG":
        			dia = "Segunda";
        			break;
        		case "TER":
        			dia = "Terça";
        			break;
        		case "QUA":
        			dia = "Quarta";
        			break;
        		case "QUI":
        			dia = "Quinta";
        			break;
        		case "SEX":
        			dia = "Sexta";
        			break;
        		case "SAB":
        			dia = "Sábado";
        			break;	
        		}
        		dia_da_semana.setModel(new DefaultTableModel(new Object[][] {},
        				new String[] { dia }));
        		
        		novaEscala.setModel(new DefaultTableModel(new Object[][] {},
        				new String[] { formato.format(data)}));
        		
        		DefaultTableModel modelo = (DefaultTableModel) novaEscala.getModel();
        		monitorId = (Integer) monitorSpinner.getValue();
        		atirador1Id = (Integer) atirador1Spinner.getValue();
        		atirador2Id = (Integer) atirador2Spinner.getValue();
        		atirador3Id = (Integer) atirador3Spinner.getValue();
        		
        		if(monitorId > 0) {
        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) monitorSpinner.getValue()) });
        		}
        		else {
        			modelo.addRow(new String[] { "" });
        		}
        		
        		if(atirador1Id > 0) {
        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador1Spinner.getValue()) });
        		}
        		else {
        			modelo.addRow(new String[] { "" });
        		}
        		
        		if(atirador2Id > 0) {
        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador2Spinner.getValue()) });
        		}
        		else {
        			modelo.addRow(new String[] { "" });
        		}
        		
        		if(atirador3Id > 0) {
        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador3Spinner.getValue()) });
        		}
        		else {
        			modelo.addRow(new String[] { "" });
        		}
        		
        		colorirTabela();
        		
        		novaEscala.setModel(modelo);
        		
        	}
        });
		
		 monitorSpinner.addChangeListener(new ChangeListener() {
	        	public void stateChanged(ChangeEvent e) {
	        		
	        		novaEscala.setModel(new DefaultTableModel(new Object[][] {},
	        				new String[] { formato.format(data)}));
	        		
	        		DefaultTableModel modelo = (DefaultTableModel) novaEscala.getModel();
	        		monitorId = (Integer) monitorSpinner.getValue();
	        		atirador1Id = (Integer) atirador1Spinner.getValue();
	        		atirador2Id = (Integer) atirador2Spinner.getValue();
	        		atirador3Id = (Integer) atirador3Spinner.getValue();
	        		
	        		if(monitorId > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) monitorSpinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador1Id > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador1Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador2Id > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador2Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador3Id > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador3Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		colorirTabela();
	        		
	        		novaEscala.setModel(modelo);
	        		
	        	}
	        });
		 
		 atirador1Spinner.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					
					novaEscala.setModel(new DefaultTableModel(new Object[][] {},
	        				new String[] { formato.format(data)}));
	        		
	        		DefaultTableModel modelo = (DefaultTableModel) novaEscala.getModel();
	        		monitorId = (Integer) monitorSpinner.getValue();
	        		atirador1Id = (Integer) atirador1Spinner.getValue();
	        		atirador2Id = (Integer) atirador2Spinner.getValue();
	        		atirador3Id = (Integer) atirador3Spinner.getValue();
	        		
	        		if(monitorId > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) monitorSpinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador1Id > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador1Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador2Id > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador2Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador3Id > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador3Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		colorirTabela();
	        		
	        		novaEscala.setModel(modelo);
					
				}
			});
		 
		 atirador2Spinner.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					
					novaEscala.setModel(new DefaultTableModel(new Object[][] {},
	        				new String[] { formato.format(data)}));
	        		
	        		DefaultTableModel modelo = (DefaultTableModel) novaEscala.getModel();
	        		monitorId = (Integer) monitorSpinner.getValue();
	        		atirador1Id = (Integer) atirador1Spinner.getValue();
	        		atirador2Id = (Integer) atirador2Spinner.getValue();
	        		atirador3Id = (Integer) atirador3Spinner.getValue();
	        		
	        		if(monitorId > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) monitorSpinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador1Id > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador1Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador2Id > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador2Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador3Id > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador3Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		colorirTabela();
	        		
	        		novaEscala.setModel(modelo);
					
				}
			});
		 
		 atirador3Spinner.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					
					novaEscala.setModel(new DefaultTableModel(new Object[][] {},
	        				new String[] { formato.format(data)}));
	        		
	        		DefaultTableModel modelo = (DefaultTableModel) novaEscala.getModel();
	        		monitorId = (Integer) monitorSpinner.getValue();
	        		atirador1Id = (Integer) atirador1Spinner.getValue();
	        		atirador2Id = (Integer) atirador2Spinner.getValue();
	        		atirador3Id = (Integer) atirador3Spinner.getValue();
	        		
	        		if(monitorId > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) monitorSpinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador1Id > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador1Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador2Id > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador2Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador3Id > 0) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador3Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		colorirTabela();
	        		
	        		novaEscala.setModel(modelo);
					
				}
			});
		 
		 btnEditarEscala.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(monitorId > 0 && atirador1Id > 0 && atirador2Id > 0 && atirador3Id > 0) {
						
						data = Data.addDias(data, -1);
						try {
						
						ResultSet resultSet = GuardaDAO.getGuardaData("Preta", data);
						int i = 0;
						int[] guardaPreta = new int[qtdAtiradores];
						
						while(resultSet.next()) {
							guardaPreta[i] = resultSet.getInt("valor");
							i++;
						}
						

						resultSet = GuardaDAO.getGuardaData("Vermelha", data);
						i = 0;
						int[] guardaVermelha = new int[qtdAtiradores];
						
						while(resultSet.next()) {
							guardaVermelha[i] = resultSet.getInt("valor");
							i++;
						}
						
						data = Data.addDias(data, 1);
						String diaSemana = Data.getDiaSemana(data);
						
						// Fim de Semana
						if(diaSemana.equals("DOM") || diaSemana.equals("SAB")){
							for(int j = 0; j < guardaVermelha.length; j++) {
								if(j == monitorId - 1 || j == atirador1Id - 1 || j == atirador2Id - 1 || j == atirador3Id - 1) {
									guardaVermelha[j] = 370;
								}
							}
						}
						// Dia da Semana
						else {
							
							ResultSet rsFeriado = FeriadoDAO.getFeriadosSemana(data);
							boolean feriado = false;
							try {
								while(rsFeriado.next()) {
									if(formato.format(data).equals(formato.format(rsFeriado.getDate("data")))) {
										feriado = true;
										
									}
								}
								
								if(feriado) {
									
									for(int j = 0; j < guardaVermelha.length; j++) {
										if(j == monitorId - 1 || j == atirador1Id - 1 || j == atirador2Id - 1 || j == atirador3Id - 1) {
											guardaVermelha[j] = 370;
										}
									}
									
								}
								else {
									
									for(int j = 0; j < guardaPreta.length; j++) {
										if(j == monitorId - 1 || j == atirador1Id - 1 || j == atirador2Id - 1 || j == atirador3Id - 1) {
											guardaPreta[j] = 370;
										}
									}
								}
							} catch (SQLException ex) {
								System.out.println("Erro ao buscar feriados da semana: " + ex.getMessage());
							}
							
						}
						
						EscalaDAO.apagarEscalasData(data);
						GuardaDAO.apagarGuardasDataMaior("Preta", data);
						GuardaDAO.apagarGuardasDataMaior("Vermelha", data);
						Escala.gerarEscala(guardaPreta, guardaVermelha, 1, data);
						
						} catch (SQLException e1) {
							System.out.println("Erro ao buscar Guardas Pretas e Guardas Vermelhas: " + e1.getMessage());
						}
						
						telaEscala frame = new telaEscala();
						frame.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos antes de editar a escala!", "Campos Incompletos!", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		
		
		this.setLocationRelativeTo(null);
	}
	
	private void colorirTabela() {
		
		DefaultTableCellRenderer preta = new DefaultTableCellRenderer();
		DefaultTableCellRenderer vermelha = new DefaultTableCellRenderer();
		
		preta.setHorizontalAlignment(SwingConstants.CENTER);
		vermelha.setHorizontalAlignment(SwingConstants.CENTER);
		
		preta.setBackground(Color.BLACK);
		preta.setForeground(Color.WHITE);
		
		vermelha.setBackground(Color.RED);
		vermelha.setForeground(Color.WHITE);
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		String diaSemana = Data.getDiaSemana(data);
		
		// Fim de Semana
		if(diaSemana.equals("DOM") || diaSemana.equals("SAB")){
			novaEscala.getColumnModel().getColumn(0).setCellRenderer(vermelha);
		}
		// Dia da Semana
		else {
			novaEscala.getColumnModel().getColumn(0).setCellRenderer(preta);
		}
		
		ResultSet rsFeriado = FeriadoDAO.getFeriadosSemana(data);
		
		try {
			while(rsFeriado.next()) {
				if(formato.format(data).equals(formato.format(rsFeriado.getDate("data")))) {
					novaEscala.getColumnModel().getColumn(0).setCellRenderer(vermelha);
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar feriados da semana: " + e.getMessage());
		}
	}
	
	private void verificarValorSpinner(JSpinner spinner, JTextField textField, List<Integer> idPermitido) {
		try {
            int valor = Integer.parseInt(textField.getText());

            if (idPermitido.contains(valor)) {
                spinner.setValue(valor);  // Atualiza o spinner com o valor digitado
            } else {
                textField.setText(spinner.getValue().toString());
                
                switch (spinner.getName()) {
				case "Monitor": 
					JOptionPane.showMessageDialog(null, "Não existe nenhum Monitor com o ID informado.", "Monitor Inexistente", JOptionPane.WARNING_MESSAGE);
					break;
				case "Atirador 1":
					JOptionPane.showMessageDialog(null, "Não existe nenhum Atirador com o ID informado.", "Atirador Inexistente", JOptionPane.WARNING_MESSAGE);
					break;
				case "Atirador 2":
					JOptionPane.showMessageDialog(null, "Não existe nenhum Atirador com o ID informado.", "Atirador Inexistente", JOptionPane.WARNING_MESSAGE);
					break;
				case "Atirador 3":
					JOptionPane.showMessageDialog(null, "Não existe nenhum Atirador com o ID informado.", "Atirador Inexistente", JOptionPane.WARNING_MESSAGE);
					break;
				}
                
            }
            
        } catch (NumberFormatException ex) {
            // Se o valor não for um número, volta para o valor anterior
            textField.setText(spinner.getValue().toString());
            JOptionPane.showMessageDialog(null, "O valor digitado não é um número ou contém espaços!", "Parâmetro Incorreto", JOptionPane.WARNING_MESSAGE);
        }
	}
	
	private void verificarDataSpinner(JSpinner spinner, JFormattedTextField textField, SimpleDateFormat formato, List<Date> dataPermitida) {
		 try {
	            // Tenta converter o texto para uma data
	            String text = textField.getText();
	            Date data = formato.parse(text);

	            // Verifica se a data está na lista de datas permitidas
	            if (dataPermitida.contains(data)) {
	                spinner.setValue(data);  // Define a data no spinner
	            } else {
	            	
	            	textField.setValue(spinner.getValue());
	            	data = Data.addDias(data, -1);
	            	if(new Date().after(data)) {
	            		JOptionPane.showMessageDialog(null, "Não é possível editar Escalas passadas ou a Escala do dia atual!", "Escala Antiga!", JOptionPane.WARNING_MESSAGE);
	            	}
	            	else {
	            		JOptionPane.showMessageDialog(null, "Não existe escala regitrada nessa data!", "Escala Inexistente", JOptionPane.WARNING_MESSAGE);
	            	}
	            	
	            }

	        } catch (ParseException ex) {
	            // Se a data não for válida, volta para o valor anterior
	        	textField.setValue(spinner.getValue());  // Reverte para o valor anterior
	            JOptionPane.showMessageDialog(null, "A data digitada não é válida. Digite uma data no formato DD/MM/AAAA", "Data Inválida!", JOptionPane.WARNING_MESSAGE);
	        }
	}
	
	private void configurarSpinner(JSpinner spinner, List<Integer> idPermitido) {
		// Pegando o editor do JSpinner para acessar o JTextField
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinner.getEditor();
        JTextField textField = editor.getTextField();

        // Adiciona um FocusListener para validar quando o campo perde o foco
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                verificarValorSpinner(spinner, textField, idPermitido);
            }
        });

        // Adiciona um KeyListener para interceptar a tecla Enter
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    verificarValorSpinner(spinner, textField, idPermitido);
                }
            }
        });
	}
}
