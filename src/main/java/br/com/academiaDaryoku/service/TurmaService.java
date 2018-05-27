package br.com.academiaDaryoku.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.academiaDaryoku.model.TbPessoa;
import br.com.academiaDaryoku.model.TbTurma;
import br.com.academiaDaryoku.respository.TurmaRepository;
import br.com.academiaDaryoku.ultils.Transacional;

public class TurmaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaRepository turmaRepository;

	@Transacional
	public TbTurma alterar(TbTurma tbTurma) {
		return turmaRepository.alterarTurma(tbTurma);
	}

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

	@Transacional
	public void isNullPessoaTurma(TbPessoa pessoaSalvar) {
		TbTurma turma = turmaPorId(pessoaSalvar);
		if ((turma != null)) {
			turma.setTbPessoa(null);
			turmaRepository.isNullPessoaTurma(turma);
		}

	}

	public TbTurma turmaPorId(TbPessoa pessoa) {
		try {
			List<TbTurma> turmas = turmaRepository.turmaPorIdPessoa(pessoa);
			return turmas.get(0);
		} catch (Exception e) {
			return null;
		}

	}

}
