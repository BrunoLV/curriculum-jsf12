package com.valhala.curriculum.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.valhala.curriculum.dao.DadosPessoaisDao;
import com.valhala.curriculum.model.DadosPessoais;

public class DadosPessoaisDaoImpl extends BaseDao<DadosPessoais> implements DadosPessoaisDao {

	private static String JPQL_DADOS_PESSOAIS_POR_ID_USUARIO = "SELECT dp FROM DadosPessoais AS dp WHERE dp.usuario.id = :idUsuario";

	public DadosPessoaisDaoImpl(EntityManager manager) {
		this.manager = manager;
		this.classePersistente = DadosPessoais.class;
	}

	@Override
	public DadosPessoais buscarPorIdComRelacionamento(Serializable id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DadosPessoais buscarPorIdUsario(Serializable idUsuario) {
		Query query = this.manager.createQuery(JPQL_DADOS_PESSOAIS_POR_ID_USUARIO);
		query.setParameter("idUsuario", idUsuario);
		return (DadosPessoais) query.getSingleResult();
	}

	@Override
	public List<DadosPessoais> listarTodos() {
		throw new UnsupportedOperationException();
	}

}
