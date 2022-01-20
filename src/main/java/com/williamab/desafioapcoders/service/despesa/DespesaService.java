package com.williamab.desafioapcoders.service.despesa;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;

import com.williamab.desafioapcoders.model.despesa.DespesaEntity;

/**
 * Serviço de manutenção dos dados de {@link DespesaEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface DespesaService {

	/**
	 * Adiciona ou atualiza uma entidade.
	 * 
	 * @param entity a entidade que será salva
	 * @return a entidade salva
	 */
	DespesaEntity save(DespesaEntity entity);

	/**
	 * Busca uma entidade pelo código.
	 * 
	 * @param codigo o código que será buscado
	 * @return a entidade encontrada, senão <code>null</code>
	 */
	DespesaEntity findByCodigo(Long codigo);

	/**
	 * Busca as entidades a partir do número de uma página de resultados e do limite
	 * de resultados que deve retornar. Limite máximo: 20. A paginação começa em 0.
	 * 
	 * @param page  o número da página a ser buscada
	 * @param limit o limite de resultados
	 * @return a página de resultado com as entidades
	 */
	Page<DespesaEntity> findByPage(int page, int limit);

	/**
	 * Busca as entidades a partir da identificação de uma unidade, do número de uma
	 * página de resultados e do limite de resultados que deve retornar. Limite
	 * máximo: 20. A paginação começa em 0.
	 * 
	 * @param identificacao a identificação da unidade que deve ser filtrada
	 * @param page          o número da página a ser buscada
	 * @param limit         o limite de resultados
	 * @return a página de resultado com as entidades
	 */
	Page<DespesaEntity> findByUnidade(String identificacao, int page, int limit);

	/**
	 * Busca as entidades que estão com fatura vencidade a partir do número de uma
	 * página de resultados e do limite de resultados que deve retornar. Limite
	 * máximo: 20. A paginação começa em 0.
	 * 
	 * @param page  o número da página a ser buscada
	 * @param limit o limite de resultados
	 * @return a página de resultado com as entidades
	 */
	Page<DespesaEntity> findByFaturaVencida(int page, int limit);

	/**
	 * Deleta uma entidade a partir do código.
	 * 
	 * @param codigo o código da entidade que será deletada
	 * @throws EntityNotFoundException se a entidade não for encontrada
	 */
	void deleteByCodigo(Long codigo) throws EntityNotFoundException;

}
