/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bruno
 */
public class ExperienciaProfissionalDto implements Serializable {

    private static final long serialVersionUID = -4149267492527781920L;
	
    private Integer id;
    private Date dataInicio;
    private Date dataSaida;
    private CargoDto cargo = new CargoDto();
    private EmpresaDto empresa = new EmpresaDto();
    private CurriculoDto curriculo = new CurriculoDto();

    public ExperienciaProfissionalDto() {
    }

    public ExperienciaProfissionalDto(Integer id, Date dataInicio, Date dataSaida, CargoDto cargo, EmpresaDto empresa) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataSaida = dataSaida;
        this.cargo = cargo;
        this.empresa = empresa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public CargoDto getCargo() {
        return cargo;
    }

    public void setCargo(CargoDto cargo) {
        this.cargo = cargo;
    }

    public EmpresaDto getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDto empresa) {
        this.empresa = empresa;
    }

    public CurriculoDto getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(CurriculoDto curriculo) {
        this.curriculo = curriculo;
    }
}
