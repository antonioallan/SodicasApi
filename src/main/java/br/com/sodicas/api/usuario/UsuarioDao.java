package br.com.sodicas.api.usuario;

import java.io.Serializable;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UsuarioDao implements Serializable {

    @PersistenceContext
    private EntityManager manager;

    public Usuario buscaPorId(String username) {
        return manager.find(Usuario.class, username);
    }

    public void adicionar(Usuario usuario) {
        manager.persist(usuario);
    }

    public Usuario alterar(Usuario usuario) {
        return manager.merge(usuario);
    }

    public boolean existeUsuario(String username) {
        Usuario usuario = this.buscaPorId(username);
        return Objects.nonNull(usuario);
    }
}
