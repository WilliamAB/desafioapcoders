package com.williamab.desafioapcoders.service.unidade;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;

import com.williamab.desafioapcoders.model.unidade.CondominioEntity;

/**
 * Servi�o de manuten��o dos dados de {@link CondominioEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface CondominioService {

	/**
	 * Adiciona ou atualiza uma entidade.
	 * 
	 * @param entity a entidade que ser� salva
	 * @return a entidade salva
	 */
	CondominioEntity save(CondominioEntity entity);

	/**
	 * Busca uma entidade pelo c�digo.
	 * 
	 * @param codigo o c�digo que ser� buscado
	 * @return a entidade encontrada, sen�o <code>null</code>
	 */
	CondominioEntity findByCodigo(Long codigo);

	/**
	 * Busca as entidades a partir do n�mero de uma p�gina de resultados e do limite
	 * de resultados que deve retornar. Limite m�ximo: 20. A pagina��o come�a em 0.
	 * 
	 * @param page  o n�mero da p�gina a ser buscada
	 * @param limit o limite de resultados
	 * @return a p�gina de resultado com as entidades
	 */
	Page<CondominioEntity> findByPage(int page, int limit);

	/**
	 * Deleta uma entidade a partir do c�digo.
	 * 
	 * @param codigo o c�digo da entidade que ser� deletada
	 * @throws EntityNotFoundException se a entidade n�o for encontrada
	 */
	void deleteByCodigo(Long codigo) throws EntityNotFoundException;

}
