package com.williamab.desafioapcoders.dto.model.unidade;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.williamab.desafioapcoders.dto.model.BasicDTO;

/**
 * DTO (Data Transfer Object) para condomínio.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class CondominioDTO extends BasicDTO<CondominioDTO> {

	@NotNull(message = "Código deve ser informado!")
	private Long codigo;

	@NotBlank(message = "Nome deve ser informado!")
	private String nome;

	@NotBlank(message = "Endereço deve ser informado!")
	private String endereco;

	public CondominioDTO() {
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
