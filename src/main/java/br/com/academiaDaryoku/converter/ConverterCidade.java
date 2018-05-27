package br.com.academiaDaryoku.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import br.com.academiaDaryoku.model.TbCidade;
import br.com.academiaDaryoku.service.CidadeService;

public class ConverterCidade implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CidadeService cidadeService;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.equals("Selecione a cidade")) {
			return null;
		}
		return cidadeService.findOne(Integer.parseInt(string));

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null) {
			return null;
		}
		return String.valueOf(((TbCidade) o).getIdCidade());
	}

}