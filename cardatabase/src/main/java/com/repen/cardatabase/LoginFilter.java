package com.repen.cardatabase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.repen.cardatabase.domain.AccountCredentials;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import service.AuthenticationService;

import java.io.IOException;
import java.util.Collections;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	public LoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		AccountCredentials creds = new ObjectMapper()
				.readValue(req.getInputStream(), AccountCredentials.class);
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
				creds.getUsername(), creds.getPassword(), Collections.emptyList()
		));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
																					HttpServletResponse res,
																					FilterChain chain,
																					Authentication auth) throws IOException, ServletException {
		AuthenticationService.addToken(res, auth.getName());
	}
}
