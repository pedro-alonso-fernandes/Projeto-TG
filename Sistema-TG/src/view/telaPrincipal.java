package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.BD;
import view.atirador.telaAtirador;
import view.escala.telaEscala;
import view.escala.telaGerarEscala;
import view.folgaEferiados.Feriados_e_Folgas;

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
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(telaPrincipal.class.getResource("/model/images/soldado (1).png")));
		BD.criarBanco();
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				telaEscala frame = new telaEscala();
				frame.setVisible(true);
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
		
		this.setLocationRelativeTo(null);
	}
}
