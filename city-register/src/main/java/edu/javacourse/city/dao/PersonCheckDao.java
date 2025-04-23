package edu.javacourse.city.dao;

import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.exception.PersonCheckException;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class PersonCheckDao {
    private static final String DB_URL = "db.url";
    private static final String DB_LOGIN = "db.login";
    private static final String DB_PASSWORD = "db.password";

    private static final String SQL_REQUEST = "SELECT temporal FROM cr_address_person AS ap " +
            "JOIN cr_person AS p USING (person_id) JOIN cr_address AS a USING (address_id) " +
            "WHERE CURRENT_DATE >= ap.start_date AND (CURRENT_DATE <= ap.end_date OR ap.end_date IS NULL) " +
            "AND upper(p.sur_name) = upper(?) AND upper(p.given_name) = upper(?) " +
            "AND upper(p.patronymic) = upper(?) AND p.date_of_birth = ? AND a.street_code = ? " +
            "AND upper(a.building) = upper(?) ";

    private ConnectionBuilder connectionBuilder;

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();

        String sql = SQL_REQUEST;
        if (request.getExtension() != null) {
            sql += "AND upper(a.extension) = upper(?) ";
        } else {
            sql += "AND a.extension IS NULL ";
        }

        if (request.getApartment() != null) {
            sql += "AND upper(a.apartment) = upper(?)";
        } else {
            sql += "AND a.apartment IS NULL";
        }

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            int paramIdx = 1;
            stmt.setString(paramIdx++, request.getSurName());
            stmt.setString(paramIdx++, request.getGivenName());
            stmt.setString(paramIdx++, request.getPatronymic());
            stmt.setDate(paramIdx++, Date.valueOf(request.getDateOfBirth()));
            stmt.setInt(paramIdx++, request.getStreetCode());
            stmt.setString(paramIdx++, request.getBuilding());
            if (request.getExtension() != null) {
                stmt.setString(paramIdx++, request.getExtension());
            }
            if (request.getApartment() != null) {
                stmt.setString(paramIdx, request.getApartment());
            }

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                response.setRegistered(true);
                response.setTemporal(rs.getBoolean("temporal"));
            }
        } catch (SQLException e) {
            throw new PersonCheckException(e);
        }

        return response;
    }

    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }
}
