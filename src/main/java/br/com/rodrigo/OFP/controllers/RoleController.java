package br.com.rodrigo.OFP.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.rodrigo.OFP.modelo.Role;
import br.com.rodrigo.OFP.repository.RoleRepository;

@Controller
@RequestMapping("/role")
public class RoleController {

	
	@Autowired
	private RoleRepository roleRepository;

	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravarRole(@Valid Role role, BindingResult result){
		

		if (result.hasErrors()) {
			return form(role);
		}
		role.setNome("ROLE_"+role.getNome().toUpperCase());
		
		roleRepository.save(role);
		return new ModelAndView("redirect:/role/cadastro");
	}

	@RequestMapping("/cadastro")
	public ModelAndView form(Role role) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-role");

		return modelAndView;
	}
	
	
	@RequestMapping("/listar")
	public ModelAndView mostrar(Model model) {
		ModelAndView modelAndView = new ModelAndView("/view/roles");
		modelAndView.addObject("roles", roleRepository.findAll());
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/editar/{nome}")
	public ModelAndView editar(@PathVariable(value="nome") String nome) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-role");
		
		Role findByNome = roleRepository.findByNome(nome);
		findByNome.setNome(findByNome.getNome().substring(5));
		modelAndView.addObject("role", findByNome);

		 return modelAndView;
	}
	@RequestMapping(value = "/deletar/{nome}")
	public ModelAndView deletar(@PathVariable(value="nome") String nome) {
		roleRepository.deleteByNome(nome);
		return new ModelAndView("redirect:/role/listar");
	}
	
	
	
	
	
	

	
	
	
	
}
