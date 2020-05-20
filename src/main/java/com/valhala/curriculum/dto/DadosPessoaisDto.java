package com.valhala.curriculum.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DadosPessoaisDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private String titulo;
	private String email;
	private String telefone;
	
	private EnderecoDto endereco;

}
