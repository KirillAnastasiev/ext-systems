package edu.javacourse.register.dao;

import edu.javacourse.register.domain.Person;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import java.util.List;

public class PersonDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> findPersons() {
        TypedQuery<Person> query = entityManager.createNamedQuery("Person.findPersons", Person.class);

        return query.getResultList();
    }

    public Long addPerson(Person p) {
        entityManager.persist(p);
        entityManager.flush();
        return p.getPersonId();
    }
}
