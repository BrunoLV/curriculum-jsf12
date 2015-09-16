package com.valhala.curriculum.ejb.impl;

import com.valhala.curriculum.dao.CurriculoDao;
import com.valhala.curriculum.dao.ExperienciaProfissionalDao;
import com.valhala.curriculum.dao.impl.CurriculoDaoImpl;
import com.valhala.curriculum.dao.impl.ExperienciaProfissionalDaoImpl;
import com.valhala.curriculum.ejb.CargoService;
import com.valhala.curriculum.ejb.CurriculoService;
import com.valhala.curriculum.ejb.EmpresaService;
import com.valhala.curriculum.ejb.UsuarioService;
import com.valhala.curriculum.model.Curriculo;
import com.valhala.curriculum.model.ExperienciaProfissional;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by bruno on 05/09/15.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CurriculoServiceBean extends BaseServiceBean implements CurriculoService {

    private CurriculoDao curriculoDao;
    private ExperienciaProfissionalDao experienciaProfissionalDao;

    @EJB
    private UsuarioService usuarioService;
    @EJB
    private CargoService cargoService;
    @EJB
    private EmpresaService empresaService;

    @PostConstruct
    private void init() {
        this.curriculoDao = new CurriculoDaoImpl(this.manager);
        this.experienciaProfissionalDao = new ExperienciaProfissionalDaoImpl(this.manager);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Curriculo salvar(Curriculo curriculo) {
        this.curriculoDao.salvar(curriculo);
        return curriculo;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Curriculo curriculo) {
        this.curriculoDao.editar(curriculo);
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
    public void salvar(Curriculo curriculo, List<ExperienciaProfissional> experienciasProfissionaisInclusao) {
        for (ExperienciaProfissional ex : experienciasProfissionaisInclusao) {
            curriculo.adicionarExperienciaProfissional(ex);
        }
        salvar(curriculo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Curriculo curriculo, List<ExperienciaProfissional> experienciasProfissionaisInclusao, List<ExperienciaProfissional> experienciasProfissionaisRemocao) {
        editar(curriculo);
        adicionarExperienciasAoCurriculo(curriculo, experienciasProfissionaisInclusao);
        removerExperienciasDoCurriculo(curriculo, experienciasProfissionaisRemocao);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void adicionarExperienciasAoCurriculo(Curriculo curriculo, List<ExperienciaProfissional> experienciasProfissionais) {
        curriculo = this.curriculoDao.buscarPorId(curriculo.getId());
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

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removerExperienciasDoCurriculo(Curriculo curriculo, List<ExperienciaProfissional> experienciasProfissionais) {
        curriculo = this.curriculoDao.buscarPorIdComRelacionamento(curriculo.getId());
        System.out.println("Tamanho: " + experienciasProfissionais.size());
        for (ExperienciaProfissional ex : experienciasProfissionais) {
            ex = experienciaProfissionalDao.buscarPorId(ex.getId());
            curriculo.removerExperienciaProfissional(ex);
            curriculoDao.editar(curriculo);
            experienciaProfissionalDao.deletar(ex);
        }
    }
}
