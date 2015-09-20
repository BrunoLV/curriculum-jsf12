/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.dao.impl;

import com.valhala.curriculum.dao.EmpresaDao;
import com.valhala.curriculum.model.Empresa;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.io.Serializable;
import java.util.List;

/**
 * @author bruno
 */
public class EmpresaDaoImpl extends BaseDao<Empresa> implements EmpresaDao {

    private static final String JPQL_ALL_EMPRESA = "SELECT e FROM Empresa AS e";

    public EmpresaDaoImpl(EntityManager manager) {
        this.manager = manager;
        this.classePersistente = Empresa.class;
    }

    public Empresa buscarPorIdComRelacionamento(Serializable id) {
        return null;
    }

    public List<Empresa> listarTodos() {
        Query query = this.manager.createQuery(JPQL_ALL_EMPRESA);
        @SuppressWarnings("unchecked")
		List<Empresa> empresas = query.getResultList();
        return empresas;
    }

}
