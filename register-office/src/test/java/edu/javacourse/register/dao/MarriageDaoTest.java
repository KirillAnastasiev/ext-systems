package edu.javacourse.register.dao;

import edu.javacourse.register.domain.MarriageCertificate;
import edu.javacourse.register.view.MarriageRequest;
import org.junit.Before;
import org.junit.Test;

public class MarriageDaoTest {
    private MarriageRequest request;

    @Before
    public void setUp() {
        request = new MarriageRequest();
        request.setMarriageCertificateNumber("12345");
    }
    @Test
    public void findMarriageCertificate() {
        MarriageDao dao = new MarriageDao();
        MarriageCertificate certificate = dao.findMarriageCertificate(request);

        System.out.println("Certificate number: " + certificate.getNumber());
        System.out.println("Certificate issue date: " + certificate.getIssueDate());
        System.out.println("Husband : " + certificate.getHusband().getFirstName() + " " + certificate.getHusband().getLastName());
        System.out.println("Wife : " + certificate.getWife().getFirstName() + " " + certificate.getWife().getLastName());
        System.out.println("Is active: " + certificate.isActive());
    }
}