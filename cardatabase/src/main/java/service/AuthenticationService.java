package service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;

public class AuthenticationService {
	static final long EXPIRATIONTIME = 86_400_000;
	static final String SIGNINGKEY = "SecretKey";
	static final String PREFIX = "Bearer";

	private static SecretKey getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SIGNINGKEY);
		return Keys.hmacShaKeyFor(keyBytes);
	};

	// Add token to Authorization header
	static public void addToken(HttpServletResponse res, String username) {
		String JwtToken = Jwts.builder().subject(username)
				.expiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(getSigningKey())
				.compact();
		res.addHeader("Authorization", PREFIX + " " + JwtToken);
		res.addHeader("Access-Control-Expose-Headers", "Authorization");
	}

//	Get token from Authorization header
	static public Authentication getAuthentication(HttpServletRequest req) {
		String token = req.getHeader("Authorization");
		if (token != null) {
			String user = Jwts.parser()
					.verifyWith(getSigningKey())
					.build()
					.parseSignedClaims(token.replace(PREFIX, ""))
					.getPayload()
					.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			}

		}
		return null;
	}
}
