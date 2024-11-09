package view.atirador;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.AlteracaoDAO;
import controller.AtiradorDAO;
import controller.EscalaDAO;

public class Remover extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private int removerId = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Remover dialog = new Remover();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Remover() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Remover.class.getResource("/model/images/soldado (2).png")));
		setBounds(100, 100, 586, 544);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setResizable(false);

		JLabel lblNewLabel = new JLabel("Remover");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel.setBounds(198, 0, 159, 48);
		contentPanel.add(lblNewLabel);

		JButton Remover = new JButton("Remover");
		Remover.setFont(new Font("Arial Black", Font.BOLD, 15));
		Remover.setBounds(198, 438, 178, 40);
		contentPanel.add(Remover);

		JButton btnVoltar = new JButton("");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				telaAtirador tela  = new telaAtirador();
				tela.setVisible(true);
			}
		});
		btnVoltar.setIcon(new ImageIcon(Remover.class.getResource("/model/images/desfazer.png")));
		btnVoltar.setBounds(8, 10, 35, 35);
		contentPanel.add(btnVoltar);
		btnVoltar.setVisible(true);

		JLabel texto = new JLabel("Pesquise o Atirador ou Monitor pelo ID!");
		texto.setFont(new Font("Arial Black", Font.BOLD, 19));
		texto.setBounds(65, 47, 451, 48);
		contentPanel.add(texto);

		JLabel texto2 = new JLabel("Confira os campos e Remova o Atirador ou Monitor");
		texto2.setFont(new Font("Arial Black", Font.BOLD, 14));
		texto2.setBounds(75, 94, 420, 21);
		contentPanel.add(texto2);

		JLabel lblNewLabel_1_3_2 = new JLabel("ID:");
		lblNewLabel_1_3_2.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel_1_3_2.setBounds(198, 155, 41, 21);
		contentPanel.add(lblNewLabel_1_3_2);

		JLabel lblNewLabel_1 = new JLabel("Informações do Atirador ou Monitor ");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_1.setBounds(112, 202, 324, 13);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Pesquisado!");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblNewLabel_2.setBounds(221, 225, 114, 17);
		contentPanel.add(lblNewLabel_2);
		
		SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(0, 0, 99, 1); // Valor inicial: 0, Mínimo: 0, Máximo: 99, Passo: de 1 em 1
		JSpinner idSpinner = new JSpinner(modeloSpinner);
		JSpinner.NumberEditor editorId = new JSpinner.NumberEditor(idSpinner, "00");
        idSpinner.setEditor(editorId);
		idSpinner.setFont(new Font("Arial", Font.PLAIN, 16));
		idSpinner.setBounds(242, 154, 40, 26);
		contentPanel.add(idSpinner);
		
		 // Pega o campo de texto do editor para capturar eventos de tecla
        JFormattedTextField textFieldSpinner = editorId.getTextField();

        // Adiciona um KeyListener para detectar a tecla Enter
        textFieldSpinner.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        int valor = Integer.parseInt(textFieldSpinner.getText());
                        
                        if(valor >= 0 && valor < 100) {
                        	idSpinner.setValue(valor);
                        }
                        else {
                        	textFieldSpinner.setText(idSpinner.getValue().toString());
                        	
                    		JOptionPane.showMessageDialog(null, "O ID deve estar entre 1 e 99", "ID Inválido!", JOptionPane.WARNING_MESSAGE);
                        	
                        }
                        
                    } catch (NumberFormatException ex) {
                    	textFieldSpinner.setText(idSpinner.getValue().toString());
                        JOptionPane.showMessageDialog(null, "O valor digitado não é um número ou contém espaços!", "Valor Inválido!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

		JButton Pesquisar = new JButton("Pesquisar");
		Pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int valorSpinner = (int) idSpinner.getValue();
				
				if (valorSpinner == 0) {
					limparTabela();
					JOptionPane.showMessageDialog(null, "Informe um ID antes de Pesquisar!", "Atenção!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					DefaultTableModel modelo = (DefaultTableModel) table.getModel();

					ResultSet rs = AtiradorDAO.getAtirador(valorSpinner);
					String id = "0";

					try {
						if (rs.next() == false) {
							idSpinner.setValue(0);
							limparTabela();
							
							JOptionPane.showMessageDialog(null, "O Atirador não Existe!", "Erro!!",
									JOptionPane.ERROR_MESSAGE);
						} else {
							removerId = valorSpinner;
							
							limparTabela();

							modelo = (DefaultTableModel) table.getModel();

							if (rs.getInt("id") < 10) {
								modelo.addRow(new Object[] { id + rs.getInt("id"), rs.getString("nome"),
										rs.getString("guerra"), rs.getString("cargo") });
							} else {
								modelo.addRow(new Object[] { rs.getInt("id"), rs.getString("nome"),
										rs.getString("guerra"), rs.getString("cargo") });
							}
						}
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}

				}
			}
		});

		Pesquisar.setFont(new Font("Arial Black", Font.BOLD, 12));
		Pesquisar.setBounds(329, 155, 109, 25);
		contentPanel.add(Pesquisar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 260, 552, 81);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Arial Black", Font.BOLD, 10));
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "Nome de Guerra", "Cargo" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(330);
		table.getColumnModel().getColumn(2).setPreferredWidth(124);
		table.getColumnModel().getColumn(3).setPreferredWidth(106);
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);


		Remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel modelo = (DefaultTableModel) table.getModel();

				if (removerId == 0) {
					JOptionPane.showMessageDialog(null, "Nenhum Atirador para ser Removido!", "Erro!!",
							JOptionPane.ERROR_MESSAGE);

				} else {
					
					boolean atiradorEmEscala = EscalaDAO.verificarAtiradorEmEscala(removerId);
					
					if(atiradorEmEscala) {
						AlteracaoDAO.cadastrarAlteracao("Atirador"); // Deixa registrado a necessidade de alterar escala
					}
				
					AtiradorDAO.RemoverAtirador(removerId); // Removendo Atirador

					JOptionPane.showMessageDialog(null, "Remoção Feita!", "Realizado!!",
							JOptionPane.INFORMATION_MESSAGE);
					
					// Atualizar tabela
					modelo = (DefaultTableModel) table.getModel();
					modelo.removeRow(0);
					table.setModel(modelo);
					idSpinner.setValue(0);
				}

			}
		});
		this.setLocationRelativeTo(null);
	}
	
	private void limparTabela() {
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome", "Nome de Guerra", "Cargo" }));

		table.getColumnModel().getColumn(1).setPreferredWidth(330);
		table.getColumnModel().getColumn(2).setPreferredWidth(124);
		table.getColumnModel().getColumn(3).setPreferredWidth(106);
		table.getTableHeader().setReorderingAllowed(false);
		table.setEnabled(false);

		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		table.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(1).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(2).setCellRenderer(centralizado);
		table.getColumnModel().getColumn(3).setCellRenderer(centralizado);
		table.getTableHeader().setReorderingAllowed(false);
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
	}
}
