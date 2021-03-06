package br.com.academiaDaryoku.filters;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {


	@Override
	public void sessionCreated(HttpSessionEvent event) {        
		System.out.println("Sess�o criada " + event.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {     
		String ultimoAcesso = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date(event.getSession().getLastAccessedTime()));
		System.out.println("Sess�o expirada "+event.getSession().getId()+". Ultimo Acesso = "+ultimoAcesso);
	}

}