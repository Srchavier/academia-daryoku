package br.com.academiaDaryoku.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.com.academiaDaryoku.model.TbContato;
import br.com.academiaDaryoku.model.TbUsuario;
import br.com.academiaDaryoku.respository.LoginRepository;
import br.com.academiaDaryoku.ultils.Transacional;
import br.com.academiaDaryoku.ultils.UtilErros;

public class LoginService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LoginRepository loginRepository;

	public TbUsuario login(String login, String senha) {
		try {
			return loginRepository.login(login, senha);
		} catch (Exception e) {
			UtilErros.getMensagemErro(e);
			return null;
		}
	}

	public TbContato porEmail(String email) throws NoResultException {
		return loginRepository.porEmail(email);
	}

	@Transacional
	public void salvar(TbUsuario usuario) {
		loginRepository.salva(usuario);
	}

	@Transacional
	public TbUsuario alterar(TbUsuario tbUsuario) {
		return loginRepository.alterar(tbUsuario);

	}

	public TbUsuario porId(int idPessoa) {
		return loginRepository.porId(idPessoa);
	}

	@Transacional
	public void delete(int idPessoa) {
		TbUsuario tbUsuario = loginRepository.porIdPessoa(idPessoa);
		if (tbUsuario != null) {
			loginRepository.remove(tbUsuario);
		}
	}

	public TbUsuario porIdPessoa(int idPessoa) {
		return loginRepository.porIdPessoa(idPessoa);
	}

	public TbUsuario porMatLogin(String matricula) {
		return loginRepository.porMatricula(matricula);
	}
}
