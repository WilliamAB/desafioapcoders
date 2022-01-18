package com.williamab.desafioapcoders.util.converter;

import com.williamab.desafioapcoders.dto.model.BasicDTO;
import com.williamab.desafioapcoders.model.BasicEntity;

/**
 * Conversor base para implementação dos conversores entidade/DTO.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 * 
 * @param E representa a entidade
 * @param D representa o DTO
 */
public abstract class BasicConverter<E extends BasicEntity, D extends BasicDTO<D>> {

	/**
	 * Converte uma entidade em um DTO.
	 * 
	 * @param entity a entidade a ser convertida
	 * @return o DTO como resultado da conversão
	 */
	public D convertEntityToDTO(E entity) {

		if (entity == null) {
			return null;
		}

		D dto = createEmptyDTO();

		dto.setId(entity.getId());

		convertFieldsFromEntityToDTO(entity, dto);

		return dto;
	}

	/**
	 * Converte um DTO em uma entidade.
	 * 
	 * @param dto o DTO a ser convertido
	 * @return a entidade como resultado da conversão
	 */
	public E convertDTOToEntity(D dto) {

		if (dto == null) {
			return null;
		}

		E entity = createEmptyEntity();

		entity.setId(dto.getId());

		convertFieldsFromDTOToEntity(dto, entity);

		return entity;
	}

	/**
	 * Cria uma instância da entidade, sem nenhum valor atribuído aos campos.
	 * 
	 * @return uma instância da entidade
	 */
	protected abstract E createEmptyEntity();

	/**
	 * Cria uma instância do DTO, sem nenhum valor atribuído aos campos.
	 * 
	 * @return uma instância do DTO
	 */
	protected abstract D createEmptyDTO();

	/**
	 * Converte os atributos da entidade para o DTO. O id já é convertido, não há
	 * necessidade de converter.
	 * 
	 * @param entity a entidade com as informações que serão copiadas para o DTO
	 * @param dto    o DTO que receberá as informações da entidade
	 */
	protected abstract void convertFieldsFromEntityToDTO(E entity, D dto);

	/**
	 * Converte os atributos do DTO para a entidade. O id já é convertido, não há
	 * necessidade de converter.
	 * 
	 * @param dto    o DTO com as informações que serão copiadas para a entidade
	 * @param entity a entidade que receberá as informações do DTO
	 */
	protected abstract void convertFieldsFromDTOToEntity(D dto, E entity);

}
