package com.valhala.curriculum.dao.impl;

import com.valhala.curriculum.dao.UsuarioDao;
import com.valhala.curriculum.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.io.Serializable;
import java.util.List;

public class UsuarioDaoImpl extends BaseDao<Usuario> implements UsuarioDao {

    private static final String JPQL_ALL_USUARIO = "SELECT u FROM Usuario AS u";
    private static final String JPQL_BYEMAIL_USUARIO = "SELECT u FROM Usuario AS u WHERE u.email = :email";

    public UsuarioDaoImpl(EntityManager manager) {
        this.manager = manager;
        this.classePersistente = Usuario.class;
    }

    public Usuario buscarPorIdComRelacionamento(Serializable id) {
        return null;
    }

    public List<Usuario> listarTodos() {
        Query query = this.manager.createQuery(JPQL_ALL_USUARIO);
        @SuppressWarnings("unchecked")
		List<Usuario> usuarios = query.getResultList();
        return usuarios;
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        Query query = this.manager.createQuery(JPQL_BYEMAIL_USUARIO);
        query.setParameter("email", email);
        Usuario usuario = (Usuario) query.getSingleResult();
        return usuario;
    }

}
