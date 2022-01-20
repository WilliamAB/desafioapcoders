package com.williamab.desafioapcoders.service.despesa.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.williamab.desafioapcoders.model.despesa.TipoDespesaEntity;
import com.williamab.desafioapcoders.repository.despesa.TipoDespesaRepository;
import com.williamab.desafioapcoders.service.despesa.TipoDespesaService;
import com.williamab.desafioapcoders.util.APIUtils;

/**
 * Implementa��o de {@link TipoDespesaService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Service
public class TipoDespesaServiceImpl implements TipoDespesaService {

	@Autowired
	private TipoDespesaRepository repository;

	@Override
	public TipoDespesaEntity save(TipoDespesaEntity entity) {
		validate(entity);
		return repository.save(entity);
	}

	@Override
	public TipoDespesaEntity findByCodigo(Long codigo) {
		return repository.findByCodigo(codigo).orElse(null);
	}

	@Override
	public Page<TipoDespesaEntity> findByPage(int page, int limit) {
		return repository.findAll(APIUtils.createPageable(page, limit));
	}

	@Override
	public void deleteByCodigo(Long codigo) throws EntityNotFoundException {
		Optional<TipoDespesaEntity> optional = repository.findByCodigo(codigo);

		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Tipo de despesa c�digo [%s] n�o encontrado!".formatted(codigo));
		}

		repository.deleteById(optional.get().getId());
	}

	/**
	 * Valida��es da entidade.
	 * 
	 * @param entity a entidade que ser� validada
	 */
	private void validate(TipoDespesaEntity entity) throws IllegalArgumentException {

		// Se o id for nulo significa que � uma entidade nova
		if (entity.getId() == null) {

			Long codigo = entity.getCodigo();
			Optional<TipoDespesaEntity> optional = repository.findByCodigo(codigo);

			// Verifica se o c�digo j� existe
			if (optional.isPresent()) {
				throw new IllegalArgumentException("Tipo de despesa c�digo [%s] j� existe!".formatted(codigo));
			}
		}
	}

}
