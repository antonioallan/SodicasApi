package br.com.sodicas.api.security;

import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.sodicas.api.usuario.Usuario;
import br.com.sodicas.api.usuario.UsuarioDao;
import br.com.sodicas.api.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Path("security")
@Stateless
public class SecurityResource {

	@EJB
	private UsuarioDao dao;

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(Credencias credencias) {
		try {
			Usuario user = dao.buscaPorId(credencias.getUsername());
			String senhaCript = credencias.getCriptSenha();
			if (Objects.nonNull(user) && (user.getSenha().equals(senhaCript))) {
				String token = JWTUtil.create(credencias.getUsername());
				UserLogged me = new UserLogged();
				me.setUsername(credencias.getUsername());
				me.setToken(token);
				return Response.ok().entity(me).build();
			} else {
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/logado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response logado(@HeaderParam(JWTUtil.TOKEN_HEADER) String token) {
		try {
			Jws<Claims> jws = JWTUtil.decode(token);
			UserLogged logado = new UserLogged();
			logado.setUsername(jws.getBody().getSubject());
			return Response.ok(logado).build();
		} catch (Exception e) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

}
