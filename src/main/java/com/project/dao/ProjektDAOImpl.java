package com.project.dao;
import java.io.Console;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import com.project.datasource.JPAUtil;
import com.project.model.Projekt;
public class ProjektDAOImpl implements ProjektDAO {	
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
public void setProjekt(Projekt projekt) {
	EntityManager entityManager = JPAUtil.getEntityManager();
	entityManager.getTransaction().begin();
	
	Projekt projekt1 = new Projekt();	
	projekt1.setNazwa(projekt.getNazwa());
	projekt1.setOpis(projekt.getOpis());
	projekt1.setDataOddania(projekt.getDataOddania());
	projekt1.setDataCzasUtworzenia(LocalDateTime.now());
	entityManager.persist(projekt1); 
	entityManager.getTransaction().commit();
	entityManager.close();
	
}
@Override
public void deleteProjekt(Integer projektId) {
	EntityManager entityManager = JPAUtil.getEntityManager();	 
	entityManager.getTransaction().begin();
	Projekt projekt = null;
	projekt = entityManager.find(Projekt.class, projektId);
	entityManager.remove(projekt);
	entityManager.getTransaction().commit();
	entityManager.close();
}
@Override
public List<Projekt> getProjekty(Integer offset, Integer limit) {
  Projekt projekt = null;
  EntityManager entityManager = JPAUtil.getEntityManager();
  TypedQuery<Projekt> query = entityManager.createQuery("SELECT p FROM Projekt p ORDER BY p.dataCzasUtworzenia DESC", Projekt.class);
           query.setFirstResult(offset);
           query.setMaxResults(limit);
           List<Projekt> projekty = query.getResultList();
  entityManager.close();
return projekty;
}
@Override
public List<Projekt> searchByNazwa(String search4, Integer offset, Integer limit) {
	Projekt projekt = null;
    EntityManager entityManager = JPAUtil.getEntityManager();
    TypedQuery<Projekt> query = entityManager.createQuery("SELECT p FROM Projekt p ORDER BY p.dataCzasUtworzenia DESC WHERE p.nazwa = " + search4, Projekt.class);
             query.setFirstResult(offset);
             query.setMaxResults(limit);
             List<Projekt> projekty = query.getResultList();
    entityManager.close();
 return projekty;
}
}