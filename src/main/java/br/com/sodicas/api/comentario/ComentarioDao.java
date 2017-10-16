package br.com.sodicas.api.comentario;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.sodicas.api.dica.Dica;

@Stateless
public class ComentarioDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public Comentario buscaPorId(Long id) {
		return manager.find(Comentario.class, id);
	}
	
	public List<Comentario> buscarPor(Dica dica){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Comentario> query = builder.createQuery(Comentario.class);
		Root<Comentario> root = query.from(Comentario.class);
		query.where(
				builder.equal(root.get("dica"), dica)
				);
		query.select(root);
		return manager.createQuery(query).getResultList();
	}
	
	public void adicionar(Comentario comentario) {
		manager.persist(comentario);
	}
	
	public void alterar(Comentario comentario) {
		manager.merge(comentario);
	}

}
