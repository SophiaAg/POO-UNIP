package br.unip.sicc.model;

import java.util.Objects;

public class Personagem {
	
    private Long id;    
    private String nome;
    private Classe classe;
    private String nomeImagem;
    private int vida;    
      
	public Personagem() {
	}
	public Personagem(String nome, Classe classe, String nomeImagem, int vida) {
		super();
		this.nome = nome;
		this.classe = classe;
		this.nomeImagem = nomeImagem;
		this.vida = vida;
	}
	public Personagem(Long id, String nome, Classe classe, String nomeImagem, int vida) {
		super();
		this.id = id;
		this.nome = nome;
		this.classe = classe;
		this.nomeImagem = nomeImagem;
		this.vida = vida;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public String getNomeImagem() {
		return nomeImagem;
	}
	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	@Override
	public int hashCode() {
		return Objects.hash(classe, id, nome, nomeImagem, Integer.valueOf(vida));
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personagem other = (Personagem) obj;
		return classe == other.classe && Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(nomeImagem, other.nomeImagem) && vida == other.vida;
	}
	@Override
	public String toString() {
		return "Personagem [id=" + id + ", nome=" + nome + ", classe=" + classe + ", nomeImagem=" + nomeImagem
				+ ", vida=" + vida + "]";
	}
    
    


}
