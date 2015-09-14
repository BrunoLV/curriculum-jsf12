/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.dao.impl;

import com.valhala.curriculum.dao.CargoDao;
import com.valhala.curriculum.model.Cargo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * @author bruno
 */
public class CargoDaoImpl extends BaseDao<Cargo> implements CargoDao {

    private static final String JPQL_ALL_CARGO = "SELECT c FROM Cargo AS c";

    public CargoDaoImpl(EntityManager manager) {
        super();
        this.classePersistente = Cargo.class;
        this.manager = manager;
    }

    public Cargo buscarPorIdComRelacionamento(Serializable id) {
        return null;
    }

    public List<Cargo> listarTodos() {
        Query query = this.manager.createQuery(JPQL_ALL_CARGO);
        List<Cargo> cargos = query.getResultList();
        return cargos;
    }

}
