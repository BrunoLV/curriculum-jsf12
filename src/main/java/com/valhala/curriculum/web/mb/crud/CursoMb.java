/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valhala.curriculum.web.mb.crud;

import com.valhala.curriculum.dto.CursoDto;
import com.valhala.curriculum.ejb.CursoService;
import com.valhala.curriculum.mappers.CursoMapper;
import com.valhala.curriculum.web.mb.BaseMb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.util.List;

/**
 * @author bruno
 */
public class CursoMb extends BaseMb {

    private static final String ID_EDICAO_SESSAO = "idParaEdicao";

    @EJB
    private CursoService cursoService;
    private CursoDto curso;
    private List<CursoDto> listaCurso;

    private CursoMapper cursoMapper = CursoMapper.INSTANCE;

    @PostConstruct
    private void init() {
        Integer id = (Integer) getAtributoSessao(ID_EDICAO_SESSAO);
        if (id != null && id > 0) {
            this.curso = cursoMapper.cursoToCursoDto(this.cursoService.pesquisarPorId(id));
            removeAtributoSessao(ID_EDICAO_SESSAO);
        } else {
            this.curso = new CursoDto();
        }
        this.listaCurso = cursoMapper.listaCursoToListaCursoDto(this.cursoService.buscarTodos());
    } // fim do metodo init

    public void salvar() {
        if (curso.getId() == 0) {
            curso.setId(null);
            this.cursoService.salvar(cursoMapper.cursoDtoToCurso(curso));
            inserirMensagemInformativa("Curso inserido com sucesso!!!");
        } else {
            this.cursoService.editar(cursoMapper.cursoDtoToCurso(curso));
            inserirMensagemInformativa("Curso editado com sucesso!!!");
        } // fim do bloco if/else
    } // fim do metodo salvar

    public void deletar() {
        this.cursoService.deletar(cursoMapper.cursoDtoToCurso(curso));
        this.listaCurso = cursoMapper.listaCursoToListaCursoDto(this.cursoService.buscarTodos());
        inserirMensagemInformativa("Curso removido com sucesso!!!");
    } // fim do metodo deletar

    public CursoDto getCurso() {
        return curso;
    }

    public void setCurso(CursoDto curso) {
        this.curso = curso;
    }

    public List<CursoDto> getListaCurso() {
        return listaCurso;
    }

    public void setListaCurso(List<CursoDto> listaCurso) {
        this.listaCurso = listaCurso;
    }

} // fim da classe CursoMb
