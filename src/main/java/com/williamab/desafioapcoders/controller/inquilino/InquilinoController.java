package com.williamab.desafioapcoders.controller.inquilino;

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
import com.williamab.desafioapcoders.controller.unidade.CondominioController;
import com.williamab.desafioapcoders.dto.model.inquilino.InquilinoDTO;
import com.williamab.desafioapcoders.dto.response.ResponseDTO;
import com.williamab.desafioapcoders.dto.response.ResponsePageableDTO;
import com.williamab.desafioapcoders.model.inquilino.InquilinoEntity;
import com.williamab.desafioapcoders.service.inquilino.InquilinoService;
import com.williamab.desafioapcoders.util.converter.inquilino.InquilinoConverter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller com requisi��es para /inquilino
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Api
@RestController
@RequestMapping("/api/inquilino")
public class InquilinoController extends BasicController {

	@Autowired
	private InquilinoService service;

	/**
	 * Requisi��o para buscar uma lista de inquilinos a partir da p�gina e do limite
	 * de registros trazidos.
	 * 
	 * @param page  a p�gina a ser filtrada
	 * @param limit o limite de registros
	 * @return a resposta com os inquilinos
	 */
	@GetMapping
	@ApiOperation("Obt�m a listagem de inquilinos")
	public ResponseEntity<ResponsePageableDTO<List<InquilinoDTO>>> getByPage(
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "20") int limit) {

		ResponsePageableDTO<List<InquilinoDTO>> response = new ResponsePageableDTO<>();

		// Busca as entidades
		Page<InquilinoEntity> pageEntities = service.findByPage(page - 1, limit);

		List<InquilinoDTO> inquilinos = new ArrayList<>();

		// Converte as entidades em DTOs
		pageEntities.forEach(e -> {
			InquilinoDTO dto = InquilinoConverter.getInstance().convertEntityToDTO(e);

			// Cria o link para o DTO
			createSelfLink(dto);
			inquilinos.add(dto);
		});

		// Atualiza a resposta com os dados
		response.setData(inquilinos);
		response.addPageInfo(pageEntities);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Requisi��o para buscar um inquilino pelo c�digo.
	 * 
	 * @param codigo o c�digo do inquilino
	 * @return a resposta com a entidade encontrada ou erro se n�o encontrar
	 */
	@GetMapping("/{codigo}")
	@ApiOperation("Obt�m um inquilino pelo c�digo")
	public ResponseEntity<ResponseDTO<InquilinoDTO>> getByCodigo(@PathVariable Long codigo) {
		ResponseDTO<InquilinoDTO> response = new ResponseDTO<>();

		InquilinoEntity entity = service.findByCodigo(codigo);

		// Se n�o encontrar a entidade retorna com 404
		if (entity == null) {
			response.addError("Registro n�o encontrado!");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		// Converte a entidade em DTO
		InquilinoDTO dto = InquilinoConverter.getInstance().convertEntityToDTO(entity);

		// Cria os links para o DTO
		createGenericLink(dto);

		// Atualiza a resposta com os dados
		response.setData(dto);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Requisi��o para salvar um inquilino.
	 * 
	 * @param dto    o inquilino a ser salvo
	 * @param result resultado da requisi��o, para tratamento de erros
	 * @return a resposta com a entidade salva se n�o ocorrer erros ou a resposta
	 *         com erros
	 */
	@PostMapping
	@ApiOperation("Salva um inquilino")
	public ResponseEntity<ResponseDTO<InquilinoDTO>> save(@RequestBody @Valid InquilinoDTO dto, BindingResult result) {

		ResponseDTO<InquilinoDTO> response = new ResponseDTO<>();

		// Verifica se a requisi��o gerou erros e faz o tratamento
		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.addError(e.getDefaultMessage()));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// Converte o DTO da requisi��o em uma entidade
		InquilinoEntity entity = InquilinoConverter.getInstance().convertDTOToEntity(dto);

		// Salva a entidade
		entity = service.save(entity);

		// Converte a entidade salva em um novo DTO
		InquilinoDTO savedDTO = InquilinoConverter.getInstance().convertEntityToDTO(entity);

		// Cria o link gen�rico para o DTO
		createGenericLink(savedDTO);

		// Atualiza a resposta com os dados
		response.setData(savedDTO);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Requisi��o para deletar um inquilino pelo c�digo.
	 * 
	 * @param codigo o c�digo do inquilino
	 * @return a resposta com sucesso se deletada ou erro se n�o encontrar
	 */
	@DeleteMapping("/{codigo}")
	@ApiOperation("Deleta um inquilino pelo c�digo")
	public ResponseEntity<ResponseDTO<String>> deleteByCodigo(@PathVariable Long codigo) {

		ResponseDTO<String> response = new ResponseDTO<>();

		try {
			service.deleteByCodigo(codigo);
		} catch (EntityNotFoundException e) {
			response.addError(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		response.setData("Registro exclu�do com sucesso!");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Cria um link para adicionar na resposta da requisi��o.
	 * 
	 * @param dto o DTO onde ser� criado o link
	 */
	private void createSelfLink(InquilinoDTO dto) {
		Link link = WebMvcLinkBuilder.linkTo(CondominioController.class).slash(dto.getCodigo()).withSelfRel();

		dto.add(link);
	}

	/**
	 * Cria um link para adicionar na resposta da requisi��o.
	 * 
	 * @param dto o DTO onde ser� criado o link
	 */
	private void createGenericLink(InquilinoDTO dto) {
		Link link = WebMvcLinkBuilder.linkTo(CondominioController.class).withSelfRel();

		dto.add(link);
	}

}
