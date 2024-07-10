package br.com.techgold.dashboard.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techgold.dashboard.dto.DTODadosDashboard;
import br.com.techgold.dashboard.orm.SolicitacaoProjecao;
import br.com.techgold.dashboard.services.SolicitacaoService;

@RestController
@RequestMapping("/api/solicitacao")
public class SolicitacaoRestController {
	
	@Autowired
	private SolicitacaoService solicitacaoService;
	
	@GetMapping
	public Long totalNaoFinalizadas() {
		return solicitacaoService.listarTotalNaoFinalizados();
	}

	@GetMapping("/critica")
	public Long totalCritica() {
		return solicitacaoService.listarTotalCritica();
	}
	
	@GetMapping("/dados")
	public DTODadosDashboard dadosDashboard() {
		return solicitacaoService.dadosDashboard();
	}
	
	@GetMapping("/lastupdateid")
	public String ultimaAtualizacaoId() {
		return solicitacaoService.buscaUltimaAtualizacaoId();
	}
	
	@GetMapping("/lastupdatesolicitacao")
	public SolicitacaoProjecao ultimaAtualizacaoSolocitacao() {
		return solicitacaoService.buscaUltimaAtualizacaoSolicitacao();
	}
	
}
