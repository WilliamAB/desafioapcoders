package com.williamab.desafioapcoders.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Classe utilitária com métodos e constantes para a API.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public final class APIUtils {

	/**
	 * Define o limite máximo de resultados que uma página deve gerar.
	 */
	public static final int PAGE_MAX_LIMIT = 20;

	private APIUtils() {
	}

	/**
	 * Cria um {@link Pageable} a partir do número da página e do limite de
	 * registros, com ordenação padrão por id. A paginação começa em 0.
	 * 
	 * @param page  o número da página
	 * @param limit o limite de registros
	 * @return um {@link Pageable}
	 */
	public static Pageable createPageable(int page, int limit) {

		// O limite máximo de resultados por página deve ser 20
		if (limit < 1 || limit > PAGE_MAX_LIMIT) {
			limit = PAGE_MAX_LIMIT;
		}

		return PageRequest.of(page, limit, Sort.by("id"));
	}

}
