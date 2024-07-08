package br.com.techgold.dashboard.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Funcionario extends Usuario{
	
	@Column(length = 50)
	private String nomeFuncionario;
//	@Enumerated(EnumType.STRING)
//	private Funcao funcao;
	private String caminhoFoto;
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	private LocalDateTime dataAtualizacaoSenha;
	
	private Boolean trocaSenha;
	



}
