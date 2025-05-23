package edu.javacourse.city.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnectionBuilder implements ConnectionBuilder {
    private static final Logger logger = LoggerFactory.getLogger(PoolConnectionBuilder.class);

    private DataSource dataSource;

    public PoolConnectionBuilder() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/cityRegister");
        } catch (NamingException e) {
            logger.error(e.toString());
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
