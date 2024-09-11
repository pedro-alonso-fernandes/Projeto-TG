package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AtiradorDAO;
import model.BD;
import view.escala.telaFolga;

import java.awt.Toolkit;

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
		btnRegistroDeAtiradores.setFont(new Font("Arial Black", Font.PLAIN, 18));
		btnRegistroDeAtiradores.setBounds(138, 84, 156, 39);
		contentPane.add(btnRegistroDeAtiradores);
		
		JButton btnEscalaDeGuarda = new JButton("Escala");
		
		btnEscalaDeGuarda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				telaFolga Folga = new telaFolga();
				Folga.setVisible(true);
			}
		});
		
		btnEscalaDeGuarda.setFont(new Font("Arial Black", Font.PLAIN, 18));
		btnEscalaDeGuarda.setBounds(138, 147, 156, 34);
		contentPane.add(btnEscalaDeGuarda);
		this.setLocationRelativeTo(null);
	}
}
