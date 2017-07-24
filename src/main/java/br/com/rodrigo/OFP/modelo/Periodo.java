package br.com.rodrigo.OFP.modelo;

public class Periodo {
	
	private int quantidade;
	private int periodicidade;

	public Periodo() {
	}

	public Periodo(int quantidade, int periodicidade) {
		super();
		this.quantidade = quantidade;
		this.periodicidade = periodicidade;
	}


	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(int periodicidade) {
		this.periodicidade = periodicidade;
	}


}
