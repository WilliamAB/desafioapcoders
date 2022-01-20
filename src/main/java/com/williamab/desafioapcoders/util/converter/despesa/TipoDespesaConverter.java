package com.williamab.desafioapcoders.util.converter.despesa;

import com.williamab.desafioapcoders.dto.model.despesa.TipoDespesaDTO;
import com.williamab.desafioapcoders.model.despesa.TipoDespesaEntity;
import com.williamab.desafioapcoders.util.converter.BasicConverter;

/**
 * Conversor entidade/DTO para tipo de despesa.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class TipoDespesaConverter extends BasicConverter<TipoDespesaEntity, TipoDespesaDTO> {

	private static TipoDespesaConverter INSTANCE;

	public static TipoDespesaConverter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TipoDespesaConverter();
		}
		return INSTANCE;
	}

	private TipoDespesaConverter() {
	}

	@Override
	protected TipoDespesaEntity createEmptyEntity() {
		return new TipoDespesaEntity();
	}

	@Override
	protected TipoDespesaDTO createEmptyDTO() {
		return new TipoDespesaDTO();
	}

	@Override
	protected void convertFieldsFromEntityToDTO(TipoDespesaEntity entity, TipoDespesaDTO dto) {
		dto.setCodigo(entity.getCodigo());
		dto.setDescricao(entity.getDescricao());
	}

	@Override
	protected void convertFieldsFromDTOToEntity(TipoDespesaDTO dto, TipoDespesaEntity entity) {
		entity.setCodigo(dto.getCodigo());
		entity.setDescricao(dto.getDescricao());
	}

}
