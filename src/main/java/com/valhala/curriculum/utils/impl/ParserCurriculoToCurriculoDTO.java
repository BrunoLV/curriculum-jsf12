/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.utils.impl;

import com.valhala.curriculum.dto.CurriculoDTO;
import com.valhala.curriculum.dto.ExperienciaProfissionalDTO;
import com.valhala.curriculum.dto.UsuarioDTO;
import com.valhala.curriculum.model.Curriculo;
import com.valhala.curriculum.model.ExperienciaProfissional;
import com.valhala.curriculum.model.Usuario;
import com.valhala.curriculum.utils.api.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bruno
 */
public class ParserCurriculoToCurriculoDTO implements Parser<Curriculo, CurriculoDTO> {

    private static final Parser<Usuario, UsuarioDTO> PARSER_USUARIO = new ParserUsuarioToUsuarioDTO();
    private static final Parser<ExperienciaProfissional, ExperienciaProfissionalDTO> PARSER_EXPERIENCIA = new ParserExperienciaToExperienciaDTO();

    public CurriculoDTO parse(Curriculo curriculo) {
        CurriculoDTO dto = new CurriculoDTO();
        dto.setId(curriculo.getId());
        dto.setUsuarioDTO(PARSER_USUARIO.parse(curriculo.getUsuario()));
        List<ExperienciaProfissional> experiencias = curriculo.getExperienciasProfissionais();
        if (experiencias != null) {
            for (ExperienciaProfissional experiencia : experiencias) {
                ExperienciaProfissionalDTO dto1 = PARSER_EXPERIENCIA.parse(experiencia);
                dto.getExperienciaProfissionalDTOsIncluir().add(dto1);
            }
        }
        return dto;
    }

    public Curriculo inverseParse(CurriculoDTO dto) {
        Curriculo curriculo = new Curriculo();
        curriculo.setId(dto.getId());
        curriculo.setUsuario(PARSER_USUARIO.inverseParse(dto.getUsuarioDTO()));
        List<ExperienciaProfissionalDTO> experiencias = dto.getExperienciaProfissionalDTOsIncluir();
        return curriculo;
    }

    public List<CurriculoDTO> massiveParse(List<Curriculo> list) {
        List<CurriculoDTO> dtos = new ArrayList<CurriculoDTO>();
        for (Curriculo curriculo : list) {
            dtos.add(parse(curriculo));
        }
        return dtos;
    }

    public List<Curriculo> massiveInverseParse(List<CurriculoDTO> list) {
        List<Curriculo> curriculos = new ArrayList<Curriculo>();
        for (CurriculoDTO dto : list) {
            curriculos.add(inverseParse(dto));
        }
        return curriculos;
    }

    public CurriculoDTO parseWithoutRelationship(Curriculo curriculo) {
        CurriculoDTO dto = new CurriculoDTO();
        dto.setId(curriculo.getId());
        dto.setUsuarioDTO(PARSER_USUARIO.parse(curriculo.getUsuario()));
        List<ExperienciaProfissional> experiencias = new ArrayList<ExperienciaProfissional>();
        return dto;
    }

    public List<CurriculoDTO> massiveParseWithoutRelationship(List<Curriculo> list) {
        List<CurriculoDTO> dtos = new ArrayList<CurriculoDTO>();
        for (Curriculo curriculo : list) {
            dtos.add(parseWithoutRelationship(curriculo));
        }
        return dtos;
    }

}
