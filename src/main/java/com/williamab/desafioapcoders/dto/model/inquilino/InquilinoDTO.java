package com.williamab.desafioapcoders.dto.model.inquilino;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.williamab.desafioapcoders.dto.model.BasicDTO;

/**
 * DTO (Data Transfer Object) para inquilino.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class InquilinoDTO extends BasicDTO<InquilinoDTO> {

	@NotNull(message = "Código deve ser informado!")
	private Long codigo;

	@NotBlank(message = "Nome deve ser informado!")
	private String nome;

	@NotNull(message = "Data de nascimento deve ser informada!")
	private Date dataNascimento;

	@Pattern(regexp = "^(MASCULINO|FEMININO)$",
			message = "O sexo deve ser um dos seguintes: MASCULINO ou FEMININO.")
	@NotBlank(message = "Sexo deve ser informado!")
	private String sexo;

	@NotBlank(message = "Telefone deve ser informado!")
	private String telefone;

	private String email;

	public InquilinoDTO() {
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
