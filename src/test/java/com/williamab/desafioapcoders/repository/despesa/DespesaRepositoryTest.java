package com.williamab.desafioapcoders.repository.despesa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import com.williamab.desafioapcoders.model.despesa.DespesaEntity;
import com.williamab.desafioapcoders.model.despesa.StatusPagamento;
import com.williamab.desafioapcoders.model.despesa.TipoDespesaEntity;
import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;
import com.williamab.desafioapcoders.repository.unidade.UnidadeRepository;
import com.williamab.desafioapcoders.repository.unidade.UnidadeRepositoryTest;

/**
 * Testes para {@link DespesaRepository}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class DespesaRepositoryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private DespesaRepository despesaRepository;

	@Autowired
	private TipoDespesaRepository tipoDespesaRepository;

	@Autowired
	private UnidadeRepository unidadeRepository;

	private final Long CODIGO = 987L;
	private final String DESCRICAO = "Despesa de teste";
	private final Double VALOR = 123.98;
	private final Date VENCIMENTO_FATURA = new GregorianCalendar(2022, 4, 25).getTime();
	private final StatusPagamento STATUS_PAGAMENTO = StatusPagamento.NAO_PAGO;

	@Test
	public void testRepository() {
		assertNotNull(despesaRepository);
		assertNotNull(tipoDespesaRepository);
		assertNotNull(unidadeRepository);
	}

	@Test(dependsOnMethods = "testRepository")
	public void testSave() {

		// Busca do Tipo de Despesa
		Optional<TipoDespesaEntity> optionalTipoDespesa = tipoDespesaRepository
				.findByCodigo(TipoDespesaRepositoryTest.TIPO_DESPESA_CODIGO);

		assertTrue(optionalTipoDespesa.isPresent());

		TipoDespesaEntity tipoDespesa = optionalTipoDespesa.get();

		assertNotNull(tipoDespesa);
		assertNotNull(tipoDespesa.getId());

		// Busca da Unidade
		Optional<UnidadeEntity> optionalUnidade = unidadeRepository
				.findByIdentificacao(UnidadeRepositoryTest.UNIDADE_IDENTIFICACAO);

		assertTrue(optionalUnidade.isPresent());

		UnidadeEntity unidade = optionalUnidade.get();

		assertNotNull(unidade);
		assertNotNull(unidade.getId());

		// Criação da Despesa
		DespesaEntity despesa = new DespesaEntity();
		despesa.setCodigo(CODIGO);
		despesa.setDescricao(DESCRICAO);
		despesa.setValor(VALOR);
		despesa.setVencimentoFatura(VENCIMENTO_FATURA);
		despesa.setStatusPagamento(STATUS_PAGAMENTO);
		despesa.setTipoDespesa(tipoDespesa);
		despesa.setUnidade(unidade);

		despesa = despesaRepository.save(despesa);

		assertNotNull(despesa);
		assertNotNull(despesa.getId());
		assertNotNull(despesa.getTipoDespesa());
		assertNotNull(despesa.getUnidade());
		assertEquals(CODIGO, despesa.getCodigo());
		assertEquals(DESCRICAO, despesa.getDescricao());
		assertEquals(VALOR, despesa.getValor());
		assertEquals(VENCIMENTO_FATURA, despesa.getVencimentoFatura());
		assertEquals(STATUS_PAGAMENTO, despesa.getStatusPagamento());
		assertEquals(TipoDespesaRepositoryTest.TIPO_DESPESA_CODIGO, despesa.getTipoDespesa().getCodigo());
		assertEquals(UnidadeRepositoryTest.UNIDADE_IDENTIFICACAO, despesa.getUnidade().getIdentificacao());
	}

	@Test(dependsOnMethods = "testSave")
	public void testFindByCodigo() {
		Optional<DespesaEntity> optional = despesaRepository.findByCodigo(CODIGO);

		assertTrue(optional.isPresent());

		DespesaEntity entity = optional.get();

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertNotNull(entity.getTipoDespesa());
		assertNotNull(entity.getUnidade());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(DESCRICAO, entity.getDescricao());
		assertEquals(VALOR, entity.getValor());
		assertEquals(VENCIMENTO_FATURA, entity.getVencimentoFatura());
		assertEquals(STATUS_PAGAMENTO, entity.getStatusPagamento());
		assertEquals(TipoDespesaRepositoryTest.TIPO_DESPESA_CODIGO, entity.getTipoDespesa().getCodigo());
		assertEquals(UnidadeRepositoryTest.UNIDADE_IDENTIFICACAO, entity.getUnidade().getIdentificacao());
	}

	@Test(dependsOnMethods = "testFindByCodigo")
	public void testDeleteById() {
		Optional<DespesaEntity> optional = despesaRepository.findByCodigo(CODIGO);

		assertTrue(optional.isPresent());

		DespesaEntity entity = optional.get();

		assertNotNull(entity);
		assertNotNull(entity.getId());

		despesaRepository.deleteById(entity.getId());

		Optional<DespesaEntity> optionalEmpty = despesaRepository.findByCodigo(CODIGO);

		assertTrue(optionalEmpty.isEmpty());
	}

}
