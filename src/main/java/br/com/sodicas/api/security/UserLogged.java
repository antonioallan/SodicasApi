package br.com.sodicas.api.security;

import br.com.sodicas.api.usuario.Usuario;

public class UserLogged {
	
	private String username;
	private String token;
	private Usuario usuario;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
