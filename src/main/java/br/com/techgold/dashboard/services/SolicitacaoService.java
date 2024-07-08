package br.com.techgold.dashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.techgold.dashboard.repository.SolicitacaoRepository;

@Service
public class SolicitacaoService {
	
	@Autowired
	private SolicitacaoRepository repository;
	
	public Long listarTotalNaoFinalizados() {
		return repository.totalNaoFinalizados();
	}
	
	

}
