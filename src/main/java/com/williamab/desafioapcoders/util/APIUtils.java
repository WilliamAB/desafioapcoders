package com.williamab.desafioapcoders.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Classe utilit�ria com m�todos e constantes para a API.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public final class APIUtils {

	/**
	 * Define o limite m�ximo de resultados que uma p�gina deve gerar.
	 */
	public static final int PAGE_MAX_LIMIT = 20;

	private APIUtils() {
	}

	/**
	 * Cria um {@link Pageable} a partir do n�mero da p�gina e do limite de
	 * registros, com ordena��o padr�o por id. A pagina��o come�a em 0.
	 * 
	 * @param page  o n�mero da p�gina
	 * @param limit o limite de registros
	 * @return um {@link Pageable}
	 */
	public static Pageable createPageable(int page, int limit) {

		// O limite m�ximo de resultados por p�gina deve ser 20
		if (limit < 1 || limit > PAGE_MAX_LIMIT) {
			limit = PAGE_MAX_LIMIT;
		}

		return PageRequest.of(page, limit, Sort.by("id"));
	}

}
