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
public class CurriculoDTO implements Serializable {

    private Integer id;
    private UsuarioDTO usuarioDTO = new UsuarioDTO();
    private List<ExperienciaProfissionalDTO> experienciaProfissionalDTOsIncluir = new ArrayList<ExperienciaProfissionalDTO>();
    private List<ExperienciaProfissionalDTO> experienciaProfissionalDTOsRemover = new ArrayList<ExperienciaProfissionalDTO>();

    public CurriculoDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public List<ExperienciaProfissionalDTO> getExperienciaProfissionalDTOsIncluir() {
        return experienciaProfissionalDTOsIncluir;
    }

    public List<ExperienciaProfissionalDTO> getExperienciaProfissionalDTOsRemover() {
        return experienciaProfissionalDTOsRemover;
    }
}
