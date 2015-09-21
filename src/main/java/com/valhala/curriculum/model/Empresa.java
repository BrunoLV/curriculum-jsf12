/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author bruno
 */
@Entity
@Table(name = "tb_empresa")
public class Empresa extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -573042122626358501L;
	
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    public Empresa() {
    }

    public Empresa(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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
        if (!(obj instanceof Empresa)) {
            return false;
        }
        final Empresa other = (Empresa) obj;
        
        return other.nome.equals(this.nome);
    }

    @Override
    public String toString() {
        return "Empresa{" + "id=" + id + ", nome=" + nome + '}';
    }

}
