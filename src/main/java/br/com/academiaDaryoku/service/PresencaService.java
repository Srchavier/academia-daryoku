package br.com.academiaDaryoku.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.academiaDaryoku.model.TbAula;
import br.com.academiaDaryoku.model.TbPessoa;
import br.com.academiaDaryoku.model.TbPresenca;
import br.com.academiaDaryoku.respository.PresencaRepository;
import br.com.academiaDaryoku.ultils.Transacional;

public class PresencaService implements Serializable {

	private static final long serialVersionUID = -2002513286820892120L;
	
	@Inject
	PresencaRepository presencaRepository;
	
	@Transacional
	public TbPresenca salvar(TbPresenca tbPresenca) {
		return presencaRepository.salva(tbPresenca);
	}

	public TbPresenca pesquisarPorAlunoAula(TbPessoa aluno, TbAula aula) {
		return this.presencaRepository.findOne("FROM TbPresenca p WHERE p.tbPessoa = ? AND p.tbAula = ?", aluno, aula);
	}
	
}
