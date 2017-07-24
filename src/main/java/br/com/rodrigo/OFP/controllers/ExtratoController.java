package br.com.rodrigo.OFP.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.rodrigo.OFP.modelo.Despesa;
import br.com.rodrigo.OFP.modelo.Receita;
import br.com.rodrigo.OFP.modelo.Transacao;
import br.com.rodrigo.OFP.modelo.Usuario;
import br.com.rodrigo.OFP.repository.DespesaRepository;
import br.com.rodrigo.OFP.repository.ReceitaRepository;
import br.com.rodrigo.OFP.repository.UsuarioRepository;

@Controller
@RequestMapping("/extrato")
public class ExtratoController {

	@Autowired
	private DespesaRepository despesaRepository;

	@Autowired
	private ReceitaRepository receitaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private List<Transacao> transacoes;

	private Page<Despesa> pageDespesa;

	private Page<Receita> pageReceita;

	private Usuario usuario;


	@RequestMapping(value = "/mostrar")
	public ModelAndView mostrar(@RequestParam(value = "search", required = false) String search,
			@PageableDefault(page = 0, value = 3, sort = "Datapagamento", direction = Direction.ASC) Pageable pageable,
			Model model) {
		preencherUsuario();
		ModelAndView modelAndView = new ModelAndView("/view/extrato");
		if (search == null) {
			transacoes = new ArrayList<Transacao>();
			pageDespesa = despesaRepository.findByUsuario(pageable, usuario);
			pageReceita = receitaRepository.findByUsuario(pageable, usuario);
			preencheEOrdenaLista();
		} else {
			transacoes = new ArrayList<Transacao>();
			pageDespesa = despesaRepository.findByNomeContainingAndUsuario(search, pageable, usuario);
			pageReceita = receitaRepository.findByNomeContainingAndUsuario(search, pageable, usuario);
			preencheEOrdenaLista();
			// modelAndView.addObject("contas",
			// contaRepository.findByNomeContaining(search));
		}
		modelAndView.addObject("transacoes", transacoes);
		modelAndView.addObject("page", pageDespesa);
		modelAndView.addObject("search", search);

		return modelAndView;
	}

	@RequestMapping(value = "/editar/receita/{uuid}")
	public ModelAndView editarReceita(@PathVariable(value = "uuid") UUID uuid) {
		return new ModelAndView("redirect:/receita/editar/{uuid}");
	}

	@RequestMapping(value = "/editar/despesa/{uuid}")
	public ModelAndView editarDespesa(@PathVariable(value = "uuid") UUID uuid) {
		return new ModelAndView("redirect:/despesa/editar/{uuid}");
	}

	@RequestMapping(value = "/deletar/receita/{uuid}")
	public ModelAndView deletarReceita(@PathVariable(value = "uuid") UUID uuid) {
		receitaRepository.delete(uuid);
		return new ModelAndView("redirect:/extrato/mostrar");
	}

	@RequestMapping(value = "/deletar/despesa/{uuid}")
	public ModelAndView deletarDespesa(@PathVariable(value = "uuid") UUID uuid) {
		despesaRepository.delete(uuid);
		return new ModelAndView("redirect:/extrato/mostrar");
	}

	private void preencheEOrdenaLista() {
		transacoes.addAll(pageDespesa.getContent());
		transacoes.addAll(pageReceita.getContent());

		transacoes.sort((t1, t2) -> t1.getDatapagamento().compareTo(t2.getDatapagamento()));

	}

	private void preencherUsuario() {
		Usuario principal = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		usuario = usuarioRepository.findOne(principal.getId());
	}


}
