package br.com.rodrigo.OFP.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.rodrigo.OFP.modelo.Conta;
import br.com.rodrigo.OFP.modelo.Receita;
import br.com.rodrigo.OFP.modelo.SubCategoriaReceita;

public class ReceitaBuilder {

	private Receita receita;

	public ReceitaBuilder(Receita receita) {
		this.receita = receita;
		verificarSeValorEhPositivo();
	}
	
	private void verificarSeValorEhPositivo() {
		if (receita.getValor().signum() < 0) {
			receita.setValor(receita.getValor().abs());
		}
		
	}


	public ReceitaBuilder comConta(List<Conta> contas) {
		for (Conta conta : contas) {
			if (receita.getConta().getId().equals(conta.getId())) {
				receita.setConta(conta);
			}
		}

		return this;

	}

	private void descontaSaldoDeConta(BigDecimal valor) {
		receita.getConta().setSaldo(receita.getConta().getSaldo().add(valor));
	}

	public ReceitaBuilder comCategoria(List<SubCategoriaReceita> categorias) {

		for (SubCategoriaReceita c1 : categorias) {
			if (receita.getSubCategoriaReceita().getId().equals(c1.getId())) {
				receita.setSubCategoriaReceita(c1);
			}
		}

		return this;
	}

	private void comPeriodo() {
		List<Receita> receitas = new ArrayList<Receita>();
		if (receita.getPeriodo() == null) {
			receitas.add(receita);
			descontaSaldoDeConta(receita.getValor());
		} else {
			
			int periodicidade = 0;
			for (int i = 0; i < receita.getPeriodo().getQuantidade(); i++) {
				Receita receitaTemp = new Receita(receita);
				receitaTemp.setNome(
						receitaTemp.getNome() + " (" + (i+1) + "/" + receita.getPeriodo().getQuantidade() + ")");
				Calendar data =(Calendar) receita.getDatapagamento().clone();
				data.add(Calendar.DATE, periodicidade);
				
				receitaTemp.setDatapagamento(data);
				descontaSaldoDeConta(receita.getValor());
				receitas.add(receitaTemp);
				periodicidade+=receita.getPeriodo().getPeriodicidade();

			}

		}
		receita.getConta().setReceitas(receitas);

	}

	public Receita constroi() {
		comPeriodo();

		return receita;

	}

}
