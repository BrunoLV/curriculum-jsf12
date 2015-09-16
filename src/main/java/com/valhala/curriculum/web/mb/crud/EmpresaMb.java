/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.web.mb.crud;

import com.valhala.curriculum.dto.EmpresaDto;
import com.valhala.curriculum.ejb.EmpresaService;
import com.valhala.curriculum.mappers.EmpresaMapper;
import com.valhala.curriculum.web.mb.BaseMb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.util.List;

/**
 * @author bruno
 */
public class EmpresaMb extends BaseMb {

    private static final String ID_EDICAO_SESSAO = "idParaEdicao";

    @EJB
    private EmpresaService service;
    private EmpresaDto empresa;
    private List<EmpresaDto> listaEmpresa;

    private EmpresaMapper empresaMapper = EmpresaMapper.INSTANCE;

    @PostConstruct
    private void init() {
        Integer id = (Integer) getAtributoSessao(ID_EDICAO_SESSAO);
        if (id != null && id > 0) {
            this.empresa = empresaMapper.empresaToEmpresaDto(this.service.pesquisarPorId(id));
            removeAtributoSessao(ID_EDICAO_SESSAO);
        } else {
            this.empresa = new EmpresaDto();
        }
        this.listaEmpresa = empresaMapper.listaEmpresaToListaEmpresaDto(this.service.buscarTodos());
    }

    public void salvar() {
        if (empresa.getId() == 0) {
            empresa.setId(null);
            this.service.salvar(empresaMapper.empresaDtoToEmpresa(this.empresa));
            inserirMensagemInformativa("Empresa inserida com sucesso!!!");
        } else {
            this.service.editar(empresaMapper.empresaDtoToEmpresa(this.empresa));
            inserirMensagemInformativa("Empresa editada com sucesso!!!");
        }
    }

    public void deletar() {
        this.service.deletar(empresaMapper.empresaDtoToEmpresa(this.empresa));
        this.listaEmpresa = empresaMapper.listaEmpresaToListaEmpresaDto(this.service.buscarTodos());
        inserirMensagemInformativa("Empresa removida com sucesso!!!");
    }

    public EmpresaDto getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDto empresa) {
        this.empresa = empresa;
    }

    public List<EmpresaDto> getListaEmpresa() {
        return listaEmpresa;
    }

    public void setListaEmpresa(List<EmpresaDto> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

}
