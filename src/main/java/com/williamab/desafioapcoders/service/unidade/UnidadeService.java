package com.williamab.desafioapcoders.service.unidade;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;

import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;

/**
 * Servi�o de manuten��o dos dados de {@link UnidadeEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface UnidadeService {

	/**
	 * Adiciona ou atualiza uma entidade.
	 * 
	 * @param entity a entidade que ser� salva
	 * @return a entidade salva
	 */
	UnidadeEntity save(UnidadeEntity entity);

	/**
	 * Busca uma entidade pela identifica��o.
	 * 
	 * @param identificacao a identifica��o que ser� buscada
	 * @return a entidade encontrada, sen�o <code>null</code>
	 */
	UnidadeEntity findByIdentificacao(String identificacao);

	/**
	 * Busca as entidades a partir do n�mero de uma p�gina de resultados e do limite
	 * de resultados que deve retornar. Limite m�ximo: 20. A pagina��o come�a em 0.
	 * 
	 * @param page  o n�mero da p�gina a ser buscada
	 * @param limit o limite de resultados
	 * @return a p�gina de resultado com as entidades
	 */
	Page<UnidadeEntity> findByPage(int page, int limit);

	/**
	 * Deleta uma entidade a partir da identifica��o.
	 * 
	 * @param identificacao a identifica��o da entidade que ser� deletada
	 * @throws EntityNotFoundException se a entidade n�o for encontrada
	 */
	void deleteByIdentificacao(String identificacao) throws EntityNotFoundException;

}
