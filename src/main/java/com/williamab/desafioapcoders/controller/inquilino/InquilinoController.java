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
 * Controller com requisições para /inquilino
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
	 * Requisição para buscar uma lista de inquilinos a partir da página e do limite
	 * de registros trazidos.
	 * 
	 * @param page  a página a ser filtrada
	 * @param limit o limite de registros
	 * @return a resposta com os inquilinos
	 */
	@GetMapping
	@ApiOperation("Obtém a listagem de inquilinos")
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
	 * Requisição para buscar um inquilino pelo código.
	 * 
	 * @param codigo o código do inquilino
	 * @return a resposta com a entidade encontrada ou erro se não encontrar
	 */
	@GetMapping("/{codigo}")
	@ApiOperation("Obtém um inquilino pelo código")
	public ResponseEntity<ResponseDTO<InquilinoDTO>> getByCodigo(@PathVariable Long codigo) {
		ResponseDTO<InquilinoDTO> response = new ResponseDTO<>();

		InquilinoEntity entity = service.findByCodigo(codigo);

		// Se não encontrar a entidade retorna com 404
		if (entity == null) {
			response.addError("Registro não encontrado!");
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
	 * Requisição para salvar um inquilino.
	 * 
	 * @param dto    o inquilino a ser salvo
	 * @param result resultado da requisição, para tratamento de erros
	 * @return a resposta com a entidade salva se não ocorrer erros ou a resposta
	 *         com erros
	 */
	@PostMapping
	@ApiOperation("Salva um inquilino")
	public ResponseEntity<ResponseDTO<InquilinoDTO>> save(@RequestBody @Valid InquilinoDTO dto, BindingResult result) {

		ResponseDTO<InquilinoDTO> response = new ResponseDTO<>();

		// Verifica se a requisição gerou erros e faz o tratamento
		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.addError(e.getDefaultMessage()));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// Converte o DTO da requisição em uma entidade
		InquilinoEntity entity = InquilinoConverter.getInstance().convertDTOToEntity(dto);

		// Salva a entidade
		entity = service.save(entity);

		// Converte a entidade salva em um novo DTO
		InquilinoDTO savedDTO = InquilinoConverter.getInstance().convertEntityToDTO(entity);

		// Cria o link genérico para o DTO
		createGenericLink(savedDTO);

		// Atualiza a resposta com os dados
		response.setData(savedDTO);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Requisição para deletar um inquilino pelo código.
	 * 
	 * @param codigo o código do inquilino
	 * @return a resposta com sucesso se deletada ou erro se não encontrar
	 */
	@DeleteMapping("/{codigo}")
	@ApiOperation("Deleta um inquilino pelo código")
	public ResponseEntity<ResponseDTO<String>> deleteByCodigo(@PathVariable Long codigo) {

		ResponseDTO<String> response = new ResponseDTO<>();

		try {
			service.deleteByCodigo(codigo);
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
	private void createSelfLink(InquilinoDTO dto) {
		Link link = WebMvcLinkBuilder.linkTo(CondominioController.class).slash(dto.getCodigo()).withSelfRel();

		dto.add(link);
	}

	/**
	 * Cria um link para adicionar na resposta da requisição.
	 * 
	 * @param dto o DTO onde será criado o link
	 */
	private void createGenericLink(InquilinoDTO dto) {
		Link link = WebMvcLinkBuilder.linkTo(CondominioController.class).withSelfRel();

		dto.add(link);
	}

}
