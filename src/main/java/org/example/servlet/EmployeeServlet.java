package org.example.servlet;

import lombok.SneakyThrows;
import org.example.base.di.Inject;
import org.example.domain.model.Employee;
import org.example.domain.model.mapper.EmployeeMapper;
import org.example.domain.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "employee", urlPatterns = "/employee/*")
public class EmployeeServlet extends HttpServlet {

    @Inject
    public Repository<Employee, Integer> repository;

    public EmployeeServlet() { }

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = new Integer(req.getPathInfo().replace("/", ""));
        Employee e = repository.findOneById(id);
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().println(EmployeeMapper.toJsonHateoasString(e));
    }
}
