package br.com.academiaDaryoku.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.academiaDaryoku.model.TbEndereco;
import br.com.academiaDaryoku.respository.EnderecoRepository;
import br.com.academiaDaryoku.ultils.Transacional;

public class EnderencoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EnderecoRepository enderecoRepository;

	@Transacional
	public TbEndereco salvar(TbEndereco tbEndereco) {
		return enderecoRepository.salva(tbEndereco);
	}

	@Transacional
	public TbEndereco alterar(TbEndereco tbEndereco) {
		return enderecoRepository.alterar(tbEndereco);

	}

	@Transacional
	public void delete(int idPessoa) {
		TbEndereco tbEndereco = enderecoRepository.porIdPessoa(idPessoa);
		if (tbEndereco != null) {
			enderecoRepository.remove(tbEndereco);
		}
	}

	public TbEndereco porIdPessoa(int idPessoa) {
		return enderecoRepository.porIdPessoa(idPessoa);
	}
}
