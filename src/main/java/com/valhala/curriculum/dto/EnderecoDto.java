package com.valhala.curriculum.dto;

import java.io.Serializable;

import com.valhala.curriculum.dto.enumerados.UFDto;

import lombok.Data;

@Data
public class EnderecoDto implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	
	private UFDto estado;
	
}
