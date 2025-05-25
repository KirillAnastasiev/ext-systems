package edu.javacourse.register.config;

import edu.javacourse.register.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.TransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
@PropertySource("classpath:/register.properties")
public class MarriageConfig {

    private ApplicationContext context;

    @Autowired
    public MarriageConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public PersonDao buildPersonDao() {
        System.out.println("PersonDao is created");
        return new PersonDao();
    }

}
