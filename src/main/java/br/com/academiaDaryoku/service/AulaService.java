package br.com.academiaDaryoku.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.academiaDaryoku.model.TbAula;
import br.com.academiaDaryoku.respository.AulaRepository;

public class AulaService implements Serializable{


	private static final long serialVersionUID = 8077987276096594224L;
	
	@Inject
	private  AulaRepository aulaRepository;

	public Object find(Class<TbAula> class1, int parseLong) {
		return aulaRepository.porId(parseLong);
	}

	public List<TbAula> findPorIdTurma(int idTurma) {
		String jpql = "FROM TbAula WHERE id_turma = ?";
		return aulaRepository.find(jpql, idTurma);
	}

	
}
