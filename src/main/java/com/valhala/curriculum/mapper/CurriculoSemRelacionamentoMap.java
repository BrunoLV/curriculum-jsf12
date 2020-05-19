package com.valhala.curriculum.mapper;

import org.modelmapper.PropertyMap;

import com.valhala.curriculum.dto.CurriculoDto;
import com.valhala.curriculum.model.Curriculo;

public class CurriculoSemRelacionamentoMap extends PropertyMap<Curriculo, CurriculoDto> {

	@Override
	protected void configure() {
		skip().setExperienciasProfissionais(null);
		skip().setFormacoesAcademicas(null);
	}

}
