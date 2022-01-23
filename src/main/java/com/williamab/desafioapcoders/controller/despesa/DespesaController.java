package com.williamab.desafioapcoders.controller.despesa;

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
import com.williamab.desafioapcoders.dto.model.despesa.DespesaDTO;
import com.williamab.desafioapcoders.dto.response.ResponseDTO;
import com.williamab.desafioapcoders.dto.response.ResponsePageableDTO;
import com.williamab.desafioapcoders.model.despesa.DespesaEntity;
import com.williamab.desafioapcoders.service.despesa.DespesaService;
import com.williamab.desafioapcoders.util.converter.despesa.DespesaConverter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller com requisi��es para /despesa
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Api
@RestController
@RequestMapping("/api/despesa")
public class DespesaController extends BasicController {

	@Autowired
	private DespesaService service;

	/**
	 * Requisi��o para buscar uma lista de despesas a partir da p�gina e do limite
	 * de registros trazidos.
	 * 
	 * @param filter o DTO com os filtros poss�veis para a requisi��o
	 * @param page  a p�gina a ser filtrada
	 * @param limit o limite de registros
	 * @return a resposta com as despesas
	 */
	@GetMapping
	@ApiOperation("Obt�m a listagem de despesas")
	public ResponseEntity<ResponsePageableDTO<List<DespesaDTO>>> getByPage(
			@RequestParam(name = "unidadeIdentificacao", required = false) String unidadeIdentificacao,
			@RequestParam(name = "faturaVencida", defaultValue = "false", required = false) boolean faturaVencida,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "20") int limit) {

		ResponsePageableDTO<List<DespesaDTO>> response = new ResponsePageableDTO<>();

		// Busca as entidades
		Page<DespesaEntity> pageEntities = null;

		try {
			pageEntities = service.findWithFilter(unidadeIdentificacao, faturaVencida, page - 1, limit);
		} catch (EntityNotFoundException e) {
			response.addError(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response.addError(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		List<DespesaDTO> despesas = new ArrayList<>();

		// Converte as entidades em DTOs
		pageEntities.forEach(e -> {
			DespesaDTO dto = DespesaConverter.getInstance().convertEntityToDTO(e);

			// Cria o link para o DTO
			createSelfLink(dto);
			despesas.add(dto);
		});

		// Atualiza a resposta com os dados
		response.setData(despesas);
		response.addPageInfo(pageEntities);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Requisi��o para buscar uma despesa pelo c�digo.
	 * 
	 * @param codigo o c�digo da despesa
	 * @return a resposta com a entidade encontrada ou erro se n�o encontrar
	 */
	@GetMapping("/{codigo}")
	@ApiOperation("Obt�m uma despesa pelo c�digo")
	public ResponseEntity<ResponseDTO<DespesaDTO>> getByCodigo(@PathVariable Long codigo) {
		ResponseDTO<DespesaDTO> response = new ResponseDTO<>();

		DespesaEntity entity = service.findByCodigo(codigo);

		// Se n�o encontrar a entidade retorna com 404
		if (entity == null) {
			response.addError("Registro n�o encontrado!");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		// Converte a entidade em DTO
		DespesaDTO dto = DespesaConverter.getInstance().convertEntityToDTO(entity);

		// Cria os links para o DTO
		createGenericLink(dto);

		// Atualiza a resposta com os dados
		response.setData(dto);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Requisi��o para salvar uma despesa.
	 * 
	 * @param dto    a despesa a ser salva
	 * @param result resultado da requisi��o, para tratamento de erros
	 * @return a resposta com a entidade salva se n�o ocorrer erros ou a resposta
	 *         com erros
	 */
	@PostMapping
	@ApiOperation("Salva uma despesa")
	public ResponseEntity<ResponseDTO<DespesaDTO>> save(@RequestBody @Valid DespesaDTO dto, BindingResult result) {

		ResponseDTO<DespesaDTO> response = new ResponseDTO<>();

		// Verifica se a requisi��o gerou erros e faz o tratamento
		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.addError(e.getDefaultMessage()));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// Converte o DTO da requisi��o em uma entidade
		DespesaEntity entity = DespesaConverter.getInstance().convertDTOToEntity(dto);

		// Salva a entidade
		entity = service.save(entity);

		// Converte a entidade salva em um novo DTO
		DespesaDTO savedDTO = DespesaConverter.getInstance().convertEntityToDTO(entity);

		// Cria o link gen�rico para o DTO
		createGenericLink(savedDTO);

		// Atualiza a resposta com os dados
		response.setData(savedDTO);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Requisi��o para deletar uma despesa pelo c�digo.
	 * 
	 * @param codigo o c�digo da despesa
	 * @return a resposta com sucesso se deletada ou erro se n�o encontrar
	 */
	@DeleteMapping("/{codigo}")
	@ApiOperation("Deleta uma despesa pelo c�digo")
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
	private void createSelfLink(DespesaDTO dto) {
		Link link = WebMvcLinkBuilder.linkTo(CondominioController.class).slash(dto.getCodigo()).withSelfRel();

		dto.add(link);
	}

	/**
	 * Cria um link para adicionar na resposta da requisi��o.
	 * 
	 * @param dto o DTO onde ser� criado o link
	 */
	private void createGenericLink(DespesaDTO dto) {
		Link link = WebMvcLinkBuilder.linkTo(CondominioController.class).withSelfRel();

		dto.add(link);
	}

}
