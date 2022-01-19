package com.williamab.desafioapcoders.repository.unidade;

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

import com.williamab.desafioapcoders.model.unidade.CondominioEntity;

/**
 * Testes para {@link CondominioRepository}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class CondominioRepositoryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private CondominioRepository repository;

	private final Long CODIGO = 10L;
	private final String NOME = "Condomínio de teste";
	private final String ENDERECO = "Rua Teste, 321 - Bairro Teste - Teste/TS";

	// Dados para condomínio que será usada em outros testes
	public static final Long CONDOMINIO_CODIGO = 123L;
	private final String CONDOMINIO_NOME = "Condomínio de teste 123";
	private final String CONDOMINIO_ENDERECO = "Rua Exemplo, 123 - Exem - Exemp/EX";

	@Test
	public void testRepository() {
		assertNotNull(repository);
	}

	@Test(dependsOnMethods = "testRepository")
	public void testSave() {
		CondominioEntity entity = new CondominioEntity();
		entity.setCodigo(CODIGO);
		entity.setNome(NOME);
		entity.setEndereco(ENDERECO);

		entity = repository.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(NOME, entity.getNome());
		assertEquals(ENDERECO, entity.getEndereco());
	}

	/**
	 * Teste que cria uma entidade de condomínio para ser utilizada em outros
	 * testes. A busca deve ser feita pelo código.
	 */
	@Test(dependsOnMethods = "testRepository")
	public void testSaveExternal() {
		CondominioEntity entity = new CondominioEntity();
		entity.setCodigo(CONDOMINIO_CODIGO);
		entity.setNome(CONDOMINIO_NOME);
		entity.setEndereco(CONDOMINIO_ENDERECO);

		entity = repository.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CONDOMINIO_CODIGO, entity.getCodigo());
		assertEquals(CONDOMINIO_NOME, entity.getNome());
		assertEquals(CONDOMINIO_ENDERECO, entity.getEndereco());
	}

	@Test(dependsOnMethods = "testSave")
	public void testFindByCodigo() {
		Optional<CondominioEntity> optional = repository.findByCodigo(CODIGO);

		assertTrue(optional.isPresent());

		CondominioEntity entity = optional.get();

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(NOME, entity.getNome());
		assertEquals(ENDERECO, entity.getEndereco());
	}

	@Test(dependsOnMethods = "testFindByCodigo")
	public void testDeleteById() {
		Optional<CondominioEntity> optional = repository.findByCodigo(CODIGO);

		assertTrue(optional.isPresent());

		CondominioEntity entity = optional.get();

		assertNotNull(entity);
		assertNotNull(entity.getId());

		repository.deleteById(entity.getId());

		Optional<CondominioEntity> optionalEmpty = repository.findByCodigo(CODIGO);

		assertTrue(optionalEmpty.isEmpty());
	}

}
