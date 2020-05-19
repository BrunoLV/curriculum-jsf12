package com.valhala.curriculum.web.mb.crud;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.valhala.curriculum.dto.CargoDto;
import com.valhala.curriculum.dto.CurriculoDto;
import com.valhala.curriculum.dto.CursoDto;
import com.valhala.curriculum.dto.EmpresaDto;
import com.valhala.curriculum.dto.EntidadeEnsinoDto;
import com.valhala.curriculum.dto.ExperienciaProfissionalDto;
import com.valhala.curriculum.dto.FormacaoAcademicaDto;
import com.valhala.curriculum.dto.UsuarioDto;
import com.valhala.curriculum.dto.enumerados.TipoFormacaoDto;
import com.valhala.curriculum.ejb.CargoService;
import com.valhala.curriculum.ejb.CurriculoService;
import com.valhala.curriculum.ejb.CursoService;
import com.valhala.curriculum.ejb.EmpresaService;
import com.valhala.curriculum.ejb.EntidadeEnsinoService;
import com.valhala.curriculum.ejb.UsuarioService;
import com.valhala.curriculum.mapper.CurriculoSemRelacionamentoMap;
import com.valhala.curriculum.model.Cargo;
import com.valhala.curriculum.model.Curriculo;
import com.valhala.curriculum.model.Curso;
import com.valhala.curriculum.model.Empresa;
import com.valhala.curriculum.model.EntidadeEnsino;
import com.valhala.curriculum.model.ExperienciaProfissional;
import com.valhala.curriculum.model.FormacaoAcademica;
import com.valhala.curriculum.model.Usuario;
import com.valhala.curriculum.web.mb.BaseMb;
import com.valhala.curriculum.web.mb.controle.ControleSessaoMb;

import lombok.Getter;
import lombok.Setter;

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

	@Getter
	@Setter
	private CurriculoDto curriculo;

	@Getter
	@Setter
	private List<CurriculoDto> listaCurriculo;

	@Getter
	private List<SelectItem> listaEmpresa = new ArrayList<SelectItem>();

	@Getter
	private List<SelectItem> listaCargo = new ArrayList<SelectItem>();

	@Getter
	private List<SelectItem> listaCurso = new ArrayList<SelectItem>();

	@Getter
	private List<SelectItem> listaEntidadeEnsino = new ArrayList<SelectItem>();

	@Getter
	@Setter
	private ControleSessaoMb controleSessaoMb;

	private Set<ExperienciaProfissionalDto> listaExperienciaProfissionalRemover = new LinkedHashSet<ExperienciaProfissionalDto>();

	private Set<FormacaoAcademicaDto> listaFormacaoAcademicaRemover = new LinkedHashSet<FormacaoAcademicaDto>();

	@Getter
	@Setter
	private ExperienciaProfissionalDto experienciaProfissional;

	@Getter
	@Setter
	private FormacaoAcademicaDto formacaoAcademica;

	ModelMapper modelMapper = new ModelMapper();

	public void deletar() {
		this.curriculoService.deletar(modelMapper.map(this.curriculo, Curriculo.class));
		carregaListaCurriculos();
		inserirMensagemInformativa("Curriculo removido com sucesso!!!");
	}

	public List<SelectItem> getTiposFormacao() {
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		TipoFormacaoDto[] enumTipoFormacaoDtos = TipoFormacaoDto.values();
		for (int i = 0; i < enumTipoFormacaoDtos.length; i++) {
			selectItems.add(new SelectItem(enumTipoFormacaoDtos[i]));
		}
		return selectItems;
	}

	public void incluirExperiencia() {
		this.curriculo.adicionaExperienciaProfissional(this.experienciaProfissional);
		this.experienciaProfissional = new ExperienciaProfissionalDto();
	}

	public void incluirFormacao() {
		this.curriculo.adicionaFormacaoAcademica(this.formacaoAcademica);
		this.formacaoAcademica = new FormacaoAcademicaDto();
	}

	@PostConstruct
	private void init() {
		Integer id = (Integer) getAtributoSessao(ID_EDICAO_SESSAO);
		if (id != null && id > 0) {
			final Curriculo curriculoEdicao = this.curriculoService.buscarPorIdComRelacionamento(id);
			this.curriculo = modelMapper.map(curriculoEdicao, CurriculoDto.class);
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
		carregaListaCurriculos();
	}

	private void carregaListaCurriculos() {
		
		ModelMapper mapperCurriculo = new ModelMapper();
		mapperCurriculo.addMappings(new CurriculoSemRelacionamentoMap());
		
		Type type = new TypeToken<List<CurriculoDto>>() {}.getType();
		this.listaCurriculo = mapperCurriculo.map(this.curriculoService.buscarTodos(), type);
	}

	private void initCargos() {
		final List<Cargo> cargos = this.cargoService.buscarTodos();

		Type type = new TypeToken<List<CargoDto>>() {
		}.getType();
		final List<CargoDto> dtos = modelMapper.map(cargos, type);

		for (CargoDto dto : dtos) {
			this.listaCargo.add(new SelectItem(dto, dto.getId() + "-" + dto.getNome()));
		}
	}

	private void initCursos() {
		final List<Curso> cursos = this.cursoService.buscarTodos();

		Type type = new TypeToken<List<CursoDto>>() {
		}.getType();
		final List<CursoDto> dtos = modelMapper.map(cursos, type);

		for (CursoDto dto : dtos) {
			this.listaCurso.add(new SelectItem(dto, dto.getId() + "-" + dto.getNome()));
		}
	}

	private void initEmpresas() {
		final List<Empresa> empresas = this.empresaService.buscarTodos();

		Type type = new TypeToken<List<EmpresaDto>>() {
		}.getType();
		final List<EmpresaDto> dtos = modelMapper.map(empresas, type);
		
		for (EmpresaDto dto : dtos) {
			this.listaEmpresa.add(new SelectItem(dto, dto.getId() + "-" + dto.getNome()));
		}
	}

	private void initEntidadesEnsino() {
		final List<EntidadeEnsino> entidadesEnsino = this.entidadeEnsinoService.buscarTodos();

		Type type = new TypeToken<List<EntidadeEnsinoDto>>() {
		}.getType();
		final List<EntidadeEnsinoDto> dtos = modelMapper.map(entidadesEnsino, type);
		
		for (EntidadeEnsinoDto dto : dtos) {
			this.listaEntidadeEnsino.add(new SelectItem(dto, dto.getId() + "-" + dto.getNome()));
		}
	}

	public void removerExperiencia() {
		this.curriculo.removeExperienciaProfissional(this.experienciaProfissional);
		if (this.experienciaProfissional.getId() != null && this.experienciaProfissional.getId() > 0) {
			listaExperienciaProfissionalRemover.add(experienciaProfissional);
		}
		this.experienciaProfissional = new ExperienciaProfissionalDto();
	}

	public void removerFormacao() {
		this.curriculo.removeFormacaoAcademica(this.formacaoAcademica);
		this.curriculo.getFormacoesAcademicas().remove(this.formacaoAcademica);
		if (this.formacaoAcademica.getId() != null && this.formacaoAcademica.getId() > 0) {
			listaFormacaoAcademicaRemover.add(formacaoAcademica);
		}
		this.formacaoAcademica = new FormacaoAcademicaDto();
	}

	public void salvar() {
		final Usuario usuario = this.usuarioService.buscarUsuarioPorEmail(controleSessaoMb.getPrincipalName());

		final UsuarioDto usuarioDto = modelMapper.map(usuario, UsuarioDto.class);

		this.curriculo.setUsuario(usuarioDto);
		if (this.curriculo.getId() == 0) {
			this.curriculo.setId(null);
			this.curriculoService.salvar(modelMapper.map(curriculo, Curriculo.class));
			inserirMensagemInformativa("Curriculo inserido com sucesso!!!");
			this.curriculo = new CurriculoDto();
		} else {
			@SuppressWarnings("rawtypes")
			Map<String, Set> mapaDeListaRemocaoRelacionamento = new HashMap<String, Set>();

			Type tipoExperienciaProfissional = new TypeToken<Set<ExperienciaProfissional>>() {}.getType();
			Set<ExperienciaProfissional> experienciasRemover = modelMapper.map(this.listaExperienciaProfissionalRemover, tipoExperienciaProfissional);
			mapaDeListaRemocaoRelacionamento.put("ExperienciaProfissional", experienciasRemover);

			Type tipoFormacaoAcademica = new TypeToken<Set<FormacaoAcademica>>() {}.getType();
			Set<FormacaoAcademica> formacoesRemover = modelMapper.map(this.listaFormacaoAcademicaRemover, tipoFormacaoAcademica);
			mapaDeListaRemocaoRelacionamento.put("FormacaoAcademica", formacoesRemover);

			this.curriculoService.editar(modelMapper.map(curriculo, Curriculo.class), mapaDeListaRemocaoRelacionamento);
			inserirMensagemInformativa("Curriculo editado com sucesso!!!");
		}
		this.experienciaProfissional = new ExperienciaProfissionalDto();
		this.formacaoAcademica = new FormacaoAcademicaDto();
	}
	
	public List<FormacaoAcademicaDto> getFormacoes() {
    	return new ArrayList<FormacaoAcademicaDto>(this.curriculo.getFormacoesAcademicas());
    }
    
    public List<ExperienciaProfissionalDto> getExperiencias() {
    	return new ArrayList<ExperienciaProfissionalDto>(this.curriculo.getExperienciasProfissionais());
    }

}
