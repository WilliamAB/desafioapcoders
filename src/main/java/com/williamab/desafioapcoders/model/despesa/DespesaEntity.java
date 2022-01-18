package com.williamab.desafioapcoders.model.despesa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.williamab.desafioapcoders.model.BasicEntity;
import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;

/**
 * Representa uma despesa de uma unidade.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Entity
@Table(name = "despesa")
public class DespesaEntity extends BasicEntity {

	@Column(name = "codigo", nullable = false)
	private Long codigo;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipo_despesa_id", nullable = false)
	private TipoDespesaEntity tipoDespesa;

	@Column(name = "valor", nullable = false)
	private Double valor;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "vencimento_fatura", nullable = false)
	private Date vencimentoFatura;

	@Enumerated(EnumType.STRING)
	@Column(name = "status_pagamento", nullable = false)
	private StatusPagamento statusPagamento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unidade_id", nullable = false)
	private UnidadeEntity unidade;

	public DespesaEntity() {
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

	public TipoDespesaEntity getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesaEntity tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getVencimentoFatura() {
		return vencimentoFatura;
	}

	public void setVencimentoFatura(Date vencimentoFatura) {
		this.vencimentoFatura = vencimentoFatura;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public UnidadeEntity getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeEntity unidade) {
		this.unidade = unidade;
	}

}
