package com.williamab.desafioapcoders.service.despesa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertThrows;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import com.williamab.desafioapcoders.model.despesa.DespesaEntity;
import com.williamab.desafioapcoders.model.despesa.StatusPagamento;
import com.williamab.desafioapcoders.model.despesa.TipoDespesaEntity;
import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;
import com.williamab.desafioapcoders.service.unidade.UnidadeService;
import com.williamab.desafioapcoders.service.unidade.UnidadeServiceTest;
import com.williamab.desafioapcoders.util.APIUtils;

/**
 * Testes para {@link DespesaService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class DespesaServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private DespesaService despesaService;

	@Autowired
	private TipoDespesaService tipoDespesaService;

	@Autowired
	private UnidadeService unidadeService;

	private final Long CODIGO = 987L;
	private final String DESCRICAO = "Despesa de teste";
	private final Double VALOR = 123.98;
	private final Date VENCIMENTO_FATURA = new GregorianCalendar(2020, 4, 25).getTime();
	private final StatusPagamento STATUS_PAGAMENTO = StatusPagamento.NAO_PAGO;

	// Dados de busca de página
	private final int PAGINA = 0;
	private final int LIMITE_10 = 10;
	private final int LIMITE_30 = 30;

	// Dados para teste inválido
	private final Long CODIGO_INVALIDO = 1000L;

	@Test
	public void testService() {
		assertNotNull(despesaService);
		assertNotNull(tipoDespesaService);
		assertNotNull(unidadeService);
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testService")
	public void testSave() {

		// Busca do Tipo de Despesa
		TipoDespesaEntity tipoDespesa = tipoDespesaService.findByCodigo(TipoDespesaServiceTest.TIPO_DESPESA_CODIGO);

		assertNotNull(tipoDespesa);
		assertNotNull(tipoDespesa.getId());

		// Busca da Unidade
		UnidadeEntity unidade = unidadeService.findByIdentificacao(UnidadeServiceTest.UNIDADE_IDENTIFICACAO);

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

		despesa = despesaService.save(despesa);

		assertNotNull(despesa);
		assertNotNull(despesa.getId());
		assertNotNull(despesa.getTipoDespesa());
		assertNotNull(despesa.getUnidade());
		assertEquals(CODIGO, despesa.getCodigo());
		assertEquals(DESCRICAO, despesa.getDescricao());
		assertEquals(VALOR, despesa.getValor());
		assertEquals(VENCIMENTO_FATURA, despesa.getVencimentoFatura());
		assertEquals(STATUS_PAGAMENTO, despesa.getStatusPagamento());
		assertEquals(TipoDespesaServiceTest.TIPO_DESPESA_CODIGO, despesa.getTipoDespesa().getCodigo());
		assertEquals(UnidadeServiceTest.UNIDADE_IDENTIFICACAO, despesa.getUnidade().getIdentificacao());

		// Atualização
		despesa = despesaService.save(despesa);

		assertNotNull(despesa);
		assertNotNull(despesa.getId());
		assertNotNull(despesa.getTipoDespesa());
		assertNotNull(despesa.getUnidade());
		assertEquals(CODIGO, despesa.getCodigo());
		assertEquals(DESCRICAO, despesa.getDescricao());
		assertEquals(VALOR, despesa.getValor());
		assertEquals(VENCIMENTO_FATURA, despesa.getVencimentoFatura());
		assertEquals(STATUS_PAGAMENTO, despesa.getStatusPagamento());
		assertEquals(TipoDespesaServiceTest.TIPO_DESPESA_CODIGO, despesa.getTipoDespesa().getCodigo());
		assertEquals(UnidadeServiceTest.UNIDADE_IDENTIFICACAO, despesa.getUnidade().getIdentificacao());
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testSaveCodigoDuplicado() {

		// Busca do Tipo de Despesa
		TipoDespesaEntity tipoDespesa = tipoDespesaService.findByCodigo(TipoDespesaServiceTest.TIPO_DESPESA_CODIGO);

		assertNotNull(tipoDespesa);
		assertNotNull(tipoDespesa.getId());

		// Busca da Unidade
		UnidadeEntity unidade = unidadeService.findByIdentificacao(UnidadeServiceTest.UNIDADE_IDENTIFICACAO);

		assertNotNull(unidade);
		assertNotNull(unidade.getId());

		// Criação da despesa
		DespesaEntity despesa = new DespesaEntity();
		despesa.setCodigo(CODIGO);
		despesa.setDescricao(DESCRICAO);
		despesa.setValor(VALOR);
		despesa.setVencimentoFatura(VENCIMENTO_FATURA);
		despesa.setStatusPagamento(STATUS_PAGAMENTO);
		despesa.setTipoDespesa(tipoDespesa);
		despesa.setUnidade(unidade);

		assertThrows(() -> despesaService.save(despesa));
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testFindByCodigo() {
		DespesaEntity entity = despesaService.findByCodigo(CODIGO);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertNotNull(entity.getTipoDespesa());
		assertNotNull(entity.getUnidade());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(DESCRICAO, entity.getDescricao());
		assertEquals(VALOR, entity.getValor());
		assertEquals(VENCIMENTO_FATURA, entity.getVencimentoFatura());
		assertEquals(STATUS_PAGAMENTO, entity.getStatusPagamento());
		assertEquals(TipoDespesaServiceTest.TIPO_DESPESA_CODIGO, entity.getTipoDespesa().getCodigo());
		assertEquals(UnidadeServiceTest.UNIDADE_IDENTIFICACAO, entity.getUnidade().getIdentificacao());
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testFindByPage() {
		Page<DespesaEntity> page1 = despesaService.findByPage(PAGINA, LIMITE_10);

		assertEquals(PAGINA, page1.getNumber());
		assertEquals(LIMITE_10, page1.getSize());

		List<DespesaEntity> content1 = page1.getContent();

		assertNotNull(content1);
		assertFalse(content1.isEmpty());

		Page<DespesaEntity> page2 = despesaService.findByPage(PAGINA, LIMITE_30);

		assertEquals(PAGINA, page2.getNumber());
		assertEquals(APIUtils.PAGE_MAX_LIMIT, page2.getSize());

		List<DespesaEntity> content2 = page2.getContent();

		assertNotNull(content2);
		assertFalse(content2.isEmpty());
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testFindByUnidade() {
		Page<DespesaEntity> page = despesaService.findByUnidade(UnidadeServiceTest.UNIDADE_IDENTIFICACAO, PAGINA,
				LIMITE_10);

		assertEquals(PAGINA, page.getNumber());
		assertEquals(LIMITE_10, page.getSize());
		assertEquals(1, page.getNumberOfElements());
		assertEquals(1, page.getTotalElements());

		List<DespesaEntity> content = page.getContent();

		assertNotNull(content);
		assertFalse(content.isEmpty());

		assertThrows(() -> despesaService.findByUnidade(UnidadeServiceTest.IDENTIFICACAO_INEXISTENTE, PAGINA, LIMITE_10));
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testFindByFaturaVencida() {
		Page<DespesaEntity> page = despesaService.findByFaturaVencida(PAGINA, LIMITE_10);

		assertEquals(PAGINA, page.getNumber());
		assertEquals(LIMITE_10, page.getSize());
		assertEquals(1, page.getNumberOfElements());
		assertEquals(1, page.getTotalElements());

		List<DespesaEntity> content = page.getContent();

		assertNotNull(content);
		assertFalse(content.isEmpty());
	}

	@Test(dependsOnGroups = "beforeDelete")
	public void testDeleteById() {
		despesaService.deleteByCodigo(CODIGO);

		DespesaEntity entity = despesaService.findByCodigo(CODIGO);

		assertNull(entity);

		assertThrows(() -> despesaService.deleteByCodigo(CODIGO_INVALIDO));
	}

}
