package com.valhala.curriculum.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CargoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

}
