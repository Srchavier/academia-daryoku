package br.com.academiaDaryoku.respository;

import java.io.Serializable;

import javax.persistence.NoResultException;

import br.com.academiaDaryoku.model.TbContato;
import br.com.academiaDaryoku.model.TbUsuario;

public class LoginRepository extends RepositoryImpl<TbUsuario> implements Serializable {

	private static final long serialVersionUID = 1L;

	public TbUsuario login(String login, String senha) throws NoResultException {
		TbUsuario tbUsuario = super.manager
				.createQuery("from TbUsuario p where p.matLogin= :login and p.matSenha = :senha", TbUsuario.class)
				.setParameter("login", login).setParameter("senha", senha).getSingleResult();
		tbUsuario.setMatSenha("");
		return tbUsuario;
	}

	public TbContato porEmail(String email) throws NoResultException {
		TbContato tbContato = super.manager.createQuery("FROM TbContato WHERE email = :email", TbContato.class)
				.setParameter("email", email).getSingleResult();
		;
		return tbContato;
	}

	public TbUsuario porIdPessoa(int idPessoa) {
		try {
			return (TbUsuario) super.manager
					.createNativeQuery("SELECT us.* FROM tb_usuario as us WHERE tb_pessoa_id_pessoa = :idPessoa",
							TbUsuario.class)
					.setParameter("idPessoa", idPessoa).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public TbUsuario porMatricula(String matricula) {
		try {
			return super.manager.createQuery("FROM TbUsuario WHERE mat_login = :matricula", TbUsuario.class)
					.setParameter("matricula", matricula).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void remove(TbUsuario tbUsuario) {
		super.manager.remove(tbUsuario);
	}

}
