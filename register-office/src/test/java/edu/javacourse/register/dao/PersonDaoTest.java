package edu.javacourse.register.dao;

import edu.javacourse.register.domain.Person;
import edu.javacourse.register.domain.PersonFemale;
import edu.javacourse.register.domain.PersonMale;
import org.junit.Test;

import java.util.List;

public class PersonDaoTest {
    @Test
    public void findPersons() {
        PersonDao dao = new PersonDao();
        List<Person> persons = dao.findPersons();

        persons.forEach(p -> {
            System.out.println("Name: " + p.getFirstName());
            System.out.println("Class for sex: " + p.getClass().getName());
            System.out.println("Passports: " + p.getPassports().size());
            System.out.println("Birth certificate: " + p.getBirthCertificate());
            if (p instanceof PersonMale) {
                System.out.println("Birth Cert: " + ((PersonMale) p).getBirthCertificates().size());
                System.out.println("Marriage Cert: " + ((PersonMale) p).getMarriageCertificates().size());
            } else {
                System.out.println("Birth Cert: " + ((PersonFemale) p).getBirthCertificates().size());
                System.out.println("Marriage Cert: " + ((PersonFemale) p).getMarriageCertificates().size());
            }
        });
    }
}