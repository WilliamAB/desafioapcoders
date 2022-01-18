package com.williamab.desafioapcoders.repository.inquilino;

import java.util.Optional;

import com.williamab.desafioapcoders.model.inquilino.InquilinoEntity;
import com.williamab.desafioapcoders.repository.BasicRepository;

/**
 * Reposit�rio para {@link InquilinoEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface InquilinoRepository extends BasicRepository<InquilinoEntity> {

	/**
	 * Busca um inquilino pelo c�digo.
	 * 
	 * @param codigo o c�digo que ser� buscado
	 * @return a entidade encontrada ou {@literal Optional#empty()}
	 */
	Optional<InquilinoEntity> findByCodigo(Long codigo);

}
