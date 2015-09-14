package com.valhala.curriculum.web.mb.crud;

import com.valhala.curriculum.dto.*;
import com.valhala.curriculum.ejb.CargoService;
import com.valhala.curriculum.ejb.CurriculoService;
import com.valhala.curriculum.ejb.EmpresaService;
import com.valhala.curriculum.ejb.UsuarioService;
import com.valhala.curriculum.model.*;
import com.valhala.curriculum.utils.api.Parser;
import com.valhala.curriculum.utils.impl.*;
import com.valhala.curriculum.web.mb.BaseMB;
import com.valhala.curriculum.web.mb.controle.ControleSessaoMB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 05/09/15.
 */
public class CurriculoMB extends BaseMB {

    private static final Parser<Curriculo, CurriculoDTO> PARSER_CURRICULO = new ParserCurriculoToCurriculoDTO();
    private static final Parser<Cargo, CargoDTO> PARSER_CARGO = new ParserCargoToCargoDTO();
    private static final Parser<Empresa, EmpresaDTO> PARSER_EMPRESA = new ParserEmpresaToEmpresaDTO();
    private static final Parser<Usuario, UsuarioDTO> PARSER_USUARIO = new ParserUsuarioToUsuarioDTO();
    private static final Parser<ExperienciaProfissional, ExperienciaProfissionalDTO> PARSER_EXPERIENCIA = new ParserExperienciaToExperienciaDTO();

    @EJB
    private CurriculoService curriculoService;
    @EJB
    private CargoService cargoService;
    @EJB
    private EmpresaService empresaService;
    @EJB
    private UsuarioService usuarioService;

    private CurriculoDTO curriculoDTO;
    private List<CurriculoDTO> curriculoDTOs;
    private List<SelectItem> empresas = new ArrayList<SelectItem>();
    private List<SelectItem> cargos = new ArrayList<SelectItem>();

    private ExperienciaProfissionalDTO experienciaProfissionalDTO;

    private ControleSessaoMB sessaoMB;

    public CurriculoDTO getCurriculoDTO() {
        return curriculoDTO;
    }

    public void setCurriculoDTO(CurriculoDTO curriculoDTO) {
        this.curriculoDTO = curriculoDTO;
    }

    @PostConstruct
    private void init() {
        Integer id = (Integer) getAtributoSessao("idParaEdicao");
        if (id != null && id > 0) {
            this.curriculoDTO = PARSER_CURRICULO.parse(this.curriculoService.buscarPorIdComRelacionamento(id));
            removeAtributoSessao("idParaEdicao");
        } else {
            this.curriculoDTO = new CurriculoDTO();
        }
        initCargos();
        initEmpresas();
        experienciaProfissionalDTO = new ExperienciaProfissionalDTO();
        this.curriculoDTOs = PARSER_CURRICULO.massiveParseWithoutRelationship(this.curriculoService.buscarTodos());
    }

    private void initCargos() {
        List<CargoDTO> dtos = PARSER_CARGO.massiveParse(this.cargoService.buscarTodos());
        for (CargoDTO dto : dtos) {
            this.cargos.add(new SelectItem(dto.getId(), dto.getNome()));
        }
    }

    private void initEmpresas() {
        List<EmpresaDTO> dtos = PARSER_EMPRESA.massiveParse(this.empresaService.buscarTodos());
        for (EmpresaDTO dto : dtos) {
            this.empresas.add(new SelectItem(dto.getId(), dto.getNome()));
        }
    }

    public void salvar() {
        UsuarioDTO usuarioDTO = PARSER_USUARIO.parse(this.usuarioService.buscarUsuarioPorEmail(sessaoMB.getPrincipalName()));
        this.curriculoDTO.setUsuarioDTO(usuarioDTO);
        if (this.curriculoDTO.getId() == 0) {
            this.curriculoDTO.setId(null);
            this.curriculoService.salvar(PARSER_CURRICULO.inverseParse(this.curriculoDTO),
                    PARSER_EXPERIENCIA.massiveInverseParse(this.curriculoDTO.getExperienciaProfissionalDTOsIncluir()));
            inserirMensagemInformativa("Curriculo inserido com sucesso!!!");
            this.curriculoDTO = new CurriculoDTO();
        } else {
            this.curriculoService.editar(PARSER_CURRICULO.inverseParse(this.curriculoDTO),
                    PARSER_EXPERIENCIA.massiveInverseParse(this.curriculoDTO.getExperienciaProfissionalDTOsIncluir()),
                    PARSER_EXPERIENCIA.massiveInverseParse(this.curriculoDTO.getExperienciaProfissionalDTOsRemover()));
            this.curriculoDTO = PARSER_CURRICULO.parse(this.curriculoService.buscarPorIdComRelacionamento(this.curriculoDTO.getId()));
            inserirMensagemInformativa("Curriculo editado com sucesso!!!");
        }
        this.experienciaProfissionalDTO = new ExperienciaProfissionalDTO();
    }

    public void incluirExperiencia() {
        this.experienciaProfissionalDTO.setCargoDTO(PARSER_CARGO.parse(this.cargoService.pesquisarPorId(this.experienciaProfissionalDTO.getCargoDTO().getId())));
        this.experienciaProfissionalDTO.setEmpresaDTO(PARSER_EMPRESA.parse(this.empresaService.pesquisarPorId(this.experienciaProfissionalDTO.getEmpresaDTO().getId())));
        this.curriculoDTO.getExperienciaProfissionalDTOsIncluir().add(this.experienciaProfissionalDTO);
        this.experienciaProfissionalDTO = new ExperienciaProfissionalDTO();
    }

    public void removerExperiencia() {
        System.out.println(this.experienciaProfissionalDTO.getId());
        this.curriculoDTO.getExperienciaProfissionalDTOsIncluir().remove(this.experienciaProfissionalDTO);
        if (this.experienciaProfissionalDTO.getId() != null && this.experienciaProfissionalDTO.getId() > 0){
            this.curriculoDTO.getExperienciaProfissionalDTOsRemover().add(this.experienciaProfissionalDTO);
        }
        this.experienciaProfissionalDTO = new ExperienciaProfissionalDTO();
    }

    public void deletar() {
        this.curriculoService.deletar(PARSER_CURRICULO.inverseParse(this.curriculoDTO));
        this.curriculoDTOs = PARSER_CURRICULO.massiveParseWithoutRelationship(this.curriculoService.buscarTodos());
        inserirMensagemInformativa("Curriculo removido com sucesso!!!");
    }

    public List<CurriculoDTO> getCurriculoDTOs() {
        return curriculoDTOs;
    }

    public void setCurriculoDTOs(List<CurriculoDTO> curriculoDTOs) {
        this.curriculoDTOs = curriculoDTOs;
    }

    public ExperienciaProfissionalDTO getExperienciaProfissionalDTO() {
        return experienciaProfissionalDTO;
    }

    public void setExperienciaProfissionalDTO(ExperienciaProfissionalDTO experienciaProfissionalDTO) {
        this.experienciaProfissionalDTO = experienciaProfissionalDTO;
    }

    public List<SelectItem> getEmpresas() {
        return empresas;
    }

    public List<SelectItem> getCargos() {
        return cargos;
    }

    public ControleSessaoMB getSessaoMB() {
        return sessaoMB;
    }

    public void setSessaoMB(ControleSessaoMB sessaoMB) {
        this.sessaoMB = sessaoMB;
    }

}
