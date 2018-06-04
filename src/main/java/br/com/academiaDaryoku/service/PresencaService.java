package br.com.academiaDaryoku.service;

import java.io.Serializable;

import javax.inject.Inject;

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
	
}
