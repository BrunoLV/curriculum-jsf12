package com.valhala.curriculum.dao;

import java.io.Serializable;

import com.valhala.curriculum.model.DadosPessoais;

public interface DadosPessoaisDao extends Dao<DadosPessoais> {

	DadosPessoais buscarPorIdUsario(Serializable idUsuario);

}
