package br.com.rodrigo.OFP.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.rodrigo.OFP.modelo.CategoriaDespesa;
import br.com.rodrigo.OFP.repository.CategoriaDespesaRepository;

@Controller
@RequestMapping("/categoriaDespesa")
public class CategoriaDespesaController {

	@Autowired
	private CategoriaDespesaRepository categoriaDespesaRepository;
	

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar( @Valid CategoriaDespesa categoriaDespesa, BindingResult result) {
		if (result.hasErrors()) {
			return form(categoriaDespesa);
		}
		categoriaDespesaRepository.save(categoriaDespesa);
		return new ModelAndView("redirect:/categoriaDespesa/cadastroGrupoCategoria");
	}
	
	

	@RequestMapping("/cadastroGrupo")
	public ModelAndView form(CategoriaDespesa categoriaDespesa) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-categoria-despesa");

		return modelAndView;
	}
	
	

}
