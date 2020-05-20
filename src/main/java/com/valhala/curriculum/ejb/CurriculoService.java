package com.valhala.curriculum.ejb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Local;

import com.valhala.curriculum.model.Curriculo;

@Local
public interface CurriculoService extends BaseService<Curriculo> {

    Curriculo buscarPorIdComRelacionamento(Serializable id);
    
    List<Curriculo> buscaPorUsuarioId(Serializable idUsuario);

    void editar(Curriculo curriculo, @SuppressWarnings("rawtypes") Map<String, Set> mapaDeListaRemocaoRelacionamento);
}
