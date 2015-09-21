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
    
    @Override
	public int hashCode() {
		int result = 21;
		result = 31 * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = 31 * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = 31 * result + ((dataSaida == null) ? 0 : dataSaida.hashCode());
		result = 31 * result + ((empresa == null) ? 0 : empresa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ExperienciaProfissionalDto))
			return false;
		ExperienciaProfissionalDto other = (ExperienciaProfissionalDto) obj;
		
		return other.dataInicio.equals(this.dataInicio) && 
				other.dataSaida.equals(this.dataSaida) && 
				other.cargo.equals(this.cargo) && 
				other.empresa.equals(this.empresa);
	}

	@Override
	public String toString() {
		return "ExperienciaProfissional ["
				+ (dataInicio != null ? "dataInicio=" + dataInicio + ", " : "")
				+ (dataSaida != null ? "dataSaida=" + dataSaida + ", " : "")
				+ (empresa != null ? "empresa=" + empresa + ", " : "")
				+ (cargo != null ? "cargo=" + cargo : "") + "]";
	}
}
