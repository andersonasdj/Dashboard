package br.com.techgold.dashboard.security;


import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		 httpSecurity
         .csrf(AbstractHttpConfigurer::disable)
         .headers(headers -> headers.disable())
         //.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         .authorizeHttpRequests(authorize -> authorize
                         .requestMatchers("/templates/**").permitAll()
                         .requestMatchers("/assets/**").permitAll()
                         .requestMatchers(HttpMethod.POST, "/login").permitAll()
                         .requestMatchers(HttpMethod.POST, "/create").permitAll()
                         .requestMatchers(HttpMethod.GET, "/create").permitAll()
                         .anyRequest().authenticated()
         ).httpBasic(withDefaults())
         .formLogin(formLogin ->
                 formLogin
                         .loginPage("/login")
                         .defaultSuccessUrl("/inicio")
                         .permitAll()
         )
         .sessionManagement((sessionManagement) -> sessionManagement.invalidSessionUrl("/login")
                 .maximumSessions(1)
                 .sessionRegistry(sessionRegistry()))
         .logout(logout -> logout.logoutSuccessUrl("/login")
                 .logoutUrl("/logout")
                 .invalidateHttpSession(true)
                 .deleteCookies("JSESSIONID"))
 		;
	 
	 return httpSecurity.build();
	}

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
	
}