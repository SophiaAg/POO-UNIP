package br.unip.sicc.exercicio.view.view;

import br.unip.sicc.exercicio.view.model.ArtefatoList;
import br.unip.sicc.exercicio.view.model.ArtefatoTableModel;
import br.unip.sicc.exercicio.view.model.Artefato;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

//classe deve herdar de JPanel
public class PainelBusca extends JPanel {
    private JLabel lblFiltro;
    //Caixa de seleção
    private JComboBox cboFiltro;
    private JButton btnFiltro;
    private JButton btnSelecionar ;
    private JButton btnExcluir;
    private JTable tabela;
    private ArtefatoList repository = new ArtefatoList();
    private static PainelBusca instance;

    public static PainelBusca getInstance(){
        if(instance == null){
            instance = new PainelBusca();
        }
        return instance;
    }


    //Construtor PainelBusca()
    private PainelBusca(){
    //Configurar o gerenciador
        this.setLayout(new BorderLayout());
        JPanel painelFiltro =  montaPainelFiltro();
        JPanel painelTabela = montaPainelTabela();
        JPanel painelBotoes = montaPainelBotoes();

        this.add(painelFiltro, BorderLayout.NORTH);
        this.add(painelTabela, BorderLayout.CENTER);
        this.add(painelBotoes, BorderLayout.SOUTH);

    }

    private JPanel montaPainelFiltro() {

        JPanel painelFiltro = new JPanel();//intanciar, criar objeto
        lblFiltro = new JLabel("Tipo");
        cboFiltro = new JComboBox();
        btnFiltro = new JButton("Buscar");
        // mnemônico de teclado (tecla de atalho) d(Alt + B)
        btnFiltro.setMnemonic(KeyEvent.VK_B);

        //Adicionar os componentes ao painel exatamente na sequência:
        // lblFiltro, cboFiltro e btnFiltro.
        painelFiltro.add(lblFiltro);
        painelFiltro.add(cboFiltro);
        painelFiltro.add(btnFiltro);
        return painelFiltro;
    }

    private JPanel montaPainelTabela(){
        JPanel painelTabela = new JPanel();
        ArtefatoTableModel modelo;
                try{
                   modelo =  new ArtefatoTableModel(repository.getTodos());
                }catch (DadosException error){
                    JOptionPane.showMessageDialog(this, "Não foi possível carregar os artefatos."
                    );
                    return painelTabela;
                }

        tabela = new JTable(modelo);//intanciar, criar objeto
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Envelopar a tabela dentro de um JScrollPane e retornar essa barra de rolagem.
        JScrollPane scroll = new JScrollPane(tabela);//configurando o o JScrollPane
        painelTabela.add(scroll);//aqui retona essa barra na tabela


        return painelTabela;
    }

    private JPanel montaPainelBotoes(){
        JPanel painelBotoes = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
        painelBotoes.setLayout(layout);

        btnSelecionar = new JButton("Selecionar");
        //mnemônico 'S' (KeyEvent.VK_S).
        btnSelecionar.setMnemonic(KeyEvent.VK_S);
        btnExcluir = new JButton("Excluir");
        //mnemônico 'X' (KeyEvent.VK_X).
        btnExcluir.setMnemonic(KeyEvent.VK_X);

        painelBotoes.add(btnSelecionar);
        painelBotoes.add(btnExcluir);

        return painelBotoes;
    }
}
