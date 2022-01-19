package com.williamab.desafioapcoders.model.unidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.williamab.desafioapcoders.model.BasicEntity;

/**
 * Representa um condomínio.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Entity
@Table(name = "condominio")
public class CondominioEntity extends BasicEntity {

	@Column(name = "codigo", nullable = false, unique = true)
	private Long codigo;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "endereco", nullable = false)
	private String endereco;

	public CondominioEntity() {
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
