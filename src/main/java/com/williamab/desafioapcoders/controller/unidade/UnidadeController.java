package com.williamab.desafioapcoders.controller.unidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.williamab.desafioapcoders.controller.BasicController;
import com.williamab.desafioapcoders.dto.model.unidade.UnidadeDTO;
import com.williamab.desafioapcoders.dto.response.ResponseDTO;
import com.williamab.desafioapcoders.dto.response.ResponsePageableDTO;
import com.williamab.desafioapcoders.model.unidade.UnidadeEntity;
import com.williamab.desafioapcoders.service.unidade.UnidadeService;
import com.williamab.desafioapcoders.util.converter.unidade.UnidadeConverter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller com requisições para /unidade
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Api
@RestController
@RequestMapping("/api/unidade")
public class UnidadeController extends BasicController {

	@Autowired
	private UnidadeService service;

	/**
	 * Requisição para buscar uma lista de unidades a partir da página e do limite
	 * de registros trazidos.
	 * 
	 * @param page  a página a ser filtrada
	 * @param limit o limite de registros
	 * @return a resposta com os unidades
	 */
	@GetMapping
	@ApiOperation("Obtém a listagem de unidades")
	public ResponseEntity<ResponsePageableDTO<List<UnidadeDTO>>> getByPage(
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "20") int limit) {

		ResponsePageableDTO<List<UnidadeDTO>> response = new ResponsePageableDTO<>();

		// Busca as entidades
		Page<UnidadeEntity> pageEntities = service.findByPage(page - 1, limit);

		List<UnidadeDTO> unidades = new ArrayList<>();

		// Converte as entidades em DTOs
		pageEntities.forEach(e -> {
			UnidadeDTO dto = UnidadeConverter.getInstance().convertEntityToDTO(e);

			// Cria o link para o DTO
			createSelfLink(dto);
			unidades.add(dto);
		});

		// Atualiza a resposta com os dados
		response.setData(unidades);
		response.addPageInfo(pageEntities);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Requisição para buscar uma unidade pela identificação.
	 * 
	 * @param identificacao a identificação da unidade
	 * @return a resposta com a entidade encontrada ou erro se não encontrar
	 */
	@GetMapping("/{identificacao}")
	@ApiOperation("Obtém uma unidade pela identificação")
	public ResponseEntity<ResponseDTO<UnidadeDTO>> getByIdentificacao(@PathVariable String identificacao) {
		ResponseDTO<UnidadeDTO> response = new ResponseDTO<>();

		UnidadeEntity entity = service.findByIdentificacao(identificacao);

		// Se não encontrar a entidade retorna com 404
		if (entity == null) {
			response.addError("Registro não encontrado!");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		// Converte a entidade em DTO
		UnidadeDTO dto = UnidadeConverter.getInstance().convertEntityToDTO(entity);

		// Cria os links para o DTO
		createGenericLink(dto);

		// Atualiza a resposta com os dados
		response.setData(dto);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Requisição para salvar uma unidade.
	 * 
	 * @param dto    a unidade a ser salva
	 * @param result resultado da requisição, para tratamento de erros
	 * @return a resposta com a entidade salva se não ocorrer erros ou a resposta
	 *         com erros
	 */
	@PostMapping
	@ApiOperation("Salva uma unidade")
	public ResponseEntity<ResponseDTO<UnidadeDTO>> save(@RequestBody @Valid UnidadeDTO dto, BindingResult result) {

		ResponseDTO<UnidadeDTO> response = new ResponseDTO<>();

		// Verifica se a requisição gerou erros e faz o tratamento
		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.addError(e.getDefaultMessage()));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// Converte o DTO da requisição em uma entidade
		UnidadeEntity entity = UnidadeConverter.getInstance().convertDTOToEntity(dto);

		// Salva a entidade
		entity = service.save(entity);

		// Converte a entidade salva em um novo DTO
		UnidadeDTO savedDTO = UnidadeConverter.getInstance().convertEntityToDTO(entity);

		// Cria o link genérico para o DTO
		createGenericLink(savedDTO);

		// Atualiza a resposta com os dados
		response.setData(savedDTO);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Requisição para deletar uma unidade pela identificação.
	 * 
	 * @param identificacao a identificação da unidade
	 * @return a resposta com sucesso se deletada ou erro se não encontrar
	 */
	@DeleteMapping("/{identificacao}")
	@ApiOperation("Deleta uma unidade pela identificação")
	public ResponseEntity<ResponseDTO<String>> deleteByIdentificacao(@PathVariable String identificacao) {

		ResponseDTO<String> response = new ResponseDTO<>();

		try {
			service.deleteByIdentificacao(identificacao);
		} catch (EntityNotFoundException e) {
			response.addError(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		response.setData("Registro excluído com sucesso!");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Cria um link para adicionar na resposta da requisição.
	 * 
	 * @param dto o DTO onde será criado o link
	 */
	private void createSelfLink(UnidadeDTO dto) {
		Link link = WebMvcLinkBuilder.linkTo(CondominioController.class).slash(dto.getIdentificacao()).withSelfRel();

		dto.add(link);
	}

	/**
	 * Cria um link para adicionar na resposta da requisição.
	 * 
	 * @param dto o DTO onde será criado o link
	 */
	private void createGenericLink(UnidadeDTO dto) {
		Link link = WebMvcLinkBuilder.linkTo(CondominioController.class).withSelfRel();

		dto.add(link);
	}

}
