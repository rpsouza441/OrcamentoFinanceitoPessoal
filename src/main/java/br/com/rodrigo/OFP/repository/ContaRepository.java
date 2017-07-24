package br.com.rodrigo.OFP.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodrigo.OFP.modelo.Conta;
import br.com.rodrigo.OFP.modelo.Usuario;

public interface ContaRepository extends JpaRepository<Conta, UUID>{

	
	List<Conta> findByNomeContainingAndUsuario(String search, Usuario usuario);
	
	List<Conta> findByUsuario(Usuario usuario);


}
