package br.com.rodrigo.OFP;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import br.com.rodrigo.OFP.modelo.Role;
import br.com.rodrigo.OFP.modelo.Usuario;
import br.com.rodrigo.OFP.repository.RoleRepository;
import br.com.rodrigo.OFP.repository.UsuarioRepository;

@Component
public class PopuladorDeBanco {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	private JpaTransactionManager transactionManager;

	@PostConstruct
	public void init() {
		TransactionTemplate template = new TransactionTemplate(transactionManager);
		template.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
//				List<Role> roles = populaRoles();
//				roleRepository.save(roles);
//
//				Usuario usuario = new Usuario();
//				populaAdmin(usuario);
//
//				List<Role> roles2 = roleAdminParaUsuarioAdmin();
//				usuario.setRoles(roles2);
//
//				usuarioRepository.save(usuario);

			}

		});
	}

	private List<Role> roleAdminParaUsuarioAdmin() {
		Role role = roleRepository.findOne("ROLE_ADMIN");
		List<Role> roles2 = new ArrayList<>();
		roles2.add(role);
		return roles2;
	}

	private List<Role> populaRoles() {
		List<Role> roles = new ArrayList<Role>();

		roles.add(new Role("ROLE_ADMIN"));
		roles.add(new Role("ROLE_USER"));
		return roles;
	}

	private void populaAdmin(Usuario usuario) {
		usuario.setDataNascimento(Calendar.getInstance());
		usuario.setEmail("admin@admin.com");
		usuario.setFirstName("Admin");
		usuario.setLastName("");
		usuario.setSenha("admin");
	}

}
