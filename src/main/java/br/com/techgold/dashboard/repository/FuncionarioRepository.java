package br.com.techgold.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.techgold.dashboard.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
	@Query(nativeQuery = true,
			value = "SELECT * FROM funcionarios f "
					+ "WHERE f.ativo = true "
					+ "AND f.ausente != true ")
	public List<Funcionario> buscarFuncionarios();
	
	@Query(value = "SELECT COUNT(*) FROM funcionarios", nativeQuery = true)
	public int existsFuncionarios();
	
	@Query(value = "SELECT f.nomeFuncionario FROM funcionarios f WHERE f.id=:id", nativeQuery = true)
	public String buscarNomePorId(Long id);
	
	public UserDetails findByUsername(String username);
	
}