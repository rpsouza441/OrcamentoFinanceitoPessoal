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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Despesa implements Transacao{

	@Id
	@Column(columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator" )
	private UUID id;

	@NotBlank
	private String nome;
	
	private BigDecimal valor  = BigDecimal.ZERO;

	private String descricao;

	@DateTimeFormat
	private Calendar datapagamento;
	
	@Transient
	private boolean vaiRepetir;

	@Transient
	private Periodo periodo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "sub_categoria_despesa_FK"))
	private SubCategoriaDespesa subCategoriaDespesa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(
	        name = "conta_despesa",
	        joinColumns = @JoinColumn(name = "despesa_id"),
	        inverseJoinColumns = @JoinColumn(name = "conta_id")
	)
	private Conta conta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(
	        name = "cartao_de_credito_despesa",
	        joinColumns = @JoinColumn(name = "despesa_id"),
	        inverseJoinColumns = @JoinColumn(name = "cartao_id")
	)
	private CartaoDeCredito cartaoDeCredito;
	
	
	
	@ManyToOne
	@JoinColumn(name = "usuario_des_id")
	private Usuario usuario;
	

	public Despesa() {

	}
	public Despesa(Despesa d){
		this.nome=d.getNome();
		this.valor= d.getValor();
		this.descricao=d.getDescricao();
		this.datapagamento=d.getDatapagamento();
		this.vaiRepetir=d.isVaiRepetir();
		this.periodo=d.getPeriodo();
		this.subCategoriaDespesa=d.getSubCategoriaDespesa();
		this.conta=d.getConta();
		this.cartaoDeCredito=d.getCartaoDeCredito();
	}

	public Despesa(UUID id, String nome, BigDecimal valor, String descricao, Calendar datapagamento, boolean vaiRepetir,
			Periodo periodo, SubCategoriaDespesa subCategoriaDespesa) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
		this.datapagamento = datapagamento;
		this.vaiRepetir = vaiRepetir;
		this.periodo = periodo;
		this.subCategoriaDespesa = subCategoriaDespesa;
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

	public SubCategoriaDespesa getSubCategoriaDespesa() {
		return subCategoriaDespesa;
	}

	public void setSubCategoriaDespesa(SubCategoriaDespesa subCategoriaDespesa) {
		this.subCategoriaDespesa = subCategoriaDespesa;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public CartaoDeCredito getCartaoDeCredito() {
		return cartaoDeCredito;
	}

	public void setCartaoDeCredito(CartaoDeCredito cartaoDeCredito) {
		this.cartaoDeCredito = cartaoDeCredito;
	}
	
	

}
