package br.com.academiaDaryoku.respository;

import java.util.List;

import br.com.academiaDaryoku.model.TbCidade;

public class CidadeRepository extends RepositoryImpl<TbCidade> {

	public List<TbCidade> findPorIdEstado(int id) {
		try {
			return super.manager.createQuery("FROM TbCidade WHERE tb_estado_id_estado = :id", TbCidade.class)
					.setParameter("id", id).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

}
