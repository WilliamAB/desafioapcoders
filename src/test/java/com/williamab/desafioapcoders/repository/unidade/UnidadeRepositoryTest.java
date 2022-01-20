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

import com.williamab.desafioapcoders.model.inquilino.InquilinoEntity;
import com.williamab.desafioapcoders.model.unidade.CondominioEntity;
import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;
import com.williamab.desafioapcoders.repository.inquilino.InquilinoRepository;
import com.williamab.desafioapcoders.repository.inquilino.InquilinoRepositoryTest;

/**
 * Testes para {@link UnidadeRepository}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class UnidadeRepositoryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private UnidadeRepository unidadeRepository;

	@Autowired
	private InquilinoRepository inquilinoRepository;

	@Autowired
	private CondominioRepository condominioRepository;

	private final String IDENTIFICACAO = "589-5";

	// Dados para unidade que será usada em outros testes
	public static final String UNIDADE_IDENTIFICACAO = "1-234";

	@Test
	public void testRepository() {
		assertNotNull(unidadeRepository);
		assertNotNull(inquilinoRepository);
		assertNotNull(condominioRepository);
	}

	@Test(dependsOnMethods = "testRepository")
	public void testSave() {

		// Busca do Proprietário
		Optional<InquilinoEntity> optionalProprietario = inquilinoRepository
				.findByCodigo(InquilinoRepositoryTest.INQUILINO_CODIGO);

		assertTrue(optionalProprietario.isPresent());

		InquilinoEntity proprietario = optionalProprietario.get();

		assertNotNull(proprietario);
		assertNotNull(proprietario.getId());

		// Busca do Condomínio
		Optional<CondominioEntity> optionalCondominio = condominioRepository
				.findByCodigo(CondominioRepositoryTest.CONDOMINIO_CODIGO);

		assertTrue(optionalCondominio.isPresent());

		CondominioEntity condominio = optionalCondominio.get();

		assertNotNull(condominio);
		assertNotNull(condominio.getId());

		// Criação da Unidade
		UnidadeEntity unidade = new UnidadeEntity();
		unidade.setIdentificacao(IDENTIFICACAO);
		unidade.setProprietario(proprietario);
		unidade.setCondominio(condominio);

		unidade = unidadeRepository.save(unidade);

		assertNotNull(unidade);
		assertNotNull(unidade.getId());
		assertNotNull(unidade.getProprietario());
		assertNotNull(unidade.getCondominio());
		assertEquals(IDENTIFICACAO, unidade.getIdentificacao());
		assertEquals(InquilinoRepositoryTest.INQUILINO_CODIGO, unidade.getProprietario().getCodigo());
		assertEquals(CondominioRepositoryTest.CONDOMINIO_CODIGO, unidade.getCondominio().getCodigo());
	}

	/**
	 * Teste que cria uma entidade de unidade para ser utilizada em outros testes. A
	 * busca deve ser feita pelo código.
	 */
	@Test(dependsOnMethods = "testRepository")
	public void testSaveExternal() {

		// Busca do Proprietário
		Optional<InquilinoEntity> optionalProprietario = inquilinoRepository
				.findByCodigo(InquilinoRepositoryTest.INQUILINO_CODIGO);

		assertTrue(optionalProprietario.isPresent());

		InquilinoEntity proprietario = optionalProprietario.get();

		assertNotNull(proprietario);
		assertNotNull(proprietario.getId());

		// Busca do Condomínio
		Optional<CondominioEntity> optionalCondominio = condominioRepository
				.findByCodigo(CondominioRepositoryTest.CONDOMINIO_CODIGO);

		assertTrue(optionalCondominio.isPresent());

		CondominioEntity condominio = optionalCondominio.get();

		assertNotNull(condominio);
		assertNotNull(condominio.getId());

		// Criação da Unidade
		UnidadeEntity unidade = new UnidadeEntity();
		unidade.setIdentificacao(UNIDADE_IDENTIFICACAO);
		unidade.setProprietario(proprietario);
		unidade.setCondominio(condominio);

		unidade = unidadeRepository.save(unidade);

		assertNotNull(unidade);
		assertNotNull(unidade.getId());
		assertNotNull(unidade.getProprietario());
		assertNotNull(unidade.getCondominio());
		assertEquals(UNIDADE_IDENTIFICACAO, unidade.getIdentificacao());
		assertEquals(InquilinoRepositoryTest.INQUILINO_CODIGO, unidade.getProprietario().getCodigo());
		assertEquals(CondominioRepositoryTest.CONDOMINIO_CODIGO, unidade.getCondominio().getCodigo());
	}

	@Test(dependsOnMethods = "testSave")
	public void testFindByIdentificacao() {
		Optional<UnidadeEntity> optional = unidadeRepository.findByIdentificacao(IDENTIFICACAO);

		assertTrue(optional.isPresent());

		UnidadeEntity entity = optional.get();

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertNotNull(entity.getProprietario());
		assertNotNull(entity.getCondominio());
		assertEquals(IDENTIFICACAO, entity.getIdentificacao());
		assertEquals(InquilinoRepositoryTest.INQUILINO_CODIGO, entity.getProprietario().getCodigo());
		assertEquals(CondominioRepositoryTest.CONDOMINIO_CODIGO, entity.getCondominio().getCodigo());
	}

	@Test(dependsOnMethods = "testFindByIdentificacao")
	public void testDeleteById() {
		Optional<UnidadeEntity> optional = unidadeRepository.findByIdentificacao(IDENTIFICACAO);

		assertTrue(optional.isPresent());

		UnidadeEntity entity = optional.get();

		assertNotNull(entity);
		assertNotNull(entity.getId());

		unidadeRepository.deleteById(entity.getId());

		Optional<UnidadeEntity> optionalEmpty = unidadeRepository.findByIdentificacao(IDENTIFICACAO);

		assertTrue(optionalEmpty.isEmpty());
	}

}
