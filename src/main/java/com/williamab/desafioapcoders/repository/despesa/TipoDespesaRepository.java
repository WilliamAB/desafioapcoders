package com.williamab.desafioapcoders.repository.despesa;

import java.util.Optional;

import com.williamab.desafioapcoders.model.despesa.TipoDespesaEntity;
import com.williamab.desafioapcoders.repository.BasicRepository;

/**
 * Repositório para {@link TipoDespesaEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface TipoDespesaRepository extends BasicRepository<TipoDespesaEntity> {

	/**
	 * Busca um tipo de despesa pelo código.
	 * 
	 * @param codigo o código que será buscado
	 * @return a entidade encontrada ou {@literal Optional#empty()}
	 */
	Optional<TipoDespesaEntity> findByCodigo(Long codigo);

}
