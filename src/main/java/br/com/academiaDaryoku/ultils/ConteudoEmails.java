package br.com.academiaDaryoku.ultils;

public class ConteudoEmails {

	public static String EMAIL_RECUPERARSENHA(String senhaTemporaria) {
		String emailRecuperarSenha = String.format(
				"<div	style=\"font-style: normal; font-weight: normal;\">" + "<br>" + "</div>"
						+ "<div style=\"font-style: normal; font-weight: normal;\">Vejo que voc� esqueceu sua senha, mas infelizmente, n�o podemos recuper�-la para voc�!</div>"
						+ "<div style=\"font-style: normal; font-weight: normal;\">" + "<br>" + "</div>"
						+ "<div style=\"font-style: normal; font-weight: normal;\">Mas n�o se assuste, geramos uma nova senha tempor�ria para voc�:</div>"
						+ "<div style=\"font-style: normal; font-weight: normal;\">" + "<br>" + "</div>" + "<div>"
						+ "<span style=\"font-style: italic;\"><span style=\"font-weight: bold;\">Nova senha: %s</span>.</span>"
						+ "</div>" + "<div style=\"font-style: normal; font-weight: normal;\">" + "<br>" + "</div>"
						+ "<div style=\"font-style: normal;\">"
						+ "<span style=\"font-weight: bold;\">Tenha mais cuidado agora para n�o esquecer sua senha.</span>"
						+ "</div>" + "<div style=\"font-style: normal; font-weight: normal;\">" + "<br>" + "</div>"
						+ "<div style=\"font-style: normal; font-weight: normal;\">Obrigado.</div>", senhaTemporaria);

		return emailRecuperarSenha;
	}

}
