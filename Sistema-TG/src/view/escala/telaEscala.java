package view.escala;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.Data;
import controller.EscalaDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class telaEscala extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable semanaAtual;
	private JTable dias_da_semana;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaEscala frame = new telaEscala();
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
	public telaEscala() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 816, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTabelaEscala = new JLabel("TABELA ESCALA");
		lblTabelaEscala.setFont(new Font("Dialog", Font.BOLD, 24));
		lblTabelaEscala.setBounds(289, 12, 215, 23);
		contentPane.add(lblTabelaEscala);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 106, 740, 142);
		contentPane.add(scrollPane);

		semanaAtual = new JTable();
		semanaAtual.setFont(new Font("Dialog", Font.PLAIN, 12));
		scrollPane.setViewportView(semanaAtual);

		//Atualizando tabela com a data de hoje
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		Date data = Data.primeiroDiaSemana(new Date()); // Pega a data do primeiro dia da semana atual

		semanaAtual.setModel(new DefaultTableModel(new Object[][] {}, new String[] { 
				formato.format(data), formato.format(Data.addDias(data, 1)), formato.format(Data.addDias(data, 2)),
				formato.format(Data.addDias(data, 3)), formato.format(Data.addDias(data, 4)), formato.format(Data.addDias(data, 5)), 
				formato.format(Data.addDias(data, 6)) 
				}){

	        private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean []{  
	            false, false, false, false, false, false, false
	        };  
	   
	        @Override  
	        public boolean isCellEditable(int rowIndex, int columnIndex) {  
	            return canEdit [columnIndex];  
	        }
	});
		semanaAtual.getColumnModel().getColumn(0).setResizable(false);
		semanaAtual.getColumnModel().getColumn(1).setResizable(false);
		semanaAtual.getColumnModel().getColumn(2).setResizable(false);
		semanaAtual.getColumnModel().getColumn(3).setResizable(false);
		semanaAtual.getColumnModel().getColumn(4).setResizable(false);
		semanaAtual.getColumnModel().getColumn(5).setResizable(false);
		semanaAtual.getColumnModel().getColumn(6).setResizable(false);
		
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		
		semanaAtual.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		semanaAtual.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		semanaAtual.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		semanaAtual.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		semanaAtual.getColumnModel().getColumn(4).setCellRenderer(centralizado);
		semanaAtual.getColumnModel().getColumn(5).setCellRenderer(centralizado);
		semanaAtual.getColumnModel().getColumn(6).setCellRenderer(centralizado);
		
		semanaAtual.getTableHeader().setReorderingAllowed(false); // Impede que o usuário mova as colunas
		
		DefaultTableModel modelo = (DefaultTableModel) semanaAtual.getModel();
		
		ResultSet rs = EscalaDAO.getEscalaSemana(data);
		Date dataEscala = null;
		int[] monitores = new int[7];
		int[] atiradores1 = new int[7];
		int[] atiradores2 = new int[7];
		int[] atiradores3 = new int[7]; 
//		try {
//			dataEscala = formato.parse("29/07/2024");
//		} catch (ParseException e) {
//			System.out.println("Erro ao salvar data: " + e.getMessage());
//		}
		try {
			int i = 0;
			while(rs.next()) {
				if(i == 0) {
					dataEscala = rs.getDate("data");
				}
				monitores[i] = rs.getInt("monitorId");
				atiradores1[i] = rs.getInt("atirador1Id");
				atiradores2[i] = rs.getInt("atirador2Id");
				atiradores3[i] = rs.getInt("atirador3Id");
				i++;
			}
			
		} catch(SQLException e) {
			System.out.println("Erro " + e.getMessage());
		}
		
		String[] linha = new String[7];
		
		switch (Data.getDiaSemana(dataEscala)) {
		case "DOM":
			
			for(int i = 0; i < 7; i++) {
				if(monitores[i] != 0) {
					linha[i] = String.valueOf(monitores[i]);
				}
				else {
					linha[i] = "";
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(atiradores1[i] != 0) {
					linha[i] = String.valueOf(atiradores1[i]);
				}
				else {
					linha[i] = "";
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(atiradores2[i] != 0) {
					linha[i] = String.valueOf(atiradores2[i]);
				}
				else {
					linha[i] = "";
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(atiradores3[i] != 0) {
					linha[i] = String.valueOf(atiradores3[i]);
				}
				else {
					linha[i] = "";
				}
			}
			modelo.addRow(linha);
			break;
			
			
			
			
		case "SEG":
			for(int i = 0; i < 7; i++) {
				if(i < 1 || monitores[i - 1] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(monitores[i - 1]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 1 || atiradores1[i - 1] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores1[i - 1]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 1 || atiradores2[i - 1] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores2[i - 1]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 1 || atiradores3[i - 1] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores3[i - 1]);
				}
			}
			modelo.addRow(linha);
			break;
			
			
			
			
		case "TER":
			for(int i = 0; i < 7; i++) {
				if(i < 2 || monitores[i - 2] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(monitores[i - 2]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 2 || atiradores1[i - 2] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores1[i - 2]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 2 || atiradores2[i - 2] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores2[i - 2]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 2 || atiradores3[i - 2] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores3[i - 2]);
				}
			}
			modelo.addRow(linha);
			
			break;
			
			
			
		case "QUA":
			for(int i = 0; i < 7; i++) {
				if(i < 3 || monitores[i - 3] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(monitores[i - 3]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 3 || atiradores1[i - 3] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores1[i - 3]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 3 || atiradores2[i - 3] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores2[i - 3]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 3 || atiradores3[i - 3] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores3[i - 3]);
				}
			}
			modelo.addRow(linha);
			
			break;
			
			
			
			
		case "QUI":
			for(int i = 0; i < 7; i++) {
				if(i < 4 || monitores[i - 4] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(monitores[i - 4]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 4 || atiradores1[i - 4] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores1[i - 4]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 4 || atiradores2[i - 4] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores2[i - 4]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 4 || atiradores3[i - 4] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores3[i - 4]);
				}
			}
			modelo.addRow(linha);
			
			break;
			
			
			
			
		case "SEX":
			for(int i = 0; i < 7; i++) {
				if(i < 5 || monitores[i - 5] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(monitores[i - 5]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 5 || atiradores1[i - 5] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores1[i - 5]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 5 || atiradores2[i - 5] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores2[i - 5]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 5 || atiradores3[i - 5] == 0) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores3[i - 5]);
				}
			}
			modelo.addRow(linha);
			
			break;
			
			
			
			
		case "SAB":
			for(int i = 0; i < 7; i++) {
				if(i < 6) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(monitores[i - 6]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 6) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores1[i - 6]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 6) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores2[i - 6]);
				}
			}
			modelo.addRow(linha);
			
			for(int i = 0; i < 7; i++) {
				if(i < 6) {
					linha[i] = "";
				}
				else {
					linha[i] = String.valueOf(atiradores3[i - 6]);
				}
			}
			modelo.addRow(linha);
			
			break;
		}
		
		modelo.isCellEditable(1, 1);
		semanaAtual.setModel(modelo);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(35, 88, 740, 23);
		contentPane.add(scrollPane_1);

		dias_da_semana = new JTable();
		dias_da_semana.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Domingo", "Segunda", "Ter\u00E7a", "Quarta", "Quinta", "Sexta", "S\u00E1bado" }));
		dias_da_semana.getColumnModel().getColumn(0).setResizable(false);
		dias_da_semana.getColumnModel().getColumn(1).setResizable(false);
		dias_da_semana.getColumnModel().getColumn(2).setResizable(false);
		dias_da_semana.getColumnModel().getColumn(3).setResizable(false);
		dias_da_semana.getColumnModel().getColumn(4).setResizable(false);
		dias_da_semana.getColumnModel().getColumn(5).setResizable(false);
		dias_da_semana.getColumnModel().getColumn(6).setResizable(false);

		dias_da_semana.getTableHeader().setReorderingAllowed(false); // Impede que o usuário mova as colunas
		scrollPane_1.setViewportView(dias_da_semana);

		this.setLocationRelativeTo(null);
	}
}
