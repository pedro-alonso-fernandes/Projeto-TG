import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class confirmacao extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					confirmacao frame = new confirmacao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public confirmacao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 429, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Procedimento bem sucedido!");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(112, 37, 301, 17);
		contentPane.add(lblNewLabel);
		
		JButton ok = new JButton("OK");
		ok.setFont(new Font("Dialog", Font.BOLD, 18));
		ok.setBounds(146, 159, 136, 42);
		contentPane.add(ok);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(confirmacao.class.getResource("/imagens/exclamacao.png")));
		label.setBounds(22, 37, 94, 83);
		contentPane.add(label);
		
		JLabel lblVolteParaA = new JLabel("Vá para a aba \"Figurinhas\" na");
		lblVolteParaA.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblVolteParaA.setBounds(122, 66, 270, 17);
		contentPane.add(lblVolteParaA);
		
		JLabel lblVerOResultado = new JLabel("o resultado");
		lblVerOResultado.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblVerOResultado.setBounds(197, 103, 108, 17);
		contentPane.add(lblVerOResultado);
		
		JLabel lblNaTelaDe = new JLabel("Tela de Consulta para ver");
		lblNaTelaDe.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNaTelaDe.setBounds(146, 87, 229, 15);
		contentPane.add(lblNaTelaDe);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
