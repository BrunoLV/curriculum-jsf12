package com.valhala.curriculum.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.valhala.curriculum.dao.EmpresaDao;
import com.valhala.curriculum.model.Empresa;

public class EmpresaDaoImpl extends BaseDao<Empresa> implements EmpresaDao {

    private static final String JPQL_TODAS_EMPRESAS = "SELECT e FROM Empresa AS e";

    public EmpresaDaoImpl(EntityManager manager) {
        this.manager = manager;
        this.classePersistente = Empresa.class;
    }

    @Override
	public Empresa buscarPorIdComRelacionamento(Serializable id) {
        return null;
    }

    @Override
	public List<Empresa> listarTodos() {
        Query query = this.manager.createQuery(JPQL_TODAS_EMPRESAS);
        @SuppressWarnings("unchecked")
		List<Empresa> empresas = query.getResultList();
        return empresas;
    }

}
