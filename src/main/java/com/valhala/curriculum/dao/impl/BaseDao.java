package com.valhala.curriculum.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.valhala.curriculum.dao.Dao;
import com.valhala.curriculum.model.BaseEntity;

public abstract class BaseDao<T extends BaseEntity> implements Dao<T> {

    EntityManager manager;

    Class<T> classePersistente;

    @Override
	public T buscarPorId(Serializable id) {
        T t = manager.find(classePersistente, id);
        return t;
    }

    @Override
	public void deletar(T t) {
        manager.joinTransaction();
        manager.remove(buscarPorId(t.getId()));
    }

    @Override
	public void editar(T t) {
        manager.joinTransaction();
        manager.merge(t);
    }

    @Override
	public boolean objetoContidoNaSessao(T t) {
        return manager.contains(t);
    }

    @Override
	public T salvar(T t) {
        manager.joinTransaction();
        manager.persist(t);
        return t;
    }

}
