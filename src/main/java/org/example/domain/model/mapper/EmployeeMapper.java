package org.example.domain.model.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.model.Employee;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

public class EmployeeMapper {
    public static String toJsonString(Employee e) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(e);
    }

    public static String toJsonHateoasString(Employee e) throws JsonProcessingException {
        Link self = new Link("/employee/".concat(Integer.toString(e.getId())));
        Link next = new Link("/employee/".concat(Integer.toString(e.getId() + 1)), "next");
        EntityModel<Employee> employeeEntityModel = new EntityModel<>(e, self, next);
        return new ObjectMapper().writeValueAsString(employeeEntityModel);
    }
}
