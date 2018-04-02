package br.com.sodicas.api.dica;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import br.com.sodicas.api.autor.AutorService;
import br.com.sodicas.api.util.Mensagem;
import javax.ws.rs.ext.Provider;

@Path("dica")
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DicaResource {

    @EJB
    private DicaDao dao;
    @EJB
    private AutorDao autorDao;
    @EJB
    private AutorService autorService;

    @POST
    @Path("filtro/{limit}/{offset}")
    public Response buscarPor(@PathParam("limit") int limit, @PathParam("offset") int offset, Dados dados) {
        try {
            System.out.println(dados.getTags());
            List<Dica> lista = dao.buscaPor(dados.getTitulo(), dados.getTags(), limit, offset);
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("lancamento")
    public Response buscarPor(@QueryParam("limit") int limit, @QueryParam("offset") int offset) {
        try {
            return Response.ok(dao.buscaPor(limit, offset)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("{id}")
    public Dica buscarPorId(@PathParam("id") Long id) {
        return dao.buscaPorId(id);
    }

    @GET
    @Path("/autor/{idAutor}")
    public List<Dica> buscarPorAutor(@PathParam("idAutor") Long idAutor) {
        Autor autor = autorDao.buscaPorId(idAutor);
        return dao.buscaPor(autor);
    }

    @POST
    public Response adicionar(Dica dica) {
        try {
            dica.setData(Calendar.getInstance());
            dao.adicionar(dica);
            return Response.ok(dica).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(new Mensagem(0, e.getMessage())).build();
        }

    }

    @PUT
    public Response alterar(Dica dica) {
        Status status = Status.BAD_REQUEST;
        Mensagem msg;
        try {
            dao.alterar(dica);
            status = Status.OK;
            msg = new Mensagem(1, "Operação realizasa com sucesso");
        } catch (Exception e) {
            msg = new Mensagem(0, e.getMessage());
        }
        return Response.status(status).entity(msg).build();
    }

    @DELETE
    @Path("{id}")
    public Response remover(@PathParam("id") Long id) {
        Status status = Status.BAD_REQUEST;
        Mensagem msg;
        try {
            Dica dica = dao.buscaPorId(id);
            dao.remove(dica);
            status = Status.OK;
            msg = new Mensagem(1, "Operação realizasa com sucesso");
        } catch (Exception e) {
            msg = new Mensagem(0, e.getMessage());
        }
        return Response.status(status).entity(msg).build();
    }

    @POST
    @Path("votar")
    public Dica votar(Voto voto) {
        voto.setDica(dao.buscaPorId(voto.getDica().getId()));
        voto.votar();
        dao.alterar(voto.getDica());
        autorService.atualizarPontuacao(voto.getDica().getAutor());
        return voto.getDica();
    }

}
