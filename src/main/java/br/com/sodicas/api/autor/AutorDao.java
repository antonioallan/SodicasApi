package br.com.sodicas.api.autor;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
@Stateless
public class AutorDao {
	
	@PersistenceContext
	private EntityManager manager;

	public Autor buscaPorId(Long id) {
		return manager.find(Autor.class, id);
	}
	
	public List<Autor> buscarTodos(){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Autor> query = builder.createQuery(Autor.class);
		Root<Autor> root = query.from(Autor.class);
		query.select(root);
		return manager.createQuery(query).getResultList();
	}

	public void adiciona(Autor autor) {
		manager.persist(autor);
	}
	
	public void alterar(Autor autor) {
		manager.merge(autor);
	}

}
