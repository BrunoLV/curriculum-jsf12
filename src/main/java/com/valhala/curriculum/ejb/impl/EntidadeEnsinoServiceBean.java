package com.valhala.curriculum.ejb.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.valhala.curriculum.dao.EntidadeEnsinoDao;
import com.valhala.curriculum.dao.impl.EntidadeEnsinoDaoImpl;
import com.valhala.curriculum.ejb.EntidadeEnsinoService;
import com.valhala.curriculum.model.EntidadeEnsino;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EntidadeEnsinoServiceBean extends BaseServiceBean implements EntidadeEnsinoService {

    private EntidadeEnsinoDao entidadeEnsinoDao;

    @Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<EntidadeEnsino> buscarTodos() {
        List<EntidadeEnsino> entidadeEnsinos = this.entidadeEnsinoDao.listarTodos();
        return entidadeEnsinos;
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletar(EntidadeEnsino entidadeEnsino) {
        this.entidadeEnsinoDao.deletar(entidadeEnsino);
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(EntidadeEnsino entidadeEnsino) {
        this.entidadeEnsinoDao.editar(entidadeEnsino);
    }

    @PostConstruct
    private void init() {
        this.entidadeEnsinoDao = new EntidadeEnsinoDaoImpl(this.manager);
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public EntidadeEnsino pesquisarPorId(Serializable id) {
        EntidadeEnsino entidadeEnsino = this.entidadeEnsinoDao.buscarPorId(id);
        return entidadeEnsino;
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public EntidadeEnsino salvar(EntidadeEnsino entidadeEnsino) {
        this.entidadeEnsinoDao.salvar(entidadeEnsino);
        return entidadeEnsino;
    }

}
