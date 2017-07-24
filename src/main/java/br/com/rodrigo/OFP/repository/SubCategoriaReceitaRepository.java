package br.com.rodrigo.OFP.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodrigo.OFP.modelo.SubCategoriaReceita;

public interface SubCategoriaReceitaRepository  extends JpaRepository<SubCategoriaReceita, UUID>{

}
