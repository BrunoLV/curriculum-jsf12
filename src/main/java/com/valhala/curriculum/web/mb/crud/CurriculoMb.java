package com.valhala.curriculum.web.mb.crud;

import com.valhala.curriculum.dto.*;
import com.valhala.curriculum.ejb.*;
import com.valhala.curriculum.mappers.*;
import com.valhala.curriculum.web.mb.BaseMb;
import com.valhala.curriculum.web.mb.controle.ControleSessaoMb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private List<ExperienciaProfissionalDto> listaExperienciaProfissionalRemover = new ArrayList<ExperienciaProfissionalDto>();
    private List<FormacaoAcademicaDto> listaFormacaoAcademicaRemover = new ArrayList<FormacaoAcademicaDto>();

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
			Map<String, List> mapaDeListaRemocaoRelacionamento = new HashMap<String, List>();
            mapaDeListaRemocaoRelacionamento.put("ExperienciaProfissional", experienciaProfissionalMapper.listaExperienciaProfissionalDtoToListaExperienciaProfissional(this.listaExperienciaProfissionalRemover));
            mapaDeListaRemocaoRelacionamento.put("FormacaoAcademica", formacaoAcademicaMapper.listaFormacaoAcademicaDtoToListaFormacaoAcademica(this.listaFormacaoAcademicaRemover));
            this.curriculoService.editar(curriculoComRelacionamentoMapper.curriculoDtoToCurriculo(this.curriculo), mapaDeListaRemocaoRelacionamento);
            inserirMensagemInformativa("Curriculo editado com sucesso!!!");
        }
        this.experienciaProfissional = new ExperienciaProfissionalDto();
        this.formacaoAcademica = new FormacaoAcademicaDto();
    }

    public void incluirExperiencia() {
        this.experienciaProfissional.setCargo(cargoMapper.cargoToCargoDto(this.cargoService.pesquisarPorId(this.experienciaProfissional.getCargo().getId())));
        this.experienciaProfissional.setEmpresa(empresaMapper.empresaToEmpresaDto(this.empresaService.pesquisarPorId(this.experienciaProfissional.getEmpresa().getId())));
        this.curriculo.getListaExperienciaProfissional().add(this.experienciaProfissional);
        this.experienciaProfissional = new ExperienciaProfissionalDto();
    }

    public void incluirFormacao() {
        this.formacaoAcademica.setCurso(cursoMapper.cursoToCursoDto(this.cursoService.pesquisarPorId(this.formacaoAcademica.getCurso().getId())));
        this.formacaoAcademica.setEntidadeEnsino(entidadeEnsinoMapper.entidadeEnsinoToEntidadeEnsinoDto(this.entidadeEnsinoService.pesquisarPorId(this.formacaoAcademica.getEntidadeEnsino().getId())));
        this.curriculo.getListaFormacaoAcademica().add(this.formacaoAcademica);
        this.formacaoAcademica = new FormacaoAcademicaDto();
    }

    public void removerExperiencia() {
        this.curriculo.getListaExperienciaProfissional().remove(this.experienciaProfissional);
        if (this.experienciaProfissional.getId() != null && this.experienciaProfissional.getId() > 0) {
            listaExperienciaProfissionalRemover.add(experienciaProfissional);
        }
        this.experienciaProfissional = new ExperienciaProfissionalDto();
    }

    public void removerFormacao() {
        this.curriculo.getListaFormacaoAcademica().remove(this.formacaoAcademica);
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

    public List<SelectItem> getTiposFormacao() {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        EnumTipoFormacaoDto[] enumTipoFormacaoDtos = EnumTipoFormacaoDto.values();
        for (int i = 0; i < enumTipoFormacaoDtos.length; i++) {
            selectItems.add(new SelectItem(enumTipoFormacaoDtos[i]));
        }
        return selectItems;
    }
}
