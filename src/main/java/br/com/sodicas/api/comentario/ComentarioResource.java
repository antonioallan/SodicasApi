package br.com.sodicas.api.comentario;

import java.util.Calendar;
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

import br.com.sodicas.api.dica.Dica;
import br.com.sodicas.api.dica.DicaDao;
import br.com.sodicas.api.util.Mensagen;

@Path("comentario")
@Stateless
public class ComentarioResource {
	
	@EJB
	private ComentarioDao dao;
	@EJB
	private DicaDao dicaDao;
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comentario> getComentarios(@PathParam("id") Long id){
		Dica dica = dicaDao.buscaPorId(id);
		return dao.buscarPor(dica);
	}
	
	@POST
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adicionar(@PathParam("id") Long dicaId, Comentario comentario) {
		try {
			comentario.setDica(dicaDao.buscaPorId(dicaId));
			comentario.setData(Calendar.getInstance());
			dao.adicionar(comentario);
			return Response.ok(comentario).build();
		} catch (Exception e) {
			Mensagen msg = new Mensagen(0,e.getMessage());
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
	}
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterar(Comentario comentario) {
		Status status = Status.BAD_REQUEST;
		Mensagen msg;
		try {
			dao.alterar(comentario);
			status = Status.OK;
			msg = new Mensagen(1, "Operação realizada com sucesso!");
		} catch (Exception e) {
			msg = new Mensagen(0,e.getMessage());
		}
		return Response.status(status).entity(msg).build();
	}
}
