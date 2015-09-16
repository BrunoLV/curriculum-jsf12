package com.valhala.curriculum.web.mb.crud;

import com.valhala.curriculum.dto.*;
import com.valhala.curriculum.ejb.CargoService;
import com.valhala.curriculum.ejb.CurriculoService;
import com.valhala.curriculum.ejb.EmpresaService;
import com.valhala.curriculum.ejb.UsuarioService;
import com.valhala.curriculum.mappers.*;
import com.valhala.curriculum.web.mb.BaseMb;
import com.valhala.curriculum.web.mb.controle.ControleSessaoMb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 05/09/15.
 */
public class CurriculoMb extends BaseMb {

    private static final String ID_EDICAO_SESSAO = "idParaEdicao";

    @EJB
    private CurriculoService curriculoService;
    @EJB
    private CargoService cargoService;
    @EJB
    private EmpresaService empresaService;
    @EJB
    private UsuarioService usuarioService;

    private CurriculoDto curriculo;
    private List<CurriculoDto> listaCurriculo;

    private List<SelectItem> listaEmpresa = new ArrayList<SelectItem>();
    private List<SelectItem> listaCargo = new ArrayList<SelectItem>();

    private ControleSessaoMb controleSessaoMb;

    private List<ExperienciaProfissionalDto> listaExperienciaProfissionalRemover = new ArrayList<ExperienciaProfissionalDto>();

    private CurriculoComRelacionamentoMapper curriculoComRelacionamentoMapper = CurriculoComRelacionamentoMapper.INSTANCE;
    private CurriculoSemRelacionamentoMapper curriculoSemRelacionamentoMapper = CurriculoSemRelacionamentoMapper.INSTANCE;
    private EmpresaMapper empresaMapper = EmpresaMapper.INSTANCE;
    private UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;
    private CargoMapper cargoMapper = CargoMapper.INSTANCE;
    private ExperienciaProfissionalMapper experienciaProfissionalMapper = ExperienciaProfissionalMapper.INSTANCE;

    private ExperienciaProfissionalDto experienciaProfissional;

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
        experienciaProfissional = new ExperienciaProfissionalDto();
        this.listaCurriculo = curriculoSemRelacionamentoMapper.listaCurriculoToListaCurriculoDto(this.curriculoService.buscarTodos());
    }

    private void initCargos() {
        List<CargoDto> dtos = cargoMapper.listaCargoToListaCargoDto(this.cargoService.buscarTodos());
        for (CargoDto dto : dtos) {
            this.listaCargo.add(new SelectItem(dto.getId(), dto.getNome()));
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
            this.curriculoService.salvar(curriculoSemRelacionamentoMapper.curriculoDtoToCurriculo(curriculo),
                    experienciaProfissionalMapper.listaExperienciaProfissionalToListaExperienciaProfissional(this.curriculo.getListaExperienciaProfissional()));
            inserirMensagemInformativa("Curriculo inserido com sucesso!!!");
            this.curriculo = new CurriculoDto();
        } else {
            this.curriculoService.editar(curriculoSemRelacionamentoMapper.curriculoDtoToCurriculo(this.curriculo),
                    experienciaProfissionalMapper.listaExperienciaProfissionalToListaExperienciaProfissional(this.curriculo.getListaExperienciaProfissional()),
                    experienciaProfissionalMapper.listaExperienciaProfissionalToListaExperienciaProfissional(this.listaExperienciaProfissionalRemover));
            this.curriculo = curriculoComRelacionamentoMapper.curriculoToCurriculoDto(this.curriculoService.buscarPorIdComRelacionamento(this.curriculo.getId()));
            inserirMensagemInformativa("Curriculo editado com sucesso!!!");
        }
        this.experienciaProfissional = new ExperienciaProfissionalDto();
    }

    public void incluirExperiencia() {
        this.experienciaProfissional.setCargo(cargoMapper.cargoToCargoDto(this.cargoService.pesquisarPorId(this.experienciaProfissional.getCargo().getId())));
        this.experienciaProfissional.setEmpresa(empresaMapper.empresaToEmpresaDto(this.empresaService.pesquisarPorId(this.experienciaProfissional.getEmpresa().getId())));
        this.curriculo.getListaExperienciaProfissional().add(this.experienciaProfissional);
        this.experienciaProfissional = new ExperienciaProfissionalDto();
    }

    public void removerExperiencia() {
        System.out.println(this.experienciaProfissional.getId());
        this.curriculo.getListaExperienciaProfissional().remove(this.experienciaProfissional);
        if (this.experienciaProfissional.getId() != null && this.experienciaProfissional.getId() > 0) {
            this.listaExperienciaProfissionalRemover.add(this.experienciaProfissional);
        }
        this.experienciaProfissional = new ExperienciaProfissionalDto();
    }

    public void deletar() {
        this.curriculoService.deletar(curriculoComRelacionamentoMapper.curriculoDtoToCurriculo(this.curriculo));
        this.listaCurriculo = curriculoComRelacionamentoMapper.listaCurriculoToListaCurriculoDto(this.curriculoService.buscarTodos());
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

    public List<SelectItem> getListaEmpresa() {
        return listaEmpresa;
    }

    public List<SelectItem> getListaCargo() {
        return listaCargo;
    }

    public ControleSessaoMb getControleSessaoMb() {
        return controleSessaoMb;
    }

    public void setControleSessaoMb(ControleSessaoMb controleSessaoMb) {
        this.controleSessaoMb = controleSessaoMb;
    }
}
