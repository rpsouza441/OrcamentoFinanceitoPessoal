package br.com.rodrigo.OFP.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.rodrigo.OFP.modelo.Despesa;
import br.com.rodrigo.OFP.modelo.Receita;
import br.com.rodrigo.OFP.modelo.Usuario;
import br.com.rodrigo.OFP.repository.ContaRepository;
import br.com.rodrigo.OFP.repository.DespesaRepository;
import br.com.rodrigo.OFP.repository.ReceitaRepository;
import br.com.rodrigo.OFP.repository.UsuarioRepository;

@Controller
public class HomeController {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private DespesaRepository despesaRepository;

	@Autowired
	private ReceitaRepository receitaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	private List<BigDecimal> saldosReceita;

	private List<BigDecimal> saldosDespesa;

	private List<Integer> diasSemana;

	private BigDecimal totalReceita;

	private BigDecimal totalDespesa;

	private Usuario usuario;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView modelAndView) {
		ultimosSeteDias();
		totalDosUltimos30Dias();
		preencheUsuario();
		modelAndView = new ModelAndView("/home");
		modelAndView.addObject("contas", contaRepository.findByUsuario(usuario));
		modelAndView.addObject("saldosReceita", saldosReceita);
		modelAndView.addObject("saldosDespesa", saldosDespesa);
		modelAndView.addObject("diasSemana", diasSemana);
		modelAndView.addObject("totalReceita", totalReceita);
		modelAndView.addObject("totalDespesa", totalDespesa);
		

		return modelAndView;
	}
	
	
	private void totalDosUltimos30Dias(){
		preencheUsuario();
		
		Calendar start = Calendar.getInstance();
		start.add(Calendar.DAY_OF_MONTH, -30);
		Calendar end = Calendar.getInstance();

		List<Despesa> despesas = despesaRepository.findByDatapagamentoBetweenAndUsuario(start, end, usuario);
		List<Receita> receitas = receitaRepository.findByDatapagamentoBetweenAndUsuario(start, end, usuario);
		totalReceita = BigDecimal.ZERO;
		totalDespesa = BigDecimal.ZERO;
		for (Receita receita : receitas) {
			totalReceita = totalReceita.add(receita.getValor());
		}
		
		for (Despesa despesa : despesas) {
			totalDespesa=totalDespesa.add(despesa.getValor().abs());
		}
		
		
		
	}


	private void preencheUsuario() {
		Usuario principal = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		usuario = usuarioRepository.findOne(principal.getId());
	}
	

	@SuppressWarnings("unused")
	private void ultimosSeteDias() {
		Calendar start = Calendar.getInstance();
		start.add(Calendar.DAY_OF_MONTH, -7);
		Calendar end = Calendar.getInstance();
		preencheUsuario();

		List<Despesa> despesas = despesaRepository.findByDatapagamentoBetweenAndUsuario(start, end, usuario);
		List<Receita> receitas = receitaRepository.findByDatapagamentoBetweenAndUsuario(start, end, usuario);
		saldosReceita = new ArrayList<>();
		saldosDespesa = new ArrayList<>();
		diasSemana = new ArrayList<>();
		BigDecimal valorReceita = BigDecimal.ZERO;
		BigDecimal valorDespesa = BigDecimal.ZERO;

		for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
			for (Despesa despesa : despesas) {
				if (despesa.getDatapagamento().get(Calendar.DAY_OF_WEEK) == start.get(Calendar.DAY_OF_WEEK)) {
					valorDespesa = valorDespesa.add(despesa.getValor().abs());
				}
			}
			for (Receita receita : receitas) {

				if (receita.getDatapagamento().get(Calendar.DAY_OF_WEEK) == start.get(Calendar.DAY_OF_WEEK)) {
					valorReceita = valorReceita.add(receita.getValor());
				}
			}

			saldosReceita.add(valorReceita);
			saldosDespesa.add(valorDespesa);
			diasSemana.add(start.get(Calendar.DAY_OF_WEEK));
			valorReceita = BigDecimal.ZERO;
			valorDespesa = BigDecimal.ZERO;
		}


	}

}
