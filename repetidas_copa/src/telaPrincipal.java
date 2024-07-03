import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaPrincipal extends JFrame {

	private JPanel contentPane;
	private telaCadastro Cadastro;
	private telaConsulta Consulta;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel texto_titulo = new JLabel("TELA PRINCIPAL");
		texto_titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		texto_titulo.setBounds(167, 28, 293, 39);
		contentPane.add(texto_titulo);
		
		Consulta = new telaConsulta();
		Cadastro = new telaCadastro(Consulta);
		
		JButton cadastro = new JButton("Tela de Cadastro");
		cadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//dispose();
				Cadastro.setVisible(true);
			}
		});
		cadastro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cadastro.setBounds(167, 125, 256, 55);
		contentPane.add(cadastro);
		
		JButton consulta = new JButton("Tela de Consulta");
		consulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//dispose();
				Consulta.setVisible(true);
			}
		});
		consulta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		consulta.setBounds(167, 200, 256, 55);
		contentPane.add(consulta);
		
		JButton encerrar = new JButton("Encerrar");
		encerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		encerrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		encerrar.setBounds(167, 275, 256, 55);
		contentPane.add(encerrar);
		this.setLocationRelativeTo(null);
		
		/*class Encerrar{
			public void Fechar() {
				dispose();
			}
		}*/
		
	}

}
