package com.valhala.curriculum.ejb.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.valhala.curriculum.dao.CurriculoDao;
import com.valhala.curriculum.dao.ExperienciaProfissionalDao;
import com.valhala.curriculum.dao.FormacaoAcademicaDao;
import com.valhala.curriculum.dao.impl.CurriculoDaoImpl;
import com.valhala.curriculum.dao.impl.ExperienciaProfissionalDaoImpl;
import com.valhala.curriculum.dao.impl.FormacaoAcademicaDaoImpl;
import com.valhala.curriculum.ejb.CargoService;
import com.valhala.curriculum.ejb.CurriculoService;
import com.valhala.curriculum.ejb.CursoService;
import com.valhala.curriculum.ejb.EmpresaService;
import com.valhala.curriculum.ejb.EntidadeEnsinoService;
import com.valhala.curriculum.ejb.UsuarioService;
import com.valhala.curriculum.model.Curriculo;
import com.valhala.curriculum.model.ExperienciaProfissional;
import com.valhala.curriculum.model.FormacaoAcademica;

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

    @Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Curriculo buscarPorIdComRelacionamento(Serializable id) {
        Curriculo curriculo = this.curriculoDao.buscarPorIdComRelacionamento(id);
        return curriculo;
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Curriculo> buscarTodos() {
        List<Curriculo> curriculos = this.curriculoDao.listarTodos();
        return curriculos;
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletar(Curriculo curriculo) {
        this.curriculoDao.deletar(curriculo);
    }

    @Override
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

    @Override
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

    @PostConstruct
    private void init() {
        this.curriculoDao = new CurriculoDaoImpl(this.manager);
        this.experienciaProfissionalDao = new ExperienciaProfissionalDaoImpl(this.manager);
        this.formacaoAcademicaDao = new FormacaoAcademicaDaoImpl(this.manager);
    }

    @Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Curriculo pesquisarPorId(Serializable id) {
        Curriculo curriculo = this.curriculoDao.buscarPorId(id);
        return curriculo;
    }

    @Override
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

	@Override
	public List<Curriculo> buscaPorUsuarioId(Serializable idUsuario) {
		List<Curriculo> curriculos = this.curriculoDao.buscaPorIdUsuario(idUsuario);
        return curriculos;
	}

}
