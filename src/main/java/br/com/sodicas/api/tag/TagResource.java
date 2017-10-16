package br.com.sodicas.api.tag;

import java.util.List;

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

import br.com.sodicas.api.util.Mensagen;

@Stateless
@Path("tag")
public class TagResource {
	
	@EJB
	private TagDao dao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tag> getTags(){
		return dao.buscaTodos();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adicionarTag(Tag tag) {
		Status status = Status.BAD_REQUEST;
		Mensagen msg;
		
		try {
			dao.adicionar(tag);
			return Response.ok(tag).build();
		} catch (Exception e) {
			msg = new Mensagen(0, e.getMessage());
			return Response.status(status).entity(msg).build();
		}
		
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterarTag(Tag tag) {
		Status status = Status.BAD_REQUEST;
		Mensagen msg;
		
		try {
			dao.alterar(tag);
			msg = new Mensagen(1, "Operação realizada com sucesso!");
			status = Status.OK;
		} catch (Exception e) {
			msg = new Mensagen(0, e.getMessage());
		}
		return Response.status(status).entity(msg).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Tag getTag(@PathParam("id") Long id) {
		return dao.buscaPorId(id);
	}

}
