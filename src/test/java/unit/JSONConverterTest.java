package unit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Person;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import utility.JSONConverter;

/**
 *
 * @author Esben
 */
public class JSONConverterTest {
    
    public JSONConverterTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetPersonFromJson() {
        Person pT = new Person();
        pT.setFirstName("Rick");
        pT.setLastName("James");
        pT.setPhone("123456");
        JSONConverter jc = new JSONConverter();
        String tS = jc.getJsonFromPerson(pT);
        Person pT2 = jc.getPersonFromJson(tS);
        System.out.println(tS);
        assertEquals(pT.getFirstName(),pT2.getFirstName());
    }
    
}
