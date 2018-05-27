package br.com.academiaDaryoku.converter;

import br.com.academiaDaryoku.model.EventoScheduleModel;
import br.com.academiaDaryoku.model.TbEvento;

public class ScheduleEventConverter {

	private static ScheduleEventConverter converter;

	private ScheduleEventConverter() {
	}

	public static ScheduleEventConverter getConverter() {
		if (converter == null)
			converter = new ScheduleEventConverter();

		return converter;
	}

	public TbEvento toAgendamento(EventoScheduleModel event) {
		if (event != null) {
			TbEvento a = new TbEvento();
			a.setId_Evento((Long) event.getData());
			a.setNome(event.getTitle());
			a.setDateInicil(event.getStartDate());
			a.setDateFim(event.getEndDate());
			a.setTbTurmas(event.getTbTurma());
			a.setDescricao(event.getDescription());
			a.setSubTitulo(event.getSubTitulo());
			return a;
		}
		return null;
	}

	public EventoScheduleModel toSchedule(TbEvento tbEvento) {
		if (tbEvento != null) {
			EventoScheduleModel as = new EventoScheduleModel();
			as.setData(tbEvento.getId_Evento());
			as.setTitle(tbEvento.getNome());
			as.setTbTurma(tbEvento.getTbTurmas());
			as.setStartDate(tbEvento.getDateInicil());
			as.setEndDate(tbEvento.getDateFim());
			as.setDescription(tbEvento.getDescricao());
			as.setSubTitulo(tbEvento.getSubTitulo());
			return as;
		}
		return null;
	}

}