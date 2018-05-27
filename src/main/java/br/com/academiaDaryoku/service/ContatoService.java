package br.com.academiaDaryoku.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.academiaDaryoku.model.TbContato;
import br.com.academiaDaryoku.respository.ContatoRepository;
import br.com.academiaDaryoku.ultils.Transacional;

public class ContatoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContatoRepository contatoRepository;

	@Transacional
	public TbContato salvar(TbContato tbContato) {
		return contatoRepository.salva(tbContato);

	}

	@Transacional
	public TbContato alterar(TbContato tbContato) {
		return contatoRepository.alterar(tbContato);
	}

	@Transacional
	public void delete(int idPessoa) {
		TbContato tbContato = contatoRepository.porIdPessoa(idPessoa);
		if (tbContato != null) {
			contatoRepository.remove(tbContato);
		}
	}

	public TbContato porIdPessoa(int idPessoa) {
		return contatoRepository.porIdPessoa(idPessoa);
	}

}
