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

import com.williamab.desafioapcoders.model.inquilino.InquilinoEntity;
import com.williamab.desafioapcoders.model.unidade.CondominioEntity;
import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;
import com.williamab.desafioapcoders.service.inquilino.InquilinoService;
import com.williamab.desafioapcoders.service.inquilino.InquilinoServiceTest;
import com.williamab.desafioapcoders.util.APIUtils;

/**
 * Testes para {@link UnidadeService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class UnidadeServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private UnidadeService unidadeService;

	@Autowired
	private InquilinoService inquilinoService;

	@Autowired
	private CondominioService condominioService;

	private final String IDENTIFICACAO = "589-5";

	// Dados para unidade que será usada em outros testes
	public static final String UNIDADE_IDENTIFICACAO = "4-89";

	// Dados de busca de página
	private final int PAGINA = 0;
	private final int LIMITE_10 = 10;
	private final int LIMITE_30 = 30;

	// Dados para teste inválido
	public static final String IDENTIFICACAO_INEXISTENTE = "1000";

	@Test
	public void testService() {
		assertNotNull(unidadeService);
		assertNotNull(inquilinoService);
		assertNotNull(condominioService);
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testService")
	public void testSave() {

		// Busca do Proprietário
		InquilinoEntity proprietario = inquilinoService.findByCodigo(InquilinoServiceTest.INQUILINO_CODIGO);

		assertNotNull(proprietario);
		assertNotNull(proprietario.getId());

		// Busca do Condomínio
		CondominioEntity condominio = condominioService.findByCodigo(CondominioServiceTest.CONDOMINIO_CODIGO);

		assertNotNull(condominio);
		assertNotNull(condominio.getId());

		// Criação da Unidade
		UnidadeEntity unidade = new UnidadeEntity();
		unidade.setIdentificacao(IDENTIFICACAO);
		unidade.setProprietario(proprietario);
		unidade.setCondominio(condominio);

		unidade = unidadeService.save(unidade);

		assertNotNull(unidade);
		assertNotNull(unidade.getId());
		assertNotNull(unidade.getProprietario());
		assertNotNull(unidade.getCondominio());
		assertEquals(IDENTIFICACAO, unidade.getIdentificacao());
		assertEquals(InquilinoServiceTest.INQUILINO_CODIGO, unidade.getProprietario().getCodigo());
		assertEquals(CondominioServiceTest.CONDOMINIO_CODIGO, unidade.getCondominio().getCodigo());

		// Atualização
		unidade = unidadeService.save(unidade);

		assertNotNull(unidade);
		assertNotNull(unidade.getId());
		assertNotNull(unidade.getProprietario());
		assertNotNull(unidade.getCondominio());
		assertEquals(IDENTIFICACAO, unidade.getIdentificacao());
		assertEquals(InquilinoServiceTest.INQUILINO_CODIGO, unidade.getProprietario().getCodigo());
		assertEquals(CondominioServiceTest.CONDOMINIO_CODIGO, unidade.getCondominio().getCodigo());
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testSaveCodigoDuplicado() {

		// Busca do Proprietário
		InquilinoEntity proprietario = inquilinoService.findByCodigo(InquilinoServiceTest.INQUILINO_CODIGO);

		assertNotNull(proprietario);
		assertNotNull(proprietario.getId());

		// Busca do Condomínio
		CondominioEntity condominio = condominioService.findByCodigo(CondominioServiceTest.CONDOMINIO_CODIGO);

		assertNotNull(condominio);
		assertNotNull(condominio.getId());

		// Criação da Unidade
		UnidadeEntity unidade = new UnidadeEntity();
		unidade.setIdentificacao(IDENTIFICACAO);
		unidade.setProprietario(proprietario);
		unidade.setCondominio(condominio);

		assertThrows(() -> unidadeService.save(unidade));
	}

	/**
	 * Teste que cria uma entidade de unidade para ser utilizada em outros testes. A
	 * busca deve ser feita pelo código.
	 */
	@Test(dependsOnMethods = "testService")
	public void testSaveExternal() {

		// Busca do Proprietário
		InquilinoEntity proprietario = inquilinoService.findByCodigo(InquilinoServiceTest.INQUILINO_CODIGO);

		assertNotNull(proprietario);
		assertNotNull(proprietario.getId());

		// Busca do Condomínio
		CondominioEntity condominio = condominioService.findByCodigo(CondominioServiceTest.CONDOMINIO_CODIGO);

		assertNotNull(condominio);
		assertNotNull(condominio.getId());

		// Criação da Unidade
		UnidadeEntity unidade = new UnidadeEntity();
		unidade.setIdentificacao(UNIDADE_IDENTIFICACAO);
		unidade.setProprietario(proprietario);
		unidade.setCondominio(condominio);

		unidade = unidadeService.save(unidade);

		assertNotNull(unidade);
		assertNotNull(unidade.getId());
		assertNotNull(unidade.getProprietario());
		assertNotNull(unidade.getCondominio());
		assertEquals(UNIDADE_IDENTIFICACAO, unidade.getIdentificacao());
		assertEquals(InquilinoServiceTest.INQUILINO_CODIGO, unidade.getProprietario().getCodigo());
		assertEquals(CondominioServiceTest.CONDOMINIO_CODIGO, unidade.getCondominio().getCodigo());
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testFindByIdentificacao() {
		UnidadeEntity entity = unidadeService.findByIdentificacao(IDENTIFICACAO);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertNotNull(entity.getProprietario());
		assertNotNull(entity.getCondominio());
		assertEquals(IDENTIFICACAO, entity.getIdentificacao());
		assertEquals(InquilinoServiceTest.INQUILINO_CODIGO, entity.getProprietario().getCodigo());
		assertEquals(CondominioServiceTest.CONDOMINIO_CODIGO, entity.getCondominio().getCodigo());
	}

	@Test(groups = "beforeDelete", dependsOnMethods = "testSave")
	public void testFindByPage() {
		Page<UnidadeEntity> page1 = unidadeService.findByPage(PAGINA, LIMITE_10);

		assertEquals(PAGINA, page1.getNumber());
		assertEquals(LIMITE_10, page1.getSize());

		List<UnidadeEntity> content1 = page1.getContent();

		assertNotNull(content1);
		assertFalse(content1.isEmpty());

		Page<UnidadeEntity> page2 = unidadeService.findByPage(PAGINA, LIMITE_30);

		assertEquals(PAGINA, page2.getNumber());
		assertEquals(APIUtils.PAGE_MAX_LIMIT, page2.getSize());

		List<UnidadeEntity> content2 = page2.getContent();

		assertNotNull(content2);
		assertFalse(content2.isEmpty());
	}

	@Test(dependsOnGroups = "beforeDelete")
	public void testDeleteById() {
		unidadeService.deleteByIdentificacao(IDENTIFICACAO);

		UnidadeEntity entity = unidadeService.findByIdentificacao(IDENTIFICACAO);

		assertNull(entity);

		assertThrows(() -> unidadeService.deleteByIdentificacao(IDENTIFICACAO_INEXISTENTE));
	}

}
