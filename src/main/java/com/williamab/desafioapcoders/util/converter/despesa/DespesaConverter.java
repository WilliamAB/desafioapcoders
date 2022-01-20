package com.williamab.desafioapcoders.util.converter.despesa;

import com.williamab.desafioapcoders.dto.model.despesa.DespesaDTO;
import com.williamab.desafioapcoders.model.despesa.DespesaEntity;
import com.williamab.desafioapcoders.model.despesa.StatusPagamento;
import com.williamab.desafioapcoders.model.despesa.TipoDespesaEntity;
import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;
import com.williamab.desafioapcoders.util.converter.BasicConverter;

/**
 * Conversor entidade/DTO para despesa.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class DespesaConverter extends BasicConverter<DespesaEntity, DespesaDTO> {

	private static DespesaConverter INSTANCE;

	public static DespesaConverter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DespesaConverter();
		}
		return INSTANCE;
	}

	private DespesaConverter() {
	}

	@Override
	protected DespesaEntity createEmptyEntity() {
		return new DespesaEntity();
	}

	@Override
	protected DespesaDTO createEmptyDTO() {
		return new DespesaDTO();
	}

	@Override
	protected void convertFieldsFromEntityToDTO(DespesaEntity entity, DespesaDTO dto) {
		dto.setCodigo(entity.getCodigo());
		dto.setDescricao(entity.getDescricao());
		dto.setValor(entity.getValor());
		dto.setStatusPagamento(entity.getStatusPagamento().name());
		dto.setVencimentoFatura(entity.getVencimentoFatura());
		dto.setTipoDespesaId(entity.getTipoDespesa().getId());
		dto.setUnidadeId(entity.getUnidade().getId());
	}

	@Override
	protected void convertFieldsFromDTOToEntity(DespesaDTO dto, DespesaEntity entity) {
		entity.setCodigo(dto.getCodigo());
		entity.setDescricao(dto.getDescricao());
		entity.setValor(dto.getValor());
		entity.setStatusPagamento(StatusPagamento.valueOf(dto.getStatusPagamento()));
		entity.setVencimentoFatura(dto.getVencimentoFatura());

		TipoDespesaEntity tipoDespesa = new TipoDespesaEntity();
		tipoDespesa.setId(dto.getTipoDespesaId());
		entity.setTipoDespesa(tipoDespesa);

		UnidadeEntity unidade = new UnidadeEntity();
		unidade.setId(dto.getUnidadeId());
		entity.setUnidade(unidade);
	}

}
