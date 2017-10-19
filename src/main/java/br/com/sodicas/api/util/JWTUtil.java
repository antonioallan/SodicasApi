package br.com.sodicas.api.util;

import java.util.Calendar;
import java.util.Objects;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {
	private static String key = "SODICAS_SECURITY_KEY";
	private static int timeToExpire = 10;

	public static final String TOKEN_HEADER = "Authentication";

	public static String create(String subject) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, timeToExpire);
		return Jwts.builder()
				.setSubject(subject)
				.setExpiration(now.getTime())
				.signWith(SignatureAlgorithm.HS512, key).compact();
	}

	public static Jws<Claims> decode(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token);
	}
	
	public static void parser(String token) throws Exception {
		String user = Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		if(Objects.isNull(user) || user.trim().isEmpty()) {
			throw new Exception("Token Inválido");
		}
	}
}
