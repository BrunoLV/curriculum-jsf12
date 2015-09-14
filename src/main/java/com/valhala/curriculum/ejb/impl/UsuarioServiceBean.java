package com.valhala.curriculum.ejb.impl;

import com.valhala.curriculum.dao.UsuarioDao;
import com.valhala.curriculum.dao.impl.UsuarioDaoImpl;
import com.valhala.curriculum.ejb.UsuarioService;
import com.valhala.curriculum.model.Usuario;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.io.Serializable;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioServiceBean extends BaseServiceBean implements UsuarioService {

    private UsuarioDao usuarioDao;

    @PostConstruct
    private void init() {
        usuarioDao = new UsuarioDaoImpl(this.manager);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Usuario salvar(Usuario usuario) {
        this.usuarioDao.salvar(usuario);
        return usuario;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Usuario usuario) {
        this.usuarioDao.editar(usuario);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletar(Usuario usuario) {
        this.usuarioDao.deletar(usuario);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Usuario pesquisarPorId(Serializable id) {
        Usuario usuario = this.usuarioDao.buscarPorId(id);
        return usuario;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Usuario> buscarTodos() {
        List<Usuario> usuarios = this.usuarioDao.listarTodos();
        return usuarios;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Usuario buscarUsuarioPorEmail(String email) {
        Usuario usuario = this.usuarioDao.buscarUsuarioPorEmail(email);
        return usuario;
    }

}
