package com.valhala.curriculum.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bruno on 17/09/15.
 */
public class FormacaoAcademicaDto implements Serializable {

    private Long id;
    private Date dataInicio;
    private Date dataTermino;
    private CursoDto curso = new CursoDto();
    private EntidadeEnsinoDto entidadeEnsino = new EntidadeEnsinoDto();
    private EnumTipoFormacaoDto tipoFormacao;

    public FormacaoAcademicaDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public CursoDto getCurso() {
        return curso;
    }

    public void setCurso(CursoDto cursoDto) {
        this.curso = cursoDto;
    }

    public EntidadeEnsinoDto getEntidadeEnsino() {
        return entidadeEnsino;
    }

    public void setEntidadeEnsino(EntidadeEnsinoDto entidadeEnsino) {
        this.entidadeEnsino = entidadeEnsino;
    }

    public EnumTipoFormacaoDto getTipoFormacao() {
        return tipoFormacao;
    }

    public void setTipoFormacao(EnumTipoFormacaoDto tipoFormacao) {
        this.tipoFormacao = tipoFormacao;
    }

}
