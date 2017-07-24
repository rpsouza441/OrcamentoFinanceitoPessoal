package br.com.rodrigo.OFP.repository;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodrigo.OFP.modelo.Despesa;
import br.com.rodrigo.OFP.modelo.Usuario;

public interface DespesaRepository extends JpaRepository<Despesa, UUID> {

	List<Despesa> findByDatapagamentoBetweenAndUsuario(Calendar start, Calendar end, Usuario usuario);

	Page<Despesa> findByNomeContainingAndUsuario(String search, Pageable pageable, Usuario usuario);
	
	List<Despesa> findByUsuario(Usuario usuario);

	Page<Despesa> findByUsuario(Pageable pageable, Usuario usuario);

	
	

}
