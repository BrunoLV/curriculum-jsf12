package com.valhala.curriculum.ejb.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.valhala.curriculum.dao.EmpresaDao;
import com.valhala.curriculum.dao.impl.EmpresaDaoImpl;
import com.valhala.curriculum.ejb.EmpresaService;
import com.valhala.curriculum.model.Empresa;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EmpresaServiceBean extends BaseServiceBean implements EmpresaService {

    private EmpresaDao empresaDao;

    @Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Empresa> buscarTodos() {
        List<Empresa> empresas = this.empresaDao.listarTodos();
        return empresas;
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletar(Empresa empresa) {
        this.empresaDao.deletar(empresa);
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Empresa empresa) {
        this.empresaDao.editar(empresa);
    }

    @PostConstruct
    private void init() {
        this.empresaDao = new EmpresaDaoImpl(this.manager);
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Empresa pesquisarPorId(Serializable id) {
        Empresa empresa = this.empresaDao.buscarPorId(id);
        return empresa;
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Empresa salvar(Empresa empresa) {
        this.empresaDao.salvar(empresa);
        return empresa;
    }

}
