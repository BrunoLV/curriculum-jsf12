package com.valhala.curriculum.ejb.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.valhala.curriculum.dao.UsuarioDao;
import com.valhala.curriculum.dao.impl.UsuarioDaoImpl;
import com.valhala.curriculum.ejb.UsuarioService;
import com.valhala.curriculum.model.Usuario;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioServiceBean extends BaseServiceBean implements UsuarioService {

    private UsuarioDao usuarioDao;

    @Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Usuario> buscarTodos() {
        List<Usuario> usuarios = this.usuarioDao.listarTodos();
        return usuarios;
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Usuario buscarUsuarioPorEmail(String email) {
        Usuario usuario = this.usuarioDao.buscarUsuarioPorEmail(email);
        return usuario;
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletar(Usuario usuario) {
        this.usuarioDao.deletar(usuario);
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Usuario usuario) {
        this.usuarioDao.editar(usuario);
    }

    @PostConstruct
    private void init() {
        usuarioDao = new UsuarioDaoImpl(this.manager);
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Usuario pesquisarPorId(Serializable id) {
        Usuario usuario = this.usuarioDao.buscarPorId(id);
        return usuario;
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Usuario salvar(Usuario usuario) {
        this.usuarioDao.salvar(usuario);
        return usuario;
    }

}
