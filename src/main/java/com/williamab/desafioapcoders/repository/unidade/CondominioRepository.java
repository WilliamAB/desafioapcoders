package com.williamab.desafioapcoders.repository.unidade;

import java.util.Optional;

import com.williamab.desafioapcoders.model.unidade.CondominioEntity;
import com.williamab.desafioapcoders.repository.BasicRepository;

/**
 * Repositório para {@link CondominioEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface CondominioRepository extends BasicRepository<CondominioEntity> {

	/**
	 * Busca um condomínio pelo código.
	 * 
	 * @param codigo o código que será buscado
	 * @return a entidade encontrada ou {@literal Optional#empty()}
	 */
	Optional<CondominioEntity> findByCodigo(Long codigo);

}
