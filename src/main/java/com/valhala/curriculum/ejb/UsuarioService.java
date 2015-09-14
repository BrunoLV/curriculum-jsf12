package com.valhala.curriculum.ejb;

import com.valhala.curriculum.model.Usuario;

import javax.ejb.Local;

@Local
public interface UsuarioService extends BaseService<Usuario> {

    Usuario buscarUsuarioPorEmail(String email);

}
