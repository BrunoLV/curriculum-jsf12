package com.valhala.curriculum.ejb;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {

    List<T> buscarTodos();

    void deletar(T t);

    void editar(T t);

    T pesquisarPorId(Serializable id);

    T salvar(T t);

}
