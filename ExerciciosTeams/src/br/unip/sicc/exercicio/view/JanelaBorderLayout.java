package br.unip.sicc.exercicio.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JanelaBorderLayout extends JFrame {
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
		
		painelCadastro = montaPainelCadastro();
		painelBusca = montaPainelBusca();
		
		this.add(painelCadastro, BorderLayout.WEST);
		this.add(painelBusca, BorderLayout.CENTER);
		
	}
	
	private JPanel montaPainelBusca() {
		JPanel painelBusca = new JPanel();
		painelBusca.setBackground(Color.WHITE);
		return painelBusca;
		
	}
	
	private JPanel montaPainelCadastro() {
		JPanel painelCadastro = new JPanel();
		painelCadastro.setBackground(Color.BLACK);
		return new PainelCadastro();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new JanelaBorderLayout().setVisible(true);
		});
	}
}
