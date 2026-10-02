import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConversorGraus extends JFrame{ 
	private JLabel lblC, lblF;
	private JTextField txtC, txtF;
	private JButton btnC, btnF;
	public ConversorGraus() {
		this.setTitle("Conversor de Temperatura");
		this.setSize(400, 150);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	
		this.setLayout(new GridLayout(3,2));
		
		lblC = new JLabel("C");
		lblF = new JLabel("F");
		txtC = new JTextField();
		txtF = new JTextField();
		btnC = new JButton("C -> F");
		btnF = new JButton("F -> C");
		
		this.add(lblC);
		this.add(lblF);
		this.add(txtC);
		this.add(txtF);
		this.add(btnC);
		this.add(btnF);
	}
	 public static void main(String[] args) {
		// SwingUtilites.involeLater(new Run)
		ConversorGraus tela = new ConversorGraus();
	 }

}
