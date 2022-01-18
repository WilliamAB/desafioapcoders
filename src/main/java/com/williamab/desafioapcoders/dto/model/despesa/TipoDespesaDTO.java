package com.williamab.desafioapcoders.dto.model.despesa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.williamab.desafioapcoders.dto.model.BasicDTO;

/**
 * DTO (Data Transfer Object) para tipo de despesa.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class TipoDespesaDTO extends BasicDTO<TipoDespesaDTO> {

	@NotNull(message = "Código deve ser informado!")
	private Long codigo;

	@NotBlank(message = "Descrição deve ser informada!")
	private String descricao;

	public TipoDespesaDTO() {
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
