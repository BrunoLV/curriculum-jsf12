/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

/**
 * @author bruno
 */
@Entity
@Table(name = "tb_usuario")
public class Usuario extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 6995562910263380576L;

	@Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "senha", unique = true, nullable = false)
    private String senha;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_papeis_usuario", joinColumns = @JoinColumn(name = "id_usuario"))
    @Column(name = "papel", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Roles> papeis = new ArrayList<Roles>();

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Roles> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Roles> papeis) {
		this.papeis = papeis;
	}

    @Override
    public int hashCode() {
        int hash = 21;
        hash = 31 * hash + (this.email == null ? 0 : this.email.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Usuario)) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        
        return this.email.equals(other.email);
    }

    @Override
    public String toString() {
    	return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + '}';
    }

}
