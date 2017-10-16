package br.com.sodicas.api.autor;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/autor")
public class AutorResource {

	@EJB
	private AutorDao dao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Autor> getAutores() {
		return dao.buscarTodos();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adicionaAutor(Autor autor) {

		dao.adiciona(autor);
		return Response.ok().build();
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Autor getAutor(@PathParam("id") Long id) {
		return dao.buscaPorId(id);
	}

}
