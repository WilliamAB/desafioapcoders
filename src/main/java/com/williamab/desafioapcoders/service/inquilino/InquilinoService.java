package com.williamab.desafioapcoders.service.inquilino;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;

import com.williamab.desafioapcoders.model.inquilino.InquilinoEntity;

/**
 * Serviço de manutenção dos dados de {@link InquilinoEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface InquilinoService {

	/**
	 * Adiciona ou atualiza uma entidade.
	 * 
	 * @param entity a entidade que será salva
	 * @return a entidade salva
	 */
	InquilinoEntity save(InquilinoEntity entity);

	/**
	 * Busca uma entidade pelo código.
	 * 
	 * @param codigo o código que será buscado
	 * @return a entidade encontrada, senão <code>null</code>
	 */
	InquilinoEntity findByCodigo(Long codigo);

	/**
	 * Busca as entidades a partir do número de uma página de resultados e do limite
	 * de resultados que deve retornar. Limite máximo: 20. A paginação começa em 0.
	 * 
	 * @param page  o número da página a ser buscada
	 * @param limit o limite de resultados
	 * @return a página de resultado com as entidades
	 */
	Page<InquilinoEntity> findByPage(int page, int limit);

	/**
	 * Deleta uma entidade a partir do código.
	 * 
	 * @param codigo o código da entidade que será deletada
	 * @throws EntityNotFoundException se a entidade não for encontrada
	 */
	void deleteByCodigo(Long codigo) throws EntityNotFoundException;

}
