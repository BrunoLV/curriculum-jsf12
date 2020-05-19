package com.valhala.curriculum.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.valhala.curriculum.dao.CargoDao;
import com.valhala.curriculum.model.Cargo;

public class CargoDaoImpl extends BaseDao<Cargo> implements CargoDao {

    private static final String JPQL_ALL_CARGO = "SELECT c FROM Cargo AS c";

    public CargoDaoImpl(EntityManager manager) {
        super();
        this.classePersistente = Cargo.class;
        this.manager = manager;
    }

    @Override
	public Cargo buscarPorIdComRelacionamento(Serializable id) {
        return null;
    }

    @Override
	public List<Cargo> listarTodos() {
        Query query = this.manager.createQuery(JPQL_ALL_CARGO);
        @SuppressWarnings("unchecked")
		List<Cargo> cargos = query.getResultList();
        return cargos;
    }

}
