package br.com.rodrigo.OFP.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.rodrigo.OFP.builder.ReceitaBuilder;
import br.com.rodrigo.OFP.modelo.Conta;
import br.com.rodrigo.OFP.modelo.Receita;
import br.com.rodrigo.OFP.modelo.SubCategoriaReceita;
import br.com.rodrigo.OFP.modelo.Usuario;
import br.com.rodrigo.OFP.repository.ContaRepository;
import br.com.rodrigo.OFP.repository.ReceitaRepository;
import br.com.rodrigo.OFP.repository.SubCategoriaReceitaRepository;
import br.com.rodrigo.OFP.repository.UsuarioRepository;
import br.com.rodrigo.OFP.validation.ReceitaValidation;

@Controller
@RequestMapping("/receita")
public class ReceitaController {

	@Autowired
	private ReceitaRepository receitaRepository;

	@Autowired
	private SubCategoriaReceitaRepository subCategoriaReceitaRepository;

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	private List<Conta> contas;
	private List<SubCategoriaReceita> categorias;

	private ReceitaBuilder receitaBuilder;

	private Usuario usuario;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Receita receita, BindingResult result) {
		ReceitaValidation receitaValidation = new ReceitaValidation(receita, result);
		result = receitaValidation.getResult();

		if (result.hasErrors()) {
			return form(receita);
		}

		receitaBuilder = new ReceitaBuilder(receita);
		receita = receitaBuilder.comCategoria(categorias).comConta(contas).constroi();
		salvaReceita(receita);
		salvaConta(receita);
		return new ModelAndView("redirect:/extrato/mostrar");
	}

	private void adicionarReceitaNoUsuario(Receita receita) {
		preencherUsuario();
		usuario.setListaReceita(new ArrayList<>(Arrays.asList(receita)));
	}

	private void preencherUsuario() {
		Usuario principal = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		usuario = usuarioRepository.findOne(principal.getId());
	}
	
	@RequestMapping(value = "/editar/{uuid}")
	public ModelAndView editar(@PathVariable(value="uuid") UUID uuid) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-receita");
		modelAndView.addObject("receita", receitaRepository.getOne(uuid));
		categorias = subCategoriaReceitaRepository.findAll();
		modelAndView.addObject("categorias", categorias);
		contas = contaRepository.findAll();
		modelAndView.addObject("contas", contas);
		 return modelAndView;
	}

	private void salvaConta(Receita receita) {
		contaRepository.save(receita.getConta());

	}

	private void salvaReceita(Receita receita) {
		for (Receita r : receita.getConta().getReceitas()) {
			adicionarReceitaNoUsuario(r);
			receitaRepository.save(r);

		}

	}
	
	
	

	@RequestMapping("/cadastro")
	public ModelAndView form(Receita receita) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-receita");
		preencherUsuario();
		categorias = subCategoriaReceitaRepository.findAll();
		modelAndView.addObject("categorias", categorias);
		contas = contaRepository.findByUsuario(usuario);
		modelAndView.addObject("contas", contas);

		return modelAndView;
	}

}
