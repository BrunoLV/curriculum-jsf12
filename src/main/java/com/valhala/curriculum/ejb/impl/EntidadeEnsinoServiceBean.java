package com.valhala.curriculum.ejb.impl;

import com.valhala.curriculum.dao.EntidadeEnsinoDao;
import com.valhala.curriculum.dao.impl.EntidadeEnsinoDaoImpl;
import com.valhala.curriculum.ejb.EntidadeEnsinoService;
import com.valhala.curriculum.model.EntidadeEnsino;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by bruno on 17/09/15.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EntidadeEnsinoServiceBean extends BaseServiceBean implements EntidadeEnsinoService {

    private EntidadeEnsinoDao entidadeEnsinoDao;

    @PostConstruct
    private void init() {
        this.entidadeEnsinoDao = new EntidadeEnsinoDaoImpl(this.manager);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public EntidadeEnsino salvar(EntidadeEnsino entidadeEnsino) {
        this.entidadeEnsinoDao.salvar(entidadeEnsino);
        return entidadeEnsino;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(EntidadeEnsino entidadeEnsino) {
        this.entidadeEnsinoDao.editar(entidadeEnsino);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletar(EntidadeEnsino entidadeEnsino) {
        this.entidadeEnsinoDao.deletar(entidadeEnsino);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public EntidadeEnsino pesquisarPorId(Serializable id) {
        EntidadeEnsino entidadeEnsino = this.entidadeEnsinoDao.buscarPorId(id);
        return entidadeEnsino;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<EntidadeEnsino> buscarTodos() {
        List<EntidadeEnsino> entidadeEnsinos = this.entidadeEnsinoDao.listarTodos();
        return entidadeEnsinos;
    }

}
