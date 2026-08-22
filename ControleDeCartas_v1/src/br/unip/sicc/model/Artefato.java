package br.unip.sicc.model;

import java.util.Objects;

public class Artefato {
	
    private Long id;    
    private String nome;
    private Categoria categoria;
    private String nomeImagem;
    private int forca;
    
	public Artefato() {
	}
	
	public Artefato(String nome, Categoria categoria, String nomeImagem, int forca) {
		super();
		this.nome = nome;
		this.categoria = categoria;
		this.nomeImagem = nomeImagem;
		this.forca = forca;
	}

	public Artefato(Long id, String nome, Categoria categoria, String nomeImagem, int forca) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.nomeImagem = nomeImagem;
		this.forca = forca;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public int getForca() {
		return forca;
	}

	public void setForca(int forca) {
		this.forca = forca;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, Integer.valueOf(forca), id, nome, nomeImagem);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artefato other = (Artefato) obj;
		return categoria == other.categoria && forca == other.forca && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(nomeImagem, other.nomeImagem);
	}

	@Override
	public String toString() {
		return "Artefato [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", nomeImagem=" + nomeImagem
				+ ", forca=" + forca + "]";
	}    
	

    
    
}
