/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author bruno
 */
@Entity
@Table(name = "tb_curriculo")
public class Curriculo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3875979851399236476L;

	@ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "curriculo", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ExperienciaProfissional> experienciasProfissionais = new LinkedHashSet<ExperienciaProfissional>();

    @OneToMany(mappedBy = "curriculo", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<FormacaoAcademica> formacoesAcademicas = new LinkedHashSet<FormacaoAcademica>();

    public Curriculo() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Set<ExperienciaProfissional> getExperienciasProfissionais() {
		return experienciasProfissionais;
	}
    
    public void setExperienciasProfissionais(
			Set<ExperienciaProfissional> experienciasProfissionais) {
		this.experienciasProfissionais = experienciasProfissionais;
	}
    
    public Set<FormacaoAcademica> getFormacoesAcademicas() {
		return formacoesAcademicas;
	}
    
    public void setFormacoesAcademicas(
			Set<FormacaoAcademica> formacoesAcademicas) {
		this.formacoesAcademicas = formacoesAcademicas;
	}

    @Override
    public int hashCode() {
        int hash = 21;
        hash = 31 * hash + (this.usuario == null ? 0 : this.usuario.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
        	return true;
        }
        if (!(obj instanceof Curriculo)) {
			return false;
		}
        final Curriculo other = (Curriculo) obj;
        return other.usuario.equals(this.usuario);
    }

    @Override
    public String toString() {
        return "Curriculo{" + "id=" + id + ", usuario=" + usuario + '}';
    }

}
