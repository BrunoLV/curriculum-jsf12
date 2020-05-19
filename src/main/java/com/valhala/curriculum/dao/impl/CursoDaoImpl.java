package com.valhala.curriculum.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.valhala.curriculum.dao.CursoDao;
import com.valhala.curriculum.model.Curso;

public class CursoDaoImpl extends BaseDao<Curso> implements CursoDao {

    private static final String JPQL_TODOS_CURSOS = "SELECT c FROM Curso AS c";

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
        Query query = this.manager.createQuery(JPQL_TODOS_CURSOS);
        @SuppressWarnings("unchecked")
		List<Curso> cursos = query.getResultList();
        return cursos;
    }
}
