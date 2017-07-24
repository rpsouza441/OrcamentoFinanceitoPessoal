package br.com.rodrigo.OFP.validation;

import org.springframework.validation.BindingResult;

import br.com.rodrigo.OFP.modelo.Despesa;

public class DespesaValidation {
	
	private Despesa despesa;
	private BindingResult result;

	public DespesaValidation(Despesa despesa, BindingResult result) {
		this.despesa = despesa;
		this.result = result;
		
	}
	
	public BindingResult verificaComConta(){
		verificaSeValorEhZero();
		verificaRequiredFieldsDePeriodo();
		verificaSeCategoriaEhNullo();
		verificaSeContaEhNullo();
		return result;
	}
	
	public BindingResult verificaComCartaoCredito(){
		verificaSeValorEhZero();
		verificaRequiredFieldsDePeriodo();
		verificaSeCategoriaEhNullo();
		verificaSeCartaoEhNullo();
		return result;
	}
	
	private void verificaSeValorEhZero() {
		if (despesa.getValor().intValue() == 0) {
			result.rejectValue("valor", "field.required.despesa");
		}
	}
	
	private void verificaRequiredFieldsDePeriodo() {
		if (!despesa.isVaiRepetir()) {
			despesa.setPeriodo(null);
		} else if (despesa.getPeriodo().getQuantidade() == 0) {
			result.rejectValue("periodo.quantidade", "field.required.despesa");

		}

	}
	
	private void verificaSeCategoriaEhNullo() {
		if (despesa.getSubCategoriaDespesa() == null) {
			result.rejectValue("subCategoriaDespesa", "field.required.despesa");
		}
	}
	
	private void verificaSeContaEhNullo() {
		if (despesa.getConta() == null) {
			result.rejectValue("conta", "field.required.despesa");
		}
	}
	private void verificaSeCartaoEhNullo() {
		if (despesa.getCartaoDeCredito() == null) {
			result.rejectValue("cartaoDeCredito", "field.required.despesa");
		}
	}
	
	

}
