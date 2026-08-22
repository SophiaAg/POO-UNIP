package br.unip.sicc.model;

import java.util.List;

import br.unip.sicc.dao.DadosException;
import br.unip.sicc.dao.PersonagemList;

public class GerenciadorDePersonagens {
	
    //implementacao do Singleton
    private static GerenciadorDePersonagens instance;

    private GerenciadorDePersonagens() {
    }

    public static GerenciadorDePersonagens getInstance() {
        if (instance == null) {
            instance = new GerenciadorDePersonagens();
        }
        return instance;
    }
    //implementacao do Singleton	
    
    private PersonagemList dao = new PersonagemList();
    
    public Personagem getNovoPersonagem() {
    	Personagem personagem = new Personagem();
    	personagem.setId(0L);
    	personagem.setClasse(Classe.GUERREIRO);
    	personagem.setVida(10);
    	return personagem;
    }
    
    public void salvar(Personagem personagem) throws DadosException {
        boolean ehNova = personagem != null && personagem.getId() != null
                && !(personagem.getId() > 0);
        if (ehNova) {
            dao.incluir(personagem);
        } else {
            dao.atualizar(personagem);
        }
    }

    public void excluir(Personagem personagem) throws DadosException {
        dao.excluir(personagem);
    }

    public Personagem getPorId(Long id) throws DadosException {
        return dao.getPorId(id);
    }

    public List<Personagem> getPorClasse(Classe classe) throws DadosException {
        if (classe != null) {
            return dao.getPorClasse(classe);
        } else {
            return dao.getTodos();
        }
    }

    public List<Personagem> getTodos() throws DadosException {
        return dao.getTodos();
    }

}
