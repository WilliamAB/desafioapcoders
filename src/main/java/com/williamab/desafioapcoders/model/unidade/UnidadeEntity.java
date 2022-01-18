package com.williamab.desafioapcoders.model.unidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.williamab.desafioapcoders.model.BasicEntity;
import com.williamab.desafioapcoders.model.inquilino.InquilinoEntity;

/**
 * Representa uma unidade de um condomínio.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Entity
@Table(name = "unidade")
public class UnidadeEntity extends BasicEntity {

	@Column(name = "identificacao", nullable = false)
	private String identificacao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "proprietario_id", nullable = false)
	private InquilinoEntity proprietario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "condominio_id", nullable = false)
	private CondominioEntity condominio;

	public UnidadeEntity() {
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public InquilinoEntity getProprietario() {
		return proprietario;
	}

	public void setProprietario(InquilinoEntity proprietario) {
		this.proprietario = proprietario;
	}

	public CondominioEntity getCondominio() {
		return condominio;
	}

	public void setCondominio(CondominioEntity condominio) {
		this.condominio = condominio;
	}

}
