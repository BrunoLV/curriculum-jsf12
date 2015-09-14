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

    @Column(name = "data_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Column(name = "data_saida")
    @Temporal(TemporalType.DATE)
    private Date dataSaida;

    @ManyToOne(cascade = CascadeType.ALL)
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
        this.curriculo = curriculo;
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
        int hash = 5;
        hash = 59 * hash + (this.dataInicio != null ? this.dataInicio.hashCode() : 0);
        hash = 59 * hash + (this.dataSaida != null ? this.dataSaida.hashCode() : 0);
        hash = 59 * hash + (this.curriculo != null ? this.curriculo.hashCode() : 0);
        hash = 59 * hash + (this.empresa != null ? this.empresa.hashCode() : 0);
        hash = 59 * hash + (this.cargo != null ? this.cargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExperienciaProfissional other = (ExperienciaProfissional) obj;
        if (!this.dataInicio.equals(other.dataInicio)) {
            return false;
        }
        if (!this.dataSaida.equals(other.dataSaida)) {
            return false;
        }
        if (!this.curriculo.equals(other.curriculo)) {
            return false;
        }
        if (!this.empresa.equals(other.empresa)) {
            return false;
        }
        if (!this.cargo.equals(other.cargo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExperienciaProfissional{" + "id=" + id + ", dataInicio=" + dataInicio + ", dataSaida=" + dataSaida + ", curriculo=" + curriculo + ", empresa=" + empresa + ", cargo=" + cargo + '}';
    }

}
