/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.web.mb.crud;

import com.valhala.curriculum.dto.EmpresaDTO;
import com.valhala.curriculum.ejb.EmpresaService;
import com.valhala.curriculum.model.Empresa;
import com.valhala.curriculum.utils.api.Parser;
import com.valhala.curriculum.utils.impl.ParserEmpresaToEmpresaDTO;
import com.valhala.curriculum.web.mb.BaseMB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.util.List;

/**
 * @author bruno
 */
public class EmpresaMB extends BaseMB {

    private static final Parser<Empresa, EmpresaDTO> PARSER_EMPRESA = new ParserEmpresaToEmpresaDTO();
    @EJB
    private EmpresaService service;
    private EmpresaDTO empresaDTO;
    private List<EmpresaDTO> empresaDTOs;

    @PostConstruct
    private void init() {
        Integer id = (Integer) getAtributoSessao("idParaEdicao");
        if (id != null && id > 0) {
            this.empresaDTO = PARSER_EMPRESA.parse(this.service.pesquisarPorId(id));
            removeAtributoSessao("idParaEdicao");
        } else {
            this.empresaDTO = new EmpresaDTO();
        }
        this.empresaDTOs = PARSER_EMPRESA.massiveParse(this.service.buscarTodos());
    }

    public void salvar() {
        if (empresaDTO.getId() == 0) {
            empresaDTO.setId(null);
            this.service.salvar(PARSER_EMPRESA.inverseParse(this.empresaDTO));
            inserirMensagemInformativa("Empresa inserida com sucesso!!!");
        } else {
            this.service.editar(PARSER_EMPRESA.inverseParse(this.empresaDTO));
            inserirMensagemInformativa("Empresa editada com sucesso!!!");
        }
    }

    public void deletar() {
        this.service.deletar(PARSER_EMPRESA.inverseParse(this.empresaDTO));
        this.empresaDTOs = PARSER_EMPRESA.massiveParse(this.service.buscarTodos());
        inserirMensagemInformativa("Empresa removida com sucesso!!!");
    }

    public EmpresaDTO getEmpresaDTO() {
        return empresaDTO;
    }

    public void setEmpresaDTO(EmpresaDTO empresaDTO) {
        this.empresaDTO = empresaDTO;
    }

    public List<EmpresaDTO> getEmpresaDTOs() {
        return empresaDTOs;
    }

    public void setEmpresaDTOs(List<EmpresaDTO> empresaDTOs) {
        this.empresaDTOs = empresaDTOs;
    }

}
