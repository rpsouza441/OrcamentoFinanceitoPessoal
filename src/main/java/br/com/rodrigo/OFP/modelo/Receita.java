package br.com.rodrigo.OFP.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Receita implements Transacao {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@NotBlank
	private String nome;
	private BigDecimal valor = BigDecimal.ZERO;

	private String descricao;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar datapagamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "conta_receita", joinColumns = @JoinColumn(name = "receita_id"), inverseJoinColumns = @JoinColumn(name = "conta_id"))
	private Conta conta;

	@Transient
	private boolean vaiRepetir = Boolean.FALSE;
	@Transient
	private Periodo periodo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "sub_categoria_receita_FK"))
	private SubCategoriaReceita subCategoriaReceita;

	@ManyToOne
	@JoinColumn(name = "usuario_rec_id")
	private Usuario usuario;

	public Receita() {

	}

	public Receita(Receita r) {
		this.nome = r.getNome();
		this.valor = r.getValor();
		this.descricao = r.getDescricao();
		this.datapagamento = r.getDatapagamento();
		this.conta = r.getConta();
		this.vaiRepetir = r.isVaiRepetir();
		this.periodo = r.getPeriodo();
		this.subCategoriaReceita = r.getSubCategoriaReceita();

	}

	public Receita(UUID id, String nome, BigDecimal valor, String descricao, Calendar datapagamento, boolean vaiRepetir,
			Periodo periodo, SubCategoriaReceita subCategoriaReceita) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
		this.datapagamento = datapagamento;
		this.vaiRepetir = vaiRepetir;
		this.periodo = periodo;
		this.subCategoriaReceita = subCategoriaReceita;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getDatapagamento() {
		return datapagamento;
	}

	public void setDatapagamento(Calendar datapagamento) {
		this.datapagamento = datapagamento;
	}

	public boolean isVaiRepetir() {
		return vaiRepetir;
	}

	public void setVaiRepetir(boolean vaiRepetir) {
		this.vaiRepetir = vaiRepetir;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public SubCategoriaReceita getSubCategoriaReceita() {
		return subCategoriaReceita;
	}

	public void setSubCategoriaReceita(SubCategoriaReceita subCategoriaReceita) {
		this.subCategoriaReceita = subCategoriaReceita;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
