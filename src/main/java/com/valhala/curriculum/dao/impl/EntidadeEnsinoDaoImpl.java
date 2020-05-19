package com.valhala.curriculum.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.valhala.curriculum.dao.EntidadeEnsinoDao;
import com.valhala.curriculum.model.EntidadeEnsino;

public class EntidadeEnsinoDaoImpl extends BaseDao<EntidadeEnsino> implements EntidadeEnsinoDao {
    private static final String JPQL_TODAS_ENTIDADES_ENSINOS = "SELECT e FROM EntidadeEnsino AS e";

    public EntidadeEnsinoDaoImpl(EntityManager manager) {
        this.manager = manager;
        this.classePersistente = EntidadeEnsino.class;
    }

    @Override
	public EntidadeEnsino buscarPorIdComRelacionamento(Serializable id) {
        return null;
    }

    @Override
	public List<EntidadeEnsino> listarTodos() {
        Query query = this.manager.createQuery(JPQL_TODAS_ENTIDADES_ENSINOS);
        @SuppressWarnings("unchecked")
		List<EntidadeEnsino> entidadesEnsino = query.getResultList();
        return entidadesEnsino;
    }
}
