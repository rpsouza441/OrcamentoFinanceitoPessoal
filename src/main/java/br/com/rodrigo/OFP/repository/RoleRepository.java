package br.com.rodrigo.OFP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rodrigo.OFP.modelo.Role;

public interface RoleRepository extends JpaRepository<Role, String> {


	public Role findByNome(String nome);
	
	public Role findByNomeContaining(String nome);
	
	@Transactional
	public void deleteByNome(String nome);
	
}
