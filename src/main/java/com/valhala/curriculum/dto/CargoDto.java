/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.dto;

import java.io.Serializable;

/**
 * @author bruno
 */
public class CargoDto implements Serializable {

    private static final long serialVersionUID = -747323525388389196L;
	
    private Integer id;
    private String nome;

    public CargoDto() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 21;
        hash = 31 * hash + (this.nome == null ? 0 : this.nome.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CargoDto)) {
            return false;
        }
        final CargoDto other = (CargoDto) obj;
        
        return other.nome.equals(this.nome);
    }

    @Override
    public String toString() {
        return "Cargo{" + "id=" + id + ", nome=" + nome + '}';
    }

}
