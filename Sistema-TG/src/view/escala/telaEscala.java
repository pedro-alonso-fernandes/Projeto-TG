package view.escala;

import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.AtiradorDAO;
import controller.EscalaDAO;
import controller.FeriadoDAO;
import controller.FolgaDAO;
import model.Data;
import model.Escala;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.Toolkit;

public class telaEscala extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable semanaAtual;
	public static JTable proximaSemana;
	private JTable dias_da_semana;
	private JTable dias_da_semana_2;
	public static boolean aviso1 = false;
	public static boolean aviso2 = false;
	private int qtdAtiradores = AtiradorDAO.getQtdAtiradoresGeral();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaEscala frame = new telaEscala();
					frame.setVisible(true);
					if(aviso1 && aviso2) {
						JOptionPane.showMessageDialog(null, "Nenhuma escala encontrada!", "Aviso", JOptionPane.WARNING_MESSAGE);
					}
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(telaEscala.class.getResource("/model/images/calendario.png")));
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 803, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTabelaEscala = new JLabel("TABELA ESCALA");
		lblTabelaEscala.setFont(new Font("Dialog", Font.BOLD, 24));
		lblTabelaEscala.setBounds(289, 12, 215, 23);
		contentPane.add(lblTabelaEscala);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(23, 101, 740, 20);
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

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(23, 120, 740, 87);
		contentPane.add(scrollPane_2);

		semanaAtual = new JTable();
		semanaAtual.setFont(new Font("Dialog", Font.PLAIN, 12));
		scrollPane_2.setViewportView(semanaAtual);

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = Data.primeiroDiaSemana(new Date()); // Pega a data do primeiro dia da semana atual
//		Date data = Data.primeiroDiaSemana(Data.addDias(new Date(), 7));
		DefaultTableCellRenderer preta = new DefaultTableCellRenderer();
		DefaultTableCellRenderer vermelha = new DefaultTableCellRenderer();

		//Tabela semana atual
		
		String[] colunasAtual = {
				formato.format(data), formato.format(Data.addDias(data, 1)), formato.format(Data.addDias(data, 2)),
				formato.format(Data.addDias(data, 3)), formato.format(Data.addDias(data, 4)), formato.format(Data.addDias(data, 5)), 
				formato.format(Data.addDias(data, 6))
		};
		semanaAtual.setModel(new DefaultTableModel(new Object[][] {}, colunasAtual){

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
		semanaAtual.setEnabled(false);
		
		Date dataProximaSemana = Data.addDias(data, 7); // Considerando que data = primeiro dia da semana atual
		ResultSet rs = EscalaDAO.getEscala(dataProximaSemana);
		
		try {
			if(!rs.next()) { 
				ResultSet resultSet = FolgaDAO.getFolga("Preta");
				int i = 0;
				int[] folgaPreta = new int[qtdAtiradores];
				
				while(resultSet.next()) {
					folgaPreta[i] = resultSet.getInt("valor");
					i++;
				}
				
				
				resultSet = FolgaDAO.getFolga("Vermelha");
				i = 0;
				int[] folgaVermelha = new int[qtdAtiradores];
				
				while(resultSet.next()) {
					folgaVermelha[i] = resultSet.getInt("valor");
					i++;
				}
				
				Escala.gerarEscala(folgaPreta, folgaVermelha, dataProximaSemana);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao verificar escalas da semana que vem: " + e.getMessage());
		}
		
		preta.setHorizontalAlignment(SwingConstants.CENTER);
		vermelha.setHorizontalAlignment(SwingConstants.CENTER);
		
		preta.setBackground(Color.BLACK);
		preta.setForeground(Color.WHITE);
		
		vermelha.setBackground(Color.RED);
		vermelha.setForeground(Color.WHITE);
		
		for(int i = 0; i < colunasAtual.length; i++) {
			String diaSemana = null;
			try {
				diaSemana = Data.getDiaSemana(formato.parse(colunasAtual[i]));
			} catch (ParseException e) {
				System.out.println("Erro ao pegar dia da semana dentro do for: " + e.getMessage());
			}
			
			if(diaSemana.equals("DOM") || diaSemana.equals("SAB")){
				semanaAtual.getColumnModel().getColumn(i).setCellRenderer(vermelha);
			}
			else {
				semanaAtual.getColumnModel().getColumn(i).setCellRenderer(preta);
			}
		}
		
		rs = FeriadoDAO.getFeriados();
		
		try {
			
			while(rs.next()) {
				
				for(int i = 0; i < colunasAtual.length; i++) {
					String dataFormatada = formato.format(rs.getDate("data"));
					if(colunasAtual[i].equals(dataFormatada)) {
						semanaAtual.getColumnModel().getColumn(i).setCellRenderer(vermelha);
					}
				}
				
			}
		} catch (SQLException e) {
			System.out.println("Erro ao percorrer pelos feriados do BD: " + e.getMessage());
		}
		
		
		
		
		
		
//		semanaAtual.getColumnModel().getColumn(0).setCellRenderer(centralizado);
//		semanaAtual.getColumnModel().getColumn(1).setCellRenderer(centralizado);
//		semanaAtual.getColumnModel().getColumn(2).setCellRenderer(centralizado);
//		semanaAtual.getColumnModel().getColumn(3).setCellRenderer(centralizado);
//		semanaAtual.getColumnModel().getColumn(4).setCellRenderer(centralizado);
//		semanaAtual.getColumnModel().getColumn(5).setCellRenderer(centralizado);
//		semanaAtual.getColumnModel().getColumn(6).setCellRenderer(centralizado);
		
		semanaAtual.getTableHeader().setReorderingAllowed(false); // Impede que o usuário mova as colunas
		
		DefaultTableModel modelo = Escala.getModelSemanaAtual(data, colunasAtual);
		
		
		semanaAtual.setModel(modelo);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(23, 272, 740, 20);
		contentPane.add(scrollPane_3);
		
		dias_da_semana_2 = new JTable();
		dias_da_semana_2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Domingo", "Segunda", "Ter\u00E7a", "Quarta", "Quinta", "Sexta", "S\u00E1bado" }));
		dias_da_semana_2.getColumnModel().getColumn(0).setResizable(false);
		dias_da_semana_2.getColumnModel().getColumn(1).setResizable(false);
		dias_da_semana_2.getColumnModel().getColumn(2).setResizable(false);
		dias_da_semana_2.getColumnModel().getColumn(3).setResizable(false);
		dias_da_semana_2.getColumnModel().getColumn(4).setResizable(false);
		dias_da_semana_2.getColumnModel().getColumn(5).setResizable(false);
		dias_da_semana_2.getColumnModel().getColumn(6).setResizable(false);

		dias_da_semana_2.getTableHeader().setReorderingAllowed(false); // Impede que o usuário mova as colunas
		scrollPane_3.setViewportView(dias_da_semana_2);
		
		
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(23, 290, 740, 87);
		contentPane.add(scrollPane_4);
		
		
		
		//Tabela próxima semana
		data = Data.diaProximaSemana(data);
		
		proximaSemana = new JTable();
		proximaSemana.setFont(new Font("Dialog", Font.PLAIN, 12));
		scrollPane_4.setViewportView(proximaSemana);
		
		String[] colunasProxima = {
				formato.format(data), formato.format(Data.addDias(data, 1)), formato.format(Data.addDias(data, 2)),
				formato.format(Data.addDias(data, 3)), formato.format(Data.addDias(data, 4)), formato.format(Data.addDias(data, 5)), 
				formato.format(Data.addDias(data, 6))
		};
		
		proximaSemana.setModel(new DefaultTableModel(new Object[][] {}, colunasProxima){

	        private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean []{  
	            false, false, false, false, false, false, false
	        };  
	   
	        @Override  
	        public boolean isCellEditable(int rowIndex, int columnIndex) {  
	            return canEdit [columnIndex];  
	        }
	});
		proximaSemana.getColumnModel().getColumn(0).setResizable(false);
		proximaSemana.getColumnModel().getColumn(1).setResizable(false);
		proximaSemana.getColumnModel().getColumn(2).setResizable(false);
		proximaSemana.getColumnModel().getColumn(3).setResizable(false);
		proximaSemana.getColumnModel().getColumn(4).setResizable(false);
		proximaSemana.getColumnModel().getColumn(5).setResizable(false);
		proximaSemana.getColumnModel().getColumn(6).setResizable(false);
		proximaSemana.setEnabled(false);
		
		
		for(int i = 0; i < colunasProxima.length; i++) {
			String diaSemana = null;
			try {
				diaSemana = Data.getDiaSemana(formato.parse(colunasProxima[i]));
			} catch (ParseException e) {
				System.out.println("Erro ao pegar dia da semana dentro do for: " + e.getMessage());
			}
			
			if(diaSemana.equals("DOM") || diaSemana.equals("SAB")){
				proximaSemana.getColumnModel().getColumn(i).setCellRenderer(vermelha);
			}
			else {
				proximaSemana.getColumnModel().getColumn(i).setCellRenderer(preta);
			}
		}
		
		rs = FeriadoDAO.getFeriados();
		
		try {
			
			while(rs.next()) {
				
				for(int i = 0; i < colunasProxima.length; i++) {
					String dataFormatada = formato.format(rs.getDate("data"));
					if(colunasProxima[i].equals(dataFormatada)) {
						proximaSemana.getColumnModel().getColumn(i).setCellRenderer(vermelha);
					}
				}
				
			}
		} catch (SQLException e) {
			System.out.println("Erro ao percorrer pelos feriados do BD: " + e.getMessage());
		}
		
//		proximaSemana.getColumnModel().getColumn(0).setCellRenderer(centralizado);
//		proximaSemana.getColumnModel().getColumn(1).setCellRenderer(centralizado);
//		proximaSemana.getColumnModel().getColumn(2).setCellRenderer(centralizado);
//		proximaSemana.getColumnModel().getColumn(3).setCellRenderer(centralizado);
//		proximaSemana.getColumnModel().getColumn(4).setCellRenderer(centralizado);
//		proximaSemana.getColumnModel().getColumn(5).setCellRenderer(centralizado);
//		proximaSemana.getColumnModel().getColumn(6).setCellRenderer(centralizado);
		
		proximaSemana.getTableHeader().setReorderingAllowed(false); // Impede que o usuário mova as colunas
		
		DefaultTableModel modelo2 = Escala.getModelProximaSemana(data, colunasProxima);
		
		proximaSemana.setModel(modelo2);
		
		JLabel lblSemanaAtual = new JLabel("Semana Atual");
		lblSemanaAtual.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSemanaAtual.setBounds(337, 76, 119, 15);
		contentPane.add(lblSemanaAtual);
		
		JLabel lblPrxmaSemana = new JLabel("Próxima Semana");
		lblPrxmaSemana.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPrxmaSemana.setBounds(323, 247, 141, 15);
		contentPane.add(lblPrxmaSemana);

		this.setLocationRelativeTo(null);
	}
}
