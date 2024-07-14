package org.silverbullet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/index.html", "/webjars/**", "/assets/**", "/favicon.ico",
						"/api/**").permitAll()
				.anyRequest().authenticated()
				
			)
			.csrf(AbstractHttpConfigurer::disable) // tempolary
//			.formLogin((form) -> form
//				.loginPage("/login")
//				.permitAll()
//			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}
}