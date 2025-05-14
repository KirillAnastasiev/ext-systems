package edu.javacourse.register.dao;

import edu.javacourse.register.domain.MarriageCertificate;
import edu.javacourse.register.view.MarriageRequest;
import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MarriageDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${test.value}")
    private String test;

    public void setTest(String test) {
        this.test = test;
    }

    public MarriageCertificate findMarriageCertificate(MarriageRequest request) {
        LOGGER.info("findMarriageCertificate() called: {}", test);
        Query query = entityManager.createNamedQuery("MarriageCertificate.findMarriageCertificate", MarriageCertificate.class);
        query.setParameter("certificateNumber", request.getMarriageCertificateNumber());

        return (MarriageCertificate) query.getSingleResult();
    }
}
