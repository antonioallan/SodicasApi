package br.com.sodicas.api.dica;

import java.math.BigDecimal;

public class Voto {
	
	private Dica dica;
	private BigDecimal nota;
	public Dica getDica() {
		return dica;
	}
	public void setDica(Dica dica) {
		this.dica = dica;
	}
	public BigDecimal getNota() {
		return nota;
	}
	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}
	
	
	public void votar() {
		BigDecimal pontuacao = this.dica.getPontuacao();
		BigDecimal novaPontuacao = BigDecimal.ZERO.compareTo(pontuacao) == 0 ? this.nota : this.dica.getPontuacao().add(this.nota).divide(BigDecimal.valueOf(2)).setScale(2);
		this.dica.setPontuacao(novaPontuacao);
	}

}
