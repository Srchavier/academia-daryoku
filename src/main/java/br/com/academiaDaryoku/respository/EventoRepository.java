package br.com.academiaDaryoku.respository;

import java.io.Serializable;
import java.util.List;

import br.com.academiaDaryoku.filters.SessionContext;
import br.com.academiaDaryoku.model.TbEvento;
import br.com.academiaDaryoku.model.TbPessoa;
import br.com.academiaDaryoku.model.TbUsuario;
import br.com.academiaDaryoku.ultils.UtilErros;

public class EventoRepository extends RepositoryImpl<TbEvento> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int LIMIT = 5;

	public List<TbEvento> buscaTodos() {
		return super.todos();
	}


	@SuppressWarnings("unchecked")
	public List<TbEvento> todosMostrarLimit10(int id) {
		try {
			return super.manager
					.createNativeQuery("SELECT e.* FROM tb_evento AS e INNER JOIN tb_evento_has_tb_turma AS te "
							+ "ON e.id_Evento=te.tb_evento_id_Evento "
							+ "INNER JOIN tb_turma AS t ON te.tb_turma_id_turma=t.id_turma "
							+ "WHERE t.id_turma = :idTurma_Evento ORDER BY e.dateInicil DESC", TbEvento.class)
					.setParameter("idTurma_Evento", id)
					.setMaxResults(LIMIT).getResultList();

		} catch (Exception e) {
			UtilErros.getMensagemErro(e);
			return null;
		}

	}

}
