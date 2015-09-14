/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.utils.impl;

import com.valhala.curriculum.dto.CargoDTO;
import com.valhala.curriculum.dto.EmpresaDTO;
import com.valhala.curriculum.dto.ExperienciaProfissionalDTO;
import com.valhala.curriculum.model.Cargo;
import com.valhala.curriculum.model.Empresa;
import com.valhala.curriculum.model.ExperienciaProfissional;
import com.valhala.curriculum.utils.api.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bruno
 */
public class ParserExperienciaToExperienciaDTO implements Parser<ExperienciaProfissional, ExperienciaProfissionalDTO> {

    private static final Parser<Cargo, CargoDTO> PARSER_CARGO = new ParserCargoToCargoDTO();
    private static final Parser<Empresa, EmpresaDTO> PARSER_EMPRESA = new ParserEmpresaToEmpresaDTO();

    public ExperienciaProfissionalDTO parse(ExperienciaProfissional experienciaProfissional) {
        ExperienciaProfissionalDTO dto = new ExperienciaProfissionalDTO();
        dto.setId(experienciaProfissional.getId());
        dto.setDataInicio(experienciaProfissional.getDataInicio());
        dto.setDataSaida(experienciaProfissional.getDataSaida());
        dto.setCargoDTO(PARSER_CARGO.parse(experienciaProfissional.getCargo()));
        dto.setEmpresaDTO(PARSER_EMPRESA.parse(experienciaProfissional.getEmpresa()));
        return dto;
    }

    public ExperienciaProfissional inverseParse(ExperienciaProfissionalDTO dto) {
        ExperienciaProfissional experienciaProfissional = new ExperienciaProfissional();
        experienciaProfissional.setId(dto.getId());
        experienciaProfissional.setDataInicio(dto.getDataInicio());
        experienciaProfissional.setDataSaida(dto.getDataSaida());
        experienciaProfissional.setCargo(PARSER_CARGO.inverseParse(dto.getCargoDTO()));
        experienciaProfissional.setEmpresa(PARSER_EMPRESA.inverseParse(dto.getEmpresaDTO()));
        return experienciaProfissional;
    }

    public List<ExperienciaProfissionalDTO> massiveParse(List<ExperienciaProfissional> list) {
        List<ExperienciaProfissionalDTO> dtos = new ArrayList<ExperienciaProfissionalDTO>();
        for (ExperienciaProfissional experienciaProfissional : list) {
            dtos.add(parse(experienciaProfissional));
        }
        return dtos;
    }

    public List<ExperienciaProfissional> massiveInverseParse(List<ExperienciaProfissionalDTO> list) {
        List<ExperienciaProfissional> experiencias = new ArrayList<ExperienciaProfissional>();
        for (ExperienciaProfissionalDTO dto : list) {
            experiencias.add(inverseParse(dto));
        }
        return experiencias;
    }

    public ExperienciaProfissionalDTO parseWithoutRelationship(ExperienciaProfissional experienciaProfissional) {
        ExperienciaProfissionalDTO dto = new ExperienciaProfissionalDTO();
        dto.setId(experienciaProfissional.getId());
        dto.setDataInicio(experienciaProfissional.getDataInicio());
        dto.setDataSaida(experienciaProfissional.getDataSaida());
        dto.setCargoDTO(PARSER_CARGO.parse(experienciaProfissional.getCargo()));
        dto.setEmpresaDTO(null);
        return dto;
    }

    public List<ExperienciaProfissionalDTO> massiveParseWithoutRelationship(List<ExperienciaProfissional> list) {
        List<ExperienciaProfissionalDTO> dtos = new ArrayList<ExperienciaProfissionalDTO>();
        for (ExperienciaProfissional experienciaProfissional : list) {
            dtos.add(parseWithoutRelationship(experienciaProfissional));
        }
        return dtos;
    }

}
