package br.com.rodrigo.OFP.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodrigo.OFP.modelo.CartaoDeCredito;
import br.com.rodrigo.OFP.modelo.Usuario;

public interface CartaoCreditoRepository extends JpaRepository<CartaoDeCredito, UUID> {

	List<CartaoDeCredito> findByNomeContainingAndUsuario(String search, Usuario usuario);
	
	List<CartaoDeCredito> findByUsuario(Usuario usuario);

}
