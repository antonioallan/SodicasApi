package br.com.sodicas.api.autor;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.sodicas.api.dica.DicaDao;

@Stateless
public class AutorService {
	@EJB
	private AutorDao dao;
	
	@EJB
	private DicaDao dicaDao;
	
	public void atualizarPontuacao(Autor autor){
		BigDecimal pontuacao = dicaDao.mediaPontuacaoPor(autor).setScale(2);
		autor.setPontuacao(pontuacao);
		dao.alterar(autor);
	}
}
