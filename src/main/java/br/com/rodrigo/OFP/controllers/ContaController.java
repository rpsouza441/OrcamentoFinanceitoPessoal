package br.com.rodrigo.OFP.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.rodrigo.OFP.modelo.Conta;
import br.com.rodrigo.OFP.modelo.Usuario;
import br.com.rodrigo.OFP.repository.ContaRepository;
import br.com.rodrigo.OFP.repository.UsuarioRepository;

@Controller
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private Usuario usuario;

	@RequestMapping(value = "gravar", method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Conta conta, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			System.out.println("erros");
			// for (FieldError iterable_element : result.getFieldErrors()) {
			// System.out.println(iterable_element.getCode());
			// System.out.println(iterable_element.getField());
			// System.out.println(iterable_element.toString());
			//
			// }
			return form(conta);
		}

		adicionarContaNoUsuario(conta);
		contaRepository.save(conta);

		return new ModelAndView("redirect:/conta/mostrar");
	}

	private void adicionarContaNoUsuario(Conta conta) {
		preencherUsuario();
		usuario.setListaConta(new ArrayList<>(Arrays.asList(conta)));
	}

	private void preencherUsuario() {
		Usuario principal = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		usuario = usuarioRepository.findOne(principal.getId());
	}

	@RequestMapping("/cadastro")
	public ModelAndView form(Conta conta) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-conta");

		return modelAndView;
	}

	//
	// @RequestMapping(value="/mostrar/")
	// public ModelAndView mostrar(@RequestParam(value = "procurar",
	// required=false) String procurar, ModelAndView modelAndView) {
	// modelAndView = new ModelAndView("/view/conta");
	// modelAndView.addObject("contas", contaRepository.findAll());
	// modelAndView.addObject("procurar", procurar);
	// System.out.println(procurar);
	//
	// return modelAndView;
	// }
	//

	@RequestMapping(value = "/mostrar")
	public ModelAndView mostrar(@RequestParam(value = "search", required = false) String search) {
		ModelAndView mav = new ModelAndView("/view/conta");
		preencherUsuario();
		if (search == null) {
			mav.addObject("contas", contaRepository.findByUsuario(usuario));
		} else {
			mav.addObject("contas", contaRepository.findByNomeContainingAndUsuario(search, usuario));
		}
		mav.addObject("search", search);

		return mav;
	}

	@RequestMapping(value = "/editar/{uuid}")
	public ModelAndView editar(@PathVariable(value = "uuid") UUID uuid) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-conta");
		modelAndView.addObject("conta", contaRepository.getOne(uuid));

		return modelAndView;
	}

	@RequestMapping(value = "/deletar/{uuid}")
	public ModelAndView deletar(@PathVariable(value = "uuid") UUID uuid) {
		contaRepository.delete(uuid);
		return new ModelAndView("redirect:/conta/");
	}

}
