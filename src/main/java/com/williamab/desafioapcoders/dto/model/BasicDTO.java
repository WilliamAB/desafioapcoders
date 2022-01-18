package com.williamab.desafioapcoders.dto.model;

import org.springframework.hateoas.RepresentationModel;

/**
 * DTO (Data Transfer Object) de base para os DTOs da API que representarão
 * entidades.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 * @param T o tipo da classe que está herdando {@link BasicDTO}
 */
public abstract class BasicDTO<T extends BasicDTO<T>> extends RepresentationModel<T> {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
