package br.com.rodrigo.OFP.modelo;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class CategoriaReceita {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@NotBlank
	private String nome;

	@OneToMany(mappedBy = "categoriaReceita")
	private List<SubCategoriaReceita> listaSubCategoriaReceita;

	public CategoriaReceita() {
	}

	public CategoriaReceita(UUID id, String nome, List<SubCategoriaReceita> listaSubCategoriaReceita) {
		super();
		this.id = id;
		this.nome = nome;
		this.listaSubCategoriaReceita = listaSubCategoriaReceita;
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

	public List<SubCategoriaReceita> getListaSubCategoriaReceita() {
		return listaSubCategoriaReceita;
	}

	public void setListaSubCategoriaReceita(List<SubCategoriaReceita> listaSubCategoriaReceita) {
		this.listaSubCategoriaReceita = listaSubCategoriaReceita;
	}

}
