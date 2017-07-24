package br.com.rodrigo.OFP.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodrigo.OFP.modelo.Bandeira;

public interface BandeiraRepository  extends JpaRepository<Bandeira, UUID>{

}
