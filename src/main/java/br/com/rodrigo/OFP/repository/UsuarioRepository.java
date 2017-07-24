package br.com.rodrigo.OFP.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodrigo.OFP.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

	Usuario findByEmail(String email);

	List<Usuario> findByFirstNameStartingWithOrderByFirstName(String nome);

	List<Usuario> findByFirstNameContainingOrLastNameContaining(String search,String search2);
	
	

}
