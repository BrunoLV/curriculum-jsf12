package com.valhala.curriculum.dto;

import java.io.Serializable;
import java.util.Date;

import com.valhala.curriculum.dto.enumerados.TipoFormacaoDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormacaoAcademicaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataInicio;
	private Date dataTermino;
	private CursoDto curso = new CursoDto();
	private EntidadeEnsinoDto entidadeEnsino = new EntidadeEnsinoDto();
	private TipoFormacaoDto tipoFormacao;

}
