package com.valhala.curriculum.web.mb.crud;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.valhala.curriculum.dto.CursoDto;
import com.valhala.curriculum.ejb.CursoService;
import com.valhala.curriculum.model.Curso;
import com.valhala.curriculum.web.mb.BaseMb;

import lombok.Getter;
import lombok.Setter;

public class CursoMb extends BaseMb {

	private static final long serialVersionUID = 6604716796555886204L;

	private static final String ID_EDICAO_SESSAO = "idParaEdicao";

	@EJB
	private CursoService cursoService;

	@Getter
	@Setter
	private CursoDto curso;

	@Getter
	@Setter
	private List<CursoDto> listaCurso;

	ModelMapper modelMapper = new ModelMapper();

	public void deletar() {
		this.cursoService.deletar(modelMapper.map(curso, Curso.class));
		carregaListaCursos();
		inserirMensagemInformativa("Curso removido com sucesso!!!");
	}

	@PostConstruct
	private void init() {
		Integer id = (Integer) getAtributoSessao(ID_EDICAO_SESSAO);
		if (id != null && id > 0) {
			this.curso = modelMapper.map(this.cursoService.pesquisarPorId(id), CursoDto.class);
			removeAtributoSessao(ID_EDICAO_SESSAO);
		} else {
			this.curso = new CursoDto();
		}
		carregaListaCursos();
	}

	private void carregaListaCursos() {
		Type type = new TypeToken<List<CursoDto>>() {
		}.getType();
		this.listaCurso = modelMapper.map(this.cursoService.buscarTodos(), type);
	}

	public void salvar() {
		if (curso.getId() == 0) {
			curso.setId(null);
			this.cursoService.salvar(modelMapper.map(curso, Curso.class));
			inserirMensagemInformativa("Curso inserido com sucesso!!!");
		} else {
			this.cursoService.editar(modelMapper.map(curso, Curso.class));
			inserirMensagemInformativa("Curso editado com sucesso!!!");
		}
	}

}
