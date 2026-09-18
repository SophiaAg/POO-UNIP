package br.unip.sicc.exercicio.view.model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ArtefatoTableModel extends AbstractTableModel {
    private List<Artefato> artefatos; //aqui armazena a lista de artefatos, para que possa ser feitas as consultas

    public ArtefatoTableModel(List<Artefato> artefatosList){//construtor
        super();//Chama o construtor da classe pai
        this.artefatos = artefatosList;
    }


    @Override
    public int getRowCount() {
        return artefatos.size();
    }//etornando o número total de artefatos presentes na lista.

    @Override
    public int getColumnCount() {
        return 4;
    }//fala a quantidade de coluna

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "ID";
            case 1:
                return "Nome";
            case 2:
                return "Categoria";
            case 3:
                return "Força";
        }
        return "";
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Artefato artefatoAtual = artefatos.get(rowIndex);
        switch (columnIndex){
            case 0:
                return artefatoAtual.getId();
            case 1:
                return artefatoAtual.getNome();
            case 2:
                return artefatoAtual.getCategoria();
            case 3:
                return artefatoAtual.getForca();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    //Implemente métodos utilitários de atualização (ex: adicionarArtefato, atualizarTabela ou envio da lista via construtor) garantindo a chamada a fireTableDataChanged() quando a lista sofrer alterações.
    public void adicionarArtefato (Artefato artefato) {
        artefatos.add(artefato);
        fireTableDataChanged();//pra q serve?
        //O fireTableDataChanged() serve para avisar a JTable que os dados mudaram e que ela precisa atualizar a exibição.
    }

    public void atualizarTabela (List<Artefato> novaLista){
        this.artefatos = novaLista;
        fireTableDataChanged();
    }

}
