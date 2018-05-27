package br.com.academiaDaryoku.ultils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 {


	public static String shaSet(String senha) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

			return new String(messageDigest);

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			UtilErros.getMensagemErro(e);
			return null;
		}

	}

}
