package com.williamab.desafioapcoders.service.inquilino;

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

import com.williamab.desafioapcoders.model.inquilino.InquilinoEntity;
import com.williamab.desafioapcoders.model.inquilino.Sexo;
import com.williamab.desafioapcoders.util.APIUtils;

/**
 * Testes para {@link InquilinoService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class InquilinoServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private InquilinoService service;

	private final Long CODIGO = 100L;
	private final String NOME = "João da Silva";
	private final Date DATA_NASCIMENTO = new GregorianCalendar(1990, 10, 1).getTime();
	private final Sexo SEXO = Sexo.MASCULINO;
	private final String TELEFONE = "(47) 12345-6789";
	private final String EMAIL = "joao.silva@williamab.com";

	// Dados para inquilino que será usado em outros testes
	public static final Long INQUILINO_CODIGO = 753L;
	private final String INQUILINO_NOME = "Maria de Souza Service";
	private final Date INQUILINO_DATA_NASCIMENTO = new GregorianCalendar(1995, 8, 15).getTime();
	private final Sexo INQUILINO_SEXO = Sexo.FEMININO;
	private final String INQUILINO_TELEFONE = "(47) 98765-4321";
	private final String INQUILINO_EMAIL = "maria.souza@williamab.com";

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
		InquilinoEntity entity = new InquilinoEntity();
		entity.setCodigo(CODIGO);
		entity.setNome(NOME);
		entity.setDataNascimento(DATA_NASCIMENTO);
		entity.setSexo(SEXO);
		entity.setTelefone(TELEFONE);
		entity.setEmail(EMAIL);

		entity = service.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(NOME, entity.getNome());
		assertEquals(DATA_NASCIMENTO, entity.getDataNascimento());
		assertEquals(SEXO, entity.getSexo());
		assertEquals(TELEFONE, entity.getTelefone());
		assertEquals(EMAIL, entity.getEmail());

		// Atualização
		entity = service.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(NOME, entity.getNome());
		assertEquals(DATA_NASCIMENTO, entity.getDataNascimento());
		assertEquals(SEXO, entity.getSexo());
		assertEquals(TELEFONE, entity.getTelefone());
		assertEquals(EMAIL, entity.getEmail());
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testSaveCodigoDuplicado() {
		InquilinoEntity entity = new InquilinoEntity();
		entity.setCodigo(CODIGO);
		entity.setNome(NOME);
		entity.setDataNascimento(DATA_NASCIMENTO);
		entity.setSexo(SEXO);
		entity.setTelefone(TELEFONE);
		entity.setEmail(EMAIL);

		assertThrows(() -> service.save(entity));
	}

	/**
	 * Teste que cria uma entidade de inquilino para ser utilizada em outros testes.
	 * A busca deve ser feita pelo código.
	 */
	@Test(dependsOnMethods = "testService")
	public void testSaveExternal() {
		InquilinoEntity entity = new InquilinoEntity();
		entity.setCodigo(INQUILINO_CODIGO);
		entity.setNome(INQUILINO_NOME);
		entity.setDataNascimento(INQUILINO_DATA_NASCIMENTO);
		entity.setSexo(INQUILINO_SEXO);
		entity.setTelefone(INQUILINO_TELEFONE);
		entity.setEmail(INQUILINO_EMAIL);

		entity = service.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(INQUILINO_CODIGO, entity.getCodigo());
		assertEquals(INQUILINO_NOME, entity.getNome());
		assertEquals(INQUILINO_DATA_NASCIMENTO, entity.getDataNascimento());
		assertEquals(INQUILINO_SEXO, entity.getSexo());
		assertEquals(INQUILINO_TELEFONE, entity.getTelefone());
		assertEquals(INQUILINO_EMAIL, entity.getEmail());
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testFindByCodigo() {
		InquilinoEntity entity = service.findByCodigo(CODIGO);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(NOME, entity.getNome());
		assertEquals(DATA_NASCIMENTO, entity.getDataNascimento());
		assertEquals(SEXO, entity.getSexo());
		assertEquals(TELEFONE, entity.getTelefone());
		assertEquals(EMAIL, entity.getEmail());
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testFindByPage() {
		Page<InquilinoEntity> page1 = service.findByPage(PAGINA, LIMITE_10);

		assertEquals(PAGINA, page1.getNumber());
		assertEquals(LIMITE_10, page1.getSize());

		List<InquilinoEntity> content1 = page1.getContent();

		assertNotNull(content1);
		assertFalse(content1.isEmpty());

		Page<InquilinoEntity> page2 = service.findByPage(PAGINA, LIMITE_30);

		assertEquals(PAGINA, page2.getNumber());
		assertEquals(APIUtils.PAGE_MAX_LIMIT, page2.getSize());

		List<InquilinoEntity> content2 = page2.getContent();

		assertNotNull(content2);
		assertFalse(content2.isEmpty());
	}

	@Test(dependsOnGroups = "beforeDelete")
	public void testDeleteByCodigo() {
		service.deleteByCodigo(CODIGO);

		InquilinoEntity entity = service.findByCodigo(CODIGO);

		assertNull(entity);

		assertThrows(() -> service.deleteByCodigo(CODIGO_INVALIDO));
	}

}
