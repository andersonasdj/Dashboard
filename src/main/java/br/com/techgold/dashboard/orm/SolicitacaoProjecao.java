package br.com.techgold.dashboard.orm;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface SolicitacaoProjecao {

	Long getId();
	String getAfetado();
	String getClassificacao();
	String getDescricao();
	String getPrioridade();
	String getStatus();
	String getLocal();
	String getNomeCliente();
	String getNomeFuncionario();
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	LocalDateTime getDataAbertura();
	String getVersao();
	boolean getVip();
	boolean getRedFlag();
	Long getPeso();
}
