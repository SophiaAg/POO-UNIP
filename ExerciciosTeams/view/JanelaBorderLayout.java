package br.unip.sicc.exercicio.view.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class JanelaBorderLayout extends JFrame implements WindowListener {
	private JPanel painelCadastro;
	private JPanel painelBusca;

	private JanelaBorderLayout() {
		//Definir o título da janela
		this.setTitle("Controle de Cartas");
		//Configurar a dimensão
		this.setSize(800, 300);
		// encerramento do processo ao fechar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//centralizar a janela
		this.setLocationRelativeTo(null);
		//ouvinte de seus eventos de janela
		this.addWindowListener(this);

		painelCadastro = montaPainelCadastro();
		painelBusca = montaPainelBusca();

		this.add(painelCadastro, BorderLayout.WEST);
		this.add(painelBusca, BorderLayout.CENTER);

	}

	private JPanel montaPainelBusca() {
		JPanel painelBusca = new PainelBusca();
		painelBusca.setBackground(Color.RED);
		return painelBusca;
	}

	private JPanel montaPainelCadastro() {
		JPanel painelCadastro = new PainelCadastro();
		painelCadastro.setBackground(Color.BLACK);
		return painelCadastro;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new JanelaBorderLayout().setVisible(true);
		});
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("MINIMIZOU");

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
