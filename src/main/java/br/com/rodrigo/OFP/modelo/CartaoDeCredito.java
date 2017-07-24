package br.com.rodrigo.OFP.modelo;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
public class CartaoDeCredito {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	@NotBlank
	private String nome;
	@Min(100)
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal limite = BigDecimal.ZERO;
	private BigDecimal limiteDisponivel= BigDecimal.ZERO;
	@Min(1)
	@Max(31)
	private int diaFechamento;
	@Min(1)
	@Max(31)
	private int diaPagamento;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(
	        name = "cartao_de_credito_despesa",
	        joinColumns = @JoinColumn(name = "cartao_id"),
	        inverseJoinColumns = @JoinColumn(name = "despesa_id")
	)
	private List<Despesa> despesas;

	@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name = "bandeira_id", foreignKey = @ForeignKey(name = "bandeira_FK"))
	@JoinTable(
	        name = "cartao_bandeira",
	        joinColumns = @JoinColumn(name = "cartao_id"),
	        inverseJoinColumns = @JoinColumn(name = "bandeira_id")
	)
	private Bandeira bandeira;
	
	@ManyToOne
	@JoinColumn(name = "usuario_ccredito_id")	
	private Usuario usuario;

	public CartaoDeCredito() {
	}

	public CartaoDeCredito(UUID id, String nome, BigDecimal limite, BigDecimal limiteDisponivel, int diaFechamento,
			int diaPagamento, Bandeira bandeira) {
		super();
		this.id = id;
		this.nome = nome;
		this.limite = limite;
		this.limiteDisponivel = limiteDisponivel;
		this.diaFechamento = diaFechamento;
		this.diaPagamento = diaPagamento;
		this.bandeira = bandeira;
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

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public BigDecimal getLimiteDisponivel() {
		return limiteDisponivel;
	}

	public void setLimiteDisponivel(BigDecimal limiteDisponivel) {
		this.limiteDisponivel = limiteDisponivel;
	}

	public int getDiaFechamento() {
		return diaFechamento;
	}

	public void setDiaFechamento(int diaFechamento) {
		this.diaFechamento = diaFechamento;
	}

	public int getDiaPagamento() {
		return diaPagamento;
	}

	public void setDiaPagamento(int diaPagamento) {
		this.diaPagamento = diaPagamento;
	}

	public Bandeira getBandeira() {
		return bandeira;
	}

	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}
	
	

}
