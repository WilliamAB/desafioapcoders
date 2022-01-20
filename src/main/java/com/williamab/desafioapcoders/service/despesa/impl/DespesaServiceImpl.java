package com.williamab.desafioapcoders.service.despesa.impl;

import java.util.Date;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.williamab.desafioapcoders.model.despesa.DespesaEntity;
import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;
import com.williamab.desafioapcoders.repository.despesa.DespesaRepository;
import com.williamab.desafioapcoders.repository.unidade.UnidadeRepository;
import com.williamab.desafioapcoders.service.despesa.DespesaService;
import com.williamab.desafioapcoders.util.APIUtils;

/**
 * Implementação de {@link DespesaService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Service
public class DespesaServiceImpl implements DespesaService {

	@Autowired
	private DespesaRepository repository;

	@Autowired
	private UnidadeRepository unidadeRepository;

	@Override
	public DespesaEntity save(DespesaEntity entity) {
		return repository.save(entity);
	}

	@Override
	public DespesaEntity findByCodigo(Long codigo) {
		return repository.findByCodigo(codigo).orElse(null);
	}

	@Override
	public Page<DespesaEntity> findByPage(int page, int limit) {
		return repository.findAll(APIUtils.createPageable(page, limit));
	}

	@Override
	public Page<DespesaEntity> findByUnidade(UnidadeEntity unidade, int page, int limit) {
		Optional<UnidadeEntity> optional = unidadeRepository.findById(unidade.getId());

		if (optional.isEmpty()) {
			throw new EntityNotFoundException(
					"Unidade identificação [%s] não encontrada!".formatted(unidade.getIdentificacao()));
		}

		return repository.findByUnidade(optional.get(), APIUtils.createPageable(page, limit));
	}

	@Override
	public Page<DespesaEntity> findByFaturaVencida(int page, int limit) {
		Date dataAtual = new Date();
		return repository.findByVencimentoFaturaLessThan(dataAtual, APIUtils.createPageable(page, limit));
	}

	@Override
	public void deleteByCodigo(Long codigo) throws EntityNotFoundException {
		Optional<DespesaEntity> optional = repository.findByCodigo(codigo);

		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Despesa código [%s] não encontrada!".formatted(codigo));
		}

		repository.deleteById(optional.get().getId());
	}

}
