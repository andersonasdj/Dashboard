package br.com.techgold.dashboard.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.techgold.dashboard.model.Solicitacao;
import br.com.techgold.dashboard.orm.SolicitacaoProjecao;

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
	
	@Query(nativeQuery = true,
			value = "SELECT COUNT(*) FROM solicitacoes s "
					+ "WHERE s.excluido != true "
					+ "AND s.status != 'FINALIZADO' "
					+ "AND s.classificacao = 'PROBLEMA'")
	public Long totalProblemas();
	
	@Query(nativeQuery = true,
			value = "SELECT COUNT(*) "
			+ "FROM solicitacoes s "
			+ "WHERE s.status = 'AGENDADO' " 
			+ "AND s.excluido != true "
			+ "AND s.dataAgendado < :agora")
	public Long totalAgendamentosAtrasados(LocalDateTime agora);

	@Query(nativeQuery = true,
			value = "SELECT * FROM solicitacoes s "
					+ "WHERE s.excluido != true "
					+ "AND s.status = 'ANDAMENTO'")
	public List<Solicitacao> buscarEmAndamento();
	
	@Query(nativeQuery = true,
			value = "SELECT COUNT(*) FROM solicitacoes s "
					+ "WHERE s.excluido != true "
					+ "AND s.status = 'ANDAMENTO' "
					+ "AND s.funcionario_id = :id")
	public Long totalAndamentoPorFuncionario(Long id);
	
	@Query(nativeQuery = true,
			value = "SELECT s.id, s.afetado, s.categoria, c.nomeCliente, "
			+ "s.classificacao, s.descricao, c.redFlag, s.status, s.peso, "
			+ "s.prioridade, c.vip, s.versao, s.local, "
			+ "f.nomeFuncionario, s.dataAbertura "
			+ "FROM solicitacoes s "
			+ "INNER JOIN clientes c ON s.cliente_id=c.id "
			+ "LEFT JOIN funcionarios f ON s.funcionario_id=f.id "
			+ "WHERE s.excluido != true "
			+ "AND s.status != 'FINALIZADO' "
			+ "AND s.status != 'PAUSADO' "
			+ "AND s.status != 'AGUARDANDO' "
			+ "AND s.prioridade != 'PLANEJADA' "
			+ "AND s.prioridade != 'MEDIA' "
			+ "AND s.prioridade != 'BAIXA' "
			+ "AND s.classificacao != 'EVENTO' "
			+ "AND s.classificacao != 'ACESSO' "
			+ "AND s.classificacao != 'BACKUP' "
			+ "AND s.classificacao != 'SOLICITACAO' "
			+ "ORDER BY s.peso DESC")
	public List<SolicitacaoProjecao> listarSolicitacoes();
	
	@Query(nativeQuery = true,
			value = "SELECT s.id, s.afetado, s.categoria, c.nomeCliente, "
					+ "s.classificacao, s.descricao, c.redFlag, s.status, s.peso, "
					+ "s.prioridade, c.vip, s.versao, "
					+ "f.nomeFuncionario, s.dataAbertura "
					+ "FROM solicitacoes s "
					+ "INNER JOIN clientes c ON s.cliente_id=c.id "
					+ "LEFT JOIN funcionarios f ON s.funcionario_id=f.id "
					+ "ORDER BY s.dataAtualizacao DESC limit 1")
	public SolicitacaoProjecao buscaUltimaSolicitacao();
	
	@Query(nativeQuery = true,
			value = "SELECT s.dataAtualizacao FROM solicitacoes s ORDER BY s.dataAtualizacao DESC limit 1")
	public String buscaUltimaSolicitacaoId();
	
	@Query(nativeQuery = true,
			value = "SELECT count(*) "
			+ "FROM solicitacoes s "
			+ "WHERE s.excluido = false "
			+ "AND s.dataAbertura >= :hojeInicio "
			+ "AND s.dataAbertura <= :hojeFim")
	public Long qtdAbertasHoje(LocalDateTime hojeInicio, LocalDateTime hojeFim);
	
	@Query(nativeQuery = true,
			value = "SELECT count(*) "
			+ "FROM solicitacoes s "
			+ "WHERE s.excluido = false "
			+ "AND s.status = 'FINALIZADO' "
			+ "AND s.dataFinalizado >= :hojeInicio "
			+ "AND s.dataFinalizado <= :hojeFim")
	public Long qtdFinalizadasHoje(LocalDateTime hojeInicio, LocalDateTime hojeFim);
	
	@Query(nativeQuery = true,
			value = "SELECT count(*) "
			+ "FROM solicitacoes s "
			+ "WHERE s.excluido = false "
			+ "AND s.prioridade = 'CRITICA' "
			+ "AND s.dataAbertura >= :hojeInicio "
			+ "AND s.dataAbertura <= :hojeFim")
	public Long qtdCriticasHoje(LocalDateTime hojeInicio, LocalDateTime hojeFim);
	
	@Query(nativeQuery = true,
			value = "SELECT count(*) "
			+ "FROM solicitacoes s "
			+ "WHERE s.excluido = false "
			+ "AND s.classificacao = 'PROBLEMA' "
			+ "AND s.dataAbertura >= :hojeInicio "
			+ "AND s.dataAbertura <= :hojeFim")
	public Long qtdProblemasHoje(LocalDateTime hojeInicio, LocalDateTime hojeFim);
	
	@Query(nativeQuery = true,
			value = "SELECT count(*) "
			+ "FROM solicitacoes s "
			+ "WHERE s.excluido = false "
			+ "AND s.status = 'AGENDADO' "
			+ "AND s.dataAbertura >= :hojeInicio "
			+ "AND s.dataAbertura <= :hojeFim")
	public Long qtdAgendamentosHoje(LocalDateTime hojeInicio, LocalDateTime hojeFim);
}
