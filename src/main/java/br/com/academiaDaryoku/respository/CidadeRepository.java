package br.com.academiaDaryoku.respository;

import java.io.Serializable;
import java.util.List;

import br.com.academiaDaryoku.model.TbCidade;

public class CidadeRepository extends RepositoryImpl<TbCidade> implements Serializable {


	private static final long serialVersionUID = -2440755556804045088L;

	public List<TbCidade> findPorIdEstado(int id) {
		try {
			return super.manager.createQuery("FROM TbCidade WHERE tb_estado_id_estado = :id", TbCidade.class)
					.setParameter("id", id).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

}
