package org.example.repository;

import org.example.domain.model.Employee;
import org.example.domain.repository.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepository implements Repository<Employee, Integer> {
    private Map<Integer, Employee> dataset = new HashMap<>();

    public EmployeeRepository() {
        dataset.put(1, new Employee(1, "Binh", "Haiphong"));
        dataset.put(2, new Employee(2, "Quynh", "Thainguyen"));
        dataset.put(3, new Employee(3, "Van", "Haiphong"));
        dataset.put(4, new Employee(4, "Van", "Vinhphuc"));
        dataset.put(5, new Employee(5, "Quynh Anh", "Haiphong"));
    }

    @Override
    public Employee findOneById(Integer integer) {
        return dataset.get(integer);
    }

    @Override
    public List<Employee> findAll() {
        return (List<Employee>)dataset.values();
    }

    @Override
    public Employee removeOne(Integer integer) {
        return null;
    }

    @Override
    public List<Employee> removeAll() {
        return null;
    }
}
