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
public class CategoriaDespesa {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@NotBlank
	private String nome;

	@OneToMany(mappedBy = "categoriaDespesa")
	private List<SubCategoriaDespesa> ListaSubCategoriaDespesa;

	public CategoriaDespesa() {
	}

	public CategoriaDespesa(UUID id, String nome, List<SubCategoriaDespesa> listaSubCategoriaDespesa) {
		super();
		this.id = id;
		this.nome = nome;
		ListaSubCategoriaDespesa = listaSubCategoriaDespesa;
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

	public List<SubCategoriaDespesa> getListaSubCategoriaDespesa() {
		return ListaSubCategoriaDespesa;
	}

	public void setListaSubCategoriaDespesa(List<SubCategoriaDespesa> listaSubCategoriaDespesa) {
		ListaSubCategoriaDespesa = listaSubCategoriaDespesa;
	}

}
