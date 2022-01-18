package com.williamab.desafioapcoders.repository.unidade;

import java.util.Optional;

import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;
import com.williamab.desafioapcoders.repository.BasicRepository;

/**
 * Repositório para {@link UnidadeEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface UnidadeRepository extends BasicRepository<UnidadeEntity> {

	/**
	 * Busca uma unidade pelo código.
	 * 
	 * @param codigo o código que será buscado
	 * @return a entidade encontrada ou {@literal Optional#empty()}
	 */
	Optional<UnidadeEntity> findByCodigo(Long codigo);

}
