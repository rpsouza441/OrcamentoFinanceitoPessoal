package br.com.rodrigo.OFP.modelo;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
public class Conta {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@Size(min = 2, max = 30)
	private String nome;
	private int agencia;
	private int conta;
	private int digito;
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal saldo = BigDecimal.ZERO;
	private String cor;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "conta_receita", joinColumns = @JoinColumn(name = "conta_id"), inverseJoinColumns = @JoinColumn(name = "receita_id"))
	private List<Receita> receitas;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "conta_despesa", joinColumns = @JoinColumn(name = "conta_id"), inverseJoinColumns = @JoinColumn(name = "despesa_id"))
	private List<Despesa> despesas;

	@ManyToOne
	@JoinColumn(name = "usuario_conta_id")
	private Usuario usuario;

	public Conta() {
	}

	public Conta(UUID id, String nome, int agencia, int conta, int digito, BigDecimal saldo, String cor) {
		super();
		this.id = id;
		this.nome = nome;
		this.agencia = agencia;
		this.conta = conta;
		this.digito = digito;
		this.saldo = saldo;
		this.cor = cor;
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

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public int getDigito() {
		return digito;
	}

	public void setDigito(int digito) {
		this.digito = digito;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}

}
