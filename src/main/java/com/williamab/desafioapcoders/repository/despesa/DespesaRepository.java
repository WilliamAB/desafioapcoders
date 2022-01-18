package com.williamab.desafioapcoders.repository.despesa;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.williamab.desafioapcoders.model.despesa.DespesaEntity;
import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;
import com.williamab.desafioapcoders.repository.BasicRepository;

/**
 * Repositório para {@link DespesaEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface DespesaRepository extends BasicRepository<DespesaEntity> {

	/**
	 * Busca as despesas da uma unidade.
	 * 
	 * @param unidade  a unidade filtrada
	 * @param pageable as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<DespesaEntity> findByUnidade(UnidadeEntity unidade, Pageable pageable);

	/**
	 * Busca as despesas com o vencimento da fatura anterior a data passada por
	 * parâmetro.
	 * 
	 * @param data     a data filtrada
	 * @param pageable as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<DespesaEntity> findByVencimentoFaturaLessThan(Date data, Pageable pageable);

}
