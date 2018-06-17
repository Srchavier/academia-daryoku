package br.com.academiaDaryoku.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.primefaces.PrimeFaces;

import br.com.academiaDaryoku.converter.ConverterAula;
import br.com.academiaDaryoku.converter.ConverterTurma;
import br.com.academiaDaryoku.filters.SessionContext;
import br.com.academiaDaryoku.model.TbAula;
import br.com.academiaDaryoku.model.TbPessoa;
import br.com.academiaDaryoku.model.TbPresenca;
import br.com.academiaDaryoku.model.TbTurma;
import br.com.academiaDaryoku.model.TbUsuario;
import br.com.academiaDaryoku.model.TipoEnum;
import br.com.academiaDaryoku.service.AulaService;
import br.com.academiaDaryoku.service.PresencaService;
import br.com.academiaDaryoku.service.TurmaService;
import br.com.academiaDaryoku.ultils.UtilErros;
import br.com.academiaDaryoku.ultils.UtilMensagens;

@Named("presencaControle")
@ViewScoped
public class PresencaControle implements Serializable {

	private static final long serialVersionUID = -336018689139447199L;

	private List<TbPresenca> tbpresencaList;
	private TbAula aula;
	private TbTurma turma;
	private TbUsuario usuario;
	private List<TbAula> aulasList;
	private List<TbPessoa> alunos;

	@Inject
	private PresencaService presencaService;

	@Inject
	private TurmaService turmasService;

	@Inject
	private AulaService aulaService;

	@Inject
	private ConverterTurma turmaConverter;

	@Inject
	private ConverterAula aulaConverter;

	@PostConstruct
	public void init() {
		tbpresencaList = new ArrayList<>();
		turma = new TbTurma();
		aula = new TbAula();
		usuario = new TbUsuario();
	}

	public void pesquisar() {
		this.tbpresencaList = new ArrayList<>();
		this.alunos.forEach(aluno -> {
			TbPresenca presenca = null;
			try {
				presenca = presencaService.pesquisarPorAlunoAula(aluno, aula);
			} catch (NoResultException e) {
				presenca = new TbPresenca();
				presenca.setPresenca(Boolean.FALSE);
				presenca.setTbAula(this.aula);
				presenca.setTbPessoa(aluno);
			}
			this.tbpresencaList.add(presenca);
		});

		if (this.aula != null) {
			// tbpresencaList = presencaService.pesquisarPorTurma(this.aula, this.turma);
			if(aula.equals(null)){
				UtilMensagens.mensagemInformacao("Não existe aulas nessa turma!");
			} {
				
			}
		} else {
			UtilMensagens.mensagemInformacao("Não " + aula.getDtAula() + " encontrado");
		}
		PrimeFaces.current().ajax().update(Arrays.asList("form:msgs", "form"));

	}

	public void salvar() {
		if (tbpresencaList.isEmpty()) {
			UtilMensagens.mensagemInformacao("Selecione uma aula!");
			PrimeFaces.current().ajax().update(Arrays.asList("form:msgs", "form"));
		} else {

			try {
				tbpresencaList.forEach(a -> presencaService.salvar(a));
				UtilMensagens.mensagemInformacao("Presença salvo com sucesso!");
				PrimeFaces.current().ajax().update(Arrays.asList("form:msgs", "form"));
			} catch (Exception e) {
				UtilMensagens.mensagemErro("Erro ao salvar!");
				UtilErros.getMensagemErro(e);
			}
		}

	}

	public void turmaSelecionada() {
		try {
			this.aulasList = aulaService.findPorIdTurma(turma.getIdTurma());
			this.alunos = turmasService.buscarAlunosDaTurma(this.turma);
		} catch (Exception e) {
			UtilErros.getMensagemErro(e);
			UtilMensagens.mensagemErro("Erro ao selecionar!");
		}
	}

	public List<TbTurma> listaTurmaPorIdProf() {
		usuario = (TbUsuario) SessionContext.getInstance().getAttribute("usuarioLogado");
		if (usuario.getTbPessoa().getTipo() == TipoEnum.ADM) {
			return turmasService.findAll();
		} else if (usuario.getTbPessoa().getTipo() == TipoEnum.PF) {

			return turmasService.turmaPorPessoa(usuario.getTbPessoa());
		}
		return null;
	}

	public List<TbPresenca> getTbpresencaList() {
		return tbpresencaList;
	}

	public TbAula getAula() {
		return aula;
	}

	public void setAula(TbAula aula) {
		this.aula = aula;
	}

	public TbTurma getTurma() {
		return turma;
	}

	public void setTurma(TbTurma turma) {
		this.turma = turma;
	}

	public TbUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TbUsuario usuario) {
		this.usuario = usuario;
	}

	public List<TbAula> getAulasList() {
		return aulasList;
	}

	public void setAulasList(List<TbAula> aulasList) {
		this.aulasList = aulasList;
	}

	public ConverterTurma getTurmaConverter() {
		return turmaConverter;
	}

	public void setTurmaConverter(ConverterTurma turmaConverter) {
		this.turmaConverter = turmaConverter;
	}

	public ConverterAula getAulaConverter() {
		return aulaConverter;
	}

	public void setAulaConverter(ConverterAula aulaConverter) {
		this.aulaConverter = aulaConverter;
	}

	public void setTbpresencaList(List<TbPresenca> tbpresencaList) {
		this.tbpresencaList = tbpresencaList;
	}

}
