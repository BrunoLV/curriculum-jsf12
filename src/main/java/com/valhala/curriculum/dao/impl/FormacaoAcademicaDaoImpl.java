package com.valhala.curriculum.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.valhala.curriculum.dao.FormacaoAcademicaDao;
import com.valhala.curriculum.model.FormacaoAcademica;

public class FormacaoAcademicaDaoImpl extends BaseDao<FormacaoAcademica> implements FormacaoAcademicaDao {

    private static final String JPQL_TODAS_FORMACOES_ACADEMICAS = "SELECT f FROM FormacaoAcademica AS f";

    public FormacaoAcademicaDaoImpl(EntityManager manager) {
        this.manager = manager;
        this.classePersistente = FormacaoAcademica.class;
    }

    @Override
	public FormacaoAcademica buscarPorIdComRelacionamento(Serializable id) {
        return null;
    }

    @Override
	public List<FormacaoAcademica> listarTodos() {
        Query query = this.manager.createQuery(JPQL_TODAS_FORMACOES_ACADEMICAS);
        @SuppressWarnings("unchecked")
		List<FormacaoAcademica> formacoes = query.getResultList();
        return formacoes;
    }
}
