package br.com.rodrigo.OFP.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.rodrigo.OFP.modelo.CategoriaDespesa;
import br.com.rodrigo.OFP.modelo.SubCategoriaDespesa;
import br.com.rodrigo.OFP.repository.CategoriaDespesaRepository;
import br.com.rodrigo.OFP.repository.SubCategoriaDespesaRepository;

@Controller
@RequestMapping("/subCategoriaDespesa")
public class SubCategoriaDespesaController {

	@Autowired
	private CategoriaDespesaRepository categoriaDespesaRepository;

	@Autowired
	private SubCategoriaDespesaRepository subCategoriaDespesaRepository;

	private List<CategoriaDespesa> listaDecategoriaDespesa;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(@Valid SubCategoriaDespesa subCategoriaDespesa, BindingResult result) {

		seSubCategoriaNaoFoiMaracadaAdicionaRequiredField(subCategoriaDespesa, result);

		if (result.hasErrors()) {
			return form(subCategoriaDespesa);
		}

		preencherCategoriaDespesa(subCategoriaDespesa);


		saveAndFlushCategoriaDespesa(subCategoriaDespesa);

		subCategoriaDespesaRepository.save(subCategoriaDespesa);
		return new ModelAndView("redirect:/subCategoriaDespesa/cadastroCategoriaDespesa");
	}

	private void saveAndFlushCategoriaDespesa(SubCategoriaDespesa subCategoriaDespesa) {
		categoriaDespesaRepository.save(categoriaDespesaRepository
				.findOneById(subCategoriaDespesa.getCategoriaDespesa().getId()));
	}

	@RequestMapping("/cadastroCategoriaDespesa")
	public ModelAndView form(SubCategoriaDespesa subCategoriaDespesa) {
		listaDecategoriaDespesa = categoriaDespesaRepository.findAll();
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-sub-categoria-despesa");
		modelAndView.addObject("listaDecategoriaDespesa", listaDecategoriaDespesa);
		return modelAndView;
	}

	private void seSubCategoriaNaoFoiMaracadaAdicionaRequiredField(SubCategoriaDespesa subCategoriaDespesa,
			BindingResult result) {
		if (subCategoriaDespesa.getCategoriaDespesa() == null) {
			result.rejectValue("categoriaDespesa", "field.required");
		}
	}

	private void preencherCategoriaDespesa(SubCategoriaDespesa subCategoriaDespesa) {
		for (CategoriaDespesa c1 : listaDecategoriaDespesa) {
			System.out.println(c1.getNome() + " " + c1.getId());
			if (subCategoriaDespesa.getCategoriaDespesa().getId().equals(c1.getId())) {
				subCategoriaDespesa.setCategoriaDespesa(c1);
			}
		}
	}

}
