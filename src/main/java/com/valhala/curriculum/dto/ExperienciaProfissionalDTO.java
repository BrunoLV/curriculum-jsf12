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
public class ExperienciaProfissionalDTO implements Serializable {

    private Integer id;
    private Date dataInicio;
    private Date dataSaida;
    private CargoDTO cargoDTO = new CargoDTO();
    private EmpresaDTO empresaDTO = new EmpresaDTO();
    private CurriculoDTO curriculoDTO = new CurriculoDTO();

    public ExperienciaProfissionalDTO() {
    }

    public ExperienciaProfissionalDTO(Integer id, Date dataInicio, Date dataSaida, CargoDTO cargoDTO, EmpresaDTO empresaDTO) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataSaida = dataSaida;
        this.cargoDTO = cargoDTO;
        this.empresaDTO = empresaDTO;
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

    public CargoDTO getCargoDTO() {
        return cargoDTO;
    }

    public void setCargoDTO(CargoDTO cargoDTO) {
        this.cargoDTO = cargoDTO;
    }

    public EmpresaDTO getEmpresaDTO() {
        return empresaDTO;
    }

    public void setEmpresaDTO(EmpresaDTO empresaDTO) {
        this.empresaDTO = empresaDTO;
    }

    public CurriculoDTO getCurriculoDTO() {
        return curriculoDTO;
    }

    public void setCurriculoDTO(CurriculoDTO curriculoDTO) {
        this.curriculoDTO = curriculoDTO;
    }
}
