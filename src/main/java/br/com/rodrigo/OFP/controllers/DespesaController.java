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

import br.com.rodrigo.OFP.builder.DespesaBuilder;
import br.com.rodrigo.OFP.modelo.CartaoDeCredito;
import br.com.rodrigo.OFP.modelo.Conta;
import br.com.rodrigo.OFP.modelo.Despesa;
import br.com.rodrigo.OFP.modelo.SubCategoriaDespesa;
import br.com.rodrigo.OFP.modelo.Usuario;
import br.com.rodrigo.OFP.repository.CartaoCreditoRepository;
import br.com.rodrigo.OFP.repository.ContaRepository;
import br.com.rodrigo.OFP.repository.DespesaRepository;
import br.com.rodrigo.OFP.repository.SubCategoriaDespesaRepository;
import br.com.rodrigo.OFP.repository.UsuarioRepository;
import br.com.rodrigo.OFP.validation.DespesaValidation;

@Controller
@RequestMapping("/despesa")
public class DespesaController {

	@Autowired
	private DespesaRepository despesaRepository;


	@Autowired
	private SubCategoriaDespesaRepository subCategoriaDespesaRepository;
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	private List<Conta> contas;
	private List<SubCategoriaDespesa> categorias;
	private List<CartaoDeCredito> cartoes;
	
	private DespesaBuilder despesabuilder;


	private Usuario usuario;

	@RequestMapping(value="gravar",method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Despesa despesa, BindingResult result) {
		DespesaValidation despesaValidation = new DespesaValidation(despesa, result);
		result = despesaValidation.verificaComConta();
		if (result.hasErrors()) {
			return form(despesa);
		}
		
		//Inicia Verificações e realiza devidas ações no banco
		
		despesabuilder = new DespesaBuilder(despesa);
		despesa=despesabuilder.comCategoria(categorias).comConta(contas).constroi();
		
		salvaDespesaComConta(despesa);
		salvaConta(despesa);
		return new ModelAndView("redirect:/extrato/mostrar");
	}
	@RequestMapping(value = "/editar/{uuid}")
	public ModelAndView editar(@PathVariable(value="uuid") UUID uuid) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-despesa");
		modelAndView.addObject("despesa", despesaRepository.getOne(uuid));
		categorias = subCategoriaDespesaRepository.findAll();
		contas = contaRepository.findByUsuario(usuario);
		modelAndView.addObject("categorias", categorias);
		modelAndView.addObject("contas", contas);
		 return modelAndView;
	}

	
	@RequestMapping("/cadastro")
	public ModelAndView form(Despesa despesa) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-despesa");
		categorias = subCategoriaDespesaRepository.findAll();
		preencheUsuario();
		contas = contaRepository.findByUsuario(usuario);
		modelAndView.addObject("categorias", categorias);
		modelAndView.addObject("contas", contas);
		return modelAndView;
	}
	

	@RequestMapping(value="gravarCartao", method = RequestMethod.POST)
	public ModelAndView gravarCartao(@Valid Despesa despesa, BindingResult result) {
		DespesaValidation despesaValidation = new DespesaValidation(despesa, result);
		result = despesaValidation.verificaComCartaoCredito();
		if (result.hasErrors()) {
			return formCartao(despesa);
		}
		
		//Inicia Verificações e realiza devidas ações no banco
		despesa.getPeriodo().setPeriodicidade(30);
		despesabuilder = new DespesaBuilder(despesa);
		despesa=despesabuilder.comCategoria(categorias).comCartao(cartoes).constroi();
		
		salvaDespesaComCartao(despesa);
		salvaCartao(despesa);
		return new ModelAndView("redirect:/extrato/mostrar");
	}

	

	@RequestMapping("/cadastroCredito")
	public ModelAndView formCartao(Despesa despesa) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-despesa-cartao-credito");
		categorias = subCategoriaDespesaRepository.findAll();
		preencheUsuario();
		cartoes = cartaoCreditoRepository.findByUsuario(usuario);
		modelAndView.addObject("categorias", categorias);
		modelAndView.addObject("cartoes", cartoes);
		return modelAndView;
	}

	

	private void salvaCartao(Despesa despesa) {
		cartaoCreditoRepository.save(despesa.getCartaoDeCredito());
		
	}
	
	private void salvaConta(Despesa despesa) {
		contaRepository.save(despesa.getConta());
	}

	private void salvaDespesaComConta(Despesa despesa) {
		for (Despesa d : despesa.getConta().getDespesas()) {
			despesaRepository.save(d);
			adicionarDespesaNoUsuario(d);
		}
	}
	private void salvaDespesaComCartao(Despesa despesa) {
		for (Despesa d : despesa.getCartaoDeCredito().getDespesas()) {
			despesaRepository.save(d);
			adicionarDespesaNoUsuario(d);
		}
	}
	
	private void adicionarDespesaNoUsuario(Despesa despesa) {
		preencheUsuario();
		usuario.setListaDespesa(new ArrayList<>(Arrays.asList(despesa)));
	}
	private void preencheUsuario() {
		Usuario principal = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		usuario = usuarioRepository.findOne(principal.getId());
	}
}

