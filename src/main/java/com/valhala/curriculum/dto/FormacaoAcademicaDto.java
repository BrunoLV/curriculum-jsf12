package com.valhala.curriculum.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bruno on 17/09/15.
 */
public class FormacaoAcademicaDto implements Serializable {

    private static final long serialVersionUID = 2910277007490051611L;
	
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
		if (!(obj instanceof FormacaoAcademicaDto))
			return false;
		FormacaoAcademicaDto other = (FormacaoAcademicaDto) obj;

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
