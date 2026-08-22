package br.unip.sicc.view;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import br.unip.sicc.model.Classe;
import br.unip.sicc.model.Personagem;

public class PersonagemTableModel implements TableModel {

	private List<Personagem> personagens;

	public PersonagemTableModel(List<Personagem> personagens) {
		super();
		this.personagens = personagens;
	}

	@Override
	public int getRowCount() {
		return personagens.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Id";
		case 1:
			return "Nome";
		case 2:
			return "Classe";
		case 3:
			return "Vida";
		}

		return "";
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Long.class;
		case 1:
			return String.class;
		case 2:
			return Classe.class;
		case 3:
			return int.class;
		}
		return void.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Personagem personagemAtual = personagens.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return personagemAtual.getId();
		case 1:
			return personagemAtual.getNome();
		case 2:
			return personagemAtual.getClasse();
		case 3:
			return personagemAtual.getVida();
		}
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
	}
	
	Personagem getPersonagem(int index) {
		return personagens.get(index);
	}
	void setPersonagens(List<Personagem> personagens) {
		this.personagens = personagens;
	}

}
