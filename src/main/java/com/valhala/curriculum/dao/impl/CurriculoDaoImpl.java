package com.valhala.curriculum.dao.impl;

import com.valhala.curriculum.dao.CurriculoDao;
import com.valhala.curriculum.model.Curriculo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by bruno on 05/09/15.
 */
public class CurriculoDaoImpl extends BaseDao<Curriculo> implements CurriculoDao {

    private static final String SQL_ALL_CURRICULO = "SELECT c FROM Curriculo AS c";
    private static final String SQL_CURRICULO_RELACAO = "SELECT c FROM Curriculo AS c LEFT OUTER JOIN FETCH c.listaExperienciaProfissional WHERE c.id = :id";

    public CurriculoDaoImpl(EntityManager manager) {
        this.manager = manager;
        this.classePersistente = Curriculo.class;
    }

    public Curriculo buscarPorIdComRelacionamento(Serializable id) {
        Query query = this.manager.createQuery(SQL_CURRICULO_RELACAO);
        query.setParameter("id", id);
        Curriculo curriculo = (Curriculo) query.getSingleResult();
        return curriculo;
    }

    public List<Curriculo> listarTodos() {
        Query query = this.manager.createQuery(SQL_ALL_CURRICULO);
        List<Curriculo> curriculos = query.getResultList();
        for (Curriculo curriculo : curriculos) {
            curriculo.getListaExperienciaProfissional();
        }
        return curriculos;
    }

}
