package br.com.sodicas.api.tag;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class TagDao {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Tag> buscaTodos(){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
		Root<Tag> root = query.from(Tag.class);
		query.select(root);
		return manager.createQuery(query).getResultList();
	}
	
	public Tag buscaPorId(Long id) {
		return manager.find(Tag.class, id);
	}
	
	public void adicionar(Tag tag) {
		manager.persist(tag);
	}
	
	public void alterar(Tag tag) {
		manager.merge(tag);
	}
}
