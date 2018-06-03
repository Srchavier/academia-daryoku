package br.com.academiaDaryoku.converter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

import br.com.academiaDaryoku.model.TbTurma;

@FacesConverter(forClass = LocalTimeConverter.class)
public class LocalTimeConverter implements Converter,Serializable {

	private static final long serialVersionUID = -6791679858272049872L;
	
	private DateTimeConverter converter = new DateTimeConverter();

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		
		LocalTime localDateTime = LocalTime.parse(string, DateTimeFormatter.ofPattern("HH:mm"));
		return localDateTime;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		return String.valueOf(o);
	}
	
	public LocalTimeConverter() {
		converter.setPattern("HH:mm");
		converter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
	}

}
