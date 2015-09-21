/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bruno
 */
@Entity
@Table(name = "tb_exp_prof_curriculo")
public class ExperienciaProfissional extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 2431264352699029440L;

	@Column(name = "data_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Column(name = "data_saida")
    @Temporal(TemporalType.DATE)
    private Date dataSaida;

    @ManyToOne
    @JoinColumn(name = "id_curriculo")
    private Curriculo curriculo;
    
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
    
    @ManyToOne
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;

    public ExperienciaProfissional() {
    }

    public ExperienciaProfissional(Integer id, Date dataInicio, Date dataSaida, Curriculo curriculo, Empresa empresa, Cargo cargo) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataSaida = dataSaida;
        this.curriculo = curriculo;
        this.empresa = empresa;
        this.cargo = cargo;
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

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
    	if (curriculo == null) {
			if (this.curriculo != null) { 
				this.curriculo.getExperienciasProfissionais().remove(this);
			}
		}
        this.curriculo = curriculo;
        if (curriculo != null) {
            this.curriculo.getExperienciasProfissionais().add(this);
        }
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
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
		if (!(obj instanceof ExperienciaProfissional))
			return false;
		ExperienciaProfissional other = (ExperienciaProfissional) obj;
		
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
