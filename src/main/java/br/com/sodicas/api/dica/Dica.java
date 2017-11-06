package br.com.sodicas.api.dica;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sodicas.api.autor.Autor;
import br.com.sodicas.api.comentario.Comentario;
import br.com.sodicas.api.tag.Tag;

@Entity
public class Dica {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data",nullable=false)
	private Calendar data;
	
	@Column(name="titulo", nullable=false)
	private String titulo;
	
	@ManyToOne
	@JoinColumn(name="autor_id",referencedColumnName="id")
	private Autor autor;
	
	@Column(name="conteudo",nullable=false, columnDefinition="TEXT")
	private String conteudo;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="tag_id",referencedColumnName="id")
	private Set<Tag> tags;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="dica")
	private Set<Comentario> comentario;
	
	@Column(name="pontuacao",scale = 2, precision = 15)
	private BigDecimal pontuacao;
	
	public Dica() {
		this.data = Calendar.getInstance();
		this.pontuacao = BigDecimal.ZERO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public BigDecimal getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(BigDecimal pontuacao) {
		this.pontuacao = pontuacao;
	}
	
}
