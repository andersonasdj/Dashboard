package br.com.techgold.dashboard.dto;

import java.util.List;

import br.com.techgold.dashboard.orm.SolicitacaoProjecao;

public record DTODadosDashboard(
		Long criticas,
		Long criticasHoje,
		Long ativas,
		Long abertasHoje,
		Long finalizadasHoje,
		Long problemas,
		Long problemasHoje,
		Long atrasados,
		Long agendadosHoje,
		List<DTOFuncionarios> funcionariosDisponiveis,
		List<SolicitacaoProjecao> solicitacoes) {
}
