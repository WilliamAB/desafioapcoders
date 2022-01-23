package com.williamab.desafioapcoders.dto.response;

import com.williamab.desafioapcoders.util.APIUtils;

/**
 * Estrutura com as informações de paginação do resultado.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class ResponsePageDTO {

	private int paginasTotal;
	private int paginaAtual;
	private long registrosTotal;
	private int registrosPagina;
	private int limit;
	private int maxLimit = APIUtils.PAGE_MAX_LIMIT;

	public ResponsePageDTO() {
	}

	public int getPaginasTotal() {
		return paginasTotal;
	}

	public void setPaginasTotal(int paginasTotal) {
		this.paginasTotal = paginasTotal;
	}

	public int getPaginaAtual() {
		return paginaAtual;
	}

	public void setPaginaAtual(int paginaAtual) {
		this.paginaAtual = paginaAtual;
	}

	public long getRegistrosTotal() {
		return registrosTotal;
	}

	public void setRegistrosTotal(long registrosTotal) {
		this.registrosTotal = registrosTotal;
	}

	public int getRegistrosPagina() {
		return registrosPagina;
	}

	public void setRegistrosPagina(int registrosPagina) {
		this.registrosPagina = registrosPagina;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getMaxLimit() {
		return maxLimit;
	}

}
