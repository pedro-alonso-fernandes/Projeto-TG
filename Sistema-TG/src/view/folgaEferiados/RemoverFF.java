package view.folgaEferiados;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.AlteracaoDAO;
import controller.EscalaDAO;
import controller.FeriadoDAO;
import controller.FolgaDAO;
import model.Data;

public class RemoverFF extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel();
	private JTable table_1;
	private String tipo = "";
	private int removerID = 0;
	private boolean registroEscala = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RemoverFF dialog = new RemoverFF();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RemoverFF() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RemoverFF.class.getResource("/model/images/calendario.png")));
		setBounds(100, 100, 586, 544);

		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblRemoverFeriadoE = new JLabel("Remover Feriado e Folga");
		lblRemoverFeriadoE.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblRemoverFeriadoE.setBounds(141, 10, 316, 40);
		contentPanel.add(lblRemoverFeriadoE);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 186, 552, 179);
		contentPanel.add(scrollPane);

		table_1 = new JTable();
		table_1.setFont(new Font("Arial Black", Font.BOLD, 10));
		table_1.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Data", "Tipo", "Feriado/Folga" }));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(301);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(112);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(112);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(109);
		scrollPane.setViewportView(table_1);
		table_1.getTableHeader().setReorderingAllowed(false);
		table_1.setEnabled(false);
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(1).setResizable(false);
		table_1.getColumnModel().getColumn(2).setResizable(false);
		table_1.getColumnModel().getColumn(3).setResizable(false);

		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		table_1.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		table_1.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table_1.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table_1.getColumnModel().getColumn(3).setCellRenderer(centralizado);

		JButton btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnRemover.setBounds(204, 429, 140, 32);
		contentPanel.add(btnRemover);
		
		JButton btnVoltar = new JButton("");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Feriados_e_Folgas FF  = new Feriados_e_Folgas();
				FF.setVisible(true);
			}
		});
		btnVoltar.setIcon(new ImageIcon(RemoverFF.class.getResource("/model/images/desfazer.png")));
		btnVoltar.setBounds(8, 10, 35, 35);
		contentPanel.add(btnVoltar);
		btnVoltar.setVisible(true);

		JLabel lblPesquiseAFolga = new JLabel("Pesquise a Folga ou Feriado pela Data!");
		lblPesquiseAFolga.setFont(new Font("Arial Black", Font.BOLD, 19));
		lblPesquiseAFolga.setBounds(60, 46, 439, 48);
		contentPanel.add(lblPesquiseAFolga);

		JLabel lblConfiraOsCampos = new JLabel("Confira os campos e Remova!");
		lblConfiraOsCampos.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblConfiraOsCampos.setBounds(152, 86, 250, 21);
		contentPanel.add(lblConfiraOsCampos);

		JLabel lblNewLabel_2 = new JLabel("Data da Folga ou Feriado:");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 143, 233, 16);
		contentPanel.add(lblNewLabel_2);

		Date dataInicial = new Date();

		SpinnerDateModel dateModel = new SpinnerDateModel(dataInicial, null, null, Calendar.DAY_OF_MONTH);
		JSpinner dataSpinner = new JSpinner(dateModel);

		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dataSpinner, "dd/MM/yyyy");
		dataSpinner.setEditor(dateEditor);

		dataSpinner.setFont(new Font("Arial Black", Font.BOLD, 15));
		dataSpinner.setBounds(253, 134, 120, 42);
		contentPanel.add(dataSpinner);
		

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int anoModelo = Calendar.getInstance().get(Calendar.YEAR);

				SimpleDateFormat formato = new SimpleDateFormat("yyyy");
				SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy");
				Date data = (Date) dataSpinner.getValue();
				int ano = Integer.parseInt(formato.format(data));

				table_1.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "Nome", "Data", "Tipo", "Feriado/Folga" }));
				table_1.getColumnModel().getColumn(0).setPreferredWidth(301);
				table_1.getColumnModel().getColumn(1).setPreferredWidth(112);
				table_1.getColumnModel().getColumn(2).setPreferredWidth(112);
				table_1.getColumnModel().getColumn(3).setPreferredWidth(109);
				table_1.getTableHeader().setReorderingAllowed(false);
				table_1.setEnabled(false);
				table_1.getColumnModel().getColumn(0).setResizable(false);
				table_1.getColumnModel().getColumn(1).setResizable(false);
				table_1.getColumnModel().getColumn(2).setResizable(false);
				table_1.getColumnModel().getColumn(3).setResizable(false);
				table_1.getColumnModel().getColumn(0).setCellRenderer(centralizado);
				table_1.getColumnModel().getColumn(1).setCellRenderer(centralizado);
				table_1.getColumnModel().getColumn(2).setCellRenderer(centralizado);
				table_1.getColumnModel().getColumn(3).setCellRenderer(centralizado);

				DefaultTableModel modelo = (DefaultTableModel) table_1.getModel();

				if (ano < anoModelo) {
					JOptionPane.showMessageDialog(null,
							"Não é possível Pesquisar datas inferiores a 01/01/" + anoModelo + "!", "Atenção!!",
							JOptionPane.WARNING_MESSAGE);
				} else {

					ResultSet rsFeriado = FeriadoDAO.getFeriadoData(data);
					ResultSet rsFolga = FolgaDAO.getFolgaData(data);

					try {
						boolean comp1 = rsFeriado.next();
						boolean comp2 = rsFolga.next();
						if ((comp1 || comp2) == false) {
							JOptionPane.showMessageDialog(null, "Não Existe Folga ou Feriado nessa data!", "Atenção!",
									JOptionPane.WARNING_MESSAGE);
							removerID = 0;
							tipo = "";
							registroEscala = false;
							
						} else if (comp1 == true) {

							modelo.addRow(new Object[] { rsFeriado.getString("nome"), formato1.format(rsFeriado.getDate("data")),
									rsFeriado.getString("tipo"), "Feriado" });

							removerID = rsFeriado.getInt("id");
							tipo = "Feriado";
							registroEscala = false;
							
						} else if (comp2 == true) {
							modelo.addRow(new Object[] { rsFolga.getString("nome"), formato1.format(rsFolga.getDate("data")), "X",
									"Folga" });

							removerID = rsFolga.getInt("id");
							tipo = "Folga";
							registroEscala = rsFolga.getInt("escala") == 1 ? true : false;	// Se o valor do campo "escala" for 1, escala = true; se não (igual a 0) escala = false

						}
					} catch (Exception e2) {
						// TODO: handle exception
					}

				}
			}
		});
		btnPesquisar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnPesquisar.setBounds(396, 137, 128, 28);
		contentPanel.add(btnPesquisar);

		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Date data = (Date) dataSpinner.getValue();
				
				if ((removerID == 0)) {
					JOptionPane.showMessageDialog(null,
							"Nenhum Feriado ou Folga selecionado! Por favor selecione uma data.", "Atenção!",
							JOptionPane.WARNING_MESSAGE);
				} else if (tipo.equals("Feriado")) {
					FeriadoDAO.removerFeriado(removerID);
					
					if(data.after(new Date())) {
						boolean existenciaFeriado = EscalaDAO.verificarEscalaEmData(data);
						
						String diaSemana = Data.getDiaSemana(data);
						
						if(existenciaFeriado && (!diaSemana.equals("DOM") && !diaSemana.equals("SAB"))) {
							AlteracaoDAO.cadastrarAlteracao("Feriado");
						}
					}
					
					JOptionPane.showMessageDialog(null, "O Feriado foi removido!", "Informação!",
							JOptionPane.INFORMATION_MESSAGE);
					table_1.setModel(new DefaultTableModel(new Object[][] {},
							new String[] { "Nome", "Data", "Tipo", "Feriado/Folga" }));
					table_1.getColumnModel().getColumn(0).setPreferredWidth(301);
					table_1.getColumnModel().getColumn(1).setPreferredWidth(112);
					table_1.getColumnModel().getColumn(2).setPreferredWidth(112);
					table_1.getColumnModel().getColumn(3).setPreferredWidth(109);
					table_1.getTableHeader().setReorderingAllowed(false);
					table_1.setEnabled(false);
					table_1.getColumnModel().getColumn(0).setResizable(false);
					table_1.getColumnModel().getColumn(1).setResizable(false);
					table_1.getColumnModel().getColumn(2).setResizable(false);
					table_1.getColumnModel().getColumn(3).setResizable(false);

				} else if (tipo.equals("Folga")) {
					FolgaDAO.removerFolga(removerID);
					
					if(data.after(new Date())) {
						
						if(registroEscala) {
							AlteracaoDAO.cadastrarAlteracao("Folga");
						}
						
					}
					
					JOptionPane.showMessageDialog(null, "A Folga foi removida!", "Informação!",
							JOptionPane.INFORMATION_MESSAGE);
					table_1.setModel(new DefaultTableModel(new Object[][] {},
							new String[] { "Nome", "Data", "Tipo", "Feriado/Folga" }));
					table_1.getColumnModel().getColumn(0).setPreferredWidth(301);
					table_1.getColumnModel().getColumn(1).setPreferredWidth(112);
					table_1.getColumnModel().getColumn(2).setPreferredWidth(112);
					table_1.getColumnModel().getColumn(3).setPreferredWidth(109);
					table_1.getTableHeader().setReorderingAllowed(false);
					table_1.setEnabled(false);
					table_1.getColumnModel().getColumn(0).setResizable(false);
					table_1.getColumnModel().getColumn(1).setResizable(false);
					table_1.getColumnModel().getColumn(2).setResizable(false);
					table_1.getColumnModel().getColumn(3).setResizable(false);
				}
			}
		});
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
