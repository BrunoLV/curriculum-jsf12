package com.valhala.curriculum.ejb;

import javax.ejb.Local;

import com.valhala.curriculum.model.Usuario;

@Local
public interface UsuarioService extends BaseService<Usuario> {

    Usuario buscarUsuarioPorEmail(String email);

}
