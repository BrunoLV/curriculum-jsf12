package com.valhala.curriculum.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.valhala.curriculum.dto.CursoDto;

public class CursoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		String[] array = value.split("-");
		CursoDto curso = new CursoDto();
		curso.setId(Integer.valueOf(array[0]));
		curso.setNome(array[1]);
		return curso;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null) {
			return null;
		}
		CursoDto curso = (CursoDto) o;
		return curso.getId() + "-" + curso.getNome();
	}

}
