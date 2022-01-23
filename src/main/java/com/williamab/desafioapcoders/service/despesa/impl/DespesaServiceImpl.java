package com.williamab.desafioapcoders.service.despesa.impl;

import java.util.Date;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.williamab.desafioapcoders.model.despesa.DespesaEntity;
import com.williamab.desafioapcoders.model.despesa.TipoDespesaEntity;
import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;
import com.williamab.desafioapcoders.repository.despesa.DespesaRepository;
import com.williamab.desafioapcoders.service.despesa.DespesaService;
import com.williamab.desafioapcoders.service.despesa.TipoDespesaService;
import com.williamab.desafioapcoders.service.unidade.UnidadeService;
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
	private UnidadeService unidadeService;

	@Autowired
	private TipoDespesaService tipoDespesaService;

	@Override
	public DespesaEntity save(DespesaEntity entity) {
		validate(entity);

		UnidadeEntity unidade = unidadeService.findByIdentificacao(entity.getUnidade().getIdentificacao());
		entity.setUnidade(unidade);

		TipoDespesaEntity tipoDespesa = tipoDespesaService.findByCodigo(entity.getTipoDespesa().getCodigo());
		entity.setTipoDespesa(tipoDespesa);

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
	public Page<DespesaEntity> findByUnidade(String identificacao, int page, int limit) {
		UnidadeEntity unidade = unidadeService.findByIdentificacao(identificacao);

		if (unidade == null) {
			throw new EntityNotFoundException("Unidade identificação [%s] não encontrada!".formatted(identificacao));
		}

		return repository.findByUnidade(unidade, APIUtils.createPageable(page, limit));
	}

	@Override
	public Page<DespesaEntity> findByFaturaVencida(int page, int limit) {
		Date dataAtual = new Date();
		return repository.findByVencimentoFaturaLessThan(dataAtual, APIUtils.createPageable(page, limit));
	}

	@Override
	public Page<DespesaEntity> findWithFilter(String unidadeIdentificacao, boolean faturaVencida, int page, int limit) {

		boolean hasUnidadeIdentificacao = unidadeIdentificacao != null;

		UnidadeEntity unidade = null;
		Date dataAtual = new Date();

		if (hasUnidadeIdentificacao) {
			unidade = unidadeService.findByIdentificacao(unidadeIdentificacao);

			if (unidade == null) {
				throw new EntityNotFoundException(
						"Unidade identificação [%s] não encontrada!".formatted(unidadeIdentificacao));
			}
		}

		Pageable pageable = APIUtils.createPageable(page, limit);

		// Filtro por unidade e fatura vencida
		if (hasUnidadeIdentificacao && faturaVencida) {
			return repository.findByUnidadeAndVencimentoFaturaLessThan(unidade, dataAtual, pageable);
		}

		// Filtro por unidade
		if (hasUnidadeIdentificacao && !faturaVencida) {
			return repository.findByUnidade(unidade, pageable);
		}

		// Filtro por fatura vencida
		if (!hasUnidadeIdentificacao && faturaVencida) {
			return repository.findByVencimentoFaturaLessThan(dataAtual, pageable);
		}

		return repository.findAll(pageable);
	}

	@Override
	public void deleteByCodigo(Long codigo) throws EntityNotFoundException {
		Optional<DespesaEntity> optional = repository.findByCodigo(codigo);

		if (optional.isEmpty()) {
			throw new EntityNotFoundException("Despesa código [%s] não encontrada!".formatted(codigo));
		}

		repository.deleteById(optional.get().getId());
	}

	/**
	 * Validações da entidade.
	 * 
	 * @param entity a entidade que será validada
	 */
	private void validate(DespesaEntity entity) throws IllegalArgumentException {

		// Se o id for nulo significa que é uma entidade nova
		if (entity.getId() == null) {

			Long codigo = entity.getCodigo();
			Optional<DespesaEntity> optional = repository.findByCodigo(codigo);

			// Verifica se o código já existe
			if (optional.isPresent()) {
				throw new IllegalArgumentException("Despesa código [%s] já existe!".formatted(codigo));
			}
		}
	}

}
