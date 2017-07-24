package br.com.rodrigo.OFP.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.rodrigo.OFP.infra.FileSaver;
import br.com.rodrigo.OFP.modelo.Bandeira;
import br.com.rodrigo.OFP.repository.BandeiraRepository;

@Controller
@RequestMapping("/bandeira")
public class BandeiraController {

	@Autowired
	private BandeiraRepository bandeiraRepository;

	@Autowired
	private FileSaver fileSaver;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(MultipartFile sumario, @Valid Bandeira bandeira, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (sumario.isEmpty()) {
			result.rejectValue("sumarioPath", "field.required");
		}

		if (result.hasErrors()) {
			return form(bandeira);
		}

		String path = fileSaver.write("arquivos-sumario", sumario);
		bandeira.setSumarioPath(path);
		bandeiraRepository.save(bandeira);

		return new ModelAndView("redirect:/bandeira/cadastro");
	}

	@RequestMapping("/cadastro")
	public ModelAndView form(Bandeira bandeira) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-bandeira");

		return modelAndView;
	}

}
