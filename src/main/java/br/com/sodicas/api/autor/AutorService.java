package br.com.sodicas.api.autor;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.sodicas.api.dica.DicaDao;
import java.io.Serializable;
import java.math.BigDecimal;

@Stateless
public class AutorService implements Serializable {

    @EJB
    private AutorDao dao;
    @EJB
    private DicaDao dicaDao;

    public void atualizarPontuacao(Autor autor) {
        BigDecimal pontuacao = dicaDao.mediaPontuacaoPor(autor).setScale(2);
        autor.setPontuacao(pontuacao);
        dao.alterar(autor);
    }
}
