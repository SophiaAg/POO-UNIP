package br.unip.sicc.dao;

import java.util.ArrayList;
import java.util.List;

import br.unip.sicc.model.Classe;
import br.unip.sicc.model.Personagem;

public class PersonagemList {

	private List<Personagem> personagens;
	private static long contador = 1;

	public PersonagemList() {
		personagens = new ArrayList<>();
		personagens.add(new Personagem(contador++, "Big Horn", Classe.GUERREIRO, "guerreiro.png", 12));
		personagens.add(new Personagem(contador++, "Oliver", Classe.ARQUEIRO, "arqueiro.png", 10));
		personagens.add(new Personagem(contador++, "Squirrel", Classe.LANDINO, "landino.png", 15));
		personagens.add(new Personagem(contador++, "Pi Popo", Classe.SACERDOTE, "sacerdote.png", 8));
		personagens.add(new Personagem(contador++, "Gordalfio", Classe.MAGO, "mago.png", 8));
	}

	public void incluir(Personagem personagem) throws DadosException{
        if (personagem != null) {
        	personagem.setId(contador++);
        	personagens.add(personagem);
        } else {
			throw new DadosException("Personagem nulo");
        }		
	}

	public void atualizar(Personagem personagem) throws DadosException {
        if (personagem != null) {
            for (Personagem personagemAtual : personagens) {
                if (personagemAtual.getId() == personagem.getId()) {
                    int indice = personagens.indexOf(personagemAtual);
                    personagens.set(indice, personagem);
                    break;
                }
            }
        } else {
			throw new DadosException("Personagem nulo");
		}
	}

	public void excluir(Personagem personagem) throws DadosException {
		if (personagem != null) {
			personagens.remove(personagem);
		} else {
			throw new DadosException("Personagem nulo");
		}
	}

	public Personagem getPorId(Long id) throws DadosException {
		Personagem personagem = null;
		for (Personagem personagemAtual : personagens) {
			if (personagemAtual.getId() == id) {
				personagem = personagemAtual;
				break;
			}
		}
		return personagem;
	}

	public List<Personagem> getPorClasse(Classe classe) throws DadosException {
		List<Personagem> atividadesFiltradas = new ArrayList<>();
		for (Personagem personagemAtual : personagens) {
			if (personagemAtual.getClasse() == classe) {
				atividadesFiltradas.add(personagemAtual);
			}
		}
		return atividadesFiltradas;
	}

	public List<Personagem> getTodos() throws DadosException {
		return personagens;
	}

}
