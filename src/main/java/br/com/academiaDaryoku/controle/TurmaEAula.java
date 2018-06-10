package br.com.academiaDaryoku.controle;

import br.com.academiaDaryoku.model.TbAula;
import br.com.academiaDaryoku.model.TbTurma;

public class TurmaEAula {
	
	private TbAula aula;
	private TbTurma turma;
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

}
