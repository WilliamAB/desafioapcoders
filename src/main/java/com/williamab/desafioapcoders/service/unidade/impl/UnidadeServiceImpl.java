package com.williamab.desafioapcoders.service.unidade.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;
import com.williamab.desafioapcoders.repository.unidade.UnidadeRepository;
import com.williamab.desafioapcoders.service.unidade.UnidadeService;
import com.williamab.desafioapcoders.util.APIUtils;

/**
 * Implementação de {@link UnidadeService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Service
public class UnidadeServiceImpl implements UnidadeService {

	@Autowired
	private UnidadeRepository repository;

	@Override
	public UnidadeEntity save(UnidadeEntity entity) {
		validate(entity);
		return repository.save(entity);
	}

	@Override
	public UnidadeEntity findByIdentificacao(String identificacao) {
		return repository.findByIdentificacao(identificacao).orElse(null);
	}

	@Override
	public Page<UnidadeEntity> findByPage(int page, int limit) {
		return repository.findAll(APIUtils.createPageable(page, limit));
	}

	@Override
	public void deleteByIdentificacao(String identificacao) throws EntityNotFoundException {
		Optional<UnidadeEntity> optional = repository.findByIdentificacao(identificacao);

		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Unidade identificação [%s] não encontrada!".formatted(identificacao));
		}

		repository.deleteById(optional.get().getId());
	}

	/**
	 * Validações da entidade.
	 * 
	 * @param entity a entidade que será validada
	 */
	private void validate(UnidadeEntity entity) throws IllegalArgumentException {

		// Se o id for nulo significa que é uma entidade nova
		if (entity.getId() == null) {

			String identificacao = entity.getIdentificacao();
			Optional<UnidadeEntity> optional = repository.findByIdentificacao(identificacao);

			// Verifica se o código já existe
			if (optional.isPresent()) {
				throw new IllegalArgumentException("Unidade identificação [%s] já existe!".formatted(identificacao));
			}
		}
	}

}
