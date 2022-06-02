package com.example.test_mulitple_schedule.jpa;

import com.example.test_mulitple_schedule.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
