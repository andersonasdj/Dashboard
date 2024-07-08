package br.com.techgold.dashboard.model;

import java.time.LocalDateTime;

import br.com.techgold.dashboard.model.enums.Categoria;
import br.com.techgold.dashboard.model.enums.Classificacao;
import br.com.techgold.dashboard.model.enums.FormaAbertura;
import br.com.techgold.dashboard.model.enums.Local;
import br.com.techgold.dashboard.model.enums.Prioridade;
import br.com.techgold.dashboard.model.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "solicitacoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Solicitacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dataAbertura;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dataAndamento;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dataFinalizado;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dataAgendado;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dataAtualizacao;
	
	@Enumerated(EnumType.STRING)
	private FormaAbertura formaAbertura;
	
	@Column(name = "solicitante", length = 60)
	private String solicitante;
	@Column(name="afetado", length = 60)
	private String afetado;
	@Column(name="descricao", length = 300)
	private String descricao;
	@Column(name="resolucao", length = 300)
	private String resolucao;
	@Column(name="observacao", length = 300)
	private String observacao;
	@Column(name="abertoPor", length = 60)
	private String abertoPor;
	@Column(name="prioridade")
	@Enumerated(EnumType.STRING)
	private Prioridade prioridade;
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column(name = "categoria")
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	@Column(name = "classificacao")
	@Enumerated(EnumType.STRING)
	private Classificacao classificacao;
	@Column(name = "local")
	@Enumerated(EnumType.STRING)
	private Local local;
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	@ManyToOne(fetch = FetchType.LAZY)
	private Funcionario funcionario;

	private Boolean excluido;
	@Column(name = "duracao")
	private Long duracao;
	
//	@Version
	private Integer versao;
	
	@OneToOne(fetch = FetchType.LAZY)
	private LogSolicitacao log;
	
	private Long peso;
	
}
