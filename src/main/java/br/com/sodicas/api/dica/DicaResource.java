package br.com.sodicas.api.dica;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.sodicas.api.autor.Autor;
import br.com.sodicas.api.autor.AutorDao;
import br.com.sodicas.api.util.Mensagen;

@Path("dica")
@Stateless
public class DicaResource {

	@EJB
	private DicaDao dao;
	@EJB
	private AutorDao autorDao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Dica> buscarPor(Dados dados){
		return dao.buscaPor(dados.getTitulo(),dados.getTags());
	}
	
	@GET
	@Path("lancamento")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dica> buscarPor(@QueryParam("limit") int limit,@QueryParam("offset") int offset){
		return dao.buscaPor(limit, offset);
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Dica buscarPorId(@PathParam("id") Long id){
		return dao.buscaPorId(id);
	}
	
	@GET
	@Path("/autor/{idAutor}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dica> buscarPorAutor(@PathParam("idAutor") Long idAutor){
		Autor autor = autorDao.buscaPorId(idAutor); 
		return dao.buscaPor(autor);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adicionar(Dica dica) {
		try {
			dao.adicionar(dica);
			return Response.ok(dica).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(new Mensagen(0, e.getMessage())).build();
		}
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterar(Dica dica) {
		Status status = Status.BAD_REQUEST;
		Mensagen msg;
		try {
			dao.alterar(dica);
			status = Status.OK;
			msg = new Mensagen(1,"Operação realizasa com sucesso");
		} catch (Exception e) {
			msg = new Mensagen(0, e.getMessage());
		}
		return Response.status(status).entity(msg).build();
	}
}
