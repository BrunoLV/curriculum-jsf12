package com.valhala.curriculum.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.valhala.curriculum.dto.enumerados.TipoFormacaoDto;

public class TipoFormacaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		for (TipoFormacaoDto tipo : TipoFormacaoDto.values()) {
			if (tipo.getDescricao().equals(value)) {
				return tipo;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null) {
			return null;
		}
		TipoFormacaoDto tipo = (TipoFormacaoDto) o;
		return tipo.getDescricao();
	}

}
