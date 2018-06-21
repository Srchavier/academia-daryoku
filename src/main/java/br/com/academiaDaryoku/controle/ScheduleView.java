package br.com.academiaDaryoku.controle;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
		listaEventos = eventoService.listarMostrarLimit();
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

	public synchronized boolean addEvent(ActionEvent actionEvent) throws ParseException {
		if(!eventoInicioEFim(event)) {
			UtilMensagens.mensagemInformacao("Data Início da evento maior que data fim ou inferior a hoje!");
			PrimeFaces.current().ajax().addCallbackParam("validacaoMat", false);
			PrimeFaces.current().ajax().update("messages");
			return false;
		}
		
		try {
			if (event.getId() == null) {

				tbEvento = ScheduleEventConverter.getConverter().toAgendamento((EventoScheduleModel) event);

				eventoService.save(tbEvento);

				UtilMensagens.mensagemInformacao("Evento salvo com sucesso!");
				PrimeFaces.current().ajax().addCallbackParam("validacaoMat", true);
				PrimeFaces.current().ajax().update("messages");

			} else {
				TbEvento tbEvento = ScheduleEventConverter.getConverter().toAgendamento((EventoScheduleModel) event);
				eventoService.alterar(tbEvento);

				UtilMensagens.mensagemInformacao("Evento alterando com sucesso!");
				PrimeFaces.current().ajax().addCallbackParam("validacaoMat", true);
				PrimeFaces.current().ajax().update("messages");
			}

			tbEvento = new TbEvento();
			event = new EventoScheduleModel();

			buscarTodos();
			return true;
		} catch (Exception e) {
			UtilMensagens.mensagemErro("Erro ao cadastrar!");
			return false;
		}

	}

	private boolean eventoInicioEFim(ScheduleEvent data) {
		return data.getStartDate().before(data.getEndDate()) && data.getStartDate().after(new Date())
				&& data.getStartDate().after(new Date());
	}

	public void excluir() {
		try {
			if (event.getData() != null) {
				tbEvento = ScheduleEventConverter.getConverter().toAgendamento((EventoScheduleModel) event);
				// eventoService.delete(tbEvento.getId_Evento());
			} else {
				UtilMensagens.mensagemInformacao("Nao existe registro");
			}
			UtilMensagens.mensagemInformacao("Evento excluído com sucesso!");
			buscarTodos();
		} catch (Exception e) {
			UtilMensagens.mensagemErro("Erro ao excluir cadastrar!");
		}
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (EventoScheduleModel) selectEvent.getObject();
	}

	public List<TbTurma> listarTurma() {
		return turmaService.findAll();
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


}
