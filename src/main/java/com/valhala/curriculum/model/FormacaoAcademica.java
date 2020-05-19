package com.valhala.curriculum.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.valhala.curriculum.model.enumerados.TipoFormacao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "tb_formacao_academica")
@EqualsAndHashCode(callSuper = false)
public class FormacaoAcademica extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

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
	private TipoFormacao tipoFormacao;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "id_curriculo")
	private Curriculo curriculo;

	public void setCurriculo(Curriculo curriculo) {
		if (curriculo == null && this.curriculo != null) {
			this.curriculo.getFormacoesAcademicas().remove(this);
		}
		this.curriculo = curriculo;
		if (this.curriculo != null) {
			this.curriculo.getFormacoesAcademicas().add(this);
		}
	}

}
