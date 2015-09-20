package com.valhala.curriculum.dao.impl;

import com.valhala.curriculum.dao.EntidadeEnsinoDao;
import com.valhala.curriculum.model.EntidadeEnsino;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bruno on 17/09/15.
 */
public class EntidadeEnsinoDaoImpl extends BaseDao<EntidadeEnsino> implements EntidadeEnsinoDao {
    private static final String JPQL_ALL_ENTIDADE_ENSINO = "SELECT e FROM EntidadeEnsino AS e";

    public EntidadeEnsinoDaoImpl(EntityManager manager) {
        this.manager = manager;
        this.classePersistente = EntidadeEnsino.class;
    }

    public EntidadeEnsino buscarPorIdComRelacionamento(Serializable id) {
        return null;
    }

    public List<EntidadeEnsino> listarTodos() {
        Query query = this.manager.createQuery(JPQL_ALL_ENTIDADE_ENSINO);
        @SuppressWarnings("unchecked")
		List<EntidadeEnsino> entidadesEnsino = query.getResultList();
        return entidadesEnsino;
    }
}
