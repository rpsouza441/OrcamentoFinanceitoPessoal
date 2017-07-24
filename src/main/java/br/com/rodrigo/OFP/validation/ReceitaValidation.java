package br.com.rodrigo.OFP.validation;

import org.springframework.validation.BindingResult;

import br.com.rodrigo.OFP.modelo.Receita;

public class ReceitaValidation {

	private Receita receita;

	public BindingResult getResult() {
		return result;
	}

	private BindingResult result;

	public ReceitaValidation(Receita receita, BindingResult result) {
		this.receita = receita;
		this.result = result;
		verificaRequiredFieldsDePeriodo();
		verificaSeCategoriaEhNullo();
		verificaSeContaEhNullo();
		verificaSeValorEhZero();
	}

	private void verificaSeContaEhNullo() {
		if (receita.getConta() == null) {
			result.rejectValue("conta", "field.required.receita");
		}
	}

	private void verificaSeCategoriaEhNullo() {
		if (receita.getSubCategoriaReceita() == null) {
			result.rejectValue("subCategoriaReceita", "field.required.receita");
		}
	}

	private void verificaSeValorEhZero() {
		if (receita.getValor().intValue() == 0) {
			result.rejectValue("valor", "field.required.receita");
		}
	}

	private void verificaRequiredFieldsDePeriodo() {
		if (!receita.isVaiRepetir()) {
			receita.setPeriodo(null);
		} else if (receita.getPeriodo().getQuantidade() == 0) {
			result.rejectValue("periodo.quantidade", "field.required.receita");

		}

	}

}
