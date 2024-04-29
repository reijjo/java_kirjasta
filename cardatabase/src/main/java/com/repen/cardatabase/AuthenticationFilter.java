package com.repen.cardatabase;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import service.AuthenticationService;

import java.io.IOException;

public class AuthenticationFilter extends GenericFilterBean {
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
		throws IOException, ServletException {
		Authentication authentication = AuthenticationService.getAuthentication((HttpServletRequest) req);
		SecurityContextHolder.getContext()
				.setAuthentication(authentication);
		filterChain.doFilter(req, res);
	}
}
