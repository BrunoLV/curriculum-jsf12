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
        List<ExperienciaProfissional> experienciasProfissionais = curriculo.getListaExperienciaProfissional();
        for (ExperienciaProfissional exp : experienciasProfissionais) {
            exp.setCurriculo(curriculo);
        }
        List<FormacaoAcademica> formacaoAcademicas = curriculo.getListaFormacaoAcademica();
        for (FormacaoAcademica fa : formacaoAcademicas) {
            fa.setCurriculo(curriculo);
        }
        this.curriculoDao.salvar(curriculo);
        return curriculo;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Curriculo curriculo) {
        List<ExperienciaProfissional> experienciasProfissionais = curriculo.getListaExperienciaProfissional();
        for (ExperienciaProfissional exp : experienciasProfissionais) {
            exp.setCurriculo(curriculo);
        }
        List<FormacaoAcademica> formacaoAcademicas = curriculo.getListaFormacaoAcademica();
        for (FormacaoAcademica fa : formacaoAcademicas) {
            fa.setCurriculo(curriculo);
        }
        this.curriculoDao.editar(curriculo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Curriculo curriculo, @SuppressWarnings("rawtypes") Map<String, List> mapRemocaoRelacionamento) {
        Set<String> keySet = mapRemocaoRelacionamento.keySet();
        for (String key : keySet) {
            if (key.equals("ExperienciaProfissional")) {
                @SuppressWarnings("unchecked")
				List<ExperienciaProfissional> exps = mapRemocaoRelacionamento.get(key);
                for (ExperienciaProfissional exp : exps) {
                    exp = this.experienciaProfissionalDao.buscarPorId(exp.getId());
                    exp.setCurriculo(null);
                    this.experienciaProfissionalDao.deletar(exp);
                }
            }
            if (key.equals("FormacaoAcademica")) {
                @SuppressWarnings("unchecked")
				List<FormacaoAcademica> fas = mapRemocaoRelacionamento.get(key);
                for (FormacaoAcademica fa : fas) {
                    fa = this.formacaoAcademicaDao.buscarPorId(fa.getId());
                    fa.setCurriculo(null);
                    this.formacaoAcademicaDao.deletar(fa);
                }
            }
        }
        this.editar(curriculo);
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

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void salvar(Curriculo curriculo,
                       List<ExperienciaProfissional> experienciasProfissionaisInclusao,
                       List<FormacaoAcademica> formacoesAcademicasInclusao) {
        for (ExperienciaProfissional ex : experienciasProfissionaisInclusao) {
            curriculo.adicionarExperienciaProfissional(ex);
        }

        for (FormacaoAcademica fa : formacoesAcademicasInclusao) {
            curriculo.adicionarFormacaoAcademica(fa);
        }
        salvar(curriculo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Curriculo curriculo,
                       List<ExperienciaProfissional> experienciasProfissionaisInclusao,
                       List<FormacaoAcademica> formacoesAcademicasInclusao,
                       List<ExperienciaProfissional> experienciasProfissionaisRemocao,
                       List<FormacaoAcademica> formacoesAcademicasRemocao) {
        editar(curriculo);
        adicionarRelacoesAoCurriculo(curriculo, experienciasProfissionaisInclusao, formacoesAcademicasInclusao);
        removerRelacoesDoCurriculo(curriculo, experienciasProfissionaisRemocao, formacoesAcademicasRemocao);
    }

    private void adicionarRelacoesAoCurriculo(Curriculo curriculo,
                                              List<ExperienciaProfissional> experienciasProfissionais,
                                              List<FormacaoAcademica> formacoesAcademicas) {
        curriculo = this.curriculoDao.buscarPorId(curriculo.getId());
        tratarListaExperienciasProfissionais(curriculo, experienciasProfissionais);
        tratarListaFormacoesAcademicas(curriculo, formacoesAcademicas);
    }

    private void removerRelacoesDoCurriculo(Curriculo curriculo,
                                            List<ExperienciaProfissional> experienciasProfissionais,
                                            List<FormacaoAcademica> formacoesAcademicas) {
        curriculo = this.curriculoDao.buscarPorIdComRelacionamento(curriculo.getId());
        removerExperienciasProfissionais(curriculo, experienciasProfissionais);
        removerFormacoesAcademicas(curriculo, formacoesAcademicas);
    }

    private void removerExperienciasProfissionais(Curriculo curriculo, List<ExperienciaProfissional> experienciasProfissionais) {
        for (ExperienciaProfissional ex : experienciasProfissionais) {
            ex = experienciaProfissionalDao.buscarPorId(ex.getId());
            curriculo.removerExperienciaProfissional(ex);
            curriculoDao.editar(curriculo);
            experienciaProfissionalDao.deletar(ex);
        }
    }

    private void removerFormacoesAcademicas(Curriculo curriculo, List<FormacaoAcademica> formacoesAcademicas) {
        for (FormacaoAcademica fa : formacoesAcademicas) {
            fa = formacaoAcademicaDao.buscarPorId(fa.getId());
            curriculo.removerFormacaoAcademica(fa);
            curriculoDao.editar(curriculo);
            formacaoAcademicaDao.deletar(fa);
        }
    }

    private void tratarListaExperienciasProfissionais(Curriculo curriculo, List<ExperienciaProfissional> experienciasProfissionais) {
        for (ExperienciaProfissional ex : experienciasProfissionais) {
            if (ex.getId() != null && ex.getId() > 0) {
                ex = experienciaProfissionalDao.buscarPorId(ex.getId());
            }
            if (!curriculo.getListaExperienciaProfissional().contains(ex)) {
                curriculo.adicionarExperienciaProfissional(ex);
                curriculoDao.editar(curriculo);
            }
        }
    }

    private void tratarListaFormacoesAcademicas(Curriculo curriculo, List<FormacaoAcademica> formacoesAcademicas) {
        for (FormacaoAcademica fa : formacoesAcademicas) {
            if (fa.getId() != null && fa.getId() > 0) {
                fa = formacaoAcademicaDao.buscarPorId(fa.getId());
            }
            if (!curriculo.getListaFormacaoAcademica().contains(fa)) {
                curriculo.adicionarFormacaoAcademica(fa);
                curriculoDao.editar(curriculo);
            }
        }
    }

}
