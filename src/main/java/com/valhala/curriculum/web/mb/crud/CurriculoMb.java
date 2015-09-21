package com.valhala.curriculum.web.mb.crud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import com.valhala.curriculum.dto.CargoDto;
import com.valhala.curriculum.dto.CurriculoDto;
import com.valhala.curriculum.dto.CursoDto;
import com.valhala.curriculum.dto.EmpresaDto;
import com.valhala.curriculum.dto.EntidadeEnsinoDto;
import com.valhala.curriculum.dto.EnumTipoFormacaoDto;
import com.valhala.curriculum.dto.ExperienciaProfissionalDto;
import com.valhala.curriculum.dto.FormacaoAcademicaDto;
import com.valhala.curriculum.dto.UsuarioDto;
import com.valhala.curriculum.ejb.CargoService;
import com.valhala.curriculum.ejb.CurriculoService;
import com.valhala.curriculum.ejb.CursoService;
import com.valhala.curriculum.ejb.EmpresaService;
import com.valhala.curriculum.ejb.EntidadeEnsinoService;
import com.valhala.curriculum.ejb.UsuarioService;
import com.valhala.curriculum.mappers.CargoMapper;
import com.valhala.curriculum.mappers.CurriculoComRelacionamentoMapper;
import com.valhala.curriculum.mappers.CurriculoSemRelacionamentoMapper;
import com.valhala.curriculum.mappers.CursoMapper;
import com.valhala.curriculum.mappers.EmpresaMapper;
import com.valhala.curriculum.mappers.EntidadeEnsinoMapper;
import com.valhala.curriculum.mappers.ExperienciaProfissionalMapper;
import com.valhala.curriculum.mappers.FormacaoAcademicaMapper;
import com.valhala.curriculum.mappers.UsuarioMapper;
import com.valhala.curriculum.web.mb.BaseMb;
import com.valhala.curriculum.web.mb.controle.ControleSessaoMb;

/**
 * Created by bruno on 05/09/15.
 */
public class CurriculoMb extends BaseMb {

    private static final long serialVersionUID = -7851091313961665265L;

	private static final String ID_EDICAO_SESSAO = "idParaEdicao";

    @EJB
    private CurriculoService curriculoService;
    @EJB
    private CargoService cargoService;
    @EJB
    private EmpresaService empresaService;
    @EJB
    private UsuarioService usuarioService;
    @EJB
    private CursoService cursoService;
    @EJB
    private EntidadeEnsinoService entidadeEnsinoService;

    private CurriculoDto curriculo;
    private List<CurriculoDto> listaCurriculo;

    private List<SelectItem> listaEmpresa = new ArrayList<SelectItem>();
    private List<SelectItem> listaCargo = new ArrayList<SelectItem>();
    private List<SelectItem> listaCurso = new ArrayList<SelectItem>();
    private List<SelectItem> listaEntidadeEnsino = new ArrayList<SelectItem>();

    private ControleSessaoMb controleSessaoMb;

    private Set<ExperienciaProfissionalDto> listaExperienciaProfissionalRemover = new LinkedHashSet<ExperienciaProfissionalDto>();
    private Set<FormacaoAcademicaDto> listaFormacaoAcademicaRemover = new LinkedHashSet<FormacaoAcademicaDto>();

    private CurriculoComRelacionamentoMapper curriculoComRelacionamentoMapper = CurriculoComRelacionamentoMapper.INSTANCE;
    private CurriculoSemRelacionamentoMapper curriculoSemRelacionamentoMapper = CurriculoSemRelacionamentoMapper.INSTANCE;
    private EmpresaMapper empresaMapper = EmpresaMapper.INSTANCE;
    private UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;
    private CargoMapper cargoMapper = CargoMapper.INSTANCE;
    private ExperienciaProfissionalMapper experienciaProfissionalMapper = ExperienciaProfissionalMapper.INSTANCE;
    private CursoMapper cursoMapper = CursoMapper.INSTANCE;
    private EntidadeEnsinoMapper entidadeEnsinoMapper = EntidadeEnsinoMapper.INSTANCE;
    private FormacaoAcademicaMapper formacaoAcademicaMapper = FormacaoAcademicaMapper.INSTANCE;

    private ExperienciaProfissionalDto experienciaProfissional;
    private FormacaoAcademicaDto formacaoAcademica;

    public CurriculoDto getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(CurriculoDto curriculo) {
        this.curriculo = curriculo;
    }

    @PostConstruct
    private void init() {
        Integer id = (Integer) getAtributoSessao(ID_EDICAO_SESSAO);
        if (id != null && id > 0) {
            this.curriculo = curriculoComRelacionamentoMapper.curriculoToCurriculoDto(this.curriculoService.buscarPorIdComRelacionamento(id));
            removeAtributoSessao(ID_EDICAO_SESSAO);
        } else {
            this.curriculo = new CurriculoDto();
        }
        initCargos();
        initEmpresas();
        initCursos();
        initEntidadesEnsino();
        experienciaProfissional = new ExperienciaProfissionalDto();
        formacaoAcademica = new FormacaoAcademicaDto();
        this.listaCurriculo = curriculoSemRelacionamentoMapper.listaCurriculoToListaCurriculoDto(this.curriculoService.buscarTodos());
    }

    private void initCargos() {
        List<CargoDto> dtos = cargoMapper.listaCargoToListaCargoDto(this.cargoService.buscarTodos());
        for (CargoDto dto : dtos) {
            this.listaCargo.add(new SelectItem(dto.getId(), dto.getNome()));
        }
    }

    private void initCursos() {
        List<CursoDto> dtos = cursoMapper.listaCursoToListaCursoDto(this.cursoService.buscarTodos());
        for (CursoDto dto : dtos) {
            this.listaCurso.add(new SelectItem(dto.getId(), dto.getNome()));
        }
    }

    private void initEntidadesEnsino() {
        List<EntidadeEnsinoDto> dtos = entidadeEnsinoMapper.listaEntidadeEnsinoToListaEntidadeEnsinoDto(this.entidadeEnsinoService.buscarTodos());
        for (EntidadeEnsinoDto dto : dtos) {
            this.listaEntidadeEnsino.add(new SelectItem(dto.getId(), dto.getNome()));
        }
    }

    private void initEmpresas() {
        List<EmpresaDto> dtos = empresaMapper.listaEmpresaToListaEmpresaDto(this.empresaService.buscarTodos());
        for (EmpresaDto dto : dtos) {
            this.listaEmpresa.add(new SelectItem(dto.getId(), dto.getNome()));
        }
    }

    public void salvar() {
        UsuarioDto usuario = usuarioMapper.usuarioToUsuarioDto(this.usuarioService.buscarUsuarioPorEmail(controleSessaoMb.getPrincipalName()));
        this.curriculo.setUsuario(usuario);
        if (this.curriculo.getId() == 0) {
            this.curriculo.setId(null);
            this.curriculoService.salvar(curriculoComRelacionamentoMapper.curriculoDtoToCurriculo(curriculo));
            inserirMensagemInformativa("Curriculo inserido com sucesso!!!");
            this.curriculo = new CurriculoDto();
        } else {
            @SuppressWarnings("rawtypes")
			Map<String, Set> mapaDeListaRemocaoRelacionamento = new HashMap<String, Set>();
            mapaDeListaRemocaoRelacionamento.put("ExperienciaProfissional", experienciaProfissionalMapper.setExperienciaProfissionalDtoToSetExperienciaProfissional(this.listaExperienciaProfissionalRemover));
            mapaDeListaRemocaoRelacionamento.put("FormacaoAcademica", formacaoAcademicaMapper.setFormacaoAcademicaDtoToSetFormacaoAcademica(this.listaFormacaoAcademicaRemover));
            this.curriculoService.editar(curriculoComRelacionamentoMapper.curriculoDtoToCurriculo(this.curriculo), mapaDeListaRemocaoRelacionamento);
            inserirMensagemInformativa("Curriculo editado com sucesso!!!");
        }
        this.experienciaProfissional = new ExperienciaProfissionalDto();
        this.formacaoAcademica = new FormacaoAcademicaDto();
    }

    public void incluirExperiencia() {
        this.experienciaProfissional.setCargo(cargoMapper.cargoToCargoDto(this.cargoService.pesquisarPorId(this.experienciaProfissional.getCargo().getId())));
        this.experienciaProfissional.setEmpresa(empresaMapper.empresaToEmpresaDto(this.empresaService.pesquisarPorId(this.experienciaProfissional.getEmpresa().getId())));
        this.curriculo.getExperienciasProfissionais().add(this.experienciaProfissional);
        this.experienciaProfissional = new ExperienciaProfissionalDto();
    }

    public void incluirFormacao() {
        this.formacaoAcademica.setCurso(cursoMapper.cursoToCursoDto(this.cursoService.pesquisarPorId(this.formacaoAcademica.getCurso().getId())));
        this.formacaoAcademica.setEntidadeEnsino(entidadeEnsinoMapper.entidadeEnsinoToEntidadeEnsinoDto(this.entidadeEnsinoService.pesquisarPorId(this.formacaoAcademica.getEntidadeEnsino().getId())));
        this.curriculo.getFormacoesAcademicas().add(this.formacaoAcademica);
        this.formacaoAcademica = new FormacaoAcademicaDto();
    }

    public void removerExperiencia() {
        this.curriculo.getExperienciasProfissionais().remove(this.experienciaProfissional);
        if (this.experienciaProfissional.getId() != null && this.experienciaProfissional.getId() > 0) {
            listaExperienciaProfissionalRemover.add(experienciaProfissional);
        }
        this.experienciaProfissional = new ExperienciaProfissionalDto();
    }

    public void removerFormacao() {
        this.curriculo.getFormacoesAcademicas().remove(this.formacaoAcademica);
        if (this.formacaoAcademica.getId() != null && this.formacaoAcademica.getId() > 0) {
            listaFormacaoAcademicaRemover.add(formacaoAcademica);
        }
        this.formacaoAcademica = new FormacaoAcademicaDto();
    }

    public void deletar() {
        this.curriculoService.deletar(curriculoComRelacionamentoMapper.curriculoDtoToCurriculo(this.curriculo));
        this.listaCurriculo = curriculoSemRelacionamentoMapper.listaCurriculoToListaCurriculoDto(this.curriculoService.buscarTodos());
        inserirMensagemInformativa("Curriculo removido com sucesso!!!");
    }

    public List<CurriculoDto> getListaCurriculo() {
        return listaCurriculo;
    }

    public void setListaCurriculo(List<CurriculoDto> listaCurriculo) {
        this.listaCurriculo = listaCurriculo;
    }

    public ExperienciaProfissionalDto getExperienciaProfissional() {
        return experienciaProfissional;
    }

    public void setExperienciaProfissional(ExperienciaProfissionalDto experienciaProfissional) {
        this.experienciaProfissional = experienciaProfissional;
    }

    public FormacaoAcademicaDto getFormacaoAcademica() {
        return formacaoAcademica;
    }

    public void setFormacaoAcademica(FormacaoAcademicaDto formacaoAcademica) {
        this.formacaoAcademica = formacaoAcademica;
    }

    public List<SelectItem> getListaEmpresa() {
        return listaEmpresa;
    }

    public List<SelectItem> getListaCargo() {
        return listaCargo;
    }

    public List<SelectItem> getListaCurso() {
        return listaCurso;
    }

    public List<SelectItem> getListaEntidadeEnsino() {
        return listaEntidadeEnsino;
    }

    public ControleSessaoMb getControleSessaoMb() {
        return controleSessaoMb;
    }

    public void setControleSessaoMb(ControleSessaoMb controleSessaoMb) {
        this.controleSessaoMb = controleSessaoMb;
    }
    
    public List<FormacaoAcademicaDto> getFormacoes() {
    	return new ArrayList<FormacaoAcademicaDto>(this.curriculo.getFormacoesAcademicas());
    }
    
    public List<ExperienciaProfissionalDto> getExperiencias() {
    	return new ArrayList<ExperienciaProfissionalDto>(this.curriculo.getExperienciasProfissionais());
    }

    public List<SelectItem> getTiposFormacao() {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        EnumTipoFormacaoDto[] enumTipoFormacaoDtos = EnumTipoFormacaoDto.values();
        for (int i = 0; i < enumTipoFormacaoDtos.length; i++) {
            selectItems.add(new SelectItem(enumTipoFormacaoDtos[i]));
        }
        return selectItems;
    }
}
