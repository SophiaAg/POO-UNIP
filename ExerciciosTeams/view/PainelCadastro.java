package br.unip.sicc.exercicio.view.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//A classe deve herdar de JPanel.
public class PainelCadastro extends JPanel{

	private JLabel lblld;
	private JLabel lblNome;
	private JTextField textld;
	private JTextField textNome;
	private JButton btnSalvar;
	private JButton btnCancelar;

	private static PainelCadastro instance;

	public static PainelCadastro getInstance() {
		if (instance == null) {
			instance = new PainelCadastro();
		}
		return instance;
	}

	//Construtor PainelCadastro():
	//instanciar = new
	private PainelCadastro() {
		this.setLayout(new BorderLayout());
		JPanel painelCadastro = montaPainelCadastro();
		JPanel painelBotoes =  montaPainelBotoes();

		this.add(painelCadastro, BorderLayout.NORTH);
		this.add(painelBotoes, BorderLayout.SOUTH);

	}

	private JPanel montaPainelCadastro() {
		//nstanciar e retornar um JPanel com layout
		//GridLayout(2, 2) (2 linhas por 2 colunas).
		JPanel painelCadastro = new JPanel();
		GridLayout layout = new GridLayout(2, 2);
		DestacaFocoText destaca = new DestacaFocoText();
		painelCadastro.setLayout(layout);

		lblld = new JLabel("id");
		textld = new JTextField();
		textld.setEnabled(false);
		lblNome = new JLabel("Nome");
		textNome = new JTextField("Nome");
		//Configurar a largura visível do campo de texto txtNome para 10 colunas.
		textNome.setColumns(10);
		textNome.addFocusListener(destaca);

		painelCadastro.add(lblld);
		painelCadastro.add(textld);
		painelCadastro.add(lblNome);
		painelCadastro.add(textNome);

		return painelCadastro;
	}

	private JPanel montaPainelBotoes()  {
		JPanel painelBotoes = new JPanel();
		FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
		Salvar salvar = new Salvar();
		painelBotoes.setLayout(layout);

		btnSalvar = new JButton("Salvar");
		btnCancelar = new JButton("Cancelar");

		// Define a tecla 'C' como atalho (Alt + C)
		btnCancelar.setMnemonic(KeyEvent.VK_C);
		//ouvinte do botão btnSalvar utilizando o método addActionListener().
		btnSalvar.addActionListener(salvar);

		painelBotoes.add(btnSalvar);
		painelBotoes.add(btnCancelar);

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Simulando o Cancelar");
			}
		});//isso é uma classe anonima, que nn precisa criar do zero, como fizemos com
		//o btnSalvar

		return painelBotoes;
	}



	private class Salvar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Simulando Salvar");
		}
	}

}

