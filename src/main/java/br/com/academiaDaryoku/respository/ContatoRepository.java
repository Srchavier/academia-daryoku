package br.com.academiaDaryoku.respository;

import java.io.Serializable;

import javax.persistence.NoResultException;

import br.com.academiaDaryoku.model.TbContato;

public class ContatoRepository extends RepositoryImpl<TbContato> implements Serializable {

	private static final long serialVersionUID = 1L;

	public TbContato porIdPessoa(int idPessoa) {
		try {
			return super.manager.createQuery("FROM TbContato WHERE tb_pessoa_id_pessoa = :idPessoa", TbContato.class)
					.setParameter("idPessoa", idPessoa).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public void remove(TbContato tbContato) {
		super.manager.remove(tbContato);
	}

}
