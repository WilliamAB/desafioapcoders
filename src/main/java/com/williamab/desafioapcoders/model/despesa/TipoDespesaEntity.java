package com.williamab.desafioapcoders.model.despesa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.williamab.desafioapcoders.model.BasicEntity;

/**
 * Representa um tipo de despesa.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Entity
@Table(name = "tipo_despesa")
public class TipoDespesaEntity extends BasicEntity {

	@Column(name = "codigo", nullable = false, unique = true)
	private Long codigo;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	public TipoDespesaEntity() {
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
