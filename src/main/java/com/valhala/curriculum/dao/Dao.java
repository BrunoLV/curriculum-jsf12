/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.dao;

import java.io.Serializable;
import java.util.List;

import com.valhala.curriculum.model.BaseEntity;

public interface Dao<T extends BaseEntity> {

    T buscarPorId(Serializable id);

    T buscarPorIdComRelacionamento(Serializable id);

    void deletar(T t);

    void editar(T t);

    List<T> listarTodos();

    boolean objetoContidoNaSessao(T t);

    T salvar(T t);

}
