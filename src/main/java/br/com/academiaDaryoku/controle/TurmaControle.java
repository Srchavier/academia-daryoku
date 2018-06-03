package br.com.academiaDaryoku.controle;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.academiaDaryoku.converter.LocalTimeConverter;
import br.com.academiaDaryoku.model.TbDiasSemana;
import br.com.academiaDaryoku.model.TbTurma;
import br.com.academiaDaryoku.service.TurmaService;
import br.com.academiaDaryoku.ultils.UtilMensagens;

@Named(value = "turmaControle")
@ViewScoped
public class TurmaControle implements Serializable {

	private static final long serialVersionUID = 7562278849777410982L;
	
	private TbTurma tbTurma;
	private TbTurma turmaSelecionada;
	private List<TbTurma> turmas;
	
	private LocalTime hrInicio;
	private LocalTime hrFim;
	
	@Inject
	private TurmaService turmaService;
	
	@Inject
	private LocalTimeConverter localConverter;
	
	@PostConstruct
	public void init() {
		tbTurma= new TbTurma();
		turmaSelecionada = new TbTurma();
		turmas = new ArrayList<>();
		turmas = turmaService.listarTodos();
	}
	
	public void editarTurma() {
		tbTurma = turmaService.findTurma(turmaSelecionada.getNome());
	}
	
	public boolean salvaTurma() {
		if(tbTurma != null && hrFim != null && hrInicio != null) {
			turmaService.salvar(tbTurma, hrInicio, hrFim);
			UtilMensagens.mensagemInformacao("Turma criada com sucesso!");
			return true;
		}
		return false;
	}
	
	
	public boolean alterarTurma() {
		PrimeFaces.current().ajax().addCallbackParam("validacaoForm", true);
		UtilMensagens.mensagemInformacao("Turma alterada com sucesso!");
		turmaService.alterar(tbTurma);
		return true;
	}
	
	public List<TbDiasSemana> listaDias(){
		return turmaService.listDias();
	}

	public List<TbTurma> getTurmas() {
		return turmas;
	}
	
	public TbTurma getTurmaSelecionada() {
		return turmaSelecionada;
	}
	public void setTurmaSelecionada(TbTurma turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}

	public TbTurma getTbTurma() {
		return tbTurma;
	}

	public void setTbTurma(TbTurma tbTurma) {
		this.tbTurma = tbTurma;
	}

	public LocalTime getHrInicio() {
		return hrInicio;
	}

	public void setHrInicio(LocalTime hrInicio) {
		this.hrInicio = hrInicio;
	}

	public LocalTime getHrFim() {
		return hrFim;
	}

	public void setHrFim(LocalTime hrFim) {
		this.hrFim = hrFim;
	}

	public LocalTimeConverter getLocalConverter() {
		return localConverter;
	}

	public void setLocalConverter(LocalTimeConverter localConverter) {
		this.localConverter = localConverter;
	}

}
