package br.com.academiaDaryoku.service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import br.com.academiaDaryoku.model.DiasDaSemana;
import br.com.academiaDaryoku.model.TbAula;
import br.com.academiaDaryoku.model.TbDiassemana;
import br.com.academiaDaryoku.model.TbPessoa;
import br.com.academiaDaryoku.model.TbTurma;
import br.com.academiaDaryoku.respository.DiaSemanaRepository;
import br.com.academiaDaryoku.respository.TurmaRepository;
import br.com.academiaDaryoku.ultils.Transacional;

public class TurmaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaRepository turmaRepository;

	@Inject
	private DiaSemanaRepository diaRepository;


	public Object find(Class<TbTurma> class1, int parseLong) {
		return turmaRepository.porId(parseLong);
	}

	public List<TbTurma> listarTodos() {
		return turmaRepository.todos();
	}

	public List<TbTurma> findAll() {
		return turmaRepository.todos();
	}

	public TbTurma findTurma(String tbString) {
		return turmaRepository.findTurma(tbString);
	}

	public boolean isProfessor(TbPessoa pessoa) {
		return turmaRepository.isProfessor(pessoa);
	}

	public TbTurma turmaPorId(TbPessoa pessoa) {
		try {
			List<TbTurma> turmas = turmaRepository.turmaPorIdPessoa(pessoa);
			return turmas.get(0);
		} catch (Exception e) {
			return null;
		}

	}

	public List<TbDiassemana> listDias() {
		return diaRepository.todos();
	}

	@Transacional
	public TbTurma alterar(TbTurma tbTurma) {
		return turmaRepository.alterarTurma(tbTurma);
	}

	@Transacional
	public void isNullPessoaTurma(TbPessoa pessoaSalvar) {
		TbTurma turma = turmaPorId(pessoaSalvar);
		if ((turma != null)) {
			turma.setTbPessoa(null);
			turmaRepository.isNullPessoaTurma(turma);
		}

	}

	@Transacional
	public void salvar(TbTurma tbTurma, LocalTime hrInicio, LocalTime hrFim) {
		if (gerarTurmas(tbTurma, hrInicio, hrFim)) {
			turmaRepository.salva(tbTurma);
		}

	}

	private boolean gerarTurmas(TbTurma tbTurma, LocalTime hrInicio, LocalTime hrFim) {
		LocalDate dataInicio = tbTurma.getDtInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dataFim = tbTurma.getDtFim().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		DiasDaSemana diasSemana = tbTurma.getTbDiassemana().getDiasDaSemana();

		List<Method> listaDias = Arrays.asList(diasSemana.getClass().getDeclaredMethods()).stream()
				.filter(f -> f.getName().startsWith("is")).collect(Collectors.toList());

		List<DayOfWeek> diasDaSemanaSelecionados = new ArrayList<>();

		listaDias.forEach(m -> {
			Boolean bDiaDaSemana = Boolean.FALSE;
			try {
				bDiaDaSemana = (Boolean) m.invoke(diasSemana);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			if (bDiaDaSemana) {
				String nomeDoMetodo = m.getName().substring(2).toUpperCase();
				DayOfWeek dayOfWeek = getDayOfWeek(nomeDoMetodo);
				diasDaSemanaSelecionados.add(dayOfWeek);
			}
		});

		List<LocalDate> datasAuxiliares = new ArrayList<>();
		diasDaSemanaSelecionados.forEach(dw -> {
			LocalDate dataAux = dataInicio;
			while (dataAux.getDayOfWeek().compareTo(dw) != 0) {
				dataAux = dataAux.plusDays(1);
			}
			datasAuxiliares.add(dataAux);
		});

		datasAuxiliares.forEach(data -> {
			LocalDate dataAux = data;
			while (dataAux.isBefore(dataFim)) {
				TbAula aula = new TbAula(dataAux, hrInicio, hrFim);
				tbTurma.adicionarAula(aula);
				dataAux = dataAux.plusWeeks(1);
			}
		});
		return true;
	}

	private DayOfWeek getDayOfWeek(String nomeDoMetodo) {
		DayOfWeek dayOfWeek = null;
		switch (nomeDoMetodo) {
		case "DOM":
			dayOfWeek = DayOfWeek.SUNDAY;
			break;
		case "SEG":
			dayOfWeek = DayOfWeek.MONDAY;
			break;
		case "TER":
			dayOfWeek = DayOfWeek.TUESDAY;
			break;
		case "QUA":
			dayOfWeek = DayOfWeek.WEDNESDAY;
			break;
		case "QUI":
			dayOfWeek = DayOfWeek.THURSDAY;
			break;
		case "SEX":
			dayOfWeek = DayOfWeek.FRIDAY;
			break;
		case "SAB":
			dayOfWeek = DayOfWeek.SATURDAY;
			break;
		}

		return dayOfWeek;
	}

}
