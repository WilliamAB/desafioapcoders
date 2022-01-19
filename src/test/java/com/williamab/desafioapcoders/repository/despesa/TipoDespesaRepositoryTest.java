package com.williamab.desafioapcoders.repository.despesa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import com.williamab.desafioapcoders.model.despesa.TipoDespesaEntity;

/**
 * Testes para {@link TipoDespesaRepository}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class TipoDespesaRepositoryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private TipoDespesaRepository repository;

	private final Long CODIGO = 10L;
	private final String DESCRICAO = "Tipo de despesa de teste";

	// Dados para condomínio que será usada em outros testes
	public static final Long TIPO_DESPESA_CODIGO = 456L;
	private final String TIPO_DESPESA_DESCRICAO = "Tipo de despesa de teste 456";

	@Test
	public void testRepository() {
		assertNotNull(repository);
	}

	@Test(dependsOnMethods = "testRepository")
	public void testSave() {
		TipoDespesaEntity entity = new TipoDespesaEntity();
		entity.setCodigo(CODIGO);
		entity.setDescricao(DESCRICAO);

		entity = repository.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(DESCRICAO, entity.getDescricao());
	}

	/**
	 * Teste que cria uma entidade de tipo de despesa para ser utilizada em outros
	 * testes. A busca deve ser feita pelo código.
	 */
	@Test(dependsOnMethods = "testRepository")
	public void testSaveExternal() {
		TipoDespesaEntity entity = new TipoDespesaEntity();
		entity.setCodigo(TIPO_DESPESA_CODIGO);
		entity.setDescricao(TIPO_DESPESA_DESCRICAO);

		entity = repository.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(TIPO_DESPESA_CODIGO, entity.getCodigo());
		assertEquals(TIPO_DESPESA_DESCRICAO, entity.getDescricao());
	}

	@Test(dependsOnMethods = "testSave")
	public void testFindByCodigo() {
		Optional<TipoDespesaEntity> optional = repository.findByCodigo(CODIGO);

		assertTrue(optional.isPresent());

		TipoDespesaEntity entity = optional.get();

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(DESCRICAO, entity.getDescricao());
	}

	@Test(dependsOnMethods = "testFindByCodigo")
	public void testDeleteById() {
		Optional<TipoDespesaEntity> optional = repository.findByCodigo(CODIGO);

		assertTrue(optional.isPresent());

		TipoDespesaEntity entity = optional.get();

		assertNotNull(entity);
		assertNotNull(entity.getId());

		repository.deleteById(entity.getId());

		Optional<TipoDespesaEntity> optionalEmpty = repository.findByCodigo(CODIGO);

		assertTrue(optionalEmpty.isEmpty());
	}

}
