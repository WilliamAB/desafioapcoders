package com.williamab.desafioapcoders.dto.response;

import org.springframework.data.domain.Page;

/**
 * Estrutura genérica de resposta para as requisições da aplicação que contenham
 * paginação.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 * 
 * @param T o tipo do dado da resposta
 */
public class ResponsePageableDTO<T> extends ResponseDTO<T> {

	private ResponsePageDTO pageInfo;

	public ResponsePageableDTO() {
	}

	public ResponsePageDTO getPageInfo() {
		return pageInfo;
	}

	/**
	 * Adiciona as informações da página a partir de um {@link Page}.
	 * 
	 * @param page um {@link Page} com as informações da página
	 */
	public void addPageInfo(Page<?> page) {
		ResponsePageDTO pageInfo = new ResponsePageDTO();

		pageInfo.setPaginasTotal(page.getTotalPages());
		pageInfo.setPaginaAtual(page.getNumber() + 1);
		pageInfo.setRegistrosTotal(page.getTotalElements());
		pageInfo.setRegistrosPagina(page.getNumberOfElements());
		pageInfo.setLimit(page.getSize());

		this.pageInfo = pageInfo;
	}

}
