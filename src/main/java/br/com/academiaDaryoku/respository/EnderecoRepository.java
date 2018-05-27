package br.com.academiaDaryoku.respository;

import java.io.Serializable;

import javax.persistence.NoResultException;

import br.com.academiaDaryoku.model.TbEndereco;

public class EnderecoRepository extends RepositoryImpl<TbEndereco> implements Serializable {

	private static final long serialVersionUID = 1L;

	public TbEndereco porIdPessoa(int idPessoa) {
		try {
			return super.manager.createQuery("FROM TbEndereco WHERE tb_pessoa_id_pessoa = :idPessoa", TbEndereco.class)
					.setParameter("idPessoa", idPessoa).getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	public void remove(TbEndereco tbEndereco) {
		super.manager.remove(tbEndereco);
	}

}
