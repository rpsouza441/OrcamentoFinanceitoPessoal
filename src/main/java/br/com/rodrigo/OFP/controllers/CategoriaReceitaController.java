package br.com.rodrigo.OFP.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.rodrigo.OFP.modelo.CategoriaReceita;
import br.com.rodrigo.OFP.repository.CategoriaReceitaRepository;

@Controller
@RequestMapping("/categoriaReceita")
public class CategoriaReceitaController {

	@Autowired
	private CategoriaReceitaRepository categoriaReceitaRepository;
	

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar( @Valid CategoriaReceita categoriaReceita, BindingResult result) {
		if (result.hasErrors()) {
			return form(categoriaReceita);
		}
		categoriaReceitaRepository.save(categoriaReceita);
		return new ModelAndView("redirect:/subCategoriaReceita/cadastroCategoriaReceita");
	}
	
	

	@RequestMapping("/cadastroGrupo")
	public ModelAndView form(CategoriaReceita categoriaReceita) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-categoria-receita");

		return modelAndView;
	}
	
	

}
