package com.valhala.curriculum.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tb_curriculo")
@EqualsAndHashCode(callSuper = false)
public class Curriculo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -3875979851399236476L;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "curriculo", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<ExperienciaProfissional> experienciasProfissionais = new LinkedHashSet<ExperienciaProfissional>();

	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "curriculo", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<FormacaoAcademica> formacoesAcademicas = new LinkedHashSet<FormacaoAcademica>();

	public void adicionaExperiencia(final ExperienciaProfissional experienciaProfissional) {
		if (experienciasProfissionais == null) {
			experienciasProfissionais = new LinkedHashSet<ExperienciaProfissional>();
		}
		experienciasProfissionais.add(experienciaProfissional);
	}

	public void adicionaFormacao(final FormacaoAcademica formacaoAcademica) {
		if (formacoesAcademicas == null) {
			formacoesAcademicas = new LinkedHashSet<FormacaoAcademica>();
		}
		formacoesAcademicas.add(formacaoAcademica);
	}

}
