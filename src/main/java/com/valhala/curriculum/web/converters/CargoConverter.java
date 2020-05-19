package com.valhala.curriculum.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.valhala.curriculum.dto.CargoDto;

public class CargoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		String[] array = value.split("-");
		CargoDto cargo = new CargoDto();
		cargo.setId(Integer.valueOf(array[0]));
		cargo.setNome(array[1]);
		return cargo;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null) {
			return null;
		}
		CargoDto cargo = (CargoDto) o;
		return cargo.getId() + "-" + cargo.getNome();
	}

}
