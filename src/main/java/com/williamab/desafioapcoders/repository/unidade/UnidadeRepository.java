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
	 * Busca uma unidade pela identificação.
	 * 
	 * @param identificacao a identificação que será buscada
	 * @return a entidade encontrada
	 */
	Optional<UnidadeEntity> findByIdentificacao(String indentificacao);

}
