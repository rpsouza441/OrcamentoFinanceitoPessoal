package br.com.rodrigo.OFP.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

public interface Transacao {

	Calendar getDatapagamento();

	String getNome();

	BigDecimal getValor();

}
