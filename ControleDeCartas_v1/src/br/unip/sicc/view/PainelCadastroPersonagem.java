package br.unip.sicc.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import br.unip.sicc.dao.DadosException;
import br.unip.sicc.model.Classe;
import br.unip.sicc.model.GerenciadorDePersonagens;
import br.unip.sicc.model.Personagem;

public class PainelCadastroPersonagem extends JPanel{
	
	private JLabel lblId, lblNome, lblClasse, lblNomeImagem, lblVida;
	private JTextField txtId, txtNome, txtNomeImagem;
	private JComboBox cboClasse;
	private JSpinner spiVida;
	private JButton btnSalvar, btnCancelar;
	
	private GerenciadorDePersonagens gerenciador;
	
	private Personagem personagem;
	
    //implementação do Singleton
    private static PainelCadastroPersonagem instance;

    private PainelCadastroPersonagem() {
        gerenciador = GerenciadorDePersonagens.getInstance();
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        JPanel painelCadastro = montaPainelCadastro();
        JPanel painelBotoes = montaPainelBotoes();
        this.add(painelCadastro, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.SOUTH);
    }

    public static PainelCadastroPersonagem getInstance() {
        if (instance == null) {
            instance = new PainelCadastroPersonagem();
        }
        return instance;
    }
    //implementacao do Singleton
    

	private JPanel montaPainelCadastro() {
        JPanel painelCadastro = new JPanel();
        GridLayout layout = new GridLayout(6, 2);
        painelCadastro.setLayout(layout);
        
        lblId = new JLabel("Id");    
        txtId = new JTextField();
        txtId.setEnabled(false);
        lblNome = new JLabel("Nome");
        txtNome = new JTextField();
        lblClasse = new JLabel("Classe");
        Classe[] classes = Classe.values();
        cboClasse = new JComboBox(classes);
        lblNomeImagem = new JLabel("Nome da Imagem");
        txtNomeImagem = new JTextField(); 
        lblVida = new JLabel("Vida");
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0,0,100,5);
        spiVida =  new JSpinner(spinnerModel);
        
        painelCadastro.add(lblId);      
        painelCadastro.add(txtId);      
        painelCadastro.add(lblNome);      
        painelCadastro.add(txtNome);      
        painelCadastro.add(lblClasse);      
        painelCadastro.add(cboClasse);      
        painelCadastro.add(lblNomeImagem);      
        painelCadastro.add(txtNomeImagem);      
        painelCadastro.add(lblVida);      
        painelCadastro.add(spiVida);              
		return painelCadastro;
	}

	private JPanel montaPainelBotoes() {
        JPanel painelBotoes = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
        painelBotoes.setLayout(layout);
        btnSalvar = new JButton("Salvar");
        btnSalvar.setMnemonic(KeyEvent.VK_S);
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSalvar.setEnabled(false);
                // SalvarAtividade que depende de recurso (BD) e pode demorar
                // chamando em uma thread diferente da EDT Event Dispatcher Thread
                Thread threadDao = new Thread(new SalvarPersonagem());
                threadDao.start();
            }
        });

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setMnemonic(KeyEvent.VK_C);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPersonagem(personagem);
            }
        });
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);
        return painelBotoes;
	}

    void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
        txtId.setText(personagem.getId().toString());
        txtNome.setText(personagem.getNome());
        cboClasse.setSelectedItem(personagem.getClasse());
        txtNomeImagem.setText(personagem.getNomeImagem());
        spiVida.setValue(personagem.getVida());
    }
    

    private Personagem getPersonagemAlterado() {
        Personagem personagemAlterado = new Personagem();
        personagemAlterado.setId(Long.parseLong(txtId.getText()));
        personagemAlterado.setNome(txtNome.getText());
        Classe classe = (Classe) cboClasse.getSelectedItem();
        personagemAlterado.setClasse(classe);
        personagemAlterado.setNomeImagem(txtNomeImagem.getText());
        Integer vida = (Integer) spiVida.getValue();
        personagemAlterado.setVida(vida);
        return personagemAlterado;
    }    
    
    private class SalvarPersonagem implements Runnable {

        @Override
        public void run() {
            Personagem personagemAlterado = getPersonagemAlterado();
            try {
                gerenciador.salvar(personagemAlterado);
            } catch (DadosException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível confirmar a operação",
                        "Confirmação", JOptionPane.ERROR_MESSAGE);
            }
            personagem = personagemAlterado;
            PainelBuscaPersonagens.getInstance().atualizaTabela();
            //Atualizando a tela na EDT Event Dispatcher Thread
            // por meio da classe SwingUtilities
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    btnSalvar.setEnabled(true);
                }
            });
        }
    }

}
