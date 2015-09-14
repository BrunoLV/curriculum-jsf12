/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.dao;

import com.valhala.curriculum.model.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T>
 * @author bruno
 */
public interface Dao<T extends BaseEntity> {

    T salvar(T t);

    void editar(T t);

    void deletar(T t);

    T buscarPorId(Serializable id);

    T buscarPorIdComRelacionamento(Serializable id);

    List<T> listarTodos();

    boolean objetoContidoNaSessao(T t);

}
