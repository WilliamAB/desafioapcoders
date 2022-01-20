package com.williamab.desafioapcoders.service.inquilino;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;

import com.williamab.desafioapcoders.model.inquilino.InquilinoEntity;

/**
 * Servi�o de manuten��o dos dados de {@link InquilinoEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface InquilinoService {

	/**
	 * Adiciona ou atualiza uma entidade.
	 * 
	 * @param entity a entidade que ser� salva
	 * @return a entidade salva
	 */
	InquilinoEntity save(InquilinoEntity entity);

	/**
	 * Busca uma entidade pelo c�digo.
	 * 
	 * @param codigo o c�digo que ser� buscado
	 * @return a entidade encontrada, sen�o <code>null</code>
	 */
	InquilinoEntity findByCodigo(Long codigo);

	/**
	 * Busca as entidades a partir do n�mero de uma p�gina de resultados e do limite
	 * de resultados que deve retornar. Limite m�ximo: 20. A pagina��o come�a em 0.
	 * 
	 * @param page  o n�mero da p�gina a ser buscada
	 * @param limit o limite de resultados
	 * @return a p�gina de resultado com as entidades
	 */
	Page<InquilinoEntity> findByPage(int page, int limit);

	/**
	 * Deleta uma entidade a partir do c�digo.
	 * 
	 * @param codigo o c�digo da entidade que ser� deletada
	 * @throws EntityNotFoundException se a entidade n�o for encontrada
	 */
	void deleteByCodigo(Long codigo) throws EntityNotFoundException;

}
