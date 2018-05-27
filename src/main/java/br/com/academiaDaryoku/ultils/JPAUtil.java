package br.com.academiaDaryoku.ultils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Eduardo
 *
 */
@ApplicationScoped
public class JPAUtil {

	private EntityManagerFactory factory;
	private static JPAUtil instance;

	public JPAUtil() {
		this.factory = Persistence.createEntityManagerFactory("db_academiadark");
	}

	public synchronized static JPAUtil getInstance() {

		if (instance == null) {
			instance = new JPAUtil();
		}
		return instance;
	}

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}

}