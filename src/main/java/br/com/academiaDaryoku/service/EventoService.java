package br.com.academiaDaryoku.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.academiaDaryoku.converter.ScheduleEventConverter;
import br.com.academiaDaryoku.filters.SessionContext;
import br.com.academiaDaryoku.model.EventoScheduleModel;
import br.com.academiaDaryoku.model.TbEvento;
import br.com.academiaDaryoku.model.TbPessoa;
import br.com.academiaDaryoku.model.TbUsuario;
import br.com.academiaDaryoku.respository.EventoRepository;
import br.com.academiaDaryoku.respository.PessoaRepository;
import br.com.academiaDaryoku.ultils.Transacional;

public class EventoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EventoRepository eventoRepository;
	
	@Inject
	private PessoaRepository pessoaRepository;

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
		TbUsuario usuarioEntity = (TbUsuario) SessionContext.getInstance().getAttribute("usuarioLogado");
		TbPessoa pessoa = pessoaRepository.porId(usuarioEntity.getTbPessoa().getIdPessoa());
		return eventoRepository.todosMostrarLimit10(pessoa.getTbTurma().getIdTurma());
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
