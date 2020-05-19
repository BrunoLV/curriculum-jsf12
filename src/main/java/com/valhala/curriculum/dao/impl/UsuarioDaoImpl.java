package com.valhala.curriculum.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.valhala.curriculum.dao.UsuarioDao;
import com.valhala.curriculum.model.Usuario;

public class UsuarioDaoImpl extends BaseDao<Usuario> implements UsuarioDao {

    private static final String JPQL_TODOS_USUARIOS = "SELECT u FROM Usuario AS u";
    private static final String JPQL_USUARIO_POR_EMAIL = "SELECT u FROM Usuario AS u WHERE u.email = :email";

    public UsuarioDaoImpl(EntityManager manager) {
        this.manager = manager;
        this.classePersistente = Usuario.class;
    }

    @Override
	public Usuario buscarPorIdComRelacionamento(Serializable id) {
        return null;
    }

    @Override
	public Usuario buscarUsuarioPorEmail(String email) {
        Query query = this.manager.createQuery(JPQL_USUARIO_POR_EMAIL);
        query.setParameter("email", email);
        Usuario usuario = (Usuario) query.getSingleResult();
        return usuario;
    }

    @Override
	public List<Usuario> listarTodos() {
        Query query = this.manager.createQuery(JPQL_TODOS_USUARIOS);
        @SuppressWarnings("unchecked")
		List<Usuario> usuarios = query.getResultList();
        return usuarios;
    }

}
