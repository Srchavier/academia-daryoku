package br.com.academiaDaryoku.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import br.com.academiaDaryoku.model.TbTurma;
import br.com.academiaDaryoku.service.TurmaService;

public class ConverterTurma implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaService turmaService;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.equals("Selecione uma turma")) {
			return null;
		}
		return turmaService.find(TbTurma.class, Integer.parseInt(string));

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null) {
			return null;
		}
		return String.valueOf(((TbTurma) o).getIdTurma());
	}

}