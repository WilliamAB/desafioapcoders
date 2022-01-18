package com.williamab.desafioapcoders.dto.model.unidade;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.williamab.desafioapcoders.dto.model.BasicDTO;

/**
 * DTO (Data Transfer Object) para unidade.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class UnidadeDTO extends BasicDTO<UnidadeDTO> {

	@NotBlank(message = "Identificação deve ser informada!")
	private String identificacao;

	@NotNull(message = "Proprietário deve ser informado!")
	private Long proprietarioId;

	@NotNull(message = "Condomínio deve ser informado!")
	private Long condominioId;

	public UnidadeDTO() {
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public Long getProprietarioId() {
		return proprietarioId;
	}

	public void setProprietarioId(Long proprietarioId) {
		this.proprietarioId = proprietarioId;
	}

	public Long getCondominioId() {
		return condominioId;
	}

	public void setCondominioId(Long condominioId) {
		this.condominioId = condominioId;
	}

}
