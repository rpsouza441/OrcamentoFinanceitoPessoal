	package br.com.rodrigo.OFP.modelo;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Bandeira {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	@NotBlank
	private String nome;
	private String sumarioPath;
	
	@OneToMany
	@JoinTable(
	        name = "cartao_bandeira",
	        joinColumns = @JoinColumn(name = "bandeira_id"),
	        inverseJoinColumns = @JoinColumn(name = "cartao_id")
	)
	private List<CartaoDeCredito> cartoes;

	public Bandeira() {
	}

	public Bandeira(UUID id, String nome, String sumarioPath) {
		super();
		this.id = id;
		this.nome = nome;
		this.sumarioPath = sumarioPath;
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

	public String getSumarioPath() {
		return sumarioPath;
	}

	public void setSumarioPath(String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartoes == null) ? 0 : cartoes.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sumarioPath == null) ? 0 : sumarioPath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bandeira other = (Bandeira) obj;
		if (cartoes == null) {
			if (other.cartoes != null)
				return false;
		} else if (!cartoes.equals(other.cartoes))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sumarioPath == null) {
			if (other.sumarioPath != null)
				return false;
		} else if (!sumarioPath.equals(other.sumarioPath))
			return false;
		return true;
	}

	


}
