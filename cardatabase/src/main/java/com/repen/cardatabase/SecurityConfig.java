package com.repen.cardatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	@Autowired
	private UserDetailServiceImpl userDetailService;


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	http.authorizeHttpRequests((auth -> auth
//			.requestMatchers("/api/users/**").permitAll()
			.anyRequest()
			.authenticated()))
			.httpBasic(Customizer.withDefaults());



		return http.build();
	}


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService)
				.passwordEncoder(new BCryptPasswordEncoder());
	}
}
