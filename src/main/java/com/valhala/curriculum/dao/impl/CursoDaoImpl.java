package com.valhala.curriculum.dao.impl;

import com.valhala.curriculum.dao.CursoDao;
import com.valhala.curriculum.model.Curso;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bruno on 17/09/15.
 */
public class CursoDaoImpl extends BaseDao<Curso> implements CursoDao {

    private static final String SQL_ALL_CURSOS = "SELECT c FROM Curso AS c";

    public CursoDaoImpl(EntityManager manager) {
        this.manager = manager;
        this.classePersistente = Curso.class;
    }

    @Override
    public Curso buscarPorIdComRelacionamento(Serializable id) {
        return null;
    }

    @Override
    public List<Curso> listarTodos() {
        Query query = this.manager.createQuery(SQL_ALL_CURSOS);
        @SuppressWarnings("unchecked")
		List<Curso> cursos = query.getResultList();
        return cursos;
    }
}
