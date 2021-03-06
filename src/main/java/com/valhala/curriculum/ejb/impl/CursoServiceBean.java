package com.valhala.curriculum.ejb.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.valhala.curriculum.dao.CursoDao;
import com.valhala.curriculum.dao.impl.CursoDaoImpl;
import com.valhala.curriculum.ejb.CursoService;
import com.valhala.curriculum.model.Curso;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CursoServiceBean extends BaseServiceBean implements CursoService {

    private CursoDao cursoDao;

    @Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Curso> buscarTodos() {
        List<Curso> cursos = this.cursoDao.listarTodos();
        return cursos;
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletar(Curso curso) {
        this.cursoDao.deletar(curso);
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Curso curso) {
        this.cursoDao.editar(curso);
    }

    @PostConstruct
    private void init() {
        this.cursoDao = new CursoDaoImpl(this.manager);
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Curso pesquisarPorId(Serializable id) {
        Curso curso = this.cursoDao.buscarPorId(id);
        return curso;
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Curso salvar(Curso curso) {
        this.cursoDao.salvar(curso);
        return curso;
    }

}
