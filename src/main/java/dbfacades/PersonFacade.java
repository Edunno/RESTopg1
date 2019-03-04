/* Esben Dalgaard; DECK-CS */
package dbfacades;

import entity.IPersonfacade;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * @author Esben All rights belong to respective contributors.
 */
public class PersonFacade implements IPersonfacade {

    private EntityManagerFactory emf;

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Person addPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            
        return p;
        } finally {
            em.close();
        }
    }

    @Override
    public Person deletePerson(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Person p = (Person) em.createQuery("select a from Person a where a.id = :id").setParameter("id", id).getSingleResult();
            em.createQuery("delete from Person where id = :id").setParameter("id", id).executeUpdate();
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    @Override
    public Person getPerson(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("select a from Person a where a.id = :id").setParameter("id", id);
            return (Person) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        try{
            Query q = em.createQuery("Select a from Person a");
            List<Person> persons = q.getResultList();
            return persons;
        }finally{
            em.close();
        }
    }

    @Override
    public Person editPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        try{
            Person pOG = em.find(Person.class, p.getId());
            em.getTransaction().begin();
//            Query q = em.createQuery("update Person set firstName = "+p.getFirstName()+", lastName = "+p.getLastName()+", phone = "+p.getPhone()+" where id = :id").setParameter("id", p.getId());
            pOG.setFirstName(p.getFirstName());
            pOG.setLastName(p.getLastName());
            pOG.setPhone(p.getPhone());
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return p;
    }

}
