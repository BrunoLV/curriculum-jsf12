package com.valhala.curriculum.dao.impl;

import com.valhala.curriculum.dao.FormacaoAcademicaDao;
import com.valhala.curriculum.model.FormacaoAcademica;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bruno on 17/09/15.
 */
public class FormacaoAcademicaDaoImpl extends BaseDao<FormacaoAcademica> implements FormacaoAcademicaDao {

    private static final String JPQL_ALL_FORMACAO_ACADEMICA = "SELECT f FROM FormacaoAcademica AS f";

    public FormacaoAcademicaDaoImpl(EntityManager manager) {
        this.manager = manager;
        this.classePersistente = FormacaoAcademica.class;
    }

    public FormacaoAcademica buscarPorIdComRelacionamento(Serializable id) {
        return null;
    }

    public List<FormacaoAcademica> listarTodos() {
        Query query = this.manager.createQuery(JPQL_ALL_FORMACAO_ACADEMICA);
        @SuppressWarnings("unchecked")
		List<FormacaoAcademica> formacoes = query.getResultList();
        return formacoes;
    }
}
