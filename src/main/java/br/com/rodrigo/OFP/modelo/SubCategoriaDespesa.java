package br.com.rodrigo.OFP.modelo;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class SubCategoriaDespesa {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@NotBlank
	private String nome;

	@ManyToOne
	@JoinColumn(name = "cat_Des_Sup_id", foreignKey = @ForeignKey(name = "cat_Des_Sup_FK"))
	private CategoriaDespesa categoriaDespesa;

	@OneToMany(mappedBy = "subCategoriaDespesa")
	private List<Despesa> despesas;

	public SubCategoriaDespesa(UUID id, String nome, CategoriaDespesa categoriaDespesa, List<Despesa> despesas) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoriaDespesa = categoriaDespesa;
		this.despesas = despesas;
	}

	public SubCategoriaDespesa() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CategoriaDespesa getCategoriaDespesa() {
		return categoriaDespesa;
	}

	public void setCategoriaDespesa(CategoriaDespesa categoriaDespesa) {
		this.categoriaDespesa = categoriaDespesa;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}

}
