package com.williamab.desafioapcoders.model.despesa;

/**
 * Enumerado de status de pagamento.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public enum StatusPagamento {

	NAO_PAGO("Não pago"),
	
	PAGO("Pago");

	private String descricao;

	private StatusPagamento(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}

}
