package br.com.rodrigo.OFP.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {


	private static final long serialVersionUID = 1L;

	@Id
	@NotBlank
	private String nome;
	
//	@ManyToOne
//	@JoinTable(
//	        name = "usuario_role",
//	        joinColumns = @JoinColumn(name = "role_nome"),
//	        inverseJoinColumns = @JoinColumn(name = "usuario_id")
//	)
//	private Usuario usuario;

	public Role() {
	}

	public Role(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Role other = (Role) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String getAuthority() {
		return this.nome;
	}
}
