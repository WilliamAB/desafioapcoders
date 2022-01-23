package com.williamab.desafioapcoders.util.converter.unidade;

import com.williamab.desafioapcoders.dto.model.unidade.UnidadeDTO;
import com.williamab.desafioapcoders.model.inquilino.InquilinoEntity;
import com.williamab.desafioapcoders.model.unidade.CondominioEntity;
import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;
import com.williamab.desafioapcoders.util.converter.BasicConverter;

/**
 * Conversor entidade/DTO para unidade.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class UnidadeConverter extends BasicConverter<UnidadeEntity, UnidadeDTO> {

	private static UnidadeConverter INSTANCE;

	public static UnidadeConverter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UnidadeConverter();
		}
		return INSTANCE;
	}

	private UnidadeConverter() {
	}

	@Override
	protected UnidadeEntity createEmptyEntity() {
		return new UnidadeEntity();
	}

	@Override
	protected UnidadeDTO createEmptyDTO() {
		return new UnidadeDTO();
	}

	@Override
	protected void convertFieldsFromEntityToDTO(UnidadeEntity entity, UnidadeDTO dto) {
		dto.setIdentificacao(entity.getIdentificacao());
		dto.setProprietarioCodigo(entity.getProprietario().getCodigo());
		dto.setCondominioCodigo(entity.getCondominio().getCodigo());
	}

	@Override
	protected void convertFieldsFromDTOToEntity(UnidadeDTO dto, UnidadeEntity entity) {
		entity.setIdentificacao(dto.getIdentificacao());

		InquilinoEntity proprietario = new InquilinoEntity();
		proprietario.setCodigo(dto.getProprietarioCodigo());
		entity.setProprietario(proprietario);

		CondominioEntity condominio = new CondominioEntity();
		condominio.setCodigo(dto.getCondominioCodigo());
		entity.setCondominio(condominio);
	}

}
