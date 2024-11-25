package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AlteracaoDAO;
import controller.AtiradorDAO;
import controller.EscalaDAO;
import model.BD;
import view.atirador.telaAtirador;
import view.escala.telaEscala;
import view.escala.telaGerarEscala;
import view.folgaEferiados.Feriados_e_Folgas;
import javax.swing.ImageIcon;

public class telaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaPrincipal frame = new telaPrincipal();
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
	public telaPrincipal() {
		
		BD.criarBanco();
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(telaPrincipal.class.getResource("/model/images/soldado (1).png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTelaPrincipal = new JLabel("TIRO DE GUERRA");
		lblTelaPrincipal.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblTelaPrincipal.setBounds(115, 10, 210, 24);
		contentPane.add(lblTelaPrincipal);

		JButton btnRegistroDeAtiradores = new JButton("Atiradores");
		btnRegistroDeAtiradores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				telaAtirador Atirador = new telaAtirador();
				Atirador.setVisible(true);
			}
		});
		btnRegistroDeAtiradores.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnRegistroDeAtiradores.setBounds(122, 72, 187, 39);
		contentPane.add(btnRegistroDeAtiradores);

		JButton btnEscalaDeGuarda = new JButton("Escala");

		btnEscalaDeGuarda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				boolean existenciaEscala = EscalaDAO.verificarExistenciaEscala();

				ResultSet rsAlteracao = AlteracaoDAO.getAlteracao();
				boolean alteracao = false;
				try {
					alteracao = rsAlteracao.next();
				} catch (SQLException e1) {
					System.out.println("Erro ao percorrer tabela Alteracao: " + e1.getMessage());
				}

				// Se for necessário alterar a escala
				if (alteracao && existenciaEscala) {

					String[] opcoes = { "Ir para Gerar Escala" };

					String tipoAlteracao = "";
					try {
						tipoAlteracao = rsAlteracao.getString("tipo").equals("Atirador") ? "Atiradores"
								: "Feriados e Folgas";
					} catch (SQLException e1) {
						System.out.println("Erro ao pegar tipo de Alteracao: " + e1.getMessage());
					}

					String texto = "Você fez alterações no registro de " + tipoAlteracao
							+ ", portanto é necessário gerar a escala novamente!";

					JOptionPane optionPane = new JOptionPane(texto, JOptionPane.INFORMATION_MESSAGE,
							JOptionPane.DEFAULT_OPTION, null, opcoes, opcoes[0]);

					JDialog tela = optionPane.createDialog("Gerar a Escala Novamente!");
					tela.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Impede que o usuário feche a janela
					tela.setIconImage(Toolkit.getDefaultToolkit()
							.getImage(telaPrincipal.class.getResource("/model/images/calendario.png")));
					tela.setVisible(true);

					dispose();

					telaGerarEscala frameGerarEscala = new telaGerarEscala();
					frameGerarEscala.setVisible(true);
				
				}
				else {
					telaEscala frame = new telaEscala();
					
					// Se não tiver escala
					if (telaEscala.aviso1 && telaEscala.aviso2) {
						int qtdAtiradores = AtiradorDAO.getQtdAtiradores();
						int qtdMonitores = AtiradorDAO.getQtdMonitores();
						
						// Se tiver atiradores cadastrados no BD
						if(qtdAtiradores > 2 && qtdMonitores > 0) {
						
							String[] opcoes = { "Ir para Gerar Escala" };
	
							String texto = "Não há nenhuma Escala registrada! É necessário gerar uma escala primeiro!";
	
							JOptionPane optionPane = new JOptionPane(texto, JOptionPane.INFORMATION_MESSAGE,
									JOptionPane.DEFAULT_OPTION, null, opcoes, opcoes[0]);
	
							JDialog tela = optionPane.createDialog("Nenhuma Escala encontrada!");
							tela.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Impede que o usuário feche a janela
							tela.setIconImage(Toolkit.getDefaultToolkit()
									.getImage(telaPrincipal.class.getResource("/model/images/calendario.png")));
							tela.setVisible(true);
	
							dispose();
							frame.dispose();
	
							telaGerarEscala frame1 = new telaGerarEscala();
							frame1.setVisible(true);
	
							telaEscala.aviso1 = false;
							telaEscala.aviso2 = false;
						
						}
						else {
							
							String[] opcoes = { "Ok" };
							
							String texto = "<html><div style='text-align: center;'>Não há atiradores suficientes! É necessário cadastrar pelo menos<br>3 atiradores e 1 monitor antes de gerar uma escala!</div></html>";
	
							JOptionPane optionPane = new JOptionPane(texto, JOptionPane.WARNING_MESSAGE,
									JOptionPane.DEFAULT_OPTION, null, opcoes, opcoes[0]);
	
							JDialog tela = optionPane.createDialog("Atiradores Insuficientes!");
							tela.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Impede que o usuário feche a janela
							tela.setIconImage(Toolkit.getDefaultToolkit()
									.getImage(telaPrincipal.class.getResource("/model/images/calendario.png")));
							tela.setVisible(true);
	
							dispose();
							frame.dispose();
							
							telaPrincipal framePrincipal = new telaPrincipal();
							framePrincipal.setVisible(true);
							
						}


					} 
					else {

						telaEscala frameEscala = new telaEscala();
						frameEscala.setVisible(true);
						
					}
				}
			}		
		});

		btnEscalaDeGuarda.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnEscalaDeGuarda.setBounds(122, 134, 187, 34);
		contentPane.add(btnEscalaDeGuarda);

		JButton btnFeriadosEFolgas = new JButton("Feriados e Folgas");
		btnFeriadosEFolgas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Feriados_e_Folgas FF = new Feriados_e_Folgas();
				FF.setVisible(true);
			}
		});
		btnFeriadosEFolgas.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnFeriadosEFolgas.setBounds(122, 188, 187, 39);
		contentPane.add(btnFeriadosEFolgas);
		
		JButton Apagartudo = new JButton("");
		Apagartudo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        // Define as mensagens dos botões
		        Object[] opcoes = {"Sim", "Não"};
		        
		        // Cria o diálogo de confirmação com os botões customizadosx'
		        int escolha = JOptionPane.showOptionDialog(
		            null,
		            "Isso apagará todos os dados salvos no programa. Deseja APAGAR TODOS os dados?",    // Mensagem do diálogo
		            "Atenção!",               		// Título do diálogo
		            JOptionPane.YES_NO_OPTION,   // Tipo de opções (Sim/Não)
		            JOptionPane.WARNING_MESSAGE, // Ícone do diálogo
		            null,                        // Ícone personalizado (null para o padrão)
		            opcoes,                     // Botões customizados
		            opcoes[0]                   // Botão padrão (selecionado inicialmente)
        		);
		        
		        if (escolha == JOptionPane.YES_OPTION) {
		        	
			        // Cria o diálogo de confirmação com os botões customizados
			        escolha = JOptionPane.showOptionDialog(
			            null,
			            "Realmente deseja APAGAR TODOS os dados salvos?",    
			            "Atenção!",               							
			            JOptionPane.YES_NO_OPTION,   
			            JOptionPane.WARNING_MESSAGE, 
			            null,                        
			            opcoes,                     
			            opcoes[0]                   
	        		);
			        
			        if(escolha == JOptionPane.YES_OPTION) {
			        	BD.apagarDatabase();
			        	BD.criarBanco();
			        	
			        	JOptionPane.showMessageDialog(null, "Banco de Dados apagado com sucesso!", "Exclusão Concluída!", JOptionPane.INFORMATION_MESSAGE);
			        	
			        }
		        	
		        }
		        // Caso contrário, não faz nada
		    }
		});
		Apagartudo.setIcon(new ImageIcon(telaPrincipal.class.getResource("/model/images/bin (1).png")));
		Apagartudo.setBounds(385, 188, 41, 40);
		contentPane.add(Apagartudo);

		this.setLocationRelativeTo(null);
	}
}
