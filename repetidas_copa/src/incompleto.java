import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class incompleto extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					incompleto frame = new incompleto();
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
	public incompleto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 254);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblErroVocDeixou = new JLabel("Erro! Você deixou campos vazios!");
		lblErroVocDeixou.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblErroVocDeixou.setBounds(92, 45, 306, 27);
		contentPane.add(lblErroVocDeixou);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(incompleto.class.getResource("/imagens/erro.png")));
		lblNewLabel_1.setBounds(26, 45, 66, 60);
		contentPane.add(lblNewLabel_1);
		
		JButton ok = new JButton("OK");
		ok.setFont(new Font("Dialog", Font.PLAIN, 20));
		ok.setBounds(156, 156, 121, 36);
		contentPane.add(ok);
		
		JLabel lblNewLabel = new JLabel("Preencha todos os campos!");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel.setBounds(126, 73, 249, 27);
		contentPane.add(lblNewLabel);
		
		this.setLocationRelativeTo(null);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
}
