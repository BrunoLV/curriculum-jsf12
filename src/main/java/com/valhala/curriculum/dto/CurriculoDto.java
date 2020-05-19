package com.valhala.curriculum.dto;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Data;

@Data
public class CurriculoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
    private UsuarioDto usuario = new UsuarioDto();
    private Set<ExperienciaProfissionalDto> experienciasProfissionais = new LinkedHashSet<ExperienciaProfissionalDto>();
    private Set<FormacaoAcademicaDto> formacoesAcademicas = new LinkedHashSet<FormacaoAcademicaDto>();
	
    public void adicionaExperienciaProfissional(final ExperienciaProfissionalDto experienciaProfissional) {
		if (experienciasProfissionais == null) {
			experienciasProfissionais = new LinkedHashSet<ExperienciaProfissionalDto>();
		}
		experienciasProfissionais.add(experienciaProfissional);
	}

	public void adicionaFormacaoAcademica(final FormacaoAcademicaDto formacaoAcademica) {
		if (formacoesAcademicas == null) {
			formacoesAcademicas = new LinkedHashSet<FormacaoAcademicaDto>();
		}
		formacoesAcademicas.add(formacaoAcademica);
	}

	public void removeExperienciaProfissional(ExperienciaProfissionalDto experienciaProfissional) {
		experienciasProfissionais.remove(experienciaProfissional);
	}

	public void removeFormacaoAcademica(final FormacaoAcademicaDto formacaoAcademica) {
		formacoesAcademicas.remove(formacaoAcademica);
	}

}
