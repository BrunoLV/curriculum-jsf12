/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.dao.impl;

import com.valhala.curriculum.dao.Dao;
import com.valhala.curriculum.model.BaseEntity;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @param <T>
 * @author bruno
 */
public abstract class BaseDao<T extends BaseEntity> implements Dao<T> {

    EntityManager manager;

    Class<T> classePersistente;

    public T salvar(T t) {
        manager.joinTransaction();
        manager.persist(t);
        return t;
    }

    public void editar(T t) {
        manager.joinTransaction();
        manager.merge(t);
    }

    public void deletar(T t) {
        manager.joinTransaction();
        manager.remove(buscarPorId(t.getId()));
    }

    public T buscarPorId(Serializable id) {
        T t = manager.find(classePersistente, id);
        return t;
    }

    public boolean objetoContidoNaSessao(T t) {
        return manager.contains(t);
    }

}
