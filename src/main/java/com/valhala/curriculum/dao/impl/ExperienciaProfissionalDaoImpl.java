package com.valhala.curriculum.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.valhala.curriculum.dao.ExperienciaProfissionalDao;
import com.valhala.curriculum.model.ExperienciaProfissional;

public class ExperienciaProfissionalDaoImpl extends BaseDao<ExperienciaProfissional> implements ExperienciaProfissionalDao {

    private static final String JPQL_TODAS_EXPERIENCIAS = "SELECT e FROM ExperienciaProfissional AS e";

    public ExperienciaProfissionalDaoImpl(EntityManager manager) {
        this.manager = manager;
        this.classePersistente = ExperienciaProfissional.class;
    }

    @Override
	public ExperienciaProfissional buscarPorIdComRelacionamento(Serializable id) {
        return null;
    }

    @Override
	public List<ExperienciaProfissional> listarTodos() {
        Query query = this.manager.createQuery(JPQL_TODAS_EXPERIENCIAS);
        @SuppressWarnings("unchecked")
		List<ExperienciaProfissional> experiencias = query.getResultList();
        return experiencias;
    }

}
