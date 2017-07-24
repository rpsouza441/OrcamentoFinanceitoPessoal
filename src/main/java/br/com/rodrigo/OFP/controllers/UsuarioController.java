package br.com.rodrigo.OFP.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.rodrigo.OFP.modelo.Role;
import br.com.rodrigo.OFP.modelo.Usuario;
import br.com.rodrigo.OFP.repository.RoleRepository;
import br.com.rodrigo.OFP.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RoleRepository roleRepository;

	private List<Role> roles;

	private List<Role> rolesTrocarSenha = new ArrayList<>();


	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult result) {
		verificaSenha(usuario, result);
		
//		if(usuarioRepository.findByEmail(usuario.getEmail()){
//			
//		}
		
		if (result.hasErrors()) {
			return form(usuario);
		}
		aplicaRolesEncryptaSenhaESalvaUsuario(usuario);

		return new ModelAndView("redirect:../");
	}

	@RequestMapping("/cadastro")
	public ModelAndView form(Usuario usuario) {
		roles = new ArrayList<>();
		Role findByNome = roleRepository.findByNome("ROLE_USER");
		roles.add(findByNome);
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-usuario");
		modelAndView.addObject("roles", roles);
		modelAndView.addObject("usuario", usuario);

		return modelAndView;
	}

	@RequestMapping("/cadastroAdmin")
	public ModelAndView formAdmin(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-usuario");
		roles = new ArrayList<>();

		roles = roleRepository.findAll();
		modelAndView.addObject("roles", roles);
		modelAndView.addObject("usuario", usuario);

		return modelAndView;
	}

	@RequestMapping(value = "/editar/{uuid}")
	public ModelAndView editar(@PathVariable(value = "uuid") UUID uuid) {
		Usuario usuario = usuarioRepository.getOne(uuid);

		return form(usuario);

	}

	@RequestMapping(value = "/editarAdmin/{uuid}")
	public ModelAndView editarAdmin(@PathVariable(value = "uuid") UUID uuid) {
		Usuario usuario = usuarioRepository.getOne(uuid);
		return formAdmin(usuario);

	}

	@RequestMapping(value = "/trocarSenha/{uuid}")
	public ModelAndView trocarSenha(@PathVariable(value = "uuid") UUID uuid) {
		Usuario usuario = usuarioRepository.getOne(uuid);
		rolesTrocarSenha = usuario.getRoles();
		ModelAndView modelAndView = new ModelAndView("/cadastro/trocar-senha-usuario");
		usuario.setSenha("");
		modelAndView.addObject("usuario", usuario);

		return modelAndView;

	}

	@RequestMapping(value = "gravarNovaSenha", method = RequestMethod.POST)
	public ModelAndView gravarNovaSenha(@Valid Usuario usuario, BindingResult result) {
		verificaSenha(usuario, result);
		
		if (result.hasErrors()) {
			return trocarSenha(usuario.getId());
		}
		encryptaSenha(usuario);
		if (result.hasErrors()) {
			return trocarSenha(usuario.getId());
		}
		
		usuario.setRoles(rolesTrocarSenha);
		usuarioRepository.save(usuario);
		rolesTrocarSenha = new ArrayList<>();

		return new ModelAndView("redirect:/usuarios/showUsuario/");
	}

	@RequestMapping("/showUsuario/")
	public ModelAndView showUsuario() {
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		ModelAndView modelAndView = new ModelAndView("/view/usuario");
		modelAndView.addObject("usuario", usuarioRepository.findOne(usuario.getId()));

		return modelAndView;
	}

	@RequestMapping("/lista")
	public ModelAndView lista(@RequestParam(value = "search", required = false) String search) {
		ModelAndView modelAndView = new ModelAndView("/view/lista-usuario");
		if (search == null) {
			modelAndView.addObject("usuarios", usuarioRepository.findAll());
		}else {
			modelAndView.addObject("usuarios", usuarioRepository.findByFirstNameContainingOrLastNameContaining(search, search));
		}
		modelAndView.addObject("search", search);

		return modelAndView;
	}
	
	
	
	
	
	
	

	private void aplicaRolesEncryptaSenhaESalvaUsuario(Usuario usuario) {

		aplicaRoles(usuario);
		if (usuario.getId() == null) {
			encryptaSenha(usuario);
		}
		usuarioRepository.save(usuario);
	}

	private void encryptaSenha(Usuario usuario) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getPassword()));
	}

	private void aplicaRoles(Usuario usuario) {
		List<Role> listaRole = new ArrayList<>();
		if(usuario.getRoles().isEmpty()){
			listaRole.add(roleRepository.findByNome("ROLE_USER"));
		}
		for (Role role : usuario.getRoles()) {
			listaRole.add(roleRepository.findByNome(role.getNome()));
		}

		
		usuario.setRoles(listaRole);
	}

	private void verificaSenha(Usuario usuario, BindingResult result) {
		if (usuario.getSenha().isEmpty() || usuario.getSenha().length() < 6) {
			result.rejectValue("senha", "field.required");
		}
	}

}
