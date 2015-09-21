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
public class EmpresaDto implements Serializable {

    private static final long serialVersionUID = -9097542490569888328L;
	
    private Integer id;
    private String nome;

    public EmpresaDto() {
    }

    public EmpresaDto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EmpresaDto)) {
            return false;
        }
        final EmpresaDto other = (EmpresaDto) obj;
        
        return other.nome.equals(this.nome);
    }

    @Override
    public String toString() {
        return "Empresa{" + "id=" + id + ", nome=" + nome + '}';
    }
    
}
