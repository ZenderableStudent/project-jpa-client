package com.project.dao;
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
 // TODO
}
@Override
public void deleteProjekt(Integer projektId) {
 // TODO
}
@Override
public List<Projekt> getProjekty(Integer offset, Integer limit) {
 // TODO
 return null;
}
@Override
public List<Projekt> searchByNazwa(String search4, Integer offset, Integer limit) {
 // TODO
 return null;
}
}