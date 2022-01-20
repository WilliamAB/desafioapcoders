package com.williamab.desafioapcoders.service.inquilino.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.williamab.desafioapcoders.model.inquilino.InquilinoEntity;
import com.williamab.desafioapcoders.repository.inquilino.InquilinoRepository;
import com.williamab.desafioapcoders.service.inquilino.InquilinoService;
import com.williamab.desafioapcoders.util.APIUtils;

/**
 * Implementa��o de {@link InquilinoService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Service
public class InquilinoServiceImpl implements InquilinoService {

	@Autowired
	private InquilinoRepository repository;

	@Override
	public InquilinoEntity save(InquilinoEntity entity) {
		validate(entity);
		return repository.save(entity);
	}

	@Override
	public InquilinoEntity findByCodigo(Long codigo) {
		return repository.findByCodigo(codigo).orElse(null);
	}

	@Override
	public Page<InquilinoEntity> findByPage(int page, int limit) {
		return repository.findAll(APIUtils.createPageable(page, limit));
	}

	@Override
	public void deleteByCodigo(Long codigo) throws EntityNotFoundException {
		Optional<InquilinoEntity> optional = repository.findByCodigo(codigo);

		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Inquilino c�digo [%s] n�o encontrado!".formatted(codigo));
		}

		repository.deleteById(optional.get().getId());
	}

	/**
	 * Valida��es da entidade.
	 * 
	 * @param entity a entidade que ser� validada
	 */
	private void validate(InquilinoEntity entity) throws IllegalArgumentException {

		// Se o id for nulo significa que � uma entidade nova
		if (entity.getId() == null) {

			Long codigo = entity.getCodigo();
			Optional<InquilinoEntity> optional = repository.findByCodigo(codigo);

			// Verifica se o c�digo j� existe
			if (optional.isPresent()) {
				throw new IllegalArgumentException("Inquilino c�digo [%s] j� existe!".formatted(codigo));
			}
		}
	}

}
