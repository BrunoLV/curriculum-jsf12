/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bruno
 */
public class CurriculoDto implements Serializable {

    private static final long serialVersionUID = 8152403301735095677L;
	
    private Integer id;
    private UsuarioDto usuario = new UsuarioDto();
    private List<ExperienciaProfissionalDto> listaExperienciaProfissional = new ArrayList<ExperienciaProfissionalDto>();
    private List<FormacaoAcademicaDto> listaFormacaoAcademica = new ArrayList<FormacaoAcademicaDto>();

    public CurriculoDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public List<ExperienciaProfissionalDto> getListaExperienciaProfissional() {
        return listaExperienciaProfissional;
    }

    public void setListaExperienciaProfissional(List<ExperienciaProfissionalDto> listaExperienciaProfissional) {
        this.listaExperienciaProfissional = listaExperienciaProfissional;
    }

    public List<FormacaoAcademicaDto> getListaFormacaoAcademica() {
        return listaFormacaoAcademica;
    }

    public void setListaFormacaoAcademica(List<FormacaoAcademicaDto> listaFormacaoAcademica) {
        this.listaFormacaoAcademica = listaFormacaoAcademica;
    }
}
