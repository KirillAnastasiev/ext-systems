package edu.javacourse.register.rest;

import edu.javacourse.register.business.MarriageManager;
import edu.javacourse.register.view.MarriageRequest;
import edu.javacourse.register.view.MarriageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("controller")
public class MarriageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageController.class);

    private MarriageManager marriageManager;

    @Autowired
    public MarriageController(@Qualifier("marriageService") MarriageManager marriageManager) {
        this.marriageManager = marriageManager;
    }

    public void setMarriageManager(MarriageManager marriageManager) {
        this.marriageManager = marriageManager;
    }

    public MarriageResponse findMarriageCertificate(MarriageRequest request) {
        LOGGER.info("findMarriageCertificate() called");
        return marriageManager.findMarriageCertificate(request);
    }
}
