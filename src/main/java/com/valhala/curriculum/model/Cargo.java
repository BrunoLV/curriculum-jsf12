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
@Table(name = "tb_cargo")
public class Cargo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3190631462867154400L;
	
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    public Cargo() {
        super();
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
        if (!(obj instanceof Cargo)) {
            return false;
        }
        final Cargo other = (Cargo) obj;
        
        return other.nome.equals(this.nome);
    }

    @Override
    public String toString() {
        return "Cargo{" + "id=" + id + ", nome=" + nome + '}';
    }

}
