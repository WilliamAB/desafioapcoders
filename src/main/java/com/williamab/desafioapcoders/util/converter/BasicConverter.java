package com.williamab.desafioapcoders.util.converter;

import com.williamab.desafioapcoders.dto.model.BasicDTO;
import com.williamab.desafioapcoders.model.BasicEntity;

/**
 * Conversor base para implementa��o dos conversores entidade/DTO.
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
	 * @return o DTO como resultado da convers�o
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
	 * @return a entidade como resultado da convers�o
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
	 * Cria uma inst�ncia da entidade, sem nenhum valor atribu�do aos campos.
	 * 
	 * @return uma inst�ncia da entidade
	 */
	protected abstract E createEmptyEntity();

	/**
	 * Cria uma inst�ncia do DTO, sem nenhum valor atribu�do aos campos.
	 * 
	 * @return uma inst�ncia do DTO
	 */
	protected abstract D createEmptyDTO();

	/**
	 * Converte os atributos da entidade para o DTO. O id j� � convertido, n�o h�
	 * necessidade de converter.
	 * 
	 * @param entity a entidade com as informa��es que ser�o copiadas para o DTO
	 * @param dto    o DTO que receber� as informa��es da entidade
	 */
	protected abstract void convertFieldsFromEntityToDTO(E entity, D dto);

	/**
	 * Converte os atributos do DTO para a entidade. O id j� � convertido, n�o h�
	 * necessidade de converter.
	 * 
	 * @param dto    o DTO com as informa��es que ser�o copiadas para a entidade
	 * @param entity a entidade que receber� as informa��es do DTO
	 */
	protected abstract void convertFieldsFromDTOToEntity(D dto, E entity);

}
