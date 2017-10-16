package br.com.sodicas.api.comentario;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sodicas.api.dica.Dica;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private Long id;
	
	@Column(name="autor")
	private String autor;
	
	@Column(name="conteudo",nullable=false, length=1024)
	private String conteudo; 
	
	@Temporal(TemporalType.DATE)
	@Column(name="data", nullable=false)
	private Calendar data;
	
	@ManyToOne
	@JoinColumn(name="dica_id",nullable=false, referencedColumnName="id")
	private Dica dica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}
	
	public Dica getDica() {
		return dica;
	}
	
	public void setDica(Dica dica) {
		this.dica = dica;
	}
	
}
