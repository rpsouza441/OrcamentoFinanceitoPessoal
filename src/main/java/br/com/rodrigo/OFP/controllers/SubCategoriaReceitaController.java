package br.com.rodrigo.OFP.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.rodrigo.OFP.modelo.CategoriaReceita;
import br.com.rodrigo.OFP.modelo.SubCategoriaReceita;
import br.com.rodrigo.OFP.repository.CategoriaReceitaRepository;
import br.com.rodrigo.OFP.repository.SubCategoriaReceitaRepository;

@Controller
@RequestMapping("/subCategoriaReceita")
public class SubCategoriaReceitaController {

	@Autowired
	private CategoriaReceitaRepository categoriaReceitaRepository;

	@Autowired
	private SubCategoriaReceitaRepository subCategoriaReceitaRepository;

	private List<CategoriaReceita> listaDecategoriaReceita;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(@Valid SubCategoriaReceita subCategoriaReceita, BindingResult result) {

		seSubCategoriaNaoFoiMaracadaAdicionaRequiredField(subCategoriaReceita, result);

		if (result.hasErrors()) {
			return form(subCategoriaReceita);
		}

		preencherCategoriaReceita(subCategoriaReceita);

		saveAndFlushCategoriaReceita(subCategoriaReceita);

		subCategoriaReceitaRepository.save(subCategoriaReceita);
		return new ModelAndView("redirect:/receita/cadastro");
	}

	private void saveAndFlushCategoriaReceita(SubCategoriaReceita subCategoriaReceita) {
		categoriaReceitaRepository.saveAndFlush(
				categoriaReceitaRepository.findOneById(subCategoriaReceita.getCategoriaReceita().getId()));
	}

	@RequestMapping("/cadastroCategoriaReceita")
	public ModelAndView form(SubCategoriaReceita subCategoriaReceita) {
		listaDecategoriaReceita = categoriaReceitaRepository.findAll();
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-sub-categoria-receita");
		modelAndView.addObject("listaDecategoriaReceita", listaDecategoriaReceita);
		return modelAndView;
	}

	private void seSubCategoriaNaoFoiMaracadaAdicionaRequiredField(SubCategoriaReceita subCategoriaReceita,
			BindingResult result) {
		if (subCategoriaReceita.getCategoriaReceita() == null) {
			result.rejectValue("categoriaReceita", "field.required");
		}
	}

	private void preencherCategoriaReceita(SubCategoriaReceita subCategoriaReceita) {
		for (CategoriaReceita c1 : listaDecategoriaReceita) {
			if (subCategoriaReceita.getCategoriaReceita().getId().equals(c1.getId())) {
				subCategoriaReceita.setCategoriaReceita(c1);
			}
		}
	}

}
