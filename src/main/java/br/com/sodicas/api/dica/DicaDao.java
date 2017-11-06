package br.com.sodicas.api.dica;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import br.com.sodicas.api.autor.Autor;
import br.com.sodicas.api.tag.Tag;

@Stateless
public class DicaDao {

	@PersistenceContext
	private EntityManager manager;

	public Dica buscaPorId(Long id) {
		return manager.find(Dica.class, id);
	}

	public List<Dica> buscaTodos() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Dica> query = builder.createQuery(Dica.class);
		Root<Dica> root = query.from(Dica.class);
		query.select(root);
		query.orderBy(builder.asc(root.get("id")));
		return manager.createQuery(query).getResultList();
	}

	public void adicionar(Dica dica) {
		manager.persist(dica);
	}

	public void alterar(Dica dica) {
		manager.merge(dica);
	}

	public void remove(Dica dica) {
		manager.remove(dica);
	}

	public List<Dica> buscaPor(String titulo, List<Tag> tags, int limit, int offset) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Dica> query = builder.createQuery(Dica.class);
		Root<Dica> root = query.from(Dica.class);
		if (tags.isEmpty()) {
			query.where(builder.like(builder.lower(root.get("titulo")), titulo.toLowerCase().concat("%")));
		} else {
			Expression<Tag> tagExp = root.join("tags");
			query.where(builder.like(builder.lower(root.get("titulo")), titulo.toLowerCase().concat("%")), tagExp.in(tags));
		}
		query.select(root);
		query.orderBy(builder.asc(root.get("id")));
		return manager.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
	}

	public List<Dica> buscaPor(Autor autor) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Dica> query = builder.createQuery(Dica.class);
		Root<Dica> root = query.from(Dica.class);
		query.where(builder.equal(root.get("autor"), autor));
		query.select(root);
		query.orderBy(builder.asc(root.get("id")));
		return manager.createQuery(query).getResultList();
	}

	public BigDecimal mediaPontuacaoPor(Autor autor) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Double> query = builder.createQuery(Double.class);
		Root<Dica> root = query.from(Dica.class);
		Path<BigDecimal> pontuacao = root.get("pontuacao");
		query.where(builder.equal(root.get("autor"), autor));
		query.select(builder.avg(pontuacao));
		Double media = manager.createQuery(query).getSingleResult();
		media = Math.round(media * 100)/100d;
		BigDecimal md = BigDecimal.valueOf(media);
		return md;
	}

	public List<Dica> buscaPor(int limit, int offset) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Dica> query = builder.createQuery(Dica.class);
		Root<Dica> root = query.from(Dica.class);
		query.select(root);
		query.orderBy(builder.asc(root.get("id")));
		return manager.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
	}

}
