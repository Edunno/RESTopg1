/* Esben Dalgaard; DECK-CS */
package unit;

import entity.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Esben All rights belong to respective contributors.
 */
public class createData {
    public static void main(String[] args) {
        createData d = new createData();
        d.makeHappen();
    }
    public static void makeHappen() {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        List<Person> persons = new ArrayList();
        persons.add(new Person("Jimmy", "Jansen", "34982819"));
        persons.add(new Person("Mike", "Wazowski", "0930883"));
        persons.add(new Person("Kayzer", "Söze", "99999999"));
        persons.add(new Person("Tim", "Toolman", "49305029"));
        persons.add(new Person("Gordon", "Freeman", "909090921"));
        try {
            em.getTransaction().begin();
            for (int i = 0; i < persons.size(); i++) {
                em.persist(persons.get(i));
            }
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }
}
