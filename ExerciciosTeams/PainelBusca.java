package br.unip.sicc.exercicio.view;

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

    //Construtor PainelBusca()
    public PainelBusca(){
    //Configurar o gerenciador
        this.setLayout(new BorderLayout());
        JPanel painelFiltro =  montaPainelFiltro();
        JTable painelTabela = montaPainelTabela();
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

    private JTable montaPainelTabela(){
        JTable tabela = new JTable();//intanciar, criar objeto

        return tabela;
    }

    private JPanel montaPainelBotoes(){

        return painelBotoes;
    }
}
