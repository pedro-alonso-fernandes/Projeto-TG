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
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import controller.AtiradorDAO;
import model.Atirador;

public class Editar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private int idAntigo = 0;
	private JTextField txtNome;
	private JTextField txtGuerra;
	private JSpinner idNovoSpinner;
	private JRadioButton rdbtnMonitor;
	private JRadioButton rdbtnAtirador;
	private JLabel lblTitulo1;
	private JLabel lblTitulo2;
	private JLabel lblNome;
	private JLabel lblGuerra;
	private JLabel lblID;
	private JLabel lblCargo;
	private JLabel lblConfiraOsCampos;
	private JButton btnEditar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Editar dialog = new Editar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Editar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Editar.class.getResource("/model/images/soldado (2).png")));
		setBounds(100, 100, 586, 544);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblEditar = new JLabel("Editar");
		lblEditar.setFont(new Font("Arial Black", Font.BOLD, 28));
		lblEditar.setBounds(232, 10, 158, 40);
		contentPanel.add(lblEditar);

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNome.setBounds(37, 239, 86, 21);
		lblNome.setVisible(false);
		contentPanel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Arial Black", Font.PLAIN, 12));
		txtNome.setColumns(10);
		txtNome.setBounds(122, 239, 406, 25);
		txtNome.setVisible(false);
		contentPanel.add(txtNome);

		lblID = new JLabel("ID:");
		lblID.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblID.setBounds(37, 327, 46, 21);
		lblID.setVisible(false);
		contentPanel.add(lblID);

		lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblCargo.setBounds(37, 369, 86, 25);
		lblCargo.setVisible(false);
		contentPanel.add(lblCargo);

		rdbtnAtirador = new JRadioButton("Atirador");
		rdbtnAtirador.setFont(new Font("Arial Black", Font.PLAIN, 15));
		rdbtnAtirador.setBounds(133, 371, 109, 21);
		rdbtnAtirador.setVisible(false);
		contentPanel.add(rdbtnAtirador);

		rdbtnMonitor = new JRadioButton("Monitor");
		rdbtnMonitor.setFont(new Font("Arial Black", Font.PLAIN, 15));
		rdbtnMonitor.setBounds(244, 372, 123, 21);
		rdbtnMonitor.setVisible(false);
		contentPanel.add(rdbtnMonitor);

		rdbtnAtirador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnMonitor.isSelected()) {
					rdbtnMonitor.setSelected(false);
				}
			}
		});

		rdbtnMonitor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (rdbtnAtirador.isSelected()) {
					rdbtnAtirador.setSelected(false);
				}

			}
		});

		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnEditar.setBounds(200, 427, 178, 40);
		btnEditar.setVisible(false);
		contentPanel.add(btnEditar);

		JButton btnVoltar = new JButton("");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				telaAtirador tela  = new telaAtirador();
				tela.setVisible(true);
			}
		});
		btnVoltar.setIcon(new ImageIcon(Editar.class.getResource("/model/images/desfazer.png")));
		btnVoltar.setBounds(8, 10, 35, 35);
		contentPanel.add(btnVoltar);
		btnVoltar.setVisible(true);

		JLabel lblNewLabel_1_3_2 = new JLabel("ID:");
		lblNewLabel_1_3_2.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel_1_3_2.setBounds(232, 143, 41, 21);
		contentPanel.add(lblNewLabel_1_3_2);

		SpinnerNumberModel modeloIdAntigoSpinner = new SpinnerNumberModel(0, 0, 99, 1); // Valor inicial: 0, Mínimo: 0, Máximo: 99, Passo: de 1 em 1
		JSpinner idAntigoSpinner = new JSpinner(modeloIdAntigoSpinner);
		JSpinner.NumberEditor editorIdAntigo = new JSpinner.NumberEditor(idAntigoSpinner, "00");
        idAntigoSpinner.setEditor(editorIdAntigo);
		idAntigoSpinner.setFont(new Font("Arial", Font.PLAIN, 16));
		idAntigoSpinner.setBounds(278, 143, 40, 26);
		contentPanel.add(idAntigoSpinner);
		
		 // Pega o campo de texto do editor para capturar eventos de tecla
        JFormattedTextField textFieldIdAntigo = editorIdAntigo.getTextField();

        // Adiciona um KeyListener para detectar a tecla Enter
        textFieldIdAntigo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        int valor = Integer.parseInt(textFieldIdAntigo.getText());
                        
                        if(valor >= 0 && valor < 100) {
                        	idAntigoSpinner.setValue(valor);
                        }
                        else {
                        	textFieldIdAntigo.setText(idAntigoSpinner.getValue().toString());
                        	
                    		JOptionPane.showMessageDialog(null, "O ID deve estar entre 1 e 99", "ID Inválido!", JOptionPane.WARNING_MESSAGE);
                        	
                        }
                        
                    } catch (NumberFormatException ex) {
                    	textFieldIdAntigo.setText(idAntigoSpinner.getValue().toString());
                        JOptionPane.showMessageDialog(null, "O valor digitado não é um número ou contém espaços!", "Valor Inválido!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        
        SpinnerNumberModel modeloIdNovoSpinner = new SpinnerNumberModel(0, 0, 99, 1); // Valor inicial: 0, Mínimo: 0, Máximo: 99, Passo: de 1 em 1
		idNovoSpinner = new JSpinner(modeloIdNovoSpinner);
		JSpinner.NumberEditor editorIdNovo = new JSpinner.NumberEditor(idNovoSpinner, "00");
        idNovoSpinner.setEditor(editorIdNovo);
		idNovoSpinner.setFont(new Font("Arial", Font.PLAIN, 16));
		idNovoSpinner.setBounds(83, 326, 40, 26);
		idNovoSpinner.setVisible(false);
		contentPanel.add(idNovoSpinner);
        
		 // Pega o campo de texto do editor para capturar eventos de tecla
        JFormattedTextField textFieldIdNovo = editorIdNovo.getTextField();

        // Adiciona um KeyListener para detectar a tecla Enter
        textFieldIdNovo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        int valor = Integer.parseInt(textFieldIdNovo.getText());
                        
                        if(valor >= 0 && valor < 100) {
                        	idNovoSpinner.setValue(valor);
                        }
                        else {
                        	textFieldIdNovo.setText(idNovoSpinner.getValue().toString());
                        	
                    		JOptionPane.showMessageDialog(null, "O ID deve estar entre 1 e 99", "ID Inválido!", JOptionPane.WARNING_MESSAGE);
                        	
                        }
                        
                    } catch (NumberFormatException ex) {
                    	textFieldIdNovo.setText(idNovoSpinner.getValue().toString());
                        JOptionPane.showMessageDialog(null, "O valor digitado não é um número ou contém espaços!", "Valor Inválido!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
		
        
        
		JButton Pesquisar = new JButton("Pesquisar");
		Pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int valorIdAntigo = (int) idAntigoSpinner.getValue();
				
				if (valorIdAntigo == 0) {
					setVisibilidadeCampos(false);
					idAntigo = 0;
					JOptionPane.showMessageDialog(null, "Informe um ID antes de Pesquisar!", "Atenção!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					ResultSet rs = AtiradorDAO.getAtirador(valorIdAntigo);

					try {
						if (rs.next() == false) {
							setVisibilidadeCampos(false);
							idAntigo = 0;
							JOptionPane.showMessageDialog(null, "Esse ID não Existe!", "Erro!!",
									JOptionPane.ERROR_MESSAGE);
						} else {
							setVisibilidadeCampos(true);
							idAntigo = valorIdAntigo;
							
							txtNome.setText(rs.getString("nome"));
							idNovoSpinner.setValue(rs.getInt("id"));
							txtGuerra.setText(rs.getString("guerra"));

							if (rs.getString("cargo").equalsIgnoreCase("Atirador")) {
								rdbtnAtirador.setSelected(true);
								rdbtnMonitor.setSelected(false);
							} else if (rs.getString("cargo").equalsIgnoreCase("Monitor")) {
								rdbtnMonitor.setSelected(true);
								rdbtnAtirador.setSelected(false);
							}

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		Pesquisar.setFont(new Font("Arial Black", Font.BOLD, 12));
		Pesquisar.setBounds(353, 144, 109, 25);
		contentPanel.add(Pesquisar);

		JLabel texto = new JLabel("Pesquise o Atirador ou Monitor pelo ID!");
		texto.setFont(new Font("Arial Black", Font.BOLD, 18));
		texto.setBounds(76, 54, 420, 48);
		contentPanel.add(texto);

		lblConfiraOsCampos = new JLabel("Confira os campos e Edite o Atirador ou Monitor");
		lblConfiraOsCampos.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblConfiraOsCampos.setBounds(86, 97, 395, 21);
		lblConfiraOsCampos.setVisible(false);
		contentPanel.add(lblConfiraOsCampos);

		lblTitulo1 = new JLabel("Informações do Atirador ou Monitor ");
		lblTitulo1.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblTitulo1.setBounds(166, 190, 324, 13);
		lblTitulo1.setVisible(false);
		contentPanel.add(lblTitulo1);

		lblTitulo2 = new JLabel("Pesquisado!");
		lblTitulo2.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblTitulo2.setBounds(251, 208, 114, 17);
		lblTitulo2.setVisible(false);
		contentPanel.add(lblTitulo2);

		lblGuerra = new JLabel("Nome de Guerra:");
		lblGuerra.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblGuerra.setBounds(37, 281, 219, 21);
		lblGuerra.setVisible(false);
		contentPanel.add(lblGuerra);

		txtGuerra = new JTextField();
		txtGuerra.setFont(new Font("Arial Black", Font.PLAIN, 12));
		txtGuerra.setColumns(10);
		txtGuerra.setBounds(218, 281, 310, 25);
		txtGuerra.setVisible(false);
		contentPanel.add(txtGuerra);
		

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int valorIdAntigo = idAntigo;
				int valorIdNovo = (int) idNovoSpinner.getValue();
				
				if (txtNome.getText().equals("") || valorIdNovo == 0 || txtGuerra.getText().equals("")
						|| (rdbtnMonitor.isSelected() == false && rdbtnAtirador.isSelected() == false)) {

					JOptionPane.showMessageDialog(null, "Preencha todos os campos antes de editar!", "Campos Incompletos!",
							JOptionPane.WARNING_MESSAGE);

				} else {
					
					ResultSet rs = AtiradorDAO.getAtirador(valorIdNovo);
					Atirador atdr = new Atirador();
				
					try {
						boolean comp = rs.next();
						// Primeira comparação: Se não existe atirador com o Id novo, e o Id antigo e novo são diferentes
						// Segunda comparação: Se existe atirador com o Id novo, e o Id antigo e novo são iguais
						if ((comp == false && valorIdAntigo != valorIdNovo) || (comp == true && valorIdAntigo == valorIdNovo)) {
							atdr.setNome(txtNome.getText());
							atdr.setID(valorIdNovo);
							atdr.setGuerra(txtGuerra.getText());

							if (rdbtnMonitor.isSelected() == true) {
								atdr.setCargo(rdbtnMonitor.getText());
							}

							else if (rdbtnAtirador.isSelected() == true) {
								atdr.setCargo(rdbtnAtirador.getText());
							}

							AtiradorDAO.EditarAtirador(atdr, valorIdNovo); // Edita Atirador
							
							JOptionPane.showMessageDialog(null, "Edição Feita!", "Completa!!",
									JOptionPane.INFORMATION_MESSAGE);
							
							setVisibilidadeCampos(false);
							idAntigoSpinner.setValue(0);
							txtNome.setText("");
							idNovoSpinner.setValue(0);
							txtGuerra.setText("");
							if (rdbtnMonitor.isSelected() == true) {
								rdbtnMonitor.setSelected(false);
							} else if (rdbtnAtirador.isSelected() == true) {
								rdbtnAtirador.setSelected(false);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Esse ID já Existe", "Erro!!",
									JOptionPane.ERROR_MESSAGE);
							idNovoSpinner.setValue(0);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

		this.setLocationRelativeTo(null);
	}
	
	private void setVisibilidadeCampos(boolean booleano) {
		txtNome.setVisible(booleano);
		txtGuerra.setVisible(booleano);
		idNovoSpinner.setVisible(booleano);
		rdbtnMonitor.setVisible(booleano);
		rdbtnAtirador.setVisible(booleano);
		lblTitulo1.setVisible(booleano);
		lblTitulo2.setVisible(booleano);
		lblConfiraOsCampos.setVisible(booleano);
		lblNome.setVisible(booleano);
		lblGuerra.setVisible(booleano);
		lblID.setVisible(booleano);
		lblCargo.setVisible(booleano);
		btnEditar.setVisible(booleano);
	}
}
