package br.com.academiaDaryoku.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.academiaDaryoku.model.TbPessoa;
import br.com.academiaDaryoku.respository.PessoaRepository;
import br.com.academiaDaryoku.respository.filter.FilterAll;
import br.com.academiaDaryoku.ultils.Transacional;
import br.com.academiaDaryoku.ultils.UtilErros;

public class PessoaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaRepository pessoaRepository;

	public List<TbPessoa> buscatodos() {
		return pessoaRepository.buscarTodos();
	}

	@Transacional
	public void delete(TbPessoa pessoa) {
		if (pessoa.getDtNascimento() != null) {
			try {
				TbPessoa tbPessoa2 = pessoaRepository.porId(pessoa.getIdPessoa());
				pessoaRepository.remover(tbPessoa2);
			} catch (Exception e) {
				UtilErros.getMensagemErro(e);
			}
		}
	}

	@Transacional
	public TbPessoa salvar(TbPessoa tbPessoa) {
		return pessoaRepository.salva(tbPessoa);
	}

	@Transacional
	public TbPessoa alterar(TbPessoa tbPessoa) {
		return pessoaRepository.alterar(tbPessoa);
	}

	public TbPessoa findOne(TbPessoa tbPessoaSelecionada) {
		return pessoaRepository.porId(tbPessoaSelecionada.getIdPessoa());
	}

	public TbPessoa porId(int id) {
		return pessoaRepository.porId(id);
	}

	public List<TbPessoa> buscaPorNome(FilterAll filter) {
		return pessoaRepository.buscaPorNome(filter);
	}

}
