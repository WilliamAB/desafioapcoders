package com.williamab.desafioapcoders.service.despesa;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;

import com.williamab.desafioapcoders.model.despesa.DespesaEntity;

/**
 * Servi�o de manuten��o dos dados de {@link DespesaEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface DespesaService {

	/**
	 * Adiciona ou atualiza uma entidade.
	 * 
	 * @param entity a entidade que ser� salva
	 * @return a entidade salva
	 */
	DespesaEntity save(DespesaEntity entity);

	/**
	 * Busca uma entidade pelo c�digo.
	 * 
	 * @param codigo o c�digo que ser� buscado
	 * @return a entidade encontrada, sen�o <code>null</code>
	 */
	DespesaEntity findByCodigo(Long codigo);

	/**
	 * Busca as entidades a partir do n�mero de uma p�gina de resultados e do limite
	 * de resultados que deve retornar. Limite m�ximo: 20. A pagina��o come�a em 0.
	 * 
	 * @param page  o n�mero da p�gina a ser buscada
	 * @param limit o limite de resultados
	 * @return a p�gina de resultado com as entidades
	 */
	Page<DespesaEntity> findByPage(int page, int limit);

	/**
	 * Busca as entidades a partir da identifica��o de uma unidade, do n�mero de uma
	 * p�gina de resultados e do limite de resultados que deve retornar. Limite
	 * m�ximo: 20. A pagina��o come�a em 0.
	 * 
	 * @param identificacao a identifica��o da unidade que deve ser filtrada
	 * @param page          o n�mero da p�gina a ser buscada
	 * @param limit         o limite de resultados
	 * @return a p�gina de resultado com as entidades
	 */
	Page<DespesaEntity> findByUnidade(String identificacao, int page, int limit);

	/**
	 * Busca as entidades que est�o com fatura vencidade a partir do n�mero de uma
	 * p�gina de resultados e do limite de resultados que deve retornar. Limite
	 * m�ximo: 20. A pagina��o come�a em 0.
	 * 
	 * @param page  o n�mero da p�gina a ser buscada
	 * @param limit o limite de resultados
	 * @return a p�gina de resultado com as entidades
	 */
	Page<DespesaEntity> findByFaturaVencida(int page, int limit);

	/**
	 * Deleta uma entidade a partir do c�digo.
	 * 
	 * @param codigo o c�digo da entidade que ser� deletada
	 * @throws EntityNotFoundException se a entidade n�o for encontrada
	 */
	void deleteByCodigo(Long codigo) throws EntityNotFoundException;

}
