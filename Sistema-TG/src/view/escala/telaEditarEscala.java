package view.escala;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.AtiradorDAO;
import controller.EscalaDAO;
import model.Data;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

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
		setBounds(100, 100, 586, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Editar Escala");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel.setBounds(216, 28, 162, 26);
		contentPane.add(lblNewLabel);
		
		Calendar calendar = Calendar.getInstance();
        Date hoje = calendar.getTime(); // Data atual

        ResultSet rsPrimeiraEscala = EscalaDAO.getPrimeiraEscala();
        Date dataMinima = null;
		try {
			rsPrimeiraEscala.next();
			dataMinima = rsPrimeiraEscala.getDate("data");
		} catch (SQLException e) {
			System.out.println("Erro ao pegar data da primeira Escala: " + e.getMessage());
		}
        
        calendar.set(2030, Calendar.DECEMBER, 31);
        Date dataMaxima = calendar.getTime();
        
        SpinnerDateModel modeloData = new SpinnerDateModel(hoje, dataMinima, dataMaxima, Calendar.DAY_OF_MONTH);
        
        JSpinner dataSpinner = new JSpinner(modeloData);
        JSpinner.DateEditor editorData = new JSpinner.DateEditor(dataSpinner, "dd/MM/yyyy");
        
        dataSpinner.setEditor(editorData);
		dataSpinner.setFont(new Font("Arial", Font.PLAIN, 16));
		dataSpinner.setBounds(119, 95, 106, 42);
		contentPane.add(dataSpinner);
		
		JLabel lblNewLabel_1 = new JLabel("Data:");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_1.setBounds(45, 109, 56, 13);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(387, 160, 154, 22);
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
		scrollPane_1.setBounds(387, 180, 154, 87);
		contentPane.add(scrollPane_1);
		
		novaEscala = new JTable();
		novaEscala.setFont(new Font("Arial", Font.PLAIN, 13));
		novaEscala.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { formato.format(data)}));
		novaEscala.getColumnModel().getColumn(0).setResizable(false); // Impede que o usuário mude o tamanho da coluna
		novaEscala.getTableHeader().setReorderingAllowed(false); // Impede que o usuário mova a coluna
		novaEscala.setEnabled(false);
		
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		
		novaEscala.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		
		scrollPane_1.setViewportView(novaEscala);
		
		JLabel lblNewLabel_2 = new JLabel("Monitor:");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(33, 179, 68, 13);
		contentPane.add(lblNewLabel_2);
		
        SpinnerNumberModel modeloMonitor = new SpinnerNumberModel(0, 0, qtdAtiradores, 1); // Valor inicial: 0, Mínimo: 0, Máximo: quantidade de atiradores, Passo: de 1 em 1
        JSpinner monitorSpinner = new JSpinner(modeloMonitor);
		monitorSpinner.setFont(new Font("Arial", Font.PLAIN, 16));
		monitorSpinner.setBounds(119, 173, 40, 26);
		contentPane.add(monitorSpinner);
		
		JLabel lblNewLabel_2_1 = new JLabel("Atirador 1:");
		lblNewLabel_2_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(21, 236, 87, 13);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Atirador 2:");
		lblNewLabel_2_1_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(21, 295, 87, 13);
		contentPane.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Atirador 3:");
		lblNewLabel_2_1_2.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2_1_2.setBounds(21, 347, 87, 13);
		contentPane.add(lblNewLabel_2_1_2);
		
		SpinnerNumberModel modeloAtirador1 = new SpinnerNumberModel(0, 0, qtdAtiradores, 1); // Valor inicial: 0, Mínimo: 0, Máximo: quantidade de atiradores, Passo: de 1 em 1
		JSpinner atirador1Spinner = new JSpinner(modeloAtirador1);
		atirador1Spinner.setFont(new Font("Arial", Font.PLAIN, 16));
		atirador1Spinner.setBounds(119, 230, 40, 26);
		contentPane.add(atirador1Spinner);
		
		SpinnerNumberModel modeloAtirador2 = new SpinnerNumberModel(0, 0, qtdAtiradores, 1); // Valor inicial: 0, Mínimo: 0, Máximo: quantidade de atiradores, Passo: de 1 em 1
		JSpinner atirador2Spinner = new JSpinner(modeloAtirador2);
		atirador2Spinner.setFont(new Font("Arial", Font.PLAIN, 16));
		atirador2Spinner.setBounds(119, 289, 40, 26);
		contentPane.add(atirador2Spinner);
		
		SpinnerNumberModel modeloAtirador3 = new SpinnerNumberModel(0, 0, qtdAtiradores, 1); // Valor inicial: 0, Mínimo: 0, Máximo: quantidade de atiradores, Passo: de 1 em 1
		JSpinner atirador3Spinner = new JSpinner(modeloAtirador3);
		atirador3Spinner.setFont(new Font("Arial", Font.PLAIN, 16));
		atirador3Spinner.setBounds(119, 341, 40, 26);
		contentPane.add(atirador3Spinner);
		
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
		
		
		this.setLocationRelativeTo(null);
	}
}
