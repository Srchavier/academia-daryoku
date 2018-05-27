package br.com.academiaDaryoku.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.academiaDaryoku.model.TbCidade;
import br.com.academiaDaryoku.respository.CidadeRepository;

public class CidadeService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CidadeRepository cidadeRepository;

	public TbCidade findOne(int idCidade) {
		return cidadeRepository.porId(idCidade);
	}

	public List<TbCidade> findPorIdEstado(int id) {
		return cidadeRepository.findPorIdEstado(id);
	}

}
