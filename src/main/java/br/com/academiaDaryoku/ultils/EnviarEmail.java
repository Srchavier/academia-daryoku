package br.com.academiaDaryoku.ultils;

import java.io.Serializable;

import javax.mail.internet.AddressException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EnviarEmail implements Serializable {

	private static final long serialVersionUID = 1L;

	private static EnviarEmail enviarEmail;
	private static final String HOSTNAME = "smtp.gmail.com";
	private static final String USERNAME = "eemailtest11@gmail.com";
	private static final String PASSWORD = "20546004";
	private static final String EMAILORIGEM = "eemailtest11@gmail.com";

	public static EnviarEmail getEnviarEmail() {
		if (enviarEmail == null) {
			enviarEmail = new EnviarEmail();
		}
		return enviarEmail;
	}

	public void enviar(String emailPessoa, String senha) throws AddressException {
		try {
			HtmlEmail email = new HtmlEmail();
			email = conectaEmail();
			email.addTo(emailPessoa);
			email.setSubject("Recupera��o de senha Escola Doryoku");
			email.setHtmlMsg(recuperaSenha(senha));
			email.setTextMsg("Seu cliente de e-mail n�o suporta mensagens HTML!");
			email.send();

		} catch (EmailException e) {
			throw new RuntimeException(e);
		}
	}

	protected HtmlEmail conectaEmail() throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(HOSTNAME);
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setSSLOnConnect(true);
		email.setCharset("UTF-8");
		email.setFrom(EMAILORIGEM);
		return email;
	}

	protected static String recuperaSenha(String senha) {
		String emailRecuperarSenha = String.format("Ol� tudo bem?"
				+ "<div	style=\"font-style: normal; font-weight: normal;\">" + "<br>" + "</div>"
				+ "<div style=\"font-style: normal; font-weight: normal;\">Vejo que voc� esqueceu sua senha, mas infelizmente, n�o podemos recuper�-la para voc�!</div>"
				+ "<div style=\"font-style: normal; font-weight: normal;\">" + "<br>" + "</div>"
				+ "<div style=\"font-style: normal; font-weight: normal;\">Mas n�o se assuste, geramos uma nova senha tempor�ria para voc�:</div>"
				+ "<div style=\"font-style: normal; font-weight: normal;\">" + "<br>" + "</div>" + "<div>"
				+ "<span style=\"font-style: italic;\"><span style=\"font-weight: bold;\">Nova senha: %s</span>.</span>"
				+ "</div>" + "<div style=\"font-style: normal; font-weight: normal;\">" + "<br>" + "</div>"
				+ "<div style=\"font-style: normal;\">"
				+ "<span style=\"font-weight: bold;\">Tenha mais cuidado agora para n�o esquecer sua senha.</span>"
				+ "</div>" + "<div style=\"font-style: normal; font-weight: normal;\">" + "<br>" + "</div>"
				+ "<div style=\"font-style: normal; font-weight: normal;\">Obrigado!  </div>"
				+ "<div style=\"font-style: normal; font-weight: normal;\"> - Escola Doryoku - Karat�-D� Shotokan.</div>"
				+ "<div style=\"font-style: normal; font-weight: normal;\">At� mais!</div>", senha);

		return emailRecuperarSenha;
	}
}
