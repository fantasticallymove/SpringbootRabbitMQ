package com.example.test_mulitple_schedule.jpa;

import com.example.test_mulitple_schedule.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeesService {
    private final EmployeeRepository repository;

    @Autowired
    public EmployeesService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void bulkSave(String name) {
        List<Employee> list = new ArrayList<>();
        for (int count = 0; count < 30000; count++) {
            Employee employee = new Employee();
            employee.setName(name);
            employee.setAge(count);
            list.add(employee);
        }

        repository.saveAll(list);
    }

    public void save(String name){
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(32);
        repository.save(employee);
    }
}
