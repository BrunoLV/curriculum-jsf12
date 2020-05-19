package com.valhala.curriculum.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "tb_exp_prof_curriculo")
@EqualsAndHashCode(callSuper = false)
public class ExperienciaProfissional extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "data_inicio", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@Column(name = "data_saida")
	@Temporal(TemporalType.DATE)
	private Date dataSaida;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "id_curriculo")
	private Curriculo curriculo;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "id_cargo")
	private Cargo cargo;

	public void setCurriculo(Curriculo curriculo) {
		if (curriculo == null && this.curriculo != null) {
			this.curriculo.getExperienciasProfissionais().remove(this);
		}
		this.curriculo = curriculo;
		if (this.curriculo != null) {
			this.curriculo.getExperienciasProfissionais().add(this);
		}
	}

}
