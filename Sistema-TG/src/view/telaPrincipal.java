package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTelaPrincipal = new JLabel("TIRO DE GUERRA");
		lblTelaPrincipal.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTelaPrincipal.setBounds(123, 12, 194, 24);
		contentPane.add(lblTelaPrincipal);
		
		JButton btnRegistroDeAtiradores = new JButton("Atiradores");
		btnRegistroDeAtiradores.setFont(new Font("Dialog", Font.BOLD, 18));
		btnRegistroDeAtiradores.setBounds(138, 84, 156, 39);
		contentPane.add(btnRegistroDeAtiradores);
		
		JButton btnEscalaDeGuarda = new JButton("Escala");
		btnEscalaDeGuarda.setFont(new Font("Dialog", Font.BOLD, 20));
		btnEscalaDeGuarda.setBounds(138, 147, 156, 34);
		contentPane.add(btnEscalaDeGuarda);
		this.setLocationRelativeTo(null);
	}
}
