package com.williamab.desafioapcoders.util.converter.unidade;

import com.williamab.desafioapcoders.dto.model.unidade.CondominioDTO;
import com.williamab.desafioapcoders.model.unidade.CondominioEntity;
import com.williamab.desafioapcoders.util.converter.BasicConverter;

/**
 * Conversor entidade/DTO para condomínio.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class CondominioConverter extends BasicConverter<CondominioEntity, CondominioDTO> {

	private static CondominioConverter INSTANCE;

	public static CondominioConverter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CondominioConverter();
		}
		return INSTANCE;
	}

	private CondominioConverter() {
	}

	@Override
	protected CondominioEntity createEmptyEntity() {
		return new CondominioEntity();
	}

	@Override
	protected CondominioDTO createEmptyDTO() {
		return new CondominioDTO();
	}

	@Override
	protected void convertFieldsFromEntityToDTO(CondominioEntity entity, CondominioDTO dto) {
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setEndereco(entity.getEndereco());
	}

	@Override
	protected void convertFieldsFromDTOToEntity(CondominioDTO dto, CondominioEntity entity) {
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setEndereco(dto.getEndereco());
	}

}
