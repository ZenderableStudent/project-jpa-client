package com.project.test; 
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import com.project.model.Projekt;
import com.project.model.Zadanie;
import com.project.model.Student; 
 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjektTest {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager; 
 
    @BeforeAll
    public static void init() throws FileNotFoundException, SQLException {
    	entityManagerFactory = Persistence.createEntityManagerFactory("testHsqlManager");
    	entityManager = entityManagerFactory.createEntityManager();
    } 
 
    @AfterAll
    public static void afterAll() {
    	entityManager.close();
    	entityManagerFactory.close();
    	
    }
 
    // --- URUCHAMIANIE TESTÓW ---
    // ABY URUCHOMIĆ TESTY KLIKNIJ NA NAZWIE KLASY PRAWYM PRZYCISKIEM
    // MYSZY I WYBIERZ Z MENU 'Run As' -> 'Gradle Test' LUB PO USTAWIENIU
    // KURSORA NA NAZWIE KLASY WCIŚNIJ SKRÓT 'CTRL+ALT+X' A PÓŹNIEJ 'G'
    // MOŻNA RÓWNIEŻ ANALOGICZNIE URUCHAMIAĆ POJEDYNCZE METODY KLIKAJĄC
    // WCZEŚNIEJ NA ICH NAZWĘ 
    // PO ZAKOŃCZENIU TESTÓW W PODKATALOGU PROJEKTU DOSTĘPNY JEST SZCZEGÓŁOWY RAPORT
    //    project-jpa-client\build\reports\tests\test\index.html,
    // JEGO ADRES DRUKOWANY JEST W KONSOLI, GDY JAKIŚ TEST ZAKOŃCZY SIĘ BŁĘDEM NP.
    //    file:///C:/eclipse-2018-09/workspace/project-jfx-client/build/reports/tests/test/index.html 
 
   @Test
   @Order(1)
   public void dodawanieProjektuZZadaniami() {
	  Projekt projekt = new Projekt("Aplikacji webowa", "Aplikacja w Javie", LocalDate.of(2020, 6, 19));
	  
      Zadanie zadanie1 = new Zadanie("Instalacja kontenera serwletów",1, "Instalacja serwera Tomcat 9.0.33");
      Zadanie zadanie2 = new Zadanie("Implementacja aplikacji",2, "Zgodna z wzorcem MVC");
      //przypisujemy do zadań projekt       
      zadanie1.setProjekt(projekt);       
      zadanie2.setProjekt(projekt); 
 
      entityManager.getTransaction().begin();
      entityManager.persist(zadanie1);      //utrwalanie zawsze dla wszystkich obiektów - projektu i jego zadań
      entityManager.persist(zadanie2);
      entityManager.persist(projekt);
      entityManager.getTransaction().commit(); 
 
      entityManager.refresh(projekt); 	
      //odświeżenie stanu zarządzanej encji          
      									
      //na podstawie informacji z bazy danych 
      //sprawdzamy czy w bazie danych do projektu zostały przypisane zadania       
      List<Zadanie> zadania = projekt.getZadania(); 
 
      assertNotNull(zadania); 
 
      assertEquals(2, zadania.size());
      System.out.printf("Projekt - Id: %d, Nazwa: %s%n", projekt.getProjektId(), projekt.getNazwa());
      for (Zadanie zad : zadania) {
    	  System.out.printf(" Zadanie - Id: %d, Nazwa: %s%n", zad.getZadanieId(), zad.getNazwa());           } 
      }
 
   @Test
   @Order(2)
   public void usuwanieProjektuZZadaniami() {
	  entityManager.getTransaction().begin();
	  Projekt projekt;
	  projekt = entityManager.find(Projekt.class, 1);
	  List<Zadanie> zadania = projekt.getZadania();
	  assertNotNull(zadania);
	  assertEquals(2, zadania.size());
	  for(Zadanie zad : zadania) {
		  entityManager.remove(zadania);
	  }
	  entityManager.remove(projekt);
	  entityManager.getTransaction().commit();
	  entityManager.close();
   }
 

   @Test
   @Order(3)
   public void dodawanieProjektuZeStudentamiIZadaniami() {
       Projekt projekt = new Projekt("Aplikacji webowa", "Aplikacja w Javie", LocalDate.of(2020, 6, 19));
         
          Zadanie zadanie1 = new Zadanie("Instalacja kontenera serwletów",1, "Instalacja serwera Tomcat 9.0.33");
          Zadanie zadanie2 = new Zadanie("Implementacja aplikacji",2, "Zgodna z wzorcem MVC");
          Student student1 = new Student("Andrzej","Petarda","66666","email@email.com",true);
          Student student2 = new Student("Mike","Smith","67890","mike@smith.com",true);
          //przypisujemy do zadań projekt      
          zadanie1.setProjekt(projekt);      
          zadanie2.setProjekt(projekt);
          Set<Projekt> projekty1 = student1.getProjekty();
          projekty1.add(projekt);
          Set<Projekt> projekty2 = student2.getProjekty();
          projekty2.add(projekt);
          student1.setProjekty(projekty1);
          student2.setProjekty(projekty2);
         
          entityManager.getTransaction().begin();
          entityManager.persist(zadanie1);      
          entityManager.persist(zadanie2);
          entityManager.persist(student1);
          entityManager.persist(student2);
         
          entityManager.persist(projekt);
          entityManager.getTransaction().commit();
     
          entityManager.refresh(projekt);  
                                               
   
          List<Zadanie> zadania = projekt.getZadania();
          Set<Student> studenci = projekt.getStudenci();
         
     
          assertNotNull(zadania);
          assertNotNull(studenci);
     
          assertEquals(2, studenci.size());
          assertEquals(2, zadania.size());
          System.out.printf("Projekt - Id: %d, Nazwa: %s%n", projekt.getProjektId(), projekt.getNazwa());
          for (Zadanie zad : zadania) {
              System.out.printf(" Zadanie - Id: %d, Nazwa: %s%n", zad.getZadanieId(), zad.getNazwa());           }
          for (Student stu : studenci) {
               System.out.printf("Student - Id: %d, Imie:%s%n, Nazwisko:%s%n ", stu.getStudent_id(),stu.getImie(),stu.getNazwisko()); }
          }
 
   @Test
   @Order(4)
   public void wyszukiwanieProjektuZeStudentamiIZadaniemTomcat() {
       entityManager.getTransaction().begin();
       List<Object[]> result = entityManager.createQuery("SELECT p.nazwa, count(z.zadanieId) FROM Projekt p"
       +"LEFT OUTER JOIN p.zadania z"
       +"WHERE p.opis LIKE '%Tomcat%'"
       +"GROUP BY p.nazwa HAVING count(z.zadanieID) >3",Object[].class).getResultList();
      
      for(Object[] object : result) {
          String nazwa = (String) object[0];
          Long liczbaZadan = (Long) object[1];
          System.out.printf("Nazwa:%s%n, Liczba projektow:%d", nazwa,liczbaZadan);
      }
            
	   }        
	   @BeforeEach    public void before(TestInfo testInfo) {       
		   System.out.printf("-- METODA -> %s%n", testInfo.getTestMethod().get().getName());    
		   } 
   
 
   @AfterEach
   public void after(TestInfo testInfo) {
	   System.out.printf("<- KONIEC -- %s%n", testInfo.getTestMethod().get().getName());    } 
   }
