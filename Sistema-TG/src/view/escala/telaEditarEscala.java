package view.escala;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.AtiradorDAO;
import controller.EscalaDAO;
import model.Data;

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
        
        ResultSet rs = EscalaDAO.getDatasEscalas();
        
        try {
			while(rs.next()) {
				datasPermitidas.add(rs.getDate("data"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao pegar a data de todas as Escala: " + e.getMessage());
		}
        
        if(datasPermitidas.size() == 0) {
        	JOptionPane.showMessageDialog(null, "Não nenhuma escala cadastrada!\nCadastre uma Escala antes de editá-la.", "Erro!", JOptionPane.ERROR_MESSAGE);
        	fechar = true;
        	return;
        }
        
        SpinnerListModel modeloData = new SpinnerListModel(datasPermitidas);
        JSpinner dataSpinner = new JSpinner(modeloData);
        
        // Formata a exibição do JSpinner manualemnte. Não pude usar o JSpinner.DateEditor porque estou usando um SpinnerListModel 
        // ao invés de um SpinnerDateModel.
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) dataSpinner.getEditor();
        JFormattedTextField textField = editor.getTextField();
        textField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
            new javax.swing.text.DateFormatter(new SimpleDateFormat("dd/MM/yyyy"))
        ));
        
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
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
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
		
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTableHeader headerDiaSemana = dia_da_semana.getTableHeader();
		headerDiaSemana.setFont(novaEscala.getFont());						// Atribue uma fonte para o cabelho da tabela
		
		JTableHeader headerNovaEscala = novaEscala.getTableHeader();
		headerNovaEscala.setFont(novaEscala.getFont());						// Atribue uma fonte para o cabelho da tabela
		
		novaEscala.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		
		scrollPane_1.setViewportView(novaEscala);
		
		JLabel lblNewLabel_2 = new JLabel("ID do Monitor:");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(362, 136, 121, 13);
		contentPane.add(lblNewLabel_2);
		
        SpinnerNumberModel modeloMonitor = new SpinnerNumberModel(0, 0, qtdAtiradores, 1); // Valor inicial: 0, Mínimo: 0, Máximo: quantidade de atiradores, Passo: de 1 em 1
        JSpinner monitorSpinner = new JSpinner(modeloMonitor);
		monitorSpinner.setFont(new Font("Arial", Font.PLAIN, 16));
		monitorSpinner.setBounds(493, 130, 40, 26);
		contentPane.add(monitorSpinner);
		
		JLabel lblNewLabel_2_1 = new JLabel("ID do 1º Atirador:");
		lblNewLabel_2_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(21, 202, 145, 13);
		contentPane.add(lblNewLabel_2_1);
		
		SpinnerNumberModel modeloAtirador1 = new SpinnerNumberModel(0, 0, qtdAtiradores, 1); // Valor inicial: 0, Mínimo: 0, Máximo: quantidade de atiradores, Passo: de 1 em 1
		JSpinner atirador1Spinner = new JSpinner(modeloAtirador1);
		atirador1Spinner.setFont(new Font("Arial", Font.PLAIN, 16));
		atirador1Spinner.setBounds(192, 196, 40, 26);
		contentPane.add(atirador1Spinner);
		
		SpinnerNumberModel modeloAtirador2 = new SpinnerNumberModel(0, 0, qtdAtiradores, 1); // Valor inicial: 0, Mínimo: 0, Máximo: quantidade de atiradores, Passo: de 1 em 1
		JSpinner atirador2Spinner = new JSpinner(modeloAtirador2);
		atirador2Spinner.setFont(new Font("Arial", Font.PLAIN, 16));
		atirador2Spinner.setBounds(493, 196, 40, 26);
		contentPane.add(atirador2Spinner);
		
		SpinnerNumberModel modeloAtirador3 = new SpinnerNumberModel(0, 0, qtdAtiradores, 1); // Valor inicial: 0, Mínimo: 0, Máximo: quantidade de atiradores, Passo: de 1 em 1
		JSpinner atirador3Spinner = new JSpinner(modeloAtirador3);
		atirador3Spinner.setFont(new Font("Arial", Font.PLAIN, 16));
		atirador3Spinner.setBounds(191, 261, 40, 26);
		contentPane.add(atirador3Spinner);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("ID do 2º Atirador:");
		lblNewLabel_2_1_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(338, 202, 145, 13);
		contentPane.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("ID do 3º Atirador:");
		lblNewLabel_2_1_2.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2_1_2.setBounds(21, 267, 145, 13);
		contentPane.add(lblNewLabel_2_1_2);
		
		JButton btnEditarEscala = new JButton("Editar Escala");
		btnEditarEscala.setFont(new Font("Arial", Font.PLAIN, 15));
		btnEditarEscala.setBounds(216, 509, 138, 34);
		contentPane.add(btnEditarEscala);
		
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
        		
        		if(monitorId > 0 && monitorId <= qtdAtiradores) {
        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) monitorSpinner.getValue()) });
        		}
        		else {
        			modelo.addRow(new String[] { "" });
        		}
        		
        		if(atirador1Id > 0 && atirador1Id <= qtdAtiradores) {
        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador1Spinner.getValue()) });
        		}
        		else {
        			modelo.addRow(new String[] { "" });
        		}
        		
        		if(atirador2Id > 0 && atirador2Id <= qtdAtiradores) {
        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador2Spinner.getValue()) });
        		}
        		else {
        			modelo.addRow(new String[] { "" });
        		}
        		
        		if(atirador3Id > 0 && atirador3Id <= qtdAtiradores) {
        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador3Spinner.getValue()) });
        		}
        		else {
        			modelo.addRow(new String[] { "" });
        		}
        		
        		novaEscala.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        		
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
	        		
	        		if(monitorId > 0 && monitorId <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) monitorSpinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador1Id > 0 && atirador1Id <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador1Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador2Id > 0 && atirador2Id <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador2Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador3Id > 0 && atirador3Id <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador3Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		novaEscala.getColumnModel().getColumn(0).setCellRenderer(centralizado);
	        		
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
	        		
	        		if(monitorId > 0 && monitorId <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) monitorSpinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador1Id > 0 && atirador1Id <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador1Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador2Id > 0 && atirador2Id <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador2Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador3Id > 0 && atirador3Id <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador3Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		novaEscala.getColumnModel().getColumn(0).setCellRenderer(centralizado);
	        		
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
	        		
	        		if(monitorId > 0 && monitorId <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) monitorSpinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador1Id > 0 && atirador1Id <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador1Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador2Id > 0 && atirador2Id <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador2Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador3Id > 0 && atirador3Id <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador3Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		novaEscala.getColumnModel().getColumn(0).setCellRenderer(centralizado);
	        		
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
	        		
	        		if(monitorId > 0 && monitorId <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) monitorSpinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador1Id > 0 && atirador1Id <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador1Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador2Id > 0 && atirador2Id <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador2Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		if(atirador3Id > 0 && atirador3Id <= qtdAtiradores) {
	        			modelo.addRow(new String[] { AtiradorDAO.getGuerraAtirador((Integer) atirador3Spinner.getValue()) });
	        		}
	        		else {
	        			modelo.addRow(new String[] { "" });
	        		}
	        		
	        		novaEscala.getColumnModel().getColumn(0).setCellRenderer(centralizado);
	        		
	        		novaEscala.setModel(modelo);
					
				}
			});
		 
		 btnEditarEscala.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					data = Data.addDias(data, -1);
					
					
					
				}
			});
		
		
		this.setLocationRelativeTo(null);
	}
}
