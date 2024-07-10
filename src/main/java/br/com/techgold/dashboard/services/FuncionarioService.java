package br.com.techgold.dashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.techgold.dashboard.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	FuncionarioRepository repository;
	
	public UserDetails buscaPorUserDetails(String nome) {
		return repository.findByUsername(nome);
	}
}
