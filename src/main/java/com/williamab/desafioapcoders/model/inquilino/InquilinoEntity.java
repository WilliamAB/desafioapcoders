package com.williamab.desafioapcoders.model.inquilino;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.williamab.desafioapcoders.model.BasicEntity;
import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;

/**
 * Representa um inquilino.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Entity
@Table(name = "inquilino")
public class InquilinoEntity extends BasicEntity {

	@Column(name = "codigo", nullable = false, unique = true)
	private Long codigo;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_nascimento", nullable = false)
	private Date dataNascimento;

	@Enumerated(EnumType.STRING)
	@Column(name = "sexo", nullable = false)
	private Sexo sexo;

	@Column(name = "telefone", nullable = false)
	private String telefone;

	@Column(name = "email", nullable = true)
	private String email;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "proprietario")
	private List<UnidadeEntity> unidades;

	public InquilinoEntity() {
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UnidadeEntity> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<UnidadeEntity> unidades) {
		this.unidades = unidades;
	}

}
