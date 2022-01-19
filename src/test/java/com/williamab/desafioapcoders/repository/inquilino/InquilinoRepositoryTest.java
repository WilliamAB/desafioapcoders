package com.williamab.desafioapcoders.repository.inquilino;

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

import com.williamab.desafioapcoders.model.inquilino.InquilinoEntity;
import com.williamab.desafioapcoders.model.inquilino.Sexo;

/**
 * Testes para {@link InquilinoRepository}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class InquilinoRepositoryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private InquilinoRepository repository;

	private final Long CODIGO = 100L;
	private final String NOME = "João da Silva";
	private final Date DATA_NASCIMENTO = new GregorianCalendar(1990, 10, 1).getTime();
	private final Sexo SEXO = Sexo.MASCULINO;
	private final String TELEFONE = "(47) 12345-6789";
	private final String EMAIL = "joao.silva@williamab.com";

	// Dados para inquilino que será usado em outros testes
	public static final Long INQUILINO_CODIGO = 321L;
	private final String INQUILINO_NOME = "Maria de Souza";
	private final Date INQUILINO_DATA_NASCIMENTO = new GregorianCalendar(1995, 8, 15).getTime();
	private final Sexo INQUILINO_SEXO = Sexo.FEMININO;
	private final String INQUILINO_TELEFONE = "(47) 98765-4321";
	private final String INQUILINO_EMAIL = "maria.souza@williamab.com";

	@Test
	public void testRepository() {
		assertNotNull(repository);
	}

	@Test(dependsOnMethods = "testRepository")
	public void testSave() {
		InquilinoEntity entity = new InquilinoEntity();
		entity.setCodigo(CODIGO);
		entity.setNome(NOME);
		entity.setDataNascimento(DATA_NASCIMENTO);
		entity.setSexo(SEXO);
		entity.setTelefone(TELEFONE);
		entity.setEmail(EMAIL);

		entity = repository.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(NOME, entity.getNome());
		assertEquals(DATA_NASCIMENTO, entity.getDataNascimento());
		assertEquals(SEXO, entity.getSexo());
		assertEquals(TELEFONE, entity.getTelefone());
		assertEquals(EMAIL, entity.getEmail());
	}

	/**
	 * Teste que cria uma entidade de inquilino para ser utilizada em outros testes.
	 * A busca deve ser feita pelo código.
	 */
	@Test(dependsOnMethods = "testRepository")
	public void testSaveExternal() {
		InquilinoEntity entity = new InquilinoEntity();
		entity.setCodigo(INQUILINO_CODIGO);
		entity.setNome(INQUILINO_NOME);
		entity.setDataNascimento(INQUILINO_DATA_NASCIMENTO);
		entity.setSexo(INQUILINO_SEXO);
		entity.setTelefone(INQUILINO_TELEFONE);
		entity.setEmail(INQUILINO_EMAIL);

		entity = repository.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(INQUILINO_CODIGO, entity.getCodigo());
		assertEquals(INQUILINO_NOME, entity.getNome());
		assertEquals(INQUILINO_DATA_NASCIMENTO, entity.getDataNascimento());
		assertEquals(INQUILINO_SEXO, entity.getSexo());
		assertEquals(INQUILINO_TELEFONE, entity.getTelefone());
		assertEquals(INQUILINO_EMAIL, entity.getEmail());
	}

	@Test(dependsOnMethods = "testSave")
	public void testFindByCodigo() {
		Optional<InquilinoEntity> optional = repository.findByCodigo(CODIGO);

		assertTrue(optional.isPresent());

		InquilinoEntity entity = optional.get();

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(CODIGO, entity.getCodigo());
		assertEquals(NOME, entity.getNome());
		assertEquals(DATA_NASCIMENTO, entity.getDataNascimento());
		assertEquals(SEXO, entity.getSexo());
		assertEquals(TELEFONE, entity.getTelefone());
		assertEquals(EMAIL, entity.getEmail());
	}

	@Test(dependsOnMethods = "testFindByCodigo")
	public void testDeleteById() {
		Optional<InquilinoEntity> optional = repository.findByCodigo(CODIGO);

		assertTrue(optional.isPresent());

		InquilinoEntity entity = optional.get();

		assertNotNull(entity);
		assertNotNull(entity.getId());

		repository.deleteById(entity.getId());

		Optional<InquilinoEntity> optionalEmpty = repository.findByCodigo(CODIGO);

		assertTrue(optionalEmpty.isEmpty());
	}

}
