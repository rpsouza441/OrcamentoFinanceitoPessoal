package br.com.rodrigo.OFP.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.rodrigo.OFP.modelo.CategoriaReceita;

public interface CategoriaReceitaRepository  extends JpaRepository<CategoriaReceita, UUID>{
	
	@Query("SELECT c FROM CategoriaReceita c WHERE c.id = :id")
	public CategoriaReceita findOneById(@Param("id")UUID id);

}
