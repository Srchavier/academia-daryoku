package br.com.academiaDaryoku.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.academiaDaryoku.model.TbCidade;
import br.com.academiaDaryoku.model.TbEstado;
import br.com.academiaDaryoku.respository.EstadoRepository;

public class EstadoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoRepository estadoRepository;

	public List<TbEstado> lista() {
		return estadoRepository.todos();
	}

	public TbEstado findId(int parseInt) {
		return estadoRepository.porId(parseInt);
	}

	public List<TbCidade> findAllidEstado(int idEstado) {
		// TODO Auto-generated method stub
		return null;
	}

}
