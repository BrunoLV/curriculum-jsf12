package com.valhala.curriculum.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by bruno on 16/09/15.
 */
@Entity
@Table(name = "tb_formacao_academica")
public class FormacaoAcademica extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;
    @ManyToOne
    @JoinColumn(name = "id_entidade_ensino")
    private EntidadeEnsino entidadeEnsino;

    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Column(name = "data_termino")
    @Temporal(TemporalType.DATE)
    private Date dataTermino;

    @Column(name = "tipo_formacao")
    @Enumerated(EnumType.STRING)
    private EnumTipoFormacao tipoFormacao;

    @ManyToOne
    @JoinColumn(name = "id_curriculo")
    private Curriculo curriculo;

    public FormacaoAcademica() {
        super();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public EntidadeEnsino getEntidadeEnsino() {
        return entidadeEnsino;
    }

    public void setEntidadeEnsino(EntidadeEnsino entidadeEnsino) {
        this.entidadeEnsino = entidadeEnsino;
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

    public EnumTipoFormacao getTipoFormacao() {
        return tipoFormacao;
    }

    public void setTipoFormacao(EnumTipoFormacao tipoFormacao) {
        this.tipoFormacao = tipoFormacao;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
        if (curriculo != null) {
            this.curriculo.adicionarFormacaoAcademica(this);
        }
    }

}
