package com.valhala.curriculum.dao;

import com.valhala.curriculum.model.Usuario;

public interface UsuarioDao extends Dao<Usuario> {

    Usuario buscarUsuarioPorEmail(String email);

}
