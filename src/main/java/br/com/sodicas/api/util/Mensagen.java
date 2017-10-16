package br.com.sodicas.api.util;

public class Mensagen {
	
	private int tipo;
	private String msg;
	
	public Mensagen() {
	}
	
	public Mensagen(int tipo, String msg) {
		super();
		this.tipo = tipo;
		this.msg = msg;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
