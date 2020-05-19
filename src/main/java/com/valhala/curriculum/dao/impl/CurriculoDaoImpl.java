package com.valhala.curriculum.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.valhala.curriculum.dao.CurriculoDao;
import com.valhala.curriculum.model.Curriculo;

public class CurriculoDaoImpl extends BaseDao<Curriculo> implements CurriculoDao {

    private static final String JPQL_TODOS_CURRICULO = "SELECT c FROM Curriculo AS c";
    private static final String JPQL_CURRICULO_RELACAO = "SELECT c FROM Curriculo AS c " +
    		"LEFT JOIN FETCH c.formacoesAcademicas " +
            "LEFT JOIN FETCH c.experienciasProfissionais " +
            "WHERE c.id = :id";

    public CurriculoDaoImpl(EntityManager manager) {
        this.manager = manager;
        this.classePersistente = Curriculo.class;
    }

    @Override
	public Curriculo buscarPorIdComRelacionamento(Serializable id) {
        Query query = this.manager.createQuery(JPQL_CURRICULO_RELACAO);
        query.setParameter("id", id);
        Curriculo curriculo = (Curriculo) query.getSingleResult();
        return curriculo;
    }

    @Override
	public List<Curriculo> listarTodos() {
        Query query = this.manager.createQuery(JPQL_TODOS_CURRICULO);
        @SuppressWarnings("unchecked")
		List<Curriculo> curriculos = query.getResultList();
        return curriculos;
    }

}
