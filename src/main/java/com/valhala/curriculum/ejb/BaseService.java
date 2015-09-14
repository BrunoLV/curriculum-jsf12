/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.ejb;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T>
 * @author bruno
 */
public interface BaseService<T> {

    T salvar(T t);

    void editar(T t);

    void deletar(T t);

    T pesquisarPorId(Serializable id);

    List<T> buscarTodos();

}
