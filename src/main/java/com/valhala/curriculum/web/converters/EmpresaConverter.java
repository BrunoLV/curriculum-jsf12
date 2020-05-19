package com.valhala.curriculum.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.valhala.curriculum.dto.EmpresaDto;

public class EmpresaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		String[] array = value.split("-");
		EmpresaDto empresa = new EmpresaDto();
		empresa.setId(Integer.valueOf(array[0]));
		empresa.setNome(array[1]);
		return empresa;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null) {
			return null;
		}
		EmpresaDto empresa = (EmpresaDto) o;
		return empresa.getId() + "-" + empresa.getNome();
	}

}
