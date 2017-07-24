package br.com.rodrigo.OFP.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodrigo.OFP.modelo.SubCategoriaDespesa;

public interface SubCategoriaDespesaRepository  extends JpaRepository<SubCategoriaDespesa, UUID>{
	

}
