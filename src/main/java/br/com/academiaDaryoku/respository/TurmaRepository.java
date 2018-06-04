package br.com.academiaDaryoku.respository;

import java.io.Serializable;
import java.util.List;

import br.com.academiaDaryoku.model.TbPessoa;
import br.com.academiaDaryoku.model.TbTurma;

public class TurmaRepository extends RepositoryImpl<TbTurma> implements Serializable {

	private static final long serialVersionUID = 1L;

	public TbTurma alterarTurma(TbTurma tbTurma) {
		return super.alterar(tbTurma);
	}

	public TbTurma findTurma(String tbString) {
		return super.manager.createQuery("FROM TbTurma WHERE nome = :turma", TbTurma.class)
				.setParameter("turma", tbString.toUpperCase()).getSingleResult();
	}

	public boolean isProfessor(TbPessoa pessoa) {
		try {
			@SuppressWarnings("unchecked")
			List<TbTurma> turmas = super.manager.createNativeQuery(
					"SELECT t.* FROM tb_pessoa  as p INNER JOIN tb_turma as t  ON p.id_pessoa = tb_pessoa_id_pessoa"
							+ " WHERE t.id_turma = :turma",
					TbTurma.class).setParameter("turma", pessoa.getTbTurma().getIdTurma()).getResultList();

			if (!turmas.isEmpty()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void isNullPessoaTurma(TbTurma turma) {
		super.alterar(turma);
	}

	public List<TbTurma> turmaPorIdPessoa(TbPessoa pessoa) {
		@SuppressWarnings("unchecked")
		List<TbTurma> turmas = super.manager
				.createNativeQuery(
						"SELECT t.* FROM tb_pessoa  as p INNER JOIN tb_turma as t  ON p.id_pessoa = tb_pessoa_id_pessoa"
								+ " WHERE p.id_pessoa = :turma",
						TbTurma.class)
				.setParameter("turma", pessoa.getIdPessoa()).getResultList();
		return turmas;
	}
	
	public void salvarTurma(TbTurma tbTurma) {
		 super.manager.persist(tbTurma);
	}

}
