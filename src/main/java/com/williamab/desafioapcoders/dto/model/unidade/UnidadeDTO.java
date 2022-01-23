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
	private Long proprietarioCodigo;

	@NotNull(message = "Condomínio deve ser informado!")
	private Long condominioCodigo;

	public UnidadeDTO() {
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public Long getProprietarioCodigo() {
		return proprietarioCodigo;
	}

	public void setProprietarioCodigo(Long proprietarioCodigo) {
		this.proprietarioCodigo = proprietarioCodigo;
	}

	public Long getCondominioCodigo() {
		return condominioCodigo;
	}

	public void setCondominioCodigo(Long condominioCodigo) {
		this.condominioCodigo = condominioCodigo;
	}

}
