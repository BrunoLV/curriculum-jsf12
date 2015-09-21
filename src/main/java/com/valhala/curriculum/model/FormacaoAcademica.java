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

    private static final long serialVersionUID = -2312078967255925815L;
	
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
    	if (curriculo == null) {
			if (this.curriculo != null) {
				this.curriculo.getFormacoesAcademicas().remove(this);
			}
		}
        this.curriculo = curriculo;
        if (curriculo != null) {
            this.curriculo.getFormacoesAcademicas().add(this);
        }
    }

	@Override
	public int hashCode() {
		int result = 21;
		result = 31 * result + ((curso == null) ? 0 : curso.hashCode());
		result = 31 * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = 31 * result + ((dataTermino == null) ? 0 : dataTermino.hashCode());
		result = 31 * result + ((entidadeEnsino == null) ? 0 : entidadeEnsino.hashCode());
		result = 31 * result + ((tipoFormacao == null) ? 0 : tipoFormacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof FormacaoAcademica))
			return false;
		FormacaoAcademica other = (FormacaoAcademica) obj;

		return other.dataInicio.equals(this.dataInicio) && 
				other.dataTermino.equals(this.dataTermino) && 
				other.tipoFormacao.equals(this.tipoFormacao) && 
				other.curso.equals(this.curso) &&
				other.entidadeEnsino.equals(this.entidadeEnsino);
	}

	@Override
	public String toString() {
		return "FormacaoAcademica ["
				+ (curso != null ? "curso=" + curso + ", " : "")
				+ (entidadeEnsino != null ? "entidadeEnsino=" + entidadeEnsino
						+ ", " : "")
				+ (dataInicio != null ? "dataInicio=" + dataInicio + ", " : "")
				+ (dataTermino != null ? "dataTermino=" + dataTermino + ", "
						: "")
				+ (tipoFormacao != null ? "tipoFormacao=" + tipoFormacao : "")
				+ "]";
	}

}
