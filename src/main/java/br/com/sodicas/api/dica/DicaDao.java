package br.com.sodicas.api.dica;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
	
	public List<Dica> buscaTodos(){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Dica> query = builder.createQuery(Dica.class);
		Root<Dica> root = query.from(Dica.class);
		query.select(root);
		return manager.createQuery(query).getResultList();
	}
	
	public void adicionar(Dica dica) {
		manager.persist(dica);
	}
	
	public void alterar(Dica dica) {
		manager.merge(dica);
	}
	
	public List<Dica> buscaPor(String titulo, List<Tag> tags){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Dica> query = builder.createQuery(Dica.class);
		Root<Dica> root = query.from(Dica.class);
		query.where(
				builder.like(root.get("titulo"), titulo.concat("%")),
				root.get("tags").in(tags)
				);
		query.select(root);
		return manager.createQuery(query).getResultList();
	}
	
	public List<Dica> buscaPor(Autor autor){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Dica> query = builder.createQuery(Dica.class);
		Root<Dica> root = query.from(Dica.class);
		query.where(
				builder.equal(root.get("autor"), autor)
				);
		query.select(root);
		return manager.createQuery(query).getResultList();
	}
	
	
	public List<Dica> buscaPor(int limit, int offset){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Dica> query = builder.createQuery(Dica.class);
		Root<Dica> root = query.from(Dica.class);
		query.select(root);
		return manager.createQuery(query)
				.setFirstResult(offset)
				.setMaxResults(limit)
				.getResultList();
	}

}
