package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(customizer -> customizer.disable())
			.authorizeHttpRequests(requests -> requests
														.requestMatchers("register", "login").permitAll() //for register and login no need of authentication
														.anyRequest().authenticated())	// Require authentication for any request
			.httpBasic(Customizer.withDefaults())// Enable HTTP Basic Authentication
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Disable session management
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		//http.formLogin(Customizer.withDefaults());// Enable Form Login
		
		
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
//		UserDetails user1 = User.withDefaultPasswordEncoder()
//								.username("vivek")
//								.password("vivek")
//								.roles("USER")
//								.build();
//		
//		UserDetails user2 = User.withDefaultPasswordEncoder()
//                                .username("admin")
//                                .password("admin")
//                                .roles("ADMIN")
//                                .build();
		
		
		return new InMemoryUserDetailsManager();
	}
	
	//for database authentication 
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return provider;
	}
	//For JWT
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
