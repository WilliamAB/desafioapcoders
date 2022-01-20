package com.williamab.desafioapcoders.util.converter.inquilino;

import com.williamab.desafioapcoders.dto.model.inquilino.InquilinoDTO;
import com.williamab.desafioapcoders.model.inquilino.InquilinoEntity;
import com.williamab.desafioapcoders.model.inquilino.Sexo;
import com.williamab.desafioapcoders.util.converter.BasicConverter;

/**
 * Conversor entidade/DTO para inquilino.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class InquilinoConverter extends BasicConverter<InquilinoEntity, InquilinoDTO> {

	private static InquilinoConverter INSTANCE;

	public static InquilinoConverter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new InquilinoConverter();
		}
		return INSTANCE;
	}

	private InquilinoConverter() {
	}

	@Override
	protected InquilinoEntity createEmptyEntity() {
		return new InquilinoEntity();
	}

	@Override
	protected InquilinoDTO createEmptyDTO() {
		return new InquilinoDTO();
	}

	@Override
	protected void convertFieldsFromEntityToDTO(InquilinoEntity entity, InquilinoDTO dto) {
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setDataNascimento(entity.getDataNascimento());
		dto.setSexo(entity.getSexo().name());
		dto.setEmail(entity.getEmail());
		dto.setTelefone(entity.getTelefone());
	}

	@Override
	protected void convertFieldsFromDTOToEntity(InquilinoDTO dto, InquilinoEntity entity) {
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setDataNascimento(dto.getDataNascimento());
		entity.setSexo(Sexo.valueOf(dto.getSexo()));
		entity.setEmail(dto.getEmail());
		entity.setTelefone(dto.getTelefone());
	}

}
