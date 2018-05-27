package br.com.academiaDaryoku.respository;

import java.io.Serializable;
import java.util.List;

import br.com.academiaDaryoku.model.TbPessoa;
import br.com.academiaDaryoku.model.TbPessoa_;
import br.com.academiaDaryoku.respository.filter.FilterAll;

public class PessoaRepository extends RepositoryImpl<TbPessoa> implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<TbPessoa> buscaPorNome(FilterAll filter) {
		return buscaCriteria(filter, TbPessoa_.nmPessoa);
	}

	public List<TbPessoa> buscarTodos() {
		return super.todos();
	}
}
