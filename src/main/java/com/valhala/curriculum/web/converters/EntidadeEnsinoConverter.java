package com.valhala.curriculum.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.valhala.curriculum.dto.EntidadeEnsinoDto;

public class EntidadeEnsinoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		String[] array = value.split("-");
		EntidadeEnsinoDto entidadeEnsino = new EntidadeEnsinoDto();
		entidadeEnsino.setId(Integer.valueOf(array[0]));
		entidadeEnsino.setNome(array[1]);
		return entidadeEnsino;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null) {
			return null;
		}
		EntidadeEnsinoDto entidadeEnsino = (EntidadeEnsinoDto) o;
		return entidadeEnsino.getId() + "-" + entidadeEnsino.getNome();
	}

}
