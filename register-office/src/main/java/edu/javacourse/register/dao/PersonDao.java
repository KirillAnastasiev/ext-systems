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
        query.setParameter("personId", 1L);

        return query.getResultList();
    }
}
