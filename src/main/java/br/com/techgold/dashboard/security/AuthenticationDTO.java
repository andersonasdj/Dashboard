package br.com.techgold.dashboard.security;

public record AuthenticationDTO(
		String username, 
		String password) {
}
