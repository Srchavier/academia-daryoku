package br.com.academiaDaryoku.controle;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.academiaDaryoku.converter.ConverterTurma;
import br.com.academiaDaryoku.converter.ScheduleEventConverter;
import br.com.academiaDaryoku.model.EventoScheduleModel;
import br.com.academiaDaryoku.model.TbEvento;
import br.com.academiaDaryoku.model.TbTurma;
import br.com.academiaDaryoku.service.EventoService;
import br.com.academiaDaryoku.service.TurmaService;
import br.com.academiaDaryoku.ultils.RepeatPaginator;
import br.com.academiaDaryoku.ultils.Transacional;
import br.com.academiaDaryoku.ultils.UtilMensagens;

@Named
@ViewScoped
public class ScheduleView implements Serializable {

	private static final long serialVersionUID = 1L;

	private ScheduleModel eventModel;

	private ScheduleEvent event;

	private TbEvento tbEvento;

	private List<EventoScheduleModel> agenda;

	private List<TbEvento> listaEventos;

	private RepeatPaginator paginator;

	@Inject
	private ConverterTurma converterTurma;

	@Inject
	private EventoService eventoService;

	@Inject
	private TurmaService turmaService;

	@PostConstruct
	public void init() {
		eventModel = new DefaultScheduleModel();
		event = new EventoScheduleModel();
		tbEvento = new TbEvento();
		agenda = new ArrayList<>();
		buscarTodos();

		listaEventos = new ArrayList<>();
		listaTodos();
		paginator = new RepeatPaginator(this.listaEventos);
	}

	private void buscarTodos() {
		agenda = eventoService.listarEventos();
		eventModel.clear();
		agenda.forEach(ag -> {
			eventModel.addEvent(ag);
		});
	}

	public void prepararEvento() {
		event = new EventoScheduleModel();
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	@Transacional
	public ScheduleEvent getEvent() {
		return event;

	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public synchronized void addEvent(ActionEvent actionEvent) throws ParseException {
		if (event.getId() == null) {

			tbEvento = ScheduleEventConverter.getConverter().toAgendamento((EventoScheduleModel) event);

			eventoService.save(tbEvento);

			UtilMensagens.mensagemInformacao("Salvo com sucesso!");
			PrimeFaces.current().ajax().addCallbackParam("validacaoMat", true);
			PrimeFaces.current().ajax().update("messages");

		} else {
			TbEvento tbEvento = ScheduleEventConverter.getConverter().toAgendamento((EventoScheduleModel) event);
			eventoService.alterar(tbEvento);

			UtilMensagens.mensagemInformacao("Alterando com sucesso!");
			PrimeFaces.current().ajax().addCallbackParam("validacaoMat", true);
			PrimeFaces.current().ajax().update("messages");
		}

		tbEvento = new TbEvento();
		event = new EventoScheduleModel();

		buscarTodos();

	}

	public void excluir() {
		if (event.getData() != null) {
			tbEvento = ScheduleEventConverter.getConverter().toAgendamento((EventoScheduleModel) event);
			// eventoService.delete(tbEvento.getId_Evento());
		} else {
			UtilMensagens.mensagemInformacao("Nao existe regristo");
		}
		UtilMensagens.mensagemInformacao("Excluído com sucesso!");
		buscarTodos();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (EventoScheduleModel) selectEvent.getObject();
	}

	public List<TbTurma> listarTurma() {
		return turmaService.findAll();
	}

	public List<TbEvento> listaTodos() {
		return this.listaEventos = eventoService.listarMostrarLimit();
	}

	public ConverterTurma getConverterTurma() {
		return converterTurma;
	}

	public void setConverterTurma(ConverterTurma converterTurma) {
		this.converterTurma = converterTurma;
	}

	public List<TbEvento> getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(List<TbEvento> listaEventos) {
		this.listaEventos = listaEventos;
	}

	public RepeatPaginator getRepeatPaginator() {
		return paginator;
	}

}
