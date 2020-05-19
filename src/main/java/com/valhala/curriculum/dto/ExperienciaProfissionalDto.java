package com.valhala.curriculum.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ExperienciaProfissionalDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
    private Date dataInicio;
    private Date dataSaida;
    private CargoDto cargo = new CargoDto();
    private EmpresaDto empresa = new EmpresaDto();

}
