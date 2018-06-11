package br.com.academiaDaryoku.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import br.com.academiaDaryoku.model.TbAula;
import br.com.academiaDaryoku.service.AulaService;

public class ConverterAula implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AulaService aulaService;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.equals("Selecione uma aula")) {
			return null;
		}
		return aulaService.find(TbAula.class, Integer.parseInt(string));

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null) {
			return null;
		}
		return String.valueOf(((TbAula) o).getIdAula());
	}

}