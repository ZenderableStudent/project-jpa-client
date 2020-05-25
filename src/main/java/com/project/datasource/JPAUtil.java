package com.project.datasource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static final String DATA_SOURCE = "sqglManager";
	private static final EntityManagerFactory entityManagerFactory;
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory(DATA_SOURCE);
	}
	
	private JPAUtil() {}
	
	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
	
	public static void close() {
		entityManagerFactory.close();
	}
}
