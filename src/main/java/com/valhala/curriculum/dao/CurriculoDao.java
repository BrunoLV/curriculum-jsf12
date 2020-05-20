package com.valhala.curriculum.dao;

import java.io.Serializable;
import java.util.List;

import com.valhala.curriculum.model.Curriculo;

public interface CurriculoDao extends Dao<Curriculo> {

	List<Curriculo> buscaPorIdUsuario(Serializable idUsuario);

}
