package br.com.techgold.dashboard.services;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.techgold.dashboard.dto.DTODadosDashboard;
import br.com.techgold.dashboard.dto.DTOFuncionarios;
import br.com.techgold.dashboard.model.Funcionario;
import br.com.techgold.dashboard.orm.SolicitacaoProjecao;
import br.com.techgold.dashboard.repository.FuncionarioRepository;
import br.com.techgold.dashboard.repository.SolicitacaoRepository;

@Service
public class SolicitacaoService {
	
	@Autowired
	private SolicitacaoRepository repository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Long listarTotalNaoFinalizados() {
		return repository.totalNaoFinalizados();
	}
	
	public Long listarTotalCritica() {
		return repository.totalCritica();
	}

	public DTODadosDashboard dadosDashboard() {
		
		LocalDateTime hojeInicio = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 00, 00, 00);
		LocalDateTime hojeFim = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 23, 59, 00);
		
		List<Funcionario> funcionarios = funcionarioRepository.buscarFuncionarios();
		List<DTOFuncionarios> funcionariosDisponiveis = new ArrayList<>();
		
		funcionarios.forEach(f -> {
			if(repository.totalAndamentoPorFuncionario(f.getId()) <= 0) {
				String name =f.getNomeFuncionario();
				//normalizando a string para ficar sem acentos
				name = Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
				//retirando as preposições. Adicione outras se desejar.
				name = name.replaceAll("da|das|do|dos", "");
				//retirando todas as palvras exceto a primeira letra e transformando e maiusculo.
				name = name.replaceAll("\\B\\w\\s*", "").toUpperCase();
				funcionariosDisponiveis.add(new DTOFuncionarios(f.getNomeFuncionario(),name, f.getRefeicao()));
			}
		});

		return new DTODadosDashboard(
				repository.totalCritica(),
				repository.qtdCriticasHoje(hojeInicio,hojeFim),
				repository.totalNaoFinalizados(),
				repository.qtdAbertasHoje(hojeInicio,hojeFim),
				repository.totalProblemas(),
				repository.qtdProblemasHoje(hojeInicio, hojeFim),
				repository.totalAgendamentosAtrasados(LocalDateTime.now()),
				repository.qtdAgendamentosHoje(hojeInicio, hojeFim),
				funcionariosDisponiveis,
				repository.listarSolicitacoes());
	}

	public String buscaUltimaAtualizacaoId() {
		return repository.buscaUltimaSolicitacaoId();
	}
	
	public SolicitacaoProjecao buscaUltimaAtualizacaoSolicitacao() {
		return repository.buscaUltimaSolicitacao();
	}
}
