/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.dto;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author bruno
 */
public class CurriculoDto implements Serializable {

    private static final long serialVersionUID = 8152403301735095677L;
	
    private Integer id;
    private UsuarioDto usuario = new UsuarioDto();
    private Set<ExperienciaProfissionalDto> experienciasProfissionais = new LinkedHashSet<ExperienciaProfissionalDto>();
    private Set<FormacaoAcademicaDto> formacoesAcademicas = new LinkedHashSet<FormacaoAcademicaDto>();

    public CurriculoDto() {
    	super();
	}

    public UsuarioDto getUsuario() {
        return usuario;
    }
    
    public Integer getId() {
		return id;
	}
    
    public void setId(Integer id) {
		this.id = id;
	}

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }
    
    public Set<ExperienciaProfissionalDto> getExperienciasProfissionais() {
		return experienciasProfissionais;
	}
    
    public void setExperienciasProfissionais(Set<ExperienciaProfissionalDto> experienciasProfissionais) {
		this.experienciasProfissionais = experienciasProfissionais;
	}
    
    public Set<FormacaoAcademicaDto> getFormacoesAcademicas() {
		return formacoesAcademicas;
	}
    
    public void setFormacoesAcademicas(
			Set<FormacaoAcademicaDto> formacoesAcademicas) {
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
        if (!(obj instanceof CurriculoDto)) {
			return false;
		}
        final CurriculoDto other = (CurriculoDto) obj;
        return other.usuario.equals(this.usuario);
    }

    @Override
    public String toString() {
        return "Curriculo{" + "id=" + id + ", usuario=" + usuario + '}';
    }
}
