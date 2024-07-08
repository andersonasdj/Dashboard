package br.com.techgold.dashboard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Usuario {
	
	
	@Column(length = 100)
	private String nomeCliente;
	@Column(length = 200)
	private String endereco;
	@Column(length = 20)
	private String telefone;
	@Column(length = 20)
	private String cnpj;
	private boolean redFlag;
	private boolean vip;
	
	
}
