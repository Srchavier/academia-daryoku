package br.com.academiaDaryoku.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.academiaDaryoku.respository.DiaSemanaRepository;

public class DiasSemanaService implements Serializable{

	private static final long serialVersionUID = -1589162824982400518L;
	
	
	@Inject
	private DiaSemanaRepository diaSemanaRepository;

}
