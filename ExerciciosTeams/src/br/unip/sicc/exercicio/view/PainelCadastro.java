package br.unip.sicc.exercicio.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
	
	//Construtor PainelCadastro():
	//instanciar = new
  public PainelCadastro() {
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
	      painelCadastro.setLayout(layout);
	      
	      lblld = new JLabel("id");
	      textld = new JTextField();
	      textld.setEnabled(false);
	      lblNome = new JLabel("Nome");
	      textNome = new JTextField("Nome");
	      //Configurar a largura visível do campo de texto txtNome para 10 colunas.
	      textNome.setColumns(10);
	      
	      painelCadastro.add(lblld);
	      painelCadastro.add(textld);
	      painelCadastro.add(lblNome);
	      painelCadastro.add(textNome);
	      
		return painelCadastro;
   }

   private JPanel montaPainelBotoes()  {
	   JPanel painelBotoes = new JPanel();
	   FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
	   painelBotoes.setLayout(layout);
	   
	   btnSalvar = new JButton("Salvar");
	   btnCancelar = new JButton("Cancelar");
	   
	   // Define a tecla 'C' como atalho (Alt + C)
       btnCancelar.setMnemonic(KeyEvent.VK_C);
	   
	   painelBotoes.add(btnSalvar);
	   painelBotoes.add(btnCancelar);
	   
	   
	   return painelBotoes;
   }
   
}

