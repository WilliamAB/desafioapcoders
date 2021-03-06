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
import com.williamab.desafioapcoders.dto.model.unidade.CondominioDTO;
import com.williamab.desafioapcoders.dto.response.ResponseDTO;
import com.williamab.desafioapcoders.dto.response.ResponsePageableDTO;
import com.williamab.desafioapcoders.model.unidade.CondominioEntity;
import com.williamab.desafioapcoders.service.unidade.CondominioService;
import com.williamab.desafioapcoders.util.converter.unidade.CondominioConverter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller com requisi??es para /condominio
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Api
@RestController
@RequestMapping("/api/condominio")
public class CondominioController extends BasicController {

	@Autowired
	private CondominioService service;

	/**
	 * Requisi??o para buscar uma lista de condom?nios a partir da p?gina e do
	 * limite de registros trazidos.
	 * 
	 * @param page  a p?gina a ser filtrada
	 * @param limit o limite de registros
	 * @return a resposta com os condom?nios
	 */
	@GetMapping
	@ApiOperation("Obt?m a listagem de condom?nios")
	public ResponseEntity<ResponsePageableDTO<List<CondominioDTO>>> getByPage(
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "20") int limit) {

		ResponsePageableDTO<List<CondominioDTO>> response = new ResponsePageableDTO<>();

		// Busca as entidades
		Page<CondominioEntity> pageEntities = service.findByPage(page - 1, limit);

		List<CondominioDTO> condominios = new ArrayList<>();

		// Converte as entidades em DTOs
		pageEntities.forEach(e -> {
			CondominioDTO dto = CondominioConverter.getInstance().convertEntityToDTO(e);

			// Cria o link para o DTO
			createSelfLink(dto);
			condominios.add(dto);
		});

		// Atualiza a resposta com os dados
		response.setData(condominios);
		response.addPageInfo(pageEntities);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Requisi??o para buscar um condom?nio pelo c?digo.
	 * 
	 * @param codigo o c?digo do condom?nio
	 * @return a resposta com a entidade encontrada ou erro se n?o encontrar
	 */
	@GetMapping("/{codigo}")
	@ApiOperation("Obt?m um condom?nio pelo c?digo")
	public ResponseEntity<ResponseDTO<CondominioDTO>> getByCodigo(@PathVariable Long codigo) {
		ResponseDTO<CondominioDTO> response = new ResponseDTO<>();

		CondominioEntity entity = service.findByCodigo(codigo);

		// Se n?o encontrar a entidade retorna com 404
		if (entity == null) {
			response.addError("Registro n?o encontrado!");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		// Converte a entidade em DTO
		CondominioDTO dto = CondominioConverter.getInstance().convertEntityToDTO(entity);

		// Cria os links para o DTO
		createGenericLink(dto);

		// Atualiza a resposta com os dados
		response.setData(dto);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Requisi??o para salvar um condom?nio.
	 * 
	 * @param dto    o condom?nio a ser salvo
	 * @param result resultado da requisi??o, para tratamento de erros
	 * @return a resposta com a entidade salva se n?o ocorrer erros ou a resposta
	 *         com erros
	 */
	@PostMapping
	@ApiOperation("Salva um condom?nio")
	public ResponseEntity<ResponseDTO<CondominioDTO>> save(@RequestBody @Valid CondominioDTO dto,
			BindingResult result) {

		ResponseDTO<CondominioDTO> response = new ResponseDTO<>();

		// Verifica se a requisi??o gerou erros e faz o tratamento
		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.addError(e.getDefaultMessage()));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// Converte o DTO da requisi??o em uma entidade
		CondominioEntity entity = CondominioConverter.getInstance().convertDTOToEntity(dto);

		// Salva a entidade
		entity = service.save(entity);

		// Converte a entidade salva em um novo DTO
		CondominioDTO savedDTO = CondominioConverter.getInstance().convertEntityToDTO(entity);

		// Cria o link gen?rico para o DTO
		createGenericLink(savedDTO);

		// Atualiza a resposta com os dados
		response.setData(savedDTO);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Requisi??o para deletar um condom?nio pelo c?digo.
	 * 
	 * @param codigo o c?digo do condom?nio
	 * @return a resposta com sucesso se deletada ou erro se n?o encontrar
	 */
	@DeleteMapping("/{codigo}")
	@ApiOperation("Deleta um condom?nio pelo c?digo")
	public ResponseEntity<ResponseDTO<String>> deleteByCodigo(@PathVariable Long codigo) {

		ResponseDTO<String> response = new ResponseDTO<>();

		try {
			service.deleteByCodigo(codigo);
		} catch (EntityNotFoundException e) {
			response.addError(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		response.setData("Registro exclu?do com sucesso!");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Cria um link para adicionar na resposta da requisi??o.
	 * 
	 * @param dto o DTO onde ser? criado o link
	 */
	private void createSelfLink(CondominioDTO dto) {
		Link link = WebMvcLinkBuilder.linkTo(CondominioController.class).slash(dto.getCodigo()).withSelfRel();

		dto.add(link);
	}

	/**
	 * Cria um link para adicionar na resposta da requisi??o.
	 * 
	 * @param dto o DTO onde ser? criado o link
	 */
	private void createGenericLink(CondominioDTO dto) {
		Link link = WebMvcLinkBuilder.linkTo(CondominioController.class).withSelfRel();

		dto.add(link);
	}

}
