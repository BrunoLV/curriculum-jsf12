package com.valhala.curriculum.dao.impl;

import com.valhala.curriculum.dao.ExperienciaProfissionalDao;
import com.valhala.curriculum.model.ExperienciaProfissional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by bruno on 07/09/15.
 */
public class ExperienciaProfissionalDaoImpl extends BaseDao<ExperienciaProfissional> implements ExperienciaProfissionalDao {

    private static final String SQL_ALL_EXPERIENCIAS = "SELECT e FROM ExperienciaProfissional AS e";

    public ExperienciaProfissionalDaoImpl(EntityManager manager) {
        this.manager = manager;
        this.classePersistente = ExperienciaProfissional.class;
    }

    public ExperienciaProfissional buscarPorIdComRelacionamento(Serializable id) {
        return null;
    }

    public List<ExperienciaProfissional> listarTodos() {
        Query query = this.manager.createQuery(SQL_ALL_EXPERIENCIAS);
        List<ExperienciaProfissional> experiencias = query.getResultList();
        return experiencias;
    }

}
