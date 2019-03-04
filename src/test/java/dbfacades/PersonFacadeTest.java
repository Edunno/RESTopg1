/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacades;

import entity.Person;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import unit.createData;

/**
 *
 * @author Esben
 */
public class PersonFacadeTest {

    public PersonFacadeTest() {
    }

    @BeforeClass
    public static void setUp() {
        createData.makeHappen();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddEntityManagerFactory() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        System.out.println("addEntityManagerFactory");
        PersonFacade instance = new PersonFacade();
        instance.addEntityManagerFactory(emf);
    }

    @Test
    public void testAddPerson() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        PersonFacade pf = new PersonFacade();
        pf.addEntityManagerFactory(emf);
        Person p = new Person("Test", "Testerson", "12345678");
        Person t = pf.addPerson(p);
        Assert.assertEquals("Test", t.getFirstName());
    }

    @Test
    public void testDeletePerson() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        PersonFacade pf = new PersonFacade();
        pf.addEntityManagerFactory(emf);
        Person testP = pf.getAllPersons().get(0);
        Person removed = pf.deletePerson(testP.getId());
        assertEquals(testP.getFirstName(), removed.getFirstName());
    }

    @Test
    public void testGetPerson() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        PersonFacade pf = new PersonFacade();
        pf.addEntityManagerFactory(emf);
        Person pTest = pf.getPerson(6);
        assertEquals("Test", pTest.getFirstName());
    }

    @Test
    public void testGetAllPersons() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        PersonFacade pf = new PersonFacade();
        pf.addEntityManagerFactory(emf);
        int i = pf.getAllPersons().size();
        assertTrue(i > 0);
    }

    @Test
    public void testEditPerson() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        PersonFacade pf = new PersonFacade();
        pf.addEntityManagerFactory(emf);
        Person pg = pf.getPerson(6);
        pg.setLastName("Johnson");
        pf.editPerson(pg);
        assertEquals(pg.getLastName(),pf.getPerson(6).getLastName());
    }
}
