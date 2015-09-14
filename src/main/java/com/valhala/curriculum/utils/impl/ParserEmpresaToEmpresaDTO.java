/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.utils.impl;

import com.valhala.curriculum.dto.EmpresaDTO;
import com.valhala.curriculum.model.Empresa;
import com.valhala.curriculum.utils.api.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bruno
 */
public class ParserEmpresaToEmpresaDTO implements Parser<Empresa, EmpresaDTO> {

    public EmpresaDTO parse(Empresa empresa) {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(empresa.getId());
        empresaDTO.setNome(empresa.getNome());
        return empresaDTO;
    }

    public Empresa inverseParse(EmpresaDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setId(dto.getId());
        empresa.setNome(dto.getNome());
        return empresa;
    }

    public List<EmpresaDTO> massiveParse(List<Empresa> list) {
        List<EmpresaDTO> dtos = new ArrayList<EmpresaDTO>();
        for (Empresa empresa : list) {
            dtos.add(parse(empresa));
        }
        return dtos;
    }

    public List<Empresa> massiveInverseParse(List<EmpresaDTO> list) {
        List<Empresa> empresas = new ArrayList<Empresa>();
        for (EmpresaDTO dto : list) {
            empresas.add(inverseParse(dto));
        }
        return empresas;
    }

    public EmpresaDTO parseWithoutRelationship(Empresa objeto) {
        return null;
    }

    public List<EmpresaDTO> massiveParseWithoutRelationship(List<Empresa> list) {
        return null;
    }

}
