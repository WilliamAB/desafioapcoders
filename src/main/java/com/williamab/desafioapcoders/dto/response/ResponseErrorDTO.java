package com.williamab.desafioapcoders.dto.response;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

/**
 * Representa os detalhes de um erro nas requisições do controller.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class ResponseErrorDTO {

	@NotNull(message = "Data e hora deve ser informada!")
	private LocalDateTime dataHora;

	@NotNull(message = "Detalhes deve ser informado!")
	private String detalhes;

	public ResponseErrorDTO() {
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

}
