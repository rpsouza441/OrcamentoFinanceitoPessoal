package br.com.rodrigo.OFP.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UK_por_email", columnNames= { "email" } ))
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator" )
	private UUID id;

	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	
	@DateTimeFormat
	private Calendar dataNascimento;
	private String senha;

	@OneToMany
	@JoinColumn(name = "usuario_conta_id", foreignKey =@ForeignKey(name="usuario_conta_FK"))
	private List<Conta> listaConta;

	@OneToMany
	@JoinColumn(name = "usuario_ccredito_id", foreignKey =@ForeignKey(name="usuario_ccredito_FK"))
	private List<CartaoDeCredito> ListaCartaoDeCredito;

	@OneToMany
	@JoinColumn(name = "usuario_des_id", foreignKey =@ForeignKey(name="usuario_des_FK"))
	private List<Despesa> listaDespesa;

	@OneToMany
	@JoinColumn(name = "usuario_rec_id", foreignKey =@ForeignKey(name="usuario_rec_FK"))
	private List<Receita> ListaReceita;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(
	        name = "usuario_role",
	        joinColumns = @JoinColumn(name = "usuario_id"),
	        inverseJoinColumns = @JoinColumn(name = "role_nome")
	)
	private List<Role> roles = new ArrayList<Role>();

	public Usuario() {
	}

	public Usuario(String email, String firstName, String lastName, Calendar dataNascimento, String senha,
			List<Role> roles) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
		this.roles = roles;
	}

	public Usuario(UUID id, String email, String firstName, String lastName, Calendar dataNascimento, String senha,
			List<Conta> listaConta, List<CartaoDeCredito> listaCartaoDeCredito, List<Despesa> listaDespesa,
			List<Receita> listaReceita, List<Role> roles) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
		this.listaConta = listaConta;
		ListaCartaoDeCredito = listaCartaoDeCredito;
		this.listaDespesa = listaDespesa;
		ListaReceita = listaReceita;
		this.roles = roles;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Conta> getListaConta() {
		return listaConta;
	}

	public void setListaConta(List<Conta> listaConta) {
		this.listaConta.addAll(listaConta);
	}

	public List<CartaoDeCredito> getListaCartaoDeCredito() {
		return ListaCartaoDeCredito;
	}

	public void setListaCartaoDeCredito(List<CartaoDeCredito> listaCartaoDeCredito) {
		ListaCartaoDeCredito.addAll(listaCartaoDeCredito);
	}

	public List<Despesa> getListaDespesa() {
		return listaDespesa;
	}

	public void setListaDespesa(List<Despesa> listaDespesa) {
		this.listaDespesa.addAll(listaDespesa);
	}

	public List<Receita> getListaReceita() {
		return ListaReceita;
	}

	public void setListaReceita(List<Receita> listaReceita) {
		this.ListaReceita.addAll(listaReceita);
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.senha;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
