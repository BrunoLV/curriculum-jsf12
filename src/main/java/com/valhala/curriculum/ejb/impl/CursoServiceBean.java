package com.valhala.curriculum.ejb.impl;

import com.valhala.curriculum.dao.CursoDao;
import com.valhala.curriculum.dao.impl.CursoDaoImpl;
import com.valhala.curriculum.ejb.CursoService;
import com.valhala.curriculum.model.Curso;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by bruno on 17/09/15.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CursoServiceBean extends BaseServiceBean implements CursoService {

    private CursoDao cursoDao;

    @PostConstruct
    private void init() {
        this.cursoDao = new CursoDaoImpl(this.manager);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Curso salvar(Curso curso) {
        this.cursoDao.salvar(curso);
        return curso;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Curso curso) {
        this.cursoDao.editar(curso);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletar(Curso curso) {
        this.cursoDao.deletar(curso);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Curso pesquisarPorId(Serializable id) {
        Curso curso = this.cursoDao.buscarPorId(id);
        return curso;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Curso> buscarTodos() {
        List<Curso> cursos = this.cursoDao.listarTodos();
        return cursos;
    }

}
