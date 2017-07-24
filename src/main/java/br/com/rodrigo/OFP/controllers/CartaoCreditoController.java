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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.rodrigo.OFP.modelo.Bandeira;
import br.com.rodrigo.OFP.modelo.CartaoDeCredito;
import br.com.rodrigo.OFP.modelo.Usuario;
import br.com.rodrigo.OFP.repository.BandeiraRepository;
import br.com.rodrigo.OFP.repository.CartaoCreditoRepository;
import br.com.rodrigo.OFP.repository.UsuarioRepository;

@Controller
@RequestMapping("/cartao-credito")
public class CartaoCreditoController {

	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;

	@Autowired
	private BandeiraRepository bandeiraRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	private List<Bandeira> bandeiras;

	private boolean editar = false;

	@RequestMapping(value="gravar", method = RequestMethod.POST)
	public ModelAndView gravar(@Valid CartaoDeCredito cartaoDeCredito, BindingResult result) {

		if (cartaoDeCredito.getBandeira() == null) {
			result.rejectValue("bandeira", "field.required");
		}
		
		if(cartaoDeCredito.getLimiteDisponivel().compareTo(cartaoDeCredito.getLimite()) > 0){
			result.rejectValue("limiteDisponivel", "field.required");
		}

		if (result.hasErrors()) {
			return form(cartaoDeCredito);
		}
		System.out.println("gravando");

		preencherBandeiraDeCartaoDeCredito(cartaoDeCredito);
		limiteDisponivelIgualLimite(cartaoDeCredito);
		
		
		adicionarCartaoDeCreditoNoUsuario(cartaoDeCredito);
		
		
		cartaoCreditoRepository.save(cartaoDeCredito);
		editar = false;
		return new ModelAndView("redirect:/cartao-credito/mostrar");
	}

	private void adicionarCartaoDeCreditoNoUsuario(CartaoDeCredito cartaoDeCredito) {
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario findOne = usuarioRepository.findOne(usuario.getId());
		findOne.setListaCartaoDeCredito(new ArrayList<>(Arrays.asList(cartaoDeCredito)));
	}

	private void preencherBandeiraDeCartaoDeCredito(CartaoDeCredito cartaoDeCredito) {
		for (Bandeira b : bandeiras) {
			if (b.getId().equals(cartaoDeCredito.getBandeira().getId()))
				cartaoDeCredito.setBandeira(b);
		}
	}

	@RequestMapping("/cadastro")
	public ModelAndView form(CartaoDeCredito cartaoDeCredito) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastro-ccredito");
		bandeiras = bandeiraRepository.findAll();
		modelAndView.addObject("cartaoDeCredito", cartaoDeCredito);
		modelAndView.addObject("bandeiras", bandeiras);

		return modelAndView;
	}

	private void limiteDisponivelIgualLimite(CartaoDeCredito cartaoDeCredito) {
		if (!editar) {
			cartaoDeCredito.setLimiteDisponivel(cartaoDeCredito.getLimite());

		}
	}
	
	
	@RequestMapping(value = "/mostrar")
	public ModelAndView mostrar(@RequestParam(value = "search", required = false) String search) {
		ModelAndView modelAndView = new ModelAndView("/view/cartao-credito");
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (search == null) {
			modelAndView.addObject("cartoes", cartaoCreditoRepository.findByUsuario(usuario));
		} else {
			modelAndView.addObject("cartoes", cartaoCreditoRepository.findByNomeContainingAndUsuario(search, usuario));
		}
		modelAndView.addObject("search", search);

		return modelAndView;
	}

	
	
	

	@RequestMapping(value = "/editar/{uuid}")
	public ModelAndView editar(@PathVariable(value = "uuid") UUID uuid) {
		editar = true;
		CartaoDeCredito cartaoDeCredito = cartaoCreditoRepository.getOne(uuid);
		return form(cartaoDeCredito);

	}
	

	@RequestMapping(value = "/deletar/{uuid}")
	public ModelAndView deletar(@PathVariable(value = "uuid") UUID uuid) {
		cartaoCreditoRepository.delete(uuid);
		return new ModelAndView("redirect:/cartao-credito/mostrar");
	}

}
