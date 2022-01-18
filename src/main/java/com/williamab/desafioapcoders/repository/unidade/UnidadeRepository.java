package com.williamab.desafioapcoders.repository.unidade;

import java.util.Optional;

import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;
import com.williamab.desafioapcoders.repository.BasicRepository;

/**
 * Reposit�rio para {@link UnidadeEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface UnidadeRepository extends BasicRepository<UnidadeEntity> {

	/**
	 * Busca uma unidade pelo c�digo.
	 * 
	 * @param codigo o c�digo que ser� buscado
	 * @return a entidade encontrada ou {@literal Optional#empty()}
	 */
	Optional<UnidadeEntity> findByCodigo(Long codigo);

}
