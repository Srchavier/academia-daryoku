package br.com.academiaDaryoku.converter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = ConvertCalendar.class)
public class ConvertCalendar implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private DateTimeConverter converter = new DateTimeConverter();

	// converte da tela para o objeto
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		Date data = (Date) converter.getAsObject(arg0, arg1, string);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar;
	}

	// converte do objeto para a tela
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		Calendar calendar = (Calendar) obj;
		return converter.getAsString(arg0, arg1, calendar.getTime());
	}

	public void CalendarConverter() {
		converter.setPattern("dd/MM/yyyy");
		converter.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
	}
}
