/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.ejb.impl;

import com.valhala.curriculum.dao.EmpresaDao;
import com.valhala.curriculum.dao.impl.EmpresaDaoImpl;
import com.valhala.curriculum.ejb.EmpresaService;
import com.valhala.curriculum.model.Empresa;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author bruno
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EmpresaServiceBean extends BaseServiceBean implements EmpresaService {

    private EmpresaDao empresaDao;

    @PostConstruct
    private void init() {
        this.empresaDao = new EmpresaDaoImpl(this.manager);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Empresa salvar(Empresa empresa) {
        this.empresaDao.salvar(empresa);
        return empresa;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Empresa empresa) {
        this.empresaDao.editar(empresa);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletar(Empresa empresa) {
        this.empresaDao.deletar(empresa);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Empresa pesquisarPorId(Serializable id) {
        Empresa empresa = this.empresaDao.buscarPorId(id);
        return empresa;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Empresa> buscarTodos() {
        List<Empresa> empresas = this.empresaDao.listarTodos();
        return empresas;
    }

}
