package com.williamab.desafioapcoders.service.unidade;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;

import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;

/**
 * Serviço de manutenção dos dados de {@link UnidadeEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface UnidadeService {

	/**
	 * Adiciona ou atualiza uma entidade.
	 * 
	 * @param entity a entidade que será salva
	 * @return a entidade salva
	 */
	UnidadeEntity save(UnidadeEntity entity);

	/**
	 * Busca uma entidade pela identificação.
	 * 
	 * @param identificacao a identificação que será buscada
	 * @return a entidade encontrada, senão <code>null</code>
	 */
	UnidadeEntity findByIdentificacao(String identificacao);

	/**
	 * Busca as entidades a partir do número de uma página de resultados e do limite
	 * de resultados que deve retornar. Limite máximo: 20. A paginação começa em 0.
	 * 
	 * @param page  o número da página a ser buscada
	 * @param limit o limite de resultados
	 * @return a página de resultado com as entidades
	 */
	Page<UnidadeEntity> findByPage(int page, int limit);

	/**
	 * Deleta uma entidade a partir da identificação.
	 * 
	 * @param identificacao a identificação da entidade que será deletada
	 * @throws EntityNotFoundException se a entidade não for encontrada
	 */
	void deleteByIdentificacao(String identificacao) throws EntityNotFoundException;

}
