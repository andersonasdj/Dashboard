package br.com.techgold.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.techgold.dashboard.model.Solicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

	
	@Query(nativeQuery = true,
			value = "SELECT COUNT(*) FROM solicitacoes s "
					+ "WHERE s.excluido != true "
					+ "AND s.status != 'FINALIZADO'")
	public Long totalNaoFinalizados();
	
	
	@Query(nativeQuery = true,
			value = "SELECT COUNT(*) FROM solicitacoes s "
					+ "WHERE s.excluido != true "
					+ "AND s.status != 'FINALIZADO' "
					+ "AND s.prioridade = 'CRITICA'")
	public Long totalCritica();
	
}
