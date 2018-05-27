package br.com.academiaDaryoku.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.academiaDaryoku.converter.ScheduleEventConverter;
import br.com.academiaDaryoku.model.EventoScheduleModel;
import br.com.academiaDaryoku.model.TbEvento;
import br.com.academiaDaryoku.respository.EventoRepository;
import br.com.academiaDaryoku.ultils.Transacional;

public class EventoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EventoRepository eventoRepository;

	@Transacional
	public TbEvento save(TbEvento tbEvento) {
		return eventoRepository.salva(tbEvento);
	}

	@Transacional
	public void alterar(TbEvento tbEvento) {
		eventoRepository.alterar(tbEvento);
	}

	public List<TbEvento> listar() {
		return eventoRepository.buscaTodos();
	}

	public List<TbEvento> listarMostrarLimit() {
		return eventoRepository.todosMostrarLimit10();
	}

	public List<EventoScheduleModel> listarEventos() {
		List<TbEvento> lista = listar();
		List<EventoScheduleModel> listaEvento = new ArrayList<>();

		lista.forEach(ag -> {
			listaEvento.add(ScheduleEventConverter.getConverter().toSchedule(ag));
		});
		return listaEvento;
	}

}
