package br.unip.sicc.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

import br.unip.sicc.dao.DadosException;
import br.unip.sicc.model.Classe;
import br.unip.sicc.model.GerenciadorDePersonagens;
import br.unip.sicc.model.Personagem;

public class PainelBuscaPersonagens extends JPanel{

    private JLabel lblFiltro;
    private JComboBox cboFiltro;
    private JButton btnFiltro;
    private JTable tabelaPersonagens;
    private JButton btnSelecionar;
    private JButton btnExcluir;

    private GerenciadorDePersonagens gerenciador;

    //implementação do Singleton
    private static PainelBuscaPersonagens instance;

    private PainelBuscaPersonagens() {
        this.gerenciador = GerenciadorDePersonagens.getInstance();

        this.setLayout(new BorderLayout());
        JPanel painelFiltro = montaPainelFiltro();
        JScrollPane painelTabela = montaPainelTabela();
        JPanel painelBotoes = montaPainelBotoes();

        this.add(painelFiltro, BorderLayout.NORTH);
        this.add(painelTabela, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.SOUTH);

    }

    public static PainelBuscaPersonagens getInstance() {
        if (instance == null) {
            instance = new PainelBuscaPersonagens();
        }
        return instance;
    }
    //implementação do Singleton

    private JPanel montaPainelFiltro() {
        JPanel painelFiltro = new JPanel();
        lblFiltro = new JLabel("Tipo");
        Classe[] classes = Classe.values();
        cboFiltro = new JComboBox(classes);
        cboFiltro.insertItemAt("TODOS", 0);
        cboFiltro.setSelectedIndex(0);
        btnFiltro = new JButton("Buscar");
        btnFiltro.setMnemonic(KeyEvent.VK_B);
        btnFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizaTabela();
            }

        });
        painelFiltro.add(lblFiltro);
        painelFiltro.add(cboFiltro);
        painelFiltro.add(btnFiltro);
        return painelFiltro;
    }

    private JPanel montaPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSelecionar = new JButton("Selecionar");
        btnSelecionar.setMnemonic(KeyEvent.VK_S);
        btnSelecionar.addActionListener(new SelecionarListener());
        btnExcluir = new JButton("Excluir");
        btnExcluir.setMnemonic(KeyEvent.VK_X);
        btnExcluir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnExcluir.setEnabled(false);
                // SalvarAtividade que depende de recurso (BD) e pode demorar
                // chamando em uma thread diferente da EDT Event Dispatcher Thread
                Thread threadDao = new Thread(new ExcluirPersonagem());
                threadDao.start();

            }

        });

        painel.add(btnSelecionar);
        painel.add(btnExcluir);
        return painel;
    }

    private JScrollPane montaPainelTabela() {
        TableModel model = null;
        try {
            model = new PersonagemTableModel(gerenciador.getTodos());
        } catch (DadosException ex) {
            ex.printStackTrace();
        }
        tabelaPersonagens = new JTable(model);
        tabelaPersonagens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(tabelaPersonagens);
        return scroll;
    }

    void atualizaTabela() {
        new Thread() {
            @Override
            public void run() {
                Object itemSelecionado = cboFiltro.getSelectedItem();
                Classe classeSelecionada = null;
                if (itemSelecionado instanceof Classe) {
                	classeSelecionada
                            = (Classe) itemSelecionado;
                }
                try {
                    List<Personagem> listaFiltrada
                            = gerenciador.getPorClasse(classeSelecionada);
                    PersonagemTableModel model = (PersonagemTableModel) tabelaPersonagens.getModel();
                    model.setPersonagens(listaFiltrada);
                    //Atualizando a tela na EDT Event Dispatcher Thread
                    // por meio da classe SwingUtilities
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            tabelaPersonagens.repaint();
                            tabelaPersonagens.revalidate();
                        }
                    });
                } catch (DadosException ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possível realizar a busca",
                            "Busca", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.start();
    }

    private Personagem getPersonagemSelecionado() {
        int linhaSelecionada = tabelaPersonagens.getSelectedRow();
        Personagem personagemSelecionado = null;
        if (linhaSelecionada >= 0) {
            PersonagemTableModel model
                    = (PersonagemTableModel) tabelaPersonagens.getModel();
            personagemSelecionado = model.getPersonagem(linhaSelecionada);
        } else {
            personagemSelecionado = null;
        }
        return personagemSelecionado;
    }

    private class SelecionarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        	Personagem personagemSelecionado = getPersonagemSelecionado();
            if (personagemSelecionado == null) {
                JOptionPane.showMessageDialog(null, "Selecione uma linha",
                        "Sobre", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            PainelCadastroPersonagem.getInstance().setPersonagem(personagemSelecionado);
        }
    }

    private class ExcluirPersonagem implements Runnable {

        @Override
        public void run() {
        	Personagem personagemSelecionado = getPersonagemSelecionado();
            if (personagemSelecionado == null) {
                JOptionPane.showMessageDialog(null, "Selecione um personagem",
                        "Selecione", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String mensagem = "Confirma a exclusão do personagem "
                    + personagemSelecionado.getId() + "?";
            int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Confirmação",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            try {
                if (opcao == JOptionPane.OK_OPTION) {
                    gerenciador.excluir(personagemSelecionado);
                    atualizaTabela();
                }
            } catch (DadosException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível realizar a exclusão",
                        "Exclusão", JOptionPane.ERROR_MESSAGE);
            }
            //Atualizando a tela na EDT Event Dispatcher Thread
            // por meio da classe SwingUtilities
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    btnExcluir.setEnabled(true);
                }
            });
        }
    }
	

}
