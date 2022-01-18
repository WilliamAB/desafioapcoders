package com.williamab.desafioapcoders.repository.inquilino;

import java.util.Optional;

import com.williamab.desafioapcoders.model.inquilino.InquilinoEntity;
import com.williamab.desafioapcoders.repository.BasicRepository;

/**
 * Repositório para {@link InquilinoEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface InquilinoRepository extends BasicRepository<InquilinoEntity> {

	/**
	 * Busca um inquilino pelo código.
	 * 
	 * @param codigo o código que será buscado
	 * @return a entidade encontrada ou {@literal Optional#empty()}
	 */
	Optional<InquilinoEntity> findByCodigo(Long codigo);

}
