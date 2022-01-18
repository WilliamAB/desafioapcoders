package com.williamab.desafioapcoders.repository.despesa;

import java.util.Optional;

import com.williamab.desafioapcoders.model.despesa.TipoDespesaEntity;
import com.williamab.desafioapcoders.repository.BasicRepository;

/**
 * Reposit�rio para {@link TipoDespesaEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface TipoDespesaRepository extends BasicRepository<TipoDespesaEntity> {

	/**
	 * Busca um tipo de despesa pelo c�digo.
	 * 
	 * @param codigo o c�digo que ser� buscado
	 * @return a entidade encontrada ou {@literal Optional#empty()}
	 */
	Optional<TipoDespesaEntity> findByCodigo(Long codigo);

}
