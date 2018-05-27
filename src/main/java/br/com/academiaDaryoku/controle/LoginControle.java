package br.com.academiaDaryoku.controle;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;

import br.com.academiaDaryoku.filters.SessionContext;
import br.com.academiaDaryoku.model.TbContato;
import br.com.academiaDaryoku.model.TbUsuario;
import br.com.academiaDaryoku.service.ContatoService;
import br.com.academiaDaryoku.service.LoginService;
import br.com.academiaDaryoku.service.PessoaService;
import br.com.academiaDaryoku.ultils.EnviarEmail;
import br.com.academiaDaryoku.ultils.Sha256;
import br.com.academiaDaryoku.ultils.UtilMensagens;

@Named(value = "controleLogin")
@SessionScoped
public class LoginControle implements Serializable {

	private static final long serialVersionUID = 1L;

	private TbUsuario usuarioEntity;
	private TbContato tbContato;

	@Inject
	private LoginService loginService;
	@Inject
	private PessoaService pessoaService;
	@Inject
	private ContatoService contatoService;
	@Inject
	private EnviarEmail email;

	private String login;
	private String senha;
	private String matricula;

	@PostConstruct
	public void inicia() {
		tbContato = new TbContato();
		usuarioEntity = new TbUsuario();
	}

	public String efetuarLogin() {
		return login();
	}

	public String efetuarLogout() {
		SessionContext.getInstance().encerrarSessao();
		PrimeFaces.current()
				.executeScript("window.location.href = \'/academia/login/login.xhtml?faces-redirect=true\';");
		return "/academia/login/login.xhtml?faces-redirect=true";
	}

	public String esqueceuSenha() {
		if (StringUtils.isBlank(matricula)) {
			UtilMensagens.mensagemInformacao("Favor informe a matricula!");
			return null;
		}
		try {
			usuarioEntity = loginService.porMatLogin(matricula);
			TbContato bContato = contatoService.porIdPessoa(usuarioEntity.getTbPessoa().getIdPessoa());
			try {
				senha = gerarSenhaTemporaria();
				email.enviar(bContato.getEmail(), senha);
				usuarioEntity.setMatSenha(Sha256.shaSet(senha));
				loginService.salvar(usuarioEntity);
				usuarioEntity.setMatSenha("");
				UtilMensagens.mensagemInformacao("Nova senha enviada para seu e-mail!");
				return "";
			} catch (MessagingException e) {
				UtilMensagens.mensagemInformacao(
						"Desculpa, não podemos processa sua informação, tente novamente em alguns instantes.");
				e.printStackTrace();
				return "";
			}
		} catch (NoResultException e) {
			UtilMensagens.mensagemInformacao("Matrícula não cadastrado!");
			return "";
		}
	}

	private String login() {
		boolean invalid = false;
		try {
			if (StringUtils.isBlank(login)) {
				UtilMensagens.mensagemInformacao("Favor informar o login!");
				invalid = true;
			}
			if (StringUtils.isBlank(senha)) {

				UtilMensagens.mensagemInformacao("Favor informar a senha!");
				invalid = true;
			}
			if (invalid) {
				return null;
			}
			senha = Sha256.shaSet(senha);
			usuarioEntity = loginService.login(login, senha);
			tbContato = contatoService.porIdPessoa(usuarioEntity.getTbPessoa().getIdPessoa());
			if (usuarioEntity != null) {
				SessionContext.getInstance().setAttribute("usuarioLogado", usuarioEntity);
				UtilMensagens.mensagemInformacao("Login efetuado com sucesso!");
				return "/sistema/Perfil.xhtml?faces-redirect=true";
			} else {
				UtilMensagens.mensagemErro("Login e/ou senha incorreto(s)!");
				return "/login";
			}
		} catch (Exception e) {
			UtilMensagens.mensagemErro("Login e/ou senha incorreto(s)!");
			return "/login";

		}
	}

	public String gerarSenhaTemporaria() {
		char[] carct = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
				'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
				'Z' };

		StringBuilder senha = new StringBuilder();

		for (int x = 0; x < 12; x++) {
			int j = (int) (Math.random() * carct.length);
			senha.append(carct[j]);
		}

		return senha.toString();
	}

	public TbUsuario getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(TbUsuario usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public PessoaService getPessoaService() {
		return pessoaService;
	}

	public void setPessoaService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public TbContato getTbContato() {
		return tbContato;
	}

	public void setTbContato(TbContato tbContato) {
		this.tbContato = tbContato;
	}

}
