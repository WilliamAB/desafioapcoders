package com.williamab.desafioapcoders.service.despesa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertThrows;

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

import com.williamab.desafioapcoders.model.despesa.TipoDespesaEntity;
import com.williamab.desafioapcoders.util.APIUtils;

/**
 * Testes para {@link TipoDespesaService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class TipoDespesaServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private TipoDespesaService service;

	private final Long CODIGO = 10L;
	private final String DESCRICAO = "Tipo de despesa de teste Service";

	// Dados para condomínio que será usada em outros testes
	public static final Long TIPO_DESPESA_CODIGO = 789L;
	private final String TIPO_DESPESA_DESCRICAO = "Tipo de despesa de teste Service 456";

	// Dados de busca de página
	private final int PAGINA = 0;
	private final int LIMITE_10 = 10;
	private final int LIMITE_30 = 30;

	// Dados para teste inválido
	private final Long CODIGO_INVALIDO = 1000L;

	@Test
	public void testService() {
		assertNotNull(service);
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testService")
	public void testSave() {
		TipoDespesaEntity entity = new TipoDespesaEntity();
		entity.setCodigo(CODIGO);
		entity.setDescricao(DESCRICAO);

		entity = service.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(DESCRICAO, entity.getDescricao());

		// Atualização
		entity = service.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(DESCRICAO, entity.getDescricao());
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testSaveCodigoDuplicado() {
		TipoDespesaEntity entity = new TipoDespesaEntity();
		entity.setCodigo(CODIGO);
		entity.setDescricao(DESCRICAO);

		assertThrows(() -> service.save(entity));
	}

	/**
	 * Teste que cria uma entidade de tipo de despesa para ser utilizada em outros
	 * testes. A busca deve ser feita pelo código.
	 */
	@Test(dependsOnMethods = "testService")
	public void testSaveExternal() {
		TipoDespesaEntity entity = new TipoDespesaEntity();
		entity.setCodigo(TIPO_DESPESA_CODIGO);
		entity.setDescricao(TIPO_DESPESA_DESCRICAO);

		entity = service.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(TIPO_DESPESA_CODIGO, entity.getCodigo());
		assertEquals(TIPO_DESPESA_DESCRICAO, entity.getDescricao());
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testFindByCodigo() {
		TipoDespesaEntity entity = service.findByCodigo(CODIGO);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(DESCRICAO, entity.getDescricao());
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testFindByPage() {
		Page<TipoDespesaEntity> page1 = service.findByPage(PAGINA, LIMITE_10);

		assertEquals(PAGINA, page1.getNumber());
		assertEquals(LIMITE_10, page1.getSize());

		List<TipoDespesaEntity> content1 = page1.getContent();

		assertNotNull(content1);
		assertFalse(content1.isEmpty());

		Page<TipoDespesaEntity> page2 = service.findByPage(PAGINA, LIMITE_30);

		assertEquals(PAGINA, page2.getNumber());
		assertEquals(APIUtils.PAGE_MAX_LIMIT, page2.getSize());

		List<TipoDespesaEntity> content2 = page2.getContent();

		assertNotNull(content2);
		assertFalse(content2.isEmpty());
	}

	@Test(dependsOnGroups = "beforeDelete")
	public void testDeleteById() {
		service.deleteByCodigo(CODIGO);

		TipoDespesaEntity entity = service.findByCodigo(CODIGO);

		assertNull(entity);

		assertThrows(() -> service.deleteByCodigo(CODIGO_INVALIDO));
	}

}
