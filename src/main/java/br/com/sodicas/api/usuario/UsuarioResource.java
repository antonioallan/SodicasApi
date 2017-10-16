package br.com.sodicas.api.usuario;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.sodicas.api.autor.AutorDao;
import br.com.sodicas.api.util.Cripto;
import br.com.sodicas.api.util.Mensagen;

@Path("usuario")
@Stateless
public class UsuarioResource {

	@EJB
	private UsuarioDao dao;
	@EJB
	private AutorDao autorDao;

	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario(@PathParam("username") String username) {
		return dao.buscaPorId(username);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response adicionarUsuario(Usuario usuario) {
		Status status = Status.BAD_REQUEST;
		Mensagen msg = new Mensagen();
		try {
			if (!dao.existeUsuario(usuario.getUsername())) {
				usuario.setSenha(Cripto.MD5(usuario.getSenha()));
				dao.adicionar(usuario);
				msg = new Mensagen(1, "Cadastro realizado com sucesso!");
				status = Status.OK;
			} else {
				msg = new Mensagen(0, "Usuário ja existe");
			}
		} catch (Exception e) {
			msg = new Mensagen(0, e.getMessage());
		}
		return Response.status(status).entity(msg).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterarUsuario(Usuario usuario) {
		Status status = Status.BAD_REQUEST;
		Mensagen msg = new Mensagen();
		try {
//			usuario.setAutor(autorDao.buscaAutorComId(usuario.getAutor().getId()));
			dao.alterar(usuario);
			status = Status.OK;
			msg = new Mensagen(1,"Operação realizada com sucesso.");
		} catch (Exception e) {
			msg = new Mensagen(0,e.getMessage());
		}
		return Response.status(status).entity(msg).build();
	}
}
