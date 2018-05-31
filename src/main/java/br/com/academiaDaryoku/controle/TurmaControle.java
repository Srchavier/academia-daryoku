package br.com.academiaDaryoku.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.academiaDaryoku.model.TbTurma;
import br.com.academiaDaryoku.service.TurmaService;

@Named(value = "turmaControle")
@ViewScoped
public class TurmaControle implements Serializable {

	private static final long serialVersionUID = 7562278849777410982L;
	
	private TbTurma tbTurma;
	private TbTurma turmaSelecionada;
	private List<TbTurma> turmas;
	
	@Inject
	private TurmaService turmaService;
	
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
	
	public List<TbTurma> getTurmas() {
		return turmas;
	}

	public TbTurma getTurmaSelecionada() {
		return turmaSelecionada;
	}
	
	public boolean salvarTurma() {
		PrimeFaces.current().ajax().addCallbackParam("validacaoForm", true);
		return true;
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
	

}
