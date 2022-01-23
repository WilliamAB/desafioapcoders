package com.williamab.desafioapcoders.repository.despesa;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.williamab.desafioapcoders.model.despesa.DespesaEntity;
import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;
import com.williamab.desafioapcoders.repository.BasicRepository;

/**
 * Reposit�rio para {@link DespesaEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface DespesaRepository extends BasicRepository<DespesaEntity> {

	/**
	 * Busca uma despesa pelo c�digo.
	 * 
	 * @param codigo o c�digo que ser� buscado
	 * @return a entidade encontrada ou {@literal Optional#empty()}
	 */
	Optional<DespesaEntity> findByCodigo(Long codigo);

	/**
	 * Busca as despesas da uma unidade.
	 * 
	 * @param unidade  a unidade filtrada
	 * @param pageable as configura��es da p�gina de resultado
	 * @return uma p�gina com os resultados
	 */
	Page<DespesaEntity> findByUnidade(UnidadeEntity unidade, Pageable pageable);

	/**
	 * Busca as despesas com o vencimento da fatura anterior a data passada por
	 * par�metro.
	 * 
	 * @param data     a data filtrada
	 * @param pageable as configura��es da p�gina de resultado
	 * @return uma p�gina com os resultados
	 */
	Page<DespesaEntity> findByVencimentoFaturaLessThan(Date data, Pageable pageable);

	/**
	 * Busca as despesas filtrando unidade e com o vencimento da fatura anterior a
	 * data passada por par�metro.
	 * 
	 * @param unidade  a unidade filtrada
	 * @param date     a data filtrada
	 * @param pageable as configura��es da p�gina de resultado
	 * @return uma p�gina com os resultados
	 */
	Page<DespesaEntity> findByUnidadeAndVencimentoFaturaLessThan(UnidadeEntity unidade, Date date, Pageable pageable);

}
