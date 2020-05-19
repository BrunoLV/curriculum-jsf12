package com.valhala.curriculum.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.valhala.curriculum.dto.enumerados.RolesDto;

public class RolesConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg) {
		for (RolesDto role : RolesDto.values()) {
			if (role.name().equals(arg)) {
				return role;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null) {
			return null;
		}
		RolesDto papel = (RolesDto) o;
		return papel.getNome();
	}

}
