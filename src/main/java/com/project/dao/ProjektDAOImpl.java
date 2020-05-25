package com.project.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.project.datasource.JPAUtil;
import com.project.model.Projekt; 

public class ProjektDAOImpl implements ProjektDAO {

	Projekt projekt;
	@Override
	public void setProjekt(Projekt projekt){
		
	}
	@Override
	public List<Projekt> getProjekty(Integer offset, Integer limit){
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		TypedQuery<Projekt> query = entityManager.createQuery("SELECT p FROM Projekt p WHERE p.projektId > 1", Projekt.class);
		List<Projekt> projekty = query.getResultList();
		return projekty;
	}
	
	@Override
	public Projekt getProjekt(Integer projektId) {
		Projekt projekt = null;
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			projekt = entityManager.find(Projekt.class, projektId);
		} finally {
			entityManager.close();
		}
		return projekt;
	}
	@Override
	public void deleteProjekt(Integer projektId) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(projekt.getProjektId());
		entityManager.getTransaction().commit();
	}
	@Override
	public List<Projekt> searchByNazwa(String search4, Integer offset, Integer limit) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		TypedQuery<Projekt> query = entityManager.createQuery("SELECT p FROM Projekt p WHERE p.projektId > 1", Projekt.class);
		List<Projekt> projekty = query.getResultList();
		return projekty;
	} 

}
