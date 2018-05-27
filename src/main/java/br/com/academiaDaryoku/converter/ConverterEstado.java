package br.com.academiaDaryoku.converter;

import java.io.Serializable;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import br.com.academiaDaryoku.model.TbCidade;
import br.com.academiaDaryoku.model.TbEstado;
import br.com.academiaDaryoku.service.EstadoService;

public class ConverterEstado implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoService estadoService;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.equals("Selecione estado")) {
			return null;
		}
		TbEstado tbEstado = estadoService.findId(Integer.parseInt(string));

		return tbEstado;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null) {
			return null;
		}
		return String.valueOf(((TbEstado) o).getIdEstado());
	}

	public List<TbCidade> findAll(int idEstado) {
		return null;
	}

}