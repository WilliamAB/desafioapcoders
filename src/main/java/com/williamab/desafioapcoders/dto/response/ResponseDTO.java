package com.williamab.desafioapcoders.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Estrutura genérica de resposta para as requisições da aplicação.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 * 
 * @param T o tipo do dado da resposta
 */
public class ResponseDTO<T> {

	private T data;
	private List<ResponseErrorDTO> errors = new ArrayList<>();

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<ResponseErrorDTO> getErrors() {
		return errors;
	}

	public void addError(ResponseErrorDTO error) {
		errors.add(error);
	}

	public void addError(String msg) {
		ResponseErrorDTO error = new ResponseErrorDTO();
		error.setDataHora(LocalDateTime.now());
		error.setDetalhes(msg);
		addError(error);
	}

}
