package br.com.rodrigo.OFP.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.rodrigo.OFP.modelo.CategoriaDespesa;

public interface CategoriaDespesaRepository  extends JpaRepository<CategoriaDespesa, UUID>{
	
	@Query("SELECT c FROM CategoriaDespesa c WHERE c.id = :id")
	public CategoriaDespesa findOneById(@Param("id")UUID id);

}
