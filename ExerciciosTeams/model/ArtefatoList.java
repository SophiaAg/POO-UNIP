package br.unip.sicc.exercicio.view.model;

import java.util.ArrayList;
import java.util.List;

import br.unip.sicc.exercicio.view.view.DadosException;
import br.unip.sicc.exercicio.view.view.Categoria;

public class ArtefatoList {

	private List<Artefato> artefatos;
	private static long contador = 1;

	public ArtefatoList() {
		artefatos = new ArrayList<>();
		artefatos.add(new Artefato(contador++, "Espada Mágica", Categoria.ATAQUE, "espada.jpg", 5));
		artefatos.add(new Artefato(contador++, "Escudo de Madeira", Categoria.DEFESA, "escudo.jpg", 6));
		artefatos.add(new Artefato(contador++, "Espada Justiceira", Categoria.ATAQUE, "espadaJ.jpg", 5));
		artefatos.add(new Artefato(contador++, "Barril", Categoria.DEFESA, "barril.jpg", 5));
		artefatos.add(new Artefato(contador++, "Veneno", Categoria.POCAO, "veneno.jpg", 8));
	}

	public void incluir(Artefato artefato) throws DadosException {
        if (artefato != null) {
        	artefato.setId(contador++);
        	artefatos.add(artefato);
        } else {
			throw new DadosException("Artefato nulo");
        }		
	}

	public void atualizar(Artefato artefato) throws DadosException {
        if (artefato != null) {
            for (Artefato artefatoAtual : artefatos) {
                if (artefatoAtual.getId() == artefato.getId()) {
                    int indice = artefatos.indexOf(artefatoAtual);
                    artefatos.set(indice, artefato);
                    break;
                }
            }
        } else {
			throw new DadosException("Artefato nulo");
		}
	}

	public void excluir(Artefato artefato) throws DadosException {
		if (artefato != null) {
			artefatos.remove(artefato);
		} else {
			throw new DadosException("Artefato nulo");
		}
	}

	public Artefato getPorId(Long id) throws DadosException {
		Artefato artefato = null;
		for (Artefato artefatoAtual : artefatos) {
			if (artefatoAtual.getId() == id) {
				artefato = artefatoAtual;
				break;
			}
		}
		return artefato;
	}

	public List<Artefato> getPorCategoria(Categoria categoria) throws DadosException {
		List<Artefato> artefatosFiltrados = new ArrayList<>();
		for (Artefato artefato : artefatos) {
			if (artefato.getCategoria() == categoria) {
				artefatosFiltrados.add(artefato);
			}
		}
		return artefatosFiltrados;
	}

	public List<Artefato> getTodos() throws DadosException {
		return artefatos;
	}

}
