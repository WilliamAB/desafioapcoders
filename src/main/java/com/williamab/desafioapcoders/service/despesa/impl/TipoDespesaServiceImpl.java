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
 * Implementação de {@link TipoDespesaService}.
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
			throw new EntityNotFoundException("Tipo de despesa código [%s] não encontrado!".formatted(codigo));
		}

		repository.deleteById(optional.get().getId());
	}

}
