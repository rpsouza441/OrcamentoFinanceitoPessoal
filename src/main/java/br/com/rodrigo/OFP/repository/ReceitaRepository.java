package br.com.rodrigo.OFP.repository;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodrigo.OFP.modelo.Receita;
import br.com.rodrigo.OFP.modelo.Usuario;

public interface ReceitaRepository  extends JpaRepository<Receita, UUID>{
	
	List<Receita> findByDatapagamentoBetweenAndUsuario(Calendar start, Calendar end, Usuario usuario);

	Page<Receita> findByNomeContainingAndUsuario(String search, Pageable pageable, Usuario usuario);
	
	List<Receita> findByUsuario(Usuario usuario);

	Page<Receita> findByUsuario(Pageable pageable, Usuario usuario);


}
