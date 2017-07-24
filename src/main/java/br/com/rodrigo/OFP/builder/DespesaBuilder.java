package br.com.rodrigo.OFP.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.rodrigo.OFP.modelo.CartaoDeCredito;
import br.com.rodrigo.OFP.modelo.Conta;
import br.com.rodrigo.OFP.modelo.Despesa;
import br.com.rodrigo.OFP.modelo.SubCategoriaDespesa;

public class DespesaBuilder {

	private Despesa despesa;

	public DespesaBuilder(Despesa despesa) {
		this.despesa = despesa;
		verificaSeValorEhNegativo();
	}

	public DespesaBuilder comConta(List<Conta> contas) {
		for (Conta conta : contas) {
			if (despesa.getConta().getId().equals(conta.getId())) {
				despesa.setConta(conta);
			}
		}

		return this;

	}

	public DespesaBuilder comCartao(List<CartaoDeCredito> cartoes) {
		for (CartaoDeCredito cartao : cartoes) {
			if (despesa.getCartaoDeCredito().getId().equals(cartao.getId())) {
				despesa.setCartaoDeCredito(cartao);
			}
		}
		return this;
	}

	public DespesaBuilder comCategoria(List<SubCategoriaDespesa> categorias) {

		for (SubCategoriaDespesa c1 : categorias) {
			if (despesa.getSubCategoriaDespesa().getId().equals(c1.getId())) {
				despesa.setSubCategoriaDespesa(c1);
			}
		}

		return this;
	}

	private void comPeriodoEmConta() {
		List<Despesa> despesas = new ArrayList<Despesa>();
		if (despesa.isVaiRepetir()) {
			int periodicidade = 0;
			for (int i = 0; i < despesa.getPeriodo().getQuantidade(); i++) {
				Despesa despesaTemp = new Despesa(despesa);
				despesaTemp.setNome(
						despesaTemp.getNome() + " (" + (i + 1) + "/" + despesa.getPeriodo().getQuantidade() + ")");
				Calendar data = (Calendar) despesa.getDatapagamento().clone();
				data.add(Calendar.DATE, periodicidade);

				despesaTemp.setDatapagamento(data);
				descontaSaldoDeConta(despesa.getValor());
				despesas.add(despesaTemp);
				periodicidade += despesa.getPeriodo().getPeriodicidade();

			}
		} else {

			despesas.add(despesa);
			descontaSaldoDeConta(despesa.getValor());

		}
		despesa.getConta().setDespesas(despesas);

	}

	private void comPeriodoEmCartao() {
		List<Despesa> despesas = new ArrayList<Despesa>();
		if (despesa.isVaiRepetir()) {
			int periodicidade = 0;
			long quantidade =despesa.getPeriodo().getQuantidade();
			BigDecimal parcelas = BigDecimal.valueOf(quantidade);;
			
			BigDecimal valorParcelado=despesa.getValor().divide(parcelas);
			for (int i = 0; i < despesa.getPeriodo().getQuantidade(); i++) {
				Despesa despesaTemp = new Despesa(despesa);
				despesaTemp.setNome(
						despesaTemp.getNome() + " (" + (i + 1) + "/" + despesa.getPeriodo().getQuantidade() + ")");
				Calendar data = (Calendar) despesa.getDatapagamento().clone();
				data.add(Calendar.DATE, periodicidade);
				despesaTemp.setValor(valorParcelado);
				despesaTemp.setDatapagamento(data);
				descontaLimiteDisponivel(despesaTemp.getValor());
				despesas.add(despesaTemp);
				periodicidade += despesa.getPeriodo().getPeriodicidade();
			}
		} else {

			despesas.add(despesa);
			descontaLimiteDisponivel(despesa.getValor());

		}
		despesa.getCartaoDeCredito().setDespesas(despesas);

	}

	public Despesa constroi() {
		if (despesa.getCartaoDeCredito() != null) {
			comPeriodoEmCartao();
		} else if (despesa.getConta() != null) {
			comPeriodoEmConta();
		}
		return despesa;
	}

	private void descontaLimiteDisponivel(BigDecimal valor) {
		despesa.getCartaoDeCredito().setLimiteDisponivel(despesa.getCartaoDeCredito().getLimiteDisponivel().add(valor));

	}

	private void descontaSaldoDeConta(BigDecimal valor) {
		despesa.getConta().setSaldo(despesa.getConta().getSaldo().add(valor));
	}

	private void verificaSeValorEhNegativo() {
		if (despesa.getValor().signum() > 0) {
			despesa.setValor(despesa.getValor().negate());
		}

	}

}
