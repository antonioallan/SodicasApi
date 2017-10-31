package br.com.sodicas.api.usuario;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.sodicas.api.autor.Autor;

@Entity
public class Usuario {

	@Id
	@Column(name="username",nullable=false)
	private String username;
	
	@Column(name="email",nullable=false)
	private String email;
	
	@JsonIgnore
	@Column(name="senha",nullable=false)
	private String senha;
	
	@OneToOne(optional=true, cascade= {CascadeType.PERSIST,CascadeType.MERGE})
	private Autor autor;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getSenha() {
		return senha;
	}

	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
}
