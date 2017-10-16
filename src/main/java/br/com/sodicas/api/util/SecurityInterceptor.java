package br.com.sodicas.api.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Provider
public class SecurityInterceptor implements ContainerRequestFilter {
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String rest = requestContext.getHeaderString("Restrito");
		boolean restrito  = "true".equals(rest);
		String token = requestContext.getHeaderString(JWTUtil.TOKEN_HEADER);
		
		if(!restrito) {
			return;
		}
		
		if(Objects.isNull(token) || token.trim().isEmpty()) {
			requestContext.abortWith(Response.status(Status.UNAUTHORIZED).entity(new Mensagen(0, "não possui token")).build());
			return;
		}
		
		try {
			JWTUtil.parser(token);
			return;
		} catch (Exception e) {
			requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
		}
	}

}
