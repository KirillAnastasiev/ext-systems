package edu.javacourse.city.web;

import edu.javacourse.city.dao.PersonCheckDao;
import edu.javacourse.city.dao.PoolConnectionBuilder;
import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.exception.PersonCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "CheckPersonServlet", urlPatterns = {"/checkPerson"})
public class CheckPersonServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CheckPersonServlet.class);

    private PersonCheckDao dao;

    @Override
    public void init() throws ServletException {
        logger.info("SERVLET is created");
        dao = new PersonCheckDao();
        dao.setConnectionBuilder(new PoolConnectionBuilder());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        PersonRequest pr = new PersonRequest();
        pr.setSurName(req.getParameter("surName"));
        pr.setGivenName(req.getParameter("givenName"));
        pr.setPatronymic(req.getParameter("patronymic"));
        LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"), DateTimeFormatter.ofPattern("yyyy-dd-MM"));
        pr.setDateOfBirth(dateOfBirth);
        pr.setStreetCode(Integer.parseInt(req.getParameter("streetCode")));
        pr.setBuilding(req.getParameter("building"));
        pr.setExtension(req.getParameter("extension"));
        pr.setApartment(req.getParameter("apartment"));

        try {
            PersonResponse response = dao.checkPerson(pr);
            if (response.isRegistered()) {
                resp.getWriter().write("Registered");
            } else {
                resp.getWriter().write("Not registered");
            }
        } catch (PersonCheckException e) {
            e.printStackTrace();
        }
    }
}
