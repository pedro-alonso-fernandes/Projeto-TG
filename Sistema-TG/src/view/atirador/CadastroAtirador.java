package view.atirador;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import controller.AlteracaoDAO;
import controller.AtiradorDAO;
import model.Atirador;

public class CadastroAtirador extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField Cguerra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroAtirador dialog = new CadastroAtirador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroAtirador() {
		setForeground(new Color(0, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastroAtirador.class.getResource("/model/images/soldado (2).png")));
		setBounds(100, 100, 586, 544);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setResizable(false);
		{
			JLabel lblNewLabel = new JLabel("Cadastrar");
			lblNewLabel.setBounds(207, 10, 158, 40);
			lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 28));
			contentPanel.add(lblNewLabel);
		}

		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(34, 139, 86, 21);
		contentPanel.add(lblNewLabel_1);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Atirador");
		rdbtnNewRadioButton.setFont(new Font("Arial Black", Font.PLAIN, 15));
		rdbtnNewRadioButton.setBounds(130, 299, 151, 21);
		contentPanel.add(rdbtnNewRadioButton);

		JRadioButton rdbtnMonitor = new JRadioButton("Monitor");
		rdbtnMonitor.setFont(new Font("Arial Black", Font.PLAIN, 15));
		rdbtnMonitor.setBounds(284, 299, 123, 21);
		contentPanel.add(rdbtnMonitor);

		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnMonitor.isSelected()) {
					rdbtnMonitor.setSelected(false);
				}
			}
		});

		rdbtnMonitor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (rdbtnNewRadioButton.isSelected()) {
					rdbtnNewRadioButton.setSelected(false);
				}

			}
		});

		textField = new JTextField();
		textField.setFont(new Font("Arial Black", Font.PLAIN, 12));
		textField.setBounds(117, 139, 421, 25);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1_3 = new JLabel("ID:");
		lblNewLabel_1_3.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(34, 247, 86, 21);
		contentPanel.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_3_1 = new JLabel("Cargo:");
		lblNewLabel_1_3_1.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel_1_3_1.setBounds(34, 297, 86, 25);
		contentPanel.add(lblNewLabel_1_3_1);

		JButton btnNewButton = new JButton("Cadastrar ");
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnNewButton.setBounds(207, 429, 178, 40);
		contentPanel.add(btnNewButton);

		JButton btnVoltar = new JButton("");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				telaAtirador tela  = new telaAtirador();
				tela.setVisible(true);
			}
		});
		btnVoltar.setIcon(new ImageIcon(CadastroAtirador.class.getResource("/model/images/desfazer.png")));
		btnVoltar.setBounds(8, 10, 35, 35);
		contentPanel.add(btnVoltar);
		btnVoltar.setVisible(true);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome de Guerra:");
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(34, 193, 219, 21);
		contentPanel.add(lblNewLabel_1_1);
		
		Cguerra = new JTextField();
		Cguerra.setFont(new Font("Arial Black", Font.PLAIN, 12));
		Cguerra.setColumns(10);
		Cguerra.setBounds(228, 193, 312, 25);
		contentPanel.add(Cguerra);
		
		JLabel lblPreenchaAsInformaes = new JLabel("Preencha as informações ");
		lblPreenchaAsInformaes.setFont(new Font("Arial Black", Font.BOLD, 19));
		lblPreenchaAsInformaes.setBounds(142, 45, 296, 48);
		contentPanel.add(lblPreenchaAsInformaes);
		
		JLabel lblConfiraOsCampos = new JLabel("Confira os campos e Cadastre o Atirador ou Monitor");
		lblConfiraOsCampos.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblConfiraOsCampos.setBounds(83, 89, 427, 21);
		contentPanel.add(lblConfiraOsCampos);
		
		SpinnerNumberModel modeloIdSpinner = new SpinnerNumberModel(0, 0, 99, 1); // Valor inicial: 0, Mínimo: 0, Máximo: 99, Passo: de 1 em 1
		JSpinner idSpinner = new JSpinner(modeloIdSpinner);
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(idSpinner, "00");
        idSpinner.setEditor(editor);
		idSpinner.setFont(new Font("Arial", Font.PLAIN, 16));
		idSpinner.setBounds(117, 247, 40, 26);
		contentPanel.add(idSpinner);
		
		 // Pega o campo de texto do editor para capturar eventos de tecla
        JFormattedTextField textFieldSpinner = editor.getTextField();

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

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int valorIdSpinner = (int) idSpinner.getValue();
				
				if (textField.getText().equals("") || valorIdSpinner == 0 || Cguerra.getText().equals("")
						|| (rdbtnMonitor.isSelected() == false && rdbtnNewRadioButton.isSelected() == false)) {

					JOptionPane.showMessageDialog(null, "Seu Cadastro Está Incompleto!!", "Atenção!!",
							JOptionPane.WARNING_MESSAGE);

				} else {

					ResultSet rs = AtiradorDAO.getAtirador(valorIdSpinner);	
					try {
						if(rs.next() == false) {
							
							Atirador atdr = new Atirador();
							atdr.setNome(textField.getText());
							atdr.setID(valorIdSpinner);
							atdr.setGuerra(Cguerra.getText());
							
							if(rdbtnMonitor.isSelected() == true) {
								atdr.setCargo(rdbtnMonitor.getText());
							}
							
							else if (rdbtnNewRadioButton.isSelected() == true) {
								atdr.setCargo(rdbtnNewRadioButton.getText());
							}
							
							AtiradorDAO.cadastrarAtirador(atdr); // Cadastra o atirador no BD
							
							AlteracaoDAO.cadastrarAlteracao("Atirador");	// Deixa registrado a necessidade de alterar escala
							
							JOptionPane.showMessageDialog(null, "Cadastro Feito" , "Realizado!" , JOptionPane.INFORMATION_MESSAGE);
						
							// Reseta os campos
							textField.setText("");
							idSpinner.setValue(0);
							Cguerra.setText("");
							if(rdbtnMonitor.isSelected() == true) {
								rdbtnMonitor.setSelected(false);
							}
							else if (rdbtnNewRadioButton.isSelected() == true){
								rdbtnNewRadioButton.setSelected(false);
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Esse ID já Existe!", "Erro!",
									JOptionPane.ERROR_MESSAGE);
							idSpinner.setValue(0);
						}
					} catch (SQLException e1) {
						System.out.println("Erro ao buscar Atirador na tela CadastroAtirador: " + e1.getMessage());
					}
					
					
				}
			}
		});

		this.setLocationRelativeTo(null);
	}
}
