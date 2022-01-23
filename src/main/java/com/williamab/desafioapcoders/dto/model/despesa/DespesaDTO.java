package com.williamab.desafioapcoders.dto.model.despesa;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import com.williamab.desafioapcoders.dto.model.BasicDTO;

/**
 * DTO (Data Transfer Object) para despesa.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class DespesaDTO extends BasicDTO<DespesaDTO> {

	@NotNull(message = "Código deve ser informado!")
	private Long codigo;

	@NotBlank(message = "Descrição deve ser informada!")
	private String descricao;

	@NotNull(message = "Tipo de despesa deve ser informada!")
	private Long tipoDespesaCodigo;

	@NotNull(message = "Valor deve ser informado!")
	@Positive(message = "Valor deve ser maior que zero!")
	private Double valor;

	@NotNull(message = "Vencimento da fatura deve ser informado!")
	private Date vencimentoFatura;

	@Pattern(regexp = "^(NAO_PAGO|PAGO)$", message = "O status de pagamento deve ser um dos seguintes: NAO_PAGO ou PAGO.")
	@NotBlank(message = "Status de pagamento deve ser informado!")
	private String statusPagamento;

	@NotNull(message = "Unidade deve ser informada!")
	private String unidadeIdentificacao;

	public DespesaDTO() {
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

	public Long getTipoDespesaCodigo() {
		return tipoDespesaCodigo;
	}

	public void setTipoDespesaCodigo(Long tipoDespesaCodigo) {
		this.tipoDespesaCodigo = tipoDespesaCodigo;
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

	public String getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(String statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public String getUnidadeIdentificacao() {
		return unidadeIdentificacao;
	}

	public void setUnidadeIdentificacao(String unidadeId) {
		this.unidadeIdentificacao = unidadeId;
	}

}
