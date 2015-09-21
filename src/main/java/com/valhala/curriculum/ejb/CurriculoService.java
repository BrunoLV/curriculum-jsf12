package com.valhala.curriculum.ejb;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import javax.ejb.Local;

import com.valhala.curriculum.model.Curriculo;

/**
 * Created by bruno on 05/09/15.
 */
@Local
public interface CurriculoService extends BaseService<Curriculo> {

    Curriculo buscarPorIdComRelacionamento(Serializable id);

    void editar(Curriculo curriculo, @SuppressWarnings("rawtypes") Map<String, Set> mapaDeListaRemocaoRelacionamento);
}
