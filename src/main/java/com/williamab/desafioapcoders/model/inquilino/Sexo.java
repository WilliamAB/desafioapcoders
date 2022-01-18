package com.williamab.desafioapcoders.model.inquilino;

/**
 * Enumerado de sexo (Masculino/Feminino).
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public enum Sexo {

	MASCULINO("Masculino"),
	FEMININO("Feminino");

	private String descricao;

	private Sexo(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}

}
