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
 * Implementação de {@link InquilinoService}.
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
			throw new EntityNotFoundException("Inquilino código [%s] não encontrado!".formatted(codigo));
		}

		repository.deleteById(optional.get().getId());
	}

}
