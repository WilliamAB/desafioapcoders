package com.williamab.desafioapcoders.service.unidade;

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

import com.williamab.desafioapcoders.model.unidade.CondominioEntity;
import com.williamab.desafioapcoders.util.APIUtils;

/**
 * Testes para {@link CondominioService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class CondominioServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private CondominioService service;

	private final Long CODIGO = 20L;
	private final String NOME = "Condomínio de teste Service";
	private final String ENDERECO = "Rua Teste, 321 - Bairro Teste - Teste/TS";

	// Dados para condomínio que será usado em outros testes
	public static final Long CONDOMINIO_CODIGO = 456L;
	private final String CONDOMINIO_NOME = "Condomínio de teste Service 123";
	private final String CONDOMINIO_ENDERECO = "Rua Exemplo, 123 - Exem - Exemp/EX";

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
		CondominioEntity entity = new CondominioEntity();
		entity.setCodigo(CODIGO);
		entity.setNome(NOME);
		entity.setEndereco(ENDERECO);

		entity = service.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(NOME, entity.getNome());
		assertEquals(ENDERECO, entity.getEndereco());

		// Atualização
		entity = service.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(NOME, entity.getNome());
		assertEquals(ENDERECO, entity.getEndereco());
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testSaveCodigoDuplicado() {
		CondominioEntity entity = new CondominioEntity();
		entity.setCodigo(CODIGO);
		entity.setNome(NOME);
		entity.setEndereco(ENDERECO);

		assertThrows(() -> service.save(entity));
	}

	/**
	 * Teste que cria uma entidade de condomínio para ser utilizada em outros
	 * testes. A busca deve ser feita pelo código.
	 */
	@Test(dependsOnMethods = "testService")
	public void testSaveExternal() {
		CondominioEntity entity = new CondominioEntity();
		entity.setCodigo(CONDOMINIO_CODIGO);
		entity.setNome(CONDOMINIO_NOME);
		entity.setEndereco(CONDOMINIO_ENDERECO);

		entity = service.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CONDOMINIO_CODIGO, entity.getCodigo());
		assertEquals(CONDOMINIO_NOME, entity.getNome());
		assertEquals(CONDOMINIO_ENDERECO, entity.getEndereco());
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testFindByCodigo() {
		CondominioEntity entity = service.findByCodigo(CODIGO);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(NOME, entity.getNome());
		assertEquals(ENDERECO, entity.getEndereco());
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testFindByPage() {
		Page<CondominioEntity> page1 = service.findByPage(PAGINA, LIMITE_10);

		assertEquals(PAGINA, page1.getNumber());
		assertEquals(LIMITE_10, page1.getSize());

		List<CondominioEntity> content1 = page1.getContent();

		assertNotNull(content1);
		assertFalse(content1.isEmpty());

		Page<CondominioEntity> page2 = service.findByPage(PAGINA, LIMITE_30);

		assertEquals(PAGINA, page2.getNumber());
		assertEquals(APIUtils.PAGE_MAX_LIMIT, page2.getSize());

		List<CondominioEntity> content2 = page2.getContent();

		assertNotNull(content2);
		assertFalse(content2.isEmpty());
	}

	@Test(dependsOnGroups = "beforeDelete")
	public void testDeleteById() {
		service.deleteByCodigo(CODIGO);

		CondominioEntity entity = service.findByCodigo(CODIGO);

		assertNull(entity);

		assertThrows(() -> service.deleteByCodigo(CODIGO_INVALIDO));
	}

}
