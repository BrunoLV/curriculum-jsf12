package com.valhala.curriculum.ejb.impl;

import com.valhala.curriculum.dao.CurriculoDao;
import com.valhala.curriculum.dao.ExperienciaProfissionalDao;
import com.valhala.curriculum.dao.FormacaoAcademicaDao;
import com.valhala.curriculum.dao.impl.CurriculoDaoImpl;
import com.valhala.curriculum.dao.impl.ExperienciaProfissionalDaoImpl;
import com.valhala.curriculum.dao.impl.FormacaoAcademicaDaoImpl;
import com.valhala.curriculum.ejb.*;
import com.valhala.curriculum.model.Curriculo;
import com.valhala.curriculum.model.ExperienciaProfissional;
import com.valhala.curriculum.model.FormacaoAcademica;

import javax.annotation.PostConstruct;
import javax.ejb.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by bruno on 05/09/15.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CurriculoServiceBean extends BaseServiceBean implements CurriculoService {

    private CurriculoDao curriculoDao;
    private ExperienciaProfissionalDao experienciaProfissionalDao;
    private FormacaoAcademicaDao formacaoAcademicaDao;

    @EJB
    private UsuarioService usuarioService;
    @EJB
    private CargoService cargoService;
    @EJB
    private EmpresaService empresaService;
    @EJB
    private CursoService cursoService;
    @EJB
    private EntidadeEnsinoService entidadeEnsinoService;

    @PostConstruct
    private void init() {
        this.curriculoDao = new CurriculoDaoImpl(this.manager);
        this.experienciaProfissionalDao = new ExperienciaProfissionalDaoImpl(this.manager);
        this.formacaoAcademicaDao = new FormacaoAcademicaDaoImpl(this.manager);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Curriculo salvar(Curriculo curriculo) {
        Set<ExperienciaProfissional> experienciasProfissionais = curriculo.getExperienciasProfissionais();
        for (ExperienciaProfissional exp : experienciasProfissionais) {
            exp.setCurriculo(curriculo);
        }
        Set<FormacaoAcademica> formacaoAcademicas = curriculo.getFormacoesAcademicas();
        for (FormacaoAcademica fa : formacaoAcademicas) {
            fa.setCurriculo(curriculo);
        }
        this.curriculoDao.salvar(curriculo);
        return curriculo;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Curriculo curriculo) {
        Set<ExperienciaProfissional> experienciasProfissionais = curriculo.getExperienciasProfissionais();
        for (ExperienciaProfissional exp : experienciasProfissionais) {
            exp.setCurriculo(curriculo);
        }
        Set<FormacaoAcademica> formacaoAcademicas = curriculo.getFormacoesAcademicas();
        for (FormacaoAcademica fa : formacaoAcademicas) {
            fa.setCurriculo(curriculo);
        }
        this.curriculoDao.editar(curriculo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Curriculo curriculo, @SuppressWarnings("rawtypes") Map<String, Set> mapRemocaoRelacionamento) {
    	this.editar(curriculo);
    	Set<String> keySet = mapRemocaoRelacionamento.keySet();
        for (String key : keySet) {
            if (key.equals("ExperienciaProfissional")) {
                @SuppressWarnings("unchecked")
				Set<ExperienciaProfissional> exps = mapRemocaoRelacionamento.get(key);
                for (ExperienciaProfissional exp : exps) {
                	exp = this.experienciaProfissionalDao.buscarPorId(exp.getId());
                    exp.setCurriculo(null);
                    this.experienciaProfissionalDao.deletar(exp);
                }
            }
            if (key.equals("FormacaoAcademica")) {
                @SuppressWarnings("unchecked")
				Set<FormacaoAcademica> fas = mapRemocaoRelacionamento.get(key);
                for (FormacaoAcademica fa : fas) {
                    fa = this.formacaoAcademicaDao.buscarPorId(fa.getId());
                    fa.setCurriculo(null);
                    this.formacaoAcademicaDao.deletar(fa);
                }
            }
        }
        
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletar(Curriculo curriculo) {
        this.curriculoDao.deletar(curriculo);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Curriculo pesquisarPorId(Serializable id) {
        Curriculo curriculo = this.curriculoDao.buscarPorId(id);
        return curriculo;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Curriculo> buscarTodos() {
        List<Curriculo> curriculos = this.curriculoDao.listarTodos();
        return curriculos;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Curriculo buscarPorIdComRelacionamento(Serializable id) {
        Curriculo curriculo = this.curriculoDao.buscarPorIdComRelacionamento(id);
        return curriculo;
    }

}
