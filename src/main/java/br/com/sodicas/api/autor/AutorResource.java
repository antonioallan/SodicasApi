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
import javax.ws.rs.ext.Provider;

@Provider
@Path("/autor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutorResource {

    @EJB
    private AutorDao dao;

    @GET
    public List<Autor> getAutores() {
        return dao.buscarTodos();
    }

    @POST
    public Response adicionaAutor(Autor autor) {

        dao.adiciona(autor);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Autor getAutor(@PathParam("id") Long id) {
        return dao.buscaPorId(id);
    }

}
