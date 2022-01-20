package com.williamab.desafioapcoders.service.unidade.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.williamab.desafioapcoders.model.unidade.CondominioEntity;
import com.williamab.desafioapcoders.repository.unidade.CondominioRepository;
import com.williamab.desafioapcoders.service.unidade.CondominioService;
import com.williamab.desafioapcoders.util.APIUtils;

/**
 * Implementação de {@link CondominioService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Service
public class CondominioServiceImpl implements CondominioService {

	@Autowired
	private CondominioRepository repository;

	@Override
	public CondominioEntity save(CondominioEntity entity) {
		validate(entity);
		return repository.save(entity);
	}

	@Override
	public CondominioEntity findByCodigo(Long codigo) {
		return repository.findByCodigo(codigo).orElse(null);
	}

	@Override
	public Page<CondominioEntity> findByPage(int page, int limit) {
		return repository.findAll(APIUtils.createPageable(page, limit));
	}

	@Override
	public void deleteByCodigo(Long codigo) throws EntityNotFoundException {
		Optional<CondominioEntity> optional = repository.findByCodigo(codigo);

		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Condomínio código [%s] não encontrado!".formatted(codigo));
		}

		repository.deleteById(optional.get().getId());
	}

	/**
	 * Validações da entidade.
	 * 
	 * @param entity a entidade que será validada
	 */
	private void validate(CondominioEntity entity) throws IllegalArgumentException {

		// Se o id for nulo significa que é uma entidade nova
		if (entity.getId() == null) {

			Long codigo = entity.getCodigo();
			Optional<CondominioEntity> optional = repository.findByCodigo(codigo);

			// Verifica se o código já existe
			if (optional.isPresent()) {
				throw new IllegalArgumentException("Condomínio código [%s] já existe!".formatted(codigo));
			}
		}
	}

}
