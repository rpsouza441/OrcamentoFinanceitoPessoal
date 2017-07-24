package br.com.rodrigo.OFP.repository.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.rodrigo.OFP.modelo.Usuario;
import br.com.rodrigo.OFP.repository.UsuarioRepository;

@Repository
public class UsuarioUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// TODO Conferir com a logica de login e senha
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(email);

		if (usuario == null) {
			throw new RuntimeException("O usuário " + email + " não foi encontrado");
		}

		return usuario;

	}

}
