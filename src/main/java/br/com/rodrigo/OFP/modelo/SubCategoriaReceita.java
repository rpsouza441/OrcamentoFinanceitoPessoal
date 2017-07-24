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
public class SubCategoriaReceita {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@NotBlank
	private String nome;

	@ManyToOne
	@JoinColumn(name = "cat_Rec_Sup_id", foreignKey = @ForeignKey(name = "cat_Rec_Sup_FK"))
	private CategoriaReceita categoriaReceita;

	@OneToMany(mappedBy = "subCategoriaReceita")
	private List<Receita> receitas;

	public SubCategoriaReceita() {
	}

	public SubCategoriaReceita(UUID id, String nome, CategoriaReceita categoriaReceita, List<Receita> receitas) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoriaReceita = categoriaReceita;
		this.receitas = receitas;
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

	public CategoriaReceita getCategoriaReceita() {
		return categoriaReceita;
	}

	public void setCategoriaReceita(CategoriaReceita categoriaReceita) {
		this.categoriaReceita = categoriaReceita;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

}
