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
import javax.swing.table.DefaultTableModel;

import controller.EscalaDAO;
import model.Data;

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
		scrollPane.setBounds(86, 106, 666, 142);
		contentPane.add(scrollPane);

		semanaAtual = new JTable();
		scrollPane.setViewportView(semanaAtual);

		//Atualizando tabela com a data de hoje
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		Date data = Data.primeiroDiaSemana(new Date()); // Pega a data do primeiro dia da semana atual

		semanaAtual.setModel(new DefaultTableModel(new Object[][] {}, new String[] { 
				formato.format(data), formato.format(Data.addDias(data, 1)), formato.format(Data.addDias(data, 2)),
				formato.format(Data.addDias(data, 3)), formato.format(Data.addDias(data, 4)), formato.format(Data.addDias(data, 5)), 
				formato.format(Data.addDias(data, 6)) 
				}));
		semanaAtual.getColumnModel().getColumn(0).setResizable(false);
		semanaAtual.getColumnModel().getColumn(1).setResizable(false);
		semanaAtual.getColumnModel().getColumn(2).setResizable(false);
		semanaAtual.getColumnModel().getColumn(3).setResizable(false);
		semanaAtual.getColumnModel().getColumn(4).setResizable(false);
		semanaAtual.getColumnModel().getColumn(5).setResizable(false);
		semanaAtual.getColumnModel().getColumn(6).setResizable(false);
		
		semanaAtual.getTableHeader().setReorderingAllowed(false); // Impede que o usuário mova as colunas
		
		DefaultTableModel modelo = (DefaultTableModel) semanaAtual.getModel();
		
		ResultSet rs = EscalaDAO.getEscalaSemana(data);
		Date dataEscala = null;
		try {
			dataEscala = formato.parse("28/07/2024");
		} catch (ParseException e) {
			System.out.println("Erro ao salvar data: " + e.getMessage());
		}
//		try {
//			rs.next();
//			dataEscala = rs.getDate("data");
//		} catch(SQLException e) {
//			System.out.println("Erro " + e.getMessage());
//		}
		
		switch (Data.getDiaSemana(dataEscala)) {
		case "DOM":
			modelo.addRow(new Object[] { 
					"" 
					});
			break;
		case "SEG":
			
			break;
		case "TER":
			
			break;
		case "QUA":
			
			break;
		case "QUI":
			
			break;
		case "SEX":
			
			break;
		case "SAB":
			
			break;
		}
		
		semanaAtual.setModel(modelo);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(86, 88, 666, 23);
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
